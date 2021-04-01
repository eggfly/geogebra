package org.geogebra.common.jre.headless;

import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.geogebra3D.euclidean3D.euclideanController3D;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.main.App;

public class euclideanController3DNoGui extends euclideanController3D {

	/**
	 * @param app
	 *            app
	 * @param kernel
	 *            kernel
	 */
	public euclideanController3DNoGui(App app, Kernel kernel) {
		super(app);
		this.kernel = kernel;
	}

	@Override
	protected void initToolTipManager() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void resetToolTipManager() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setView(euclideanView view) {
		super.setView3D(view);

	}

}
