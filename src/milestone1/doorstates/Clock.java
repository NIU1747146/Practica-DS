package milestone1.doorstates;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Clock.java
// Unique clock (Singleton) that calls update() on its observers every period seconds (Observer
// pattern). UnlockedShortly uses it to know when its time is up. One shared timer is cheaper than
// one per door: it starts with the first observer and stops when the last one leaves.
public class Clock {
  private static final Logger logger = LoggerFactory.getLogger(Clock.class);

  private static Clock instance;
  private Timer timer;
  private final int period; // seconds
  // thread safe: observers remove themselves while being notified
  private final List<Observer> observers = new CopyOnWriteArrayList<>();

  private Clock(int period) {
    this.period = period;
  }

  // the period is only used when the only Clock is created, later calls ignore it
  public static synchronized Clock getInstance(int period) {
    if (instance == null) {
      instance = new Clock(period);
    }
    return instance;
  }

  public synchronized void start() {
    if (timer == null) {
      timer = new Timer();
      TimerTask repeatedTask = new TimerTask() {
        @Override
        public void run() {
          notifyObservers();
        }
      };
      timer.scheduleAtFixedRate(repeatedTask, 0, 1000L * period);
      logger.debug("Clock started");
    }
  }

  public synchronized void stop() {
    if (timer != null) {
      timer.cancel();
      timer = null;
      logger.debug("Clock stopped");
    }
  }

  public void addObserver(Observer observer) {
    observers.add(observer);
    start();
  }

  public void removeObserver(Observer observer) {
    observers.remove(observer);
    if (observers.isEmpty()) {
      stop();
    }
  }

  private void notifyObservers() {
    for (Observer observer : observers) {
      observer.update();
    }
  }
}
