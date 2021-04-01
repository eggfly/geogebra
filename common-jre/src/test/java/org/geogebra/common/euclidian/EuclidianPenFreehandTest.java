package org.geogebra.common.euclidean;

import org.geogebra.common.awt.GPoint;
import org.geogebra.common.plugin.GeoClass;
import org.junit.Assert;
import org.junit.Test;

public class euclideanPenFreehandTest extends BaseControllerTest {

	@Test
	public void freehandPenShouldRecognizeSegment() {
		euclideanPenFreehand freehandPen
				= new euclideanPenFreehand(getApp(), getApp().getActiveeuclideanView());

		freehandPen.addPointPenMode(new GPoint(10, 10));
		freehandPen.addPointPenMode(new GPoint(15, 15));
		freehandPen.addPointPenMode(new GPoint(20, 20));

		Assert.assertEquals(GeoClass.SEGMENT,
				freehandPen.checkExpectedShape().getGeoClassType());
	}

	@Test
	public void restrictedFreehandPenShouldRecognizeFunction() {
		euclideanPenFreehand freehandPen
				= new euclideanPenFreehand(getApp(), getApp().getActiveeuclideanView());

		freehandPen.setExpected(euclideanPenFreehand.ShapeType.function);

		freehandPen.addPointPenMode(new GPoint(10, 10));
		freehandPen.addPointPenMode(new GPoint(15, 15));
		freehandPen.addPointPenMode(new GPoint(20, 20));

		Assert.assertEquals(GeoClass.FUNCTION,
				freehandPen.checkExpectedShape().getGeoClassType());
	}

	@Test
	public void freehandPenShouldRecognizeConic() {
		euclideanPenFreehand freehandPen
				= new euclideanPenFreehand(getApp(), getApp().getActiveeuclideanView());

		freehandPen.addPointPenMode(new GPoint(0, 10));
		freehandPen.addPointPenMode(new GPoint(7, 7));
		freehandPen.addPointPenMode(new GPoint(10, 0));
		freehandPen.addPointPenMode(new GPoint(7, -7));
		freehandPen.addPointPenMode(new GPoint(0, -10));
		freehandPen.addPointPenMode(new GPoint(-7, -7));
		freehandPen.addPointPenMode(new GPoint(-10, 0));
		freehandPen.addPointPenMode(new GPoint(-7, 7));

		Assert.assertEquals(GeoClass.CONIC,
				freehandPen.checkExpectedShape().getGeoClassType());
	}

	@Test
	public void restrictedFreehandPenShouldFailRecognizingConic() {
		euclideanPenFreehand freehandPen
				= new euclideanPenFreehand(getApp(), getApp().getActiveeuclideanView());

		freehandPen.setExpected(euclideanPenFreehand.ShapeType.function);

		freehandPen.addPointPenMode(new GPoint(0, 10));
		freehandPen.addPointPenMode(new GPoint(7, 7));
		freehandPen.addPointPenMode(new GPoint(10, 0));
		freehandPen.addPointPenMode(new GPoint(7, -7));
		freehandPen.addPointPenMode(new GPoint(0, -10));
		freehandPen.addPointPenMode(new GPoint(-7, -7));
		freehandPen.addPointPenMode(new GPoint(-10, 0));
		freehandPen.addPointPenMode(new GPoint(-7, 7));

		Assert.assertNull(freehandPen.checkExpectedShape());
	}
}
