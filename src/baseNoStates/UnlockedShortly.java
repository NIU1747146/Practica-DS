package baseNoStates;

public class UnlockedShortly extends DoorState {

  public UnlockedShortly(Door door) {
    super(door);
    this.name = States.UNLOCKED_SHORTLY;
    //TODO
  }
  @Override
  public void open() {
    //TODO
  }
  @Override
  public void close() {
    //TODO
  }
  @Override
  public void lock() {
    System.out.println("Cannot lock directly from unlocked_shortly");
  }
  @Override
  public void unlock() {
    System.out.println("The door is already unlocked shortly");
  }

}
