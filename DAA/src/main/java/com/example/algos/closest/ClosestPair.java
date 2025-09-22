package com.example.algos.closest;

import java.util.Arrays;

public class ClosestPair {
    public static class Point {
        public double x, y;
        public Point(double x, double y) {
            this.x = x; this.y = y;
        }
    }

    public static double closest(Point[] points) {
        Point[] px = points.clone();
        Arrays.sort(px, (a, b) -> Double.compare(a.x, b.x));
        return closestRec(px, 0, px.length - 1);
    }

    private static double closestRec(Point[] px, int left, int right) {
        if (right - left <= 3) {
            double min = Double.POSITIVE_INFINITY;
            for (int i = left; i <= right; i++) {
                for (int j = i + 1; j <= right; j++) {
                    min = Math.min(min, dist(px[i], px[j]));
                }
            }
            Arrays.sort(px, left, right + 1, (a, b) -> Double.compare(a.y, b.y));
            return min;
        }

        int mid = (left + right) / 2;
        double midX = px[mid].x;
        double d1 = closestRec(px, left, mid);
        double d2 = closestRec(px, mid + 1, right);
        double d = Math.min(d1, d2);

        Point[] strip = new Point[right - left + 1];
        int m = 0;
        for (int i = left; i <= right; i++) {
            if (Math.abs(px[i].x - midX) < d) {
                strip[m++] = px[i];
            }
        }
        Arrays.sort(strip, 0, m, (a, b) -> Double.compare(a.y, b.y));
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m && (strip[j].y - strip[i].y) < d; j++) {
                d = Math.min(d, dist(strip[i], strip[j]));
            }
        }
        return d;
    }

    private static double dist(Point a, Point b) {
        double dx = a.x - b.x, dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}