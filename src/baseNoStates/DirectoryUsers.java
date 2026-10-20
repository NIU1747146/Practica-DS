package baseNoStates;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public final class DirectoryUsers {
  private static final ArrayList<User> users = new ArrayList<>();

  public static void makeUsers() {
    //TODO: make user groups according to the specifications in the comments, because
    // now all are the same

    // users without any privilege, just to keep temporally users instead of deleting them,
    // this is to withdraw all permissions but still to keep user data to give back
    // permissions later
    // ==========================================
    // 1. BLANK / NO PRIVILEGES (Bernat, Blai)
    // ==========================================
    ArrayList<User> blankUsers = new ArrayList<>();
    blankUsers.add(new User("Bernat", "12345"));
    blankUsers.add(new User("Blai", "77532"));
    Schedule blankSchedule = new Schedule(LocalDate.of(2026, 1, 1),
                                     LocalDate.of(2026, 1, 2), new ArrayList<>(),
                                     LocalTime.of(0, 0), LocalTime.of(0, 1));

    new UserGroup("blank", blankSchedule, new ArrayList<>(), new ArrayList<>(), blankUsers);
    // employees :
    // Sep. 1 this year to Mar. 1 next year
    // week days 9-17h
    // just shortly unlock
    // ground floor, floor1, exterior, stairs (this, for all), that is, everywhere but the parking
    // ==========================================
    // 2. EMPLOYEES (Ernest, Eulalia)
    // ==========================================
    ArrayList<User> employeeUsers = new ArrayList<>();
    employeeUsers.add(new User("Ernest", "74984"));
    employeeUsers.add(new User("Eulalia", "43295"));
    ArrayList<DayOfWeek> weekDays = new ArrayList<>(Arrays.asList(
        DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY
    ));

    Schedule employeeSchedule = new Schedule(LocalDate.of(2026, 9, 1),
        LocalDate.of(2027, 3, 1), weekDays,
        LocalTime.of(9, 0), LocalTime.of(17, 0));

    ArrayList<String> empActions = new ArrayList<>(Arrays.asList("open", "close", "unlock_shortly"));
    ArrayList<Area> empAreas = new ArrayList<>();
    empAreas.add(DirectoryAreas.findAreaById("ground_floor"));
    empAreas.add(DirectoryAreas.findAreaById("floor1"));
    empAreas.add(DirectoryAreas.findAreaById("exterior"));
    empAreas.add(DirectoryAreas.findAreaById("stairs"));

    new UserGroup("employees", employeeSchedule, empActions, empAreas, employeeUsers);

    // managers :
    // Sep. 1 this year to Mar. 1 next year
    // week days + saturday, 8-20h
    // all actions
    // all spaces
    // ==========================================
    // 3. MANAGERS (Manel, Marta)
    // ==========================================
    ArrayList<User> managerUsers = new ArrayList<>();
    managerUsers.add(new User("Manel", "95783"));
    managerUsers.add(new User("Marta", "05827"));
    ArrayList<DayOfWeek> managerDays = new ArrayList<>(weekDays);
    managerDays.add(DayOfWeek.SATURDAY);

    Schedule managerSchedule = new Schedule(
        LocalDate.of(2026, 9, 1), LocalDate.of(2027, 3, 1),
        managerDays, LocalTime.of(8, 0), LocalTime.of(20, 0)
    );

    ArrayList<String> allActions = new ArrayList<>(Arrays.asList("open", "close", "lock", "unlock", "unlock_shortly"));
    ArrayList<Area> allAreas = new ArrayList<>();
    allAreas.add(DirectoryAreas.findAreaById("building"));

    new UserGroup("managers", managerSchedule, allActions, allAreas, managerUsers);

    // admin :
    // always=Jan. 1 this year to 2100
    // all days of the week
    // all actions
    // all spaces
    // ==========================================
    // 4. ADMIN (Ana)
    // ==========================================
    ArrayList<User> adminUsers = new ArrayList<>();
    adminUsers.add(new User("Ana", "11343"));
    ArrayList<DayOfWeek> allDays = new ArrayList<>(Arrays.asList(DayOfWeek.values()));

    Schedule adminSchedule = new Schedule(
        LocalDate.of(2026, 1, 1), LocalDate.of(2100, 1, 1),
        allDays, LocalTime.of(0, 0), LocalTime.of(23, 59)
    );

    new UserGroup("admin", adminSchedule, allActions, allAreas, adminUsers);

    users.addAll(blankUsers);
    users.addAll(employeeUsers);
    users.addAll(managerUsers);
    users.addAll(adminUsers);
  }

  public static User findUserByCredential(String credential) {
    for (User user : users) {
      if (user.getCredential().equals(credential)) {
        return user;
      }
    }
    System.out.println("user with credential " + credential + " not found");
    return null; // otherwise we get a Java error
  }

}
