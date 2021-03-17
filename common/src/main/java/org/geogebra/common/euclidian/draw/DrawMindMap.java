package org.geogebra.common.euclidian.draw;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import org.geogebra.common.kernel.geos.MoveGeos;
import org.geogebra.common.kernel.matrix.Coords;

public class DrawMindMap extends DrawInlineText {

	private static final int BORDER_RADIUS = 8;

	private final GeoMindMapNode node;

	public DrawMindMap(EuclidianView view, GeoInline text) {
		super(view, text);
		this.node = (GeoMindMapNode) text;
	}

	@Override
	public void draw(GGraphics2D g2) {
		for (GeoMindMapNode childGeo : node.getChildren()) {
			DrawMindMap child = (DrawMindMap) view.getDrawableFor(childGeo);
			if (child == null) {
				continue;
			}

			NodeAlignment alignment = childGeo.getAlignment();

			double x0 = rectangle.getLeft() + alignment.dx0 * rectangle.getWidth();
			double y0 = rectangle.getTop() + alignment.dy0 * rectangle.getHeight();
			double x1 = child.rectangle.getLeft() + alignment.dx1 * child.rectangle.getWidth();
			double y1 = child.rectangle.getTop() + alignment.dy1 * child.rectangle.getHeight();

			GGeneralPath path = AwtFactory.getPrototype().newGeneralPath();
			path.moveTo(x0, y0);
			if (alignment == NodeAlignment.TOP || alignment == NodeAlignment.BOTTOM) {
				path.curveTo(x0, (y0 + 3 * y1) / 4, x1, (3 * y0 + y1) / 4, x1, y1);
			} else {
				path.curveTo((x0 + 3 * x1) / 4, y0, (3 * x0 + x1) / 4, y1, x1, y1);
			}

			g2.setStroke(border1);
			g2.setColor(GColor.BLACK);
			g2.draw(path);
		}

		draw(g2, BORDER_RADIUS);
	}

	private NodeAlignment toAlignment(EuclidianBoundingBoxHandler addHandler) {
		switch (addHandler) {
		case ADD_TOP:
			return NodeAlignment.TOP;
		case ADD_RIGHT:
			return NodeAlignment.RIGHT;
		case ADD_BOTTOM:
			return NodeAlignment.BOTTOM;
		case ADD_LEFT:
			return NodeAlignment.LEFT;
		default:
			return null;
		}
	}

	public GeoMindMapNode addChildNode(EuclidianBoundingBoxHandler addHandler) {
		NodeAlignment newAlignment = toAlignment(addHandler);

		GPoint2D newLocation = computeNewLocation(newAlignment);
		GeoMindMapNode child = new GeoMindMapNode(node.getConstruction(), newLocation);
		child.setSize(GeoMindMapNode.MIN_WIDTH, GeoMindMapNode.CHILD_HEIGHT);
		child.setParent(node, newAlignment);
		child.setBackgroundColor(child.getKernel().getApplication().isMebis()
				? GColor.MOW_MIND_MAP_CHILD_BG_COLOR : GColor.MIND_MAP_CHILD_BG_COLOR);
		child.setBorderColor(child.getKernel().getApplication().isMebis()
				? GColor.MOW_MIND_MAP_CHILD_BORDER_COLOR : GColor.MIND_MAP_CHILD_BORDER_COLOR);
		return child;
	}

	private GPoint2D computeNewLocation(NodeAlignment newAlignment) {
		Comparator<DrawMindMap> comparator;
		if (newAlignment == NodeAlignment.TOP || newAlignment == NodeAlignment.BOTTOM) {
			comparator = Comparator.comparing(mindMap -> mindMap.rectangle.getRight());
		} else {
			comparator = Comparator.comparing(mindMap -> mindMap.rectangle.getBottom());
		}

		List<GeoMindMapNode> childGeos = node.getChildren().stream()
				.filter(node -> node.getAlignment() == newAlignment)
				.collect(Collectors.toList());

		List<DrawMindMap> children = childGeos.stream()
				.map(node -> (DrawMindMap) view.getDrawableFor(node))
				.sorted(comparator)
				.collect(Collectors.toList());

		if (correctlyAligned(newAlignment, children)) {
			int spaceGained = decreaseDistanceBetweenChildren(newAlignment, children);

			if (newAlignment == NodeAlignment.TOP || newAlignment == NodeAlignment.BOTTOM) {
				double toMove
						= marginLeft(newAlignment, children.size()) + GeoMindMapNode.MIN_WIDTH - spaceGained;
				MoveGeos.moveObjects(childGeos, new Coords(-view.getInvXscale() * toMove / 2, 0, 0),
						null, null, view);
			} else {
				double toMove
						= marginTop(newAlignment, children.size()) + GeoMindMapNode.CHILD_HEIGHT - spaceGained;
				MoveGeos.moveObjects(childGeos, new Coords(0, view.getInvYscale() * toMove / 2,  0),
						null, null, view);
			}
		}

		double left = 0;
		double top = 0;
		if (children.isEmpty()) {
			left = rectangle.getLeft() + newAlignment.dx0 * rectangle.getWidth();
			top = rectangle.getTop() + newAlignment.dy0 * rectangle.getHeight();

			switch (newAlignment) {
			case BOTTOM:
				left -= GeoMindMapNode.MIN_WIDTH / 2;
				top += 64;
				break;
			case LEFT:
				left -= 64;
				top -= GeoMindMapNode.CHILD_HEIGHT / 2;
				break;
			case TOP:
				left -= GeoMindMapNode.MIN_WIDTH / 2;
				top -= 64;
				break;
			case RIGHT:
				left += 64;
				top -= GeoMindMapNode.CHILD_HEIGHT / 2;
				break;
			}
		} else {
			Stream<DrawMindMap> stream = children.stream();
			DrawMindMap last = children.get(children.size() - 1);

			switch (newAlignment) {
			case BOTTOM:
				left = last.rectangle.getRight();
				top = stream.mapToInt(mindMap -> mindMap.rectangle.getTop()).min().orElse(0);
				break;
			case LEFT:
				left = stream.mapToInt(mindMap -> mindMap.rectangle.getRight()).min().orElse(0);
				top = last.rectangle.getBottom();
				break;
			case TOP:
				left = last.rectangle.getRight();
				top = stream.mapToInt(mindMap -> mindMap.rectangle.getBottom()).max().orElse(0);
				break;
			case RIGHT:
				left = stream.mapToInt(mindMap -> mindMap.rectangle.getLeft()).min().orElse(0);
				top = last.rectangle.getBottom();
				break;
			}

			left += marginLeft(newAlignment, children.size());
			top += marginTop(newAlignment, children.size());
		}

		switch (newAlignment) {
		case TOP:
			top -= GeoMindMapNode.CHILD_HEIGHT;
			break;
		case LEFT:
			left -= GeoMindMapNode.MIN_WIDTH;
			break;
		}

		return new GPoint2D(view.toRealWorldCoordX(left), view.toRealWorldCoordY(top));
	}

	private boolean correctlyAligned(NodeAlignment newAlignment,
			List<DrawMindMap> children) {
		if (newAlignment == NodeAlignment.TOP || newAlignment == NodeAlignment.BOTTOM) {
			for (int i = 1; i < children.size(); i++) {
				int rightOfLeft = children.get(i - 1).rectangle.getRight();
				int leftOfRight = children.get(i).rectangle.getLeft();

				int distance = leftOfRight - rightOfLeft;
				if (Math.abs(distance - marginLeft(newAlignment, children.size() - 1)) > 3) {
					return false;
				}
			}

			return true;
		} else {
			return true;
		}
	}

	private int decreaseDistanceBetweenChildren(NodeAlignment newAlignment,
			List<DrawMindMap> children) {
		if (children.size() == 1) {
			return 0;
		}

		if (newAlignment == NodeAlignment.TOP || newAlignment == NodeAlignment.BOTTOM) {
			if (children.size() == 2) {
				int rightOfLeft = children.get(0).rectangle.getRight();
				int leftOfRight = children.get(1).rectangle.getLeft();

				int error = (leftOfRight - rightOfLeft) - 32;
				double toMove = -view.getInvXscale() * (16 + error);
				MoveGeos.moveObjects(Collections.singletonList(children.get(1).node),
						new Coords(toMove, 0, 0), null, null, view);
				return 16 + error;
			}
		} else {
			if (children.size() == 2) {
				int bottomOfTop = children.get(0).rectangle.getBottom();
				int topOfBottom = children.get(1).rectangle.getTop();

				int error = (topOfBottom - bottomOfTop) - 64;
				double toMove = view.getInvYscale() * (32 + error);
				MoveGeos.moveObjects(Collections.singletonList(children.get(1).node),
						new Coords(0, toMove, 0), null, null, view);
				return 32 + error;
			} else if (children.size() == 3) {
				int bottomOfTop = children.get(0).rectangle.getBottom();
				int topOfMiddle = children.get(1).rectangle.getTop();

				int bottomOfMiddle = children.get(1).rectangle.getBottom();
				int topOfBottom = children.get(2).rectangle.getTop();

				int error1 = (topOfMiddle - bottomOfTop) - 32;
				int error2 = (topOfBottom - bottomOfMiddle) - 32;

				double toMove1 = view.getInvYscale() * (16 + error1);
				double toMove2 = view.getInvYscale() * (32 + error1 + error2);

				MoveGeos.moveObjects(Collections.singletonList(children.get(1).node),
						new Coords(0, toMove1, 0), null, null, view);
				MoveGeos.moveObjects(Collections.singletonList(children.get(2).node),
						new Coords(0, toMove2, 0), null, null, view);
				return 32 + error1 + error2;
			}
		}

		return 0;
	}

	private int marginLeft(NodeAlignment newAlignment, int size) {
		if (newAlignment == NodeAlignment.TOP || newAlignment == NodeAlignment.BOTTOM) {
			if (size == 1) {
				return 32;
			} else {
				return 16;
			}
		}

		return 0;
	}

	private double marginTop(NodeAlignment newAlignment, int size) {
		if (newAlignment == NodeAlignment.LEFT || newAlignment == NodeAlignment.RIGHT) {
			if (size == 1) {
				return 64;
			} else if (size == 2) {
				return 32;
			} else {
				return 16;
			}
		}

		return 0;
	}
}
