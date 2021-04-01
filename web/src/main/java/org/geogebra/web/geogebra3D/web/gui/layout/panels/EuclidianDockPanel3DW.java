package org.geogebra.web.geogebra3D.web.gui.layout.panels;

import org.geogebra.common.gui.toolbar.ToolBar;
import org.geogebra.common.main.App;
import org.geogebra.web.full.gui.layout.panels.euclideanDockPanelWAbstract;
import org.geogebra.web.geogebra3D.web.euclidean3D.euclideanView3DW;
import org.geogebra.web.geogebra3D.web.gui.ContextMenuGraphicsWindow3DW;
import org.geogebra.web.html5.Browser;
import org.geogebra.web.html5.main.AppW;

import com.google.gwt.resources.client.ResourcePrototype;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Dock panel for 3D
 */
public class euclideanDockPanel3DW extends euclideanDockPanelWAbstract {
	/**
	 * default width of this panel
	 */
	public static final int DEFAULT_WIDTH = 480;
	/** the inner panel */
	euclideanPanel euclideanpanel;

	/**
	 * constructor
	 * 
	 * @param app
	 *            application
	 * 
	 */
	public euclideanDockPanel3DW(App app) {
		super(App.VIEW_euclidean3D, // view id
				"GraphicsView3D", // view title
				ToolBar.getAllToolsNoMacros3D(app), // toolbar string
				true, // style bar?
				true, // zoom panel
				4, // menu order
				'3' // ctrl-shift-3
		);
		this.app = (AppW) app;
		this.setEmbeddedSize(DEFAULT_WIDTH);
	}

	@Override
	protected Widget loadComponent() {
		// 2D app or exam: just flow panel; 3D app in old browser: EVnoWebGL
		if (!app.supportsView(App.VIEW_euclidean3D)
				&& Browser.supportsWebGL()) {
			return new FlowPanel();
		}
		euclideanView3DW view = (euclideanView3DW) app.geteuclideanView3D();
		euclideanpanel = new euclideanPanel(this,
				(AbsolutePanel) view.getComponent());
		return euclideanpanel;
	}

	@Override
	protected Widget loadStyleBar() {
		if (geteuclideanView() == null) {
			return super.loadStyleBar();
		}
		return (Widget) geteuclideanView().getStyleBar();
	}

	@Override
	protected ContextMenuGraphicsWindow3DW getGraphicsWindowContextMenu() {
		return new ContextMenuGraphicsWindow3DW(app, 0, 0);
	}

	@Override
	public euclideanView3DW geteuclideanView() {
		// do NOT initialize the view if it wasn't done previously
		if (app != null && app.iseuclideanView3Dinited()) {
			return (euclideanView3DW) app.geteuclideanView3D();
		}
		return null;
	}

	@Override
	public euclideanPanel geteuclideanPanel() {
		return euclideanpanel;
	}

	@Override
	public void calculateEnvironment() {
		if (app.iseuclideanView3Dinited()) {
			app.geteuclideanView3D().geteuclideanController()
					.calculateEnvironment();
		}
	}

	@Override
	public void resizeView(int width, int height) {
		app.ggwGraphicsView3DDimChanged(width, height);
	}

	@Override
	protected boolean needsResetIcon() {
		return app.showResetIcon() && !app.showView(App.VIEW_euclidean)
				&& !app.showView(App.VIEW_euclidean2);
	}

	@Override
	protected ResourcePrototype getViewIcon() {
		return getResources().styleBar_graphics3dView();
	}

}
