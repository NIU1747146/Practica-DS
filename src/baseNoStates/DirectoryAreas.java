package baseNoStates;

// Builds and keeps the tree of areas of the building shown in the simulator, and gives access to
// it through its root (the building). Static like DirectoryDoors and DirectoryUsers.
public final class DirectoryAreas {
  private static Area rootArea;

  // must be called after DirectoryDoors.makeDoors()
  public static void makeAreas() {
    Space parking = new Space("parking");
    Space hall = new Space("hall");
    Space room1 = new Space("room1");
    Space room2 = new Space("room2");
    Space corridor = new Space("corridor");
    Space room3 = new Space("room3");
    Space it = new Space("IT");
    Space stairs = new Space("stairs");
    Space exterior = new Space("exterior");

    // door, from (where the reader is), to (the space you enter)
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

  private static void link(String doorId, Space from, Space to) {
    Door door = DirectoryDoors.findDoorById(doorId);
    door.setSpaces(from, to);
    to.addDoorGivingAccess(door);
  }

  public static Area findAreaById(String id) {
    return rootArea.findAreaById(id);
  }
}
