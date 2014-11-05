package com.github.cheesesucker.sortvisualizer.algorithms;

import com.github.cheesesucker.sortvisualizer.Helper;

/** Calls to less and exch must have indices subtracted by one, 
 * to emulate array indexing from 1..N. */
public class HeapSort implements ISorter {
    public void sort(double[] pq) {
        int N = pq.length;
        
        // Create heap structure
        for (int k = N/2; k >= 1; k--) {
            sink(pq, k, N);
        }
        
        // Move largest item to end of unsorted section of the array
        while (N > 1) {
            Helper.exch(pq, 1-1, --N);
            sink(pq, 1, N);
        }
    }

    private void sink(double[] pq, int k, int N) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && Helper.less(pq, j-1, j)) j++;
            if (!Helper.less(pq, k-1, j-1)) break;
            Helper.exch(pq, k-1, j-1);
            k = j;
        }
    }
    
	@Override
	public String getName() {
		return getClass().getSimpleName();
	}
	
	public static void main(String[] args) {
		double[] input = Helper.generateInput(100);
		Helper.printArray(input);
		
		HeapSort sorter = new HeapSort();
		sorter.sort(input);
		
		Helper.printArray(input);
		
		if (!Helper.isSorted(input)) {
			throw new RuntimeException("Postcondition failed: Array is not sorted!");
		} else {
			System.out.println("sorted");
		}
	}
}
