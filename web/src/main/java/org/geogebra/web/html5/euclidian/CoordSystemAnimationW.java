package org.geogebra.web.html5.euclidean;

import org.geogebra.common.euclidean.CoordSystemAnimation;
import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.util.GTimerListener;
import org.geogebra.web.html5.sound.GTimerW;

public class CoordSystemAnimationW extends CoordSystemAnimation implements GTimerListener {
	protected GTimerW timer; // for animation

	/**
	 * @param view
	 *            zoomed / panned euclidean view
	 */
	public CoordSystemAnimationW(euclideanView view) {
		super(view);
		timer = new GTimerW(this, DELAY);
	}

	@Override
	protected void stopTimer() {
		timer.stop();
	}

	@Override
	protected boolean hasTimer() {
		return timer != null;
	}

	@Override
	public synchronized void onRun() {
		step();
	}

	@Override
	protected void startTimer() {
		timer.startRepeat();
	}
}
