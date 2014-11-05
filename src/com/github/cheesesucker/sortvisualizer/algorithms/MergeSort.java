package com.github.cheesesucker.sortvisualizer.algorithms;

import com.github.cheesesucker.sortvisualizer.Helper;

public class MergeSort implements ISorter {
	private final int cutoff;
	
	public MergeSort(int cutoff) {
		this.cutoff = cutoff;
	}
	
	public void sort(double[] input) {
		sort(input, 0, input.length);
	}
	
	private void sort(double[] input, int lo, int hi) {
		if (hi - lo <= cutoff) {
			insertionSort(input, lo, hi);
			return;
		}
		int mid = lo + (hi - lo) / 2;
		sort(input, lo, mid);
		sort(input, mid, hi);
		merge(input, lo, mid, hi);
	}

	private void insertionSort(double[] input, int lo, int hi) {
		// Insertion sort
		for (int i = lo + 1; i < hi; i++) {
			for (int j = i; j > lo && Helper.less(input, j, j-1); j--) {
				Helper.exch(input, j, j-1);
			}
		}
	}
	
	@Override
	public String getName() {
		return getClass().getSimpleName() + " Cutoff=" + cutoff;
	}
	
	public static void merge(double[] input, int lo, int mid, int hi) {
		// Must use auxilliary array. In-place merge is a lot harder. See book.
		double[] aux = new double[hi - lo];
		
		// Merge into aux
		int i = lo;
		int j = mid;
		for (int m = 0; m < aux.length; m++) {
			if (i >= mid) {
				aux[m] = input[j++];
			} else if (j >= hi) {
				aux[m] = input[i++];
			} else if (Helper.less(input, i, j)) {
				aux[m] = input[i++];
			} else {
				aux[m] = input[j++];
			}
		}
		
		// Copy back
		for (int m = 0; m < aux.length; m++) {
			input[m + lo] = aux[m];
			Helper.exch(input, m + lo, m + lo); // Hack to draw the copy process
		}
	}

	public static void main(String[] args) {
		double[] input = Helper.generateInput(100);
		Helper.printArray(input);
		
		MergeSort sorter = new MergeSort(10);
		sorter.sort(input);
		
		Helper.printArray(input);
		
		if (!Helper.isSorted(input)) {
			throw new RuntimeException("Postcondition failed: Array is not sorted!");
		} else {
			System.out.println("sorted");
		}
	}
}
