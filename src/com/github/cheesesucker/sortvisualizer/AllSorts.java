package com.github.cheesesucker.sortvisualizer;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.HashSet;
import java.util.Set;

import com.github.cheesesucker.sortvisualizer.algorithms.BubbleSort;
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
				new BubbleSort(),
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
		int maxWindowsX = screen.width / GraphicalView.WindowWidth;
		int maxWindowsY = availableHeight / GraphicalView.WindowHeight;
		Point layout = getLayout(numWindows, maxWindowsX, maxWindowsY);
		GraphicalView.WindowsPerRow = layout.x;
		
		// Center vertically
		int windowsPerColumn = (int)Math.ceil((double)numWindows / GraphicalView.WindowsPerRow);
		int totalHeight = windowsPerColumn * GraphicalView.WindowHeight;
		GraphicalView.WindowOffsetY = (availableHeight - totalHeight) / 2;
	}
	
	/**
	 * Find a somewhat pleasing window layout.
	 * In this case we prefer rectangular layouts over jagged ones.
	 */
	public static Point getLayout(int numWindows, int maxX, int maxY) {
		// Look for rectangular layout
		Set<Integer> divisors = findDivisors(numWindows);
		for (int x : divisors) {
			int y = numWindows / x;
			if (x <= maxX && y <= maxY) {
				return new Point(x, y);
			}
		}
		
		// No rectangular layout found. Use a jagged one, possibly overflowing the screen.
		int x = (int)Math.ceil((double)numWindows / maxY);
		int y = maxY;
		return new Point(x, y);
	}
	
	/**
	 * Find the divisors of a number.
	 * 8 => [1, 2, 4, 8]
	 */
	public static Set<Integer> findDivisors(int a) {
		Set<Integer> factors = new HashSet<Integer>();
		factors.add(1);
		for (int x = 2; x < a; x++) {
			if (a % x == 0) {
				factors.add(x);
			}
		}
		return factors;
	}
}
