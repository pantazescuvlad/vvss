package tasks.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import tasks.model.ArrayTaskList;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DateServiceTest {

    DateService dateService;

    @BeforeAll
    void setUp() {
        TasksService tasksService = new TasksService(new ArrayTaskList());
        dateService = new DateService(tasksService);
    }

    @Test
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