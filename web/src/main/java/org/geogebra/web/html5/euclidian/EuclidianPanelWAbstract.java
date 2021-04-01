package org.geogebra.web.html5.euclidean;

import org.geogebra.common.euclidean.euclideanView;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Panel;

public interface euclideanPanelWAbstract {

	AbsolutePanel getAbsolutePanel();

	Panel geteuclideanPanel();

	Canvas getCanvas();

	euclideanView geteuclideanView();

	void setPixelSize(int x, int y);

	int getOffsetWidth();

	int getOffsetHeight();

	void onResize();

	void deferredOnResize();

	void updateNavigationBar();

	Element getElement();

	void reset();

	boolean isAttached();

}
