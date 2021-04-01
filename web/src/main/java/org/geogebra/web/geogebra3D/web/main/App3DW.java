package org.geogebra.web.geogebra3D.web.main;

import org.geogebra.common.factories.AwtFactory;
import org.geogebra.common.geogebra3D.input3D.Input3D;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.main.settings.euclideanSettings;
import org.geogebra.common.util.debug.Log;
import org.geogebra.web.geogebra3D.web.euclidean3D.euclideanController3DW;
import org.geogebra.web.geogebra3D.web.euclidean3D.euclideanView3DW;
import org.geogebra.web.geogebra3D.web.euclidean3DnoWebGL.euclideanController3DWnoWebGL;
import org.geogebra.web.geogebra3D.web.euclidean3DnoWebGL.euclideanView3DWnoWebGL;
import org.geogebra.web.geogebra3D.web.input3D.euclideanControllerInput3DW;
import org.geogebra.web.geogebra3D.web.input3D.euclideanViewInput3DW;
import org.geogebra.web.geogebra3D.web.input3D.InputZSpace3DW;
import org.geogebra.web.geogebra3D.web.input3D.ZSpaceGwt;
import org.geogebra.web.html5.Browser;
import org.geogebra.web.html5.main.AppW;

/**
 * @author mathieu
 *
 */
public class App3DW {

	static private boolean useZSpace = false;

	/**
	 * 
	 * @param kernel
	 *            kernel
	 * @return new controller for 3D view
	 */
	static final public euclideanController3DW neweuclideanController3DW(
	        Kernel kernel) {
		if (Browser.supportsWebGL()) {

			useZSpace = ZSpaceGwt.zspaceIsAvailable();
			Log.debug("useZSpace: " + useZSpace);
			if (useZSpace) {
				Input3D input = new InputZSpace3DW();
				return new euclideanControllerInput3DW(kernel, input);
			}

			return new euclideanController3DW(kernel);
		}

		return new euclideanController3DWnoWebGL(kernel);
	}

	/**
	 * 
	 * @param ec
	 *            controller for 3D view
	 * @param settings
	 *            euclidean settings
	 * @return new 3D view
	 */
	static final public euclideanView3DW neweuclideanView3DW(
	        euclideanController3DW ec, euclideanSettings settings) {
		if (Browser.supportsWebGL()) {
			if (useZSpace) {
				return new euclideanViewInput3DW(ec, settings);
			}

			return new euclideanView3DW(ec, settings);
		}

		return new euclideanView3DWnoWebGL(ec, settings);
	}

	/**
	 * Resets the width of the Canvas converning the Width of its wrapper
	 * (splitlayoutpanel center)
	 * 
	 * @param app
	 *            application instance
	 *
	 * @param width
	 *            new width
	 * 
	 * @param height
	 *            new height
	 */
	static final public void ggwGraphicsView3DDimChanged(AppW app, int width,
			int height) {
		app.getSettings().geteuclidean(3).setPreferredSize(
				AwtFactory.getPrototype().newDimension(width, height));

		euclideanView3DW view = (euclideanView3DW) app.geteuclideanView3D();
		view.setCoordinateSpaceSize(width, height);
		view.doRepaint2();
	}

}
