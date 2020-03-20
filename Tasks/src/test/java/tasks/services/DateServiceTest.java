package tasks.services;

import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import tasks.model.ArrayTaskList;

import java.util.Calendar;
import java.util.Date;


import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DateServiceTest {
    private DateService dateService;

    @BeforeAll
    void setUp() {
        TasksService tasksService = new TasksService(new ArrayTaskList());
        dateService = new DateService(tasksService);
    }

    DateService dateService;

    @BeforeAll
    void setUp() {
        TasksService tasksService = new TasksService(new ArrayTaskList());
        dateService = new DateService(tasksService);
    }

    @Test

    void getDateMergedWithTime_noTimeDate_valid_ECP() {
        String validTime = "10:00";
        Date validDate = new Date("2020/3/20");

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(validDate);

        Date result = dateService.getDateMergedWithTime(validTime, validDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(result);

        assert(calendar.get(Calendar.DATE) == calendar2.get(Calendar.DATE));
        assert(calendar.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH));
        assert(calendar.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR));
    }

    @Test
    void getDateMergedWithTime_noTimeDate_invalid_ECP() {
        String validTime = "10:00";
        Date validDate = new Date("2016/3/20");

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(validDate);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Date result = dateService.getDateMergedWithTime(validTime, validDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(result);
        });

        String expectedMessage = "task can't be older than a year";
        String actualMessage = exception.getMessage();

        assert(actualMessage.contains(expectedMessage));
    }

    @Test
    void getDateMergedWithTime_noTimeDate_valid_BVA() {
        String validTime = "00:00";

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        cal.add(Calendar.DATE, 1);
        Date validDate = cal.getTime();

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(validDate);

        Date result = dateService.getDateMergedWithTime(validTime, validDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(result);

        assert(calendar.get(Calendar.DATE) == calendar2.get(Calendar.DATE));
        assert(calendar.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH));
        assert(calendar.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR));
    }

    @Test
    void getDateMergedWithTime_noTimeDate_invalid_BVA() {
        String validTime = "23:59";

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        cal.add(Calendar.DATE, -1);
        Date validDate = cal.getTime();
        
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(validDate);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Date result = dateService.getDateMergedWithTime(validTime, validDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(result);
        });

        String expectedMessage = "task can't be older than a year";
        String actualMessage = exception.getMessage();

        assert(actualMessage.contains(expectedMessage));

    void getDateMergedWithTime_time_valid_ECP() {
        String valid_time = "08:11";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateService.getDateMergedWithTime(valid_time, new Date()));
        assert(calendar.get(Calendar.HOUR_OF_DAY) == 8);
        assert(calendar.get(Calendar.MINUTE) == 11);
    }

    @Test
    void getDateMergedWithTime_time_invalid_ECP() {
        String invalid_time = "abc";
        assertThrows(IllegalArgumentException.class, () -> dateService.getDateMergedWithTime(invalid_time, new Date()), "Expected IllegalArgumentException to be thrown.");
    }

    @Test
    void getDateMergedWithTime_time_valid_BVA() {
        String valid_time = "23:59";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateService.getDateMergedWithTime(valid_time, new Date()));
        assert(calendar.get(Calendar.HOUR_OF_DAY) == 23);
        assert(calendar.get(Calendar.MINUTE) == 59);
    }

    @Test
    void getDateMergedWithTime_time_invalid_BVA() {
        String valid_time = "24:00";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateService.getDateMergedWithTime(valid_time, new Date()));
        assertFalse(calendar.get(Calendar.HOUR_OF_DAY) == 24);

    }
}

//TODO: adnotari