package milestone.n2.doorstates;

import java.time.Duration;
import java.time.LocalDateTime;
import milestone.n2.Door;

/**
 * Door unlocked for a limited time; observes the Clock to transition to Locked or Propped.
 */
public class UnlockedShortly extends DoorState implements Observer {
  private static final int TIMEOUT_SECONDS = 10;
  private static final int CLOCK_PERIOD_SECONDS = 1;

  private final Clock clock;
  private LocalDateTime unlockTime;

  public UnlockedShortly(Door door, String name) {
    super(door, name);
    this.unlockTime = LocalDateTime.now();
    this.clock = Clock.getInstance(CLOCK_PERIOD_SECONDS);
    clock.addObserver(this);
  }

  @Override
  public void update() {
    if (hasTimeoutPassed()) {
      clock.removeObserver(this);

      if (door.isClosed()) {
        door.setState(new Locked(door, States.LOCKED));
        System.out.println("Door " + door.getId() + " automatically locked after "
                + TIMEOUT_SECONDS + " seconds.");
      } else {
        door.setState(new Propped(door, States.PROPPED));
        System.out.println("Door " + door.getId() + " propped after "
                + TIMEOUT_SECONDS + " seconds.");
      }
    }
  }

  @Override
  public void open() {
    if (door.isClosed()) {
      door.setClosed(false);
    } else {
      System.out.println("The door is already open");
    }
  }

  @Override
  public void close() {
    if (!door.isClosed()) {
      door.setClosed(true);
    } else {
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

  private boolean hasTimeoutPassed() {
    return Duration.between(unlockTime, LocalDateTime.now()).getSeconds() >= TIMEOUT_SECONDS;
  }
}