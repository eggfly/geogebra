package org.geogebra.common.euclidean;

/**
 * Interface for panel containing euclideanView
 */
public interface euclideanViewJPanel {
	/**
	 * @return true if the panel is focused
	 */
	public boolean hasFocus();

	/**
	 * Repaint the panel
	 */
	public void repaint();

	/**
	 * @param f
	 *            true to make the panel focusable
	 */
	public void setFocusable(boolean f);

	/**
	 * Removes all components from the panel
	 */
	public void removeAll();
}
