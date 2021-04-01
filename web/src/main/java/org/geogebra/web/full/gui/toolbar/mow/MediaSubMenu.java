package org.geogebra.web.full.gui.toolbar.mow;

import org.geogebra.common.euclidean.euclideanConstants;
import org.geogebra.common.gui.AccessibilityGroup;
import org.geogebra.common.gui.toolbar.ToolBar;
import org.geogebra.web.html5.Browser;
import org.geogebra.web.html5.main.AppW;

/**
 * Submenu for media (i.e. photo, video, ...)
 * 
 * @author Alicia Hofstaetter
 *
 */
public class MediaSubMenu extends SubMenuPanel {
	/**
	 * @param app
	 *            application
	 */
	public MediaSubMenu(AppW app) {
		super(app);
	}

	@Override
	protected void createContentPanel() {
		super.createContentPanel();
		boolean isEnabled = Browser.isGraspableMathEnabled();
		super.createPanelRow(ToolBar.getMOWMediaToolBarDefString(isEnabled));
		makeButtonsAccessible(AccessibilityGroup.NOTES_TOOL_MEDIA);
	}

	@Override
	public int getFirstMode() {
		return euclideanConstants.MODE_MEDIA_TEXT;
	}

	@Override
	public boolean isValidMode(int mode) {
		return mode == euclideanConstants.MODE_MEDIA_TEXT
				|| mode == euclideanConstants.MODE_IMAGE
				|| mode == euclideanConstants.MODE_CAMERA
				|| mode == euclideanConstants.MODE_VIDEO
				|| mode == euclideanConstants.MODE_AUDIO
				|| mode == euclideanConstants.MODE_GRAPHING
				|| mode == euclideanConstants.MODE_PDF
				|| mode == euclideanConstants.MODE_EXTENSION
				|| mode == euclideanConstants.MODE_TABLE
				|| mode == euclideanConstants.MODE_EQUATION
				|| mode == euclideanConstants.MODE_GRASPABLE_MATH
				|| mode == euclideanConstants.MODE_CAS;
	}
}
