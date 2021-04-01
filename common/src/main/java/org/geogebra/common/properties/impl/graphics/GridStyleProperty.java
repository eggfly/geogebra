package org.geogebra.common.properties.impl.graphics;

import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.main.Localization;
import org.geogebra.common.main.settings.euclideanSettings;
import org.geogebra.common.properties.IconsEnumerableProperty;
import org.geogebra.common.properties.PropertyResource;
import org.geogebra.common.properties.impl.AbstractEnumerableProperty;

/**
 * This property controls the style of the grid.
 */
public class GridStyleProperty extends AbstractEnumerableProperty
		implements IconsEnumerableProperty {

	private euclideanSettings euclideanSettings;

	private PropertyResource[] icons = new PropertyResource[] {
			PropertyResource.ICON_CARTESIAN,
			PropertyResource.ICON_CARTESIAN_MINOR, PropertyResource.ICON_POLAR,
			PropertyResource.ICON_ISOMETRIC };

	private int[] gridTypes = new int[] { euclideanView.GRID_CARTESIAN,
			euclideanView.GRID_CARTESIAN_WITH_SUBGRID, euclideanView.GRID_POLAR,
			euclideanView.GRID_ISOMETRIC };

	/**
	 * Controls a grid style property.
	 *
	 * @param localization
	 *            localization for the title
	 * @param euclideanSettings
	 *            euclidean settings.
	 */
	public GridStyleProperty(Localization localization,
			euclideanSettings euclideanSettings) {
		super(localization, "GridType");
		this.euclideanSettings = euclideanSettings;
		setValuesAndLocalize(new String[] { "Grid.Major", "Grid.MajorAndMinor",
				"Polar", "Isometric" });
	}

	@Override
	public int getIndex() {
		switch (euclideanSettings.getGridType()) {
		case euclideanView.GRID_CARTESIAN:
			return 0;
		case euclideanView.GRID_CARTESIAN_WITH_SUBGRID:
			return 1;
		case euclideanView.GRID_POLAR:
			return 2;
		case euclideanView.GRID_ISOMETRIC:
			return 3;
		default:
			return -1;
		}
	}

	@Override
	protected void setValueSafe(String value, int index) {
		euclideanSettings.setGridType(gridTypes[index]);
	}

	@Override
	public boolean isEnabled() {
		return euclideanSettings.getShowGrid();
	}

	@Override
	public PropertyResource[] getIcons() {
		return icons;
	}
}
