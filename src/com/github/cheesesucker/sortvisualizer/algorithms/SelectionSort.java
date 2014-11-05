package com.github.cheesesucker.sortvisualizer.algorithms;

import com.github.cheesesucker.sortvisualizer.Helper;

public class SelectionSort implements ISorter {
	public void sort(double[] input) {
		for (int i = 0; i < input.length; i++) {
			// Find minimimum value
			int lowest = i;
			for (int j = i; j < input.length; j++) {
				if (Helper.less(input, j, lowest)) {
					lowest = j;
				}
			}
			Helper.exch(input, lowest, i);
		}
	}
	
	@Override
	public String getName() {
		return getClass().getSimpleName();
	}

	public static void main(String[] args) {
		double[] input = Helper.generateInput(100);
		Helper.printArray(input);
		
		SelectionSort sorter = new SelectionSort();
		sorter.sort(input);
		
		Helper.printArray(input);
		
		if (!Helper.isSorted(input)) {
			throw new RuntimeException("Postcondition failed: Array is not sorted!");
		} else {
			System.out.println("sorted");
		}
	}
}
