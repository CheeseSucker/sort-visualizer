package com.github.cheesesucker.sortvisualizer.algorithms;

import com.github.cheesesucker.sortvisualizer.Helper;

public class InsertionSort implements ISorter {
	public void sort(double[] input) {
		for (int i = 1; i < input.length; i++) {
			for (int j = i; j > 0 && Helper.less(input, j, j-1); j--) {
				Helper.exch(input, j-1, j);
			}
		}
	}
	
	@Override
	public String getName() {
		return getClass().getSimpleName();
	}

	public static void main(String[] args) {
		Helper.runExperiment(new InsertionSort(), 100);
	}
}
