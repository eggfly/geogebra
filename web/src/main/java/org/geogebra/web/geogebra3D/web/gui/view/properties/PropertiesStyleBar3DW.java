package org.geogebra.web.geogebra3D.web.gui.view.properties;

import org.geogebra.common.main.App;
import org.geogebra.common.main.OptionType;
import org.geogebra.web.full.gui.images.SvgPerspectiveResources;
import org.geogebra.web.full.gui.properties.PropertiesStyleBarW;
import org.geogebra.web.full.gui.properties.PropertiesViewW;
import org.geogebra.web.html5.main.AppW;

/**
 * Style bar for properties view (in 3D)
 * 
 * @author mathieu
 *
 */
public class PropertiesStyleBar3DW extends PropertiesStyleBarW {

	/**
	 * constructor
	 * 
	 * @param propertiesView
	 *            properties view
	 * @param app
	 *            application
	 */
	public PropertiesStyleBar3DW(PropertiesViewW propertiesView, AppW app) {
		super(propertiesView, app);
	}

	@Override
	protected String getTypeIcon(OptionType type) {
		SvgPerspectiveResources pr = SvgPerspectiveResources.INSTANCE;

		switch (type) {
		case euclidean3D:
			return pr.menu_icon_graphics3D_transparent().getSafeUri().asString();
		case euclidean_FOR_PLANE:
			return pr.menu_icon_graphics_extra().getSafeUri().asString();
		default:
			return super.getTypeIcon(type);
		}
	}

	@Override
	public void updateGUI() {

		super.updateGUI();

		setButtonVisible(OptionType.euclidean3D,
		        app.getGuiManager().showView(App.VIEW_euclidean3D));

		setButtonVisible(OptionType.euclidean_FOR_PLANE,
				app.haseuclideanViewForPlaneVisible());

	}

	@Override
	protected boolean typeAvailable(OptionType type) {
		return true;
	}

}
