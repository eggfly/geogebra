package org.geogebra.desktop.gui.layout.panels;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.main.App;
import org.geogebra.desktop.main.AppD;
import org.geogebra.desktop.util.GuiResourcesD;

/**
 * Dock panel for the primary euclidean view.
 */
public class euclideanDockPanel extends euclideanDockPanelAbstract {
	private static final long serialVersionUID = 1L;
	private AppD app;

	/**
	 * Panel to hold euclidean view and navigation bar if necessary.
	 */

	/**
	 * @param app
	 */
	public euclideanDockPanel(AppD app, String toolbar) {
		super(App.VIEW_euclidean, // view id
				"DrawingPad", // view title
				toolbar, // toolbar string
				true, // style bar?
				4, // menu order
				'1' // ctrl-shift-1
		);

		this.app = app;
	}

	@Override
	protected JComponent loadStyleBar() {
		return (JComponent) app.geteuclideanView1().getStyleBar();
	}

	/**
	 * As the component of this panel is not just the euclidean view as asserted
	 * in euclideanDockPanelAbstract we have to override this method to provide
	 * the correct euclidean view.
	 */
	@Override
	public euclideanView geteuclideanView() {
		return app.geteuclideanView1();
	}

	@Override
	public ImageIcon getIcon() {
		return app.getMenuIcon(GuiResourcesD.MENU_VIEW_GRAPHICS);
	}
}
