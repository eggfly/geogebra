package org.geogebra.web.full.gui.view.data;

import org.geogebra.common.gui.view.data.PlotPaneleuclideanViewCommon;
import org.geogebra.common.gui.view.data.PlotPaneleuclideanViewInterface;
import org.geogebra.common.gui.view.data.PlotSettings;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.ggbjdk.java.awt.geom.Dimension;
import org.geogebra.web.full.gui.GuiManagerW;
import org.geogebra.web.full.gui.layout.panels.ProbabilityCalculatorDockPanelW;
import org.geogebra.web.html5.euclidean.euclideanViewW;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author gabor
 *
 *Plot panel for ProbabilityCalculator
 */
public class PlotPaneleuclideanViewW extends euclideanViewW
		implements PlotPaneleuclideanViewInterface {
	
	/**
	 * default height ot PlotPaneleuclideanViewW
	 */
	public static final int DEFAULT_HEIGHT = 300;
	
	public PlotPaneleuclideanViewCommon commonFields;
	
	/*************************************************
	 * Construct the panel
	 */
	public PlotPaneleuclideanViewW(Kernel kernel) {
		super(new PlotPaneleuclideanControllerW(kernel), EVNO_GENERAL, null);
		
		if (commonFields == null) {
			setCommonFields();
		}
		
		// set preferred size so that updateSize will work and this EV can be
		// properly initialized
		setPreferredSize(new Dimension(
				ProbabilityCalculatorDockPanelW.DEFAULT_WIDTH, DEFAULT_HEIGHT));
		updateSize();
	}
	
	private void setCommonFields() {
		// set fields
		commonFields = new PlotPaneleuclideanViewCommon(false);
		commonFields.setPlotSettings(new PlotSettings());

		setViewId(kernel);
	}
	
	/*********** End Constructor **********************/

	/**
	 * Overrides euclideanView setMode method so that no action is taken on a
	 * mode change.
	 */
	@Override
	public void setMode(int mode) {
		// .... do nothing
	}
	
	/** Returns viewID */
	@Override
	public int getViewID() {
		if (commonFields == null) {
			setCommonFields();
		}
		return commonFields.getViewID();
	}

	@Override
	public void setViewId(Kernel kernel) {
	    // get viewID from GuiManager
		commonFields.setViewID(((GuiManagerW) kernel.getApplication().getGuiManager())
				.assignPlotPanelID(this));
    }

	@Override
	public void setEVParams() {
	    commonFields.setEVParams(this);
    }

	@Override
	public double getPixelOffset() {
		return (30 * getApplication().getFontSize()
				) / 12.0;
	}

	@Override
	public void updateSizeKeepDrawables() {
		super.updateSizeKeepDrawables();
	}
	
	/**
	 * @return panel wrapping the view
	 */
	public Widget getComponent() {
		return getAbsolutePanel();
	}

	@Override
	public boolean isPlotPanel() {
		return true;
	}

}
