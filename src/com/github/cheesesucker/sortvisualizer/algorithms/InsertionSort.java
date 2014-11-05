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
		double[] input = Helper.generateInput(100);
		Helper.printArray(input);
		
		InsertionSort sorter = new InsertionSort();
		sorter.sort(input);
		
		Helper.printArray(input);
		
		if (!Helper.isSorted(input)) {
			throw new RuntimeException("Postcondition failed: Array is not sorted!");
		} else {
			System.out.println("sorted");
		}
	}
}
