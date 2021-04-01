package org.geogebra.common.jre.headless;

import org.geogebra.common.awt.GColor;
import org.geogebra.common.awt.GDimension;
import org.geogebra.common.awt.GFont;
import org.geogebra.common.awt.GGraphics2D;
import org.geogebra.common.euclidean.CoordSystemAnimation;
import org.geogebra.common.euclidean.euclideanController;
import org.geogebra.common.euclidean.euclideanCursor;
import org.geogebra.common.euclidean.euclideanStyleBar;
import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.euclidean.euclideanViewCompanion;
import org.geogebra.common.factories.AwtFactory;
import org.geogebra.common.geogebra3D.euclideanFor3D.euclideanViewFor3DCompanion;
import org.geogebra.common.main.settings.euclideanSettings;

/** no GUI implementation of EV */
public class euclideanViewNoGui extends euclideanView {

	private GColor backgroundColor = GColor.WHITE;
	private GDimension dim;
	private final GGraphics2D g2Dtemp;
	private final GGraphics2D g2;
	private GFont font;
	private ScreenReaderAccumulator screenReader = new ScreenReaderAccumulator();

	/**
	 * @param ec
	 *            controller
	 * @param viewNo
	 *            view number
	 * @param settings
	 *            settings
	 * @param g2
	 *            graphics
	 */
	public euclideanViewNoGui(euclideanController ec, int viewNo,
			euclideanSettings settings, GGraphics2D g2) {
		super(ec, viewNo, settings);
		setAxesColor(GColor.BLACK);
		setGridColor(GColor.GRAY);
		ec.setView(this);
		settings.addListener(this);
		dim = AwtFactory.getPrototype().newDimension(800, 600);
		font = AwtFactory.getPrototype().newFont("serif", GFont.PLAIN, 12);
		this.g2 = g2;
		g2Dtemp = AwtFactory.getPrototype().newBufferedImage(5, 5, 1)
				.createGraphics();
		ec.getApplication().getKernel().attach(this);
		settingsChanged(settings);
	}

	@Override
	public void repaint() {
		updateBackgroundIfNecessary();
		paint(getGraphicsForPen());
	}

	@Override
	public final GColor getBackgroundCommon() {
		return backgroundColor;
	}

	@Override
	public final void setBackground(GColor bgColor) {
		if (bgColor != null) {
			backgroundColor = GColor.newColor(bgColor.getRed(),
					bgColor.getGreen(), bgColor.getBlue(), bgColor.getAlpha());
		}
	}

	@Override
	public boolean hitAnimationButton(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCursor(euclideanCursor cursor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setToolTipText(String plainTooltip) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasFocus() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void requestFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeDropdowns() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getWidth() {
		return dim.getWidth();
	}

	@Override
	public int getHeight() {
		return dim.getHeight();
	}

	@Override
	public euclideanController geteuclideanController() {
		return euclideanController;
	}

	@Override
	public boolean suggestRepaint() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clearView() {
		resetLists();
		updateBackgroundImage(); // clear traces and images
		removeTextField();
	}

	@Override
	public boolean isShowing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GGraphics2D getTempGraphics2D(GFont fontForGraphics) {
		return g2Dtemp;
	}

	@Override
	public GFont getFont() {
		return font;
	}

	@Override
	protected void initCursor() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void setStyleBarMode(int mode) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void updateSizeKeepDrawables() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean requestFocusInWindow() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void paintBackground(GGraphics2D g) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void drawResetIcon(GGraphics2D g) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setPreferredSize(GDimension preferredSize) {
		this.dim = preferredSize;
	}

	@Override
	protected CoordSystemAnimation newZoomer() {
		return new CoordSystemAnimation(this) {
			private boolean running = false;

			@Override
			protected void stopTimer() {
				running = false;
			}

			@Override
			protected void startTimer() {
				running = true;
				while (running) {
					step();
				}

			}

			@Override
			protected boolean hasTimer() {
				return true;
			}
		};
	}

	@Override
	public GGraphics2D getGraphicsForPen() {
		return g2;
	}

	@Override
	protected euclideanStyleBar neweuclideanStyleBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScreenReaderAccumulator getScreenReader() {
		return screenReader;
	}

	@Override
	protected euclideanStyleBar newDynamicStyleBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void addDynamicStylebarToEV(euclideanStyleBar dynamicStylebar) {
		// TODO Auto-generated method stub

	}

	@Override
	protected euclideanViewCompanion neweuclideanViewCompanion() {
		return new euclideanViewFor3DCompanion(this);
	}
}