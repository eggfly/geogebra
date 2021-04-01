package org.geogebra.common.kernel.scripting;

import org.geogebra.common.euclidean.euclideanViewInterfaceSlim;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.kernel.arithmetic.Command;
import org.geogebra.common.kernel.arithmetic.NumberValue;
import org.geogebra.common.kernel.commands.CmdScripting;
import org.geogebra.common.kernel.geos.GeoBoolean;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.kernelND.GeoAxisND;
import org.geogebra.common.main.App;
import org.geogebra.common.main.MyError;
import org.geogebra.common.main.settings.euclideanSettings;

/**
 * SetVisibleInView
 */
public class CmdSetVisibleInView extends CmdScripting {

	/**
	 * Create new command processor
	 * 
	 * @param kernel
	 *            kernel
	 */
	public CmdSetVisibleInView(Kernel kernel) {
		super(kernel);
	}

	@Override
	protected final GeoElement[] perform(Command c) throws MyError {
		int n = c.getArgumentNumber();

		switch (n) {
		case 3:
			GeoElement[] arg = resArgs(c);
			if (!(arg[1] instanceof NumberValue)) {
				throw argErr(c, arg[1]);
			}

			if (arg[2].isGeoBoolean()) {

				GeoElement geo = arg[0];

				int viewNo = (int) arg[1].evaluateDouble();

				euclideanViewInterfaceSlim ev = null;
				boolean show = ((GeoBoolean) arg[2]).getBoolean();

				int viewID;
				switch (viewNo) {
				case 1:
					viewID = App.VIEW_euclidean;
					ev = app.geteuclideanView1();
					break;
				case 2:
					viewID = App.VIEW_euclidean2;
					if (app.haseuclideanView2(1)) {

						ev = app.geteuclideanView2(1);
					}
					break;
				case -1:
					viewID = App.VIEW_euclidean3D;
					if (app.iseuclideanView3Dinited()) {
						ev = app.geteuclideanView3D();
					}
					break;
				default:
					return arg;
				}
				if (geo instanceof GeoAxisND) {

					euclideanSettings evs = app.getSettings()
							.geteuclidean(viewNo < 0 ? 3 : viewNo);

					evs.setShowAxis(((GeoAxisND) geo).getType(), show);
					geo.updateRepaint();
					return arg;
				}
				if (show) {
					geo.seteuclideanVisible(true);
					geo.addView(viewID);
					if (ev != null) {
						ev.add(geo);
						geo.updateRepaint();
					}
				} else {
					geo.removeView(viewID);
					if (ev != null) {
						ev.remove(geo);
						geo.updateRepaint();
					}
				}

				return arg;
			}
			throw argErr(c, arg[2]);

		default:
			throw argNumErr(c);
		}
	}
}
