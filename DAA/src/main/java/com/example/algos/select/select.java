package com.example.algos.select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Select {

    private static List<List<Integer>> partition(int[] arr, int pivot) {
        List<Integer> less = new ArrayList<>();
        List<Integer> equal = new ArrayList<>();
        List<Integer> greater = new ArrayList<>();

        for (int x : arr) {
            if (x < pivot) less.add(x);
            else if (x > pivot) greater.add(x);
            else equal.add(x);
        }

        return Arrays.asList(less, equal, greater);
    }

    public static int deterministicSelect(int[] arr, int k) {
        if (arr.length <= 5) {
            Arrays.sort(arr);
            return arr[k];
        }

        int numGroups = (int) Math.ceil(arr.length / 5.0);
        int[] medians = new int[numGroups];

        for (int i = 0; i < numGroups; i++) {
            int start = i * 5;
            int end = Math.min(start + 5, arr.length);
            int[] group = Arrays.copyOfRange(arr, start, end);
            Arrays.sort(group);
            medians[i] = group[group.length / 2];
        }

        int pivot = deterministicSelect(medians, medians.length / 2);

        List<List<Integer>> parts = partition(arr, pivot);
        List<Integer> less = parts.get(0);
        List<Integer> equal = parts.get(1);
        List<Integer> greater = parts.get(2);
        
        if (k < less.size()) {
            return deterministicSelect(less.stream().mapToInt(i -> i).toArray(), k);
        } else if (k < less.size() + equal.size()) {
            return pivot;
        } else {
            return deterministicSelect(
                    greater.stream().mapToInt(i -> i).toArray(),
                    k - less.size() - equal.size()
            );
        }
    }
}
