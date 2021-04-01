package org.geogebra.common.geogebra3D.euclidean3D.animator;

import org.geogebra.common.euclidean.euclideanController;
import org.geogebra.common.geogebra3D.euclidean3D.euclideanView3D;
import org.geogebra.common.geogebra3D.euclidean3D.animator.euclideanView3DAnimator.AnimationType;

/**
 * animation for axis scale
 *
 */
public class euclideanView3DAnimationAxisScale extends euclideanView3DAnimation {

	private double axisScaleFactor;
	private double axisScaleOld;
	private int axisScaleMode;
	private double xZeroOld;
	private double yZeroOld;
	private double zZeroOld;

	/**
	 * 
	 * @param view3D 3D view
	 * @param animator animator
	 */
	euclideanView3DAnimationAxisScale(euclideanView3D view3D, euclideanView3DAnimator animator) {
		super(view3D, animator);
	}

	/**
	 * remembers original values
	 */
	public void rememberOrigins() {
		xZeroOld = view3D.getXZero();
		yZeroOld = view3D.getYZero();
		zZeroOld = view3D.getZZero();
	}

	/**
	 * 
	 * @param factor
	 *            scale factor
	 * @param scaleOld
	 *            scale at start
	 * @param mode
	 *            axis concerned
	 */
	public void set(double factor, double scaleOld, int mode) {
		axisScaleFactor = factor;
		axisScaleOld = scaleOld;
		axisScaleMode = mode;
	}

	@Override
	public void setupForStart() {
		// nothing to do
	}

	@Override
	public AnimationType getType() {
		return AnimationType.AXIS_SCALE;
	}

	@Override
	public void animate() {
		switch (axisScaleMode) {
			case euclideanController.MOVE_X_AXIS:
				view3D.setXZero(xZeroOld / axisScaleFactor);
				view3D.getSettings().setXscaleValue(axisScaleFactor * axisScaleOld);
				break;
			case euclideanController.MOVE_Y_AXIS:
				view3D.setYZero(yZeroOld / axisScaleFactor);
				view3D.getSettings().setYscaleValue(axisScaleFactor * axisScaleOld);
				break;
			case euclideanController.MOVE_Z_AXIS:
				view3D.setZZero(zZeroOld / axisScaleFactor);
				view3D.getSettings().setZscaleValue(axisScaleFactor * axisScaleOld);
				break;
			default:
				// do nothing
				break;
		}

		view3D.getSettings().updateOriginFromView(view3D.getXZero(), view3D.getYZero(),
				view3D.getZZero());

		view3D.updateMatrix();
		view3D.setViewChangedByTranslate();
		view3D.setViewChangedByZoom();
		end();
	}

}
