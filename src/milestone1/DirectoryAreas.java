package milestone1;

import milestone2.visitors.FindAreaByIdVisitor;

/**
 * Creates the tree of areas and save it in the system.
 */
public final class DirectoryAreas {
  private static DirectoryAreas instance = null;
  private Area rootArea;

  private DirectoryAreas() {}

  public static synchronized DirectoryAreas getInstance() {
    if (instance == null) {
      instance = new DirectoryAreas();
    }
    return instance;
  }

  // Must be called after DirectoryDoors.getInstance().makeDoors()
  public void makeAreas() {
    final Space parking = new Space("parking");
    final Space hall = new Space("hall");
    final Space room1 = new Space("room1");
    final Space room2 = new Space("room2");
    final Space corridor = new Space("corridor");
    final Space room3 = new Space("room3");
    final Space it = new Space("IT");
    final Space stairs = new Space("stairs");
    final Space exterior = new Space("exterior");

    // doorId, from (where the reader is), to (the space you enter)
    link("D1", exterior, parking);
    link("D2", stairs, parking);
    link("D3", exterior, hall);
    link("D4", stairs, hall);
    link("D5", hall, room1);
    link("D6", hall, room2);
    link("D7", stairs, corridor);
    link("D8", corridor, room3);
    link("D9", corridor, it);

    Partition basement = new Partition("basement");
    basement.addChild(parking);
    Partition groundFloor = new Partition("ground_floor");
    groundFloor.addChild(hall);
    groundFloor.addChild(room1);
    groundFloor.addChild(room2);
    Partition floor1 = new Partition("floor1");
    floor1.addChild(corridor);
    floor1.addChild(room3);
    floor1.addChild(it);

    Partition building = new Partition("building");
    building.addChild(basement);
    building.addChild(groundFloor);
    building.addChild(floor1);
    building.addChild(stairs);
    building.addChild(exterior);
    rootArea = building;
  }

  private void link(String doorId, Space from, Space to) {
    Door door = DirectoryDoors.getInstance().findDoorById(doorId);
    if (door == null) {
      throw new IllegalArgumentException("door with id " + doorId + " not found");
    }
    door.setSpaces(from, to);
    to.addDoorGivingAccess(door);
  }

  public Area findAreaById(String id) {
    FindAreaByIdVisitor visitor = new FindAreaByIdVisitor(id);
    rootArea.accept(visitor);
    return visitor.getResultArea();
  }
}
