package org.geogebra.common.euclidean;

import org.geogebra.common.kernel.Kernel;

/**
 * Hosts the euclidean views.
 */
public interface euclideanHost {

	/**
	 * @return euclidean view; if not present yet, new one is created
	 */
	euclideanView createeuclideanView();

	/**
	 * @return active euclidean view (may be EV, EV2 or 3D)
	 */
	euclideanView getActiveeuclideanView();

	/**
	 * @return whether EV2 was initialized
	 */
	boolean haseuclideanView2EitherShowingOrNot(int idx);

	/**
	 * @return whether EV2 is visible
	 */
	boolean isShowingeuclideanView2(int idx);

	/**
	 * @param kernel kernel
	 * @return euclidean controller
	 */
	euclideanController neweuclideanController(Kernel kernel);

	/**
	 * @return DrawEquation instance
	 */
	DrawEquation getDrawEquation();
}
