package org.geogebra.common.io;

import org.geogebra.common.AppCommonFactory;
import org.geogebra.common.jre.headless.AppCommon;
import org.junit.Before;
import org.junit.Test;

public class DeleteInMatrixTest {
	private static MatrixChecker checker;
	private static final AppCommon app = AppCommonFactory.create();

	@Before
	public void setUp() {
		checker = new MatrixChecker(app);
	}

	@Test
	public void testSelectionShouldDelete1OnlyFromLeft() {
		checker.shiftRightTwice()
				.shouldDeleteOnly(1);
	}

	@Test
	public void testSelectionShouldDelete1OnlyFromRight() {
		checker.right()
				.shiftLeftTwice()
				.shouldDeleteOnly(1);
	}

	@Test
	public void testSelectionShouldDelete2OnlyFromLeft() {
		checker.rightTimes(2)
				.shiftRightTwice()
				.shouldDeleteOnly(2);
	}

	@Test
	public void testSelectionShouldDelete2OnlyFromRight() {
		checker.rightTimes(3)
				.shiftLeftTwice()
				.shouldDeleteOnly(2);
	}

	@Test
	public void testSelectionShouldDelete3OnlyFromLeft() {
		checker.rightTimes(4)
				.shiftRightTwice()
				.shouldDeleteOnly(3);
	}

	@Test
	public void testSelectionShouldDelete3OnlyFromRight() {
		checker.rightTimes(5)
				.shiftLeftTwice()
				.shouldDeleteOnly(3);
	}

	@Test
	public void testSelectionShouldDelete4OnlyFromLeft() {
		checker.down()
				.shiftRightTwice()
				.shouldDeleteOnly(4);
	}

	@Test
	public void testSelectionShouldDelete4OnlyFromRight() {
		checker.down()
				.right()
				.shiftLeftTwice()
				.shouldDeleteOnly(4);
	}

	@Test
	public void testSelectionShouldDelete5OnlyFromRight() {
		checker.down()
				.rightTimes(3)
				.shiftLeftTwice()
				.shouldDeleteOnly(5);
	}

	@Test
	public void testSelectionShouldDelete5OnlyFromLeft() {
		checker.down()
				.rightTimes(4)
				.shiftLeftTwice()
				.shouldDeleteOnly(5);
	}

	@Test
	public void testSelectionShouldDelete6OnlyFromRight() {
		checker.down()
				.rightTimes(4)
				.shiftRightTwice()
				.shouldDeleteOnly(6);
	}

	@Test
	public void testSelectionShouldDelete6OnlyFromLeft() {
		checker.down()
				.rightTimes(5)
				.shiftLeftTwice()
				.shouldDeleteOnly(6);
	}

	@Test
	public void testSelectionShouldDelete7OnlyFromLeft() {
		checker.downTimes(2)
				.shiftRightTwice()
				.shouldDeleteOnly(7);
	}

	@Test
	public void testSelectionShouldDelete7OnlyFromRight() {
		checker.downTimes(2)
				.right()
				.shiftLeftTwice()
				.shouldDeleteOnly(7);
	}

	@Test
	public void testSelectionShouldDelete8OnlyFromLeft() {
		checker.downTimes(2)
				.rightTimes(2)
				.shiftRightTwice()
				.shouldDeleteOnly(8);
	}

	@Test
	public void testSelectionShouldDelete8OnlyFromRight() {
		checker.downTimes(2)
				.rightTimes(3)
				.shiftLeftTwice()
				.shouldDeleteOnly(8);
	}

	@Test
	public void testSelectionShouldDelete9OnlyFromLeft() {
		checker.downTimes(2)
				.rightTimes(4)
				.shiftRightTwice()
				.shouldDeleteOnly(9);
	}

	@Test
	public void testSelectionShouldDelete9OnlyFromRight() {
		checker.downTimes(2)
				.rightTimes(5)
				.shiftLeftTwice()
				.shouldDeleteOnly(9);
	}
}
