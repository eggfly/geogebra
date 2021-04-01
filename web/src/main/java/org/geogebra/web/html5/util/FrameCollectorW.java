package org.geogebra.web.html5.util;

import org.geogebra.common.main.App.ExportType;
import org.geogebra.web.html5.euclidean.euclideanViewWInterface;

public interface FrameCollectorW {

	String finish(int width, int height);

	void addFrame(euclideanViewWInterface ev, double scale,
			ExportType format);

}
