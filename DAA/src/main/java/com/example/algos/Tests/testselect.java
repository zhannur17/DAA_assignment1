package com.example.algos;

import com.example.algos.select.Select;
import java.util.Arrays;
import java.util.Random;

public class testselect {
    public static void main(String[] args) {
        Random rand = new Random();
        boolean allGood = true;

        for (int t = 0; t < 100; t++) {
            int n = 50 + rand.nextInt(150);
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = rand.nextInt(1000);

            int k = rand.nextInt(n);

            int[] copy = arr.clone();
            Arrays.sort(copy);
            int expected = copy[k];

            int result = Select.deterministicSelect(arr, k);

            if (result != expected) {
                System.out.println("Mismatch! k=" + k +
                        " expected=" + expected + " got=" + result);
                allGood = false;
                break;
            }
        }
        System.out.println("Deterministic Select test passed: " + allGood);
    }
}
