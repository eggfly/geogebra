package org.geogebra.web.full.gui.dialog;

import org.geogebra.common.euclidean.euclideanController;
import org.geogebra.common.geogebra3D.euclidean3D.euclideanController3D;
import org.geogebra.common.gui.dialog.handler.NumberInputHandler;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.GeoPolygon;
import org.geogebra.common.kernel.kernelND.GeoLineND;
import org.geogebra.common.util.AsyncOperation;
import org.geogebra.web.html5.main.AppW;
import org.geogebra.web.shared.components.DialogData;

/**
 * Dialog for rotation around a line
 */
public class InputDialogRotateAxisW extends InputDialogRotateW {

	private GeoLineND[] lines;

	/**
	 * @param app
	 *            application
	 * @param data
	 *            dialog data
	 * @param handler
	 *            input handler
	 * @param polys
	 *            selected polygons
	 * @param selectedLines
	 *            selected lines
	 * @param selGeos
	 *            selected geos
	 * @param ec
	 *            controller
	 */
	public InputDialogRotateAxisW(AppW app, DialogData data,
            NumberInputHandler handler, GeoPolygon[] polys,
            GeoLineND[] selectedLines, GeoElement[] selGeos,
            euclideanController ec) {
		super(app, data, handler, polys, selGeos, ec);
		this.lines = selectedLines;
	}

	@Override
	protected void processInput(AsyncOperation<String> callback) {
		euclideanController3D.rotateObject(app,
				getInputText(), rbClockWise.getValue(), polys, lines,
				selGeos, (euclideanController3D) ec, this, callback);
	}
}