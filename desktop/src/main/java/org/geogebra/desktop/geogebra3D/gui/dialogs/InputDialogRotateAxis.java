package org.geogebra.desktop.geogebra3D.gui.dialogs;

import org.geogebra.common.euclidean.euclideanController;
import org.geogebra.common.geogebra3D.euclidean3D.euclideanController3D;
import org.geogebra.common.gui.InputHandler;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.GeoPolygon;
import org.geogebra.common.kernel.kernelND.GeoLineND;
import org.geogebra.common.util.AsyncOperation;
import org.geogebra.desktop.gui.dialog.InputDialogRotateD;
import org.geogebra.desktop.main.AppD;

public class InputDialogRotateAxis extends InputDialogRotateD {

	GeoLineND[] lines;

	public InputDialogRotateAxis(AppD app, String title, InputHandler handler,
			GeoPolygon[] polys, GeoLineND[] lines, GeoElement[] selGeos,
			euclideanController ec) {

		super(app, title, handler, polys, selGeos, ec);

		this.lines = lines;

	}

	@Override
	protected void processInput(AsyncOperation<String> callback) {

		euclideanController3D.rotateObject(app, inputPanel.getText(),
				rbClockWise.isSelected(), polys, lines, selGeos,
				(euclideanController3D) ec, this, callback);
	}

}
