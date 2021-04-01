package org.geogebra.common.main.settings.updater;

import org.geogebra.common.euclidean.euclideanHost;
import org.geogebra.common.gui.view.algebra.AlgebraView;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.main.AppConfig;
import org.geogebra.common.main.settings.AlgebraStyle;
import org.geogebra.common.main.settings.euclideanSettings;
import org.geogebra.common.main.settings.LabelVisibility;
import org.geogebra.common.main.settings.Settings;

import com.google.j2objc.annotations.Weak;

/**
 * Updates the settings.
 * Every complex (longer than 1 line) logic related to a combination of settings
 * should be implemented in this class.
 */
public class SettingsUpdater {

	@Weak
	private Kernel kernel;
	private euclideanHost euclideanHost;
	private Settings settings;
	private AppConfig appConfig;
	private FontSettingsUpdater fontSettingsUpdater;
	private LabelSettingsUpdater labelSettingsUpdater;

	/**
	 * Resets the settings which should be reset on app start and after Clear All.
	 */
	public void resetSettingsOnAppStart() {
		resetSettingsOnlyOnAppStart();
		resetSettingsAfterClearAll();
	}

	protected void resetSettingsOnlyOnAppStart() {
		kernel.setPrintDecimals(appConfig.getDefaultPrintDecimals());
		labelSettingsUpdater.setLabelVisibility(LabelVisibility.PointsOnly);
		settings.getAlgebra().setStyle(AlgebraStyle.DEFINITION_AND_VALUE);
	}

	/**
	 * Resets the settings which should be reset after Clear All.
	 */
	public void resetSettingsAfterClearAll() {
		setSortModeForCompactOutput();
		seteuclideanSettings();
	}

	private void setSortModeForCompactOutput() {
		settings.getAlgebra().setTreeMode(AlgebraView.SortMode.ORDER);
	}

	private void seteuclideanSettings() {
		euclideanSettings euclideanSettings = euclideanHost.getActiveeuclideanView().getSettings();
		euclideanSettings.reset();
		euclideanSettings.showGrid(appConfig.showGridOnFileNew());
		euclideanSettings.setShowAxes(appConfig.showAxesOnFileNew());
	}

	void seteuclideanHost(euclideanHost euclideanHost) {
		this.euclideanHost = euclideanHost;
	}

	protected Settings getSettings() {
		return settings;
	}

	void setSettings(Settings settings) {
		this.settings = settings;
	}

	void setAppConfig(AppConfig appConfig) {
		this.appConfig = appConfig;
	}

	protected Kernel getKernel() {
		return kernel;
	}

	void setKernel(Kernel kernel) {
		this.kernel = kernel;
	}

	void setFontSettingsUpdater(FontSettingsUpdater fontSettingsUpdater) {
		this.fontSettingsUpdater = fontSettingsUpdater;
	}

	public FontSettingsUpdater getFontSettingsUpdater() {
		return fontSettingsUpdater;
	}

	void setLabelSettingsUpdater(LabelSettingsUpdater labelSettingsUpdater) {
		this.labelSettingsUpdater = labelSettingsUpdater;
	}

	public LabelSettingsUpdater getLabelSettingsUpdater() {
		return labelSettingsUpdater;
	}
}
