package milestone1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import milestone1.visitors.SpacesVisitor;

/**
 * Role with the actions, areas and schedule allowed to its users.
 */
public class UserGroup {
  private final String name;
  private final Schedule schedule;
  private final ArrayList<String> actions;
  private final ArrayList<Area> areas;

  // Each user gets its group here.
  public UserGroup(String name, Schedule schedule, ArrayList<String> actions,
                   ArrayList<Area> areas, ArrayList<User> users) {
    this.name = name;
    this.schedule = schedule;
    this.actions = actions;
    this.areas = areas;
    for (User user : users) {
      user.setGroup(this);
    }
  }

  public String getName() {
    return name;
  }

  public boolean isWithinSchedule(LocalDateTime dateTime) {
    return schedule.isWithin(dateTime);
  }

  public boolean hasAction(String action) {
    return actions.contains(action);
  }

  // an area may be a partition, so we check all its spaces
  public boolean hasSpace(Space space) {
    for (Area area : areas) {
      SpacesVisitor visitor = new SpacesVisitor();
      area.accept(visitor);
      if (visitor.getSpaces().contains(space)) {
        return true;
      }
    }
    return false;
  }
}
