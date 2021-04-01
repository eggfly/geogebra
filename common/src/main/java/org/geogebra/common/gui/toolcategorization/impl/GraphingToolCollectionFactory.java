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
public class GraphingToolCollectionFactory extends AbstractToolCollectionFactory {

    @Override
    public ToolCollection createToolCollection() {
        ToolCollectionImpl impl = new ToolCollectionImpl();
        createTools(impl);

        return impl;
    }

    private void createTools(ToolCollectionImpl impl) {
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
                euclideanConstants.MODE_SHOW_HIDE_OBJECT,
                euclideanConstants.MODE_COPY_VISUAL_STYLE);

        if (!isPhoneApp) {
            impl.extendCategory(ToolCategory.MEDIA,
                    euclideanConstants.MODE_TEXT);
        }

        impl.extendCategory(ToolCategory.POINTS,
                euclideanConstants.MODE_POINT,
                euclideanConstants.MODE_INTERSECT,
                euclideanConstants.MODE_POINT_ON_OBJECT,
                euclideanConstants.MODE_ATTACH_DETACH,
                euclideanConstants.MODE_EXTREMUM,
                euclideanConstants.MODE_ROOTS,
                euclideanConstants.MODE_COMPLEX_NUMBER,
                euclideanConstants.MODE_CREATE_LIST);

        impl.extendCategory(ToolCategory.LINES,
                euclideanConstants.MODE_JOIN,
                euclideanConstants.MODE_RAY,
                euclideanConstants.MODE_VECTOR,
                euclideanConstants.MODE_VECTOR_FROM_POINT);

        List<Integer> others = new ArrayList<>(Arrays.asList(
                euclideanConstants.MODE_PEN,
                euclideanConstants.MODE_FREEHAND_FUNCTION));

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
