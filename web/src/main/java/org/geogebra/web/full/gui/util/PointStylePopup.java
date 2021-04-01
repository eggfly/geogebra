package org.geogebra.web.full.gui.util;

import java.util.HashMap;
import java.util.List;

import org.geogebra.common.euclidean.euclideanConstants;
import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.gui.SetLabels;
import org.geogebra.common.gui.dialog.options.model.IComboListener;
import org.geogebra.common.gui.dialog.options.model.PointStyleModel;
import org.geogebra.common.gui.util.SelectionTable;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.PointProperties;
import org.geogebra.web.html5.gui.util.ImageOrText;
import org.geogebra.web.html5.main.AppW;

public class PointStylePopup extends PopupMenuButtonW
		implements IComboListener, SetLabels {

	private static final int DEFAULT_SIZE = 4;
	static HashMap<Integer, Integer> pointStyleMap;
	int mode;
	private PointStyleModel model;
	private boolean euclidean3D;

	/**
	 * @param app
	 *            application
	 * @param mode
	 *            mode
	 * @param hasSlider
	 *            whether to include size slider
	 * @param model
	 *            model
	 * @return point stylle popup
	 */
	public static PointStylePopup create(AppW app, int mode, boolean hasSlider,
			PointStyleModel model) {
		
		pointStyleMap = new HashMap<>();
		for (int i = 0; i < euclideanView.getPointStyleLength(); i++) {
			pointStyleMap.put(euclideanView.getPointStyle(i), i);
		}

		ImageOrText[] pointStyleIcons = new ImageOrText[euclideanView
				.getPointStyleLength()];
		for (int i = 0; i < euclideanView.getPointStyleLength(); i++) {
			pointStyleIcons[i] = GeoGebraIconW
					.createPointStyleIcon(euclideanView.getPointStyle(i));
		}

		PointStylePopup popup = new PointStylePopup(app, pointStyleIcons, 2,
				SelectionTable.MODE_ICON, true,
				hasSlider, model);
		popup.mode = mode;
		return popup;
	}

	/**
	 * @param app
	 *            application
	 * @param mode
	 *            mode
	 * @param model
	 *            model
	 * @return point stylle popup
	 */
	public static PointStylePopup create(AppW app, int mode,
			PointStyleModel model) {
		return new PointStylePopup(app, null, 1, 
				SelectionTable.MODE_ICON, false, true, model);
	}

	/**
	 * @param app
	 *            application
	 * @param data
	 *            point style icons
	 * @param rows
	 *            number of rows
	 * @param tableMode
	 *            selection mode
	 * @param hasTable
	 *            whether table is used
	 * @param hasSlider
	 *            whether size slider is used
	 * @param model
	 *            options model
	 */
	public PointStylePopup(AppW app, ImageOrText[] data, Integer rows,
			SelectionTable tableMode, boolean hasTable, boolean hasSlider,
			PointStyleModel model) {
		super(app, data, rows, -1, tableMode, hasTable, hasSlider, null);
		getMyPopup().addStyleName("pointSizeSlider");
		this.model = model;
		euclidean3D = false;
	}

	public void setModel(PointStyleModel model) {
		this.model = model;
	}

	@Override
	public void update(List<GeoElement> geos) {
		updatePanel(geos.toArray());
	}

	@Override
	public Object updatePanel(Object[] geos) {
		model.setGeos(geos);
		
		if (!model.hasGeos()) {
			this.setVisible(false);
			return null;
		}
		
		boolean geosOK = model.checkGeos(); 
		
		this.setVisible(geosOK);

		if (geosOK) {
			getMyTable().setVisible(!euclidean3D);

			model.updateProperties();

			PointProperties geo0 = (PointProperties) model.getGeoAt(0);
			if (hasSlider()) {
				setSliderValue(geo0.getPointSize());
			}

			setSelectedIndex(pointStyleMap.get(euclidean3D ? 0 : geo0
			        .getPointStyle()));

			this.setKeepVisible(euclideanConstants.isMoveOrSelectionMode(mode));
		}
		return this;
	}

	@Override
	public void handlePopupActionEvent() {
		super.handlePopupActionEvent();
		model.applyChanges(getSelectedIndex());
	}

	@Override
	public ImageOrText getButtonIcon() {
		if (getSelectedIndex() > -1) {
			return GeoGebraIconW
					.createPointStyleIcon(euclideanView
							.getPointStyle(this.getSelectedIndex()));
		}
		return new ImageOrText();
	}
	
	@Override 
	public int getSliderValue() {
		int val = super.getSliderValue();
		return val == -1 ? DEFAULT_SIZE : val;
	}

	@Override
	public void setSelectedIndex(int index) {
		super.setSelectedIndex(index);
	}

	@Override
	public void addItem(String item) {
	    // TODO Auto-generated method stub
	}

	public boolean iseuclidean3D() {
		return euclidean3D;
	}

	public void seteuclidean3D(boolean euclidean3d) {
		euclidean3D = euclidean3d;
	}

	@Override
	public void clearItems() {
		// TODO Auto-generated method stub
	}

	@Override
	public void setLabels() {
		// Overridden in MowPointStyle
	}

}
