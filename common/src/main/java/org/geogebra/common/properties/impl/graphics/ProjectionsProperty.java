package org.geogebra.common.properties.impl.graphics;

import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.euclidean3D.euclideanView3DInterface;
import org.geogebra.common.main.Localization;
import org.geogebra.common.main.settings.euclideanSettings3D;
import org.geogebra.common.properties.IconsEnumerableProperty;
import org.geogebra.common.properties.PropertyResource;
import org.geogebra.common.properties.impl.AbstractEnumerableProperty;

/**
 * This property controls the projection type for 3D view.
 */
public class ProjectionsProperty extends AbstractEnumerableProperty
		implements IconsEnumerableProperty {

    private euclideanView view;
	private euclideanSettings3D euclideanSettings;

	private PropertyResource[] icons = new PropertyResource[] {
			PropertyResource.ICON_PROJECTION_PARALLEL,
            PropertyResource.ICON_PROJECTION_PERSPECTIVE,
            PropertyResource.ICON_PROJECTION_GLASSES,
            PropertyResource.ICON_PROJECTION_OBLIQUE
	};

	/**
	 * Controls a grid style property.
	 *
	 * @param localization
	 *            localization for the title
     * @param view
     * 	          euclidean view.
	 * @param euclideanSettings
	 *            euclidean settings.
	 */
	public ProjectionsProperty(Localization localization,
                               euclideanView view,
                               euclideanSettings3D euclideanSettings) {
		super(localization, "Projection");
		this.view = view;
		this.euclideanSettings = euclideanSettings;
		setValuesAndLocalize(new String[] {
		        "stylebar.ParallelProjection",
                "stylebar.PerspectiveProjection",
				"stylebar.GlassesProjection",
                "stylebar.ObliqueProjection"
		});
	}

	@Override
	public int getIndex() {
	    if (view.isXREnabled()) {
	        return euclideanView3DInterface.PROJECTION_PERSPECTIVE;
        }
		return euclideanSettings.getProjection();
	}

	@Override
	protected void setValueSafe(String value, int index) {
		euclideanSettings.setProjection(index);
	}

	@Override
	public PropertyResource[] getIcons() {
		return icons;
	}

    @Override
    public boolean isEnabled() {
        return !view.isXREnabled();
    }
}
