package org.geogebra.desktop.gui.view.data;

import org.geogebra.common.awt.GPoint;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.desktop.euclidean.euclideanControllerD;

public class PlotPaneleuclideanControllerD extends euclideanControllerD {

	public PlotPaneleuclideanControllerD(Kernel kernel) {
		super(kernel);
	}

	@Override
	public void showDrawingPadPopup(GPoint mouseLoc) {
		// do nothing
	}
}