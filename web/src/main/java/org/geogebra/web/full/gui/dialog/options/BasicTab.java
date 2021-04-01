package org.geogebra.web.full.gui.dialog.options;

import java.util.Collection;

import org.geogebra.common.awt.GColor;
import org.geogebra.common.euclidean.event.KeyEvent;
import org.geogebra.common.euclidean.event.KeyHandler;
import org.geogebra.common.gui.dialog.handler.ColorChangeHandler;
import org.geogebra.common.gui.dialog.options.model.euclideanOptionsModel;
import org.geogebra.common.gui.dialog.options.model.euclideanOptionsModel.MinMaxType;
import org.geogebra.common.gui.view.consprotocol.ConstructionProtocolNavigation;
import org.geogebra.common.kernel.StringTemplate;
import org.geogebra.common.plugin.euclideanStyleConstants;
import org.geogebra.web.full.css.MaterialDesignResources;
import org.geogebra.web.full.gui.util.GeoGebraIconW;
import org.geogebra.web.full.gui.util.MyCJButton;
import org.geogebra.web.full.gui.util.PopupMenuButtonW;
import org.geogebra.web.html5.gui.inputfield.AutoCompleteTextFieldW;
import org.geogebra.web.html5.gui.util.FormLabel;
import org.geogebra.web.html5.gui.util.GToggleButton;
import org.geogebra.web.html5.gui.util.ImageOrText;
import org.geogebra.web.html5.gui.util.LayoutUtilW;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.impl.ImageResourcePrototype;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

public class BasicTab extends OptionseuclideanW.euclideanTab {

	/**
	 * 
	 */
	private final OptionseuclideanW optionseuclideanW;
	private Label dimTitle;
	private FormLabel[] dimLabel;
	private AutoCompleteTextFieldW[] dimField;
	AutoCompleteTextFieldW tfAxesRatioX;
	AutoCompleteTextFieldW tfAxesRatioY;
	private Label axesRatioLabel;
	private FlowPanel dimPanel;
	GToggleButton tbLockRatio;
	private Image imgLock;
	private Image imgUnlock;

	protected CheckBox cbShowAxes;
	CheckBox cbBoldAxes;
	private Label colorLabel;
	private MyCJButton btAxesColor;
	private Label lineStyle;
	protected FlowPanel axesOptionsPanel;
	private Label axesOptionsTitle;
	private PopupMenuButtonW axesStylePopup;
	protected Label backgroundColorLabel;
	protected MyCJButton btBackgroundColor;
	CheckBox cbShowMouseCoords;
	private FormLabel tooltips;
	private FormLabel rightAngleStyleLabel;
	protected Label miscTitle;
	private Label consProtocolTitle;
	private FlowPanel consProtocolPanel;
	CheckBox cbShowNavbar;
	CheckBox cbNavPlay;
	CheckBox cbOpenConsProtocol;
	protected Label lblAxisLabelStyle;
	protected CheckBox cbAxisLabelSerif;
	protected CheckBox cbAxisLabelBold;
	protected CheckBox cbAxisLabelItalic;
	protected FlowPanel miscPanel;
	private euclideanOptionsModel model;
	ListBox rightAngleStyleListBox;
	ListBox lbTooltips;

	/**
	 * @param optionseuclideanW
	 *            euclidean options panel
	 */
	public BasicTab(OptionseuclideanW optionseuclideanW) {
		super();
		this.optionseuclideanW = optionseuclideanW;
		this.model = optionseuclideanW.model;
		addDimensionPanel();
		addAxesOptionsPanel();
		addConsProtocolPanel();
		addMiscPanel();
	}

	private void addMinMaxHandler(final AutoCompleteTextFieldW tf,
			final MinMaxType type) {

		tf.addKeyHandler(new KeyHandler() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.isEnterKey()) {
					model.applyMinMax(tf.getText(), type);
					BasicTab.this.optionseuclideanW.updateView();
				}
			}
		});

		tf.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				model.applyMinMax(tf.getText(), type);
				BasicTab.this.optionseuclideanW.updateView();
			}
		});

	}

	protected double parseDouble(String text) {
		if (text == null || "".equals(text)) {
			return Double.NaN;
		}
		return this.optionseuclideanW.app.getKernel().getAlgebraProcessor()
				.evaluateToDouble(text);
	}

	void applyAxesRatio() {
		this.optionseuclideanW.model.applyAxesRatio(
				parseDouble(tfAxesRatioX.getText()),
				parseDouble(tfAxesRatioY.getText()));
		this.optionseuclideanW.updateView();
	}

	private void addAxesRatioHandler(final AutoCompleteTextFieldW tf) {

		tf.addKeyHandler(new KeyHandler() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.isEnterKey()) {
					applyAxesRatio();
				}
			}
		});

		tf.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				applyAxesRatio();
			}
		});
	}

	private void addDimensionPanel() {
		dimTitle = new Label("");
		dimTitle.setStyleName("panelTitle");
		int dimension = this.optionseuclideanW.view.getDimension();
		dimLabel = new FormLabel[dimension * 2]; // "Xmin", "Xmax" etc.
		dimField = new AutoCompleteTextFieldW[dimension * 2];

		tfAxesRatioX = this.optionseuclideanW.getTextField();
		tfAxesRatioY = this.optionseuclideanW.getTextField();

		enableAxesRatio(this.optionseuclideanW.view.isZoomable()
				&& !this.optionseuclideanW.view.isLockedAxesRatio());

		imgLock = new Image(new ImageResourcePrototype(null,
				MaterialDesignResources.INSTANCE.lock_black()
						.getSafeUri(),
				0, 0, 18, 18, false, false));
		imgUnlock = new Image(
				new ImageResourcePrototype(null,
						MaterialDesignResources.INSTANCE
								.lock_open_black().getSafeUri(),
						0, 0, 18, 18, false, false));

		tbLockRatio = new GToggleButton(imgLock);
		tbLockRatio.setValue(this.optionseuclideanW.view.isLockedAxesRatio());
		tbLockRatio.setEnabled(this.optionseuclideanW.view.isZoomable());

		axesRatioLabel = new Label("");

		dimPanel = new FlowPanel();
		add(dimTitle);
		FlowPanel[] axisRangePanel = new FlowPanel[dimension * 2];
		MinMaxType[] fields;
		if (dimension == 2) {
			fields = new MinMaxType[]{MinMaxType.minX,
					MinMaxType.maxX, MinMaxType.minY, MinMaxType.maxY};
		} else {
			fields = new MinMaxType[]{MinMaxType.minX,
					MinMaxType.maxX, MinMaxType.minY, MinMaxType.maxY, MinMaxType.minZ,
					MinMaxType.maxZ};
		}
		for (int i = 0; i < fields.length; i++) {
			dimLabel[i] = new FormLabel();
			dimField[i] = this.optionseuclideanW.getTextField();
			axisRangePanel[i] = new FlowPanel();
			axisRangePanel[i].setStyleName("panelRowCell");
			axisRangePanel[i].add(dimLabel[i].setFor(dimField[i]));
			axisRangePanel[i].add(dimField[i]);
			addMinMaxHandler(dimField[i], fields[i]);
		}

		dimPanel.add(
				LayoutUtilW.panelRow(axisRangePanel[0], axisRangePanel[1]));
		dimPanel.add(
				LayoutUtilW.panelRow(axisRangePanel[2], axisRangePanel[3]));
		if (dimension == 3) {
			dimPanel.add(
					LayoutUtilW.panelRow(axisRangePanel[4], axisRangePanel[5]));
		}

		if (dimension == 2) {
			dimPanel.add(LayoutUtilW.panelRow(axesRatioLabel));
			dimPanel.add(LayoutUtilW.panelRow(tfAxesRatioX, new Label(" : "),
					tfAxesRatioY, tbLockRatio));
		}

		indent(dimPanel);
		addAxesRatioHandler(tfAxesRatioX);
		addAxesRatioHandler(tfAxesRatioY);

		tbLockRatio.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (tbLockRatio.getValue()) {
					model.applyLockRatio(parseDouble(tfAxesRatioX.getText())
							/ parseDouble(tfAxesRatioY.getText()));
				} else {
					model.applyLockRatio(-1);
				}
			}
		});
	}

	protected int setDimension() {
		return 4;
	}

	protected void indent(FlowPanel panel) {
		FlowPanel indent = new FlowPanel();
		indent.setStyleName("panelIndent");
		indent.add(panel);
		add(indent);
	}

	protected void addAxesOptionsPanel() {

		axesOptionsTitle = new Label();
		axesOptionsTitle.setStyleName("panelTitle");
		// show axes checkbox
		cbShowAxes = new CheckBox(
				this.optionseuclideanW.loc.getMenu("ShowAxes"));

		// show bold checkbox
		cbBoldAxes = new CheckBox(this.optionseuclideanW.loc.getMenu("Bold"));

		// axes color
		colorLabel = new Label(
				this.optionseuclideanW.loc.getMenu("Color") + ":");

		lblAxisLabelStyle = new Label(
				this.optionseuclideanW.loc.getMenu("LabelStyle") + ":");
		// show axis label bold checkbox
		cbAxisLabelBold = new CheckBox(
				this.optionseuclideanW.loc.getMenu("Bold"));

		cbAxisLabelSerif = new CheckBox(
				this.optionseuclideanW.loc.getMenu("Serif"));

		// show axis label italic checkbox
		cbAxisLabelItalic = new CheckBox(
				this.optionseuclideanW.loc.getMenu("Italic"));

		btAxesColor = new MyCJButton();

		btAxesColor.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				BasicTab.this.optionseuclideanW.getDialogManager()
						.showColorChooserDialog(model.getAxesColor(),
								new ColorChangeHandler() {

									@Override
									public void onForegroundSelected() {
										// TODO Auto-generated method stub

									}

									@Override
									public void onColorChange(GColor color) {
										model.applyAxesColor(color);
										updateAxesColorButton(color);
									}

									@Override
									public void onClearBackground() {
										// TODO Auto-generated method stub

									}

									@Override
									public void onBackgroundSelected() {
										// TODO Auto-generated method stub

									}

									@Override
									public void onAlphaChange() {
										// TODO Auto-generated method stub

									}

									@Override
									public void onBarSelected() {
										// TODO Auto-generated method stub

									}
								});
			}
		});

		// axes style
		lineStyle = new Label(
				this.optionseuclideanW.loc.getMenu("LineStyle") + ":");
		final ImageOrText[] iconArray = new ImageOrText[euclideanOptionsModel
				.getAxesStyleLength()];
		for (int i = 0; i < iconArray.length; i++) {
			iconArray[i] = GeoGebraIconW.createAxesStyleIcon(
					euclideanStyleConstants.getLineStyleOptions(i));
		}

		axesStylePopup = new PopupMenuButtonW(this.optionseuclideanW.app,
				iconArray, -1, 1,
				org.geogebra.common.gui.util.SelectionTable.MODE_ICON) {
			@Override
			public void handlePopupActionEvent() {
				int idx = getSelectedIndex();

				model.applyAxesStyle(euclideanStyleConstants
						.getLineStyleOptions(idx)
						// make sure bold checkbox doesn't change
						+ (cbBoldAxes.getValue()
								? euclideanStyleConstants.AXES_BOLD : 0));
				BasicTab.this.optionseuclideanW.updateView();
				super.handlePopupActionEvent();

			}
		};
		axesStylePopup.setKeepVisible(false);

		// axes options panel
		axesOptionsPanel = new FlowPanel();
		add(axesOptionsTitle);
		fillAxesOptionsPanel();
		cbShowAxes.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				setShowAxes(cbShowAxes.getValue());
				BasicTab.this.optionseuclideanW.updateView();
				BasicTab.this.optionseuclideanW.app.storeUndoInfo();
			}
		});

		cbBoldAxes.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				model.applyBoldAxes(cbBoldAxes.getValue(),
						cbShowAxes.getValue());
				BasicTab.this.optionseuclideanW.updateView();
			}
		});

		cbAxisLabelSerif.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				model.setAxesLabelsSerif(cbAxisLabelSerif.getValue());
			}
		});

		cbAxisLabelBold.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				model.setAxisFontBold(cbAxisLabelBold.getValue());
			}
		});

		cbAxisLabelItalic.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				model.setAxisFontItalic(cbAxisLabelItalic.getValue());
			}
		});

		indent(axesOptionsPanel);
	}

	protected void setShowAxes(Boolean value) {
		this.optionseuclideanW.model.showAxes(value);
		this.optionseuclideanW.xAxisTab.setShowAxis(value);
		this.optionseuclideanW.yAxisTab.setShowAxis(value);
	}

	protected void fillAxesOptionsPanel() {
		axesOptionsPanel.add(LayoutUtilW.panelRow(cbShowAxes, cbBoldAxes));
		axesOptionsPanel.add(LayoutUtilW.panelRow(colorLabel, btAxesColor,
				lineStyle, axesStylePopup));
		axesOptionsPanel.add(LayoutUtilW.panelRow(lblAxisLabelStyle,
				cbAxisLabelSerif, cbAxisLabelBold, cbAxisLabelItalic));
	}

	void togglePlayButton() {

		Collection<ConstructionProtocolNavigation> cpns = this.optionseuclideanW.app
				.getGuiManager().getAllCPNavigations();
		for (ConstructionProtocolNavigation cpn : cpns) {
			cpn.setPlayButtonVisible(!cpn.isPlayButtonVisible());
		}
		this.optionseuclideanW.app.setUnsaved();
		this.optionseuclideanW.updateGUI();
	}

	void toggleConsProtButton() {
		Collection<ConstructionProtocolNavigation> cpns = this.optionseuclideanW.app
				.getGuiManager().getAllCPNavigations();
		for (ConstructionProtocolNavigation cpn : cpns) {
			cpn.setConsProtButtonVisible(!cpn.isConsProtButtonVisible());
		}
		this.optionseuclideanW.app.setUnsaved();
		this.optionseuclideanW.updateGUI();
	}

	private void addConsProtocolPanel() {
		consProtocolTitle = new Label();
		consProtocolTitle.setStyleName("panelTitle");
		consProtocolPanel = new FlowPanel();

		cbShowNavbar = new CheckBox();

		consProtocolPanel.add(cbShowNavbar);

		cbNavPlay = new CheckBox();

		cbOpenConsProtocol = new CheckBox();

		cbShowNavbar.setStyleName("checkBoxPanel");

		FlowPanel buttons = new FlowPanel();
		buttons.setStyleName("panelIndent");
		cbNavPlay.setStyleName("checkBoxPanel");
		cbOpenConsProtocol.setStyleName("checkBoxPanel");
		buttons.add(cbNavPlay);
		buttons.add(cbOpenConsProtocol);
		consProtocolPanel.add(buttons);

		add(consProtocolTitle);
		indent(consProtocolPanel);

		cbShowNavbar.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				BasicTab.this.optionseuclideanW.app
						.toggleShowConstructionProtocolNavigation(
								BasicTab.this.optionseuclideanW.view
										.getViewID());
				cbNavPlay.setEnabled(cbShowNavbar.getValue());
				cbOpenConsProtocol.setEnabled(cbShowNavbar.getValue());
			}
		});

		cbNavPlay.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				togglePlayButton();
			}
		});

		cbOpenConsProtocol.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				toggleConsProtButton();
			}
		});
	}

	protected void applyBackgroundColor(GColor color) {
		int viewIdx = 0;
		if (this.optionseuclideanW.view == this.optionseuclideanW.app
				.geteuclideanView1()) {
			viewIdx = 1;
		} else if (this.optionseuclideanW.app
				.haseuclideanView2EitherShowingOrNot(1)
				&& this.optionseuclideanW.view == this.optionseuclideanW.app
						.geteuclideanView2(1)) {
			viewIdx = 2;
		} else if (this.optionseuclideanW.app
				.iseuclideanView3D(this.optionseuclideanW.view)) {
			viewIdx = 3;
		}
		this.optionseuclideanW.model.applyBackgroundColor(viewIdx, color);
	}

	protected void addMiscPanel() {
		miscTitle = new Label();
		miscTitle.setStyleName("panelTitle");
		// background color panel
		backgroundColorLabel = new Label(
				this.optionseuclideanW.loc.getMenu("BackgroundColor") + ":");

		btBackgroundColor = new MyCJButton();

		// show mouse coords
		cbShowMouseCoords = new CheckBox();

		// show tooltips
		this.lbTooltips = new ListBox();
		fillTooltipCombo();
		tooltips = new FormLabel(
				this.optionseuclideanW.loc.getMenu("Tooltips") + ":")
						.setFor(lbTooltips);

		this.rightAngleStyleListBox = new ListBox();
		updateRightAngleCombo();
		rightAngleStyleListBox
				.setSelectedIndex(optionseuclideanW.view.getRightAngleStyle());
		rightAngleStyleLabel = new FormLabel(
				this.optionseuclideanW.loc.getMenu("RightAngleStyle") + ":")
						.setFor(rightAngleStyleListBox);
		miscPanel = new FlowPanel();
		add(miscTitle);

		fillMiscPanel();

		indent(miscPanel);

		btBackgroundColor.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				BasicTab.this.optionseuclideanW.getDialogManager()
						.showColorChooserDialog(model.getBackgroundColor(),
								new ColorChangeHandler() {

									@Override
									public void onForegroundSelected() {
										// TODO Auto-generated method stub
									}

									@Override
									public void onColorChange(GColor color) {
										applyBackgroundColor(color);
										updateBackgroundColorButton(color);
									}

									@Override
									public void onClearBackground() {
										// TODO Auto-generated method stub
									}

									@Override
									public void onBackgroundSelected() {
										// TODO Auto-generated method stub
									}

									@Override
									public void onAlphaChange() {
										// TODO Auto-generated method stub
									}

									@Override
									public void onBarSelected() {
										// TODO Auto-generated method stub
									}
								});
				// model.applyBackgroundColor();
			}
		});

		cbShowMouseCoords.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				model.applyMouseCoords(cbShowMouseCoords.getValue());
			}
		});

		this.lbTooltips.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				model.applyTooltipMode(
						BasicTab.this.lbTooltips.getSelectedIndex());
			}
		});

		this.rightAngleStyleListBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				model.applyRightAngleStyle(BasicTab.this.rightAngleStyleListBox
						.getSelectedIndex());
			}
		});
	}

	private void updateRightAngleCombo() {
		this.rightAngleStyleListBox.clear();
		for (String s : this.model.fillRightAngleCombo()) {
			this.rightAngleStyleListBox.addItem(s);
		}
	}

	protected void fillMiscPanel() {
		miscPanel.add(
				LayoutUtilW.panelRow(backgroundColorLabel, btBackgroundColor));
		miscPanel.add(LayoutUtilW.panelRow(tooltips, this.lbTooltips));
		miscPanel.add(LayoutUtilW.panelRow(cbShowMouseCoords));
		miscPanel.add(LayoutUtilW.panelRow(rightAngleStyleLabel,
				this.rightAngleStyleListBox));

	}

	@Override
	public void setLabels() {
		dimTitle.setText(this.optionseuclideanW.loc.getMenu("Dimensions"));

		dimLabel[0].setText(this.optionseuclideanW.loc.getMenu("xmin") + ":");
		dimLabel[1].setText(this.optionseuclideanW.loc.getMenu("xmax") + ":");
		dimLabel[2].setText(this.optionseuclideanW.loc.getMenu("ymin") + ":");
		dimLabel[3].setText(this.optionseuclideanW.loc.getMenu("ymax") + ":");
		axesRatioLabel.setText(this.optionseuclideanW.loc.getMenu("xAxis")
				+ " : " + this.optionseuclideanW.loc.getMenu("yAxis"));

		axesOptionsTitle.setText(this.optionseuclideanW.loc.getMenu("Axes"));
		cbShowAxes.setText(this.optionseuclideanW.loc.getMenu("ShowAxes"));
		cbBoldAxes.setText(this.optionseuclideanW.loc.getMenu("Bold"));
		colorLabel.setText(this.optionseuclideanW.loc.getMenu("Color") + ":");
		lineStyle
				.setText(this.optionseuclideanW.loc.getMenu("LineStyle") + ":");

		miscTitle.setText(this.optionseuclideanW.loc.getMenu("Miscellaneous"));
		backgroundColorLabel.setText(
				this.optionseuclideanW.loc.getMenu("BackgroundColor") + ":");
		int index = this.lbTooltips.getSelectedIndex();

		fillTooltipCombo();
		this.lbTooltips.setSelectedIndex(index);
		cbShowMouseCoords.setText(
				this.optionseuclideanW.loc.getMenu("ShowMouseCoordinates"));

		index = this.rightAngleStyleListBox.getSelectedIndex();

		updateRightAngleCombo();
		this.rightAngleStyleListBox.setSelectedIndex(index);

		consProtocolTitle.setText(this.optionseuclideanW.loc
				.getMenu("ConstructionProtocolNavigation"));

		cbShowNavbar.setText(this.optionseuclideanW.loc.getMenu("Show"));
		cbNavPlay.setText(this.optionseuclideanW.loc.getMenu("PlayButton"));
		cbOpenConsProtocol.setText(this.optionseuclideanW.loc
				.getMenu("ConstructionProtocolButton"));

		lblAxisLabelStyle.setText(
				this.optionseuclideanW.loc.getMenu("LabelStyle") + ":");
		cbAxisLabelSerif.setText(this.optionseuclideanW.loc.getMenu("Serif"));
		cbAxisLabelBold.setText(this.optionseuclideanW.loc.getMenu("Bold"));
		cbAxisLabelItalic.setText(this.optionseuclideanW.loc.getMenu("Italic"));
		tooltips.setText(this.optionseuclideanW.loc.getMenu("Tooltips") + ":");

		rightAngleStyleLabel.setText(
				this.optionseuclideanW.loc.getMenu("RightAngleStyle") + ":");
	}

	private void fillTooltipCombo() {
		this.lbTooltips.clear();
		for (String item : model.fillTooltipCombo()) {
			this.lbTooltips.addItem(item);
		}
	}

	/**
	 * @param value
	 *            axes ratio
	 */
	public void enableAxesRatio(boolean value) {
		tfAxesRatioX.getTextBox().setEnabled(value);
		tfAxesRatioY.getTextBox().setEnabled(value);
		// tbLockRatio.getDownFace().setImage(value ? imgUnlock : imgLock);
		if (tbLockRatio != null) {
			tbLockRatio.getUpFace().setImage(value ? imgUnlock : imgLock);
		}
	}

	/**
	 * @param minX
	 *            min x
	 * @param maxX
	 *            max x
	 * @param minY
	 *            min y
	 * @param maxY
	 *            max y
	 */
	public void setMinMaxText(String minX, String maxX, String minY,
			String maxY) {
		dimField[0].setText(minX);
		dimField[1].setText(maxX);
		dimField[2].setText(minY);
		dimField[3].setText(maxY);
	}

	/**
	 * @param minX
	 *            min x
	 * @param maxX
	 *            max x
	 * @param minY
	 *            min y
	 * @param maxY
	 *            max y
	 * @param minZ
	 *            min z
	 * @param maxZ
	 *            max z
	 */
	public void setMinMaxText(String minX, String maxX, String minY,
			String maxY, String minZ, String maxZ) {
		dimField[0].setText(minX);
		dimField[1].setText(maxX);
		dimField[2].setText(minY);
		dimField[3].setText(maxY);
		dimField[4].setText(minZ);
		dimField[5].setText(maxZ);
	}

	/**
	 * Updates color, visible and bold checkboxes using current view settings.
	 * 
	 * @param color
	 *            axes color override for axis color
	 */
	public final void updateAxes(GColor color) {
		// btAxesColor.setForeground(new GColorW(view.getAxesColor()));
		cbShowAxes.setValue(this.optionseuclideanW.view.getShowXaxis()
				&& this.optionseuclideanW.view.getShowYaxis());
		cbBoldAxes.setValue(this.optionseuclideanW.view.areAxesBold());
		updateAxesColorButton(color);
	}

	/**
	 * @param color
	 *            axes color
	 */
	public void updateAxesColorButton(GColor color) {
		ImageOrText content = new ImageOrText();
		content.setBgColor(color);
		btAxesColor.setIcon(content);
	}

	/**
	 * @param color
	 *            background color
	 */
	public void updateBackgroundColorButton(GColor color) {
		ImageOrText content = new ImageOrText();
		content.setBgColor(color);
		btBackgroundColor.setIcon(content);
	}

	/**
	 * Update construction protocol navigation settings.
	 * 
	 * @param isVisible
	 *            whether the checkboxes should be enabled
	 */
	public void updateConsProtocolPanel(boolean isVisible) {
		// cons protocol panel
		cbShowNavbar.setValue(isVisible);
		ConstructionProtocolNavigation cpn = this.optionseuclideanW.app
				.getGuiManager().getCPNavigationIfExists();
		cbNavPlay.setValue(cpn == null || cpn.isPlayButtonVisible());
		cbOpenConsProtocol
				.setValue(cpn == null || cpn.isConsProtButtonVisible());

		cbNavPlay.setEnabled(isVisible);
		cbOpenConsProtocol.setEnabled(isVisible);
	}

	public void showMouseCoords(boolean value) {
		cbShowMouseCoords.setValue(value);
	}

	public void selectAxesStyle(int index) {
		axesStylePopup.setSelectedIndex(index);
	}

	public void enabeLock(boolean value) {
		tbLockRatio.setEnabled(value);
	}

	protected void updateMinMax() {
		this.optionseuclideanW.view.updateBoundObjects();

			setMinMaxText(
					this.optionseuclideanW.view.getXminObject()
							.getLabel(StringTemplate.editTemplate),
					this.optionseuclideanW.view.getXmaxObject()
							.getLabel(StringTemplate.editTemplate),
					this.optionseuclideanW.view.getYminObject()
							.getLabel(StringTemplate.editTemplate),
					this.optionseuclideanW.view.getYmaxObject()
							.getLabel(StringTemplate.editTemplate));
	}

	/**
	 * Update xmin, xmax, ymin, ymax and scale inputs
	 */
	public void updateBounds() {

		updateMinMax();

		double xscale = this.optionseuclideanW.view.getXscale();
		double yscale = this.optionseuclideanW.view.getYscale();
		if (xscale >= yscale) {
			tfAxesRatioX.setText("1");
			tfAxesRatioY.setText(this.optionseuclideanW.app.getKernel()
					.format(xscale / yscale, StringTemplate.editTemplate));
		} else {
			tfAxesRatioX.setText(this.optionseuclideanW.app.getKernel()
					.format(yscale / xscale, StringTemplate.editTemplate));
			tfAxesRatioY.setText("1");
		}

	}

	public FormLabel[] getDimLabel() {
		return dimLabel;
	}

	public OptionseuclideanW getOptionseuclideanW() {
		return optionseuclideanW;
	}
}