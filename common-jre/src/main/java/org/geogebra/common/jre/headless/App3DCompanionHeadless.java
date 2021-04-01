package org.geogebra.common.jre.headless;

import org.geogebra.common.geogebra3D.euclideanForPlane.euclideanViewForPlaneCompanion;
import org.geogebra.common.geogebra3D.main.App3DCompanion;
import org.geogebra.common.gui.layout.DockPanel;
import org.geogebra.common.kernel.kernelND.ViewCreator;
import org.geogebra.common.main.App;
import org.geogebra.common.main.settings.euclideanSettings;

public final class App3DCompanionHeadless extends App3DCompanion {
	public App3DCompanionHeadless(App app) {
		super(app);
	}

	@Override
	protected euclideanViewForPlaneCompanion createeuclideanViewForPlane(
			ViewCreator plane, euclideanSettings evSettings,
			boolean panelSettings) {
		return null;
	}

	@Override
	public DockPanel getPanelForPlane() {
		return null;
	}
}