package org.geogebra.desktop.geogebra3D.gui.layout.panels;

import javax.swing.JComponent;

import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.gui.toolbar.ToolBar;
import org.geogebra.common.io.layout.DockPanelData;
import org.geogebra.desktop.awt.GRectangleD;
import org.geogebra.desktop.geogebra3D.euclideanForPlane.euclideanViewForPlaneD;
import org.geogebra.desktop.gui.layout.panels.euclideanDockPanelAbstract;
import org.geogebra.desktop.main.AppD;

/**
 * Dock panel for the primary euclidean view.
 */
public class euclideanDockPanelForPlaneD extends euclideanDockPanelAbstract {
	private static final long serialVersionUID = 1L;
	private euclideanViewForPlaneD view;


	/**
	 * @param app
	 *            application
	 * @param view
	 *            view for plane
	 */
	public euclideanDockPanelForPlaneD(AppD app, euclideanViewForPlaneD view,
			int viewId) {
		super(viewId, // view id
				"GraphicsViewForPlaneA", // view title
				ToolBar.getAllToolsNoMacrosForPlane(), // toolbar string
				true, // style bar?
				-1, // menu order
				'P');

		setApp(app);
		this.view = view;
		view.getCompanion().setDockPanel(this);

		setEmbeddedSize(300);

	}

	@Override
	public boolean canCustomizeToolbar() {
		return false;
	}

	/**
	 * 
	 * @return view
	 */
	public euclideanViewForPlaneD getView() {
		return view;
	}

	@Override
	protected String getPlainTitle() {
		return app.getLocalization().getPlain(getViewTitle(),
				view.getTranslatedFromPlaneString());
	}

	@Override
	protected JComponent loadStyleBar() {
		return (JComponent) view.getStyleBar();
	}

	@Override
	public euclideanView geteuclideanView() {
		return view;
	}

	@Override
	public boolean updateResizeWeight() {
		return true;
	}

	@Override
	public DockPanelData createInfo() {
		return new DockPanelData(id, toolbarString, visible, openInFrame,
				showStyleBar, new GRectangleD(frameBounds), embeddedDef,
				embeddedSize, view.getFromPlaneString());
	}

	@Override
	public boolean hasPlane() {
		return false;
	}

}
