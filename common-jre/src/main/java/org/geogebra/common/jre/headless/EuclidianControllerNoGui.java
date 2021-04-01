package org.geogebra.common.jre.headless;

import org.geogebra.common.euclidean.euclideanController;
import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.main.App;

public class euclideanControllerNoGui extends euclideanController {

	/**
	 * @param app
	 *            app
	 * @param kernel1
	 *            kernel
	 */
	public euclideanControllerNoGui(App app, Kernel kernel1) {
		super(app);
		kernel = kernel1;
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
		super.setView(view);

	}

}
