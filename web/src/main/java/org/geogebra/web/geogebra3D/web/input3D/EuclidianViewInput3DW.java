package org.geogebra.web.geogebra3D.web.input3D;

import org.geogebra.common.euclidean.euclideanViewCompanion;
import org.geogebra.common.geogebra3D.euclidean3D.euclideanController3D;
import org.geogebra.common.geogebra3D.euclidean3D.openGL.Renderer;
import org.geogebra.common.geogebra3D.input3D.euclideanViewInput3DCompanion;
import org.geogebra.common.geogebra3D.input3D.Input3D;
import org.geogebra.common.main.settings.euclideanSettings;
import org.geogebra.web.geogebra3D.web.euclidean3D.euclideanView3DW;

/**
 * 3D graphics with 3D input for Web
 *
 */
public class euclideanViewInput3DW extends euclideanView3DW {

	private Input3D input3D;
	private euclideanViewInput3DCompanion companionInput3D;

	/**
	 * @param ec
	 *            controller
	 * @param settings
	 *            settings
	 */
	public euclideanViewInput3DW(euclideanController3D ec,
			euclideanSettings settings) {
		super(ec, settings);
	}

	@Override
	protected euclideanViewCompanion neweuclideanViewCompanion() {
		companionInput3D = new euclideanViewInput3DCompanion(this);
		return companionInput3D;
	}

	@Override
	public euclideanViewInput3DCompanion getCompanion() {
		return companionInput3D;
	}

	@Override
	protected void start() {
		input3D = ((euclideanControllerInput3DW) euclideanController).input3D;
		input3D.init(this);
		getCompanion().setInput3D(input3D);
		super.start();
	}

	/**
	 * @return 3D input
	 */
	public Input3D getInput3D() {
		return input3D;
	}

	@Override
	protected Renderer createRenderer() {
		return new RendererWithImplZSpaceW(this);

	}

	@Override
	public boolean isAnimated() {
		return true;
	}

	@Override
	public void setTransparentCursor() {
		// avoid transparent cursor for now
		setDefault2DCursor();
	}

}
