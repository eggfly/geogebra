package org.geogebra.common.euclidian.draw;

import org.geogebra.common.awt.GColor;
import org.geogebra.common.awt.GGeneralPath;
import org.geogebra.common.awt.GGraphics2D;
import org.geogebra.common.awt.GPoint2D;
import org.geogebra.common.euclidian.EuclidianBoundingBoxHandler;
import org.geogebra.common.euclidian.EuclidianView;
import org.geogebra.common.factories.AwtFactory;
import org.geogebra.common.kernel.geos.GeoInline;
import org.geogebra.common.kernel.geos.GeoMindMapNode;
import org.geogebra.common.kernel.geos.GeoMindMapNode.NodeAlignment;

public class DrawMindMap extends DrawInlineText {

	private static final int BORDER_RADIUS = 8;

	private final GeoMindMapNode node;
	private double mx0 = 0;
	private double mx1 = 0;
	private double my0 = 0;
	private double my1 = 0;
	private NodeAlignment mAlignment = null;

	public DrawMindMap(EuclidianView view, GeoInline text) {
		super(view, text);
		this.node = (GeoMindMapNode) text;
	}

	@Override
	public void update() {
		super.update();
		GeoMindMapNode parentGeo = node.getParent();
		DrawMindMap parent = (DrawMindMap) view.getDrawableFor(parentGeo);
		if (parent != null) {
			parent.updateAlignment(this);
		}
	}

	@Override
	public void draw(GGraphics2D g2) {
		for (GeoMindMapNode childGeo : node.getChildren()) {
			DrawMindMap child = (DrawMindMap) view.getDrawableFor(childGeo);
			if (child == null) {
				continue;
			}
			GGeneralPath path = child.getConnectionPath();
			g2.setStroke(border1);
			g2.setColor(GColor.BLACK);
			g2.draw(path);
		}

		draw(g2, BORDER_RADIUS);
	}

	private GGeneralPath getConnectionPath() {
		GGeneralPath path = AwtFactory.getPrototype().newGeneralPath();
		path.moveTo(mx0, my0);
		double w0 = 1.0 / 4;
		if (isIntersecting(mAlignment, mx0, mx1, my0, my1)) {
			w0 = 2;
		}
		double w1 = 1 - w0;
		if (mAlignment == NodeAlignment.TOP || mAlignment == NodeAlignment.BOTTOM) {
			path.curveTo(mx0, w0 * my0 + w1 * my1, mx1,
					w1 * my0 + w0 * my1, mx1, my1);
		} else {
			path.curveTo(w0 * mx0 + w1 * mx1, my0, w1 * mx0 + w0 * mx1, my1, mx1, my1);
		}
		return path;
	}

	private void updateAlignment(DrawMindMap child) {
		double distSq = Double.POSITIVE_INFINITY;
		boolean intersect = true;
		for (NodeAlignment alignment: NodeAlignment.values()) {
			if (node.getParent() != null && alignment.isOpposite(mAlignment)) {
				continue;
			}
			double x0 = rectangle.getLeft() + alignment.dx0 * rectangle.getWidth();
			double y0 = rectangle.getTop() + alignment.dy0 * rectangle.getHeight();
			double x1 = child.rectangle.getLeft() + alignment.dx1 * child.rectangle.getWidth();
			double y1 = child.rectangle.getTop() + alignment.dy1 * child.rectangle.getHeight();
			double newDist = (x0 - x1) * (x0 - x1) + (y0 - y1) * (y0 - y1);
			boolean newIntersect = isIntersecting(alignment, x0, x1, y0, y1);
			if ((!newIntersect && intersect) || ((newIntersect == intersect) && newDist < distSq)) {
				child.mx0 = x0;
				child.mx1 = x1;
				child.my0 = y0;
				child.my1 = y1;
				child.mAlignment = alignment;
				intersect = newIntersect;
				distSq = newDist;
			}
		}
	}

	private boolean isIntersecting(NodeAlignment alignment,
			double x0, double x1, double y0, double y1) {
		return ((alignment.dx0 - 0.5) * (x0 - x1) > 0)
				|| ((alignment.dy0 - 0.5) * (y0 - y1) > 0);
	}

	public GeoMindMapNode addChildNode(EuclidianBoundingBoxHandler addHandler) {
		GPoint2D newLocation = new GPoint2D(node.getLocation().x, node.getLocation().y);
		GeoMindMapNode child = new GeoMindMapNode(node.getConstruction(), newLocation);
		child.setSize(GeoMindMapNode.MIN_WIDTH, GeoMindMapNode.CHILD_HEIGHT);
		child.setParent(node);
		child.setBackgroundColor(child.getKernel().getApplication().isMebis()
				? GColor.MOW_MIND_MAP_CHILD_BG_COLOR : GColor.MIND_MAP_CHILD_BG_COLOR);
		child.setBorderColor(child.getKernel().getApplication().isMebis()
				? GColor.MOW_MIND_MAP_CHILD_BORDER_COLOR : GColor.MIND_MAP_CHILD_BORDER_COLOR);
		return child;
	}

	public NodeAlignment getAlignment() {
		return mAlignment;
	}
}
