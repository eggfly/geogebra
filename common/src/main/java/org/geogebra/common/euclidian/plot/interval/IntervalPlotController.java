package org.geogebra.common.euclidean.plot.interval;

import org.geogebra.common.euclidean.CoordSystemAnimationListener;
import org.geogebra.common.euclidean.CoordSystemInfo;
import org.geogebra.common.euclidean.euclideanController;

/**
 * Controller for Interval Plotter to handle zoom and moving the view.
 *
 * @author laszlo
 */
public class IntervalPlotController implements CoordSystemAnimationListener {

	private final IntervalPlotModel model;
	private euclideanController euclideanController;

	/**
	 * Constructor.
	 * @param model {@link IntervalPlotModel}
	 */
	public IntervalPlotController(IntervalPlotModel model) {
		this.model = model;
	}

	/**
	 * @param controller {@link euclideanController}
	 */
	public void attacheuclideanController(euclideanController controller) {
		euclideanController = controller;
		euclideanController.addZoomerAnimationListener(this);
	}

	@Override
	public void onZoomStop(CoordSystemInfo info) {
		info.setXAxisZoom(false);
		model.updateAll();
	}

	@Override
	public void onMoveStop() {
		model.updateAll();
	}

	@Override
	public void onMove(CoordSystemInfo info) {
		if (info.isXAxisZoom() || info.isCenterVew()) {
			return;
		}
		model.updateDomain();
	}

	/**
	 * Remove controller as zoomer animation listener.
	 */
	public void detach() {
		euclideanController.removeZoomerAnimationListener(this);
	}
}