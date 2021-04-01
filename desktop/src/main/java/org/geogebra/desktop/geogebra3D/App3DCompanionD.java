package org.geogebra.desktop.geogebra3D;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.geogebra.common.geogebra3D.euclideanForPlane.euclideanViewForPlaneCompanion;
import org.geogebra.common.geogebra3D.main.App3DCompanion;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.kernelND.ViewCreator;
import org.geogebra.common.main.App;
import org.geogebra.common.main.settings.euclideanSettings;
import org.geogebra.desktop.geogebra3D.euclideanForPlane.euclideanControllerForPlaneD;
import org.geogebra.desktop.geogebra3D.euclideanForPlane.euclideanViewForPlaneD;
import org.geogebra.desktop.geogebra3D.gui.layout.panels.euclideanDockPanelForPlaneD;
import org.geogebra.desktop.gui.layout.DockPanelD;
import org.geogebra.desktop.gui.layout.LayoutD;
import org.geogebra.desktop.main.AppD;

/**
 * 
 * @author mathieu
 *
 *         Companion for 3D application in desktop
 */
public class App3DCompanionD extends App3DCompanion {

	/**
	 * constructor
	 * 
	 * @param app
	 *            application
	 */
	public App3DCompanionD(App app) {
		super(app);
	}

	@Override
	protected euclideanViewForPlaneCompanion createeuclideanViewForPlane(
			ViewCreator plane, euclideanSettings evSettings,
			boolean panelSettings) {
		euclideanViewForPlaneD view = new euclideanViewForPlaneD(
				new euclideanControllerForPlaneD(app.getKernel()), plane,
				evSettings);

		// create dock panel
		panel = new euclideanDockPanelForPlaneD((AppD) app, view, incViewID());

		view.setPanelID(panel.getViewId());

		((LayoutD) app.getGuiManager().getLayout()).registerPanel(panel);

		if (panelSettings) {
			panel.setFrameBounds(new Rectangle(600, 400));
			panel.setVisible(true);
			panel.toggleStyleBar();

			((LayoutD) app.getGuiManager().getLayout()).getDockManager()
					.show(panel);

		}

		return view.getCompanion();
	}

	private euclideanDockPanelForPlaneD panel;

	@Override
	public DockPanelD getPanelForPlane() {
		return panel;
	}

	private ArrayList<euclideanDockPanelForPlaneD> panelForPlaneList;

	@Override
	public void storeViewCreators() {

		if (panelForPlaneList == null) {
			panelForPlaneList = new ArrayList<>();
		} else {
			panelForPlaneList.clear();
		}
		if (app.getGuiManager() != null) {
			DockPanelD[] panels = ((LayoutD) app.getGuiManager().getLayout())
					.getDockManager().getPanels();
			for (int i = 0; i < panels.length; i++) {
				if (panels[i] instanceof euclideanDockPanelForPlaneD) {
					panelForPlaneList
							.add((euclideanDockPanelForPlaneD) panels[i]);
				}
			}
		}

	}

	@Override
	public void recallViewCreators() {

		for (euclideanDockPanelForPlaneD p : panelForPlaneList) {
			euclideanViewForPlaneD view = p.getView();
			GeoElement geo = app.getKernel()
					.lookupLabel(((GeoElement) view.getCompanion().getPlane())
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

}
