package org.geogebra.common.gui.view.data;

import org.geogebra.common.euclidean.euclideanViewInterfaceCommon;
import org.geogebra.common.kernel.Construction;
import org.geogebra.common.kernel.geos.GeoNumeric;
import org.geogebra.common.plugin.euclideanStyleConstants;

/**
 * @author gabor Common things used by plotPaneleuclideanViewsW/D
 *
 */
public class PlotPaneleuclideanViewCommon {
	/**
	 * Plot panel viewID. This is not a constant; it is assigned by GuiManager.
	 */
	private int viewID;
	/**
	 * Flag to determine if the mouse is over the drag region, a thin rectangle
	 * at the top of the panel
	 */
	private boolean overDragRegion;
	/** Settings to control euclideanView features (e.g. axes visibility) */
	private PlotSettings plotSettings;
	public static final boolean SHOW_GRID = false;
	public static final boolean[] SHOW_AXES = { true, true };

	/**
	 * @param overDragRegion
	 * 
	 *            Constructor
	 */
	public PlotPaneleuclideanViewCommon(boolean overDragRegion) {
		this.overDragRegion = overDragRegion;
	}

	/**
	 * @return view ID
	 */
	public int getViewID() {
		return viewID;
	}

	public void setViewID(int viewID) {
		this.viewID = viewID;
	}

	public boolean isOverDragRegion() {
		return overDragRegion;
	}

	public void setOverDragRegion(boolean overDragRegion) {
		this.overDragRegion = overDragRegion;
	}

	public PlotSettings getPlotSettings() {
		return plotSettings;
	}

	public void setPlotSettings(PlotSettings plotSettings) {
		this.plotSettings = plotSettings;
	}

	/**
	 * Sets the plotSettings field and updates the panel accordingly.
	 * 
	 * @param plotPaneleuclideanView
	 *            TODO
	 * @param settings
	 *            settings
	 */
	public void updateSettings(
			PlotPaneleuclideanViewInterface plotPaneleuclideanView,
			PlotSettings settings) {
		setPlotSettings(settings);
		plotPaneleuclideanView.setEVParams();
	}

	/**
	 * Uses the values stored in the plotSettings field to update the features
	 * of this euclideanView (e.g. axes visibility)
	 * 
	 * @param plotPaneleuclideanViewD
	 *            TODO
	 */
	public void setEVParams(
			PlotPaneleuclideanViewInterface plotPaneleuclideanViewD) {
		plotPaneleuclideanViewD.showGrid(getPlotSettings().showGrid);
		plotPaneleuclideanViewD.setShowAxis(euclideanViewInterfaceCommon.AXIS_Y,
				getPlotSettings().showYAxis, false);

		plotPaneleuclideanViewD.setShowAxis(euclideanViewInterfaceCommon.AXIS_X,
				getPlotSettings().showXAxis, false);

		plotPaneleuclideanViewD.setLogAxis(euclideanViewInterfaceCommon.AXIS_X,
				getPlotSettings().logXAxis, false);
		plotPaneleuclideanViewD.setLogAxis(euclideanViewInterfaceCommon.AXIS_Y,
				getPlotSettings().logYAxis, false);

		plotPaneleuclideanViewD
				.setAutomaticGridDistance(getPlotSettings().gridIntervalAuto);
		if (!getPlotSettings().gridIntervalAuto) {
			plotPaneleuclideanViewD
					.setGridDistances(getPlotSettings().gridInterval);
		}

		if (getPlotSettings().showArrows) {
			plotPaneleuclideanViewD.setAxesLineStyle(
					euclideanStyleConstants.AXES_LINE_TYPE_ARROW);
		} else {
			plotPaneleuclideanViewD.setAxesLineStyle(
					euclideanStyleConstants.AXES_LINE_TYPE_FULL);
		}

		plotPaneleuclideanViewD.setDrawBorderAxes(getPlotSettings().isEdgeAxis);
		if (!getPlotSettings().isEdgeAxis[0]) {
			plotPaneleuclideanViewD.setAxisCross(0, 0);
		}
		if (!getPlotSettings().isEdgeAxis[1]) {
			plotPaneleuclideanViewD.setAxisCross(1, 0);
		}

		plotPaneleuclideanViewD
				.setPositiveAxes(getPlotSettings().isPositiveOnly);

		if (getPlotSettings().forceXAxisBuffer) {
			// ensure that the axis labels are shown
			// by forcing a fixed pixel height below the x-axis
			double pixelOffset = plotPaneleuclideanViewD.getPixelOffset();
			double pixelHeight = plotPaneleuclideanViewD.getHeight();
			getPlotSettings().yMin = (-pixelOffset * getPlotSettings().yMax)
					/ (pixelHeight + pixelOffset);
		}

		plotPaneleuclideanViewD.setAxesCornerCoordsVisible(false);

		plotPaneleuclideanViewD.setAutomaticAxesNumberingDistance(
				getPlotSettings().xAxesIntervalAuto, 0);
		plotPaneleuclideanViewD.setAutomaticAxesNumberingDistance(
				getPlotSettings().yAxesIntervalAuto, 1);
		Construction cons = plotPaneleuclideanViewD.getApplication().getKernel()
				.getConstruction();
		if (!getPlotSettings().xAxesIntervalAuto) {
			plotPaneleuclideanViewD.setAxesNumberingDistance(
					new GeoNumeric(cons, getPlotSettings().xAxesInterval), 0);
		} else {
			getPlotSettings().xAxesInterval = plotPaneleuclideanViewD
					.getAxesNumberingDistances()[0];
		}
		if (!getPlotSettings().yAxesIntervalAuto) {
			plotPaneleuclideanViewD.setAxesNumberingDistance(
					new GeoNumeric(cons, getPlotSettings().yAxesInterval), 1);
		} else {
			getPlotSettings().yAxesInterval = plotPaneleuclideanViewD
					.getAxesNumberingDistances()[1];
		}

		plotPaneleuclideanViewD
				.setPointCapturing(getPlotSettings().pointCaptureStyle);

		// do this last ?

		plotPaneleuclideanViewD.setRealWorldCoordSystem(
				getPlotSettings().logXAxis && getPlotSettings().xMin <= 0 ? 0.1
						: getPlotSettings().xMin,
				getPlotSettings().xMax,
				getPlotSettings().logYAxis && getPlotSettings().yMin <= 0 ? 0.1
						: getPlotSettings().yMin,
				getPlotSettings().yMax);

		plotPaneleuclideanViewD.repaint();
	}

	/**
	 * Update plot size
	 * 
	 * @param plotPanelEView
	 *            plot panel view
	 */
	public void updateSize(PlotPaneleuclideanViewInterface plotPanelEView) {
		// record the old coord system
		double xminTemp = plotPanelEView.getXmin();
		double xmaxTemp = plotPanelEView.getXmax();
		double yminTemp = plotPanelEView.getYmin();
		double ymaxTemp = plotPanelEView.getYmax();

		plotPanelEView.updateSizeKeepDrawables();

		// now reset the coord system so that our view dimensions are restored
		// using the new scaling factors.
		plotPanelEView.setRealWorldCoordSystem(xminTemp, xmaxTemp, yminTemp,
				ymaxTemp);

	}
}