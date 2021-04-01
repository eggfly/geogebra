package org.geogebra.web.geogebra3D.web.euclidean3D;

import java.util.List;

import org.geogebra.common.euclidean.euclideanConstants;
import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.gui.util.SelectionTable;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.web.full.gui.util.PopupMenuButtonW;
import org.geogebra.web.html5.gui.util.ImageOrText;

import com.google.gwt.resources.client.ImageResource;

/**
 * Stylebar popup for 3D rotation
 */
class RotateViewPopup extends PopupMenuButtonW {

	private final euclideanStyleBar3DW euclideanStyleBar3DW;
	private ImageOrText pauseIcon;
	private ImageOrText playIcon;

	/**
	 * @param euclideanStyleBar3DW
	 *            stylebar
	 * @param playIcon
	 *            play icon
	 * @param pauseIcon
	 *            pause icon
	 */
	public RotateViewPopup(euclideanStyleBar3DW euclideanStyleBar3DW,
			ImageResource playIcon, ImageResource pauseIcon) {
		super(euclideanStyleBar3DW.app, null, -1, -1, SelectionTable.MODE_ICON,
				false, true, null);
		this.euclideanStyleBar3DW = euclideanStyleBar3DW;

		this.playIcon = new ImageOrText(playIcon);
		this.pauseIcon = new ImageOrText(pauseIcon);

		setIcon(this.playIcon);

		getMySlider().setMinimum(-10);
		getMySlider().setMaximum(10);
		getMySlider().setTickSpacing(1);
		setSliderValue(5);
	}

	@Override
	protected void fireActionPerformed() {

		this.euclideanStyleBar3DW.getView().setRotContinueAnimation(0, getSliderValue() * 0.01);
		if (getSliderValue() == 0) {
			setIcon(playIcon);
		} else {
			setIcon(pauseIcon);
		}
	}

	@Override
	protected void onClickAction() {
		if (this.euclideanStyleBar3DW.getView().isRotAnimatedContinue()) {
			this.euclideanStyleBar3DW.getView().stopAnimation();
			setIcon(playIcon);
		} else {
			this.euclideanStyleBar3DW.getView().setRotContinueAnimation(0, getSliderValue() * 0.01);
			setIcon(pauseIcon);
		}
	}

	@Override
	public void update(List<GeoElement> geos) {
		this.setVisible(
				geos.size() == 0 && !euclideanView.isPenMode(app.getMode())
						&& app.getMode() != euclideanConstants.MODE_DELETE);
	}
}
