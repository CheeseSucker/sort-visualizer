package com.github.cheesesucker.sortvisualizer;

import com.github.cheesesucker.sortvisualizer.algorithms.ISorter;


public class Helper {
	public static void exch(double[] input, int a, int b) {
		double tmp = input[a];
		input[a] = input[b];
		input[b] = tmp;
		
		GraphicalView view = GraphicalView.get(input);
		view.exchs++;
		view.exch.clear();
		view.exch.add(a);
		view.exch.add(b);
		view.draw(input);
		view.less.clear();
	}
	
	public static boolean isSorted(double[] input) {
		for (int i = 1; i < input.length; i++) {
			if (input[i-1] > input[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean less(double[] input, int a, int b) {
		GraphicalView view = GraphicalView.get(input);
		view.compares++;
		view.less.add(a);
		view.less.add(b);
		view.draw(input);
		return less(input[a], input[b]);
	}

	public static <T extends Comparable<T>> boolean less(T a, T b) {
		return a.compareTo(b) < 0;
	}
	
	public static void printArray(double[] a) {
		System.out.print("[");
		for (double e : a) {
			System.out.print(e + ", ");
		}
		System.out.println("]");
	}

	public static double[] generateInput(int n) {		
		double[] input = new double[n];
		for (int i = 0; i < n; i++) {
			input[i] = StdRandom.uniform();
		}
		return input;
	}

	public static void runExperiment(ISorter sorter, int n) {
		double[] input = generateInput(n);
		
		GraphicalView.get(input, sorter.getName());
		sorter.sort(input);
		
		if (!isSorted(input)) {
			throw new RuntimeException("Postcondition failed: Array is not sorted!");
		} else {
			System.out.println("sorted");
		}
	}
}
