package org.geogebra.common.geogebra3D.euclidean3D;

import org.geogebra.common.main.App;

/**
 * 3D controller not-displayed 3D view (this controller just avoids NPE)
 */
public class euclideanController3DForExport extends euclideanController3D {

	/**
	 * constructor
	 * 
	 * @param app
	 *            application
	 */
	public euclideanController3DForExport(App app) {
		super(app);
		setKernel(app.getKernel());
	}

	@Override
	protected void initToolTipManager() {
		// no need
	}

	@Override
	protected void resetToolTipManager() {
		// no need
	}

}
