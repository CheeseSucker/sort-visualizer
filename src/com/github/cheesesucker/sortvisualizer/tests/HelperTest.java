package com.github.cheesesucker.sortvisualizer.tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.cheesesucker.sortvisualizer.Helper;


public class HelperTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testExch() {
		double[] input = {1.0, 2.0, 3.0};
		Helper.exch(input, 1, 2);
		assertArrayEquals(new double[]{1.0, 3.0, 2.0}, input, 0.000001);
	}

	@Test
	public void testLess_EqualReturnsFalse() {
		assertFalse(Helper.less(3, 3));
	}
	
	@Test
	public void testLess_LessReturnsTrue() {
		assertTrue(Helper.less(2, 3));
	}
	
	@Test
	public void testLess_GreaterReturnsFalse() {
		assertFalse(Helper.less(4, 3));
	}
}
