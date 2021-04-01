package org.geogebra.common.properties.impl.graphics;

import org.geogebra.common.geogebra3D.euclidean3D.euclideanView3D;
import org.geogebra.common.main.App;
import org.geogebra.common.main.Localization;
import org.geogebra.common.properties.Property;
import org.geogebra.common.properties.impl.AbstractPropertyCollection;

public class ARRatioPropertyCollection extends AbstractPropertyCollection {

	/**
	 * Constructs a ar ratio property collection.
	 * @param app application
	 * @param localization localization for the title
	 */
	public ARRatioPropertyCollection(App app, Localization localization) {
		super(localization, "Settings.ArRatio");

		euclideanView3D view3D = ((euclideanView3D) app.getActiveeuclideanView());
		Property arRatio = new ARRatioProperty(localization, view3D);
		Property ratioUnit = new RatioUnitProperty(localization, view3D);
		setProperties(new Property[]{arRatio, ratioUnit});
	}
}
