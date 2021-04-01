package org.geogebra.common.geogebra3D.kernel3D.scripting;

import org.geogebra.common.geogebra3D.euclidean3D.euclideanView3D;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.kernel.arithmetic.Command;
import org.geogebra.common.kernel.commands.CmdScripting;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.GeoNumberValue;
import org.geogebra.common.main.MyError;
import org.geogebra.common.main.settings.euclideanSettings3D;

/**
 * ZoomIn
 */
public class CmdSetSpinSpeed extends CmdScripting {
	/**
	 * Creates new ZooomOut command
	 * 
	 * @param kernel
	 *            kernel
	 */
	public CmdSetSpinSpeed(Kernel kernel) {
		super(kernel);
	}

	@Override
	protected final GeoElement[] perform(Command c) throws MyError {
		int n = c.getArgumentNumber();

		switch (n) {
		case 1:
			GeoElement[] arg = resArgs(c);
			if (arg[0] instanceof GeoNumberValue) {
				GeoNumberValue v = (GeoNumberValue) arg[0];
				if (!app.iseuclideanView3Dinited()) {
					euclideanSettings3D settings = (euclideanSettings3D) app
							.getSettings().geteuclidean(3);
					settings.setRotSpeed(v.getDouble() * 0.01);
				} else {
					euclideanView3D view3D = (euclideanView3D) app
							.geteuclideanView3D();

					view3D.setRotContinueAnimation(0, v.getDouble() * 0.01);
				}
				return arg;

			}

			throw argErr(c, arg[0]);

		default:
			throw argNumErr(c);
		}
	}
}
