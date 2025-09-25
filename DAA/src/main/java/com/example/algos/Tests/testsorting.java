package com.example.algos;

import com.example.algos.mergesort.MergeSort;
import com.example.algos.quicksort.QuickSort;

import java.util.Random;

public class testsorting {
    public static void main(String[] args) {
        int[] sizes = {10, 100, 1000, 10000};

        for (int n : sizes) {
            System.out.println("\n=== Array size " + n + " ===");

            int[] arr1 = randomArray(n);
            int[] arr2 = arr1.clone();

            // Sort using MergeSort and QuickSort
            MergeSort.sort(arr1);
            QuickSort.sort(arr2);

            // Check correctness
            System.out.println("MergeSort correct: " + isSorted(arr1));
            System.out.println("QuickSort correct: " + isSorted(arr2));

            // Print max recursion depth for QuickSort
            System.out.println("QuickSort recursion depth: " + QuickSort.getMaxRecursionDepth() +
                    " (expected ≲ 2*floor(log2(n)) + O(1))");

            // Adversarial test: already sorted and reverse sorted arrays
            int[] sorted = new int[n];
            int[] reversed = new int[n];
            for (int i = 0; i < n; i++) {
                sorted[i] = i;
                reversed[i] = n - i;
            }

            MergeSort.sort(sorted);
            QuickSort.sort(reversed);

            System.out.println("Adversarial tests passed: " + (isSorted(sorted) && isSorted(reversed)));
        }
    }

    // Generate random array of size n
    private static int[] randomArray(int n) {
        Random rand = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = rand.nextInt(100000);
        return arr;
    }

    // Check if array is sorted in non-decreasing order
    private static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }
}
