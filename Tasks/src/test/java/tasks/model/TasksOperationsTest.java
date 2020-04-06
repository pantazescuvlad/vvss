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
        ObservableList<Task> tasksList = FXCollections.observableArrayList(tasks);
        tasksOperations = new TasksOperations(tasksList);
    }

    @Test
    void incoming_nextTime_is_null(){
        Date start = new Date("3005/3/20");
        Date end = new Date("3005/3/21");

        assert(((Collection<?>)tasksOperations.incoming(start, end)).isEmpty());
    }

}