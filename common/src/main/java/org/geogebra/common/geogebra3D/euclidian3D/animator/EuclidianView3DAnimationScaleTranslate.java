package org.geogebra.common.geogebra3D.euclidean3D.animator;

import org.geogebra.common.geogebra3D.euclidean3D.euclideanView3D;

/**
 * animation for scale and translate
 *
 */
public class euclideanView3DAnimationScaleTranslate extends euclideanView3DAnimationScaleAbstract {

	private static final double STEPS_TO_TIME_FACTOR = 0.0003;

	/**
	 * 
	 * @param view3D 3D view
	 * @param animator animator
	 * @param x translation in x
	 * @param y translation in y
	 * @param z translation in z
	 * @param newScale new scale
	 * @param steps steps for animation
	 */
	euclideanView3DAnimationScaleTranslate(euclideanView3D view3D, euclideanView3DAnimator animator,
			double x, double y, double z, double newScale, int steps) {
		super(view3D, animator);
		animatedScaleEndX = x;
		animatedScaleEndY = y;
		animatedScaleEndZ = z;
		xScaleEnd = newScale;
		yScaleEnd = newScale;
		zScaleEnd = newScale;
		animatedScaleTimeFactor = STEPS_TO_TIME_FACTOR * steps;
	}

	@Override
	public void setupForStart() {
		xScaleStart = view3D.getXscale();
		yScaleStart = view3D.getYscale();
		zScaleStart = view3D.getZscale();
		animatedScaleStartX = view3D.getXZero();
		animatedScaleStartY = view3D.getYZero();
		animatedScaleStartZ = view3D.getZZero();

		animatedScaleTimeStart = getMillisecondTime();
	}

	@Override
	protected boolean animationAllowed() {
		return true;
	}
}
