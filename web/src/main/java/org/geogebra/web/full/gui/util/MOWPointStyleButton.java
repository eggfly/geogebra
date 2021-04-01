package org.geogebra.web.full.gui.util;

import java.util.HashMap;

import org.geogebra.common.awt.GColor;
import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.euclidean.draw.DrawPoint;
import org.geogebra.common.gui.dialog.options.model.PointStyleModel;
import org.geogebra.common.gui.util.SelectionTable;
import org.geogebra.common.kernel.geos.GeoPoint;
import org.geogebra.web.html5.awt.GGraphics2DW;
import org.geogebra.web.html5.euclidean.GGraphics2DWI;
import org.geogebra.web.html5.gui.util.ImageOrText;
import org.geogebra.web.html5.main.AppW;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Button for point style with preview
 * 
 * @author Laszlo
 */
public class MOWPointStyleButton extends PointStylePopup {
	/** Size of the value canvas */
	private static final int CANVAS_SIZE = 32;

	/** The value canvas next to the slider */
	protected Canvas canvas;

	private GGraphics2DWI g2;
	private DrawPoint drawPoint;
	private GeoPoint p;

	/**
	 * Constructor
	 * 
	 * @param app
	 *            GGB app.
	 * @param data
	 *            PointStyle icons.
	 */
	public MOWPointStyleButton(AppW app, ImageOrText[] data) {
		super(app, data, 2, SelectionTable.MODE_ICON, true, true,
				new PointStyleModel(app));

		// Rearranging content.
		VerticalPanel panel = ((ButtonPopupMenu) getMyPopup()).getPanel();
		panel.clear();
		panel.add(getMyTable());
		panel.add(sliderPanel);

		canvas = Canvas.createIfSupported();

		canvas.setCoordinateSpaceHeight(CANVAS_SIZE);
		canvas.setCoordinateSpaceWidth(CANVAS_SIZE);
		canvas.addStyleName("preview");

		addSliderTitle();
		panel.addStyleName("mowPopup");

		sliderPanel.add(canvas);
		g2 = new GGraphics2DW(canvas);
		p = new GeoPoint(app.getKernel().getConstruction(),
				0, 0, 0);
		drawPoint = new DrawPoint(app.getActiveeuclideanView(), p);
		p.seteuclideanVisible(true);
	}

	/**
	 * 
	 * @param app
	 *            GGB app.
	 * @return Point style button for MOW
	 */
	public static MOWPointStyleButton create(AppW app) {

		pointStyleMap = new HashMap<>();
		for (int i = 0; i < euclideanView.getPointStyleLength(); i++) {
			pointStyleMap.put(euclideanView.getPointStyle(i), i);
		}

		ImageOrText[] pointStyleIcons = new ImageOrText[euclideanView
				.getPointStyleLength()];
		for (int i = 0; i < euclideanView.getPointStyleLength(); i++) {
			pointStyleIcons[i] = GeoGebraIconW
					.createPointStyleIcon(euclideanView.getPointStyle(i));
		}

		MOWPointStyleButton btn = new MOWPointStyleButton(app, pointStyleIcons);
		btn.setKeepVisible(true);
		return btn;
	}

	@Override
	public void handlePopupActionEvent() {
		super.handlePopupActionEvent();
		updateCanvas();
	}

	@Override
	protected void onClickAction() {
		super.onClickAction();
		updateCanvas();
	}

	@Override
	public void onSliderInput() {
		super.onSliderInput();
		updateCanvas();
	}

	private void addSliderTitle() {
		titleLabel = new Label();
		titleLabel.addStyleName("pointSizeLabel");
		sliderPanel.insert(titleLabel, 0);
		getMySlider().setWidth("140px");
		setLabels();
	}

	/**
	 * No text (but canvas) for slider so leave this empty.
	 */
	@Override
	protected void setSliderText(String text) {
		// intentionally blank
	}

	private void updateCanvas() {

		canvas.getContext2d().clearRect(0, 0, CANVAS_SIZE, CANVAS_SIZE);
		updateGeo();
		drawPoint.update();
		drawPoint.updateStylePreview(CANVAS_SIZE / 2d, CANVAS_SIZE / 2d);
		drawPoint.draw(g2);

	}

	private void updateGeo() {
		p.setPointSize(getSliderValue());
		p.setObjColor(GColor.BLACK);
		p.setPointStyle(getMyTable().getSelectedIndex());
	}

	@Override
	public void setLabels() {
		titleLabel.setText(app.getLocalization().getMenu("PointSize"));
	}
}
