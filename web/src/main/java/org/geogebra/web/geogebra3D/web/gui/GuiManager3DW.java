package org.geogebra.web.geogebra3D.web.gui;

import org.geogebra.common.euclidean.euclideanViewInterfaceCommon;
import org.geogebra.common.main.OptionType;
import org.geogebra.web.full.gui.ContextMenuGeoElementW;
import org.geogebra.web.full.gui.GuiManagerW;
import org.geogebra.web.full.gui.layout.DockPanelW;
import org.geogebra.web.full.gui.properties.PropertiesViewW;
import org.geogebra.web.full.main.GDevice;
import org.geogebra.web.geogebra3D.web.euclidean3D.euclideanView3DW;
import org.geogebra.web.geogebra3D.web.gui.layout.panels.euclideanDockPanel3DW;
import org.geogebra.web.geogebra3D.web.gui.view.properties.PropertiesView3DW;
import org.geogebra.web.html5.main.AppW;

import com.google.gwt.user.client.Command;

/**
 * web gui manager for 3D
 * 
 * @author mathieu
 *
 */
public class GuiManager3DW extends GuiManagerW {

	private DockPanelW euclidean3Dpanel;

	/**
	 * constructor
	 * 
	 * @param app
	 *            application
	 * @param device
	 *            device (browser / tablet)
	 */
	public GuiManager3DW(AppW app, GDevice device) {
		super(app, device);
	}

	@Override
	protected boolean initLayoutPanels() {

		if (super.initLayoutPanels()) {
			this.euclidean3Dpanel = new euclideanDockPanel3DW(getApp());
			layout.registerPanel(this.euclidean3Dpanel);
			return true;
		}

		return false;

	}

	@Override
	public DockPanelW geteuclidean3DPanel() {
		return this.euclidean3Dpanel;
	}

	@Override
	public void showDrawingPadPopup3D(euclideanViewInterfaceCommon view,
	        org.geogebra.common.awt.GPoint p) {

		// clear highlighting and selections in views
		getApp().getActiveeuclideanView().resetMode();
		getDrawingPadpopupMenu3D(p.x, p.y).showScaled(
				((euclideanView3DW) view).getG2P().getElement(), p.x, p.y);
	}

	private ContextMenuGeoElementW getDrawingPadpopupMenu3D(int x, int y) {
		currentPopup = new ContextMenuGraphicsWindow3DW(getApp(), x, y);
		return (ContextMenuGeoElementW) currentPopup;
	}

	/**
	 * 
	 * @return command to show/hide 3D axis
	 */
	public Command getShowAxes3DAction() {
		return new Command() {

			@Override
			public void execute() {
				// toggle axes
				((euclideanView3DW) getApp().geteuclideanView3D()).toggleAxis();
				// getApp().geteuclideanView().repaint();
				getApp().storeUndoInfo();
				getApp().updateMenubar();
			}
		};
	}

	/**
	 * 
	 * @return command to show/hide 3D grid
	 */
	public Command getShowGrid3DAction() {
		return new Command() {

			@Override
			public void execute() {
				// toggle grid
				((euclideanView3DW) getApp().geteuclideanView3D()).toggleGrid();
				// getApp().geteuclideanView().repaint();
				getApp().storeUndoInfo();
				getApp().updateMenubar();
			}
		};
	}

	/**
	 * 
	 * @return command to show/hide 3D plane
	 */
	public Command getShowPlane3DAction() {
		return new Command() {

			@Override
			public void execute() {
				// toggle plane
				((euclideanView3DW) getApp().geteuclideanView3D())
				        .getSettings().togglePlane();
				// getApp().geteuclideanView().repaint();
				getApp().storeUndoInfo();
				getApp().updateMenubar();
			}
		};
	}

	@Override
	protected PropertiesViewW newPropertiesViewW(AppW app1, OptionType ot) {
		return new PropertiesView3DW(app1, ot);
	}

}
