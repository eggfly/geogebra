package org.geogebra.common.properties.impl.graphics;

import java.util.ArrayList;

import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.main.App;
import org.geogebra.common.main.Localization;
import org.geogebra.common.main.settings.euclideanSettings;
import org.geogebra.common.properties.Property;
import org.geogebra.common.properties.impl.AbstractPropertyCollection;

/**
 * This collection groups properties that are related to the distances of axes numbering.
 */
public class DistancePropertyCollection extends AbstractPropertyCollection {

	/**
	 * Constructs a numbering distances property collection.
	 * @param app application
	 * @param localization localization for the title
	 * @param euclideanSettings EV settings
	 */
	public DistancePropertyCollection(App app, Localization localization, euclideanSettings
			euclideanSettings) {
		super(localization, "Distance");

		Kernel kernel = app.getKernel();
		euclideanView euclideanView = app.getActiveeuclideanView();
		ArrayList<Property> properties = new ArrayList<>();

		properties.add(new AxesNumberingDistanceProperty(localization, euclideanSettings,
				euclideanView, kernel));
		properties.add(new AxisDistanceProperty(localization, euclideanSettings, euclideanView,
				kernel, "xAxis", 0));
		properties.add(new AxisDistanceProperty(localization, euclideanSettings, euclideanView,
				kernel, "yAxis", 1));
		if (euclideanSettings.getDimension() > 2) {
			properties.add(
					new AxisDistanceProperty(localization, euclideanSettings, euclideanView, kernel,
							"zAxis", 2));
		}

		setProperties(properties.toArray(new Property[0]));
	}
}
