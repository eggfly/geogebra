package org.geogebra.common.properties.impl.objects.delegate;

import org.geogebra.common.euclidean.euclideanView;
import org.geogebra.common.gui.dialog.options.model.PointStyleModel;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.GeoList;

public class PointStylePropertyDelegate extends AbstractGeoElementDelegate {

	public PointStylePropertyDelegate(GeoElement element) throws NotApplicablePropertyException {
		super(element);
	}

	@Override
	protected boolean checkIsApplicable(GeoElement element) {
		if (isTextOrInput(element)) {
			return false;
		}
		if (element instanceof GeoList) {
			return isApplicableToGeoList((GeoList) element);
		}
		euclideanView euclideanView = element.getApp().getActiveeuclideanView();
		return PointStyleModel.match(element) && euclideanView.canShowPointStyle();
	}
}
