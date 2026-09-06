package org.firstinspires.ftc.teamcode.Utils;

import java.util.TreeMap;

public class LinearInterpolationMap {
    private final TreeMap<Double, Double> map = new TreeMap<>();

    // Add a known data point
    public void put(double x, double y) {
        map.put(x, y);
    }

    // Retrieve an exact value or calculate the linearly interpolated value
    public Double interpolate(double x) {
        if (map.isEmpty()) {
            return null;
        }

        // Exact match check
        if (map.containsKey(x)) {
            return map.get(x);
        }

        // Find the bounding elements
        Double lowerX = map.floorKey(x);
        Double upperX = map.ceilingKey(x);

        // Handle out-of-bounds queries (Clamping)
        if (lowerX == null) return map.get(upperX); // x is below the lowest bound
        if (upperX == null) return map.get(lowerX); // x is above the highest bound

        // Get matching y values
        double lowerY = map.get(lowerX);
        double upperY = map.get(upperX);

        // Perform linear interpolation formula: y = y0 + ((x - x0) * (y1 - y0)) / (x1 - x0)
        return lowerY + ((x - lowerX) * (upperY - lowerY)) / (upperX - lowerX);
    }

    public static void main(String[] args) {
        LinearInterpolationMap table = new LinearInterpolationMap();

        // Define key-value points
        table.put(0.0, 10.0);
        table.put(1.0, 20.0);
        table.put(5.0, 60.0);

        // Test values
        System.out.println("Interpolation at 0.5: " + table.interpolate(0.5)); // Expected: 15.0
        System.out.println("Interpolation at 3.0: " + table.interpolate(3.0)); // Expected: 40.0
        System.out.println("Clamped lower bound (-1): " + table.interpolate(-1.0)); // Expected: 10.0
    }
}
