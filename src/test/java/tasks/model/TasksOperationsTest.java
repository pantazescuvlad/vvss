package tasks.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TasksOperationsTest {

    TasksOperations tasksOperations;

    @BeforeAll
    void setUp() {
        List<Task> tasks = new ArrayList<>();
        Task t1 = new Task("task1", new Date("2020/4/10"));
        t1.setActive(true);
        tasks.add(t1);

        ObservableList<Task> tasksList = FXCollections.observableArrayList(tasks);
        tasksOperations = new TasksOperations(tasksList);
    }

    @Test
    void incoming_nextTime_is_null_true() {
        Date start = new Date("3005/3/20");
        Date end = new Date("3005/3/21");

        assert(((Collection<?>)tasksOperations.incoming(start, end)).isEmpty());
    }

    @Test
    void incoming_nextTime_is_null_false_before_end_true() {
        Date start = new Date("2020/4/5");
        Date end = new Date("2020/4/15");
        int size = ((Collection<?>)tasksOperations.incoming(start, end)).size();
        assert(size == 1);
    }

    @Test
    void incoming_nextTime_is_null_false_before_end_false_equals_end_true() {
        Date start = new Date("2020/4/5");
        Date end = new Date("2020/4/10");

        assert(((Collection<?>)tasksOperations.incoming(start, end)).size() == 1);
    }

    @Test
    void incoming_nextTime_is_null_false_before_end_false_equals_end_false() {
        Date start = new Date("2020/4/5");
        Date end = new Date("2020/4/8");

        assert(((Collection<?>)tasksOperations.incoming(start, end)).size() == 0);
    }
}