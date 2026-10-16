package baseNoStates;

import java.time.Duration;
import java.time.LocalDateTime;

// Design Patterns: Observer(observes the Clock for updates).
// Represents the state of a door that remains unlocked for 10 seconds before automatically changing its state.
public class UnlockedShortly extends DoorState implements Observer {
  private static final int TIMEOUT_SECONDS = 10;

  private final Clock clock;
  private LocalDateTime unlockTime;
  private boolean isExpired = false;

  public UnlockedShortly(Door door, String name) {
    super(door, name);
    unlockTime = LocalDateTime.now();
    clock = Clock.getInstance(1); // Period of 1s for notifications.
    clock.addObserver(this);
    clock.start();
  }

  private boolean hasTimeoutPassed() {
    return Duration.between(unlockTime, LocalDateTime.now()).getSeconds() >= TIMEOUT_SECONDS;
  }

  //Called by the Clock to notify the observer of updates
  @Override
  public void update() {
    if (!isExpired && hasTimeoutPassed()) {
      if (door.isClosed()) {
        door.setDoorState(new Locked(door, States.LOCKED));
        System.out.println("Door " + door.getId() + " automatically locked after " + TIMEOUT_SECONDS + " seconds.");
      } else {
        door.setDoorState(new Propped(door, States.PROPPED));
        System.out.println("Door " + door.getId() + " propped state after " + TIMEOUT_SECONDS + " seconds.");
      }
      isExpired = true;
      clock.removeObserver(this); // Stop observing after state change
    }
  }

  @Override
  public void open() {
    if (door.isClosed()) {
      door.setClosed(false);
    }
    else {
      System.out.println("The door is already open");
    }
  }
  @Override
  public void close() {
    if (!door.isClosed()) {
      door.setClosed(true);
    }
    else {
      System.out.println("The door is already closed");
    }
  }

  @Override
  public void lock() {
    System.out.println("Cannot lock directly from unlocked_shortly");
  }
  @Override
  public void unlock() {
    System.out.println("The door is already unlocked shortly");
  }

  @Override
  public void unlockShortly() {
    if (isExpired) {
      // Reset the unlock time if the state has already changed.
      isExpired = false;
      unlockTime = LocalDateTime.now();
      clock.addObserver(this);
      System.out.println("Door " + door.getId() + " unlock timer reset.");
    } else {
      System.out.println("Door " + door.getId() + " already in UnlockedShortly state.");
    }
  }

}
