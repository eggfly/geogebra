package org.geogebra.common.kernel.geos;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.geogebra.common.BaseUnitTest;
import org.geogebra.common.gui.view.algebra.EvalInfoFactory;
import org.geogebra.common.kernel.commands.EvalInfo;
import org.geogebra.common.main.App;
import org.geogebra.test.UndoRedoTester;
import org.junit.Before;
import org.junit.Test;

public class SliderTest extends BaseUnitTest {

	private EvalInfo info;

	@Before
	public void setUp() {
		info = EvalInfoFactory.getEvalInfoForAV(getApp(), true);
	}

	@Test
	public void setShowExtendedAV() {
		GeoNumeric slider = add("a = 1", info);
		slider.initAlgebraSlider();
		slider.setShowExtendedAV(false);
		assertThat(slider.showIneuclideanView(), is(true));
	}

	@Test
	public void testMarbleFunctionalityWithUndoRedo() {
		App app = getApp();
		UndoRedoTester undoRedo = new UndoRedoTester(app);
		undoRedo.setupUndoRedo();

		GeoNumeric slider = add("a = 1", info);
		app.storeUndoInfo();
		slider.seteuclideanVisible(true);
		app.storeUndoInfo();
		slider.setShowExtendedAV(false);
		app.storeUndoInfo();
		slider.seteuclideanVisible(false);
		app.storeUndoInfo();
		slider.seteuclideanVisible(true);
		app.storeUndoInfo();

		slider = undoRedo.getAfterUndo("a");
		assertThat(slider.iseuclideanVisible(), is(false));

		slider = undoRedo.getAfterUndo("a");
		assertThat(slider.iseuclideanVisible(), is(true));

		slider = undoRedo.getAfterUndo("a");
		assertThat(slider.isShowingExtendedAV(), is(true));

		slider = undoRedo.getAfterRedo("a");
		assertThat(slider.isShowingExtendedAV(), is(false));

		slider = undoRedo.getAfterRedo("a");
		assertThat(slider.iseuclideanVisible(), is(false));

		slider = undoRedo.getAfterRedo("a");
		assertThat(slider.iseuclideanVisible(), is(true));
	}

	@Test
	public void removeSlider() {
		App app = getApp();
		UndoRedoTester undoRedo = new UndoRedoTester(app);
		undoRedo.setupUndoRedo();

		GeoNumeric slider = add("a = 1", info);
		app.storeUndoInfo();
		slider.createSlider();
		app.storeUndoInfo();
		slider.seteuclideanVisible(true);
		app.storeUndoInfo();
		slider.removeSlider();
		app.storeUndoInfo();
		assertThat(slider.isSeteuclideanVisible(), is(false));

		slider = undoRedo.getAfterUndo("a");
		assertThat(slider.iseuclideanVisible(), is(true));
	}
}