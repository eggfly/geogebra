package org.geogebra.web.html5.euclidean;

import org.geogebra.common.euclidean.event.AbstractEvent;
import org.geogebra.common.euclidean.event.PointerEventType;
import org.geogebra.common.kernel.ModeSetter;
import org.geogebra.web.html5.event.PointerEvent;
import org.geogebra.web.html5.gui.util.LongTouchManager;
import org.geogebra.web.html5.gui.util.LongTouchTimer.LongTouchHandler;

public interface IseuclideanController extends LongTouchHandler {

	void setExternalHandling(boolean b);

	void twoTouchStart(double x1, double y1, double x2, double y2);

	void setDefaultEventType(PointerEventType pointerEventType,
			boolean pointerDown);

	void twoTouchMove(double x1, double y1, double x2, double y2);

	int getEvNo();

	LongTouchManager getLongTouchManager();

	void setActualSticky(boolean b);

	boolean isDraggingBeyondThreshold();

	int getMode();

	void setMode(int i, ModeSetter ms);

	void onPointerEventStart(AbstractEvent e);

	void onPointerEventMove(PointerEvent e);

	void onPointerEventEnd(PointerEvent e);

	MouseTouchGestureControllerW getOffsets();

	EnvironmentStyleW getEnvironmentStyle();
}
