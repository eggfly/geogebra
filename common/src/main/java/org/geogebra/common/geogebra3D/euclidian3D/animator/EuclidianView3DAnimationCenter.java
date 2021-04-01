package org.geogebra.common.geogebra3D.euclidean3D.animator;

import org.geogebra.common.geogebra3D.euclidean3D.euclideanView3D;
import org.geogebra.common.geogebra3D.euclidean3D.animator.euclideanView3DAnimator.AnimationType;
import org.geogebra.common.kernel.matrix.Coords;

/**
 * animation for centering view
 *
 */
public class euclideanView3DAnimationCenter extends euclideanView3DAnimation {

	private double xEnd;
	private double yEnd;
	private double zEnd;

	/**
	 * 
	 * @param view3D 3D view
	 * @param animator animator
	 * @param p
	 *            point to center about
	 */
	euclideanView3DAnimationCenter(euclideanView3D view3D, euclideanView3DAnimator animator,
			Coords p) {

		super(view3D, animator);
		xEnd = -p.getX();
		yEnd = -p.getY();
		zEnd = -p.getZ();
	}

	@Override
	public void setupForStart() {
		// nothing to do
	}

	@Override
	public AnimationType getType() {
		return AnimationType.TRANSLATION;
	}

	@Override
	public void animate() {
		view3D.setXZero(xEnd);
		view3D.setYZero(yEnd);
		view3D.setZZero(zEnd);
		view3D.getSettings().updateOriginFromView(xEnd, yEnd, zEnd);

		// update the view
		view3D.updateTranslationMatrices();
		view3D.setGlobalMatrices();

		view3D.setViewChangedByTranslate();
		end();
	}

}
