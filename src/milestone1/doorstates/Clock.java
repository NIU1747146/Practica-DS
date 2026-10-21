package milestone1.doorstates;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

// Unique clock that notifies its observers every period seconds (Observer and Singleton patterns).
public class Clock {
  private static Clock instance;
  private Timer timer;
  private final int period; // seconds
  // thread safe: observers remove themselves while being notified
  private final List<Observer> observers = new CopyOnWriteArrayList<>();

  private Clock(int period) {
    this.period = period;
  }

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
    }
  }

  public synchronized void stop() {
    if (timer != null) {
      timer.cancel();
      timer = null;
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
