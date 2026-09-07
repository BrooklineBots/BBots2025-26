package org.firstinspires.ftc.teamcode.Utils;

import java.util.TreeMap;

public class LinearInterpolationMap {
  private TreeMap<Double, Integer> map = new TreeMap<>();

  public LinearInterpolationMap(TreeMap<Double, Integer> map) {
    this.map = map;
  }

  // Add a known data point
  public void put(double x, int y) {
    map.put(x, y);
  }

  // Retrieve an exact value or calculate the linearly interpolated value
  public Integer interpolate(double x) {
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
    int lowerY = map.get(lowerX);
    int upperY = map.get(upperX);

    // Perform linear interpolation formula: y = y0 + ((x - x0) * (y1 - y0)) / (x1 - x0)
    return (int) Math.round(lowerY + ((x - lowerX) * (upperY - lowerY)) / (upperX - lowerX));
  }
}
