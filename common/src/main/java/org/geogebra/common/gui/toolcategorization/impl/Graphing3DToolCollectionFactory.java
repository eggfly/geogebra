package org.geogebra.common.gui.toolcategorization.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.geogebra.common.euclidean.euclideanConstants;
import org.geogebra.common.gui.toolcategorization.ToolCategory;
import org.geogebra.common.gui.toolcategorization.ToolCollection;
import org.geogebra.common.gui.toolcategorization.ToolsetLevel;

/**
 * ToolCollectionFactory for the 3D Grapher app.
 */
public class Graphing3DToolCollectionFactory extends AbstractToolCollectionFactory {

    @Override
    public ToolCollection createToolCollection() {
        ToolCollectionImpl impl = new ToolCollectionImpl();
        createStandardLevel(impl);
        createAdvancedLevel(impl);

        impl.setLevel(ToolsetLevel.STANDARD);
        return impl;
    }

    private void createStandardLevel(ToolCollectionImpl impl) {
        impl.addLevel(ToolsetLevel.STANDARD);

        impl.extendCategory(ToolCategory.BASIC,
                euclideanConstants.MODE_MOVE,
                euclideanConstants.MODE_POINT,
                euclideanConstants.MODE_PYRAMID,
                euclideanConstants.MODE_CUBE,
                euclideanConstants.MODE_SPHERE_TWO_POINTS,
                euclideanConstants.MODE_PLANE_THREE_POINTS,
                euclideanConstants.MODE_INTERSECTION_CURVE,
                euclideanConstants.MODE_NET);
    }

    private void createAdvancedLevel(ToolCollectionImpl impl) {
        impl.addLevel(ToolsetLevel.ADVANCED);

        impl.extendCategory(ToolCategory.BASIC);

        impl.addCategory(ToolCategory.EDIT,
                euclideanConstants.MODE_SHOW_HIDE_LABEL,
                euclideanConstants.MODE_SHOW_HIDE_OBJECT,
                euclideanConstants.MODE_DELETE,
                euclideanConstants.MODE_VIEW_IN_FRONT_OF);

        impl.addCategory(ToolCategory.POINTS,
                euclideanConstants.MODE_POINT,
                euclideanConstants.MODE_INTERSECT,
                euclideanConstants.MODE_MIDPOINT,
                euclideanConstants.MODE_POINT_ON_OBJECT,
                euclideanConstants.MODE_ATTACH_DETACH);

		impl.addCategory(ToolCategory.LINES_AND_POLYGONS,
				euclideanConstants.MODE_SEGMENT,
				euclideanConstants.MODE_SEGMENT_FIXED,
				euclideanConstants.MODE_JOIN,
                euclideanConstants.MODE_RAY,
				euclideanConstants.MODE_VECTOR,
                euclideanConstants.MODE_POLYGON,
				euclideanConstants.MODE_REGULAR_POLYGON,
				euclideanConstants.MODE_ORTHOGONAL_THREE_D,
				euclideanConstants.MODE_PARALLEL,
				euclideanConstants.MODE_ANGULAR_BISECTOR,
				euclideanConstants.MODE_TANGENTS);

        impl.addCategory(ToolCategory.SOLIDS,
                euclideanConstants.MODE_PYRAMID,
                euclideanConstants.MODE_PRISM,
                euclideanConstants.MODE_TETRAHEDRON,
                euclideanConstants.MODE_CUBE,
                euclideanConstants.MODE_SPHERE_TWO_POINTS,
                euclideanConstants.MODE_SPHERE_POINT_RADIUS,
                euclideanConstants.MODE_CONE_TWO_POINTS_RADIUS,
                euclideanConstants.MODE_CYLINDER_TWO_POINTS_RADIUS,
                euclideanConstants.MODE_CONIFY,
                euclideanConstants.MODE_EXTRUSION,
                euclideanConstants.MODE_NET,
                euclideanConstants.MODE_SURFACE_OF_REVOLUTION);

        impl.addCategory(ToolCategory.PLANES,
                euclideanConstants.MODE_PLANE_THREE_POINTS,
                euclideanConstants.MODE_PLANE,
                euclideanConstants.MODE_PARALLEL_PLANE,
                euclideanConstants.MODE_ORTHOGONAL_PLANE);

        impl.addCategory(ToolCategory.CIRCLES,
                euclideanConstants.MODE_CIRCLE_AXIS_POINT,
                euclideanConstants.MODE_CIRCLE_POINT_RADIUS_DIRECTION,
                euclideanConstants.MODE_CIRCLE_THREE_POINTS,
                euclideanConstants.MODE_CIRCLE_ARC_THREE_POINTS,
                euclideanConstants.MODE_CIRCUMCIRCLE_ARC_THREE_POINTS,
                euclideanConstants.MODE_CIRCLE_SECTOR_THREE_POINTS,
                euclideanConstants.MODE_CIRCUMCIRCLE_SECTOR_THREE_POINTS);

        impl.addCategory(ToolCategory.CURVES,
                euclideanConstants.MODE_ELLIPSE_THREE_POINTS,
                euclideanConstants.MODE_CONIC_FIVE_POINTS,
                euclideanConstants.MODE_PARABOLA,
                euclideanConstants.MODE_HYPERBOLA_THREE_POINTS,
                euclideanConstants.MODE_LOCUS,
                euclideanConstants.MODE_INTERSECTION_CURVE);

        impl.addCategory(ToolCategory.TRANSFORM,
                euclideanConstants.MODE_MIRROR_AT_PLANE,
                euclideanConstants.MODE_MIRROR_AT_POINT,
                euclideanConstants.MODE_ROTATE_AROUND_LINE,
                euclideanConstants.MODE_TRANSLATE_BY_VECTOR,
                euclideanConstants.MODE_DILATE_FROM_POINT,
                euclideanConstants.MODE_MIRROR_AT_LINE);

        impl.addCategory(ToolCategory.MEASURE,
                euclideanConstants.MODE_ANGLE,
                euclideanConstants.MODE_DISTANCE,
                euclideanConstants.MODE_AREA,
                euclideanConstants.MODE_VOLUME);

        List<Integer> others = new ArrayList<>(Arrays.asList(
                euclideanConstants.MODE_ROTATEVIEW,
                euclideanConstants.MODE_TRANSLATEVIEW,
                euclideanConstants.MODE_COPY_VISUAL_STYLE
        ));
        if (!isPhoneApp) {
            others.add(euclideanConstants.MODE_TEXT);
        }
        impl.addCategory(ToolCategory.OTHERS, others);

        impl.addCategory(ToolCategory.SPECIAL_LINES,
                euclideanConstants.MODE_VECTOR_FROM_POINT,
                euclideanConstants.MODE_POLYLINE,
                // euclideanConstants.MODE_FITLINE,
                euclideanConstants.MODE_POLAR_DIAMETER);
    }
}
