import java.util.*;

public class TestSelect {
    public static void main(String[] args) {
        Random rand = new Random();
        boolean allGood = true;

        for (int t = 0; t < 100; t++) {
            int n = 100 + rand.nextInt(200);
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = rand.nextInt(1000);

            int k = rand.nextInt(n);

            int[] copy = arr.clone();
            Arrays.sort(copy);
            int expected = copy[k];

            int result = DeterministicSelect.select(arr, k);

            if (result != expected) {
                System.out.println("Mismatch! k=" + k + " expected " + expected + " got " + result);
                allGood = false;
                break;
            }
        }
        System.out.println("Deterministic Select test passed: " + allGood);
    }
}