package org.geogebra.web.geogebra3D.web.main;

import java.util.ArrayList;

import org.geogebra.common.geogebra3D.euclideanForPlane.euclideanViewForPlaneCompanion;
import org.geogebra.common.geogebra3D.main.App3DCompanion;
import org.geogebra.common.gui.layout.DockPanel;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.kernelND.ViewCreator;
import org.geogebra.common.main.App;
import org.geogebra.common.main.settings.euclideanSettings;
import org.geogebra.web.full.gui.layout.DockManagerW;
import org.geogebra.web.full.gui.layout.LayoutW;
import org.geogebra.web.geogebra3D.web.euclideanForPlane.euclideanControllerForPlaneW;
import org.geogebra.web.geogebra3D.web.euclideanForPlane.euclideanViewForPlaneW;
import org.geogebra.web.geogebra3D.web.gui.layout.panels.euclideanDockPanelForPlaneW;

/**
 * 
 * @author mathieu
 *
 *         Companion for 3D application in desktop
 */
public class App3DCompanionW extends App3DCompanion {

	private euclideanDockPanelForPlaneW panel;
	private ArrayList<euclideanDockPanelForPlaneW> panelForPlaneList;

	/**
	 * constructor
	 * 
	 * @param app
	 *            application
	 */
	public App3DCompanionW(App app) {
		super(app);
	}

	@Override
	protected euclideanViewForPlaneCompanion createeuclideanViewForPlane(
	        ViewCreator plane, euclideanSettings evSettings,
	        boolean panelSettings) {

		// create dock panel
		panel = new euclideanDockPanelForPlaneW(app, incViewID());

		panel.loadComponent();
		euclideanViewForPlaneW view = new euclideanViewForPlaneW(panel,
		        new euclideanControllerForPlaneW(app.getKernel()), plane,
		        evSettings, panel.getViewId());
		panel.setView(view);

		((LayoutW) app.getGuiManager().getLayout()).registerPanel(panel);

		if (panelSettings) {
			// panel.setFrameBounds(new Rectangle(600, 400));
			panel.setVisible(true);
			// panel.toggleStyleBar();

			((LayoutW) app.getGuiManager().getLayout()).getDockManager().show(
			        panel);

		}

		return view.getCompanion();
	}

	/**
	 * 
	 * @return current dockpanel for plane
	 */
	@Override
	public DockPanel getPanelForPlane() {
		return panel;
	}

	@Override
	public void storeViewCreators() {

		if (panelForPlaneList == null) {
			panelForPlaneList = new ArrayList<>();
		} else {
			panelForPlaneList.clear();
		}

		DockPanel[] panels = ((DockManagerW) app.getGuiManager().getLayout()
		        .getDockManager()).getPanels();
		for (int i = 0; i < panels.length; i++) {
			if (panels[i] instanceof euclideanDockPanelForPlaneW) {
				panelForPlaneList.add((euclideanDockPanelForPlaneW) panels[i]);
			}
		}

	}

	@Override
	public void recallViewCreators() {

		for (euclideanDockPanelForPlaneW p : panelForPlaneList) {
			euclideanViewForPlaneW view = p.getView();
			GeoElement geo = app.getKernel().lookupLabel(
			        ((GeoElement) view.getCompanion().getPlane())
			                .getLabelSimple());
			if (geo != null && (geo instanceof ViewCreator)) {
				ViewCreator plane = (ViewCreator) geo;
				view.getCompanion().setPlane(plane);
				plane.seteuclideanViewForPlane(view.getCompanion());
				view.getCompanion().updateForPlane();
			} else {
				// no more creator : remove
				p.getView().getCompanion().doRemove();
			}
		}
	}

	/**
	 * recalculates views environments.
	 */
	public void recalculateEnvironments() {
		if (euclideanViewForPlaneCompanionList == null) {
			return;
		}
		for (euclideanViewForPlaneCompanion vfpc : euclideanViewForPlaneCompanionList) {
			vfpc.getView().geteuclideanController().calculateEnvironment();
		}
	}

	/**
	 * update view for plane sizes
	 */
	public void updateViewSizes() {
		if (euclideanViewForPlaneCompanionList == null) {
			return;
		}
		for (euclideanViewForPlaneCompanion vfpc : euclideanViewForPlaneCompanionList) {
			((euclideanViewForPlaneW) vfpc.getView()).getDockPanel()
			        .deferredOnResize();
		}
	}

}
