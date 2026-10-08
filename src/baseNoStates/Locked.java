package baseNoStates;

public class Locked extends DoorState {

  public Locked(Door door) {
    super(door);
    this.name = States.LOCKED;
  }

  public void open() {
    System.out.println("Door is locked, cannot open");
  }

  public void close() {
    System.out.println("Door is locked, already closed");
  }

  public void lock() {
    System.out.println("Door is already locked");
  }

  public void unlock() {
    door.setState(new Unlocked(door));
  }

}
