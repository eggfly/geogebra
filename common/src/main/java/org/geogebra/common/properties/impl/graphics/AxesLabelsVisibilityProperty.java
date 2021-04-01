package org.geogebra.common.properties.impl.graphics;

import org.geogebra.common.main.Localization;
import org.geogebra.common.main.settings.euclideanSettings;
import org.geogebra.common.properties.BooleanProperty;
import org.geogebra.common.properties.impl.AbstractProperty;

/**
 * This property controls the visibility of the axis labels.
 */
public class AxesLabelsVisibilityProperty extends AbstractProperty
		implements BooleanProperty {

	private euclideanSettings euclideanSettings;

	/**
	 * Constructs an Axes visibility property.
	 *
	 * @param localization
	 *            localization for the title
	 * @param euclideanSettings
	 *            euclidean settings
	 */
	public AxesLabelsVisibilityProperty(Localization localization,
			euclideanSettings euclideanSettings) {
		super(localization, "Show");
		this.euclideanSettings = euclideanSettings;
	}

	@Override
	public boolean getValue() {
		String[] axesLabels = euclideanSettings.getAxesLabels();
		boolean value = false;
		for (int i = 0; i < euclideanSettings.getDimension(); i++) {
			value |= axesLabels[i] != null;
		}
		return value;
	}

	@Override
	public void setValue(boolean value) {
		int length = euclideanSettings.getDimension();
		for (int i = 0; i < length; i++) {
			euclideanSettings.setAxisLabel(i,
					value ? euclideanSettings.getDefaultAxisLabel(i) : null,
					i == length - 1);
		}
	}
}
