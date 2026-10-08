package baseNoStates;

public class UnlockedShortly extends DoorState {

  public UnlockedShortly(Door door) {
    super(door);
    this.name = States.UNLOCKED_SHORTLY;
    //TODO
  }

  public void open() {
    //TODO
  }

  public void close() {
    //TODO
  }

  public void lock() {
    System.out.println("Cannot lock directly from unlocked_shortly");
  }

  public void unlock() {
    System.out.println("The door is already unlocked shortly");
  }

}
