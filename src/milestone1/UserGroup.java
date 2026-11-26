package milestone1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import milestone2.SpacesVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// UserGroup.java
// Role (employees, managers...) with the schedule, actions and areas allowed to its users;
// building it assigns the group to its users. Areas may be partitions, so to know if a space is
// allowed it visits the area with a SpacesVisitor.
public class UserGroup {
  private final String name;
  private final Schedule schedule;
  private final ArrayList<String> actions;
  private final ArrayList<Area> areas;
  private static final Logger logger = LoggerFactory.getLogger(UserGroup.class);

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
        logger.debug("Group {} has access to space {}", name, space.getId());
        return true;
      }
    }
    logger.debug("Group {} has no access to space {}", name, space.getId());
    return false;
  }
}
