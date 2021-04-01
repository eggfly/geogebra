package org.geogebra.common.main.settings.updater;

import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.main.App;
import org.geogebra.common.main.settings.FontSettings;
import org.geogebra.common.util.Util;

import com.google.j2objc.annotations.Weak;

/**
 * This class updates the font settings.
 * Every complex (longer than 1 line) logic related to font settings
 * should be implemented in this class.
 */
public class FontSettingsUpdater {

	@Weak
	private App app;
	private FontSettings fontSettings;

	/**
	 * This constructor is protected because it should be called only by the SettingsUpdaterBuilder.
	 * @param app app
	 */
	protected FontSettingsUpdater(App app) {
		this.app = app;
		fontSettings = app.getSettings().getFontSettings();
	}

	/**
	 * Changes font size and possibly resets fonts
	 *
	 * @param fontSize
	 *            font size
	 * @see #resetFonts()
	 */
	public void setAppFontSize(int fontSize) {
		if (fontSize == fontSettings.getAppFontSize()) {
			return;
		}
		fontSettings.setAppFontSize(Util.getValidFontSize(fontSize));
	}

	/**
	 * Sets the app font size and updates the views.
	 * @param fontSize font size
	 */
	public void setAppFontSizeAndUpdateViews(int fontSize) {
		setAppFontSize(fontSize);
		updateeuclideanViews();
		resetFonts();
		app.updateUI();
	}

	private void updateeuclideanViews() {
		euclideanView ev1 = app.geteuclideanView1();
		if (ev1 != null && ev1.hasStyleBar()) {
			ev1.getStyleBar().reinit();
		}

		if (app.haseuclideanView2(1)) {
			euclideanView ev2 = app.geteuclideanView2(1);
			if (ev2 != null && ev2.hasStyleBar()) {
				ev2.getStyleBar().reinit();
			}
		}

		if (app.iseuclideanView3Dinited() && app.geteuclideanView3D().hasStyleBar()) {
			app.geteuclideanView3D().getStyleBar().reinit();
		}
	}

	/**
	 * Update font sizes of all components to match current GUI font size
	 */
	public void resetFonts() {
		app.getFontManager().setFontSize(getGUIFontSize());
		updateeuclideanViewFonts();
	}

	protected void updateeuclideanViewFonts() {
		euclideanView euclideanView = app.geteuclideanView1();
		if (euclideanView != null) {
			euclideanView.updateFonts();
		}

		if (app.getGuiManager() != null) {
			app.getGuiManager().updateFonts();
			if (app.haseuclideanView2(1)) {
				app.geteuclideanView2(1).updateFonts();
			}
		}
		if (app.getCompanion() != null) {
			app.getCompanion().updateFonts3D();
		}
	}

	/**
	 * @param size
	 *            GUI font size
	 */
	public void setGUIFontSizeAndUpdate(int size) {
		fontSettings.setGuiFontSize(size);
		resetFonts();
	}

	/**
	 * @return font size for GUI; if not specified, general font size is
	 *         returned
	 */
	public int getGUIFontSize() {
		int guiFontSize = fontSettings.getGuiFontSize();
		return guiFontSize == -1 ? fontSettings.getAppFontSize() : guiFontSize;
	}

	protected FontSettings getFontSettings() {
		return fontSettings;
	}

	protected App getApp() {
		return app;
	}
}
