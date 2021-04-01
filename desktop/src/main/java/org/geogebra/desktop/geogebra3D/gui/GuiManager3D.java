package org.geogebra.desktop.geogebra3D.gui;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import org.geogebra.common.awt.GPoint;
import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.euclidean.euclideanViewInterfaceCommon;
import org.geogebra.common.euclidean3D.euclideanView3DInterface;
import org.geogebra.common.geogebra3D.main.App3DCompanion;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.main.App;
import org.geogebra.common.main.Localization;
import org.geogebra.common.main.settings.euclideanSettings;
import org.geogebra.desktop.euclidean.euclideanViewD;
import org.geogebra.desktop.euclideanND.euclideanViewInterfaceD;
import org.geogebra.desktop.geogebra3D.App3D;
import org.geogebra.desktop.geogebra3D.euclideanFor3D.euclideanControllerFor3DD;
import org.geogebra.desktop.geogebra3D.euclideanFor3D.euclideanViewFor3DD;
import org.geogebra.desktop.geogebra3D.gui.dialogs.DialogManager3D;
import org.geogebra.desktop.geogebra3D.gui.layout.panels.euclideanDockPanel3DD;
import org.geogebra.desktop.geogebra3D.gui.view.properties.PropertiesView3DD;
import org.geogebra.desktop.gui.ContextMenuChooseGeoD;
import org.geogebra.desktop.gui.ContextMenuGeoElementD;
import org.geogebra.desktop.gui.GuiManagerD;
import org.geogebra.desktop.gui.view.algebra.AlgebraControllerD;
import org.geogebra.desktop.gui.view.algebra.AlgebraViewD;
import org.geogebra.desktop.gui.view.properties.PropertiesViewD;
import org.geogebra.desktop.main.AppD;
import org.geogebra.desktop.util.GuiResourcesD;

/**
 * Extending DefaultGuiManager class for 3D
 * 
 * @author Mathieu
 * 
 */
public class GuiManager3D extends GuiManagerD {

	private AbstractAction showAxes3DAction, showGrid3DAction, showPlaneAction;

	/**
	 * default constructor
	 * 
	 * @param app
	 *            application
	 */
	public GuiManager3D(AppD app) {
		super(app);
		javax.swing.JPopupMenu.setDefaultLightWeightPopupEnabled(false); // popups
																			// over
																			// the
																			// 3D
																			// canvas
		javax.swing.ToolTipManager.sharedInstance()
				.setLightWeightPopupEnabled(false); // tooltips over the 3D
													// canvas

		dialogManagerFactory = new DialogManager3D.Factory();
	}

	@Override
	public void initialize() {
		super.initialize();
	}

	/**
	 * Add 3D euclidean view to layout.
	 */
	@Override
	protected void initLayoutPanels() {
		super.initLayoutPanels();
		if (getApp().supportsView(App.VIEW_euclidean3D)) {
			getLayout().registerPanel(new euclideanDockPanel3DD(getApp()));
		}
	}

	// ////////////////////////////
	// ACTIONS
	// ////////////////////////////

	@Override
	protected boolean initActions() {

		if (!super.initActions()) {
			return false;
		}
		Localization loc = getApp().getLocalization();
		showAxes3DAction = new AbstractAction(
				loc.getMenu("Axes"),
				(getApp()).getScaledIcon(GuiResourcesD.AXES)) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// toggle axes
				((App3D) getApp()).toggleAxis3D();
				// getApp().geteuclideanView().repaint();
				getApp().storeUndoInfo();
				getApp().updateMenubar();

			}
		};

		showGrid3DAction = new AbstractAction(
				loc.getMenu("Grid"),
				getApp().getScaledIcon(GuiResourcesD.GRID)) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// toggle grid
				((App3D) getApp()).toggleGrid3D();
				// getApp().geteuclideanView().repaint();
				getApp().storeUndoInfo();
				getApp().updateMenubar();

			}
		};

		showPlaneAction = new AbstractAction(
				loc.getMenu("Plane"),
				(getApp()).getScaledIcon(GuiResources3D.PLANE)) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// toggle plane
				((App3D) getApp()).togglePlane();
				getApp().storeUndoInfo();
				getApp().updateMenubar();
			}
		};

		return true;

	}

	/**
	 * @return Action for showing axes in 3D
	 */
	public AbstractAction getShowAxes3DAction() {
		initActions();
		return showAxes3DAction;
	}

	/**
	 * @return Action for showing grid in 3D
	 */
	public AbstractAction getShowGrid3DAction() {
		initActions();
		return showGrid3DAction;
	}

	/**
	 * @return Action for showing xOyPlane in 3D
	 */
	public AbstractAction getShowPlaneAction() {
		initActions();
		return showPlaneAction;
	}

	// ////////////////////////////
	// POPUP MENU
	// ////////////////////////////

	/**
	 * Displays the zoom menu at the position p in the coordinate space of
	 * euclideanView
	 */
	/*
	 * public void showDrawingPadPopup(Component invoker, Point p) { // clear
	 * highlighting and selections in views app.geteuclideanView().resetMode();
	 * 
	 * // menu for drawing pane context menu ContextMenuGraphicsWindow3D
	 * popupMenu = new ContextMenuGraphicsWindow3D( app, p.x, p.y);
	 * popupMenu.show(invoker, p.x, p.y); }
	 */

	/**
	 * Displays the zoom menu at the position p in the coordinate space of
	 * euclideanView
	 * 
	 * @param view
	 *            view
	 * @param p
	 *            zoom point
	 */
	@Override
	public void showDrawingPadPopup3D(euclideanViewInterfaceCommon view,
			GPoint p) {
		// clear highlighting and selections in views
		getApp().geteuclideanView3D().resetMode();

		// menu for drawing pane context menu
		ContextMenuGraphicsWindow3DD popupMenu = new ContextMenuGraphicsWindow3DD(
				getApp());
		popupMenu.getWrappedPopup()
				.show(((euclideanViewInterfaceD) view).getJPanel(), p.x, p.y);
	}

	/**
	 * Displays the popup menu for geo at the position p in the coordinate space
	 * of the component invoker
	 * 
	 * @param selectedGeos
	 *            first geos
	 * @param geos
	 *            list of geos
	 * @param view
	 *            view calling
	 * @param p
	 *            place to show the popup menue
	 */
	@Override
	public void showPopupChooseGeo(ArrayList<GeoElement> selectedGeos,
			ArrayList<GeoElement> geos, euclideanView view, GPoint p) {

		if (selectedGeos == null || selectedGeos.isEmpty()
				|| selectedGeos.get(0) == null) {
			return;
		}

		// clear highlighting and selections in views
		getApp().getActiveeuclideanView().resetMode();

		Component invoker = ((euclideanViewInterfaceD) view).getJPanel();

		Point screenPos = (invoker == null) ? new Point(0, 0)
				: invoker.getLocationOnScreen();
		screenPos.translate(p.x, p.y);

		ContextMenuGeoElementD popupMenu = new ContextMenuChooseGeoD(getApp(),
				view, selectedGeos, geos, screenPos, p);
		popupMenu.getWrappedPopup().show(invoker, p.x, p.y);

	}

	// ////////////////////////////
	// ALGEBRA VIEW
	// ////////////////////////////

	@Override
	protected AlgebraViewD newAlgebraView(AlgebraControllerD algc) {
		return new AlgebraViewD(algc);
	}

	@Override
	protected euclideanViewD neweuclideanView(boolean[] showAxis,
			boolean showGrid, int viewId) {

		euclideanSettings settings = getApp().getSettings().geteuclidean(viewId);

		return new euclideanViewFor3DD(new euclideanControllerFor3DD(kernel),
				showAxis, showGrid, viewId, settings);
	}

	// ////////////////////////////
	// 3D VIEW
	// ////////////////////////////

	@Override
	protected PropertiesViewD newPropertiesViewD(AppD appD) {
		return new PropertiesView3DD(appD);
	}

	@Override
	public void setLabels() {

		super.setLabels();

		if (getApp().iseuclideanView3Dinited()) {
			euclideanView3DInterface view = getApp().geteuclideanView3D();
			if (view != null && view.hasStyleBar()) {
				view.getStyleBar().setLabels();
			}

		}
	}

	@Override
	public boolean loadURL(String urlString, boolean suppressErrorMsg) {
		((App3DCompanion) getApp().getCompanion()).removeAlleuclideanViewForPlane();
		return super.loadURL(urlString, suppressErrorMsg);
	}
}
