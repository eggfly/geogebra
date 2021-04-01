package org.geogebra.common.geogebra3D.euclidean3D.draw;

import org.geogebra.common.geogebra3D.euclidean3D.euclideanView3D;
import org.geogebra.common.geogebra3D.kernel3D.geos.GeoLine3D;
import org.geogebra.common.geogebra3D.kernel3D.geos.GeoQuadric3D;
import org.geogebra.common.kernel.geos.GeoElement;

/**
 * Draw line for quadrics
 * 
 * @author mathieu
 *
 */
public class DrawLine3DForQuadrics extends DrawLine3D {

	private GeoQuadric3D quadric;

	/**
	 * @param a_view3D
	 *            view
	 * @param line
	 *            line
	 * @param quadric
	 *            quadric
	 */
	public DrawLine3DForQuadrics(euclideanView3D a_view3D, GeoLine3D line,
			GeoQuadric3D quadric) {
		super(a_view3D, line, quadric);
	}

	@Override
	protected void init(GeoElement line, GeoElement geo2) {

		super.init(line);
		this.quadric = (GeoQuadric3D) geo2;

	}

	@Override
	public GeoElement getGeoElement() {
		return quadric;
	}

	@Override
	protected GeoLine3D getLine() {
		return (GeoLine3D) super.getGeoElement();
	}

}
