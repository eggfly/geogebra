package org.geogebra.common.properties.impl.graphics;

import org.geogebra.common.main.App;
import org.geogebra.common.main.Localization;
import org.geogebra.common.plugin.euclideanStyleConstants;
import org.geogebra.common.properties.impl.AbstractEnumerableProperty;

/**
 * Property for setting the point capturing.
 */
public class PointCapturingProperty extends AbstractEnumerableProperty {

    private static final int[] pointCapturingModes = {
            euclideanStyleConstants.POINT_CAPTURING_AUTOMATIC,
            euclideanStyleConstants.POINT_CAPTURING_ON,
            euclideanStyleConstants.POINT_CAPTURING_ON_GRID,
            euclideanStyleConstants.POINT_CAPTURING_OFF
    };

    private App app;

    /**
     * Constructs a point capturing property.
     *
     * @param app          app
     * @param localization localization
     */
    public PointCapturingProperty(App app, Localization localization) {
        super(localization, "PointCapturing");
        this.app = app;
        setValuesAndLocalize(new String[]{
                "Labeling.automatic",
                "SnapToGrid",
                "FixedToGrid",
                "Off"
        });
    }

    @Override
    public int getIndex() {
        int pointCapturingMode = app.getActiveeuclideanView().getPointCapturingMode();
        for (int i = 0; i < pointCapturingModes.length; i++) {
            if (pointCapturingModes[i] == pointCapturingMode) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void setValueSafe(String value, int index) {
        app.getActiveeuclideanView().setPointCapturing(pointCapturingModes[index]);
    }
}
