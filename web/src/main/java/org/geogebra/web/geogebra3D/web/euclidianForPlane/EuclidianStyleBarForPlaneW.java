package org.geogebra.web.geogebra3D.web.euclideanForPlane;

import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.geogebra3D.euclideanForPlane.euclideanViewForPlaneCompanion;
import org.geogebra.common.main.OptionType;
import org.geogebra.web.full.euclidean.euclideanStyleBarW;

/**
 * Stylebar for view for plane
 * 
 * @author Mathieu
 */
public class euclideanStyleBarForPlaneW extends euclideanStyleBarW {

	/**
	 * @param ev
	 *            view
	 * @param viewID
	 *            view ID
	 */
	public euclideanStyleBarForPlaneW(euclideanView ev, int viewID) {
		super(ev, viewID);
	}

	@Override
	protected void setOptionType() {
		optionType = OptionType.euclidean_FOR_PLANE;
	}

	@Override
	protected void setEvStandardView() {
		euclideanViewForPlaneCompanion companion = (euclideanViewForPlaneCompanion) getView()
				.getCompanion();
		companion.updateCenterAndOrientationRegardingView();
		companion.updateScaleRegardingView();
	}
}
