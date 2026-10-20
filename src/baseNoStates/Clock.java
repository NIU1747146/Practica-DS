package baseNoStates;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

// Represents a clock that periodically notifies its observers of updates.
public class Clock {
    private static Clock instance;
    private Timer timer;
    private final int period;
    private final List<Observer> observers = new CopyOnWriteArrayList<>();

    private Clock(int period) {
        this.period = period;
    }

    // Design Pattern: Singleton
    // Instance of Clock is created
    public static Clock getInstance(int period) {
        if (instance == null) {
            instance = new Clock(period);
        }
        return instance;
    }

    // starts timer
    public synchronized void start() {
        if (timer == null) {
            timer = new Timer();
            TimerTask repeatedTask = new TimerTask() {
                @Override
                public void run() {
                    notifyObservers();
                }
            };
            timer.scheduleAtFixedRate(repeatedTask, 0, 1000L * period); // Notifies every second
        }
    }

    public synchronized void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
        // If there are no observers left, stop the clock
        if (observers.isEmpty()) {
            stop();
        }
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
    public void addObserver(Observer observer) {
        observers.add(observer);
        start();
    }


}
