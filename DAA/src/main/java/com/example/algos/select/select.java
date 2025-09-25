package com.example.algos.select;

public class Select {
    public static int sort(int[] arr) {
        if (arr == null || arr.length <= 1) return 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) minIndex = j;
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        return 0;
    }
}
