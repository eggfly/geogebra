package org.geogebra.common.properties.impl.graphics;

import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.geogebra3D.euclidean3D.euclideanView3D;
import org.geogebra.common.main.App;
import org.geogebra.common.main.Localization;
import org.geogebra.common.properties.ActionsEnumerableProperty;
import org.geogebra.common.properties.PropertyResource;

/**
 * This property controls the current positioning of the grid
 */
public class GraphicsPositionProperty implements ActionsEnumerableProperty {

    private Localization localization;
    private euclideanView euclideanView;
    private App app;

    private PropertyResource[] icons;
    private PropertyResource[] iconsAR;

    private String[] values;
    private String[] valuesAR;

    private Runnable[] callbacks;
    private Runnable[] callbacksAR;

    /**
     * Constructs a GraphicsPositionProperty
     *
     * @param app for access to localization, the euclideanView, and the app configuration
     */
    public GraphicsPositionProperty(final App app) {
        this.app = app;
        this.localization = app.getLocalization();
        this.euclideanView = app.getActiveeuclideanView();
    }

    @Override
    public Runnable[] getActions() {
        if (euclideanView.isXREnabled()) {
            if (callbacksAR == null) {
                callbacksAR = new Runnable[]{
                        new Runnable() {
                            @Override
                            public void run() {
                                // restart AR session
                                euclideanView3D euclideanView3D = (euclideanView3D) euclideanView;
                                euclideanView3D.getRenderer().setARShouldRestart();
                            }
                        },
                        new Runnable() {
                            @Override
                            public void run() {
                                euclideanView3D euclideanView3D = (euclideanView3D) euclideanView;
                                euclideanView3D.getRenderer().fitThicknessInAR();
                            }
                        }
                };
            }
            return callbacksAR;
        } else {
            if (callbacks == null) {
                callbacks = new Runnable[]{
                        new Runnable() {
                            @Override
                            public void run() {
                                euclideanView.setStandardView(true);
                            }
                        },
                        new Runnable() {
                            @Override
                            public void run() {
                                boolean keepRatio = app.getConfig().shouldKeepRatioeuclidean();
                                euclideanView.setViewShowAllObjects(true, keepRatio);
                            }
                        }
                };
            }
            return callbacks;
        }
    }

    @Override
    public PropertyResource[] getIcons() {
        if (euclideanView.isXREnabled()) {
            if (iconsAR == null) {
                iconsAR = new PropertyResource[]{
                        PropertyResource.ICON_RELOAD_AR,
                        PropertyResource.ICON_AR_FIT_THICKNESS
                };
            }
            return iconsAR;
        } else {
            if (icons == null) {
                icons = new PropertyResource[]{
                        PropertyResource.ICON_STANDARD_VIEW,
                        PropertyResource.ICON_ZOOM_TO_FIT
                };
            }
            return icons;
        }
    }

    @Override
    public String[] getValues() {
        if (euclideanView.isXREnabled()) {
            if (valuesAR == null) {
                valuesAR = new String[]{
                        "ar.restart",
                        "ar.FitThickness"
                };
                localizeValues(valuesAR);
            }
            return valuesAR;
        } else {
            if (values == null) {
                values = new String[]{
                        "StandardView",
                        "ShowAllObjects"
                };
                localizeValues(values);
            }
            return values;
        }
    }

    private void localizeValues(String[] messages) {
        for (int i = 0; i < messages.length; i++) {
            messages[i] = localization.getMenu(messages[i]);
        }
    }

	@Override
	public String getName() {
        return null; // no name needed
    }

	@Override
	public boolean isEnabled() {
        return true;
    }
}