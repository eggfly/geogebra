package org.geogebra.common.euclidean;

import static org.junit.Assert.assertArrayEquals;

import org.geogebra.common.kernel.geos.GeoElement;
import org.junit.Test;

public class NotesSelectionTest extends BaseControllerTest {

	@Test
	public void selectionRectangleShouldSelectPartOfStroke() {
		setMode(euclideanConstants.MODE_PEN);
		dragStart(100, 100);
		dragEnd(200, 100);
		setMode(euclideanConstants.MODE_SELECT_MOW);
		dragStart(150, 50);
		dragEnd(250, 150);
		assertSelected(lookup("stroke1"));
	}

	@Test
	public void selectionRectangleShouldSelectPartOfMoreStrokes() {
		setMode(euclideanConstants.MODE_PEN);
		dragStart(100, 100);
		dragEnd(200, 100);
		setMode(euclideanConstants.MODE_SELECT_MOW);
		setMode(euclideanConstants.MODE_PEN);
		dragStart(220, 100);
		dragEnd(300, 100);
		setMode(euclideanConstants.MODE_SELECT_MOW);
		dragStart(150, 50);
		dragEnd(250, 150);
		assertSelected(lookup("stroke1"), lookup("stroke2"));
	}

	private void assertSelected(GeoElement... geos) {
		assertArrayEquals(getApp().getSelectionManager().getSelectedGeos().toArray(),
				geos);
	}
}
