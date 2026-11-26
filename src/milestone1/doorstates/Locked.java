package milestone1.doorstates;

import milestone1.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// State of a door that is closed and refuses to open. A door becomes Locked from Unlocked.lock()
// (only if closed), from Propped.close(), and when UnlockedShortly times out with the door closed.
public class Locked extends DoorState {
  private static final Logger logger = LoggerFactory.getLogger(Locked.class);

  public Locked(Door door, String name) {
    super(door, name);
  }

  @Override
  public void open() {
    logger.info("Door " + door.getId() + " is locked, cannot open");
  }

  @Override
  public void close() {
    logger.info("Door " + door.getId() + " is locked, already closed");
  }

  @Override
  public void lock() {
    logger.info("Door " + door.getId() + " is already locked");
  }

  @Override
  public void unlock() {
    door.setState(new Unlocked(door, States.UNLOCKED));
  }

  @Override
  public void unlockShortly() {
    door.setState(new UnlockedShortly(door, States.UNLOCKED_SHORTLY));
  }
}
