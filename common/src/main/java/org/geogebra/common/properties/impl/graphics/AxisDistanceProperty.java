package org.geogebra.common.properties.impl.graphics;

import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.kernel.arithmetic.NumberValue;
import org.geogebra.common.kernel.geos.GeoNumberValue;
import org.geogebra.common.main.Localization;
import org.geogebra.common.main.settings.euclideanSettings;
import org.geogebra.common.properties.impl.AbstractNumericProperty;

/**
 * This property controls the distance of an axis numbering
 */
public class AxisDistanceProperty extends AbstractNumericProperty {

	private final euclideanSettings euclideanSettings;
	private final euclideanView euclideanView;
	private final int axis;

	/**
	 * Constructs an xAxis property.
	 * @param localization localization for the title
	 * @param euclideanSettings euclidean settings
	 * @param euclideanView the active euclidean view
	 * @param kernel kernel
	 * @param label label of the axis
	 * @param axis the axis for the numbering distance will be set
	 */
	AxisDistanceProperty(Localization localization, euclideanSettings
			euclideanSettings, euclideanView euclideanView, Kernel kernel, String label, int axis) {
		super(kernel.getAlgebraProcessor(), localization, label);
		this.euclideanSettings = euclideanSettings;
		this.euclideanView = euclideanView;
		this.axis = axis;
	}

	@Override
	protected void setNumberValue(GeoNumberValue value) {
		euclideanSettings.setAxisNumberingDistance(axis, value);
	}

	@Override
	protected NumberValue getNumberValue() {
		GeoNumberValue numberValue = euclideanSettings.getAxisNumberingDistance(axis);
		if (numberValue != null) {
			return numberValue;
		}
		return euclideanView.getAxesDistanceObjects()[axis];
	}

	@Override
	public boolean isEnabled() {
		boolean[] axesAutomaticDistances = euclideanSettings.getAutomaticAxesNumberingDistances();
		for (int i = 0; i < euclideanSettings.getDimension(); i++) {
			if (!axesAutomaticDistances[i]) {
				return true;
			}
		}
		return false;
	}
}
