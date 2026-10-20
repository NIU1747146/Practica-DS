package baseNoStates;

// Represents the state of an unlocked door.
public class Unlocked extends DoorState {
  public Unlocked(Door door, String name) {
    super(door, name);
  }

  //If the door is closed, open it, if not dont do anything
  @Override
  public void open() {
    if (door.isClosed()) {
      door.setClosed(false);
    }
    else {
      System.out.println("The door is already open");
    }
  }
  @Override
  public void close() {
    if(door.isClosed()) {
      System.out.println("The door is already closed");
    }
    else {
      door.setClosed(true);
    }
  }
  @Override
  public void lock() {
    if (door.isClosed()) {
      door.setState(new Locked(door, States.LOCKED));
    }
    else {
      System.out.println("Cannot lock an open door");
    }
  }
  @Override
  public void unlock() {
    System.out.println("The door is already unlocked");
  }

  @Override
  public void unlockShortly(){
    System.out.println("Door " + door.getId() + " already unlocked.");
  }

}
