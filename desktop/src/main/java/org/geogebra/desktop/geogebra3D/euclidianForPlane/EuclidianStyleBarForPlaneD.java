package org.geogebra.desktop.geogebra3D.euclideanForPlane;

import java.util.ArrayList;
import java.util.List;

import org.geogebra.common.euclidean.euclideanConstants;
import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.geogebra3D.euclideanForPlane.euclideanViewForPlaneCompanion;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.desktop.euclidean.euclideanStyleBarD;
import org.geogebra.desktop.gui.util.MyToggleButtonD;
import org.geogebra.desktop.util.GuiResourcesD;

/**
 * StyleBar for view for plane
 * 
 * @author Mathieu
 *
 */
public class euclideanStyleBarForPlaneD extends euclideanStyleBarD {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MyToggleButtonD btnCenterAndOrientation;

	/**
	 * Common constructor.
	 * 
	 * @param ev
	 *            view
	 */
	public euclideanStyleBarForPlaneD(euclideanViewForPlaneD ev) {
		super(ev);
	}

	@Override
	protected void addGraphicsDecorationsButtons() {
		add(btnShowAxes);
		add(btnShowGrid);
	}

	@Override
	protected void addBtnRotateView() {

		add(btnCenterAndOrientation);

	}

	@Override
	protected boolean isVisibleInThisView(GeoElement geo) {
		return geo.isVisibleInViewForPlane();
	}

	@Override
	protected void processSource(Object source,
			ArrayList<GeoElement> targetGeos) {

		if (source.equals(btnCenterAndOrientation)) {
			euclideanViewForPlaneCompanion companion = (euclideanViewForPlaneCompanion) ((euclideanView) ev)
					.getCompanion();
			companion.updateCenterAndOrientationRegardingView();
			companion.updateScaleRegardingView();
		} else {
			super.processSource(source, targetGeos);
		}
	}

	@Override
	protected void createButtons() {

		super.createButtons();

		// ========================================
		// button
		btnCenterAndOrientation = new MyToggleButtonD(
				app.getScaledIcon(GuiResourcesD.STANDARD_VIEW), iconHeight) {

			private static final long serialVersionUID = 1L;

			@Override
			public void update(List<GeoElement> geos) {
				// always show this button unless in pen mode
				this.setVisible(mode != euclideanConstants.MODE_PEN);
			}
		};
		btnCenterAndOrientation.addActionListener(this);

	}

	@Override
	public void setLabels() {
		super.setLabels();
		btnCenterAndOrientation
				.setToolTipText(loc.getPlainTooltip("stylebar.ViewDefault"));

	}

	@Override
	public void updateGUI() {
		super.updateGUI();

		btnCenterAndOrientation.removeActionListener(this);
		btnCenterAndOrientation.setSelected(false);
		btnCenterAndOrientation.addActionListener(this);

	}

}
