package milestone1.doorstates;

import milestone1.Door;

// Represents a door left open after unlocked shortly expired.
public class Propped extends DoorState {

  public Propped(Door door, String name) {
    super(door, name);
  }

  @Override
  public void open() {
    System.out.println("The door is already open");
  }

  @Override
  public void close() {
    door.setClosed(true);
    door.setState(new Locked(door, States.LOCKED));
  }

  @Override
  public void lock() {
    System.out.println("Cannot lock a propped door until it is closed");
  }

  @Override
  public void unlock() {
    System.out.println("Cannot unlock a propped door");
  }

  @Override
  public void unlockShortly() {
    System.out.println("Cannot unlock shortly while the door is propped open.");
  }
}
