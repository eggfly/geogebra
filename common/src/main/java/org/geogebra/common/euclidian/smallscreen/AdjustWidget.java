package org.geogebra.common.euclidean.smallscreen;

import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.main.App;

/**
 * 
 * @author lac
 *
 */
public abstract class AdjustWidget {
	protected euclideanView view;
	protected double ratioX;
	protected double ratioY;

	/**
	 * @param view
	 *            view
	 */
	public AdjustWidget(euclideanView view) {
		this.view = view;
		App app = view.getApplication();
		int fileWidth = app.getSettings()
				.geteuclidean(view.geteuclideanViewNo()).getFileWidth();
		int fileHeight = app.getSettings()
				.geteuclidean(view.geteuclideanViewNo()).getFileHeight();

		ratioX = fileWidth == 0 ? 1 : (double) view.getViewWidth() / fileWidth;
		ratioY = fileHeight == 0 ? 1
				: (double) view.getViewHeight() / fileHeight;

		// Log.debug("[ADJUST] ratioX: " + ratioX + " ratioY: " + ratioY);

	}

	/**
	 * @return if the entire widget is visible on the screen.
	 */
	public abstract boolean isOnScreen();

	/**
	 * Adjust the widget: if it is offscreen, this method repositions it to be
	 * on screen entirely.
	 */
	public abstract void apply();

}