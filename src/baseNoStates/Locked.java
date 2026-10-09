package baseNoStates;

public class Locked extends DoorState {

  public Locked(Door door) {
    super(door);
    this.name = States.LOCKED;
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
    door.setState(new Unlocked(door));
  }
  @Override
  public void unlockShortly() {
    door.setState(new UnlockedShortly(door));
  }

}
