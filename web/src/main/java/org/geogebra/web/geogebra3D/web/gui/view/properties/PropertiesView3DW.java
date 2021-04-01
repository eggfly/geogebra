package org.geogebra.web.geogebra3D.web.gui.view.properties;

import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.main.OptionType;
import org.geogebra.web.full.gui.dialog.options.OptionPanelW;
import org.geogebra.web.full.gui.dialog.options.OptionseuclideanW;
import org.geogebra.web.full.gui.properties.PropertiesStyleBarW;
import org.geogebra.web.full.gui.properties.PropertiesViewW;
import org.geogebra.web.geogebra3D.web.euclidean3D.euclideanView3DW;
import org.geogebra.web.geogebra3D.web.gui.dialog.options.Optionseuclidean3DW;
import org.geogebra.web.geogebra3D.web.gui.dialog.options.OptionseuclideanForPlaneW;
import org.geogebra.web.html5.main.AppW;

/**
 * Just adding 3D view for properties
 * 
 * @author mathieu
 *
 */
public class PropertiesView3DW extends PropertiesViewW {

	private OptionseuclideanW euclideanPanel3D;
	private OptionseuclideanW euclideanForPlanePanel;

	/**
	 * Constructor
	 * 
	 * @param app
	 *            application
	 * @param op
	 *            selected option on start
	 */
	public PropertiesView3DW(AppW app, OptionType op) {
		super(app, op);
	}

	@Override
	public OptionPanelW getOptionPanel(OptionType type, int subType) {
		switch (type) {
		case euclidean3D:
			if (euclideanPanel3D == null) {
				euclideanPanel3D = new Optionseuclidean3DW((AppW) app,
						app.geteuclideanView3D());
				euclideanPanel3D.setLabels();
				euclideanPanel3D.setView((euclideanView3DW) app
						.geteuclideanView3D());
			}

			return euclideanPanel3D;

		case euclidean_FOR_PLANE:
			euclideanView view = app.getActiveeuclideanView();
			if (!view.isViewForPlane()) {
				view = app.getViewForPlaneVisible();
			}
			if (euclideanForPlanePanel == null) {
				euclideanForPlanePanel = new OptionseuclideanForPlaneW(
						(AppW) app, view);
				euclideanForPlanePanel.setLabels();
			} else {
				euclideanForPlanePanel.updateView(view);
				euclideanForPlanePanel.setLabels();
			}

			return euclideanForPlanePanel;
		default:
			return super.getOptionPanel(type, subType);
		}
	}

	@Override
	protected PropertiesStyleBarW newPropertiesStyleBar() {
		return new PropertiesStyleBar3DW(this, (AppW) app);
	}

}
