package org.geogebra.common.properties.impl.graphics;

import org.geogebra.common.main.Localization;
import org.geogebra.common.main.settings.euclideanSettings3D;
import org.geogebra.common.properties.BooleanProperty;
import org.geogebra.common.properties.impl.AbstractProperty;

/**
 * This property controls the color of axes.
 */
public class AxesColoredProperty extends AbstractProperty
		implements BooleanProperty {

	private euclideanSettings3D euclideanSettings;

	/**
	 * Constructs an Axes colored property.
	 *
	 * @param localization
	 *            localization for the title
	 * @param euclideanSettings
	 *            euclidean settings
	 */
	public AxesColoredProperty(Localization localization,
                               euclideanSettings3D euclideanSettings) {
		super(localization, "AxesColored");
		this.euclideanSettings = euclideanSettings;
	}

	@Override
	public boolean getValue() {
		return euclideanSettings.getHasColoredAxes();
	}

	@Override
	public void setValue(boolean value) {
		euclideanSettings.setHasColoredAxes(value);
	}
}
