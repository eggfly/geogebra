package org.geogebra.common.properties.impl.graphics;

import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.main.Localization;
import org.geogebra.common.main.error.ErrorHelper;
import org.geogebra.common.main.settings.euclideanSettings;
import org.geogebra.common.properties.BooleanProperty;
import org.geogebra.common.properties.impl.AbstractProperty;

import com.google.j2objc.annotations.Weak;

/**
 * This property controls the distance of the axes numbering.
 */
public class AxesNumberingDistanceProperty extends AbstractProperty implements BooleanProperty {

    @Weak
    private euclideanSettings euclideanSettings;
    @Weak
    private Kernel kernel;
    @Weak
    private euclideanView euclideanView;

    /**
     * Constructs an Axes numbering distance property.
     *
     * @param localization localization for the title
     */
    AxesNumberingDistanceProperty(Localization localization, euclideanSettings
            euclideanSettings, euclideanView euclideanView, Kernel kernel) {
        super(localization, "Automatic");
        this.euclideanSettings = euclideanSettings;
        this.kernel = kernel;
        this.euclideanView = euclideanView;
    }

    @Override
    public boolean getValue() {
        boolean[] axesAutomaticDistances = euclideanSettings.getAutomaticAxesNumberingDistances();

        for (int i = 0; i < euclideanSettings.getDimension(); i++) {
            if (!axesAutomaticDistances[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void setValue(boolean automatic) {
        if (automatic) {
            setAutoDistance();
        } else {
            setCustomDistance();
        }
    }

    private void setCustomDistance() {
        double[] axesDistances = euclideanView.getAxesNumberingDistances();
        for (int i = 0; i < euclideanSettings.getDimension(); i++) {
            euclideanSettings.setAxisNumberingDistance(i, kernel.getAlgebraProcessor()
                    .evaluateToNumeric("" + axesDistances[i] / 2, ErrorHelper.silent()));
        }
    }

    private void setAutoDistance() {
        for (int i = 0; i < euclideanSettings.getDimension(); i++) {
            euclideanSettings.setAutomaticAxesNumberingDistance(true, i, true);
        }
    }
}
