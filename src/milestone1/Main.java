package milestone1;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

// Builds doors, areas and users, and starts the web server.
public class Main {
  public static void main(String[] args) {
    DirectoryDoors.makeDoors();
    DirectoryAreas.makeAreas();
    DirectoryUsers.makeUsers();
    new WebServer();
  }
}
