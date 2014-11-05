package com.github.cheesesucker.sortvisualizer;

import com.github.cheesesucker.sortvisualizer.algorithms.HeapSort;
import com.github.cheesesucker.sortvisualizer.algorithms.ISorter;
import com.github.cheesesucker.sortvisualizer.algorithms.InsertionSort;
import com.github.cheesesucker.sortvisualizer.algorithms.MergeSort;
import com.github.cheesesucker.sortvisualizer.algorithms.MergeSortBottomUp;
import com.github.cheesesucker.sortvisualizer.algorithms.QuickSort;
import com.github.cheesesucker.sortvisualizer.algorithms.SelectionSort;
import com.github.cheesesucker.sortvisualizer.algorithms.ShellSort;

public class AllSorts {
	private static class SorterThread extends Thread {
		public ISorter sorter;
		public double[] input;

		public SorterThread(ISorter sorter, double[] input) {
			this.sorter = sorter;
			this.input = input.clone();
			GraphicalView.get(this.input, sorter.getName());
		}
		
		@Override
		public void run() {
			this.sorter.sort(input);
		}
	}
	
	public static void main(String[] args) {
		double[] input = Helper.generateInput(100);
		Thread[] sorters = new SorterThread[]{
			new SorterThread(new InsertionSort(), input),
			new SorterThread(new SelectionSort(), input),
			new SorterThread(new ShellSort(), input),
			new SorterThread(new MergeSort(1), input),
			new SorterThread(new MergeSort(10), input),
			new SorterThread(new MergeSortBottomUp(), input),
			new SorterThread(new QuickSort(), input),
			new SorterThread(new HeapSort(), input)
		};
		
		for (Thread t : sorters) {
			t.start();
		}
		
		
	}
	
}
