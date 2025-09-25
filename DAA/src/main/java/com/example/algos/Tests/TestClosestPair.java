package com.example.algos;

import com.example.algos.closest.ClosestPair;
import com.example.algos.closest.ClosestPair.Point;

import java.util.Random;

public class TestClosestPair {

    private static double bruteForce(Point[] points) {
        double minDist = Double.POSITIVE_INFINITY;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                minDist = Math.min(minDist, dist(points[i], points[j]));
            }
        }
        return minDist;
    }

    private static double dist(Point a, Point b) {
        return Math.hypot(a.x - b.x, a.y - b.y);
    }

    private static Point[] randomPoints(int n) {
        Random rand = new Random();
        Point[] pts = new Point[n];
        for (int i = 0; i < n; i++) {
            pts[i] = new Point(rand.nextDouble() * 1000, rand.nextDouble() * 1000);
        }
        return pts;
    }

    public static void main(String[] args) {
        for (int n = 10; n <= 2000; n *= 10) {
            Point[] pts = randomPoints(n);
            double fast = ClosestPair.closest(pts.clone());
            double slow = bruteForce(pts);
            System.out.println("n=" + n + " validated: " + (Math.abs(fast - slow) < 1e-9));
        }

        Point[] large = randomPoints(100000);
        double result = ClosestPair.closest(large);
        System.out.println("Large test n=100000 result=" + result);
    }
}
