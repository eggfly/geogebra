package org.geogebra.common.geogebra3D.euclidean3D;

import java.util.HashMap;

import org.geogebra.common.euclidean.euclideanConstants;
import org.geogebra.common.geogebra3D.kernel3D.ConstructionDefaults3D;
import org.geogebra.common.kernel.ConstructionDefaults;

/**
 * static methods used in desktop / web for 3D style bar
 * 
 * @author mathieu
 *
 */
public class euclideanStyleBarStatic3D {

	/**
	 * fill map for 3D
	 * 
	 * @param defaultGeoMap
	 *            map already filled for 2D
	 */
	public static void addToDefaultMap(
			HashMap<Integer, Integer> defaultGeoMap) {

		// lines
		defaultGeoMap.put(euclideanConstants.MODE_ORTHOGONAL_THREE_D,
				ConstructionDefaults.DEFAULT_LINE);

		// conics
		defaultGeoMap.put(euclideanConstants.MODE_CIRCLE_AXIS_POINT,
				ConstructionDefaults.DEFAULT_CONIC);
		defaultGeoMap.put(euclideanConstants.MODE_CIRCLE_POINT_RADIUS_DIRECTION,
				ConstructionDefaults.DEFAULT_CONIC);

		// intersection curve
		defaultGeoMap.put(euclideanConstants.MODE_INTERSECTION_CURVE,
				ConstructionDefaults3D.DEFAULT_INTERSECTION_CURVE);

		// planes
		defaultGeoMap.put(euclideanConstants.MODE_PLANE_THREE_POINTS,
				ConstructionDefaults3D.DEFAULT_PLANE3D);
		defaultGeoMap.put(euclideanConstants.MODE_PLANE,
				ConstructionDefaults3D.DEFAULT_PLANE3D);
		defaultGeoMap.put(euclideanConstants.MODE_ORTHOGONAL_PLANE,
				ConstructionDefaults3D.DEFAULT_PLANE3D);
		defaultGeoMap.put(euclideanConstants.MODE_PARALLEL_PLANE,
				ConstructionDefaults3D.DEFAULT_PLANE3D);

		// spheres
		defaultGeoMap.put(euclideanConstants.MODE_SPHERE_POINT_RADIUS,
				ConstructionDefaults3D.DEFAULT_QUADRIC);
		defaultGeoMap.put(euclideanConstants.MODE_SPHERE_TWO_POINTS,
				ConstructionDefaults3D.DEFAULT_QUADRIC);

		// cylinders, cones
		defaultGeoMap.put(euclideanConstants.MODE_CONE_TWO_POINTS_RADIUS,
				ConstructionDefaults.DEFAULT_PYRAMID_AND_CONE);
		defaultGeoMap.put(euclideanConstants.MODE_CYLINDER_TWO_POINTS_RADIUS,
				ConstructionDefaults.DEFAULT_PRISM_AND_CYLINDER);
		defaultGeoMap.put(euclideanConstants.MODE_EXTRUSION,
				ConstructionDefaults.DEFAULT_PRISM_AND_CYLINDER);
		defaultGeoMap.put(euclideanConstants.MODE_CONIFY,
				ConstructionDefaults.DEFAULT_PYRAMID_AND_CONE);

		// polyhedrons
		defaultGeoMap.put(euclideanConstants.MODE_PYRAMID,
				ConstructionDefaults.DEFAULT_PYRAMID_AND_CONE);
		defaultGeoMap.put(euclideanConstants.MODE_PRISM,
				ConstructionDefaults.DEFAULT_PRISM_AND_CYLINDER);
		defaultGeoMap.put(euclideanConstants.MODE_TETRAHEDRON,
				ConstructionDefaults.DEFAULT_PYRAMID_AND_CONE);
		defaultGeoMap.put(euclideanConstants.MODE_CUBE,
				ConstructionDefaults.DEFAULT_POLYHEDRON);

		// net
		defaultGeoMap.put(euclideanConstants.MODE_NET,
				ConstructionDefaults3D.DEFAULT_NET);

	}
}
