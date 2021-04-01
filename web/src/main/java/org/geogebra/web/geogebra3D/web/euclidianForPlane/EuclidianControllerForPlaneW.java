/* 
 GeoGebra - Dynamic Mathematics for Everyone
 http://www.geogebra.org

 This file is part of GeoGebra.

 This program is free software; you can redistribute it and/or modify it 
 under the terms of the GNU General Public License as published by 
 the Free Software Foundation.

 */

package org.geogebra.web.geogebra3D.web.euclideanForPlane;

import org.geogebra.common.euclidean.euclideanControllerCompanion;
import org.geogebra.common.geogebra3D.euclideanForPlane.euclideanControllerForPlaneCompanion;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.web.html5.euclidean.euclideanControllerW;

/**
 * controller for view for plane
 */
public class euclideanControllerForPlaneW extends euclideanControllerW {

	/**
	 * constructor
	 * 
	 * @param kernel
	 *            kernel
	 */
	public euclideanControllerForPlaneW(Kernel kernel) {
		super(kernel);
	}

	@Override
	protected euclideanControllerCompanion newCompanion() {
		return new euclideanControllerForPlaneCompanion(this);
	}
}
