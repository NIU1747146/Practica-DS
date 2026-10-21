package milestone1;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

// Dates, weekdays and hours in which a user group can send requests.
public class Schedule {
  private final LocalDate fromDate;
  private final LocalDate toDate;
  private final ArrayList<DayOfWeek> daysOfWeek;
  private final LocalTime fromHour;
  private final LocalTime toHour;

  public Schedule(LocalDate fromDate, LocalDate toDate, ArrayList<DayOfWeek> daysOfWeek,
                  LocalTime fromHour, LocalTime toHour) {
    this.fromDate = fromDate;
    this.toDate = toDate;
    this.daysOfWeek = daysOfWeek;
    this.fromHour = fromHour;
    this.toHour = toHour;
  }

  // limits included
  public boolean isWithin(LocalDateTime dateTime) {
    LocalDate date = dateTime.toLocalDate();
    LocalTime time = dateTime.toLocalTime();
    DayOfWeek day = dateTime.getDayOfWeek();

    boolean validDate = !date.isBefore(fromDate) && !date.isAfter(toDate);
    boolean validDay = daysOfWeek.contains(day);
    boolean validTime = !time.isBefore(fromHour) && !time.isAfter(toHour);

    return validDate && validDay && validTime;
  }
}
