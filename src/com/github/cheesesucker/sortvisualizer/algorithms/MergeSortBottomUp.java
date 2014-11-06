package com.github.cheesesucker.sortvisualizer.algorithms;

import com.github.cheesesucker.sortvisualizer.Helper;

public class MergeSortBottomUp implements ISorter {
	public void sort(double[] input) {
		int N = input.length;
		for (int sz = 2; sz < N*2; sz *= 2) {
			for (int lo = 0; lo < N; lo += sz) {
				int hi = Math.min(lo + sz, N);
				int mid = lo + sz / 2;
				MergeSort.merge(input, lo, mid, hi);
			}
		}
	}
	
	@Override
	public String getName() {
		return getClass().getSimpleName();
	}
	
	public static void main(String[] args) {
		Helper.runExperiment(new MergeSortBottomUp(), 100);
	}
}
