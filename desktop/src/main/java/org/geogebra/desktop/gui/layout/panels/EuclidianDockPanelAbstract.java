package org.geogebra.desktop.gui.layout.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

import org.geogebra.common.euclidean.euclideanStyleBar;
import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.euclidean.GetViewId;
import org.geogebra.common.main.settings.ConstructionProtocolSettings;
import org.geogebra.desktop.euclideanND.euclideanViewInterfaceD;
import org.geogebra.desktop.gui.layout.DockPanelD;
import org.geogebra.desktop.gui.view.consprotocol.ConstructionProtocolNavigationD;

/**
 * Abstract class for all "euclidean" panels.
 * 
 * @author Mathieu
 * Remark: {@link #geteuclideanView()} has to be overridden if
 *         {@link #getComponent()} does not return the euclidean view directly
 */
public abstract class euclideanDockPanelAbstract extends DockPanelD
		implements GetViewId {
	/** */
	private static final long serialVersionUID = 1L;

	private boolean haseuclideanFocus;
	private JPanel panel;
	/**
	 * Component of the construction protocol navigation bar, invisible if not
	 * needed.
	 */
	private ConstructionProtocolNavigationD consProtNav;

	/**
	 * default constructor
	 * 
	 * @param id
	 * @param title
	 * @param toolbar
	 * @param hasStyleBar
	 * @param menuOrder
	 */
	public euclideanDockPanelAbstract(int id, String title, String toolbar,
			boolean hasStyleBar, int menuOrder, char shortcut) {
		super(id, title, toolbar, hasStyleBar, menuOrder, shortcut);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		super.mousePressed(arg0);
		dockManager.setFocusedPanel(this);
	}

	/**
	 * @return The euclidean view associated with this dock panel.
	 * Remark: This method has to be overridden if the component of the dock
	 *         panel is not the euclidean view itself
	 */
	abstract public euclideanView geteuclideanView();

	/**
	 * sets this euclidean panel to have the "euclidean focus"
	 * 
	 * @param hasFocus
	 */
	public final void seteuclideanFocus(boolean hasFocus) {
		haseuclideanFocus = hasFocus;
	}

	@Override
	protected boolean titleIsBold() {
		return super.titleIsBold() || haseuclideanFocus;
	}

	/**
	 * create the focus panel (composed of titleLabel, and, for
	 * euclideanDockPanels, focus icon)
	 * 
	 * @return the focus panel
	 */
	@Override
	protected JComponent createFocusPanel() {
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());

		// add title label
		panel1.add(super.createFocusPanel(),
				app.getLocalization().borderWest());

		return panel1;
	}

	@Override
	public boolean updateResizeWeight() {
		return true;
	}

	@Override
	protected void setStyleBar() {
		super.setStyleBar();
		((euclideanStyleBar) styleBar).resetFirstPaint();
	}

	@Override
	protected final JComponent loadComponent() {
		if (panel == null) {
			panel = new JPanel(new BorderLayout());

			panel.add(
					((euclideanViewInterfaceD) geteuclideanView()).getJPanel(),
					BorderLayout.CENTER);

			consProtNav = (ConstructionProtocolNavigationD) app.getGuiManager()
					.getConstructionProtocolNavigation(id);

			ConstructionProtocolSettings cps = app.getSettings()
					.getConstructionProtocol();
			consProtNav.settingsChanged(cps);
			cps.addListener(consProtNav);

			if (app.getShowCPNavNeedsUpdate(id)) {
				app.setShowConstructionProtocolNavigation(
						app.showConsProtNavigation(id), id);
			}
			consProtNav.getImpl().setBorder(BorderFactory.createMatteBorder(1,
					0, 0, 0, Color.lightGray));
			consProtNav.getImpl().setVisible(app.showConsProtNavigation(id));

			panel.add(consProtNav.getImpl(), BorderLayout.SOUTH); // may be
																	// invisible,
																	// but made
																	// visible
																	// later
		}

		return panel;
	}

}
