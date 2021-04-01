package org.geogebra.web.full.gui.layout.panels;

import org.geogebra.common.euclidean.euclideanStyleBar;
import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.main.App;
import org.geogebra.web.html5.euclidean.euclideanPanelWAbstract;
import org.geogebra.web.html5.main.AppW;
import org.geogebra.web.html5.util.TestHarness;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.resources.client.ResourcePrototype;
import com.google.gwt.user.client.ui.Widget;

public class euclideanDockPanelW extends euclideanDockPanelWAbstract
		implements euclideanPanelWAbstract {

	euclideanStyleBar espanel;
	euclideanPanel euclideanpanel;

	Canvas eview1 = null; // static foreground

	/**
	 * This constructor is used by the Application
	 * and by the other constructor
	 * 
	 * @param stylebar
	 *            (is there stylebar?)
	 */
	public euclideanDockPanelW(boolean stylebar) {
		super(
				App.VIEW_euclidean,	// view id
				"DrawingPad", 				// view title
				null,
				stylebar, // style bar?
				true, // zoom panel?
				5,							// menu order
				'1' // ctrl-shift-1
			);

		//TODO: temporary fix to make applets work until
		// dockpanels works for applets
		component = loadComponent();
		if (!stylebar) {
			buildDockPanel();
		}
	}
	
	/**
	 * This constructor is used by the applet
	 * 
	 * @param application
	 *            application
	 * @param stylebar
	 *            whether to use stylebar
	 */
	public euclideanDockPanelW(AppW application, boolean stylebar) {
		this(stylebar);
		attachApp(application);
	}

	/**
	 * @param application
	 *            application
	 */
	public void attachApp(AppW application) {
		app = application;

		// GuiManager can be null at the startup of the application,
		// but then the addNavigationBar method will be called explicitly.
		if (app.getGuiManager() != null
				&& app.showConsProtNavigation(App.VIEW_euclidean)) {
			addNavigationBar();
		}
	}

	@Override
	protected Widget loadComponent() {
		if (euclideanpanel == null) {
			euclideanpanel = new euclideanPanel(this);
			eview1 = Canvas.createIfSupported();
			TestHarness.setAttr(eview1, "euclideanView");
			addCanvas(eview1);
		}
		return euclideanpanel;
	}

	private void addCanvas(Canvas c) {
		if (c != null) {
			euclideanpanel.getAbsolutePanel().add(c);
		}
	}

	@Override
	protected Widget loadStyleBar() {
		if (espanel == null) {
			espanel = app.geteuclideanView1().getStyleBar();
		}
		return (Widget) espanel;
	}

	@Override
	public Canvas getCanvas() {
		return eview1;
	}

	@Override
	public euclideanPanel geteuclideanPanel() {
		return euclideanpanel;
	}

	/**
	 * @param w
	 *            widget to be removed
	 */
	public void remove(Widget w) {
		euclideanpanel.remove(w);
	}

	@Override
	public euclideanView geteuclideanView() {
		if (app != null) {
			return app.geteuclideanView1();
		}
		return null;
	}

	@Override
	public ResourcePrototype getIcon() {
		return getResources().menu_icon_graphics();
	}

	@Override
	public void calculateEnvironment() {
		app.geteuclideanView1().geteuclideanController().calculateEnvironment();
	}

	@Override
	public void resizeView(int width, int height) {
		app.ggwGraphicsViewDimChanged(width, height);
	}

	@Override
	protected ResourcePrototype getViewIcon() {
		return getResources().styleBar_graphicsView();
	}
}
