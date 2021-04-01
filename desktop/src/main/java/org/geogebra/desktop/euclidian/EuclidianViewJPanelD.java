package org.geogebra.desktop.euclidean;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.euclidean.euclideanViewJPanel;
import org.geogebra.desktop.awt.GGraphics2DD;
import org.geogebra.desktop.awt.GRectangleD;

public class euclideanViewJPanelD extends JPanel
		implements euclideanViewJPanel {

	private static final long serialVersionUID = 1L;

	euclideanView view;

	public euclideanViewJPanelD(euclideanView view, boolean addListeners) {
		this.view = view;

		// algebra controller will take care of our key events
		setFocusable(true);

		setLayout(null);
		setMinimumSize(new Dimension(20, 20));

		// register Listener
		if (addListeners) {
			((euclideanControllerListeners) view.geteuclideanController())
					.addListenersTo(this);
		}

		// enable drop transfers
		setTransferHandler(new euclideanViewTransferHandler(view));
	}

	public euclideanViewJPanelD(euclideanView view) {

		this(view, true);

	}

	protected Color bgColor;

	@Override
	public Color getBackground() {
		return bgColor;
	}

	@Override
	public void setBackground(Color bgColor) {
		if (bgColor != null) {
			this.bgColor = bgColor;
		}
	}

	@Override
	public void paintChildren(Graphics g) {
		super.paintChildren(g);
	}

	@Override
	public Rectangle getBounds() {
		return GRectangleD.getAWTRectangle(view.getBounds());
	}

	@Override
	public void setToolTipText(String plain) {
		super.setToolTipText(plain);
	}

	private GGraphics2DD g2 = new GGraphics2DD(null);

	@Override
	final public void paint(Graphics g) {
		g2.setImpl((Graphics2D) g);
		view.paint(g2);
	}

	public void processMouseEventImpl(MouseEvent e) {
		processMouseEvent(e);
	}
}
