/* 
GeoGebra - Dynamic Mathematics for Everyone
http://www.geogebra.org

This file is part of GeoGebra.

This program is free software; you can redistribute it and/or modify it 
under the terms of the GNU General Public License as published by 
the Free Software Foundation.

 */

/**
 * GeoGebra Application
 *
 * @author Markus Hohenwarter
 */
package org.geogebra.desktop.geogebra3D;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.geogebra.common.awt.GBufferedImage;
import org.geogebra.common.euclidean.euclideanController;
import org.geogebra.common.euclidean.euclideanCursor;
import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.euclidean.euclideanViewInterfaceCommon;
import org.geogebra.common.euclidean.event.AbstractEvent;
import org.geogebra.common.euclidean3D.Input3DConstants;
import org.geogebra.common.geogebra3D.euclidean3D.euclideanController3D;
import org.geogebra.common.geogebra3D.euclidean3D.euclideanView3D;
import org.geogebra.common.geogebra3D.euclidean3D.openGL.GLFactory;
import org.geogebra.common.geogebra3D.input3D.Input3D;
import org.geogebra.common.geogebra3D.kernel3D.geos.GeoPlane3D;
import org.geogebra.common.geogebra3D.main.App3DCompanion;
import org.geogebra.common.gui.layout.DockManager;
import org.geogebra.common.jre.openGL.GLFactoryJre;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.kernel.geos.AnimationExportSlider;
import org.geogebra.common.main.App;
import org.geogebra.common.main.AppCompanion;
import org.geogebra.common.main.settings.euclideanSettings3D;
import org.geogebra.common.main.settings.updater.SettingsUpdaterBuilder;
import org.geogebra.common.util.debug.Log;
import org.geogebra.desktop.CommandLineArguments;
import org.geogebra.desktop.euclidean.event.MouseEventD;
import org.geogebra.desktop.geogebra3D.euclidean3D.euclideanController3DD;
import org.geogebra.desktop.geogebra3D.euclidean3D.euclideanView3DD;
import org.geogebra.desktop.geogebra3D.euclideanFor3D.euclideanControllerFor3DD;
import org.geogebra.desktop.geogebra3D.euclideanFor3D.euclideanViewFor3DD;
import org.geogebra.desktop.geogebra3D.euclideanInput3D.euclideanControllerHand3D;
import org.geogebra.desktop.geogebra3D.euclideanInput3D.euclideanControllerInput3D;
import org.geogebra.desktop.geogebra3D.euclideanInput3D.euclideanViewInput3D;
import org.geogebra.desktop.geogebra3D.gui.GuiManager3D;
import org.geogebra.desktop.geogebra3D.gui.layout.panels.euclideanDockPanel3DD;
import org.geogebra.desktop.geogebra3D.input3D.Input3DFactory;
import org.geogebra.desktop.geogebra3D.input3D.Input3DFactory.Input3DException;
import org.geogebra.desktop.geogebra3D.input3D.Input3DFactory.Input3DExceptionType;
import org.geogebra.desktop.geogebra3D.util.ImageManager3D;
import org.geogebra.desktop.gui.GuiManagerD;
import org.geogebra.desktop.gui.app.GeoGebraFrame3D;
import org.geogebra.desktop.gui.layout.DockManagerD;
import org.geogebra.desktop.main.AppD;
import org.geogebra.desktop.main.GeoGebraPreferencesD;
import org.geogebra.desktop.main.LocalizationD;
import org.geogebra.desktop.main.settings.updater.FontSettingsUpdaterD;
import org.geogebra.desktop.util.FrameCollector;

public class App3D extends AppD {

	private euclideanView3D euclideanView3D;
	private euclideanController3D euclideanController3D;

	public App3D(CommandLineArguments args,
			boolean undoActive) {
		this(args, null, undoActive);
	}

	public App3D(CommandLineArguments args, JFrame frame, boolean undoActive) {

		super(args, frame, null, undoActive, new LocalizationD(3));

		runThreadForCheckInput3D();
	}

	public App3D(CommandLineArguments args, Container comp,
			boolean undoActive) {
		super(args, null, comp, undoActive, new LocalizationD(3));

		runThreadForCheckInput3D();
	}

	private class ThreadForCheckInput3D extends Thread {

		private App app;

		public ThreadForCheckInput3D(App app) {
			this.app = app;
		}

		@Override
		public void run() {

			if (app.isApplet()) {
				return;
			}

			boolean realsenseInited = initRealsense();

			if (!realsenseInited) {
				initZspace();
			}

		}

		private boolean initRealsense() {
			try {
				// try to init realsense
				Input3DFactory.initRealsense();
				Log.debug("RealSense: Session successfully created");

				// save in prefs
				setInput3DType(Input3DConstants.PREFS_REALSENSE);

				// show message
				showRealSenseCongratulations();

				return true;
			} catch (Input3DException e) {
				Log.debug(e.getMessage());
				if (e.getType() == Input3DExceptionType.NOT_UP_TO_DATE) {
					showRealSenseNotUpToDate(e.getMessage());
				}
			}

			return false;
		}

		private boolean initZspace() {
			try {
				// try to init zSpace
				Log.debug("zSpace: try to init");
				Input3DFactory.initZSpace();
				Log.debug("zSpace: successfully detected");

				// save in prefs
				setInput3DType(Input3DConstants.PREFS_ZSPACE);

				// show message
				showZSpaceCongratulations();

				return true;
			} catch (Input3DException e) {
				Log.debug(e.getMessage());
			}

			return false;
		}
	}

	private void runThreadForCheckInput3D() {
		if (!tubeLoginIsShowing && AppD.WINDOWS && !isApplet()
				&& getInput3DType().equals(Input3DConstants.PREFS_NONE)) {
			Log.debug("============ runThreadToCheckInput3D ");
			Thread t = new ThreadForCheckInput3D(this);
			t.start();
		}
	}

	/**
	 * shows congratulations message for using realsense
	 */
	void showRealSenseCongratulations() {
		showInput3DCongratulations(
				getLocalization().getMenu("RealSense.DetectedMessage"),
				REALSENSE_TUTORIAL);
	}

	/**
	 * recommend to update version
	 * 
	 * @param version
	 *            version currently installed
	 */
	void showRealSenseNotUpToDate(String version) {
		showInput3DMessage(
				getLocalization().getPlain("RealSense.NotUpToDate", version),
				getLocalization().getMenu("RealSense.DownloadUpdate"),
				"https://software.intel.com/intel-realsense-sdk/download");
	}

	/**
	 * shows congratulations message for using zspace
	 */
	void showZSpaceCongratulations() {
		showInput3DCongratulations(
				getLocalization().getMenu("ZSpace.DetectedMessage"),
				"http://www.geogebra.org/tutorial/zspace");

	}

	private void showInput3DCongratulations(final String message,
			final String tutorialURL) {
		showInput3DMessage(message, getLocalization().getMenu("OpenTutorial"),
				tutorialURL);
	}

	private void showInput3DMessage(final String message,
			final String messageForURL, final String URL) {
		// popup help dialog
		input3DPopupShowing = true;
		final JFrame frame = new JFrame();
		Container c = frame.getContentPane();
		JPanel panel = new JPanel();
		c.add(panel);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		JLabel label = new JLabel(message);
		JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelPanel.setBackground(Color.WHITE);
		labelPanel.add(label);
		panel.add(labelPanel);

		JLabel website = new JLabel();
		// String tutorialText = "Click here to get a tutorial";
		website.setText("<html><a href=\"\">" + messageForURL + "</a></html>");
		website.setCursor(new Cursor(Cursor.HAND_CURSOR));
		website.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					try {
						frame.setAlwaysOnTop(false);
					} catch (SecurityException se) {
						// failed to unset on top
					}
					Desktop.getDesktop().browse(new URI(URL));
				} catch (IOException e1) {
					// not working
				} catch (URISyntaxException e1) {
					// not working
				}
			}
		});
		JPanel websitePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		websitePanel.setBackground(Color.WHITE);
		websitePanel.add(website);
		panel.add(websitePanel);

		JLabel closeLabel = new JLabel("OK");
		closeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		closeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				if (tubeLoginHasToBeShown) {
					perspectivePopupHasToBeShown = perspectivePopupHasToBeShown
							&& superShowTubeLogin();
				}
				if (perspectivePopupHasToBeShown) {
					superShowPerspectivePopup();
				}
			}
		});
		JPanel closePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		closePanel.setBackground(Color.WHITE);
		closePanel.add(closeLabel);
		panel.add(closePanel);

		frame.setUndecorated(true);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		try {
			frame.setAlwaysOnTop(true);
		} catch (SecurityException e) {
			// failed to set on top
		}
	}

	/**
	 * download and update realsense
	 */
	void updateRealSense() {
		Log.debug("\n========== updating RealSense");
	}

	boolean input3DPopupShowing = false;
	boolean tubeLoginHasToBeShown = false;
	private boolean tubeLoginIsShowing = false;
	boolean perspectivePopupHasToBeShown = false;

	@Override
	protected boolean showTubeLogin() {
		if (input3DPopupShowing) {
			tubeLoginHasToBeShown = true;
			return true;
		}
		return superShowTubeLogin();
	}

	boolean superShowTubeLogin() {
		tubeLoginHasToBeShown = false;
		boolean ret = super.showTubeLogin();
		tubeLoginIsShowing = false;
		return ret;
	}

	@Override
	public void isShowingLogInDialog() {
		tubeLoginIsShowing = true;
		runThreadForCheckInput3D();
	}

	@Override
	protected void showPerspectivePopup() {
		if (input3DPopupShowing) {
			perspectivePopupHasToBeShown = true;
		} else {
			superShowPerspectivePopup();
		}
	}

	void superShowPerspectivePopup() {
		perspectivePopupHasToBeShown = false;
		super.showPerspectivePopup();
	}

	/**
	 * set 3D input
	 * 
	 * @param type
	 *            type
	 */
	public static void setInput3DType(String type) {
		GeoGebraPreferencesD.getPref().setInput3DType(type);
	}

	@Override
	public String getInput3DType() {
		return GeoGebraPreferencesD.getPref().getInput3DType();
	}

	@Override
	protected void initImageManager(Component component) {
		imageManager = new ImageManager3D(component, this);
	}

	private void initeuclideanController3D() {

		Input3D input3D;

		if (AppD.WINDOWS && !isApplet()) {
			// init the 3D euclidean view (with perhaps a specific 3D input)
			try {
				input3D = Input3DFactory.createInput3D(this, getInput3DType());
			} catch (Input3DException e) {
				if (e.getType() == Input3DExceptionType.INSTALL) {
					// reset 3D input type, guessing 3d input has been
					// uninstalled
					setInput3DType(Input3DConstants.PREFS_NONE);
				} else if (e.getType() == Input3DExceptionType.NOT_UP_TO_DATE) {
					showRealSenseNotUpToDate(e.getMessage());
				}
				input3D = null;
				Log.debug("Problem initializing 3D Input:" + e.getMessage());
			} catch (Throwable e) {
				input3D = null;
				Log.debug("Problem initializing 3D Input:" + e.getClass() + " "
						+ e.getMessage());
			}
		} else {
			input3D = null;
		}

		// input3D = null;
		if (input3D != null) {
			switch (input3D.getDeviceType()) {
			case HAND:
				euclideanController3D = new euclideanControllerHand3D(kernel,
						input3D);
				break;
			case PEN:
			default:
				euclideanController3D = new euclideanControllerInput3D(kernel,
						input3D);
				break;
			}

			// set specific settings
			input3D.setSpecificSettings(
					(euclideanSettings3D) getSettings().geteuclidean(3));

		} else {
			euclideanController3D = new euclideanController3DD(kernel);
		}
	}

	@Override
	protected void exitFrame() {
		super.exitFrame();
		if (euclideanController3D != null) {
			euclideanController3D.exitInput3D();
		}
	}

	@Override
	public boolean isRightClick(AbstractEvent e) {
		if (e instanceof MouseEventD) {
			return isRightClick(MouseEventD.getEvent(e));
		}

		return e.isRightClick();
	}

	@Override
	public euclideanController neweuclideanController(Kernel kernel) {
		return new euclideanControllerFor3DD(kernel);
	}

	@Override
	protected euclideanView neweuclideanView(boolean[] showAxes1,
			boolean showGrid1) {
		return new euclideanViewFor3DD(geteuclideanController(), showAxes1,
				showGrid1, 1, getSettings().geteuclidean(1));
	}

	@Override
	public void setMode(int mode) {
		super.setMode(mode);

		if (iseuclideanView3Dinited()) {
			euclideanView3D.setMode(mode);
		}
	}

	@Override
	public String getCompleteUserInterfaceXML(boolean asPreference) {
		StringBuilder sb = new StringBuilder();

		// save super settings
		sb.append(super.getCompleteUserInterfaceXML(asPreference));

		// save euclideanView3D settings
		if (iseuclideanView3Dinited()) {
			euclideanView3D.getXML(sb, asPreference);
		}

		// save euclidean views for plane settings
		((App3DCompanion) companion).addCompleteUserInterfaceXMLForPlane(sb,
				asPreference);

		return sb.toString();
	}

	/**
	 * return the 3D euclidean view
	 * 
	 * @return the 3D euclidean view
	 */
	@Override
	public euclideanView3D geteuclideanView3D() {
		if (this.euclideanView3D == null) {
			initeuclideanController3D();
			if (euclideanController3D.hasInput3D()) {
				euclideanView3D = new euclideanViewInput3D(
						euclideanController3D, getSettings().geteuclidean(3));
			} else {
				euclideanView3D = new euclideanView3DD(euclideanController3D,
						getSettings().geteuclidean(3));
			}
		}
		return euclideanView3D;
	}

	@Override
	public boolean iseuclideanView3Dinited() {
		return this.euclideanView3D != null;
	}

	@Override
	public void needThumbnailFor3D() {
		if (euclideanView3D != null) {
			geteuclideanView3D().getRenderer().needExportImage();
		}
	}

	/**
	 * check is view is 3D WITHOUT creating 3D View
	 * 
	 * @param view
	 *            view
	 * @return true if it's 3D
	 */
	@Override
	public boolean iseuclideanView3D(euclideanViewInterfaceCommon view) {
		// euclideanView3D might be null
		return view != null && view == euclideanView3D;
	}

	// ///////////////////////////////
	// GUI
	// ///////////////////////////////

	@Override
	public void refreshViews() {
		if (iseuclideanView3Dinited()) {
			geteuclideanView3D().reset();
			DockManagerD dockManager = (DockManagerD) getGuiManager()
					.getLayout().getDockManager();
			((euclideanDockPanel3DD) dockManager.getPanel(VIEW_euclidean3D))
					.refresh(dockManager);

		}
		super.refreshViews();
	}

	@Override
	public void resume3DRenderer() {
		if (iseuclideanView3Dinited()) {
			DockManager dockManager = getGuiManager().getLayout()
					.getDockManager();
			((euclideanDockPanel3DD) dockManager.getPanel(VIEW_euclidean3D))
					.resumeRenderer();

		}
	}

	public void toggleAxis3D() {
		// toggle axis
		geteuclideanView3D().toggleAxis();
	}

	public void togglePlane() {
		// toggle xOy plane
		geteuclideanView3D().getSettings().togglePlane();
	}

	public void toggleGrid3D() {
		// toggle xOy grid
		geteuclideanView3D().toggleGrid();
	}

	public void setShowAxesSelected3D(JCheckBoxMenuItem cb) {
		cb.setSelected(geteuclideanView3D().axesAreAllVisible());
	}

	/**
	 * set the show plane combo box selected if the plane is visible
	 * 
	 * @param cb
	 */
	public void setShowPlaneSelected(JCheckBoxMenuItem cb) {
		GeoPlane3D p = (GeoPlane3D) getKernel().getXOYPlane();
		cb.setSelected(p.isPlateVisible());
	}

	/**
	 * set the show grid combo box selected if the plane is visible
	 * 
	 * @param cb
	 */
	public void setShowGridSelected3D(JCheckBoxMenuItem cb) {
		GeoPlane3D p = (GeoPlane3D) getKernel().getXOYPlane();
		cb.setSelected(p.isGridVisible());
	}

	@Override
	protected GuiManagerD newGuiManager() {
		return new GuiManager3D(this);
	}

	// /////////////////////////////////////
	// COMMANDS
	// /////////////////////////////////////

	/*
	 * (non-Javadoc)
	 * 
	 * @see geogebra.main.Application#getCommandSyntax(java.lang.String) check
	 * if there's a Command.Syntax3D key. If not, return Command.Syntax key
	 */

	@Override
	public void updateStyleBars() {
		super.updateStyleBars();
		if (showView(App.VIEW_euclidean3D)) {
			geteuclideanView3D().getStyleBar().updateStyleBar();
		}
	}

	// ///////////////////////////////
	// FOR TESTING : TODO remove all

	@Override
	public boolean is3D() {
		return true;
	}

	private euclideanCursor oldCursorMode;

	@Override
	protected void handleShiftEvent(boolean isShiftDown) {
		if (!this.iseuclideanView3Dinited()) {
			return;
		}
		if (isShiftDown) {
			euclideanCursor cursor = geteuclideanView3D()
					.updateCursorIfNotTranslateViewCursor();
			if (cursor != null) {
				oldCursorMode = cursor;
			}
			// oldCursorMode = geteuclideanView3D().getCursor();
			// geteuclideanView3D().setCursor(euclideanCursor.MOVE);
			// Log.debug(oldCursorMode);
		} else {
			if (oldCursorMode != null) {
				geteuclideanView3D().setCursor(oldCursorMode);
			}
		}
	}

	@Override
	public void exportAnimatedGIF(euclideanView ev, FrameCollector gifEncoder,
			AnimationExportSlider num, int n, double val, double min,
			double max, double step) {

		if (!(ev instanceof euclideanView3D)) {
			// regular 2D export
			super.exportAnimatedGIF(ev, gifEncoder, num, n, val, min, max,
					step);
			return;
		}

		geteuclideanView3D().getRenderer().startAnimatedGIFExport(gifEncoder,
				num, n, val, min, max, step);
	}

	@Override
	public void copyGraphicsViewToClipboard() {

		if (!(getActiveeuclideanView() instanceof euclideanView3D)) {
			// regular 2D export
			super.copyGraphicsViewToClipboard();
			return;
		}

		geteuclideanView3D().getRenderer().exportToClipboard();

	}

	@Override
	public void fileNew() {
		super.fileNew();

		((App3DCompanion) companion).removeAlleuclideanViewForPlane();
	}

	@Override
	public boolean loadFile(File file, boolean isMacroFile) {

		if (!checkFileExistsAndShowFileNotFound(file)) {
			return false;
		}

		((App3DCompanion) companion).removeAlleuclideanViewForPlane();

		return loadExistingFile(file, isMacroFile);
	}

	@Override
	public void createNewWindow() {
		GeoGebraFrame3D.createNewWindow3D(cmdArgs.getGlobalArguments());
	}

	@Override
	public GBufferedImage getActiveeuclideanViewExportImage(double maxX,
			double maxY) {

		euclideanView ev = getActiveeuclideanView();

		// force 3D view if showing
		if (this.euclideanView3D != null) {
			euclideanView3D ev3D = geteuclideanView3D();
			if (ev3D.isShowing()) {
				ev = ev3D;
			}
		}

		return geteuclideanViewExportImage(ev, maxX, maxY);
	}

	/**
	 * only for 3D really. Overridden in App3D
	 */
	@Override
	public void uploadToGeoGebraTubeOnCallback() {

		if (!iseuclideanView3Dinited()) {
			uploadToGeoGebraTube();
			return;
		}

		euclideanView3D ev3D = geteuclideanView3D();

		if (ev3D.isShowing()) {
			ev3D.getRenderer().uploadToGeoGebraTube();
		} else {
			uploadToGeoGebraTube();
		}

	}

	@Override
	protected void initFactories() {
		super.initFactories();

		if (GLFactory.getPrototype() == null) {
			GLFactory.setPrototypeIfNull(new GLFactoryJre());
		}
	}

	@Override
	protected AppCompanion newAppCompanion() {
		return new App3DCompanionD(this);
	}

	@Override
	protected void handleOptionArgsEarly(CommandLineArguments args) {
		super.handleOptionArgsEarly(args);

		isStereo3D = false;
		useShaders = false;

		if (args == null) {
			return;
		}

		if (args.containsArg("testShaders")) {
			useShaders = true;
		}

		if (args.containsArg("stereo")) {
			isStereo3D = true;
		}

	}

	private boolean useShaders;

	private boolean isStereo3D;

	public boolean isStereo3D() {
		return isStereo3D && !isApplet();
	}

	@Override
	public boolean useShaders() {
		return useShaders && !isApplet();
	}

	@Override
	protected AppD newAppForTemplateOrInsertFile() {
		return new App3D(new CommandLineArguments(null), new JPanel(), true);
	}

	@Override
	public boolean handleSpaceKey() {

		if (iseuclideanView3Dinited()) {
			if (euclideanView3D.getCompanion().useHandGrabbing()) {
				euclideanView3D.handleSpaceKey();
			}
		}

		return super.handleSpaceKey();
	}

	@Override
	public boolean useHugeGuiForInput3D() {
		return euclideanController3D != null
				&& euclideanController3D.isZSpace();
	}

	@Override
	public int getToolbarPosition() {
		if (useHugeGuiForInput3D()) {
			return SwingConstants.WEST;
		}

		return super.getToolbarPosition();
	}

	@Override
	public int getGUIFontSize() {
		int size = super.getGUIFontSize();
		if (size < 24 && useHugeGuiForInput3D()) {
			return 24;
		}

		return size;
	}

	@Override
	public int getScaledIconSize() {
		int size = super.getScaledIconSize();
		if (size < 36 && useHugeGuiForInput3D()) {
			return 36;
		}
		return size;
	}

	@Override
	protected SettingsUpdaterBuilder newSettingsUpdaterBuilder() {
		return new SettingsUpdaterBuilder(this)
				.withFontSettingsUpdater(new FontSettingsUpdaterD(this));
	}
}
