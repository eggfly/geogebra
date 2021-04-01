package org.geogebra.common.main.settings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.geogebra.common.euclidean.euclideanViewInterfaceCommon;
import org.geogebra.common.main.App;

/**
 * Class which contains references to all settings of the application.
 * 
 * To add new setting containers to this class perform the following steps: 1.
 * Add attributes and getters (no setters allowed!) 2. Init in constructor 3.
 * Modify beginBatch() and endBatch()
 * 
 * @author Florian Sonner
 */
public class Settings {

	private Set<Resetable> resetableSettings;

	private final euclideanSettings[] euclideanSettings;

	private HashMap<String, euclideanSettings> euclideanSettingsForPlane;

	private AlgebraSettings algebraSettings;

	private SpreadsheetSettings spreadsheetSettings;

	private ConstructionProtocolSettings consProtSettings;

	private LayoutSettings layoutSettings;

	private ApplicationSettings applicationSettings;

	private AbstractSettings keyboardSettings;

	private CASSettings casSettings;

	private ProbabilityCalculatorSettings probCalcSettings;

	private DataAnalysisSettings daSettings;

	private TableSettings tableSettings;

	private FontSettings fontSettings;

	private LabelSettings labelSettings;

	private StyleSettings styleSettings;

	/**
	 * Initialize settings using the constructors of the setting container
	 * classes.
	 * 
	 * @param app
	 *            - app
	 * 
	 * @param euclideanLength
	 *            2 or 3 euclidean views
	 */
	public Settings(App app, int euclideanLength) {
		euclideanSettings = new euclideanSettings[euclideanLength];
		euclideanSettingsForPlane = new HashMap<>();
		resetableSettings = new HashSet<>();
		resetSettings(app);
	}

	private static euclideanSettings createEuclidanSettings(App app, int i) {
		if (i == 2) { // 3D view
			return new euclideanSettings3D(app);
		}

		return new euclideanSettings(app);
	}

	/**
	 * clear settings
	 * 
	 * @param app
	 *            - app
	 */
	public void resetSettings(App app) {
		for (int i = 0; i < euclideanSettings.length; ++i) {
			if (euclideanSettings[i] == null) {
				euclideanSettings[i] = createEuclidanSettings(app, i);
			} else {
				LinkedList<SettingListener> ls = euclideanSettings[i]
						.getListeners();
				euclideanSettings[i] = createEuclidanSettings(app, i);
				Iterator<SettingListener> lsi = ls.iterator();
				while (lsi.hasNext()) {
					SettingListener a = lsi.next();
					euclideanSettings[i].addListener(a);
				}
			}
		}

		for (euclideanSettings settings : euclideanSettingsForPlane.values()) {
			settings.reset();
		}

		if (spreadsheetSettings == null) {
			spreadsheetSettings = new SpreadsheetSettings();
		} else {
			spreadsheetSettings = new SpreadsheetSettings(
					spreadsheetSettings.getListeners());
		}

		if (consProtSettings == null) {
			consProtSettings = new ConstructionProtocolSettings();
		} else {
			consProtSettings = new ConstructionProtocolSettings(
					consProtSettings.getListeners());
		}

		if (layoutSettings == null) {
			layoutSettings = new LayoutSettings();
		} else {
			layoutSettings = new LayoutSettings(layoutSettings.getListeners());
		}

		if (applicationSettings == null) {
			applicationSettings = new ApplicationSettings();
		} else {
			applicationSettings = new ApplicationSettings(
					applicationSettings.getListeners());
		}

		if (keyboardSettings == null) {
			keyboardSettings = app.getKeyboardSettings(keyboardSettings);
		}

		if (casSettings == null) {
			casSettings = new CASSettings();
		} else {
			casSettings = new CASSettings(casSettings.getListeners());
		}
		if (!app.getConfig().isCASEnabled()) {
			casSettings.setEnabled(false);
		}

		if (probCalcSettings == null) {
			probCalcSettings = new ProbabilityCalculatorSettings();
		} else {
			probCalcSettings = new ProbabilityCalculatorSettings(
					probCalcSettings.getListeners());
		}

		tableSettings = new TableSettings();
		styleSettings = new StyleSettings();

		for (Resetable setting : resetableSettings) {
			setting.resetDefaults();
		}
	}

	/**
	 * Begin batch for all settings at once (helper).
	 * 
	 * Remark: Recommended to be used just for file loading, in other situations
	 * individual setting containers should be used to start batching.
	 */
	public void beginBatch() {
		for (euclideanSettings settings : euclideanSettings) {
			settings.beginBatch();
		}

		for (euclideanSettings settings : euclideanSettingsForPlane.values()) {
			settings.beginBatch();
		}

		algebraSettings.beginBatch();
		spreadsheetSettings.beginBatch();
		consProtSettings.beginBatch();
		layoutSettings.beginBatch();
		applicationSettings.beginBatch();
		keyboardSettings.beginBatch();
		casSettings.beginBatch();
		probCalcSettings.beginBatch();
		tableSettings.beginBatch();
	}

	/**
	 * End batch for all settings at once (helper).
	 * 
	 * Remark: Recommended to be used just for file loading, in other situations
	 * individual setting containers should be used to end batching.
	 */
	public void endBatch() {
		for (euclideanSettings settings : euclideanSettings) {
			settings.endBatch();
		}

		for (euclideanSettings settings : euclideanSettingsForPlane.values()) {
			settings.endBatch();
		}

		algebraSettings.endBatch();
		spreadsheetSettings.endBatch();
		consProtSettings.endBatch();
		layoutSettings.endBatch();
		applicationSettings.endBatch();
		keyboardSettings.endBatch();
		casSettings.endBatch();
		probCalcSettings.endBatch();
		tableSettings.endBatch();
	}

	/**
	 * @param number
	 *            Number of euclidean view to return settings for. Starts with
	 *            1.
	 * @return Settings of euclidean view.
	 */
	public final euclideanSettings geteuclidean(int number) {
		return euclideanSettings[number == -1 ? 2 : number - 1];
	}
	
	/**
	 * @return - if support 3d
	 */
	public final boolean supports3D() {
		if (euclideanSettings.length <= 2) {
			return false;
		}
		return geteuclidean(-1).isEnabled();
	}

	/**
	 * 
	 * @param plane
	 *            name of the plane creator
	 * @return settings of view for this plane
	 */
	public final euclideanSettings geteuclideanForPlane(String plane) {
		return euclideanSettingsForPlane.get(plane);
	}

	/**
	 * map the plane/settings
	 * 
	 * @param plane
	 *            name of the plane creator
	 * @param settings
	 *            settings
	 */
	public final void seteuclideanSettingsForPlane(String plane,
			euclideanSettings settings) {
		euclideanSettingsForPlane.put(plane, settings);
	}

	/**
	 * clear all settings for plane
	 */
	public final void cleareuclideanSettingsForPlane() {
		euclideanSettingsForPlane.clear();
	}

	/**
	 * remove settings for this plane
	 * 
	 * @param plane
	 *            name of the plane creator
	 */
	public final void removeeuclideanSettingsForPlane(String plane) {
		euclideanSettingsForPlane.remove(plane);
	}

	/**
	 * @return Settings of the algebra view.
	 */
	public final AlgebraSettings getAlgebra() {
		return algebraSettings;
	}

	/**
	 * @return Settings of the spreadsheet view.
	 */
	public final SpreadsheetSettings getSpreadsheet() {
		return spreadsheetSettings;
	}

	/**
	 * @return Settings of the spreadsheet view.
	 */
	public final TableSettings getTable() {
		return this.tableSettings;
	}

	/**
	 * @return style settings (e.g. for buttons)
	 */
	public StyleSettings getStyle() {
		return styleSettings;
	}

	/**
	 * Restores spreadsheet defaults
	 */
	public void restoreDefaultSpreadsheetSettings() {
		if (spreadsheetSettings == null) {
			spreadsheetSettings = new SpreadsheetSettings();
		} else {
			spreadsheetSettings = new SpreadsheetSettings(
					spreadsheetSettings.getListeners());
		}
	}

	/**
	 * @return Settings of the probability calculator view.
	 */
	public final ProbabilityCalculatorSettings getProbCalcSettings() {
		return probCalcSettings;
	}

	/**
	 * @return Settings of the construction protocol.
	 */
	public final ConstructionProtocolSettings getConstructionProtocol() {
		return consProtSettings;
	}

	/**
	 * @return layout settings
	 */
	public final LayoutSettings getLayout() {
		return layoutSettings;
	}

	/**
	 * @return General settings of the application.
	 */
	public final ApplicationSettings getApplication() {
		return applicationSettings;
	}

	/**
	 * @return desktop keyboard settings
	 */
	public final AbstractSettings getKeyboard() {
		return keyboardSettings;
	}

	/**
	 * @return CAS settings
	 */
	public final CASSettings getCasSettings() {
		return casSettings;
	}

	/**
	 * @param ev
	 *            - euclidean view
	 * @param app
	 *            - app
	 * @return -
	 */
	public euclideanSettings geteuclideanForView(
			euclideanViewInterfaceCommon ev, App app) {
		if (app.geteuclideanView1() == ev) {
			return geteuclidean(1);
		} else if (app.haseuclideanView2EitherShowingOrNot(1)
				&& app.geteuclideanView2(1) == ev) {
			return geteuclidean(2);
		} else if (app.iseuclideanView3D(ev)) {
			return geteuclidean(3);
		} else if (ev.isViewForPlane()) {
			return ev.getSettings();
		} else {
			return null;
		}
	}

	/**
	 * @return data analysis settings
	 */
	public DataAnalysisSettings getDataAnalysis() {
		if (this.daSettings == null) {
			daSettings = new DataAnalysisSettings();
		}
		return daSettings;
	}

	/**
	 * Reset euclidean settings for all views, no notifications.
	 */
	public void resetNoFireeuclideanSettings() {
		if (euclideanSettings == null) {
			return;
		}
		for (euclideanSettings s : euclideanSettings) {
			if (s != null) {
				s.resetNoFire();
			}
		}
	}

	public FontSettings getFontSettings() {
		return fontSettings;
	}

	void setFontSettings(FontSettings fontSettings) {
		this.fontSettings = fontSettings;
	}

	public LabelSettings getLabelSettings() {
		return labelSettings;
	}

	void setLabelSettings(LabelSettings labelSettings) {
		this.labelSettings = labelSettings;
	}

	void setAlgebraSettings(AlgebraSettings algebraSettings) {
		this.algebraSettings = algebraSettings;
		resetableSettings.add(algebraSettings);
	}
}