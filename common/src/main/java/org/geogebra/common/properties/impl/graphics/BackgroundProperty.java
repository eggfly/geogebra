package org.geogebra.common.properties.impl.graphics;

import org.geogebra.common.geogebra3D.euclidean3D.euclideanView3D;
import org.geogebra.common.geogebra3D.euclidean3D.openGL.Renderer;
import org.geogebra.common.main.App;
import org.geogebra.common.main.Localization;
import org.geogebra.common.properties.impl.AbstractEnumerableProperty;

public class BackgroundProperty extends AbstractEnumerableProperty {

	private App app;

	/**
	 * Constructs an AbstractEnumerableProperty
	 *
	 * @param app
	 *            app
	 * @param localization
	 *            the localization used
	 */
	public BackgroundProperty(App app, Localization localization) {
		super(localization, "ar.background");
		this.app = app;
		setValuesAndLocalize(new String[] { "Camera", "ar.filter", "ar.opaqueColor"});
	}

	@Override
	protected void setValueSafe(String value, int index) {
		// after settings
		euclideanView3D euclideanView3D = (euclideanView3D) app.getActiveeuclideanView();
		if (euclideanView3D.isXREnabled()) {
            euclideanView3D.getRenderer().setBackgroundStyle(
                    Renderer.BackgroundStyle.values()[index]);
		}
	}

	@Override
	public int getIndex() {
		return app.geteuclideanView3D().getRenderer().getBackgroundStyle().ordinal();
	}
}
