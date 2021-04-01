/* 
GeoGebra - Dynamic Mathematics for Everyone
http://www.geogebra.org

This file is part of GeoGebra.

This program is free software; you can redistribute it and/or modify it 
under the terms of the GNU General Public License as published by 
the Free Software Foundation.

 */

package org.geogebra.common.kernel.scripting;

import org.geogebra.common.euclidean.euclideanViewInterfaceCommon;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.kernel.arithmetic.BooleanValue;
import org.geogebra.common.kernel.arithmetic.Command;
import org.geogebra.common.kernel.arithmetic.NumberValue;
import org.geogebra.common.kernel.commands.CmdScripting;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.main.MyError;

/**
 * ShowGrid[]
 * 
 * ShowGrid[&lt;Boolean>]
 * 
 * ShowGrid[&lt;View ID>, &lt;Boolean]
 */
public class CmdShowGrid extends CmdScripting {

	/**
	 * Create new command processor
	 * 
	 * @param kernel
	 *            kernel
	 */
	public CmdShowGrid(Kernel kernel) {
		super(kernel);
	}

	@Override
	protected final GeoElement[] perform(Command c) throws MyError {
		int n = c.getArgumentNumber();

		euclideanViewInterfaceCommon ev = null;

		GeoElement[] arg = resArgs(c);
		switch (n) {
		case 0:
			ev = app.getActiveeuclideanView();
			ev.showGrid(true);
			ev.repaintView();
			break;
		case 1:
			if (!(arg[0] instanceof BooleanValue)) {
				throw argErr(c, arg[0]);
			}

			boolean show = ((BooleanValue) arg[0]).getBoolean();
			ev = app.getActiveeuclideanView();
			setAndRepaint(show, ev);

			break;
		case 2:
			if (!(arg[0] instanceof NumberValue)) {
				throw argErr(c, arg[0]);
			}
			if (!(arg[1] instanceof BooleanValue)) {
				throw argErr(c, arg[1]);
			}

			show = ((BooleanValue) arg[1]).getBoolean();

			switch ((int) (((NumberValue) arg[0]).getDouble())) {
			case 2:
				if (app.haseuclideanView2(1)) {
					ev = app.geteuclideanView2(1);
				}
				break;
			case 3:
				if (app.iseuclideanView3Dinited()) {
					ev = app.geteuclideanView3D();
				}
				break;
			default:
				ev = app.geteuclideanView1();
			}
			if (ev != null) {
				setAndRepaint(show, ev);
			}
			break;

		default:
			throw argNumErr(c);
		}
		return arg;
	}

	private static void setAndRepaint(boolean show,
			euclideanViewInterfaceCommon ev) {
		if (ev.getSettings() != null) {
			ev.getSettings().showGrid(show);
		} else {
			ev.setShowAxis(show);
		}
		ev.repaintView();

	}
}
