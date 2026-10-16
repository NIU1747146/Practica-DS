package baseNoStates;

import java.util.Timer;
import java.util.TimerTask;

public class UnlockedShortly extends DoorState {
  private static final int TIMEOUT_SECONDS = 10;

  public UnlockedShortly(Door door) {
    super(door);
    this.name = States.UNLOCKED_SHORTLY;

    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        if (door.isClosed()) {
          door.setState(new Locked(door));
        }
        else {
          door.setState(new Propped(door));
        }
        timer.cancel();
      }
    }, TIMEOUT_SECONDS * 1000);
  }
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
    if (!door.isClosed()) {
      door.setClosed(true);
    }
    else {
      System.out.println("The door is already closed");
    }
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
