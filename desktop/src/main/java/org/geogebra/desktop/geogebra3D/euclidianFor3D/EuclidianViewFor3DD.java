package org.geogebra.desktop.geogebra3D.euclideanFor3D;

import org.geogebra.common.euclidean.euclideanController;
import org.geogebra.common.euclidean.euclideanViewCompanion;
import org.geogebra.common.geogebra3D.euclideanFor3D.euclideanViewFor3DCompanion;
import org.geogebra.common.main.settings.euclideanSettings;
import org.geogebra.desktop.euclidean.euclideanViewD;

/**
 * Simple extension of euclideanView to implement handling of 3D objects
 * 
 * @author Mathieu
 * 
 */
public class euclideanViewFor3DD extends euclideanViewD {

	/**
	 * @param ec
	 *            controller
	 * @param showAxes
	 *            show the axes
	 * @param showGrid
	 *            shos the grid
	 * @param evno
	 *            dock panel id
	 * @param settings
	 *            euclidean settings
	 */
	public euclideanViewFor3DD(euclideanController ec, boolean[] showAxes,
			boolean showGrid, int evno, euclideanSettings settings) {
		super(ec, showAxes, showGrid, evno, settings);

	}

	@Override
	protected euclideanViewCompanion neweuclideanViewCompanion() {
		return new euclideanViewFor3DCompanion(this);
	}

}
