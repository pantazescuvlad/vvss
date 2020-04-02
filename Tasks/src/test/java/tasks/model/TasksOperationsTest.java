package tasks.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import tasks.services.DateService;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

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

    }

}