package org.geogebra.desktop.geogebra3D.gui.view.properties;

import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.main.App;
import org.geogebra.common.main.OptionType;
import org.geogebra.desktop.geogebra3D.App3D;
import org.geogebra.desktop.geogebra3D.gui.dialogs.options.Optionseuclidean3DD;
import org.geogebra.desktop.gui.dialog.options.OptionPanelD;
import org.geogebra.desktop.gui.dialog.options.OptionseuclideanD;
import org.geogebra.desktop.gui.dialog.options.OptionseuclideanForPlaneD;
import org.geogebra.desktop.gui.view.properties.PropertiesStyleBarD;
import org.geogebra.desktop.gui.view.properties.PropertiesViewD;
import org.geogebra.desktop.main.AppD;

/**
 * Just adding 3D view for properties
 * 
 * @author mathieu
 *
 */
public class PropertiesView3DD extends PropertiesViewD {

	private OptionseuclideanD euclideanPanel3D;
	private OptionseuclideanForPlaneD euclideanForPlanePanel;

	/**
	 * Constructor
	 * 
	 * @param app
	 *            application
	 */
	public PropertiesView3DD(AppD app) {
		super(app);
	}

	@Override
	public OptionPanelD getOptionPanel(OptionType type) {

		switch (type) {
		case euclidean3D:
			if (euclideanPanel3D == null) {
				euclideanPanel3D = new Optionseuclidean3DD((AppD) app,
						((App3D) app).geteuclideanView3D());
				euclideanPanel3D.setLabels();
			}

			return euclideanPanel3D;

		case euclidean_FOR_PLANE:
			euclideanView view = app.getActiveeuclideanView();
			if (!view.isViewForPlane()) {
				view = app.getViewForPlaneVisible();
			}
			if (euclideanForPlanePanel == null) {
				euclideanForPlanePanel = new OptionseuclideanForPlaneD(
						(AppD) app, view);
				euclideanForPlanePanel.setLabels();
			} else {
				euclideanForPlanePanel.updateView(view);
				euclideanForPlanePanel.setLabels();
			}

			return euclideanForPlanePanel;
		}

		return super.getOptionPanel(type);
	}

	@Override
	public void setLabels() {

		super.setLabels();

		if (euclideanPanel3D != null) {
			euclideanPanel3D.setLabels();
		}

		if (euclideanForPlanePanel != null) {
			euclideanForPlanePanel.setLabels();
		}

	}

	@Override
	public void updateFonts() {

		if (isIniting) {
			return;
		}

		super.updateFonts();

		if (euclideanPanel3D != null) {
			euclideanPanel3D.updateFont();
		}

		if (euclideanForPlanePanel != null) {
			euclideanForPlanePanel.updateFont();
		}

	}

	@Override
	protected PropertiesStyleBarD newPropertiesStyleBar() {
		return new PropertiesStyleBar3DD(this, (AppD) app);
	}

	@Override
	public void updatePanelGUI(int id) {
		if (id == App.VIEW_euclidean3D && euclideanPanel3D != null) {
			euclideanPanel3D.updateGUI();
		} else {
			super.updatePanelGUI(id);
		}
	}

}
