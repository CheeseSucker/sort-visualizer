package com.github.cheesesucker.sortvisualizer.algorithms;

import com.github.cheesesucker.sortvisualizer.Helper;

public class QuickSort implements ISorter {
	public void sort(double[] input) {
		// StdRandom.shuffle(input);
		sort(input, 0, input.length - 1);
	}
	
	private void sort(double[] input, int lo, int hi) {
		if (hi <= lo) return;
		int j = partition(input, lo, hi);
		sort(input, lo, j-1);
		sort(input, j+1, hi);
	}
	
	private int partition(double[] input, int lo, int hi) {
		int i = lo;
		int j = hi+1;
		int anchor = lo;
		while (true) {
			while (Helper.less(input, ++i, anchor)) {
				if (i == hi) break;
			}
			while (Helper.less(input, anchor, --j)) {
				if (j == lo) break;
			}
			if (i >= j) break;
			Helper.exch(input, i, j);
		}
		Helper.exch(input, lo, j);
		return j;
	}
	
	@Override
	public String getName() {
		return getClass().getSimpleName();
	}
	
	public static void main(String[] args) {
		Helper.runExperiment(new QuickSort(), 100);
	}
}
