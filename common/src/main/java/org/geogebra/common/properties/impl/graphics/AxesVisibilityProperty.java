package org.geogebra.common.properties.impl.graphics;

import org.geogebra.common.main.Localization;
import org.geogebra.common.main.settings.euclideanSettings;
import org.geogebra.common.properties.BooleanProperty;
import org.geogebra.common.properties.impl.AbstractProperty;

/**
 * This property controls the visibility of the axes.
 */
public class AxesVisibilityProperty extends AbstractProperty
		implements BooleanProperty {

	private euclideanSettings euclideanSettings;

	/**
	 * Constructs an AxesVisibility property.
	 *
	 * @param localization
	 *            localization for the name
	 * @param euclideanSettings
	 *            euclidean settings
	 */
	public AxesVisibilityProperty(Localization localization,
			euclideanSettings euclideanSettings) {
		super(localization, "ShowAxes");
		this.euclideanSettings = euclideanSettings;
	}

	@Override
	public boolean getValue() {
		boolean[] showAxes = euclideanSettings.getShowAxes();
		boolean value = false;
		for (int i = 0; i < euclideanSettings.getDimension(); i++) {
			value |= showAxes[i];
		}

		return value;
	}

	@Override
	public void setValue(boolean value) {
		euclideanSettings.setShowAxes(value);
	}
}
