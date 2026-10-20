package baseNoStates;

import java.time.LocalDateTime;

public class User {
  private final String name;
  private final String credential;
  private UserGroup group;

  public User(String name, String credential) {
    this.name = name;
    this.credential = credential;
  }

  public String getName() {
    return name;
  }
  public String getCredential() {
    return credential;
  }

  public void setGroup(UserGroup group) {
    this.group = group;
  }

  public boolean canSendRequest(LocalDateTime now) {
    return group != null && group.isWithinSchedule(now);
  }

  public boolean canAccessSpace(Space space) {
    return group != null && group.hasSpace(space);
  }

  public boolean canDoAction(String action) {
    return group != null && group.hasAction(action);
  }

  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }
}
