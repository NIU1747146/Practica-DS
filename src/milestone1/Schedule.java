package milestone1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

// Dates, weekdays and hours in which a UserGroup can send requests. isWithin() says if a moment
// is inside the three of them, limits included.
public class Schedule {
  private final LocalDate fromDate;
  private final LocalDate toDate;
  private final ArrayList<DayOfWeek> daysOfWeek;
  private final LocalTime fromHour;
  private final LocalTime toHour;
  private static final Logger logger = LoggerFactory.getLogger(Schedule.class);

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

    boolean within = validDate && validDay && validTime;
    logger.debug("Schedule check of {}: date {}, day {}, time {} -> {}",
        dateTime, validDate, validDay, validTime, within);
    return within;
  }
}
