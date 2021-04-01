package org.geogebra.web.full.euclidean;

import java.util.List;

import org.geogebra.common.euclidean.euclideanConstants;
import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.web.html5.gui.util.ImageOrText;
import org.geogebra.web.html5.main.AppW;

/**
 * Grid style popup
 *
 */
public class GridPopup extends PopupMenuButtonWithDefault {

	/**
	 * @param app
	 *            app
	 * @param data
	 *            icons
	 * @param ev
	 *            view
	 */
	public GridPopup(AppW app, ImageOrText[] data, euclideanView ev) {
		super(app, data);
		this.setIcon(data[euclideanStyleBarW.gridIndex(ev)]);
	}

	@Override
	public void update(List<GeoElement> geos) {
		this.setVisible(
				geos.size() == 0 && !euclideanView.isPenMode(app.getMode())
						&& app.getMode() != euclideanConstants.MODE_DELETE);
	}

}
