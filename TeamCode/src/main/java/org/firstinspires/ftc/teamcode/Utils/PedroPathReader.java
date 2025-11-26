package org.firstinspires.ftc.teamcode.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pedropathing.geometry.Pose;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class PedroPathReader {

  private final PedroPP file;
  private final Map<String, Pose> poses = new HashMap<>();

  private double lastX;
  private double lastY;
  private double lastDeg;

  public PedroPathReader(String filename) throws IOException {

    InputStream stream =
        Objects.requireNonNull(getClass().getClassLoader())
            .getResourceAsStream("AutoPaths/" + filename);

    if (stream == null) {
      throw new FileNotFoundException("PP File not found: " + filename);
    }

    Gson gson = new GsonBuilder().create();
    try (InputStreamReader reader = new InputStreamReader(stream)) {
      this.file = gson.fromJson(reader, PedroPP.class);
    }

    loadAllPoints();
  }

  private void loadAllPoints() {
    lastX = file.startPoint.x;
    lastY = file.startPoint.y;
    lastDeg = file.startPoint.startDeg;

    poses.put("startPoint", toPose(lastX, lastY, lastDeg));

    for (PedroPP.Line line : file.lines) {
      double x = line.endPoint.x;
      double y = line.endPoint.y;

      double heading = extractHeading(line.endPoint.heading, lastX, lastY, x, y, lastDeg);

      poses.put(line.name, toPose(x, y, heading));

      lastX = x;
      lastY = y;
      lastDeg = heading;
    }
  }

  public Pose get(String name) {
    return poses.get(name);
  }

  private static Pose toPose(double x, double y, double deg) {
    return new Pose(y, 144 - x, Math.toRadians(deg - 90));
  }

  private static double extractHeading(
      String mode, double lastX, double lastY, double x, double y, double lastDeg) {
    double dx = x - lastX;
    double dy = y - lastY;

    if (Math.abs(dx) < 1e-6 && Math.abs(dy) < 1e-6) {
      return lastDeg;
    }

    double linearDeg = Math.toDegrees(Math.atan2(dy, dx));

    if (mode.equals("linear")) return linearDeg;
    if (mode.equals("tangential")) return linearDeg;

    return lastDeg;
  }
}

////////////////////////////////////////////////////////////////////////////////////////////////////
///                                                                                              ///
///  PEDRO PP FILE DEFINITIONS                                                                   ///
///                                                                                              ///
////////////////////////////////////////////////////////////////////////////////////////////////////
class PedroPP {

  public StartPoint startPoint;
  public List<Line> lines;

  public static class StartPoint {
    public double x;
    public double y;
    public String heading;
    public double startDeg;
    public double endDeg;
  }

  public static class Line {
    public String name;
    public EndPoint endPoint;
  }

  public static class EndPoint {
    public double x;
    public double y;
    public String heading;
    public boolean reverse;
  }
}
