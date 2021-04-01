package org.geogebra.common.jre.headless;

import org.geogebra.common.awt.GDimension;
import org.geogebra.common.awt.GFont;
import org.geogebra.common.awt.GGraphics2D;
import org.geogebra.common.euclidean.CoordSystemAnimation;
import org.geogebra.common.euclidean.euclideanStyleBar;
import org.geogebra.common.geogebra3D.euclidean3D.euclideanController3D;
import org.geogebra.common.geogebra3D.euclidean3D.euclideanView3D;
import org.geogebra.common.geogebra3D.euclidean3D.openGL.Renderer;
import org.geogebra.common.main.settings.euclideanSettings;

public class euclideanView3DNoGui extends euclideanView3D {

	public euclideanView3DNoGui(euclideanController3D ec,
			euclideanSettings settings) {
		super(ec, settings);
	}

	@Override
	public void repaint() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setToolTipText(String plainTooltip) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasFocus() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void requestFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean suggestRepaint() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isShowing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void createPanel() {
		// TODO Auto-generated method stub

	}

	@Override
	protected Renderer createRenderer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setTransparentCursor() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean getShiftDown() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void setDefault2DCursor() {
		// TODO Auto-generated method stub

	}

	@Override
	public GGraphics2D getTempGraphics2D(GFont fontForGraphics) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GFont getFont() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setStyleBarMode(int mode) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateSizeKeepDrawables() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean requestFocusInWindow() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPreferredSize(GDimension preferredSize) {
		// TODO Auto-generated method stub

	}

	@Override
	protected CoordSystemAnimation newZoomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected euclideanStyleBar neweuclideanStyleBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected euclideanStyleBar newDynamicStyleBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void addDynamicStylebarToEV(euclideanStyleBar dynamicStylebar) {
		// TODO Auto-generated method stub

	}

}
