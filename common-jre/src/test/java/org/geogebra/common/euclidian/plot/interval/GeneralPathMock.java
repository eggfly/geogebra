package org.geogebra.common.euclidean.plot.interval;

import org.geogebra.common.euclidean.euclideanViewInterfaceSlim;
import org.geogebra.common.euclidean.GeneralPathClipped;
import org.geogebra.common.kernel.MyPoint;

public class GeneralPathMock extends GeneralPathClipped {
	/**
	 * Creates new clipped general path
	 * @param view view
	 */
	public GeneralPathMock(euclideanViewInterfaceSlim view) {
		super(view);
	}

	@Override
	public MyPoint firstPoint() {
		return super.firstPoint();
	}
}
