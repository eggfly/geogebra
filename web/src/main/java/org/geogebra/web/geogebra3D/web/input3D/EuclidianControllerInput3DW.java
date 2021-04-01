package org.geogebra.web.geogebra3D.web.input3D;

import org.geogebra.common.awt.GPoint;
import org.geogebra.common.euclidean.euclideanControllerCompanion;
import org.geogebra.common.euclidean.event.AbstractEvent;
import org.geogebra.common.geogebra3D.input3D.euclideanControllerInput3DCompanion;
import org.geogebra.common.geogebra3D.input3D.Input3D;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.web.geogebra3D.web.euclidean3D.euclideanController3DW;

/**
 * Controller with 3D input
 */
public class euclideanControllerInput3DW extends euclideanController3DW {
	/** 3D input */
	protected Input3D input3D;

	/**
	 * @param kernel
	 *            kernel
	 * @param input3D
	 *            3D input
	 */
	public euclideanControllerInput3DW(Kernel kernel, Input3D input3D) {
		super(kernel);
		this.input3D = input3D;
		((euclideanControllerInput3DCompanion) companion).setInput3D(input3D);
	}

	@Override
	public void updateInput3D() {
		input3D.update();
	}

	@Override
	protected euclideanControllerCompanion newCompanion() {
		return new euclideanControllerInput3DCompanion(this);
	}

	@Override
	public boolean hasInput3D() {
		return true;
	}

	@Override
	public GPoint getMouseLoc() {
		if (input3D.currentlyUseMouse2D()) {
			return super.getMouseLoc();
		}

		return input3D.getMouseLoc();
	}

	@Override
	public void wrapMouseReleased(AbstractEvent e) {
		if (!input3D.wasRightReleased() && !input3D.useQuaternionsForRotate()) {
			processRightRelease();
			return;
		}

		super.wrapMouseReleased(e);
	}

	private void processRightRelease() {
		setRotContinueAnimation();
	}

}
