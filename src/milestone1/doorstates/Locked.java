package milestone1.doorstates;

import milestone1.Door;

// Represents the state of a locked door.
public class Locked extends DoorState {

  public Locked(Door door, String name) {
    super(door, name);
  }

  @Override
  public void open() {
    System.out.println("Door is locked, cannot open");
  }

  @Override
  public void close() {
    System.out.println("Door is locked, already closed");
  }

  @Override
  public void lock() {
    System.out.println("Door is already locked");
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
