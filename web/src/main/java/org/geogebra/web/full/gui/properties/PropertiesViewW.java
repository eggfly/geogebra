package org.geogebra.web.full.gui.properties;

import java.util.ArrayList;

import org.geogebra.common.gui.SetLabels;
import org.geogebra.common.gui.view.properties.PropertiesView;
import org.geogebra.common.kernel.ModeSetter;
import org.geogebra.common.kernel.geos.GProperty;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.main.App;
import org.geogebra.common.main.OptionType;
import org.geogebra.web.full.gui.dialog.options.OptionPanelW;
import org.geogebra.web.full.gui.dialog.options.OptionsAlgebraW;
import org.geogebra.web.full.gui.dialog.options.OptionsCASW;
import org.geogebra.web.full.gui.dialog.options.OptionsDefaultsW;
import org.geogebra.web.full.gui.dialog.options.OptionseuclideanW;
import org.geogebra.web.full.gui.dialog.options.OptionsGlobalW;
import org.geogebra.web.full.gui.dialog.options.OptionsLayoutW;
import org.geogebra.web.full.gui.dialog.options.OptionsObjectW;
import org.geogebra.web.full.gui.dialog.options.OptionsSpreadsheetW;
import org.geogebra.web.full.main.AppWFull;
import org.geogebra.web.html5.main.AppW;
import org.geogebra.web.html5.util.CSSAnimation;
import org.geogebra.web.html5.util.PersistablePanel;
import org.geogebra.web.html5.util.tabpanel.MultiRowsTabPanel;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author gabor
 * 
 * PropertiesView for Web
 *
 */
public class PropertiesViewW extends PropertiesView
		implements RequiresResize, SetLabels {

	private FlowPanel wrappedPanel;

	// option panels
	private OptionsDefaultsW defaultsPanel;
	private OptionseuclideanW euclideanPanel;
	private OptionseuclideanW euclideanPanel2;
	private OptionseuclideanW euclideanPanel3D;
	private OptionsSpreadsheetW spreadsheetPanel;
	private OptionsCASW casPanel;
	private OptionsLayoutW layoutPanel;
	private OptionsAlgebraW algebraPanel;
	private OptionsGlobalW globalPanel;
	
	private PropertiesStyleBarW styleBar;

	private FlowPanel contentsPanel;
	private OptionType optionType;
	private boolean floatingAttached = false;

	/**
	 * 
	 * @param app
	 *            app
	 * @param ot
	 *            initial options type
	 */
	public PropertiesViewW(AppW app, OptionType ot) {
		super(app);
		this.wrappedPanel = app.isUnbundledOrWhiteboard()
				? new PersistablePanel() : new FlowPanel();
		app.setPropertiesView(this);
		app.setWaitCursor();   

		optionType = ot;
		initGUI();
		app.setDefaultCursor();
	}

	private void initGUI() {
		wrappedPanel.addStyleName("PropertiesViewW");

		contentsPanel = new FlowPanel();
		contentsPanel.addStyleName("contentsPanel");
		wrappedPanel.add(contentsPanel);
		wrappedPanel.add(getStyleBar().getWrappedPanel());

		setOptionPanel(optionType, 0);
	}

	/**
	 * @return the style bar for this view.
	 */
	public PropertiesStyleBarW getStyleBar() {
		if (styleBar == null) {
			styleBar = newPropertiesStyleBar();
		}
		return styleBar;
	}

	/**
	 * @return properties stylebar
	 */
	protected PropertiesStyleBarW newPropertiesStyleBar() {
		return new PropertiesStyleBarW(this, app);
	}

	/**
	 * Returns the option panel for the given type. If the panel does not exist,
	 * a new one is constructed
	 * 
	 * @param type
	 *            panel type
	 * @param subType
	 *            tab number for given panel
	 * @return options panel
	 */
	public OptionPanelW getOptionPanel(OptionType type, int subType) {
		if (styleBar != null) {
			styleBar.updateGUI();
		}	
		switch (type) {
		case GLOBAL:
			if (globalPanel == null) {
				globalPanel = new OptionsGlobalW((AppW) app);
			}
			return globalPanel;

		case DEFAULTS:
			if (defaultsPanel == null) {
				defaultsPanel = new OptionsDefaultsW();
			}
			return defaultsPanel;

		case CAS:
			if (casPanel == null) {
				casPanel = new OptionsCASW((AppW) app);
			}
			return casPanel;

		case euclidean:
			if (euclideanPanel == null) {
				euclideanPanel = new OptionseuclideanW((AppW) app,
						((AppW) app).getActiveeuclideanView());
				euclideanPanel.setLabels();
				euclideanPanel.setView(((AppW) app).geteuclideanView1());
			}
			return euclideanPanel;

		case euclidean2:
			if (euclideanPanel2 == null) {
				euclideanPanel2 = new OptionseuclideanW((AppW) app,
						((AppW) app).geteuclideanView2(1));
				euclideanPanel2.setLabels();
				euclideanPanel2.setView(((AppW) app).geteuclideanView2(1));
			}
			return euclideanPanel2;

		case euclidean3D:
			if (euclideanPanel3D == null) {
				euclideanPanel3D = new OptionseuclideanW((AppW) app,
						((AppW) app).geteuclideanView3D());
				euclideanPanel3D.setLabels();
			}
			return euclideanPanel2;

		case SPREADSHEET:
			if (spreadsheetPanel == null) {
				spreadsheetPanel = new OptionsSpreadsheetW((AppW) app);
			}
			return spreadsheetPanel;

		case ALGEBRA:
			if (algebraPanel == null) {
				algebraPanel = new OptionsAlgebraW((AppW) app);
			}
			return algebraPanel;

		case LAYOUT:
			if (layoutPanel == null) {
				layoutPanel = new OptionsLayoutW();
			}
			layoutPanel.getWrappedPanel().setStyleName("layoutPanel");
			return layoutPanel;

		case OBJECTS:
			if (getObjectPanel() == null) {
				setObjectPanel(new OptionsObjectW((AppW) app, false,
						new Runnable() {

							@Override
							public void run() {
								updatePropertiesView();
							}
						}));
			}
			getObjectPanel().selectTab(subType);
			return getObjectPanel();
		}
		return null;
	}

	@Override
	protected OptionsObjectW getObjectPanel() {
		return super.getObjectPanel() != null
				? (OptionsObjectW) super.getObjectPanel() : null;
	}

	private OptionseuclideanW geteuclideanPanel() {
		return euclideanPanel;
	}

	@Override
	public void add(GeoElement geo) {
		// TODO Auto-generated method stub
	}

	@Override
	public void remove(GeoElement geo) {
		// TODO Auto-generated method stub
	}	

	@Override
	public void rename(GeoElement geo) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(GeoElement geo) {
		if (geo.isLabelSet()) {
			updatePropertiesGUI();
		}
	}

	@Override
	public void updateVisualStyle(GeoElement geo, GProperty prop) {
		if (geo.isLabelSet()) {
			updatePropertiesGUI();
		}
	}

	@Override
	public void updateAuxiliaryObject(GeoElement geo) {
		updatePropertiesGUI();
	}

	@Override
	public void repaintView() {
		// nothing on repaint
	}

	@Override
	public void reset() {
		// do nothing
	}

	@Override
	public void clearView() {
		// do nothing
	}

	@Override
	public void setMode(int mode, ModeSetter m) {
		// do nothing
	}

	@Override
	public int getViewID() {
		return 0;
	}

	@Override
	public boolean hasFocus() {
		return false;
	}

	@Override
	public void updateSelection() {
		if (app.getSelectionManager().selectedGeosSize() != 0 && optionType != OptionType.OBJECTS) {
			setOptionPanel(OptionType.OBJECTS);
		} else if (app.getSelectionManager().selectedGeosSize() == 0) {
			if (optionType != OptionType.euclidean
					|| optionType != OptionType.euclidean2
					|| optionType != OptionType.euclidean3D
					|| optionType != OptionType.euclidean_FOR_PLANE) {
				if (app.getActiveeuclideanView().iseuclideanView3D()) {
					setOptionPanel(OptionType.euclidean3D);
				} else if (app.getActiveeuclideanView().isDefault2D()) {
					setOptionPanel(app.getActiveeuclideanView().geteuclideanViewNo() == 1
						? OptionType.euclidean : OptionType.euclidean2);
				} else {
					setOptionPanel(OptionType.euclidean_FOR_PLANE);
				}
			}
		}
		updatePropertiesGUI();
	}

	@Override
	protected void setOptionPanelWithoutCheck(OptionType type) {
		int sType = 0;
		if (type == OptionType.OBJECTS && this.getObjectPanel() != null) {
			MultiRowsTabPanel tabPanel = this.getObjectPanel()
					.getTabPanel();
			sType = tabPanel.getTabBar().getSelectedTab();

		}
		setOptionPanel(type, sType);
	}

	@Override
	protected void setObjectsToolTip() {
		// styleBar.setObjectsToolTip();
	}

	@Override
	protected void setSelectedTab(OptionType type) {
		switch (type) {
		case euclidean:
			euclideanPanel.setSelectedTab(getSelectedTab());
			break;
		case euclidean2:
			euclideanPanel2.setSelectedTab(getSelectedTab());
			break;
		default:
			// do nothing
			break;
		}
	}

	@Override
	protected void updateObjectPanelSelection(ArrayList<GeoElement> geos) {
		if (getObjectPanel() == null) {
			return;
		}
		getObjectPanel().updateSelection(geos);
		updateTitleBar();
		setObjectsToolTip();
	}

	@Override
	public void setOptionPanel(OptionType type, int subType) {
		optionType = type;
		contentsPanel.clear();
		OptionPanelW optionPanel = getOptionPanel(type, subType);
		Widget wPanel = optionPanel.getWrappedPanel();
		contentsPanel.add(wPanel);
		if (wPanel != null) {
			onResize();
		}
		this.styleBar.selectButton(type);
	}

	/**
	 * @return selected option type
	 */
	public OptionType getOptionType() {
		return optionType;
	}

	@Override
	public void mousePressedForPropertiesView() {
		if (getObjectPanel() == null) {
			return;
		}
		getObjectPanel().forgetGeoAdded();
	}

	@Override
	public void updateSelection(ArrayList<GeoElement> geos) {
		if (geos.size() != 0 && optionType != OptionType.OBJECTS) {
			setOptionPanel(OptionType.OBJECTS);
		}
		updatePropertiesGUI();
	}

	private void updatePropertiesGUI() {
		OptionsObjectW panel = getObjectPanel();
		if (panel != null) {
			panel.updateGUI();
			if (optionType == OptionType.OBJECTS) {
				if (!panel.getWrappedPanel().isVisible()) {
					setOptionPanel(OptionType.euclidean);
				}
			}
		}
		if (geteuclideanPanel() != null) {
			geteuclideanPanel().updateGUI();
		}

		if (styleBar != null) {
			styleBar.updateGUI();
		}	
	}

	@Override
	protected void updateTitleBar() {
		updatePropertiesGUI();
	}

	@Override
	public void attachView() {
		if (isAttached()) {
			return;
		}

		clearView();
		kernel.notifyAddAll(this);
		kernel.attach(this);
		app.getKernel().getAnimatonManager().stopAnimation();
		setAttached(true);
	}

	@Override
	public void detachView() {
		kernel.detach(this);
		clearView();
		app.getKernel().getAnimatonManager().startAnimation();
		setAttached(false);
	}

	@Override
	public void updatePropertiesView() {
		updatePropertiesGUI();
	}

	/**
	 * 
	 * @return GWT panel of this view
	 */
	public Widget getWrappedPanel() {
		return wrappedPanel;
	}

	/**
	 * Rebuild GUI for the new font size
	 */
	public void updateFonts() {
		updatePropertiesGUI();
	}

	@Override
	public void onResize() {
		int width = getWrappedPanel().getOffsetWidth() - 40;
		int height = getWrappedPanel().getOffsetHeight();
		if (height > 0 && width > 0) {
			contentsPanel.setWidth(width + "px");
		} else if (app.isUnbundledOrWhiteboard() && width == -40
				&& getWrappedPanel() != null) {
			contentsPanel.setWidth("460px");
		}
	}

	@Override
	public boolean suggestRepaint() {
		return false;
	}

	@Override
    public void setLabels() {
		if (globalPanel != null) {
			globalPanel.setLabels();
		}
		if (euclideanPanel != null) {
			euclideanPanel.setLabels();
		}
		if (euclideanPanel2 != null) {
			euclideanPanel2.setLabels();
		}
		if (euclideanPanel3D != null) {
			euclideanPanel3D.setLabels();
		}
		if (spreadsheetPanel != null) {
			spreadsheetPanel.setLabels();
		}
		if (casPanel != null) {
			casPanel.setLabels();
		}
		if (algebraPanel != null) {
			algebraPanel.setLabels();
		}
    }

	@Override
	public void updateStyleBar() {
		if (styleBar != null) {
			styleBar.updateGUI();
		}
	}

	/**
	 * Opens floating settings.
	 */
	public void open() {
		if (!app.isUnbundledOrWhiteboard()) {
			return;
		}
		if (!isFloatingAttached()) {
			wrappedPanel.setVisible(true);
			wrappedPanel.addStyleName("floatingSettings");
			((AppWFull) app).getAppletFrame().add(wrappedPanel);
			setFloatingAttached(true);
		}
		final Style style = ((AppW) app).getFrameElement().getStyle();
		style.setOverflow(Overflow.HIDDEN);
		((AppWFull) app).centerAndResizeViews();
		wrappedPanel.removeStyleName("animateOut");
		wrappedPanel.addStyleName("animateIn");
		CSSAnimation.runOnAnimation(new Runnable() {
			@Override
			public void run() {
				style.setOverflow(Overflow.VISIBLE);
			}
		}, wrappedPanel.getElement(), "animateIn");
	}

	/**
	 * Closes floating settings.
	 */
	public void close() {
		if (!app.isUnbundledOrWhiteboard()) {
			app.getGuiManager().setShowView(false, App.VIEW_PROPERTIES);
			return;
		}
		wrappedPanel.removeStyleName("animateIn");
		wrappedPanel.addStyleName("animateOut");
		((AppW) app).getFrameElement().getStyle().setOverflow(Overflow.HIDDEN);
		CSSAnimation.runOnAnimation(new Runnable() {
			@Override
			public void run() {
				onFloatingSettingsClose();
			}
		}, wrappedPanel.getElement(),
				"animateOut");
	}

	/**
	 * Callback for animation in floating mode
	 */
	protected void onFloatingSettingsClose() {
		app.getGuiManager().setShowView(false, App.VIEW_PROPERTIES);
		((AppWFull) app).getAppletFrame().remove(wrappedPanel);
		setFloatingAttached(false);
		((AppW) app).getFrameElement().getStyle()
				.setOverflow(Overflow.VISIBLE);
	}

	/**
	 * @return true is settings panel is floating
	 */
	public boolean isFloatingAttached() {
		return floatingAttached;
	}

	/**
	 * @param floatingAttached
	 *            true if settings panel is floating
	 */
	public void setFloatingAttached(boolean floatingAttached) {
		this.floatingAttached = floatingAttached;
	}

	/**
	 * @param width
	 *            width
	 * @param height
	 *            heigth
	 */
	public void resize(double width, double height) {
		wrappedPanel.setPixelSize((int) Math.min(width, 500), (int) height);
		onResize();
	}
}
