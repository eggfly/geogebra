package org.geogebra.web.full.gui.view.data;

import java.util.ArrayList;

import org.geogebra.common.awt.GPoint;
import org.geogebra.common.euclidean.Hits;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.web.html5.euclidean.euclideanControllerW;

/**
 * @author gabor
 * 
 * euclideanController for plot panel in web
 *
 */
public class PlotPaneleuclideanControllerW extends euclideanControllerW {

	/**
	 * @param kernel Kernel
	 */
	public PlotPaneleuclideanControllerW(Kernel kernel) {
	    super(kernel);
    }
	
	@Override
	public void showDrawingPadPopup(GPoint mouse) {
		// do nothing		
	}
	
	@Override
	public void wrapMouseWheelMoved(int x, int y, double delta,
	        boolean shiftOrMeta, boolean alt) {
		//scolling disabled
	}

	@Override
	protected void showPopupMenuChooseGeo(ArrayList<GeoElement> selectedGeos1,
			Hits hits) {
		// kill menu in plot panel
	}
		
}
