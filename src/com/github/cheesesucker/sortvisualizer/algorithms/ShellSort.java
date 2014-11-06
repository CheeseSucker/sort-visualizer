package com.github.cheesesucker.sortvisualizer.algorithms;

import com.github.cheesesucker.sortvisualizer.Helper;

public class ShellSort implements ISorter {
	public void sort(double[] input) {
		int N = input.length;
		int h = 1;
		while (h < N / 3) {
			h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
		}
		while (h >= 1) { // h-sort the array.
			for (int i = h; i < N; i++) { // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
				for (int j = i; j >= h && Helper.less(input, j, j - h); j -= h) {
					Helper.exch(input, j, j - h);
				}
			}
			h = h / 3;
		}
	}
	
	@Override
	public String getName() {
		return getClass().getSimpleName();
	}

	public static void main(String[] args) {
		Helper.runExperiment(new ShellSort(), 100);
	}
}
