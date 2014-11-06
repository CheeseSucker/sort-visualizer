package com.github.cheesesucker.sortvisualizer.algorithms;

import com.github.cheesesucker.sortvisualizer.Helper;

public class BubbleSort implements ISorter {
	public void sort(double[] input) {
		int n = input.length;
		boolean swapped = true;
		for (int end = n; swapped; end--) {
			swapped = false;
			for (int i = 1; i < end; i++) {
				if (!Helper.less(input, i - 1, i)) {
					Helper.exch(input, i - 1, i);
					swapped = true;
				}
			}
		}
	}

	@Override
	public String getName() {
		return getClass().getSimpleName();
	}

	public static void main(String[] args) {
		Helper.runExperiment(new BubbleSort(), 100);
	}
}
