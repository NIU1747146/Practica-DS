package milestone1.doorstates;

import java.time.Duration;
import java.time.LocalDateTime;
import milestone1.Door;

// Door unlocked for 10 s; observes the Clock and then goes to Locked or Propped (Observer pattern).
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

  private boolean hasTimeoutPassed() {
    return Duration.between(unlockTime, LocalDateTime.now()).getSeconds() >= TIMEOUT_SECONDS;
  }

  // called by the Clock every second
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

  // restarts the 10 s
  @Override
  public void unlockShortly() {
    unlockTime = LocalDateTime.now();
    System.out.println("Door " + door.getId() + " unlock timer reset.");
  }
}
