package org.geogebra.web.geogebra3D.web.euclidean3D;

import org.geogebra.common.awt.GDimension;
import org.geogebra.common.awt.GFont;
import org.geogebra.common.awt.GGraphics2D;
import org.geogebra.common.awt.GPoint;
import org.geogebra.common.euclidean.CoordSystemAnimation;
import org.geogebra.common.euclidean.euclideanController;
import org.geogebra.common.euclidean.euclideanStyleBar;
import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.euclidean.ScreenReaderAdapter;
import org.geogebra.common.geogebra3D.euclidean3D.euclideanController3D;
import org.geogebra.common.geogebra3D.euclidean3D.euclideanView3D;
import org.geogebra.common.geogebra3D.euclidean3D.openGL.Renderer;
import org.geogebra.common.geogebra3D.euclidean3D.printer3D.Format;
import org.geogebra.common.io.MyXMLio;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.GeoText;
import org.geogebra.common.main.App.ExportType;
import org.geogebra.common.main.settings.euclideanSettings;
import org.geogebra.common.util.DoubleUtil;
import org.geogebra.common.util.debug.GeoGebraProfiler;
import org.geogebra.common.util.debug.Log;
import org.geogebra.web.geogebra3D.web.euclidean3D.openGL.RendererWInterface;
import org.geogebra.web.geogebra3D.web.euclidean3D.openGL.RendererWithImplW;
import org.geogebra.web.geogebra3D.web.euclidean3DnoWebGL.RendererWnoWebGL;
import org.geogebra.web.html5.Browser;
import org.geogebra.web.html5.awt.GGraphics2DW;
import org.geogebra.web.html5.euclidean.euclideanPanelWAbstract;
import org.geogebra.web.html5.euclidean.euclideanViewW;
import org.geogebra.web.html5.euclidean.euclideanViewWInterface;
import org.geogebra.web.html5.euclidean.GGraphics2DWI;
import org.geogebra.web.html5.euclidean.IseuclideanController;
import org.geogebra.web.html5.euclidean.MyeuclideanViewPanel;
import org.geogebra.web.html5.euclidean.PointerEventHandler;
import org.geogebra.web.html5.euclidean.ReaderWidget;
import org.geogebra.web.html5.gui.util.CancelEventTimer;
import org.geogebra.web.html5.main.AppW;
import org.geogebra.web.html5.main.GgbFile;
import org.geogebra.web.html5.main.TimerSystemW;

import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.animation.client.AnimationScheduler.AnimationCallback;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.GestureChangeEvent;
import com.google.gwt.event.dom.client.GestureEndEvent;
import com.google.gwt.event.dom.client.GestureStartEvent;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.TouchCancelEvent;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.Widget;

/**
 * 3D view
 * 
 * @author mathieu
 *
 */
public class euclideanView3DW extends euclideanView3D implements
		euclideanViewWInterface {

	private euclideanPanelWAbstract evPanel;

	/** graphics */
	private GGraphics2DWI g2p = null;

	private AnimationScheduler repaintScheduler = AnimationScheduler.get();
	private long lastRepaint;
	private int waitForRepaint = TimerSystemW.SLEEPING_FLAG;
	private int objectsWaitingForNewRepaint = 0;

	private boolean readyToRender = false;

	private ReaderWidget screenReader;

	private AppW appW = (AppW) super.app;

	/**
	 * constructor
	 * 
	 * @param ec
	 *            euclidean controller
	 * @param settings
	 *            euclidean settings
	 */
	public euclideanView3DW(euclideanController3D ec, euclideanSettings settings) {
		super(ec, settings);
		initBaseComponents(evPanel, ec);

		getRenderer().init();
		initAriaDefaults();
	}

	private void initBaseComponents(euclideanPanelWAbstract euclideanViewPanel,
	        euclideanController euclideancontroller) {

		Canvas canvas = euclideanViewPanel.getCanvas();
		setEvNo();
		this.g2p = new GGraphics2DW(canvas);

		updateFonts();
		initView(true);
		attachView();

		euclideancontroller.setView(this);

		registerKeyHandlers(canvas);
		registerMouseTouchGestureHandlers(euclideanViewPanel,
		        (euclideanController3DW) euclideancontroller);

		euclideanSettings es = this.app.getSettings().geteuclidean(3);
		settingsChanged(es);
		es.addListener(this);
		addScreenReader();
	}

	private void initAriaDefaults() {
		Element elem = g2p.getElement();
		if (elem != null) {
			elem.setAttribute("role", "figure");
			elem.setAttribute("aria-label", "3D View");
		}
	}

	private void setEvNo() {
		this.evNo = EVNO_3D;
	}

	@Override
	public void seteuclideanViewNo(int evNo) {
		this.evNo = evNo;
		// this.g2p.getCanvas().getElement().setId("View_"+App.VIEW_euclidean3D);
	}

	private void registerKeyHandlers(Canvas canvas) {
		if (canvas == null) {
			return;
		}

		new euclideanKeyHandler3DW((AppW) app).listenTo(canvas);
	}

	private void registerMouseTouchGestureHandlers(
	        euclideanPanelWAbstract euclideanViewPanel,
	        euclideanController3DW euclideancontroller) {
		Widget absPanel = euclideanViewPanel.getAbsolutePanel();
		absPanel.addDomHandler(euclideancontroller, MouseWheelEvent.getType());

		if (Browser.supportsPointerEvents()) {
			PointerEventHandler pointerHandler = new PointerEventHandler(
					(IseuclideanController) euclideanController,
					euclideancontroller.getOffsets());
			PointerEventHandler.attachTo(absPanel.getElement(), pointerHandler);
			CancelEventTimer.killTouch(absPanel);
		} else {
			absPanel.addDomHandler(euclideancontroller,
					MouseMoveEvent.getType());
			absPanel.addDomHandler(euclideancontroller,
					MouseOverEvent.getType());
			absPanel.addDomHandler(euclideancontroller, MouseOutEvent.getType());
			if (((AppW) app).getLAF() == null
					|| !((AppW) app).getLAF().isSmart()) {
				absPanel.addDomHandler(euclideancontroller,
						MouseDownEvent.getType());
			}
			absPanel.addDomHandler(euclideancontroller, MouseUpEvent.getType());
			absPanel.addBitlessDomHandler(euclideancontroller, TouchStartEvent.getType());
			absPanel.addBitlessDomHandler(euclideancontroller, TouchEndEvent.getType());
			absPanel.addBitlessDomHandler(euclideancontroller, TouchMoveEvent.getType());
			absPanel.addBitlessDomHandler(euclideancontroller, TouchCancelEvent.getType());
			absPanel.addDomHandler(euclideancontroller, GestureStartEvent.getType());
			absPanel.addDomHandler(euclideancontroller, GestureChangeEvent.getType());
			absPanel.addDomHandler(euclideancontroller, GestureEndEvent.getType());
		}
	}

	/**
	 * @return panel component
	 */
	public Widget getComponent() {
		return evPanel.getAbsolutePanel();
	}

	// //////////////////////////////////////////////////////////
	// MyeuclideanViewPanel
	// //////////////////////////////////////////////////////////

	/**
	 * @return EV panel
	 */
	protected MyeuclideanViewPanel newMyeuclideanViewPanel() {
		return new MyeuclideanViewPanel3D(this);
	}

	/**
	 * panel for 3D
	 * 
	 * @author mathieu
	 *
	 */
	private class MyeuclideanViewPanel3D extends MyeuclideanViewPanel implements
	        RequiresResize {

		/**
		 * constructor
		 * 
		 * @param ev
		 *            euclidean view
		 */
		public MyeuclideanViewPanel3D(euclideanView ev) {
			super(ev);
		}

		@Override
		protected Canvas createCanvas() {
			Renderer pRenderer = getRenderer();
			return (Canvas) pRenderer.getCanvas();
		}

		@Override
		public void onResize() {
			super.onResize();
			geteuclideanController().calculateEnvironment();
		}
	}

	/**
	 * tells the view that all is ready for GL rendering
	 */
	public void setReadyToRender() {
		readyToRender = true;
		repaintView();
	}

	@Override
	public void setToolTipText(String plainTooltip) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasFocus() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void requestFocus() {
		// this may be really necessary preventing a tabbing away issue
		// but the reasons of it are not well understood #5158
		// after better understanding, this can probably be merged
		// with the following method (requestFocusInWindow()):
		requestFocusInWindow();
	}

	/**
	 * Gets the coordinate space width of the &lt;canvas&gt;.
	 * 
	 * @return the logical width
	 */
	@Override
	public int getWidth() {
		return g2p == null ? 0
				: (int) (this.g2p.getCoordinateSpaceWidth() / getPixelRatio());
	}

	/**
	 * Gets the coordinate space height of the &lt;canvas&gt;.
	 * 
	 * @return the logical height
	 */
	@Override
	public int getHeight() {
		return g2p == null ? 0
				: (int) (this.g2p.getCoordinateSpaceHeight() / getPixelRatio());
	}

	@Override
	public final boolean isShowing() {
		return g2p != null && g2p.getCanvas() != null
		        && g2p.getCanvas().isAttached() && g2p.getCanvas().isVisible();
	}

	@Override
	protected void createPanel() {
		evPanel = newMyeuclideanViewPanel();

	}

	@Override
	protected Renderer createRenderer() {
		Canvas webGLcanvas = Canvas.createIfSupported();
		if (webGLcanvas == null) {
			return new RendererWnoWebGL(this);
		}
		return new RendererWithImplW(this, webGLcanvas);
	}

	@Override
	protected boolean getShiftDown() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void setDefault2DCursor() {
		setCursorClass("cursor_hit");
	}

	@Override
	public GGraphics2D getTempGraphics2D(GFont fontForGraphics) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GFont getFont() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	final protected void setStyleBarMode(int mode) {
		if (hasStyleBar()) {
			getStyleBar().setMode(mode);
		}
	}

	@Override
	protected void updateSizeKeepDrawables() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean requestFocusInWindow() {
		g2p.getElement().focus();
		return true;
	}

	@Override
	public void setPreferredSize(GDimension preferredSize) {
		if (renderer != null) {
			((RendererWInterface) renderer).setPixelRatio(getPixelRatio());
			renderer.setView(0, 0, preferredSize.getWidth(),
					preferredSize.getHeight());
		}
		if (g2p != null && g2p.getContext() != null) {
			g2p.setPreferredSize(preferredSize);

			updateSize();
			setReIniting(false);
		}
	}

	@Override
	public double getPixelRatio() {
		return ((AppW) app).getPixelRatio();
	}

	@Override
	protected CoordSystemAnimation newZoomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Widget box, GPoint position) {
		if (evPanel != null) {
			evPanel.getAbsolutePanel().add(box,
					position.getX(), position.getY());
		}
	}

	private void setCursorClass(String className) {
		// IMPORTANT: do nothing if we already have the classname,
		// app.resetCursor is VERY expensive in IE
		Canvas canvas = (Canvas) this.getRenderer().getCanvas();
		if (canvas != null && !canvas.getElement().hasClassName(className)) {
			((AppW) this.app).resetCursor();
			canvas.setStyleName("");
			canvas.addStyleName(className);
		}
	}

	@Override
	public void setTransparentCursor() {
		setCursorClass("cursor_transparent");
	}

	@Override
	protected euclideanStyleBar neweuclideanStyleBar() {
		return new euclideanStyleBar3DW(this);
	}

	@Override
	public int getAbsoluteTop() {
		return g2p.getAbsoluteTop();
	}

	@Override
	public int getAbsoluteLeft() {
		return g2p.getAbsoluteLeft();
	}

	@Override
	public Element getCanvasElement() {
		return g2p == null ? null : g2p.getElement();
	}

	/**
	 * the file has been set by the App
	 * 
	 * @param file
	 *            file
	 */
	public void setCurrentFile(GgbFile file) {
		// used only when no webGL
	}

	private AnimationCallback repaintCallback = new AnimationCallback() {
		@Override
		public void execute(double ts) {
			doRepaint2();
		}
	};

	/**
	 * This doRepaint method should be used instead of repaintView in cases when
	 * the repaint should be done immediately
	 */
	public final void doRepaint2() {
		if (!isParentWindowVisible()) {
			return;
		}
		long time = System.currentTimeMillis();
		// ((DrawEquationWeb) this.app.getDrawEquation()).clearLaTeXes(this);
		this.updateBackgroundIfNecessary();

		// paint(this.g2p);
		if (readyToRender) {
			renderer.drawScene();
		}

		lastRepaint = System.currentTimeMillis() - time;
		GeoGebraProfiler.addRepaint(lastRepaint);

		if (objectsWaitingForNewRepaint > 0) {
			kernel.notifyControllersMoveIfWaiting();
			waitForRepaint = TimerSystemW.euclidean_LOOPS;
			objectsWaitingForNewRepaint--;
		} else {
			waitForRepaint = TimerSystemW.SLEEPING_FLAG;
		}
	}

	@Override
	public long getLastRepaintTime() {
		return lastRepaint;
	}

	@Override
	public void repaint() {
		getApplication().ensureTimerRunning();
		if (waitForRepaint == TimerSystemW.SLEEPING_FLAG) {
			waitForRepaint = TimerSystemW.euclidean_LOOPS;
		}
	}

	@Override
	final public void waitForNewRepaint() {
		objectsWaitingForNewRepaint++;
	}

	/**
	 * schedule a repaint
	 */
	public void doRepaint() {
		repaintScheduler.requestAnimationFrame(repaintCallback);
	}

	/**
	 * timer system suggests a repaint
	 */
	@Override
	public boolean suggestRepaint() {
		if (waitForRepaint == TimerSystemW.SLEEPING_FLAG) {
			return false;
		}

		if (waitForRepaint == TimerSystemW.REPAINT_FLAG) {
			if (isShowing()) {
				doRepaint();
			}
			return true;
		}

		waitForRepaint--;
		return true;
	}

	@Override
	public void exportPaintPre(GGraphics2D g2d, double scale,
	        boolean transparency) {
		// TODO Auto-generated method stub

	}

	@Override
	public GGraphics2DWI getG2P() {
		return g2p;
	}

	@Override
	public void resetPointerEventHandler() {
		// TODO Auto-generated method stub
	}

	@Override
	public String getExportImageDataUrl(double scale, boolean transparent, boolean greyscale) {
		return getExportImageDataUrl(scale, transparent, ExportType.PNG,
				greyscale);
	}

	@Override
	public String getExportImageDataUrl(double scale, boolean transparent,
			ExportType format, boolean greyscale) {
		return getExportCanvas().toDataUrl(
				format == ExportType.WEBP ? "image/webp" : "image/png");
	}

	@Override
	public String getCanvasBase64WithTypeString() {
		((RendererWInterface) this.renderer).setBuffering(true);
		this.doRepaint2();
		String ret = getCanvasBase64WithTypeString(
				this.getWidth(), getHeight());
		((RendererWInterface) this.renderer).setBuffering(false);
		return ret;
	}

	private String getCanvasBase64WithTypeString(double width, double height) {
		Canvas foreground = ((RendererWInterface) this.renderer).getCanvas();
		double ratio = width / height;
		double thx = MyXMLio.THUMBNAIL_PIXELS_X;
		double thy = MyXMLio.THUMBNAIL_PIXELS_Y;
		if (ratio < 1) {
			thx *= ratio;
		} else if (ratio > 1) {
			thy /= ratio;
		}

		Canvas canv = Canvas.createIfSupported();
		canv.setCoordinateSpaceHeight((int) thy);
		canv.setCoordinateSpaceWidth((int) thx);
		canv.setWidth((int) thx + "px");
		canv.setHeight((int) thy + "px");
		Context2d c2 = canv.getContext2d();

		c2.drawImage(foreground.getCanvasElement(), 0, 0, (int) thx,
				(int) thy);

		return euclideanViewW.dataURL(canv, null);
	}

	@Override
	public void setPixelRatio(double pixelRatio) {
		if (DoubleUtil.isEqual(g2p.getDevicePixelRatio(), pixelRatio)
				|| pixelRatio == 0) {
			// GGB-2355 we shouldn't set ratio to 0; quit fast before we get
			// into loop
			return;
		}
		int realWidth = g2p.getOffsetWidth();
		int realHeight = g2p.getOffsetHeight();
		g2p.setDevicePixelRatio(pixelRatio);
		if (realHeight > 0 && realWidth > 0) {
			((AppW) app).ggwGraphicsView3DDimChanged(realWidth, realHeight);
		}
	}

	/**
	 * @param width
	 *            canvas width
	 * @param height
	 *            canvas height
	 */
	public void setCoordinateSpaceSize(int width, int height) {

		// no transform nor color set since it's a WebGL context
		g2p.setCoordinateSpaceSizeNoTransformNoColor(width, height);
		try {
			// just resizing the AbsolutePanelSmart, not the whole of DockPanel
			g2p.getElement().getParentElement().getStyle()
					.setWidth(width, Style.Unit.PX);
			g2p.getElement().getParentElement().getStyle()
					.setHeight(height, Style.Unit.PX);
			geteuclideanController().calculateEnvironment();
		} catch (Exception exc) {
			Log.debug("Problem with the parent element of the canvas");
		}
	}

	@Override
	public void setAltText() {
		String altStr = appW.getLocalization().getMenu("GraphicsView3D");
		GeoElement alt = app.getKernel().lookupLabel("altText3D1");
		if (alt == null) {
			alt = app.getKernel().lookupLabel("altText3D");
		}
		if (alt == null) {
			alt = app.getKernel().lookupLabel("altText");
		}
		if (alt instanceof GeoText) {
			altStr = ((GeoText) alt).getAuralText();
		}
		setAltText(altStr);
	}

	private void setAltText(String text) {
		if (renderer != null && renderer.getCanvas() != null) {
			((Canvas) renderer.getCanvas()).getElement().setInnerText(text);
		} else {
			g2p.setAltText(text);
		}
	}

	@Override
	public ScreenReaderAdapter getScreenReader() {
		return screenReader;
	}

	@Override
	protected void drawBackgroundImage(GGraphics2D g2d) {
		// nothing to do here
	}

	@Override
	protected void addDynamicStylebarToEV(euclideanStyleBar dynamicStylebar) {
		if (app.isUnbundled() && ((AppW) app).allowStylebar()) {
			if (((Widget) dynamicStylebar).getParent() == null) {
				appW.getGuiManager().addStylebar(this, dynamicStylebar);
			}
		}
	}

	@Override
	protected euclideanStyleBar newDynamicStyleBar() {
		if (app.isUnbundled() && ((AppW) app).allowStylebar()) {
			return appW.getGuiManager().newDynamicStylebar(this);
		}
		return null;
	}
	
	@Override
	public void setExport3D(Format format) {
		super.setExport3D(format);
		repaint();
	}

	@Override
	public String getExportSVG(double scale, boolean transparency) {
		return "";
	}

	@Override
	public String getExportPDF(double scale) {
		return null;
	}

	/**
	 * @return whether the frame we are running in is visible
	 */
	private static boolean isParentWindowVisible() {
		return Window.getClientWidth() > 0;
	}

	private void addScreenReader() {
		screenReader = new ReaderWidget(evNo, g2p.getElement());
		euclideanViewW.attachReaderWidget(screenReader, app);
	}

	@Override
	public boolean isAttached() {
		return g2p != null && g2p.isAttached();
	}

	@Override
	public CanvasElement getExportCanvas() {
		RendererWInterface rendererW = (RendererWInterface) this.renderer;
		rendererW.setBuffering(true);
		this.doRepaint2();
		rendererW.setBuffering(true);
		return rendererW.getCanvas().getCanvasElement();
	}
}
