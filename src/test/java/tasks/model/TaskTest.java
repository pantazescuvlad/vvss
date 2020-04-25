package tasks.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

class TaskTest {
    Task t;

    @BeforeEach
    void setUp() {
        t = new Task("test", new Date("2020/3/5"), new Date("2020/4/10"), 5);
        t.setActive(true);
    }

    @Test
    void nextTimeAfter_date_after_end(){
        assert(t.nextTimeAfter(new Date("2020/5/1")) == null);
    }

    @Test
    void nextTimeAfter_date_before_start(){
        System.out.println(t.nextTimeAfter(new Date("2020/3/1")));
        assert(t.nextTimeAfter(new Date("2020/3/1")).equals(t.getStartTime()));
    }
}