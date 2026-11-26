package milestone1.doorstates;

import java.time.Duration;
import java.time.LocalDateTime;
import milestone1.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Door unlocked for a limited time; observes the Clock to transition to Locked or Propped.
 */
public class UnlockedShortly extends DoorState implements Observer {
  private static final Logger logger = LoggerFactory.getLogger(UnlockedShortly.class);

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
        logger.info("Door " + door.getId() + " automatically locked after "
                + TIMEOUT_SECONDS + " seconds.");
      } else {
        door.setState(new Propped(door, States.PROPPED));
        logger.warn("Door " + door.getId() + " propped after "
                + TIMEOUT_SECONDS + " seconds.");
      }
    }
  }

  @Override
  public void open() {
    if (door.isClosed()) {
      door.setClosed(false);
    } else {
      logger.info("Door " + door.getId() + " is already open");
    }
  }

  @Override
  public void close() {
    if (!door.isClosed()) {
      door.setClosed(true);
    } else {
      logger.info("Door " + door.getId() + " is already closed");
    }
  }

  @Override
  public void lock() {
    logger.info("Cannot lock door " + door.getId() + " directly from unlocked_shortly");
  }

  @Override
  public void unlock() {
    logger.info("Door " + door.getId() + " is already unlocked shortly");
  }

  @Override
  public void unlockShortly() {
    unlockTime = LocalDateTime.now();
    logger.debug("Door " + door.getId() + " unlock timer reset.");
  }

  private boolean hasTimeoutPassed() {
    return Duration.between(unlockTime, LocalDateTime.now()).getSeconds() >= TIMEOUT_SECONDS;
  }
}