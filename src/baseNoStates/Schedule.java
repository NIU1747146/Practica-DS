package baseNoStates;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Schedule {
  private LocalDate fromDate;
  private LocalDate toDate;
  private ArrayList<DayOfWeek> daysOfWeek;
  private LocalTime fromHour;
  private LocalTime toHour;

  public Schedule(LocalDate fDate, LocalDate tDate, ArrayList<DayOfWeek> days, LocalTime fHour, LocalTime tHour) {
    this.fromDate = fDate;
    this.toDate = tDate;
    this.daysOfWeek = days;
    this.fromHour = fHour;
    this.toHour = tHour;
  }

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
