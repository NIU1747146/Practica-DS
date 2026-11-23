package milestone1;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

/**
 * Entry point of the application, responsible for initializing the system.
 */
public class Main {
  public static void main(String[] args) {
    DirectoryDoors.getInstance().makeDoors();
    DirectoryAreas.getInstance().makeAreas();
    DirectoryUsers.getInstance().makeUsers();
    new WebServer();
  }
}
