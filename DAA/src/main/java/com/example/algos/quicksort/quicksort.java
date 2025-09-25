package com.example.algos.quicksort;

public class QuickSort {
    private static int maxDepth; // to track recursion depth

    public static void sort(int[] arr) {
        maxDepth = 0;
        if (arr == null || arr.length <= 1) return;
        quickSort(arr, 0, arr.length - 1, 0);
    }

    private static void quickSort(int[] arr, int low, int high, int depth) {
        if (low < high) {
            maxDepth = Math.max(maxDepth, depth);
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1, depth + 1);
            quickSort(arr, pi + 1, high, depth + 1);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int getMaxRecursionDepth() {
        return maxDepth;
    }
}
