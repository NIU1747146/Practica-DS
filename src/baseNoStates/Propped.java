package baseNoStates;

public class Propped extends DoorState {

  public Propped(Door door) {
    super(door);
    this.name = States.PROPPED;
  }

  public void open() {
    System.out.println("The door is already open");
  }

  public void close() {
    door.setClosed(true);
    door.setState(new Locked(door));
  }

  public void lock() {
    System.out.println("Cannot lock a propped door until it is closed");
  }

  public void unlock() {
    System.out.println("Cannot unlock a propped door");
  }

}
