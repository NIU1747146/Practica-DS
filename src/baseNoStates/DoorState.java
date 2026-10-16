package baseNoStates;

// Design Patterns: State.
// Abstract base class that defines the common behavior for all door states.
public abstract class DoorState {
  protected Door door;
  protected String name;

  public DoorState(Door door, String name) {
    this.door = door;
    this.name = name;
  }

  public String getName() { return name; }

  // Each action is defined as an abstract method that must be implemented by concrete states.
  public abstract void open();
  public abstract void close();
  public abstract void lock();
  public abstract void unlock();
  public abstract void unlockShortly();

}
