package milestone1;

// Names of the actions a request can ask for. They are the same strings the simulator sends and
// the ones each UserGroup stores to say what its users may do. Only constants, not instantiable.
public final class Actions {
  private Actions() {
    // restrict instantiation
  }

  // possible actions in reader and area requests
  public static final String LOCK = "lock";
  public static final String UNLOCK = "unlock";
  public static final String UNLOCK_SHORTLY = "unlock_shortly";

  // possible actions in door requests
  public static final String OPEN = "open";
  public static final String CLOSE = "close";
}