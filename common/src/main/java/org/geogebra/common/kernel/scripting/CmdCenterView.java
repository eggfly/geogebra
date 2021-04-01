package org.geogebra.common.kernel.scripting;

import org.geogebra.common.euclidean.euclideanViewInterfaceSlim;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.kernel.arithmetic.Command;
import org.geogebra.common.kernel.commands.CmdScripting;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.kernelND.GeoPointND;
import org.geogebra.common.main.MyError;

/**
 * ZoomIn
 */
public class CmdCenterView extends CmdScripting {
	/**
	 * Creates new ZooomOut command
	 * 
	 * @param kernel
	 *            kernel
	 */
	public CmdCenterView(Kernel kernel) {
		super(kernel);
	}

	@Override
	protected final GeoElement[] perform(Command c) throws MyError {
		int n = c.getArgumentNumber();

		switch (n) {
		case 1:
			GeoElement[] arg = resArgs(c);
			if (arg[0].isGeoPoint()) {
				GeoPointND p = (GeoPointND) arg[0];

				euclideanViewInterfaceSlim ev = app.getActiveeuclideanView();
				ev.centerView(p);
				return arg;

			}
			throw argErr(c, arg[0]);

		default:
			throw argNumErr(c);
		}
	}
}
