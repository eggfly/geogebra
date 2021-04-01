package org.geogebra.common.gui.toolcategorization.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.geogebra.common.euclidean.euclideanConstants;
import org.geogebra.common.gui.toolcategorization.ToolCategory;
import org.geogebra.common.gui.toolcategorization.ToolCollection;
import org.geogebra.common.gui.toolcategorization.ToolsetLevel;

/**
 * ToolCollectionFactory for the Graphing Calculator app.
 */
public class SuiteToolCollectionFactory extends AbstractToolCollectionFactory {
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
                euclideanConstants.MODE_SLIDER,
                euclideanConstants.MODE_INTERSECT,
                euclideanConstants.MODE_EXTREMUM,
                euclideanConstants.MODE_ROOTS,
                euclideanConstants.MODE_FITLINE);

        impl.extendCategory(ToolCategory.EDIT,
                euclideanConstants.MODE_SELECT,
                euclideanConstants.MODE_TRANSLATEVIEW,
                euclideanConstants.MODE_DELETE,
                euclideanConstants.MODE_SHOW_HIDE_LABEL,
                euclideanConstants.MODE_SHOW_HIDE_OBJECT);

        if (!isPhoneApp) {
            impl.extendCategory(ToolCategory.MEDIA,
                    euclideanConstants.MODE_IMAGE,
                    euclideanConstants.MODE_TEXT);
        }

        impl.extendCategory(ToolCategory.MEASURE,
                euclideanConstants.MODE_ANGLE,
                euclideanConstants.MODE_DISTANCE,
                euclideanConstants.MODE_AREA);

        impl.extendCategory(ToolCategory.TRANSFORM,
                euclideanConstants.MODE_MIRROR_AT_LINE,
                euclideanConstants.MODE_MIRROR_AT_POINT,
                euclideanConstants.MODE_TRANSLATE_BY_VECTOR);

        impl.extendCategory(ToolCategory.CONSTRUCT,
                euclideanConstants.MODE_MIDPOINT,
                euclideanConstants.MODE_ORTHOGONAL,
                euclideanConstants.MODE_LINE_BISECTOR,
                euclideanConstants.MODE_PARALLEL,
                euclideanConstants.MODE_ANGULAR_BISECTOR,
                euclideanConstants.MODE_TANGENTS);

        impl.extendCategory(ToolCategory.LINES,
                euclideanConstants.MODE_SEGMENT,
                euclideanConstants.MODE_JOIN,
                euclideanConstants.MODE_RAY,
                euclideanConstants.MODE_VECTOR);

        impl.extendCategory(ToolCategory.CIRCLES,
                euclideanConstants.MODE_CIRCLE_TWO_POINTS,
                euclideanConstants.MODE_COMPASSES,
                euclideanConstants.MODE_SEMICIRCLE);
    }

    private void createAdvancedLevel(ToolCollectionImpl impl) {
        impl.addLevel(ToolsetLevel.ADVANCED);

        impl.extendCategory(ToolCategory.BASIC);

        impl.extendCategory(ToolCategory.EDIT,
                euclideanConstants.MODE_COPY_VISUAL_STYLE);

        if (!isPhoneApp) {
            impl.extendCategory(ToolCategory.MEDIA);
        }

        impl.extendCategory(ToolCategory.MEASURE,
                euclideanConstants.MODE_ANGLE_FIXED,
                euclideanConstants.MODE_SLOPE);

        impl.extendCategory(ToolCategory.POINTS,
                euclideanConstants.MODE_POINT,
                euclideanConstants.MODE_INTERSECT,
                euclideanConstants.MODE_POINT_ON_OBJECT,
                euclideanConstants.MODE_ATTACH_DETACH,
                euclideanConstants.MODE_EXTREMUM,
                euclideanConstants.MODE_ROOTS,
                euclideanConstants.MODE_COMPLEX_NUMBER,
                euclideanConstants.MODE_CREATE_LIST);

        impl.extendCategory(ToolCategory.CONSTRUCT,
                euclideanConstants.MODE_LOCUS);

        impl.extendCategory(ToolCategory.LINES,
                euclideanConstants.MODE_SEGMENT_FIXED,
                euclideanConstants.MODE_VECTOR_FROM_POINT,
                euclideanConstants.MODE_POLAR_DIAMETER,
                euclideanConstants.MODE_POLYLINE);

        impl.extendCategory(ToolCategory.POLYGONS,
                euclideanConstants.MODE_POLYGON,
                euclideanConstants.MODE_REGULAR_POLYGON,
                euclideanConstants.MODE_VECTOR_POLYGON,
                euclideanConstants.MODE_RIGID_POLYGON);

        impl.extendCategory(ToolCategory.CIRCLES,
                euclideanConstants.MODE_CIRCLE_POINT_RADIUS,
                euclideanConstants.MODE_CIRCLE_THREE_POINTS,
                euclideanConstants.MODE_CIRCLE_ARC_THREE_POINTS,
                euclideanConstants.MODE_CIRCUMCIRCLE_ARC_THREE_POINTS,
                euclideanConstants.MODE_CIRCLE_SECTOR_THREE_POINTS,
                euclideanConstants.MODE_CIRCUMCIRCLE_SECTOR_THREE_POINTS);

        impl.extendCategory(ToolCategory.CONICS,
                euclideanConstants.MODE_ELLIPSE_THREE_POINTS,
                euclideanConstants.MODE_CONIC_FIVE_POINTS,
                euclideanConstants.MODE_PARABOLA,
                euclideanConstants.MODE_HYPERBOLA_THREE_POINTS);

        impl.extendCategory(ToolCategory.TRANSFORM,
                euclideanConstants.MODE_ROTATE_BY_ANGLE,
                euclideanConstants.MODE_DILATE_FROM_POINT,
                euclideanConstants.MODE_MIRROR_AT_CIRCLE);

        List<Integer> others = new ArrayList<>(Arrays.asList(
                euclideanConstants.MODE_PEN,
                euclideanConstants.MODE_FREEHAND_SHAPE,
                euclideanConstants.MODE_RELATION));

        if (!isPhoneApp) {
            others.addAll(Arrays.asList(
                    // euclideanConstants.MODE_FUNCTION_INSPECTOR,
                    euclideanConstants.MODE_BUTTON_ACTION,
                    euclideanConstants.MODE_SHOW_HIDE_CHECKBOX,
                    euclideanConstants.MODE_TEXTFIELD_ACTION
                    // euclideanConstants.MODE_CREATE_LIST
            ));
        }
        impl.extendCategory(ToolCategory.OTHERS, others);
    }
}

