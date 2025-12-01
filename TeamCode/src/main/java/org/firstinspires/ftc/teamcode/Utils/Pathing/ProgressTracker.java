package org.firstinspires.ftc.teamcode.Utils.Pathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import java.util.HashMap;
import java.util.Map;

/** Enhanced wrapper that adds progress tracking and event marker support to Pedro Pathing */
public class ProgressTracker {
  private final Follower follower;
  private PathChain currentChain;
  private final Map<String, Double> eventPositions = new HashMap<>();
  private final Map<String, Boolean> eventTriggered = new HashMap<>();

  public ProgressTracker(Follower follower) {
    this.follower = follower;
  }

  public void setCurrentChain(PathChain chain) {
    this.currentChain = chain;
    clearEvents();
  }

  public void registerEvent(String eventName, double position) {
    eventPositions.put(eventName, position);
    eventTriggered.put(eventName, false);
  }

  public void clearEvents() {
    eventPositions.clear();
    eventTriggered.clear();
  }

  public void executeEvent(String eventName) {
    if (!eventTriggered.getOrDefault(eventName, true)) {
      eventTriggered.put(eventName, true);
      // Execute the named command if it exists
      if (NamedCommands.hasCommand(eventName)) {
        NamedCommands.getCommand(eventName).schedule();
      }
    }
  }

  public boolean isEventTriggered(String eventName) {
    return eventTriggered.getOrDefault(eventName, false);
  }

  public boolean shouldTriggerEvent(String eventName) {
    if (!eventPositions.containsKey(eventName) || isEventTriggered(eventName)) {
      return false;
    }

    double progress = getProgress();
    double eventPosition = eventPositions.get(eventName);
    return progress >= eventPosition;
  }

  public double getProgress() {
    if (currentChain != null) {
      // Calculate chain progress
      int currentIndex = follower.getChainIndex();
      double totalProgress = 0;
      double currentProgress = 0;

      for (int i = 0; i < currentChain.size(); i++) {
        Path path = currentChain.getPath(i);
        if (i < currentIndex) {
          // Path completed
          currentProgress += 1.0;
        } else if (i == currentIndex) {
          // Current path
          currentProgress += Math.min(follower.getCurrentTValue(), 1.0);
        }
        totalProgress += 1.0;
      }

      return totalProgress > 0 ? currentProgress / totalProgress : 0.0;
    } else {
      // Single path
      return Math.min(follower.getCurrentTValue(), 1.0);
    }
  }

  // Convenience method to follow and track
  public void followAndTrack(PathChain chain) {
    setCurrentChain(chain);
    follower.followPath(chain);
  }

  // Delegate update
  public void update() {
    follower.update();
  }

  // Common delegate methods
  public boolean isBusy() {
    return follower.isBusy();
  }

  public void breakFollowing() {
    follower.breakFollowing();
  }
}
