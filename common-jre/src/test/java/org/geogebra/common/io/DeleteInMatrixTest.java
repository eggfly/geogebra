package org.geogebra.common.io;

import org.geogebra.common.AppCommonFactory;
import org.geogebra.common.jre.headless.AppCommon;
import org.geogebra.common.main.App;
import org.junit.Before;
import org.junit.Test;

import com.himamis.retex.editor.share.event.KeyEvent;
import com.himamis.retex.editor.share.util.JavaKeyCodes;

public class DeleteInMatrixTest {
	private static final String matix3x3 = "{{1,2,3}, {4,5,6}, {7,8,9}}";
	private static MatrixChecker checker;
	private static AppCommon app = AppCommonFactory.create();

	private class MatrixChecker extends EditorChecker {

		protected MatrixChecker(App app) {
			super(app);
		}

		public MatrixChecker shiftOn() {
			return (MatrixChecker) this.setModifiers(KeyEvent.SHIFT_MASK);
		}

		public MatrixChecker right() {
			return (MatrixChecker) typeKey(JavaKeyCodes.VK_RIGHT);
		}

		public MatrixChecker down() {
			return (MatrixChecker) typeKey(JavaKeyCodes.VK_DOWN);
		}


		public MatrixChecker delete() {
			return (MatrixChecker) typeKey(JavaKeyCodes.VK_DELETE);
		}

		public MatrixChecker rightTimes(int times) {
			return (MatrixChecker) repeatKey(JavaKeyCodes.VK_RIGHT, times);
		}

		public MatrixChecker downTimes(int times) {
			return (MatrixChecker) repeatKey(JavaKeyCodes.VK_DOWN, times);
		}

	}
	@Before
	public void setUp() {
		checker = new MatrixChecker(app);
		checker.matrixFromParser(matix3x3);
	}

	@Test
	public void testSelectionShouldDelete1Only() {
		checker.shiftOn()
				.rightTimes(2)
				.delete()
				.checkAsciiMath("{{,2,3},{4,5,6},{7,8,9}}");
	}

	@Test
	public void testSelectionShouldDelete2Only() {
		checker.rightTimes(2)
				.shiftOn()
				.rightTimes(2)
				.delete()
				.checkAsciiMath("{{1,,3},{4,5,6},{7,8,9}}");
	}

	@Test
	public void testSelectionShouldDelete3Only() {
		checker.rightTimes(4)
				.shiftOn()
				.rightTimes(2)
				.delete()
				.checkAsciiMath("{{1,2,},{4,5,6},{7,8,9}}");
	}

	@Test
	public void testSelectionShouldDelete4Only() {
		checker.down()
				.shiftOn()
				.rightTimes(2)
				.delete()
				.checkAsciiMath("{{1,2,3},{,5,6},{7,8,9}}");
	}

	@Test
	public void testSelectionShouldDelete5Only() {
		checker.down()
				.rightTimes(2)
				.shiftOn()
				.rightTimes(2)
				.delete()
				.checkAsciiMath("{{1,2,3},{4,,6},{7,8,9}}");
	}

	@Test
	public void testSelectionShouldDelete6Only() {
		checker.down()
				.rightTimes(4)
				.shiftOn()
				.rightTimes(2)
				.delete()
				.checkAsciiMath("{{1,2,3},{4,5,},{7,8,9}}");
	}

	@Test
	public void testSelectionShouldDelete7Only() {
		checker.downTimes(2)
				.shiftOn()
				.rightTimes(2)
				.delete()
				.checkAsciiMath("{{1,2,3},{4,5,6},{,8,9}}");
	}

	@Test
	public void testSelectionShouldDelete8Only() {
		checker.downTimes(2)
				.rightTimes(2)
				.shiftOn()
				.rightTimes(2)
				.delete()
				.checkAsciiMath("{{1,2,3},{4,5,6},{7,,9}}");
	}

	@Test
	public void testSelectionShouldDelete9Only() {
		checker.downTimes(2)
				.rightTimes(4)
				.shiftOn()
				.rightTimes(2)
				.delete()
				.checkAsciiMath("{{1,2,3},{4,5,6},{7,8,}}");
	}
}
