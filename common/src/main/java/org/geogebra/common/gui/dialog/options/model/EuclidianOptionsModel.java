package org.geogebra.common.gui.dialog.options.model;

import org.geogebra.common.awt.GColor;
import org.geogebra.common.awt.GFont;
import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.euclidean.background.BackgroundType;
import org.geogebra.common.geogebra3D.euclidean3D.euclideanView3D;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.kernel.StringTemplate;
import org.geogebra.common.kernel.algos.AlgoFractionText;
import org.geogebra.common.kernel.arithmetic.NumberValue;
import org.geogebra.common.main.App;
import org.geogebra.common.main.Localization;
import org.geogebra.common.main.settings.euclideanSettings;
import org.geogebra.common.main.settings.euclideanSettings3D;
import org.geogebra.common.plugin.euclideanStyleConstants;
import org.geogebra.common.util.DoubleUtil;

import com.himamis.retex.editor.share.util.Unicode;

public class euclideanOptionsModel {
	public static final int MAX_AXES_STYLE_COUNT = 5;
	public static final int X_AXIS = 0;
	public static final int Y_AXIS = 1;
	public static final int Z_AXIS = 2;
	private App app;
	private euclideanView view;
	private IeuclideanOptionsListener listener;

	public euclideanOptionsModel(App app, euclideanView view,
			IeuclideanOptionsListener listener) {
		this.app = app;
		this.view = view;
		this.listener = listener;
	}

	public static int getAxesStyleLength() {
		return MAX_AXES_STYLE_COUNT;
	}

	public void setView(euclideanView view) {
		this.view = view;
	}

	public void applyBackgroundColor() {
		if (view == app.geteuclideanView1()) {
			app.getSettings().geteuclidean(1)
					.setBackground(listener.geteuclideanBackground(1));
		} else if (app.haseuclideanView2EitherShowingOrNot(1)
				&& app.geteuclideanView2(1) == view) {
			app.getSettings().geteuclidean(2)
					.setBackground(listener.geteuclideanBackground(2));
		} else if (app.iseuclideanView3D(view)) {
			app.getSettings().geteuclidean(3)
					.setBackground(listener.geteuclideanBackground(3));
		} else {
			view.setBackground(view.getBackgroundCommon());
		}
	}

	public void applyBackgroundColor(int viewIdx, GColor color) {
		if (viewIdx == 1 || viewIdx == 2 || viewIdx == 3) {
			app.getSettings().geteuclidean(viewIdx).setBackground(color);
			return;
		}

		euclideanSettings settings = view.getSettings();
		if (settings != null) {
			settings.setBackground(color);
			return;
		}

		view.setBackground(view.getBackgroundCommon());
	}

	public void applyAxesColor(GColor col) {
		euclideanSettings settings = getSettings();
		if (settings != null) {
			settings.setAxesColor(col);
			return;
		}

		view.setAxesColor(col);
	}

	private euclideanSettings getSettings() {
		if (view == app.geteuclideanView1()) {
			return app.getSettings().geteuclidean(1);
		}
		if (app.haseuclideanView2EitherShowingOrNot(1)
				&& app.geteuclideanView2(1) == view) {
			return app.getSettings().geteuclidean(2);
		}
		if (app.iseuclideanView3D(view)) {
			return app.getSettings().geteuclidean(3);
		}
		return view.getSettings();
	}

	public void applyGridColor(GColor col) {
		euclideanSettings settings = getSettings();
		if (settings != null) {
			settings.setGridColor(col);
			return;
		}

		view.setGridColor(col);
	}

	public void applyRulerColor(GColor col) {
		view.getSettings().setBgRulerColor(col);
		view.repaintView();
	}

	public void applyBoldRuler(boolean bold) {
		view.getSettings().setRulerBold(bold);
		view.repaintView();
	}

	public void applyTooltipMode(int mode0) {
		int mode = mode0;
		if (mode == 0) {
			mode = euclideanStyleConstants.TOOLTIPS_ON;
		} else if (mode == 1) {
			mode = euclideanStyleConstants.TOOLTIPS_AUTOMATIC;
		} else if (mode == 2) {
			mode = euclideanStyleConstants.TOOLTIPS_OFF;
		}

		euclideanSettings settings = getSettings();
		if (settings != null) {
			settings.setAllowToolTips(mode);
			return;
		}

		view.setAllowToolTips(mode);
	}

	/**
	 * Apply chosen right angle style
	 * 
	 * @param mode0
	 *            - right angle style
	 */
	public void applyRightAngleStyle(int mode0) {
		int mode = mode0;
		if (mode == 0) {
			mode = euclideanStyleConstants.RIGHT_ANGLE_STYLE_NONE;
		} else if (mode == 1) {
			mode = euclideanStyleConstants.RIGHT_ANGLE_STYLE_SQUARE;
		} else if (mode == 2) {
			mode = euclideanStyleConstants.RIGHT_ANGLE_STYLE_DOT;
		} else if (mode == 3) {
			mode = euclideanStyleConstants.RIGHT_ANGLE_STYLE_L;
		}

		app.setRightAngleStyle(mode);
		app.geteuclideanView1().updateAllDrawables(true);
	}

	public void showAxes(boolean value) {
		euclideanSettings settings = getSettings();
		if (settings != null) {
			settings.setShowAxes(value);
			return;
		}

		view.setShowAxes(value, true);
	}

	public void applyBoldAxes(boolean isBold, boolean isVisible) {
		euclideanSettings settings = getSettings();
		if (settings != null) {
			settings.setBoldAxes(isBold);
			return;
		}

		view.setBoldAxes(isBold);
	}

	public void showGrid(boolean value) {
		euclideanSettings settings = getSettings();
		if (settings != null) {
			settings.showGrid(value);
			return;
		}

		view.showGrid(value);
	}

	public void applyBoldGrid(boolean value) {
		euclideanSettings settings = getSettings();
		if (settings != null) {
			settings.setGridIsBold(value);
			return;
		}

		view.setGridIsBold(value);
	}

	public void applyMouseCoords(boolean value) {
		euclideanSettings settings = getSettings();
		if (settings != null) {
			settings.setAllowShowMouseCoords(value);
			return;
		}

		view.setAllowShowMouseCoords(value);
	}

	public void applyGridType(int type) {
		euclideanSettings settings = getSettings();
		if (settings != null) {
			settings.setGridType(type);
			return;
		}

		view.setGridType(type);
		if (type == euclideanView.GRID_POLAR) {
			view.updateBounds(true, true);
		}
	}

	public void applyAxesStyle(int style) {
		euclideanSettings settings = getSettings();
		if (settings != null) {
			settings.setAxesLineStyle(style);
			return;
		}

		view.setAxesLineStyle(style);
	}

	public void applyGridStyle(int style) {
		euclideanSettings settings = getSettings();
		if (settings != null) {
			settings.setGridLineStyle(style);
			return;
		}

		view.setGridLineStyle(style);
	}

	public void applyRulerStyle(int style) {
		view.getSettings().setRulerLineStyle(style);
		view.repaintView();
	}

	public void applyGridManualTick(boolean value) {
		euclideanSettings settings = getSettings();
		if (settings != null) {
			settings.setAutomaticGridDistance(!value, true);
			return;
		}

		view.setAutomaticGridDistance(!value);
	}

	public void applyAxesRatio(double xval, double yval) {
		if (!(Double.isInfinite(xval) || Double.isNaN(xval)
				|| Double.isInfinite(yval) || Double.isNaN(yval))) {
			// ratio = xval / yval
			// xscale / yscale = ratio
			// => yscale = xscale * xval/yval
			view.setCoordSystem(view.getXZero(), view.getYZero(),
					view.getXscale(), view.getXscale() * xval / yval);
		}
	}

	public void applyLockRatio(double value) {
		view.getSettings().setLockedAxesRatio(value);
		listener.enableAxesRatio(
				view.isZoomable() && !view.isLockedAxesRatio());
	}

	public void applyMinMax(String text, MinMaxType type) {
		NumberValue minMax = app.getKernel().getAlgebraProcessor()
				.evaluateToNumeric(text, false);
		// not parsed to number => return all
		if (minMax == null) {
			listener.setMinMaxText(
					view.getXminObject().getLabel(StringTemplate.editTemplate),
					view.getXmaxObject().getLabel(StringTemplate.editTemplate),
					view.getYminObject().getLabel(StringTemplate.editTemplate),
					view.getYmaxObject().getLabel(StringTemplate.editTemplate),
					((euclideanView3D) view).getZminObject().getLabel(StringTemplate.editTemplate),
					((euclideanView3D) view).getZmaxObject().getLabel(StringTemplate.editTemplate));
		} else {
			final euclideanSettings settings = getSettings();
			if (settings instanceof euclideanSettings3D) {
				((euclideanSettings3D) settings).setUpdateScaleOrigin(true);
			}

			switch (type) {
			case maxX:
				if (settings != null) {
					settings.setXmaxObject(minMax, true);
				} else {
					view.setXmaxObject(minMax);
				}
				break;
			case maxY:
				if (settings != null) {
					settings.setYmaxObject(minMax, true);
				} else {
					view.setYmaxObject(minMax);
				}

				break;
			case minX:
				if (settings != null) {
					settings.setXminObject(minMax, true);
				} else {
					view.setXminObject(minMax);
				}
				break;
			case minY:
				if (settings != null) {
					settings.setYminObject(minMax, true);
				} else {
					view.setYminObject(minMax);
				}
				break;
			case minZ:
				if (settings != null) {
					((euclideanSettings3D) settings).setZminObject(minMax, true);
				} else {
					((euclideanView3D) view).setZminObject(minMax);
				}
				break;
			case maxZ:
				if (settings != null) {
					((euclideanSettings3D) settings).setZmaxObject(minMax, true);
				} else {
					((euclideanView3D) view).setZmaxObject(minMax);
				}
				break;
			default:
				break;
			}

			view.setXminObject(view.getXminObject());

			listener.enableAxesRatio(
					(view.isZoomable() && !view.isLockedAxesRatio()));

			view.updateBounds(true, true);
		}
	}

	public String[] fillTooltipCombo() {
		Localization loc = app.getLocalization();
		return new String[] { loc.getMenu("On"),
				loc.getMenu("Automatic"), loc.getMenu("Off") };
	}

	/**
	 * fill list with right angle styles
	 */
	public String[] fillRightAngleCombo() {
		return new String[] { app.getLocalization().getMenu("Off"),
				"\u25a1", "\u2219", "\u2335" };
	}

	public void updateProperties() {

		listener.updateAxes(view.getAxesColor(),
				view.getShowXaxis() && view.getShowYaxis(), view.areAxesBold());

		listener.updateGrid(view.getGridColor(), view.getShowGrid(),
				view.getGridIsBold(), view.getGridType());

		listener.updateBackgroundColor(getBackgroundColor());
		euclideanSettings es = view.getSettings();
		listener.updateRuler(es.getBackgroundType().value(), es.getBgRulerColor(),
				es.getRulerLineStyle(), es.isRulerBold());
		int ind = view.getAllowToolTips();
		int idx = -1;

		if (ind == euclideanStyleConstants.TOOLTIPS_ON) {
			idx = 0;
		} else if (ind == euclideanStyleConstants.TOOLTIPS_AUTOMATIC) {
			idx = 1;
		} else if (ind == euclideanStyleConstants.TOOLTIPS_OFF) {
			idx = 2;
		}

		listener.selectTooltipType(idx);

		listener.showMouseCoords(view.getAllowShowMouseCoords());

		listener.enableAxesRatio(
				view.isZoomable() && !view.isLockedAxesRatio());
		listener.enableLock(view.isZoomable());
		listener.updateBounds();

		// need style with bold removed for menu
		for (int i = 0; i < euclideanStyleConstants
				.getLineStyleOptionsLength(); i++) {
			if (euclideanView.getBoldAxes(false,
					view.getAxesLineStyle()) == euclideanStyleConstants
							.getLineStyleOptions(i)) {
				listener.selectAxesStyle(i);
				break;
			}
		}

		listener.selectGridStyle(view.getGridLineStyle());

		listener.updateGridTicks(view.isAutomaticGridDistance(),
				view.getGridDistances(), view.getGridType());

		// cons protocol panel
		listener.updateConsProtocolPanel(
				app.showConsProtNavigation(view.getViewID()));
		int fontStyle = view.getSettings().getAxisFontStyle();
		boolean serif = view.getSettings().getAxesLabelsSerif();
		listener.updateAxisFontStyle(serif,
				(fontStyle & GFont.BOLD) == GFont.BOLD,
				(fontStyle & GFont.ITALIC) == GFont.ITALIC);
	}

	public void fillGridTypeCombo() {
		String[] gridTypes = new String[4];
		Localization loc = app.getLocalization();
		gridTypes[euclideanView.GRID_CARTESIAN] = loc
				.getMenu("Grid.Major");
		gridTypes[euclideanView.GRID_ISOMETRIC] = loc.getMenu("Isometric");
		gridTypes[euclideanView.GRID_POLAR] = loc.getMenu("Polar");
		gridTypes[euclideanView.GRID_CARTESIAN_WITH_SUBGRID] = loc
					.getMenu("Grid.MajorAndMinor");

		for (String item : gridTypes) {
			listener.addGridTypeItem(item);
		}
	}

	public void fillRulingCombo() {
		for (BackgroundType type : BackgroundType.rulingOptions) {
			listener.addRulerTypeItem(getTitleForRulingType(type), type);
		}
	}

	public void fillAngleOptions() {
		String[] angleOptions = { Unicode.PI_STRING + "/12",
				Unicode.PI_STRING + "/6", Unicode.PI_STRING + "/4",
				Unicode.PI_STRING + "/3", Unicode.PI_STRING + "/2", };
		for (String item : angleOptions) {
			listener.addAngleOptionItem(item);
		}
	}

	public void applyGridTicks(String str, int idx) {
		double value = Double.NaN;
		final String text = str.trim();
		if (!"".equals(text)) {
			value = app.getKernel().getAlgebraProcessor()
					.evaluateToDouble(text);
		}
		if (value > 0) {
			double[] ticks = view.getGridDistances();
			ticks[idx] = value;
			view.setGridDistances(ticks);
		}
	}

	public void applyGridTickAngle(String text) {
		if (!"".equals(text)) {

			double[] ticks = view.getGridDistances();
			// val = 4 gives 5*PI/12, skip this and go to 6*Pi/2 = Pi/2

			double value = app.getKernel().getAlgebraProcessor()
					.evaluateToDouble(text);
			value = Math.PI
					/ Math.min(360, Math.round(Math.abs(Math.PI / value)));
			ticks[2] = value;
			view.setGridDistances(ticks);
		}
	}

	public String gridAngleToString() {
		double val = view.getGridDistances(2) / Math.PI;
		double[] frac = AlgoFractionText.decimalToFraction(val,
				Kernel.MAX_PRECISION);
		StringBuilder sb = new StringBuilder();
		if (frac[1] < 361) {
			if (!DoubleUtil.isEqual(1, frac[0])) {
				sb.append(Math.round(frac[0]));
			}
			sb.append(Unicode.pi);
			if (!DoubleUtil.isEqual(1, frac[1])) {
				sb.append("/");
				sb.append(Math.round(frac[1]));
			}
		} else {
			sb.append(app.getKernel().format(view.getGridDistances(2),
					StringTemplate.editTemplate));
		}
		return sb.toString();
	}

	public GColor getAxesColor() {
		return view.getAxesColor();
	}

	public GColor getGridColor() {
		return view.getGridColor();
	}

	public GColor getRulerColor() {
		return view.getSettings().getBgRulerColor();
	}

	public GColor getBackgroundColor() {
		return view.getBackgroundCommon();
	}

	public int getAxisFontStyle() {
		return view.getSettings().getAxisFontStyle();
	}

	public void setAxisFontStyle(int fontStyle) {
		view.getSettings().setAxisFontStyle(fontStyle);
		view.updateFonts();
	}

	public void setAxesLabelsSerif(boolean serif) {
		view.getSettings().setAxesLabelsSerif(serif);
		view.updateFonts();
	}

	public void setAxisFontBold(boolean value) {
		int style = getAxisFontStyle();
		if (value) {
			if (style == GFont.PLAIN || style == GFont.ITALIC) {
				style += GFont.BOLD;
				setAxisFontStyle(style);
			}
		} else {
			if (style == GFont.BOLD || style == GFont.ITALIC + GFont.BOLD) {
				style -= GFont.BOLD;
				setAxisFontStyle(style);
			}
		}
	}

	public void setAxisFontItalic(boolean value) {
		int style = getAxisFontStyle();
		if (value) {
			if (style == GFont.PLAIN || style == GFont.BOLD) {
				style += GFont.ITALIC;
				setAxisFontStyle(style);
			}
		} else {
			if (style == GFont.ITALIC || style == GFont.ITALIC + GFont.BOLD) {
				style -= GFont.ITALIC;
				setAxisFontStyle(style);
			}
		}
	}

	public enum MinMaxType {
		minX, maxX, minY, maxY, minZ, maxZ
	}

	public interface IeuclideanOptionsListener {

		GColor geteuclideanBackground(int viewNumber);

		void addRulerTypeItem(String item, BackgroundType type);

		void enableAxesRatio(boolean value);

		void setMinMaxText(String minX, String maxX, String minY, String maxY, String minZ,
				String maxZ);

		void addGridTypeItem(String item);

		void updateAxes(GColor color, boolean isShown, boolean isBold);

		void updateAxisFontStyle(boolean isSerif, boolean isBold,
				boolean isItalic);

		void updateBackgroundColor(GColor color);

		void updateGrid(GColor color, boolean isShown, boolean isBold,
				int gridType);

		void selectTooltipType(int index);

		void updateConsProtocolPanel(boolean isVisible);

		void updateBounds();

		void showMouseCoords(boolean value);

		void selectAxesStyle(int index);

		void updateGridTicks(boolean isAutoGrid, double[] gridTicks,
				int gridType);

		void enableLock(boolean zoomable);

		void selectGridStyle(int style);

		void addAngleOptionItem(String item);

		void updateRuler(int typeIdx, GColor color, int lineStyle, boolean bold);
	}

	/**
	 * Set ruler type from dropdown.
	 * 
	 * @param type
	 *            the selected index
	 */
	public void applyRulerType(BackgroundType type) {
		euclideanSettings settings = view.getSettings();
		settings.setBackgroundType(type);
	}

	private String getTransKeyForRulingType(BackgroundType rulingType) {
		switch (rulingType) {
			case RULER:
				return "Ruled";
			case SQUARE_SMALL:
				return "Squared5";
			case SQUARE_BIG:
				return "Squared1";
			case ELEMENTARY12:
				return "Elementary12";
			case ELEMENTARY12_COLORED:
				return "Elementary12Colored";
			case ELEMENTARY12_HOUSE:
				return "Elementary12WithHouse";
			case ELEMENTARY34:
				return "Elementary34";
			case MUSIC:
				return "Music";
			default:
				return "NoRuling";
		}
	}

	private String getTitleForRulingType(BackgroundType rulingType) {
		Localization loc = app.getLocalization();
		return loc.getMenu(getTransKeyForRulingType(rulingType));
	}
}
