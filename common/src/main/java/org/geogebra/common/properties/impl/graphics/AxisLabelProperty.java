package org.geogebra.common.properties.impl.graphics;

import org.geogebra.common.main.Localization;
import org.geogebra.common.main.settings.euclideanSettings;
import org.geogebra.common.properties.StringProperty;
import org.geogebra.common.properties.impl.AbstractProperty;

/**
 * This property controls the label on an axis.
 */
public class AxisLabelProperty extends AbstractProperty
        implements StringProperty {

    private euclideanSettings euclideanSettings;
    private int axis;

    /**
     * Constructs an xAxis property.
     *
     * @param localization      localization for the title
     * @param euclideanSettings euclidean settings
     * @param label             the name of the axis
     * @param axis              the axis for label
     */
    public AxisLabelProperty(Localization localization,
            euclideanSettings euclideanSettings, String label, int axis) {
        super(localization, label);
        this.euclideanSettings = euclideanSettings;
        this.axis = axis;
    }

    @Override
    public String getValue() {
        if (!isEnabled()) {
            return euclideanSettings.getDefaultAxisLabel(axis);
        }
        String axisLabel = euclideanSettings.getAxesLabels()[axis];
        return axisLabel == null ? "" : axisLabel;
    }

    @Override
    public void setValue(String value) {
        euclideanSettings.setAxisLabel(axis, value);
    }

    @Override
    public boolean isValid(String value) {
        return true;
    }

    @Override
    public boolean isEnabled() {
        String[] labels = euclideanSettings.getAxesLabels();
        boolean enabled = false;
        for (int i = 0; i < euclideanSettings.getDimension(); i++) {
            enabled |= labels[i] != null;
        }
        return enabled;
    }
}
