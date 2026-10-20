package baseNoStates;

import java.time.Duration;
import java.time.LocalDateTime;

// Design Patterns: Observer(observes the Clock for updates).
// Represents the state of a door that remains unlocked for 10 seconds before automatically changing its state.
public class UnlockedShortly extends DoorState implements Observer {
  private static final int TIMEOUT_SECONDS = 10;

  private final Clock clock;
  private LocalDateTime unlockTime;

  public UnlockedShortly(Door door, String name) {
    super(door, name);
    this.unlockTime = LocalDateTime.now();
    this.clock = Clock.getInstance(1); // Period of 1s for notifications.
    clock.addObserver(this);
  }

  private boolean hasTimeoutPassed() {
    return Duration.between(unlockTime, LocalDateTime.now()).getSeconds() >= TIMEOUT_SECONDS;
  }

  //Called by the Clock to notify the observer of updates
  @Override
  public void update() {
    if (hasTimeoutPassed()) {
      clock.removeObserver(this);

      if (door.isClosed()) {
        door.setDoorState(new Locked(door, States.LOCKED));
        System.out.println("Door " + door.getId() + " automatically locked after " + TIMEOUT_SECONDS + " seconds.");
      } else {
        door.setDoorState(new Propped(door, States.PROPPED));
        System.out.println("Door " + door.getId() + " propped state after " + TIMEOUT_SECONDS + " seconds.");
      }
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
    unlockTime = LocalDateTime.now();
    System.out.println("Door " + door.getId() + " unlock timer reset.");
  }

}
