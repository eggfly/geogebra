package org.geogebra.web.html5.euclidean;

import org.geogebra.common.awt.GPoint;
import org.geogebra.common.euclidean.euclideanStyleBar;
import org.geogebra.common.euclidean.euclideanViewInterfaceSlim;
import org.geogebra.common.euclidean.Hits;
import org.geogebra.common.euclidean.Previewable;
import org.geogebra.common.euclidean.event.PointerEventType;
import org.geogebra.common.main.App;
import org.geogebra.common.main.App.ExportType;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

/**
 * interface for euclideanViewW / euclideanView3DW
 * 
 * @author mathieu
 *
 */
public interface euclideanViewWInterface extends euclideanViewInterfaceSlim {

	/**
	 * 
	 * @return canvas
	 */
	Element getCanvasElement();

	Hits getHits();

	boolean hasStyleBar();

	euclideanStyleBar getStyleBar();

	int getViewWidth();

	/**
	 * @param x
	 *            event x-coord
	 * @param y
	 *            event y-coord
	 * @param type
	 *            event type
	 * @return whether textfield was clicked
	 */
	boolean textfieldClicked(int x, int y, PointerEventType type);

	@Override
	int getViewID();

	double getXZero();

	double getYZero();

	@Override
	double getXscale();

	void setCoordSystem(double xZero, double yZero, double xscale,
	        double newRatioY);

	@Override
	double getYscale();

	void rememberOrigins();

	void translateCoordSystemInPixels(int dx, int dy, int dz);

	void setHits(GPoint gPoint, PointerEventType touch);

	Previewable getPreviewDrawable();

	void updatePreviewableForProcessMode();

	int getAbsoluteLeft();

	int getAbsoluteTop();

	GGraphics2DWI getG2P();

	void resetPointerEventHandler();

	String getExportImageDataUrl(double scale, boolean transparent,
			ExportType format, boolean greyscale);

	String getExportImageDataUrl(double scale, boolean transparent,
			boolean greyscale);

	App getApplication();

	String getCanvasBase64WithTypeString();

	void requestFocus();

	void setAltText();

	String getExportSVG(double scale, boolean transparency);

	String getExportPDF(double scale);

	int getExportWidth();

	int getExportHeight();

	boolean isAttached();

	void add(Widget box, GPoint gPoint);

	Object getExportCanvas();
}
