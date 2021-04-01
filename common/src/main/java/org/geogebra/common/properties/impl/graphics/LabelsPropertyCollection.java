package org.geogebra.common.properties.impl.graphics;

import java.util.ArrayList;

import org.geogebra.common.main.Localization;
import org.geogebra.common.main.settings.euclideanSettings;
import org.geogebra.common.properties.Property;
import org.geogebra.common.properties.impl.AbstractPropertyCollection;

/**
 * This collection groups properties that are related to labeling the axes.
 */
public class LabelsPropertyCollection extends AbstractPropertyCollection {

	/**
	 * Constructs a labels property collection.
	 * @param localization localization for the title
	 * @param euclideanSettings euclidean settings
	 */
	public LabelsPropertyCollection(Localization localization,
			euclideanSettings euclideanSettings) {
		super(localization, "Labels");

		ArrayList<Property> properties = new ArrayList<>();
		properties.add(new AxesLabelsVisibilityProperty(localization, euclideanSettings));
		properties.add(new AxisLabelProperty(localization, euclideanSettings, "xAxis", 0));
		properties.add(new AxisLabelProperty(localization, euclideanSettings, "yAxis", 1));
		if (euclideanSettings.getDimension() > 2) {
			properties.add(new AxisLabelProperty(localization,
					euclideanSettings, "zAxis", 2));
		}

		setProperties(properties.toArray(new Property[0]));
	}
}
