package milestone1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

// Person who sends requests, identified by a credential. It does not know its own permissions:
// it asks its UserGroup, so they are changed in one place per group.
public class User {
  private final String name;
  private final String credential;
  private UserGroup group;
  private static final Logger logger = LoggerFactory.getLogger(User.class);

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
    boolean allowed = group != null && group.isWithinSchedule(now);
    logger.debug("User {} can send a request at {}: {}", name, now, allowed);
    return allowed;
  }

  public boolean cannotAccessSpace(Space space) {
    return group == null || !group.hasSpace(space);
  }

  public boolean canDoAction(String action) {
    boolean allowed = group != null && group.hasAction(action);
    logger.debug("User {} can do action {}: {}", name, action, allowed);
    return allowed;
  }

  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }
}
