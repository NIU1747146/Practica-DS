package milestone1.doorstates;

import milestone1.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents the state of an unlocked door.
 */
public class Unlocked extends DoorState {
  private static final Logger logger = LoggerFactory.getLogger(Unlocked.class);

  public Unlocked(Door door, String name) {
    super(door, name);
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
    if (door.isClosed()) {
      logger.info("Door " + door.getId() + " is already closed");
    } else {
      door.setClosed(true);
    }
  }

  @Override
  public void lock() {
    if (door.isClosed()) {
      door.setState(new Locked(door, States.LOCKED));
    } else {
      logger.info("Cannot lock door " + door.getId() + " because it is open");
    }
  }

  @Override
  public void unlock() {
    logger.warn("Door " + door.getId() + " is already unlocked");
  }

  @Override
  public void unlockShortly() {
    logger.info("Door " + door.getId() + " already unlocked.");
  }
}
