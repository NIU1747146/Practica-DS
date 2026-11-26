package milestone1;

import java.util.ArrayList;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create and centralizes the management of doors throughout the system.
 */
public final class DirectoryDoors {
  private static final Logger logger = LoggerFactory.getLogger(DirectoryDoors.class);

  private ArrayList<Door> allDoors = new ArrayList<>();
  private static DirectoryDoors instance = null;

  private DirectoryDoors() {}

  public static synchronized DirectoryDoors getInstance() {
    if (instance == null) {
      instance = new DirectoryDoors();
    }
    return instance;
  }

  public void makeDoors() {
    // basement
    Door d1 = new Door("D1"); // exterior, parking
    Door d2 = new Door("D2"); // stairs, parking

    // ground floor
    Door d3 = new Door("D3"); // exterior, hall
    Door d4 = new Door("D4"); // stairs, hall
    Door d5 = new Door("D5"); // hall, room1
    Door d6 = new Door("D6"); // hall, room2

    // first floor
    Door d7 = new Door("D7"); // stairs, corridor
    Door d8 = new Door("D8"); // corridor, room3
    Door d9 = new Door("D9"); // corridor, IT

    allDoors = new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));
  }

  public Door findDoorById(String id) {
    for (Door door : allDoors) {
      if (door.getId().equals(id)) {
        return door;
      }
    }

    logger.warn("door with id " + id + " not found");
    return null; // otherwise we get a Java error
  }

  // this is needed by RequestRefresh
  public ArrayList<Door> getAllDoors() {
    logger.debug("all doors: " + allDoors);
    return allDoors;
  }
}
