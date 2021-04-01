package org.geogebra.desktop.euclidean;

import org.geogebra.common.euclidean.ScreenReaderAdapter;
import org.geogebra.common.util.debug.Log;

public class ScreenReaderAdapterD implements ScreenReaderAdapter {

	@Override
	public void readText(String text) {
		Log.read("Reading text: " + text);
	}

	@Override
	public void readDelayed(String text) {
		readText(text);
	}
}
