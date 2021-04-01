package org.geogebra.web.geogebra3D.web.euclideanFor3D;

import org.geogebra.common.euclidean.euclideanController;
import org.geogebra.common.euclidean.euclideanViewCompanion;
import org.geogebra.common.geogebra3D.euclideanFor3D.euclideanViewFor3DCompanion;
import org.geogebra.common.main.settings.euclideanSettings;
import org.geogebra.web.html5.euclidean.euclideanPanelWAbstract;
import org.geogebra.web.html5.euclidean.euclideanViewW;

/**
 * Simple extension of euclideanView to implement handling of 3D objects
 * 
 * @author mathieu
 * 
 */
public class euclideanViewFor3DW extends euclideanViewW {

	/**
	 * @param euclideanViewPanel
	 *            parent panel
	 * @param euclideancontroller
	 *            controller
	 * @param evNo
	 *            view number
	 * @param settings
	 *            settings
	 */
	public euclideanViewFor3DW(euclideanPanelWAbstract euclideanViewPanel,
			euclideanController euclideancontroller, int evNo,
			euclideanSettings settings) {
		super(euclideanViewPanel, euclideancontroller, evNo, settings);
	}

	@Override
	protected euclideanViewCompanion neweuclideanViewCompanion() {
		return new euclideanViewFor3DCompanion(this);
	}

}
