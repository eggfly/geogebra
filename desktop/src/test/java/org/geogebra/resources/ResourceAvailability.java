package org.geogebra.resources;

import java.net.URL;

import javax.swing.JPanel;

import org.geogebra.common.euclidean.euclideanConstants;
import org.geogebra.common.util.StringUtil;
import org.geogebra.desktop.geogebra3D.gui.GuiResources3D;
import org.geogebra.desktop.util.GuiResourcesD;
import org.geogebra.desktop.util.ImageManagerD;
import org.geogebra.desktop.util.ImageResourceD;
import org.geogebra.desktop.util.StringUtilD;
import org.junit.Assert;
import org.junit.Test;

public class ResourceAvailability {
	@Test
	public void checkGuiResources() {
		for (GuiResourcesD res : GuiResourcesD.values()) {
			String fn = res.getFilename();
			if (!fn.startsWith("/org")) {
				fn = "/org/geogebra/desktop/" + fn;
			}
			URL url = ResourceAvailability.class.getResource(fn);
			Assert.assertNotNull("" + res, url);
		}
	}

	@Test
	public void checkGuiResources3D() {
		for (GuiResources3D res : GuiResources3D.values()) {
			String fn = res.getFilename();
			if (!fn.startsWith("/org")) {
				fn = "/org/geogebra/desktop/geogebra3D" + fn;
			}
			URL url = ResourceAvailability.class.getResource(fn);
			Assert.assertNotNull("" + res, url);
		}
	}

	@Test
	public void checkToolIcons() {
		StringUtil.setPrototypeIfNull(new StringUtilD());
		ImageManagerD man = new ImageManagerD(new JPanel());
		StringBuilder missing = new StringBuilder();

		for (int i = 0; i < 1000; i++) {
			String modeText = euclideanConstants.getModeTextSimple(i);

			if (modeText.isEmpty()) {
				continue;
			}
			switch (i) {
			case euclideanConstants.MODE_SELECTION_LISTENER:
			case euclideanConstants.MODE_PEN_PANEL:
			case euclideanConstants.MODE_TOOLS_PANEL:
			case euclideanConstants.MODE_MEDIA_PANEL:
			case euclideanConstants.MODE_VIDEO:
			case euclideanConstants.MODE_AUDIO:
			case euclideanConstants.MODE_GRAPHING:
			case euclideanConstants.MODE_EXTENSION:
			case euclideanConstants.MODE_TABLE:
			case euclideanConstants.MODE_EQUATION:
			case euclideanConstants.MODE_CAMERA:
			case euclideanConstants.MODE_PDF:
			case euclideanConstants.MODE_GRASPABLE_MATH:
			case euclideanConstants.MODE_CAS:
			case euclideanConstants.MODE_SURFACE_OF_REVOLUTION:
			case euclideanConstants.MODE_FREEHAND_FUNCTION:
			case euclideanConstants.MODE_MASK:
				continue;
			default:
				ImageResourceD res = man.getToolImageResource(modeText);
				URL url = ResourceAvailability.class.getResource(res
						.getFilename());
				if (url == null) {
					missing.append(modeText + ",");
				}
			}

		}

		Assert.assertEquals(missing.toString(), missing.toString(), "");
	}
}
