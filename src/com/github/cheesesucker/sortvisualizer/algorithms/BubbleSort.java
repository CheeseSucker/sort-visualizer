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
		double[] input = Helper.generateInput(100);
		Helper.printArray(input);
		
		BubbleSort sorter = new BubbleSort();
		sorter.sort(input);
		
		Helper.printArray(input);
		
		if (!Helper.isSorted(input)) {
			throw new RuntimeException("Postcondition failed: Array is not sorted!");
		} else {
			System.out.println("sorted");
		}
	}
}
