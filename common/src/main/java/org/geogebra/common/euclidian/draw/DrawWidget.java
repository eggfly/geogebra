package org.geogebra.common.euclidean.draw;

import java.util.ArrayList;
import java.util.List;

import org.geogebra.common.awt.GAffineTransform;
import org.geogebra.common.awt.GPoint2D;
import org.geogebra.common.awt.GRectangle;
import org.geogebra.common.awt.GShape;
import org.geogebra.common.euclidean.BoundingBox;
import org.geogebra.common.euclidean.Drawable;
import org.geogebra.common.euclidean.euclideanBoundingBoxHandler;
import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.euclidean.MediaBoundingBox;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.GeoWidget;
import org.geogebra.common.kernel.geos.RectangleTransformable;

public abstract class DrawWidget extends Drawable implements HasTransformation {

	private final TransformableRectangle rectangle;

	/**
	 * @param view view
	 * @param geo construction element
	 */
	public DrawWidget(euclideanView view, GeoElement geo, boolean fixedRatio) {
		super(view, geo);
		this.rectangle = new TransformableRectangle(view, (RectangleTransformable) geo,
				fixedRatio);
	}

	protected void updateBounds() {
		rectangle.updateSelfAndBoundingBox();
	}

	@Override
	public boolean hit(int x, int y, int hitThreshold) {
		return rectangle.hit(x, y);
	}

	@Override
	public boolean isInside(GRectangle rect) {
		return rect.contains(getBounds());
	}

	@Override
	public GRectangle getBounds() {
		return rectangle.getBounds();
	}

	@Override
	public MediaBoundingBox getBoundingBox() {
		return rectangle.getBoundingBox();
	}

	@Override
	public BoundingBox<? extends GShape> getSelectionBoundingBox() {
		return getBoundingBox();
	}

	/**
	 * @return width on screen at current zoom
	 */
	public final double getWidth() {
		return getGeoElement().getWidth();
	}

	/**
	 * @return height on screen at current zoom
	 */
	public final double getHeight() {
		return getGeoElement().getHeight();
	}

	/**
	 * @return left corner x-coord in EV
	 */
	public final double getLeft() {
		return view.toScreenCoordX(getGeoElement().getLocation().getX());
	}

	/**
	 * @return top corner y-coord in EV
	 */
	public final double getTop() {
		return view.toScreenCoordY(getGeoElement().getLocation().getY());
	}

	@Override
	public List<GPoint2D> toPoints() {
		return rectangle.toPoints();
	}

	@Override
	public void fromPoints(ArrayList<GPoint2D> pts) {
		rectangle.fromPoints(pts);
	}

	@Override
	public void updateByBoundingBoxResize(GPoint2D point,
			euclideanBoundingBoxHandler handler) {
		rectangle.updateByBoundingBoxResize(point, handler);
	}

	@Override
	public abstract GeoWidget getGeoElement();

	/**
	 * @return embed ID
	 */
	public abstract int getEmbedID();

	public abstract boolean isBackground();

	public abstract void setBackground(boolean b);

	@Override
	public GAffineTransform getTransform() {
		return rectangle.getDirectTransform();
	}

	public GPoint2D getInversePoint(int x, int y) {
		return rectangle.getInversePoint(x, y);
	}
}
