package org.geogebra.common.gui.toolcategorization;

import java.util.Arrays;
import java.util.List;

import org.geogebra.common.euclidean.euclideanConstants;

/**
 * Utility class for Graphing ToolSet tests.
 */
public class GraphingToolSet {

    private static List<Integer> notAllowedToolsGraphingCalc = Arrays.asList(
        euclideanConstants.MODE_SEGMENT,
        euclideanConstants.MODE_IMAGE,
        euclideanConstants.MODE_ANGLE,
        euclideanConstants.MODE_ANGLE_FIXED,
        euclideanConstants.MODE_SLOPE,
        euclideanConstants.MODE_MIDPOINT,
        euclideanConstants.MODE_LINE_BISECTOR,
        euclideanConstants.MODE_PARALLEL,
        euclideanConstants.MODE_ANGULAR_BISECTOR,
        euclideanConstants.MODE_TANGENTS,
        euclideanConstants.MODE_LOCUS,
        euclideanConstants.MODE_SEGMENT_FIXED,
        euclideanConstants.MODE_POLAR_DIAMETER,
        euclideanConstants.MODE_POLYLINE,
        euclideanConstants.MODE_POLYGON,
        euclideanConstants.MODE_REGULAR_POLYGON,
        euclideanConstants.MODE_VECTOR_POLYGON,
        euclideanConstants.MODE_RIGID_POLYGON,
        euclideanConstants.MODE_SHAPE_CIRCLE,
        euclideanConstants.MODE_COMPASSES,
        euclideanConstants.MODE_SEMICIRCLE,
        euclideanConstants.MODE_CIRCLE_POINT_RADIUS_DIRECTION,
        euclideanConstants.MODE_CIRCLE_POINT_RADIUS,
        euclideanConstants.MODE_CIRCLE_ARC_THREE_POINTS,
        euclideanConstants.MODE_CIRCLE_THREE_POINTS,
        euclideanConstants.MODE_CIRCLE_SECTOR_THREE_POINTS,
        euclideanConstants.MODE_CIRCLE_TWO_POINTS,
        euclideanConstants.MODE_CIRCLE_AXIS_POINT,
        euclideanConstants.MODE_CIRCUMCIRCLE_ARC_THREE_POINTS,
        euclideanConstants.MODE_CIRCUMCIRCLE_SECTOR_THREE_POINTS,
        euclideanConstants.MODE_SHAPE_ELLIPSE,
        euclideanConstants.MODE_CONIC_FIVE_POINTS,
        euclideanConstants.MODE_PARABOLA,
        euclideanConstants.MODE_HYPERBOLA_THREE_POINTS,
        euclideanConstants.MODE_MIRROR_AT_LINE,
        euclideanConstants.MODE_MIRROR_AT_POINT,
        euclideanConstants.MODE_MOVE_ROTATE,
        euclideanConstants.MODE_TRANSLATE_BY_VECTOR,
        euclideanConstants.MODE_DILATE_FROM_POINT,
        euclideanConstants.MODE_MIRROR_AT_CIRCLE,
        euclideanConstants.MODE_RELATION);

    public static boolean isInGraphingToolSet(int tool) {
        return notAllowedToolsGraphingCalc.contains(tool);
    }
}