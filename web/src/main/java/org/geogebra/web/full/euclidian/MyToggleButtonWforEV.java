package org.geogebra.web.full.euclidean;

import java.util.List;

import org.geogebra.common.euclidean.euclideanConstants;
import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.web.full.gui.util.MyToggleButtonW;

import com.google.gwt.resources.client.ImageResource;

/**
 * Toggle button that should be visible if no geos are selected or to be
 * created and no special icons appear in stylebar (eg. delete mode)
 */
public class MyToggleButtonWforEV extends MyToggleButtonW {
	private euclideanStyleBarW stylebar;

	/**
	 * @param img
	 *            image
	 * @param stylebar
	 *            parent stylebar
	 */
	public MyToggleButtonWforEV(ImageResource img,
			euclideanStyleBarW stylebar) {
		super(img);
		this.stylebar = stylebar;
	}

	@Override
	public void update(List<GeoElement> geos) {
		if (stylebar.app.isUnbundledOrWhiteboard()) {
			this.setVisible(geos.size() == 0);
		} else {
			int mode = stylebar.mode;
			this.setVisible(geos.size() == 0 && !euclideanView.isPenMode(mode)
					&& mode != euclideanConstants.MODE_DELETE
					&& mode != euclideanConstants.MODE_ERASER);
		}
	}
}
