// Package name contains digits (milestone1/milestone2) as required by the project specification
package milestone1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

// Checkstyle PackageName rejects digits. We keep milestone1/milestone2 as package names on
// purpose: logback.xml enables or disables the logs of each milestone by package name.

// Main.java
// Starts the system: creates the doors, then the areas (they need the doors), then the users
// (they need the areas), and starts the web server that receives the requests.
// Checkstyle PackageName rejects digits. We keep milestone1/milestone2 as package names on
// purpose: logback.xml enables or disables the logs of each milestone by package name.
public class Main {
  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    logger.info("Starting the access control system");
    DirectoryDoors.getInstance().makeDoors();
    DirectoryAreas.getInstance().makeAreas();
    DirectoryUsers.getInstance().makeUsers();
    logger.info("Doors, areas and users created");
    new WebServer();
  }
}
