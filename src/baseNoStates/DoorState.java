package baseNoStates;

public abstract class DoorState {
  protected Door door;
  protected String name;

  public DoorState(Door door) {
    this.door = door;
  }

  public String getName() { return name; }

  public void open() {}
  public void close() {}
  public void lock() {}
  public void unlock() {}


}
