package org.geogebra.common.properties.impl.graphics;

import org.geogebra.common.main.Localization;
import org.geogebra.common.main.settings.euclideanSettings;
import org.geogebra.common.properties.BooleanProperty;
import org.geogebra.common.properties.impl.AbstractProperty;

/**
 * This property controls the visibility of the grid.
 */
public class GridVisibilityProperty extends AbstractProperty
		implements BooleanProperty {

	private euclideanSettings euclideanSettings;

	/**
	 * Constructs a GridVisibility property.
	 *
	 * @param localization
	 *            localization for the name
	 * @param euclideanSettings
	 *            euclidean settings
	 */
	public GridVisibilityProperty(Localization localization,
			euclideanSettings euclideanSettings) {
		super(localization, "ShowGrid");
		this.euclideanSettings = euclideanSettings;
	}

	@Override
	public boolean getValue() {
		return euclideanSettings.getShowGrid();
	}

	@Override
	public void setValue(boolean value) {
		euclideanSettings.showGrid(value);
	}
}
