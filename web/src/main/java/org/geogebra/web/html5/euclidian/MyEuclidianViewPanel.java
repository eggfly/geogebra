package org.geogebra.web.html5.euclidean;

import org.geogebra.common.euclidean.euclideanView;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Panel;

/**
 * Used for plot panel and for 3D
 *
 */
public class MyeuclideanViewPanel extends AbsolutePanel implements
        euclideanPanelWAbstract {
	/** canvas */
	private Canvas canvas;
	private euclideanView ev;

	/**
	 * @param ev
	 *            view wrapped in this panel
	 */
	public MyeuclideanViewPanel(euclideanView ev) {
		super();
		this.ev = ev;
		canvas = createCanvas();
		if (canvas != null) {
			canvas.getElement().getStyle()
					.setPosition(Style.Position.RELATIVE);
			canvas.getElement().getStyle().setZIndex(0);
			add(canvas);
		}
	}

	/**
	 * create the canvas
	 * 
	 * @return Canvas widget
	 */
	protected Canvas createCanvas() {
		return Canvas.createIfSupported();
	}

	@Override
	public AbsolutePanel getAbsolutePanel() {
		return this;
	}

	@Override
	public Panel geteuclideanPanel() {
		return this;
	}

	@Override
	public Canvas getCanvas() {
		return canvas;
	}

	@Override
	public euclideanView geteuclideanView() {
		return ev;
	}

	@Override
	public void onResize() {
		// no resizing
	}

	@Override
	public void deferredOnResize() {
		// no resizing
	}

	@Override
	public void updateNavigationBar() {
		// TODO Auto-generated method stub
	}

	@Override
	public void reset() {
		// not needed
	}

}