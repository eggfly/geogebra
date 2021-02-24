package org.geogebra.common.io;

import org.geogebra.common.main.App;

import com.himamis.retex.editor.share.event.KeyEvent;
import com.himamis.retex.editor.share.util.JavaKeyCodes;

class MatrixChecker extends EditorChecker {
	private static final String matix3x3 = "{{1,2,3},{4,5,6},{7,8,9}}";

	protected MatrixChecker(App app) {
		super(app);
		matrixFromParser(matix3x3);
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

	public MatrixChecker leftTimes(int times) {
		return (MatrixChecker) repeatKey(JavaKeyCodes.VK_LEFT, times);
	}

	public MatrixChecker shiftRightTwice() {
		return shiftOn().rightTimes(2);
	}

	public MatrixChecker shiftLeftTwice() {
		return shiftOn().leftTimes(2);
	}

	public void shouldDeleteOnly(Integer number) {
		delete()
		.checkAsciiMath(matix3x3.replace(number.toString(), ""));

	}
}
