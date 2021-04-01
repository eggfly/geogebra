package org.geogebra.web.full.gui.layout.panels;

import org.geogebra.common.euclidean.euclideanStyleBar;
import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.main.App;
import org.geogebra.web.html5.euclidean.euclideanPanelWAbstract;
import org.geogebra.web.resources.SVGResource;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.dom.client.Style;
import com.google.gwt.resources.client.ResourcePrototype;
import com.google.gwt.user.client.ui.Widget;

/**
 * This class may be redundant since euclideanDockPanelW, but GeoGebra Desktop
 * also uses two different classes for similar purposes, so its behaviour was
 * imitated here.
 * 
 * @author arpad
 */

public class euclidean2DockPanelW extends euclideanDockPanelWAbstract
		implements euclideanPanelWAbstract {

	euclideanStyleBar espanel;
	euclideanPanel euclideanpanel;

	Canvas eview1 = null; // static foreground
	private int idx;

	/**
	 * @param stylebar
	 *            allow stylebar?
	 * @param idx
	 *            index for app.geteuclideanView2(idx)
	 */
	public euclidean2DockPanelW(boolean stylebar, int idx) {
		super(App.VIEW_euclidean2, // view id
				"DrawingPad2", // view title
				// ToolBar.getAllToolsNoMacros(true), // toolbar string... TODO:
				// ToolBarW.getAllTools(app);
				null, stylebar, // style bar?
				false, // zoom panel
				6, // menu order
				'2' // ctrl-shift-1
		);

		this.idx = idx;
		// copied here from loadComponent
		setEmbeddedSize(300);

		// this should execute when DockPanelW.register is not called
		if (!stylebar) {
			buildDockPanel();
		}
	}

	@Override
	protected Widget loadComponent() {
		if (euclideanpanel == null) {
			euclideanpanel = new euclideanPanel(this);
			eview1 = Canvas.createIfSupported();
			eview1.getElement().getStyle().setPosition(Style.Position.RELATIVE);
			eview1.getElement().getStyle().setZIndex(0);
			euclideanpanel.getAbsolutePanel().add(eview1);
		}

		// euclidean2DockPanelW.loadComponent will be called lazy,
		// so it is this place where euclideanView 2 should be inited
		// in euclideanDockPanelW, euclideanView is created automatically
		if (app != null) {
			app.geteuclideanView2(1);
		}

		return euclideanpanel;
	}

	@Override
	public SVGResource getViewIcon() {
		return getResources().styleBar_graphics2View();
	}

	@Override
	protected Widget loadStyleBar() {
		if (espanel == null) {
			espanel = app.geteuclideanView2(idx).getStyleBar();
		}

		return (Widget) espanel;
	}

	@Override
	public Canvas getCanvas() {
		return eview1;
	}

	public void remove(Widget w) {
		euclideanpanel.remove(w);
	}

	public euclidean2DockPanelW geteuclideanView2Wrapper() {
		return this;
	}

	@Override
	public euclideanPanel geteuclideanPanel() {
		return euclideanpanel;
	}

	@Override
	public euclideanView geteuclideanView() {
		if (app != null && app.haseuclideanView2(idx)) {
			return app.geteuclideanView2(idx);
		}
		return null;
	}

	@Override
	public ResourcePrototype getIcon() {
		return getResources().menu_icon_graphics2();
	}

	@Override
	public void calculateEnvironment() {
		if (app.haseuclideanView2EitherShowingOrNot(1)) {
			app.geteuclideanView2(1).geteuclideanController()
					.calculateEnvironment();
		}
	}

	@Override
	public void resizeView(int width, int height) {
		app.ggwGraphicsView2DimChanged(width, height);
	}

}
