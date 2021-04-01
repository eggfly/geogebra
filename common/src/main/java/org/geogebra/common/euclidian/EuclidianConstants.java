package org.geogebra.common.euclidean;

import org.geogebra.common.util.debug.Log;

/**
 * Mode numbers
 */
public final class euclideanConstants {

	/** Move */
	public static final int MODE_MOVE = 0;

	/** New Point */
	public static final int MODE_POINT = 1;

	/** Line through Two Points */
	public static final int MODE_JOIN = 2;

	/** Parallel Line */
	public static final int MODE_PARALLEL = 3;

	/** Perpendicular Line */
	public static final int MODE_ORTHOGONAL = 4;

	/** Intersect Two Objects */
	public static final int MODE_INTERSECT = 5;

	/** Delete Object */
	public static final int MODE_DELETE = 6;

	/** Vector between Two Points */
	public static final int MODE_VECTOR = 7;

	/** Perpendicular Bisector */
	public static final int MODE_LINE_BISECTOR = 8;

	/** Angle Bisector */
	public static final int MODE_ANGULAR_BISECTOR = 9;

	/** Circle with Center through Point */
	public static final int MODE_CIRCLE_TWO_POINTS = 10;

	/** Circle through Three Points */
	public static final int MODE_CIRCLE_THREE_POINTS = 11;

	/** Conic through Five Points */
	public static final int MODE_CONIC_FIVE_POINTS = 12;

	/** Tangents */
	public static final int MODE_TANGENTS = 13;

	/** Relation between Two Objects */
	public static final int MODE_RELATION = 14;

	/** Segment between Two Points */
	public static final int MODE_SEGMENT = 15;

	/** Polygon */
	public static final int MODE_POLYGON = 16;

	/** Insert Text */
	public static final int MODE_TEXT = 17;

	/** Ray through Two Points */
	public static final int MODE_RAY = 18;

	/** Midpoint or Center */
	public static final int MODE_MIDPOINT = 19;

	/** Circular Arc with Center between Two Points */
	public static final int MODE_CIRCLE_ARC_THREE_POINTS = 20;

	/** Circular Sector with Center between Two Points */
	public static final int MODE_CIRCLE_SECTOR_THREE_POINTS = 21;

	/** Circumcircular Arc through Three Points */
	public static final int MODE_CIRCUMCIRCLE_ARC_THREE_POINTS = 22;

	/** Circumcircular Sector through Three Points */
	public static final int MODE_CIRCUMCIRCLE_SECTOR_THREE_POINTS = 23;

	/** Semicircle through Two Points */
	public static final int MODE_SEMICIRCLE = 24;

	/** Slider */
	public static final int MODE_SLIDER = 25;

	/** Insert Image */
	public static final int MODE_IMAGE = 26;

	/** Show / Hide Object */
	public static final int MODE_SHOW_HIDE_OBJECT = 27;

	/** Show / Hide Label */
	public static final int MODE_SHOW_HIDE_LABEL = 28;

	/** Reflect Object about Point */
	public static final int MODE_MIRROR_AT_POINT = 29;

	/** Reflect Object about Line */
	public static final int MODE_MIRROR_AT_LINE = 30;

	/** Translate Object by Vector */
	public static final int MODE_TRANSLATE_BY_VECTOR = 31;

	/** Rotate Object around Point by Angle */
	public static final int MODE_ROTATE_BY_ANGLE = 32;

	/** Dilate Object from Point by Factor */
	public static final int MODE_DILATE_FROM_POINT = 33;

	/** Circle with Center and Radius */
	public static final int MODE_CIRCLE_POINT_RADIUS = 34;

	/** Copy Visual Style */
	public static final int MODE_COPY_VISUAL_STYLE = 35;

	/** Angle */
	public static final int MODE_ANGLE = 36;

	/** Vector from Point */
	public static final int MODE_VECTOR_FROM_POINT = 37;

	/** Distance or Length */
	public static final int MODE_DISTANCE = 38;

	/** Rotate around Point */
	public static final int MODE_MOVE_ROTATE = 39;

	/** Move Graphics View */
	public static final int MODE_TRANSLATEVIEW = 40;

	/** Zoom In */
	public static final int MODE_ZOOM_IN = 41;

	/** Zoom Out */
	public static final int MODE_ZOOM_OUT = 42;

	/** Select Object */
	public static final int MODE_SELECTION_LISTENER = 43;

	/** Polar or Diameter Line */
	public static final int MODE_POLAR_DIAMETER = 44;

	/** Segment with Given Length from Point */
	public static final int MODE_SEGMENT_FIXED = 45;

	/** Angle with Given Size */
	public static final int MODE_ANGLE_FIXED = 46;

	/** Locus */
	public static final int MODE_LOCUS = 47;
	/** Macro */
	public static final int MODE_MACRO = 48;

	/** Area */
	public static final int MODE_AREA = 49;

	/** Slope */
	public static final int MODE_SLOPE = 50;

	/** Regular Polygon */
	public static final int MODE_REGULAR_POLYGON = 51;

	/** Check Box to Show / Hide Objects */
	public static final int MODE_SHOW_HIDE_CHECKBOX = 52;
	// GeoGebra 3.2 start
	/** Compass */
	public static final int MODE_COMPASSES = 53;

	/** Reflect Object about Circle */
	public static final int MODE_MIRROR_AT_CIRCLE = 54;

	/** Ellipse */
	public static final int MODE_ELLIPSE_THREE_POINTS = 55;

	/** Hyperbola */
	public static final int MODE_HYPERBOLA_THREE_POINTS = 56;

	/** Parabola */
	public static final int MODE_PARABOLA = 57;

	/** Best Fit Line */
	public static final int MODE_FITLINE = 58;

	/** Record to Spreadsheet removed from GeoGebra 5 */
	// public static final int MODE_RECORD_TO_SPREADSHEET = 59;
	// GeoGebra 4.0 start
	/** Insert Button */
	public static final int MODE_BUTTON_ACTION = 60;

	/** Insert Input Box */
	public static final int MODE_TEXTFIELD_ACTION = 61;

	/** Pen Tool */
	public static final int MODE_PEN = 62;

	/** Visual Style, removed */
	// public static final int MODE_VISUAL_STYLE = 63;

	/** Rigid Polygon */
	public static final int MODE_RIGID_POLYGON = 64;

	/** PolyLine between Points */
	public static final int MODE_POLYLINE = 65;

	/** Probability Calculator */
	public static final int MODE_PROBABILITY_CALCULATOR = 66;

	/** Attach / Detach Point */
	public static final int MODE_ATTACH_DETACH = 67;

	/** Function Inspector */
	public static final int MODE_FUNCTION_INSPECTOR = 68;

	/** Intersect Two Surfaces */
	public static final int MODE_INTERSECTION_CURVE = 69;

	/** Vector Polygon */
	public static final int MODE_VECTOR_POLYGON = 70;

	/** Create List */
	public static final int MODE_CREATE_LIST = 71;

	/** Complex Number */
	public static final int MODE_COMPLEX_NUMBER = 72;

	/** Freehand */
	public static final int MODE_FREEHAND_SHAPE = 73;

	/** Freehand function */
	public static final int MODE_FREEHAND_FUNCTION = 74;

	/** Extremum */
	public static final int MODE_EXTREMUM = 75;

	/** Roots */
	public static final int MODE_ROOTS = 76;
	
	/** Select multiple objects */
	public static final int MODE_SELECT = 77;

	/** */
	public static final int MODE_SELECT_MOW = 78;

	/** Graspable Math tool */
	public static final int MODE_GRASPABLE_MATH = 79;

	/** CAS media panel*/
	public static final int MODE_CAS = 80;

	/** Point on Object */
	public static final int MODE_POINT_ON_OBJECT = 501;

	// ggb3D start

	/** mode that change the view to be in front of selected plane */
	public static final int MODE_VIEW_IN_FRONT_OF = 502;

	/** creates a plane through three points */
	public static final int MODE_PLANE_THREE_POINTS = 510;

	/** creates a plane through three points */
	public static final int MODE_PLANE = 511;

	/** creates a plane orthogonal to a line */
	public static final int MODE_ORTHOGONAL_PLANE = 512;

	/** creates a plane parallel to another */
	public static final int MODE_PARALLEL_PLANE = 513;

	/** Perpendicular Line (for 3D view) */
	public static final int MODE_ORTHOGONAL_THREE_D = 514;

	/** creates a sphere with midpoint and radius */
	public static final int MODE_SPHERE_POINT_RADIUS = 520;

	/** creates a sphere with midpoint through another point */
	public static final int MODE_SPHERE_TWO_POINTS = 521;

	/**
	 * creates a cone with center of basis, apex point and radius of the basis
	 */
	public static final int MODE_CONE_TWO_POINTS_RADIUS = 522;

	/**
	 * creates a cylinder with center of basis, apex point and radius of the
	 * basis
	 */
	public static final int MODE_CYLINDER_TWO_POINTS_RADIUS = 523;

	/**
	 * creates a prism with basis and first vertex of the second parallel face
	 */
	public static final int MODE_PRISM = 531;

	/** creates a prism/cylinder with basis and height */
	public static final int MODE_EXTRUSION = 532;

	/** creates a prism with basis and top vertex */
	public static final int MODE_PYRAMID = 533;

	/** creates a pyramid/cone with basis and height */
	public static final int MODE_CONIFY = 534;

	/** polyhedronNet */
	public static final int MODE_NET = 535;

	/** creates a cube */
	public static final int MODE_CUBE = 536;

	/** creates a tetrahedron */
	public static final int MODE_TETRAHEDRON = 537;

	/** creates a surface by revolving a line around x-axis */
	public static final int MODE_SURFACE_OF_REVOLUTION = 538;

	/** rotate the view */
	public static final int MODE_ROTATEVIEW = 540;

	/** circle with center, radius, direction */
	public static final int MODE_CIRCLE_POINT_RADIUS_DIRECTION = 550;

	/** circle with center, radius, direction */
	public static final int MODE_CIRCLE_AXIS_POINT = 551;

	/** volume */
	public static final int MODE_VOLUME = 560;

	/** Rotate Object around Axis by Angle */
	public static final int MODE_ROTATE_AROUND_LINE = 570;

	/** Reflect Object about Plane */
	public static final int MODE_MIRROR_AT_PLANE = 571;

	// CAS view modes
	/** Evaluate */
	public static final int MODE_CAS_EVALUATE = 1001;
	/** Numeric */
	public static final int MODE_CAS_NUMERIC = 1002;
	/** Keep Input */
	public static final int MODE_CAS_KEEP_INPUT = 1003;
	/** Expand */
	public static final int MODE_CAS_EXPAND = 1004;
	/** Factor */
	public static final int MODE_CAS_FACTOR = 1005;
	/** Substitute */
	public static final int MODE_CAS_SUBSTITUTE = 1006;
	/** Solve */
	public static final int MODE_CAS_SOLVE = 1007;
	/** Derivative */
	public static final int MODE_CAS_DERIVATIVE = 1008;
	/** Integral */
	public static final int MODE_CAS_INTEGRAL = 1009;
	/** Solve Numerically */
	public static final int MODE_CAS_NUMERICAL_SOLVE = 1010;
	// SpreadsheetView modes
	/** Create List */
	public static final int MODE_SPREADSHEET_CREATE_LIST = 2001;
	/** Create Matrix */
	public static final int MODE_SPREADSHEET_CREATE_MATRIX = 2002;
	/** Create List of Points */
	public static final int MODE_SPREADSHEET_CREATE_LISTOFPOINTS = 2003;
	/** Create Table */
	public static final int MODE_SPREADSHEET_CREATE_TABLETEXT = 2004;
	/** Create PolyLine */
	public static final int MODE_SPREADSHEET_CREATE_POLYLINE = 2005;

	/** One Variable Analysis */
	public static final int MODE_SPREADSHEET_ONEVARSTATS = 2020;
	/** Two Variable Regression Analysis */
	public static final int MODE_SPREADSHEET_TWOVARSTATS = 2021;
	/** Multiple Variable Analysis */
	public static final int MODE_SPREADSHEET_MULTIVARSTATS = 2022;
	/** Sort cells */
	public static final int MODE_SPREADSHEET_SORT = 2030;
	/** Sort cells increasingly */
	public static final int MODE_SPREADSHEET_SORT_AZ = 2031;
	/** Sort cells decreasingly */
	public static final int MODE_SPREADSHEET_SORT_ZA = 2032;

	/** Sum */
	public static final int MODE_SPREADSHEET_SUM = 2040;
	/** Mean */
	public static final int MODE_SPREADSHEET_AVERAGE = 2041;
	/** Count */
	public static final int MODE_SPREADSHEET_COUNT = 2042;
	/** Minimum */
	public static final int MODE_SPREADSHEET_MIN = 2043;
	/** Maximum */
	public static final int MODE_SPREADSHEET_MAX = 2044;

	/** WHITEBOARD TOOLS */
	public static final int MODE_SHAPE_LINE = 101;
	/** Triangle */
	public static final int MODE_SHAPE_TRIANGLE = 102;
	/** Square */
	public static final int MODE_SHAPE_SQUARE = 103;
	/** Rectangle */
	public static final int MODE_SHAPE_RECTANGLE = 104;
	/** Rounded rectangle */
	public static final int MODE_SHAPE_RECTANGLE_ROUND_EDGES = 105;
	/** Regular polygon */
	public static final int MODE_SHAPE_PENTAGON = 106;
	/** Freeform TODO same as normal polygon? */
	public static final int MODE_SHAPE_FREEFORM = 107;
	/** Circle */
	public static final int MODE_SHAPE_CIRCLE = 108;
	/** Ellipse */
	public static final int MODE_SHAPE_ELLIPSE = 109;
	/** Eraser */
	public static final int MODE_ERASER = 110;
	/** Highlighter */
	public static final int MODE_HIGHLIGHTER = 111;
	/** Pen Panel */
	public static final int MODE_PEN_PANEL = 112;
	/** Tools Panel */
	public static final int MODE_TOOLS_PANEL = 113;
	/** Media Panel */
	public static final int MODE_MEDIA_PANEL = 114;
	/** Video */
	public static final int MODE_VIDEO = 115;
	/** Audio */
	public static final int MODE_AUDIO = 116;
	/** Geogebra */
	public static final int MODE_GRAPHING = 117;
	/** Camera */
	public static final int MODE_CAMERA = 118;
	/** PDF tool */
	public static final int MODE_PDF = 119;
	/** Extension embed */
	public static final int MODE_EXTENSION = 120;
	/** Text tool */
	public static final int MODE_MEDIA_TEXT = 121;
	/** Mask */
	public static final int MODE_MASK = 122;
	/** Table */
	public static final int MODE_TABLE = 123;
	/** Equation */
	public static final int MODE_EQUATION = 124;
	/** macro tools ID offset */
	public static final int MACRO_MODE_ID_OFFSET = 100001;
	/** max delay between taps of a doublecklick */
	public static final long DOUBLE_CLICK_DELAY = 300;
	/** ignore drag until this many miliseconds after drag start */
	public static final long DRAGGING_DELAY = 100;
    /** ignore drag until this many miliseconds after drag start, for moving created point along
     * z axis */
    public static final long DRAGGING_DELAY_FOR_MOVING_POINT_ALONG_Z = 200;
	/** default size of delete tool rectangle in pixels */
	public static final int DEFAULT_ERASER_SIZE = 20;
	/** line thickness for pen (mow) */
	public static final int DEFAULT_PEN_SIZE = 5;
	/** min length of input box that allows display of symbol button */
	public static final int SHOW_SYMBOLBUTTON_MINLENGTH = 8;

	/** 13 in older files */
	public static final int DEFAULT_CHECKBOX_SIZE = 26;

	/**
	 * min line thickness of highlighter
	 */
	public static final int MIN_PEN_HIGHLIGHTER_SIZE = 1;
	/**
	 * default step size to increase line thickness of pen/highlighter
	 */
	public static final int DEFAULT_PEN_STEP = 1;
	/**
	 * default opacity of highlighter (0.3)
	 */
	public static final int DEFAULT_HIGHLIGHTER_OPACITY = 77;
	/**
	 * default line thickness of highlighter
	 */
	public static final int DEFAULT_HIGHLIGHTER_SIZE = 20;
	/**
	 * max line thickness of highlighter
	 */
	public static final int MAX_PEN_HIGHLIGHTER_SIZE = 30;

	/**
	 * @param mode
	 *            mode ID
	 * @return tool name without the .tool suffix
	 */
	public static String getModeTextSimple(int mode) {
		return getModeText(mode).replace(".Tool", "");
	}

	/**
	 * @param mode
	 *            mode number
	 * @return key of the mode description
	 */
	public static String getModeText(int mode) {
		switch (mode) {

		// 3D Modes
		case euclideanConstants.MODE_VIEW_IN_FRONT_OF:
			return "ViewInFrontOf";

		case euclideanConstants.MODE_PLANE_THREE_POINTS:
			return "PlaneThreePoint";

		case euclideanConstants.MODE_PLANE:
			return "Plane.Tool";

		case euclideanConstants.MODE_ORTHOGONAL_PLANE:
			return "OrthogonalPlane";

		case euclideanConstants.MODE_PARALLEL_PLANE:
			return "ParallelPlane";

		case euclideanConstants.MODE_CUBE:
			return "Cube.Tool";

		case euclideanConstants.MODE_TETRAHEDRON:
			return "Tetrahedron";

		case euclideanConstants.MODE_PRISM:
			return "Prism";

		case euclideanConstants.MODE_EXTRUSION:
			return "Extrusion";

		case euclideanConstants.MODE_SURFACE_OF_REVOLUTION:
			return "SurfaceOfRevolution";

		case euclideanConstants.MODE_CONIFY:
			return "Conify";

		case euclideanConstants.MODE_PYRAMID:
			return "Pyramid.Tool";

		case euclideanConstants.MODE_NET:
			return "Net.Tool";

		case euclideanConstants.MODE_SPHERE_POINT_RADIUS:
			return "SpherePointRadius";

		case euclideanConstants.MODE_SPHERE_TWO_POINTS:
			return "Sphere2";

		case euclideanConstants.MODE_CONE_TWO_POINTS_RADIUS:
			return "Cone.Tool";

		case euclideanConstants.MODE_CYLINDER_TWO_POINTS_RADIUS:
			return "Cylinder.Tool";

		case euclideanConstants.MODE_ROTATEVIEW:
			return "RotateView";

		case euclideanConstants.MODE_CIRCLE_POINT_RADIUS_DIRECTION:
			return "CirclePointRadiusDirection";

		case euclideanConstants.MODE_CIRCLE_AXIS_POINT:
			return "CircleAxisPoint";

		case euclideanConstants.MODE_VOLUME:
			return "Volume";

		case euclideanConstants.MODE_MIRROR_AT_PLANE:
			return "MirrorAtPlane";

		case euclideanConstants.MODE_ROTATE_AROUND_LINE:
			return "RotateAroundLine";

		case euclideanConstants.MODE_ORTHOGONAL_THREE_D:
			return "OrthogonalThreeD";

		case euclideanConstants.MODE_SELECTION_LISTENER:
			return "Select";

		case euclideanConstants.MODE_MOVE:
			return "Move";

		case euclideanConstants.MODE_POINT:
			return "Point.Tool";

		case euclideanConstants.MODE_COMPLEX_NUMBER:
			return "ComplexNumber.Tool";

		case euclideanConstants.MODE_POINT_ON_OBJECT:
			return "PointOnObject";

		case euclideanConstants.MODE_JOIN:
			return "Join";

		case euclideanConstants.MODE_SEGMENT:
			return "Segment.Tool";

		case euclideanConstants.MODE_SEGMENT_FIXED:
			return "SegmentFixed";

		case euclideanConstants.MODE_RAY:
			return "Ray.Tool";

		case euclideanConstants.MODE_POLYGON:
			return "Polygon.Tool";

		case euclideanConstants.MODE_POLYLINE:
			return "PolyLine.Tool";

		case euclideanConstants.MODE_RIGID_POLYGON:
			return "RigidPolygon";

		case euclideanConstants.MODE_VECTOR_POLYGON:
			return "VectorPolygon";

		case euclideanConstants.MODE_PARALLEL:
			return "Parallel";

		case euclideanConstants.MODE_ORTHOGONAL:
			return "Orthogonal";

		case euclideanConstants.MODE_INTERSECT:
			return "Intersect";

		case euclideanConstants.MODE_INTERSECTION_CURVE:
			return "IntersectionCurve";

		case euclideanConstants.MODE_LINE_BISECTOR:
			return "LineBisector";

		case euclideanConstants.MODE_ANGULAR_BISECTOR:
			return "AngularBisector";

		case euclideanConstants.MODE_TANGENTS:
			return "Tangent";

		case euclideanConstants.MODE_POLAR_DIAMETER:
			return "PolarDiameter";

		case euclideanConstants.MODE_CIRCLE_TWO_POINTS:
			return "Circle2";

		case euclideanConstants.MODE_CIRCLE_THREE_POINTS:
			return "Circle3";

		case euclideanConstants.MODE_ELLIPSE_THREE_POINTS:
			return "Ellipse3";

		case euclideanConstants.MODE_PARABOLA:
			return "Parabola.Tool";

		case euclideanConstants.MODE_HYPERBOLA_THREE_POINTS:
			return "Hyperbola3";

		// Michael Borcherds 2008-03-13
		case euclideanConstants.MODE_COMPASSES:
			return "Compasses";

		case euclideanConstants.MODE_CONIC_FIVE_POINTS:
			return "Conic5";

		case euclideanConstants.MODE_RELATION:
			return "Relation";

		case euclideanConstants.MODE_TRANSLATEVIEW:
			return "TranslateView";

		case euclideanConstants.MODE_SHOW_HIDE_OBJECT:
			return "ShowHideObject";

		case euclideanConstants.MODE_SHOW_HIDE_LABEL:
			return "ShowHideLabel";

		case euclideanConstants.MODE_COPY_VISUAL_STYLE:
			return "CopyVisualStyle";

		case euclideanConstants.MODE_DELETE:
			return "Delete";

		case euclideanConstants.MODE_VECTOR:
			return "Vector.Tool";

		case euclideanConstants.MODE_TEXT:
		case euclideanConstants.MODE_MEDIA_TEXT:
			return "Text.Tool";

		case euclideanConstants.MODE_IMAGE:
			return "Image.Tool";

		case euclideanConstants.MODE_MIDPOINT:
			return "Midpoint.Tool";

		case euclideanConstants.MODE_SEMICIRCLE:
			return "Semicircle.Tool";

		case euclideanConstants.MODE_CIRCLE_ARC_THREE_POINTS:
			return "CircleArc3";

		case euclideanConstants.MODE_CIRCLE_SECTOR_THREE_POINTS:
			return "CircleSector3";

		case euclideanConstants.MODE_CIRCUMCIRCLE_ARC_THREE_POINTS:
			return "CircumcircleArc3";

		case euclideanConstants.MODE_CIRCUMCIRCLE_SECTOR_THREE_POINTS:
			return "CircumcircleSector3";

		case euclideanConstants.MODE_SLIDER:
			return "Slider.Tool";

		case euclideanConstants.MODE_MIRROR_AT_POINT:
			return "MirrorAtPoint";

		case euclideanConstants.MODE_MIRROR_AT_LINE:
			return "MirrorAtLine";

		case euclideanConstants.MODE_MIRROR_AT_CIRCLE:
			return "MirrorAtCircle";

		case euclideanConstants.MODE_TRANSLATE_BY_VECTOR:
			return "TranslateByVector";

		case euclideanConstants.MODE_ROTATE_BY_ANGLE:
			return "RotateByAngle";

		case euclideanConstants.MODE_DILATE_FROM_POINT:
			return "DilateFromPoint";

		case euclideanConstants.MODE_CIRCLE_POINT_RADIUS:
			return "CirclePointRadius";

		case euclideanConstants.MODE_ANGLE:
			return "Angle.Tool";

		case euclideanConstants.MODE_ANGLE_FIXED:
			return "AngleFixed";

		case euclideanConstants.MODE_VECTOR_FROM_POINT:
			return "VectorFromPoint";

		case euclideanConstants.MODE_DISTANCE:
			return "Distance";

		case euclideanConstants.MODE_MOVE_ROTATE:
			return "MoveRotate";

		case euclideanConstants.MODE_ZOOM_IN:
			return "ZoomIn.Tool";

		case euclideanConstants.MODE_ZOOM_OUT:
			return "ZoomOut.Tool";

		case euclideanConstants.MODE_LOCUS:
			return "Locus.Tool";

		case euclideanConstants.MODE_AREA:
			return "Area";

		case euclideanConstants.MODE_SLOPE:
			return "Slope";

		case euclideanConstants.MODE_REGULAR_POLYGON:
			return "RegularPolygon";

		case euclideanConstants.MODE_SHOW_HIDE_CHECKBOX:
			return "ShowCheckBox";

		case euclideanConstants.MODE_BUTTON_ACTION:
			return "ButtonAction";

		case euclideanConstants.MODE_TEXTFIELD_ACTION:
			return "TextFieldAction";

		case euclideanConstants.MODE_PEN:
			return "Pen";

		// case euclideanConstants.MODE_PENCIL:
		// return "Pencil";

		case euclideanConstants.MODE_FREEHAND_SHAPE:
			return "FreehandShape";

		case euclideanConstants.MODE_FREEHAND_FUNCTION:
			return "FreehandFunction";

		// case euclideanConstants.MODE_VISUAL_STYLE:
		// return "VisualStyle";

		case euclideanConstants.MODE_FITLINE:
			return "FitLine";

		case euclideanConstants.MODE_CREATE_LIST:
			return "CreateList";

		case euclideanConstants.MODE_PROBABILITY_CALCULATOR:
			return "ProbabilityCalculator";

		case euclideanConstants.MODE_FUNCTION_INSPECTOR:
			return "FunctionInspector";

		// CAS
		case euclideanConstants.MODE_CAS_EVALUATE:
			return "Evaluate";

		case euclideanConstants.MODE_CAS_NUMERIC:
			return "Numeric.Tool";

		case euclideanConstants.MODE_CAS_KEEP_INPUT:
			return "KeepInput";

		case euclideanConstants.MODE_CAS_EXPAND:
			return "Expand";

		case euclideanConstants.MODE_CAS_FACTOR:
			return "Factor";

		case euclideanConstants.MODE_CAS_SUBSTITUTE:
			return "Substitute.Tool";

		case euclideanConstants.MODE_CAS_SOLVE:
			return "Solve";

		case euclideanConstants.MODE_CAS_NUMERICAL_SOLVE:
			return "NSolve";
		case euclideanConstants.MODE_CAS_DERIVATIVE:
			return "Derivative";

		case euclideanConstants.MODE_CAS_INTEGRAL:
			return "Integral";

		case euclideanConstants.MODE_ATTACH_DETACH:
			return "AttachDetachPoint";

		// Spreadsheet
		case euclideanConstants.MODE_SPREADSHEET_ONEVARSTATS:
			return "OneVarStats";

		case euclideanConstants.MODE_SPREADSHEET_TWOVARSTATS:
			return "TwoVarStats";

		case euclideanConstants.MODE_SPREADSHEET_MULTIVARSTATS:
			return "MultiVarStats";

		case euclideanConstants.MODE_SPREADSHEET_CREATE_LIST:
			return "CreateList";

		case euclideanConstants.MODE_SPREADSHEET_CREATE_LISTOFPOINTS:
			return "CreateListOfPoints";

		case euclideanConstants.MODE_SPREADSHEET_CREATE_MATRIX:
			return "CreateMatrix";

		case euclideanConstants.MODE_SPREADSHEET_CREATE_TABLETEXT:
			return "CreateTable";

		case euclideanConstants.MODE_SPREADSHEET_CREATE_POLYLINE:
			return "CreatePolyLine";

		case euclideanConstants.MODE_SPREADSHEET_SUM:
			return "SumCells";

		case euclideanConstants.MODE_SPREADSHEET_AVERAGE:
			return "MeanCells";

		case euclideanConstants.MODE_SPREADSHEET_COUNT:
			return "CountCells";

		case euclideanConstants.MODE_SPREADSHEET_MIN:
			return "MinCells";

		case euclideanConstants.MODE_SPREADSHEET_MAX:
			return "MaxCells";

		case euclideanConstants.MODE_EXTREMUM:
			return "Extremum";

		case euclideanConstants.MODE_ROOTS:
			return "Roots";

		/** WHITEBOARD TOOLS */
		case euclideanConstants.MODE_SHAPE_LINE:
			return "Line";
		case euclideanConstants.MODE_SHAPE_TRIANGLE:
			return "ShapeTriangle";
		case euclideanConstants.MODE_SHAPE_SQUARE:
			return "ShapeSquare";
		case euclideanConstants.MODE_SHAPE_RECTANGLE:
			return "ShapeRectangle";
		case euclideanConstants.MODE_SHAPE_RECTANGLE_ROUND_EDGES:
			return "ShapeRoundedRectangle";
		case euclideanConstants.MODE_SHAPE_PENTAGON:
			return "Pentagon";
		case euclideanConstants.MODE_SHAPE_FREEFORM:
			return "ShapeFreeform";
		case euclideanConstants.MODE_SHAPE_CIRCLE:
			return "Circle";
		case euclideanConstants.MODE_SHAPE_ELLIPSE:
			return "Ellipse";
		case euclideanConstants.MODE_MASK:
			return "MaskTool";
		case euclideanConstants.MODE_ERASER:
			return "Eraser";
		case euclideanConstants.MODE_HIGHLIGHTER:
			return "Highlighter";

		case euclideanConstants.MODE_PEN_PANEL:
			return "PenPanel";
		case euclideanConstants.MODE_TOOLS_PANEL:
			return "ToolsPanel";
		case euclideanConstants.MODE_MEDIA_PANEL:
			return "MediaPanel";
		case euclideanConstants.MODE_VIDEO:
			return "Video";
		case euclideanConstants.MODE_CAMERA:
			return "Camera";
		case euclideanConstants.MODE_AUDIO:
			return "Audio";
		case euclideanConstants.MODE_GRAPHING:
			return "Graphing";
		case euclideanConstants.MODE_CAS:
				return "CAS";
        case euclideanConstants.MODE_GRASPABLE_MATH:
            return "Graspable Math";
		case euclideanConstants.MODE_PDF:
			return "PDF";
		case euclideanConstants.MODE_EXTENSION:
			return "Web";
		case euclideanConstants.MODE_SELECT:
			return "Select";
		case euclideanConstants.MODE_SELECT_MOW:
			return "Select";
		case euclideanConstants.MODE_TABLE:
			return "Table";
		case euclideanConstants.MODE_EQUATION:
			return "Equation";

		default:
			Log.error("Unknown mode " + mode);
			return "";
		}
	}
	
	/**
	 * @param mode
	 *            mode number
	 * @return whether mode is MOVE or SELECT
	 */
	static public boolean isMoveOrSelectionMode(int mode) {
		return mode == MODE_MOVE || mode == MODE_SELECT
				|| mode == MODE_SELECT_MOW;
	}

	/**
	 * @param mode
	 *            tool id
	 * @return true if tool id is shape
	 */
	static public boolean isShapeMode(int mode) {
		return mode == MODE_SHAPE_FREEFORM || mode == MODE_SHAPE_SQUARE
				|| mode == MODE_SHAPE_CIRCLE || mode == MODE_SHAPE_ELLIPSE
				|| mode == MODE_SHAPE_PENTAGON || mode == MODE_SHAPE_RECTANGLE
				|| mode == MODE_SHAPE_TRIANGLE || mode == MODE_SHAPE_LINE;
	}

	/**
	 * @param mode mode number
	 * @return true if mode does NOT clear selection when set
	 */
	static public boolean keepSelectionWhenSet(int mode) {
		return isMoveOrSelectionMode(mode)
				|| (mode == MODE_SHOW_HIDE_OBJECT
				|| mode == MODE_SHOW_HIDE_LABEL
				|| mode == MODE_DELETE);
	}

	/**
	 * @param mode
	 *            mode number
	 * @param draggingOccured
	 *            tells if dragging occured
	 *
	 * @return whether mode is MOVE or SELECT, and return false if the mode is
	 *         not compatible with dragging occured
	 */
	static public boolean isMoveOrSelectionModeCompatibleWithDragging(int mode,
			boolean draggingOccured) {
		switch (mode) {
		case MODE_MOVE:
			return !draggingOccured;
		case MODE_SELECT:
		case MODE_SELECT_MOW:
			return true;
		default:
			return false;
		}
	}

	// prevent instantiation
	private euclideanConstants() {
	}

}
