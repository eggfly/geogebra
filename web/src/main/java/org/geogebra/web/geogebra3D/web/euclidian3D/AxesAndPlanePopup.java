package org.geogebra.web.geogebra3D.web.euclidean3D;

import java.util.List;

import org.geogebra.common.euclidean.euclideanConstants;
import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.geogebra3D.euclidean3D.euclideanView3D;
import org.geogebra.common.kernel.arithmetic.MyDouble;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.web.full.euclidean.PopupMenuButtonWithDefault;
import org.geogebra.web.html5.gui.util.ImageOrText;
import org.geogebra.web.html5.main.AppW;

/**
 * Popup for axes and coordinate plane
 *
 */
public class AxesAndPlanePopup extends PopupMenuButtonWithDefault {

	private euclideanView3D ev;

	/**
	 * @param app
	 *            application
	 * @param data
	 *            icons
	 * @param ev
	 *            view
	 */
	public AxesAndPlanePopup(AppW app, ImageOrText[] data, euclideanView3D ev) {
		super(app, data);
		this.ev = ev;
		this.setIcon(data[getIndexFromEV()]);
	}

	private int getIndexFromEV() {
		int ret = 0;
		if (ev.getShowXaxis()) {
			ret++;
		}
		if (ev.getShowPlane()) {
			ret += 2;
		}
		return ret;
	}

	/**
	 * Select item based on current EV state
	 */
	public void setIndexFromEV() {
		setSelectedIndex(getIndexFromEV());
	}

	/**
	 * set euclidean view from index
	 */
	public void setEVFromIndex() {
		int index = getSelectedIndex();
		ev.getSettings().beginBatch();
		ev.getSettings().setShowAxes(MyDouble.isOdd(index));
		ev.getSettings().setShowPlate(index >= 2);
		ev.getSettings().endBatch();
		((euclideanView3DW) ev).doRepaint();
	}

	@Override
	public void update(List<GeoElement> geos) {
		this.setVisible(
				geos.size() == 0 && !euclideanView.isPenMode(app.getMode())
						&& app.getMode() != euclideanConstants.MODE_DELETE);
	}

}
