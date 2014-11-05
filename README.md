Sort visualizer
============================

Visualizes how several different sorting algorithms work in realtime.

![Screenshot](screenshot.png?raw=true "Screenshot")
Colors:
  - Red = Array modifications (exchanges).
  - Yellow = Compared items since last exchange.
  - Gray = Untouched since last exchange.

Labels on bottom of each panel:
  - E: Number of exchanges
  - C: Number of compares
  - T: Exchanges + Compares (can be interpreted as the total time required by the algorithm)

=====

The sorting algorithms are based on the book "Algorithms" by Robert Sedgewick and Kevin Wayne.

Currently implemented sorting algorithms:
  - Insertion sort
  - Selection sort
  - Merge sort (top down, with cutoff for insertion sort)
  - Merge sort (bottom up)
  - Shellsort
  - Quicksort
  - Heapsort
 
I use some code from StdLib shipped with the book, so the program must be licensed under GPL.
