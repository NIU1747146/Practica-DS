package baseNoStates;

public class Unlocked extends DoorState {
  public Unlocked(Door door) {
    super(door);
    this.name = States.UNLOCKED;
  }

  //If the door is closed, open it, if not dont do anything
  public void open() {
    if (door.isClosed()) {
      door.setClosed(false);
    }
    else {
      System.out.println("The door is already open");
    }
  }

  public void closed() {
    if(door.isClosed()) {
      System.out.println("The door is already closed");
    }
    else {
      door.setClosed(true);
    }
  }

  public void lock() {
    if (door.isClosed()) {
      door.setState(new Locked(door));
    }
    else {
      System.out.println("Cannot lock an open door");
    }
  }

  public void unlock() {
    System.out.println("The door is already unlocked");
  }

}
