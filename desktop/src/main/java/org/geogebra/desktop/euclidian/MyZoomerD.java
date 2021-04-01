package org.geogebra.desktop.euclidean;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.geogebra.common.euclidean.CoordSystemAnimation;
import org.geogebra.common.euclidean.euclideanView;

public class MyZoomerD extends CoordSystemAnimation implements ActionListener {
	protected Timer timer; // for animation

	public MyZoomerD(euclideanView view) {
		super(view);
		timer = new Timer(DELAY, this);
	}

	@Override
	protected void stopTimer() {
		timer.stop();
	}

	@Override
	protected boolean hasTimer() {
		return timer != null;
	}

	@Override
	public synchronized void actionPerformed(ActionEvent e) {
		step();
	}

	@Override
	protected void startTimer() {
		timer.start();
	}

}