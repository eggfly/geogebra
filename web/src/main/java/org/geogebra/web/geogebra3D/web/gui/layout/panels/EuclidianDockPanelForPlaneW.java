package org.geogebra.web.geogebra3D.web.gui.layout.panels;

import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.factories.AwtFactory;
import org.geogebra.common.gui.toolbar.ToolBar;
import org.geogebra.common.io.layout.DockPanelData;
import org.geogebra.common.main.App;
import org.geogebra.common.main.settings.euclideanSettings;
import org.geogebra.ggbjdk.java.awt.geom.Rectangle;
import org.geogebra.web.full.gui.layout.panels.euclideanDockPanelWAbstract;
import org.geogebra.web.geogebra3D.web.euclideanForPlane.euclideanViewForPlaneW;
import org.geogebra.web.html5.euclidean.euclideanPanelWAbstract;
import org.geogebra.web.html5.main.AppW;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.dom.client.Style;
import com.google.gwt.resources.client.ResourcePrototype;
import com.google.gwt.user.client.ui.Widget;

/**
 * Dock panel for view for plane
 *
 */
public class euclideanDockPanelForPlaneW extends euclideanDockPanelWAbstract
        implements euclideanPanelWAbstract {

	/**
	 * default width of this panel
	 */
	public static final int DEFAULT_WIDTH = 480;

	private euclideanViewForPlaneW view;

	/** pcontent panel */
	euclideanPanel euclideanpanel;

	/** static foreground */
	Canvas eview1 = null;

	/**
	 * constructor
	 * 
	 * @param app
	 *            application
	 * @param viewId
	 *            view ID
	 * 
	 */
	public euclideanDockPanelForPlaneW(App app, int viewId) {
		super(viewId, // view id
		        "GraphicsViewForPlaneA", // view title
		        ToolBar.getAllToolsNoMacrosForPlane(), // toolbar string
		        true, // style bar?
				false, // zoom panel
		        -1, // menu order
		        'p');

		this.app = (AppW) app;
		this.setEmbeddedSize(DEFAULT_WIDTH);
	}

	@Override
	public boolean canCustomizeToolbar() {
		return false;
	}

	/**
	 * set the view attached
	 * 
	 * @param view
	 *            euclidean view
	 */
	public void setView(euclideanViewForPlaneW view) {
		this.view = view;
		view.getCompanion().setDockPanel(this);
	}

	@Override
	public Widget loadComponent() {
		setViewImage(getResources().styleBar_graphics_extra());
		if (euclideanpanel == null) {
			euclideanpanel = new euclideanPanel(this);
			eview1 = Canvas.createIfSupported();
			eview1.getElement().getStyle().setPosition(Style.Position.RELATIVE);
			eview1.getElement().getStyle().setZIndex(0);
			euclideanpanel.getAbsolutePanel().add(eview1);
		}

		return euclideanpanel;
	}

	@Override
	protected Widget loadStyleBar() {
		return (Widget) view.getStyleBar();
	}

	/**
	 * 
	 * @return view attached in this panel
	 */
	public euclideanViewForPlaneW getView() {
		return view;
	}

	@Override
	public Canvas getCanvas() {
		return eview1;
	}

	@Override
	public euclideanPanel geteuclideanPanel() {
		return euclideanpanel;
	}

	@Override
	public euclideanView geteuclideanView() {
		return view;
	}

	@Override
	public DockPanelData createInfo() {
		return new DockPanelData(id, getToolbarString(), visible,
				false, showStyleBar, new Rectangle(frameBounds),
				embeddedDef, embeddedSize, view.getFromPlaneString());
	}

	@Override
    public ResourcePrototype getIcon() {
		return getResources().menu_icon_graphics_extra();
	}

	@Override
	public boolean hasPlane() {
		return true;
	}

	@Override
	public void calculateEnvironment() {
		view.geteuclideanController().calculateEnvironment();

	}

	@Override
	public void resizeView(int width, int height) {

		final euclideanSettings settings = app.getSettings()
				.geteuclideanForPlane(
						view.getCompanion().getPlane()
								.getLabelSimple());
		settings.setPreferredSize(
				AwtFactory.getPrototype()
				.newDimension(width, height));

		view.synCanvasSize();
		view.doRepaint2();
	}

	@Override
	protected ResourcePrototype getViewIcon() {
		return null;
	}

}
