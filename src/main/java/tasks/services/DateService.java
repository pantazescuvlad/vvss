package tasks.services;


import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateService {
    static final int SECONDS_IN_MINUTE = 60;
    static final int MINUTES_IN_HOUR = 60;
    private static final int HOURS_IN_A_DAY = 24;
    private Calendar gregorianCalendar = new GregorianCalendar();

    private TasksService service;

    public DateService(TasksService service){
        this.service=service;
    }
    public static LocalDate getLocalDateValueFromDate(Date date){//for setting to DatePicker - requires LocalDate
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    }
    public Date getDateValueFromLocalDate(LocalDate localDate){//for getting from DatePicker
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        return Date.from(instant);
    }

    public Date getDateMergedWithTime(String time, Date noTimeDate) {//to retrieve Date object from both DatePicker and time field
        String[] units = time.split(":");
        int hour = Integer.parseInt(units[0]);
        int minute = Integer.parseInt(units[1]);
        if (hour > HOURS_IN_A_DAY || minute > MINUTES_IN_HOUR) throw new IllegalArgumentException("time unit exceeds bounds");
        Calendar calendar = gregorianCalendar;
        calendar.setTime(noTimeDate);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        Date prevYear = cal.getTime();

        if(!calendar.getTime().after(prevYear)) throw new IllegalArgumentException("task can't be older than a year");
        return calendar.getTime();
    }

    public String getTimeOfTheDayFromDate(Date date){//to set in detached time field
        Calendar calendar = gregorianCalendar;
        calendar.setTime(date);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);

        return service.formTimeUnit(hours) + ":" + service.formTimeUnit(minutes);
    }


}