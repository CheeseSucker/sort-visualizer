package com.github.cheesesucker.sortvisualizer;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

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
		ISorter[] sorters = new ISorter[]{
				new InsertionSort(),
				new SelectionSort(),
				new ShellSort(),
				new MergeSort(1),
				new MergeSort(10),
				new MergeSortBottomUp(),
				new QuickSort(),
				new HeapSort()
		};
		
		setupWindowLayout(sorters.length);
		
		for (ISorter sorter : sorters) {
			new SorterThread(sorter, input).start();
		}
	}

	public static void setupWindowLayout(int numWindows) {
		final int taskbarHeight = 40; // TODO: Get the real value for this
		
		final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		
		// Prefer vertical over horizontal layout
		int availableHeight = screen.height - taskbarHeight;
		int maxWindowsY = availableHeight / GraphicalView.WindowHeight;
		GraphicalView.WindowsPerRow = (int)Math.ceil((double)numWindows / maxWindowsY);
		
		// Center vertically
		int windowsPerColumn = (int)Math.ceil((double)numWindows / GraphicalView.WindowsPerRow);
		int totalHeight = windowsPerColumn * GraphicalView.WindowHeight;
		GraphicalView.WindowOffsetY = (availableHeight - totalHeight) / 2;
	}
}
