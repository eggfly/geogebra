/* 
 GeoGebra - Dynamic Mathematics for Everyone
 http://www.geogebra.org

 This file is part of GeoGebra.

 This program is free software; you can redistribute it and/or modify it 
 under the terms of the GNU General Public License as published by 
 the Free Software Foundation.

 */

package org.geogebra.web.geogebra3D.web.euclideanFor3D;

import org.geogebra.common.euclidean.euclideanControllerCompanion;
import org.geogebra.common.geogebra3D.euclideanFor3D.euclideanControllerFor3DCompanion;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.web.html5.euclidean.euclideanControllerW;

/**
 * euclidean controller for 2D view with 3D geos
 */
public class euclideanControllerFor3DW extends euclideanControllerW {

	/**
	 * @param kernel
	 *            kernel
	 */
	public euclideanControllerFor3DW(Kernel kernel) {
		super(kernel);
	}

	@Override
	protected euclideanControllerCompanion newCompanion() {
		return new euclideanControllerFor3DCompanion(this);
	}

}
