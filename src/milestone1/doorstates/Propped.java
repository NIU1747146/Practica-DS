package milestone1.doorstates;

import milestone1.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a door left open after unlocked shortly expired.
 */
public class Propped extends DoorState {
  private static final Logger logger = LoggerFactory.getLogger(Propped.class);

  public Propped(Door door, String name) {
    super(door, name);
  }

  @Override
  public void open() {
    logger.info("Door " + door.getId() + " is already open");
  }

  @Override
  public void close() {
    door.setClosed(true);
    door.setState(new Locked(door, States.LOCKED));
  }

  @Override
  public void lock() {
    logger.info("Cannot lock door " + door.getId() + " until it is closed");
  }

  @Override
  public void unlock() {
    logger.info("Cannot unlock door " + door.getId() + " because it is propped");
  }

  @Override
  public void unlockShortly() {
    logger.info("Cannot unlock shortly door " + door.getId() + " while it is propped");
  }
}
