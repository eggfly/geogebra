package org.geogebra.web.geogebra3D.web.euclideanForPlane;

import org.geogebra.common.euclidean.euclideanController;
import org.geogebra.common.euclidean.euclideanStyleBar;
import org.geogebra.common.euclidean.euclideanViewCompanion;
import org.geogebra.common.euclideanForPlane.euclideanViewForPlaneInterface;
import org.geogebra.common.geogebra3D.euclideanForPlane.euclideanViewForPlaneCompanion;
import org.geogebra.common.kernel.kernelND.ViewCreator;
import org.geogebra.common.main.settings.euclideanSettings;
import org.geogebra.web.geogebra3D.web.gui.layout.panels.euclideanDockPanelForPlaneW;
import org.geogebra.web.html5.euclidean.euclideanPanelWAbstract;
import org.geogebra.web.html5.euclidean.euclideanViewW;

import com.google.gwt.user.client.ui.Widget;

/**
 * 2D view for plane.
 * 
 * @author Mathieu
 *
 */
public class euclideanViewForPlaneW extends euclideanViewW implements
        euclideanViewForPlaneInterface {

	private int panelID;

	/**
	 * 
	 * @param euclideanViewPanel
	 *            view panel
	 * @param ec
	 *            controller
	 * @param plane
	 *            plane creating this view
	 * @param settings
	 *            euclidean settings
	 * @param panelID
	 *            id of the view
	 */
	public euclideanViewForPlaneW(euclideanPanelWAbstract euclideanViewPanel,
	        euclideanController ec, ViewCreator plane,
	        euclideanSettings settings, int panelID) {
		super(euclideanViewPanel, ec, EVNO_GENERAL, settings);
		this.panelID = panelID;
		((euclideanViewForPlaneCompanion) companion).initView(plane);
	}

	@Override
	protected euclideanViewCompanion neweuclideanViewCompanion() {
		return new euclideanViewForPlaneCompanion(this);
	}

	@Override
	public euclideanViewForPlaneCompanion getCompanion() {
		return (euclideanViewForPlaneCompanion) super.getCompanion();
	}

	/**
	 * @return panel component
	 */
	public Widget getComponent() {
		return getAbsolutePanel();
	}

	// @Override
	// public final void repaint() {
	//
	// // temporary hack : use timer instead
	// doRepaint();
	// }

	/**
	 * 
	 * @return dock panel
	 */
	public euclideanDockPanelForPlaneW getDockPanel() {
		return (euclideanDockPanelForPlaneW) evPanel;
	}

	@Override
	protected euclideanStyleBar neweuclideanStyleBar() {
		if (getApplication().getGuiManager() == null) {
			return null;
		}
		return new euclideanStyleBarForPlaneW(this, panelID);
	}

	@Override
	public int getViewID() {
		return panelID;
	}
}
