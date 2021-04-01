package org.geogebra.common.geogebra3D.main;

import java.util.ArrayList;

import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.euclidean3D.euclideanView3DInterface;
import org.geogebra.common.geogebra3D.euclidean3D.euclideanController3DForExport;
import org.geogebra.common.geogebra3D.euclidean3D.euclideanView3D;
import org.geogebra.common.geogebra3D.euclidean3D.euclideanView3DForExport;
import org.geogebra.common.geogebra3D.euclidean3D.openGL.Renderer;
import org.geogebra.common.geogebra3D.euclidean3D.openGL.RendererForExport;
import org.geogebra.common.geogebra3D.euclidean3D.printer3D.Format;
import org.geogebra.common.geogebra3D.euclidean3D.printer3D.FormatCollada;
import org.geogebra.common.geogebra3D.euclideanForPlane.euclideanViewForPlaneCompanion;
import org.geogebra.common.geogebra3D.kernel3D.GeoFactory3D;
import org.geogebra.common.geogebra3D.kernel3D.Kernel3D;
import org.geogebra.common.geogebra3D.main.settings.euclideanSettingsForPlane;
import org.geogebra.common.gui.dialog.Export3dDialogInterface;
import org.geogebra.common.gui.dialog.options.model.AxisModel;
import org.geogebra.common.gui.layout.DockPanel;
import org.geogebra.common.kernel.GeoFactory;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.kernel.commands.CommandsConstants;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.kernelND.ViewCreator;
import org.geogebra.common.main.App;
import org.geogebra.common.main.AppCompanion;
import org.geogebra.common.main.DialogManager;
import org.geogebra.common.main.settings.euclideanSettings;
import org.geogebra.common.main.settings.euclideanSettings3D;
import org.geogebra.common.main.settings.Settings;
import org.geogebra.common.plugin.Geometry3DGetter;

/**
 * 
 * @author mathieu
 *
 *         Companion for 3D application
 */
public abstract class App3DCompanion extends AppCompanion {
	// id of the first view
	private int viewId = App.VIEW_euclidean_FOR_PLANE_START;
	/** view for plane companions */
	protected ArrayList<euclideanViewForPlaneCompanion> euclideanViewForPlaneCompanionList;

	private euclideanViewForPlaneCompanion euclideanViewForPlaneCompanion;

	/**
	 * Constructor
	 * 
	 * @param app
	 *            application
	 */
	public App3DCompanion(App app) {
		super(app);
	}

	@Override
	public Kernel newKernel() {

		if (app.is3DViewEnabled()) {
			return new Kernel3D(app, new GeoFactory3D());
		}

		return new Kernel(app, new GeoFactory());
	}

	@Override
	protected boolean tableVisible(int table) {
		return super.tableVisible(table)
				|| (table == CommandsConstants.TABLE_3D && app.areCommands3DEnabled());
	}

	// ///////////////////////////////
	// euclidean VIEW FOR PLANE
	// ///////////////////////////////

	/**
	 * add euclidean views for plane settings
	 * 
	 * @param sb
	 *            string builder
	 * @param asPreference
	 *            save as preference flag
	 */
	public void addCompleteUserInterfaceXMLForPlane(StringBuilder sb,
			boolean asPreference) {
		if (euclideanViewForPlaneCompanionList != null) {
			for (euclideanViewForPlaneCompanion vfpc : euclideanViewForPlaneCompanionList) {
				vfpc.getView().getXML(sb, asPreference);
			}
		}
	}

	@Override
	public void geteuclideanViewXML(StringBuilder sb, boolean asPreference) {
		super.geteuclideanViewXML(sb, asPreference);

		if (app.iseuclideanView3Dinited()) {
			// TODO it would be cleaner to use euclideanSettings here instead
			app.geteuclideanView3D().getXML(sb, asPreference);
		}

		if (euclideanViewForPlaneCompanionList != null) {
			for (euclideanViewForPlaneCompanion vfpc : euclideanViewForPlaneCompanionList) {
				vfpc.getView().getXML(sb, asPreference);
			}
		}

	}

	/**
	 * create new euclidean view for plane
	 * 
	 * @param plane
	 *            plane
	 * @param evSettings
	 *            settings
	 * @param panelSettings
	 *            whether to set the visibility and size of the panel
	 * @return view companion
	 */
	protected abstract euclideanViewForPlaneCompanion createeuclideanViewForPlane(
			ViewCreator plane, euclideanSettings evSettings,
			boolean panelSettings);

	@Override
	public euclideanViewForPlaneCompanion createeuclideanViewForPlane(
			ViewCreator plane, boolean panelSettings) {
		// create new view for plane and controller
		Settings settings = app.getSettings();
		String name = plane.getLabelSimple();
		euclideanSettings evSettings = settings.geteuclideanForPlane(name);
		if (evSettings == null) {
			evSettings = new euclideanSettingsForPlane(app);
			evSettings.setShowGridSetting(false);
			evSettings.setShowAxes(false, false);
			settings.seteuclideanSettingsForPlane(name, evSettings);
		}
		euclideanViewForPlaneCompanion = createeuclideanViewForPlane(plane,
				evSettings, panelSettings);
		evSettings.addListener(euclideanViewForPlaneCompanion.getView());
		euclideanViewForPlaneCompanion.getView().updateFonts();
		euclideanViewForPlaneCompanion.addExistingGeos();

		// add it to list
		if (euclideanViewForPlaneCompanionList == null) {
			euclideanViewForPlaneCompanionList = new ArrayList<>();
		}
		euclideanViewForPlaneCompanionList.add(euclideanViewForPlaneCompanion);

		return euclideanViewForPlaneCompanion;
	}

	/**
	 * remove the view from the list
	 * 
	 * @param vfpc
	 *            view for plane companion
	 */
	public void removeeuclideanViewForPlaneFromList(
			euclideanViewForPlaneCompanion vfpc) {
		euclideanViewForPlaneCompanionList.remove(vfpc);
		app.getSettings().removeeuclideanSettingsForPlane(
				vfpc.getPlane().getLabelSimple());
	}

	/**
	 * remove all euclidean views for plane
	 */
	public void removeAlleuclideanViewForPlane() {

		if (euclideanViewForPlaneCompanionList == null) {
			return;
		}

		for (euclideanViewForPlaneCompanion vfpc : euclideanViewForPlaneCompanionList) {
			vfpc.removeFromGuiAndKernel();
		}

		euclideanViewForPlaneCompanionList.clear();
		app.getSettings().cleareuclideanSettingsForPlane();

	}

	@Override
	public DockPanel createeuclideanDockPanelForPlane(int id, String plane) {

		GeoElement geo = app.getKernel().lookupLabel(plane);
		if (geo == null) {
			return null;
		}
		if (!(geo instanceof ViewCreator)) {
			return null;
		}

		ViewCreator vc = (ViewCreator) geo; // getViewCreator(id);
		vc.seteuclideanViewForPlane(createeuclideanViewForPlane(vc, false));
		return getPanelForPlane();
	}

	/**
	 * 
	 * @return current dockpanel for plane
	 */
	abstract public DockPanel getPanelForPlane();

	@Override
	public boolean haseuclideanViewForPlane() {
		return euclideanViewForPlaneCompanionList != null
				&& euclideanViewForPlaneCompanionList.size() > 0;
	}

	@Override
	public boolean haseuclideanViewForPlaneVisible() {
		if (!haseuclideanViewForPlane()) {
			return false;
		}

		for (euclideanViewForPlaneCompanion c : euclideanViewForPlaneCompanionList) {

			if (c.isPanelVisible()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public euclideanView getViewForPlaneVisible() {
		if (!haseuclideanViewForPlane()) {
			return null;
		}

		for (euclideanViewForPlaneCompanion c : euclideanViewForPlaneCompanionList) {
			if (c.getView().isShowing()) {
				return c.getView();
			}
		}

		return null;

	}

	@Override
	public void addToViewsForPlane(GeoElement geo) {
		if (euclideanViewForPlaneCompanionList == null) {
			return;
		}

		for (euclideanViewForPlaneCompanion c : euclideanViewForPlaneCompanionList) {
			c.getView().add(geo);
		}
	}

	@Override
	public void removeFromViewsForPlane(GeoElement geo) {
		if (euclideanViewForPlaneCompanionList == null) {
			return;
		}

		for (euclideanViewForPlaneCompanion c : euclideanViewForPlaneCompanionList) {
			c.getView().remove(geo);
		}
	}

	@Override
	public final void reseteuclideanViewForPlaneIds() {
		viewId = App.VIEW_euclidean_FOR_PLANE_START;
	}

	/**
	 * Increment view ID and return the old one
	 * 
	 * @return next view ID
	 */
	public int incViewID() {
		return viewId++;
	}
	
	@Override
	public void setExport3D(Format format) {
		// try first with existing 3D view
		if (format.useSpecificViewForExport()) {
			int width = 0, height = 0;
			boolean use2d = true;
			if (app.iseuclideanView3Dinited()) {
				euclideanView3DInterface view3D = app.geteuclideanView3D();
				if (view3D.isShowing()) {
					Renderer r = view3D.getRenderer();
					width = r.getWidth();
					height = r.getHeight();
					use2d = false;
				}
			}
			euclideanSettings3D settings = (euclideanSettings3D) app.getSettings().geteuclidean(3);
			if (use2d) {
				euclideanView ev = app.getActiveeuclideanView();
				width = ev.getWidth();
				height = ev.getHeight();
				euclideanSettings s2d = ev.getSettings();
				settings.setShowAxis(AxisModel.AXIS_X,
						s2d.getShowAxis(AxisModel.AXIS_X));
				settings.setShowAxis(AxisModel.AXIS_Y,
						s2d.getShowAxis(AxisModel.AXIS_Y));
				settings.setShowAxis(AxisModel.AXIS_Z, false);
				settings.setShowPlate(false);
				settings.setShowGridSetting(s2d.getShowGrid() && (s2d
						.getGridType() == euclideanView.GRID_CARTESIAN
						|| s2d.getGridType() == euclideanView.GRID_CARTESIAN_WITH_SUBGRID));
				double xscale = s2d.getXscale();
				double yscale = s2d.getYscale();
				double xmin = -s2d.getXZero() / xscale;
				double xmax = (width - s2d.getXZero()) / xscale;
				double ymin = -(height - s2d.getYZero()) / yscale;
				double ymax = s2d.getYZero() / yscale;
				settings.setXscale(s2d.getXscale());
				settings.setYscale(s2d.getYscale());
				settings.setZscale(s2d.getXscale());
				settings.updateOriginFromView(-(xmin + xmax) / 2,
						-(ymin + ymax) / 2, 0);
				settings.setYAxisVertical(true); // this way view height will be
													// used for clipping
			}
			euclideanView3DForExport exportView3D = new euclideanView3DForExport(
					new euclideanController3DForExport(app), settings);
			if (width > 0) {
				RendererForExport renderer = (RendererForExport) exportView3D.getRenderer();
				renderer.setReduceForClipping(!use2d);
				renderer.setView(0, 0, width, height);
			}
			Export3dDialogInterface dialog = null;
			DialogManager dialogManager = app.getDialogManager();
			if (dialogManager != null) {
				dialog = dialogManager.getExport3dDialog(exportView3D);
			}
			if (dialog != null) {
				exportView3D.export3D(format, dialog);
				app.getKernel().detach(exportView3D);
			} else {
				StringBuilder export = exportView3D.export3D(format);
				app.getKernel().detach(exportView3D);
				app.exportStringToFile(format.getExtension(),
						export.toString());
			}
		} else {
			if (app.iseuclideanView3Dinited()) {
				euclideanView3DInterface view3D = app.geteuclideanView3D();
				if (view3D.isShowing() && view3D.getRenderer().useShaders()) {
					view3D.setExport3D(format);
					return;
				}
			}
			// use ad hoc 3D view for export
			euclideanView3DForExport exportView3D = new euclideanView3DForExport(
					new euclideanController3DForExport(app),
					app.getSettings().geteuclidean(3));
			StringBuilder export = exportView3D.export3D(format);
			app.getKernel().detach(exportView3D);
			app.exportStringToFile(format.getExtension(), export.toString());
		}
	}

	@Override
	public String exportCollada(double xmin, double xmax, double ymin,
			double ymax, double zmin, double zmax, double xyScale,
			double xzScale, double xTickDistance, double yTickDistance,
			double zTickDistance) {
		// use ad hoc 3D view for export
		euclideanSettings3D settings = new euclideanSettings3D(app);
		euclideanView3DForExport exportView3D = new euclideanView3DForExport(
				new euclideanController3DForExport(app), settings);
		Format format = new FormatCollada();
		exportView3D.updateSettings(xmin, xmax, ymin, ymax, zmin, zmax, xyScale,
				xzScale, xTickDistance, yTickDistance, zTickDistance);
		StringBuilder export = exportView3D.export3D(format);
		app.getKernel().detach(exportView3D);
		return export.toString();
	}

	@Override
	public void exportGeometry3D(Geometry3DGetter getter, double xmin,
			double xmax, double ymin, double ymax, double zmin, double zmax,
			double xyScale, double xzScale, double xTickDistance,
			double yTickDistance, double zTickDistance) {
		// use ad hoc 3D view for export
		euclideanSettings3D settings = new euclideanSettings3D(app);
		euclideanView3DForExport exportView3D = new euclideanView3DForExport(
				new euclideanController3DForExport(app), settings);

		if (app.iseuclideanView3Dinited()) {
			euclideanView3D view3D = (euclideanView3D) app.geteuclideanView3D();
			euclideanSettings3D viewSettings = view3D.getSettings();
			settings.setShowAxes(viewSettings.axisShown());
			if (xmin > xmax) { // use original view settings
				exportView3D.updateSettings(view3D.getXmin(), view3D.getXmax(),
						view3D.getYmin(), view3D.getYmax(), view3D.getZmin(),
						view3D.getZmax(),
						view3D.getYscale() / view3D.getXscale(),
						view3D.getZscale() / view3D.getXscale(),
						view3D.getAxisNumberingDistance(0),
						view3D.getAxisNumberingDistance(1),
						view3D.getAxisNumberingDistance(2));
			} else {
				exportView3D.updateSettings(xmin, xmax, ymin, ymax, zmin, zmax,
						xyScale, xzScale, xTickDistance, yTickDistance,
						zTickDistance);
			}
		} else {
			exportView3D.updateSettings(xmin, xmax, ymin, ymax, zmin, zmax,
					xyScale, xzScale, xTickDistance, yTickDistance,
					zTickDistance);
		}
		exportView3D.export3D(getter);
		app.getKernel().detach(exportView3D);
	}

	public euclideanViewForPlaneCompanion geteuclideanViewForPlaneCompanion() {
		return euclideanViewForPlaneCompanion;
	}

	@Override
	public void updateFonts3D() {
		if (app.iseuclideanView3Dinited()) {
			((euclideanView) app.geteuclideanView3D()).updateFonts();
		}
		if (euclideanViewForPlaneCompanion != null) {
			euclideanViewForPlaneCompanion.getView().updateFonts();
		}
	}
}
