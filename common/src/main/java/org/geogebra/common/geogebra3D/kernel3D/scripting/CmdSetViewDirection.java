package org.geogebra.common.geogebra3D.kernel3D.scripting;

import org.geogebra.common.euclidean3D.euclideanView3DInterface;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.kernel.arithmetic.Command;
import org.geogebra.common.kernel.commands.CmdScripting;
import org.geogebra.common.kernel.geos.GeoBoolean;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.GeoNumeric;
import org.geogebra.common.kernel.kernelND.GeoDirectionND;
import org.geogebra.common.kernel.kernelND.GeoPointND;
import org.geogebra.common.kernel.kernelND.GeoVectorND;
import org.geogebra.common.kernel.matrix.Coords;
import org.geogebra.common.main.MyError;

/**
 * SetViewDirection processor
 */
public class CmdSetViewDirection extends CmdScripting {
	private Coords tmpCoords;

	/**
	 * Creates new ZooomOut command
	 * 
	 * @param kernel
	 *            kernel
	 */
	public CmdSetViewDirection(Kernel kernel) {
		super(kernel);
	}

	@Override
	protected final GeoElement[] perform(Command c) throws MyError {

		if (!app.iseuclideanView3Dinited()) {
			return new GeoElement[0];
		}

		int n = c.getArgumentNumber();

		if (n > 2) {
			throw argNumErr(c);
		}

		// no argument: set default orientation
		if (n == 0) {
			app.geteuclideanView3D().setDefaultRotAnimation();
			return new GeoElement[0];
		}

		GeoElement[] arg = resArgs(c);

		boolean animated = true;
		if (n == 2) {
			if (arg[1].isGeoBoolean()) {
				animated = ((GeoBoolean) arg[1]).getBoolean();
			} else {
				throw argErr(c, arg[1]);
			}
		}

		if (arg[0].isGeoVector()) {
			GeoVectorND v = (GeoVectorND) arg[0];

			euclideanView3DInterface view3D = app.geteuclideanView3D();

			if (tmpCoords == null) {
				tmpCoords = new Coords(3);
			}
			tmpCoords.setMul(v.getCoordsInD3(), -1);
			view3D.setRotAnimation(tmpCoords, false, animated);

			return arg;
		}

		if (arg[0] instanceof GeoDirectionND) {
			GeoDirectionND d = (GeoDirectionND) arg[0];

			euclideanView3DInterface view3D = app.geteuclideanView3D();

			Coords v = d.getDirectionInD3();
			if (v != null) {
				view3D.setClosestRotAnimation(v, animated);
			}

			return arg;

		}

		if (arg[0].isGeoPoint()) {
			GeoPointND p = (GeoPointND) arg[0];

			if (p.isDefined()) {
				euclideanView3DInterface view3D = app.geteuclideanView3D();
				view3D.setClosestRotAnimation(p.getInhomCoordsInD3(), animated);
			}

			return arg;

		}

		if (arg[0].isGeoNumeric()) {
			// shift value to have x-axis to the left when angle is zero
			// sign for anti-clockwise rotation
			double value = -((GeoNumeric) arg[0]).getDouble() - Math.PI / 2;

			app.geteuclideanView3D().setRotAnimation(value, false, animated);

			return arg;

		}

		throw argErr(c, arg[0]);

	}
}
