package org.geogebra.desktop.geogebra3D.euclideanForPlane;

import org.geogebra.common.euclidean.euclideanController;
import org.geogebra.common.euclidean.euclideanViewCompanion;
import org.geogebra.common.euclideanForPlane.euclideanViewForPlaneInterface;
import org.geogebra.common.geogebra3D.euclideanForPlane.euclideanViewForPlaneCompanion;
import org.geogebra.common.kernel.kernelND.ViewCreator;
import org.geogebra.common.main.settings.euclideanSettings;
import org.geogebra.desktop.euclidean.euclideanStyleBarD;
import org.geogebra.desktop.euclidean.euclideanViewD;

/**
 * 2D view for plane.
 * 
 * @author Mathieu
 *
 */
public class euclideanViewForPlaneD extends euclideanViewD
		implements euclideanViewForPlaneInterface {

	/**
	 * 
	 * @param ec
	 *            controller
	 * @param plane
	 *            plane creating this view
	 * @param settings
	 *            euclidean settings
	 */
	public euclideanViewForPlaneD(euclideanController ec, ViewCreator plane,
			euclideanSettings settings) {
		super(ec, new boolean[] { false, false }, false, EVNO_GENERAL,
				settings); // TODO
							// euclidean
							// settings

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

	@Override
	protected euclideanStyleBarD neweuclideanStyleBar() {
		return new euclideanStyleBarForPlaneD(this);
	}

	@Override
	public int getViewID() {
		return panelID;
	}

	private int panelID;

	/**
	 * set panel id
	 * 
	 * @param panelID
	 *            panel id
	 */
	public void setPanelID(int panelID) {
		this.panelID = panelID;
	}

}
