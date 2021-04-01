package org.geogebra.common.gui.menu.impl;

import org.geogebra.common.GeoGebraConstants;
import org.geogebra.common.gui.menu.DrawerMenu;
import org.geogebra.common.gui.menu.MenuItemGroup;

/**
 * Creates drawer menus for apps when in exam mode.
 */
public class SavingExamDrawerMenuFactory extends AbstractDrawerMenuFactory {

	/**
	 * Create a new ExamDrawerMenuFactory.
	 * @param version version of the app
	 */
	public SavingExamDrawerMenuFactory(GeoGebraConstants.Version version) {
		this(version, version.equals(GeoGebraConstants.Version.SUITE));
	}

	/**
	 * Create a new ExamDrawerMenuFactory.
	 * @param version version of the app
	 * @param isSuiteApp whether it is the Suite app
	 */
	public SavingExamDrawerMenuFactory(GeoGebraConstants.Version version, boolean isSuiteApp) {
		super(version, isSuiteApp);
	}

	@Override
	public DrawerMenu createDrawerMenu() {
		MenuItemGroup group =
				new MenuItemGroupImpl(removeNulls(
						clearConstruction(),
						openFile(),
						saveFile(),
						showSwitchCalculator(),
						showExamLog(),
						exitExamMode()));
		String title = getMenuTitle();
		return new DrawerMenuImpl(title, group);
	}
}
