package org.geogebra.common.properties.impl.graphics;

import org.geogebra.common.main.Localization;
import org.geogebra.common.main.settings.euclideanSettings3D;
import org.geogebra.common.properties.BooleanProperty;
import org.geogebra.common.properties.impl.AbstractProperty;

public class PlaneVisibilityProperty extends AbstractProperty implements BooleanProperty {

    private euclideanSettings3D euclideanSettings;

    /**
     * Constructs an abstract property.
     *
     * @param localization      this is used to localize the name
     * @param euclideanSettings euclidean settings
     */
    public PlaneVisibilityProperty(Localization localization,
            euclideanSettings3D euclideanSettings) {
        super(localization, "ShowPlane");
        this.euclideanSettings = euclideanSettings;
    }

    @Override
    public boolean getValue() {
        return euclideanSettings.getShowPlate();
    }

    @Override
    public void setValue(boolean value) {
        euclideanSettings.setShowPlate(value);
    }
}
