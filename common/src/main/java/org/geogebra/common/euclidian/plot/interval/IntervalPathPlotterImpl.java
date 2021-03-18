package org.geogebra.common.euclidian.plot.interval;

import org.geogebra.common.awt.GGraphics2D;
import org.geogebra.common.euclidian.GeneralPathClipped;
import org.geogebra.common.kernel.geos.GeoElement;

public class IntervalPathPlotterImpl implements IntervalPathPlotter {
	private final GeoElement geo;
	private GeneralPathClipped gp;

	/**
	 * @param gp path
	 * @param geo construction element
	 */
	public IntervalPathPlotterImpl(GeneralPathClipped gp, GeoElement geo) {
		this.gp = gp;
		this.geo = geo;
	}

	@Override
	public void reset() {
		gp.resetWithThickness(geo.getLineThickness());
	}

	@Override
	public void moveTo(double x, double y) {
		gp.moveTo(x, y);
	}

	@Override
	public void lineTo(double x, double y) {
		gp.lineTo(x, y);
	}

	@Override
	public void draw(GGraphics2D g2) {
		g2.draw(gp);
	}
}
