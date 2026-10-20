package baseNoStates;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserGroup {
  private Schedule schedule;
  private ArrayList<String> actions;
  private String name;
  private ArrayList<Area> areas;
  private ArrayList<User> users;

  public UserGroup(String name, Schedule schedule, ArrayList<String> actions, ArrayList<Area> areas,
                   ArrayList<User> users) {
    this.name = name;
    this.schedule = schedule;
    this.users = users;
    this.actions = actions;
    this.areas = areas;

    for (int i = 0; i < users.size(); i++) {
      users.get(i).setGroup(this);
    }

  }

  public boolean isWithinSchedule(LocalDateTime dateTime) {
    return schedule.isWithin(dateTime);
  }

  public boolean hasAction(String action) {
    return actions.contains(action);
  }

  public boolean hasSpace(Space space) {
    for (int i = 0; i < areas.size(); i++) {
      ArrayList<Space> spaces = areas.get(i).getSpaces();

      if (spaces.contains(space)) {
        return true;
      }
    }

    return false;
  }

  public User findUserByCredentials(String credential) {
    for (int i = 0; i < users.size(); i++) {
      String concrete_credential = users.get(i).getCredential();

      if (concrete_credential.equals(credential)) {
        return users.get(i);
      }
    }

    return null;


  }

}
