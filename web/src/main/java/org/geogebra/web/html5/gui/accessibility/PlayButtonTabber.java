package org.geogebra.web.html5.gui.accessibility;

import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.gui.AccessibilityGroup;
import org.geogebra.common.gui.MayHaveFocus;

public class PlayButtonTabber implements MayHaveFocus {
	private final euclideanView view;

	public PlayButtonTabber(euclideanView view) {
		this.view = view;
	}

	private boolean isPlayVisible() {
		return view.getKernel().needToShowAnimationButton()
				&& view.drawPlayButtonInThisView();
	}

	@Override
	public boolean focusIfVisible(boolean reverse) {
		if (isPlayVisible()) {
			view.setAnimationButtonSelected(true);
			return true;
		}
		return false;
	}

	@Override
	public boolean hasFocus() {
		return view.isAnimationButtonSelected();
	}

	@Override
	public boolean focusNext() {
		view.setAnimationButtonSelected(false);
		return false;
	}

	@Override
	public boolean focusPrevious() {
		view.setAnimationButtonSelected(false);
		return false;
	}

	@Override
	public AccessibilityGroup getAccessibilityGroup() {
		return AccessibilityGroup.getViewGroup(view.getViewID());
	}

	@Override
	public AccessibilityGroup.ViewControlId getViewControlId() {
		return AccessibilityGroup.ViewControlId.PLAY_BUTTON;
	}
}
