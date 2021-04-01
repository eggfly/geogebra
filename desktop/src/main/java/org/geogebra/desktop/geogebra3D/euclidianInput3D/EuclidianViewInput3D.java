package org.geogebra.desktop.geogebra3D.euclideanInput3D;

import org.geogebra.common.euclidean.euclideanViewCompanion;
import org.geogebra.common.geogebra3D.euclidean3D.euclideanController3D;
import org.geogebra.common.geogebra3D.euclidean3D.openGL.Renderer;
import org.geogebra.common.geogebra3D.input3D.euclideanViewInput3DCompanion;
import org.geogebra.common.geogebra3D.input3D.Input3D;
import org.geogebra.common.main.settings.euclideanSettings;
import org.geogebra.desktop.geogebra3D.euclidean3D.euclideanView3DD;
import org.geogebra.desktop.geogebra3D.euclidean3D.opengl.RendererCheckGLVersionD;
import org.geogebra.desktop.geogebra3D.euclidean3D.opengl.RendererJogl;

/**
 * euclideanView3D with controller using 3D input
 * 
 * @author mathieu
 * 
 */
public class euclideanViewInput3D extends euclideanView3DD {

	private Input3D input3D;

	/**
	 * constructor
	 * 
	 * @param ec
	 *            euclidean controller
	 * @param settings
	 *            settings
	 */
	public euclideanViewInput3D(euclideanController3D ec,
			euclideanSettings settings) {
		super(ec, settings);

	}

	private euclideanViewInput3DCompanion companionInput3D;

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
		input3D = ((euclideanControllerInput3D) euclideanController).input3D;
		input3D.init(this);
		getCompanion().setInput3D(input3D);
		super.start();
	}

	@Override
	protected Renderer createRenderer() {
		RendererJogl.setDefaultProfile();
		// return new RendererLogicalPickingGL2(this, !app.isApplet());
		return new RendererCheckGLVersionD(this, true);
	}
}
