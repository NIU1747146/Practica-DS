package milestone1.doorstates;

import milestone1.Door;

// DoorState.java
// Abstract state of a Door (State pattern). Each subclass defines what each of the five actions
// does in that state and, when needed, moves the door to another state with door.setState().
public abstract class DoorState {
  protected Door door;
  protected String name;

  public DoorState(Door door, String name) {
    this.door = door;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public abstract void open();

  public abstract void close();

  public abstract void lock();

  public abstract void unlock();

  public abstract void unlockShortly();
}
