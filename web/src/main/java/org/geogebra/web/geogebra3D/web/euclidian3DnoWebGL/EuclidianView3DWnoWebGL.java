package org.geogebra.web.geogebra3D.web.euclidean3DnoWebGL;

import java.util.HashMap;

import org.geogebra.common.awt.GBufferedImage;
import org.geogebra.common.awt.GColor;
import org.geogebra.common.geogebra3D.euclidean3D.euclideanController3D;
import org.geogebra.common.geogebra3D.euclidean3D.openGL.Renderer;
import org.geogebra.common.main.settings.euclideanSettings;
import org.geogebra.common.util.debug.Log;
import org.geogebra.web.geogebra3D.web.euclidean3D.euclideanView3DW;
import org.geogebra.web.html5.gawt.GBufferedImageW;
import org.geogebra.web.html5.main.AppW;
import org.geogebra.web.html5.main.GgbFile;
import org.geogebra.web.html5.util.ImageLoadCallback;
import org.geogebra.web.html5.util.ImageWrapper;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.Style.Unit;

/**
 * (dummy) 3D view for browsers that don't support webGL
 * 
 * @author mathieu
 *
 */
public class euclideanView3DWnoWebGL extends euclideanView3DW {

	private GBufferedImage thumb;

	/**
	 * constructor
	 * 
	 * @param ec
	 *            controller
	 * @param settings
	 *            settings
	 */
	public euclideanView3DWnoWebGL(euclideanController3D ec,
	        euclideanSettings settings) {
		super(ec, settings);
		setCurrentFile(((AppW) ec.getApplication()).getCurrentFile());
	}

	@Override
	protected Renderer createRenderer() {
		return new RendererWnoWebGL(this);
	}

	@Override
	public void repaintView() {
		// repaint will be done only when resized
	}

	@Override
	public void repaint() {
		if (thumb != null) {
			getG2P().scale(
					(double) getG2P().getOffsetWidth() / thumb.getWidth(),
					(double) getG2P().getOffsetHeight() / thumb.getHeight());
			getG2P().drawImage(thumb, 0, 0);
		}

		getG2P().setColor(GColor.BLACK);
		if (!getApplication().isScreenshotGenerator()) {
			getG2P().drawString(
					getApplication().getLocalization().getMenu("NoWebGL"), 10,
					20);
		}
	}

	@Override
	public void setCurrentFile(GgbFile f) {

		Log.debug("No 3D:Set thumbnail");
		HashMap<String, String> file = f;
		if (file != null && file.get("geogebra_thumbnail.png") != null) {
			ImageElement img = Document.get().createImageElement();
			img.setSrc(file.get("geogebra_thumbnail.png"));
			thumb = new GBufferedImageW(img);
			ImageWrapper.nativeon(img, "load", new ImageLoadCallback() {

				@Override
				public void onLoad() {
					repaint();

				}
			});
			Log.debug("Set thumbnail done");
		}

		repaint();
	}

	/**
	 * Update the image size
	 */
	public void onResize() {
		getG2P().setCoordinateSpaceSize(this.getWidth(), this.getHeight());
		getG2P().getElement().getParentElement().getStyle()
				.setWidth(getG2P().getCoordinateSpaceWidth(), Unit.PX);
		getG2P().getElement().getParentElement().getStyle()
				.setHeight(getG2P().getCoordinateSpaceHeight(), Unit.PX);
	}

}
