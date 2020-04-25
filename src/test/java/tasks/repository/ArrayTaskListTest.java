package tasks.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import tasks.model.Task;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ArrayTaskListTest {

    ArrayTaskList arrayTaskList;

    @BeforeEach
    void setUp() {
        arrayTaskList = new ArrayTaskList();
    }

    @Test
    void add_task_null(){
        Exception exception = assertThrows(NullPointerException.class, () -> {
            arrayTaskList.add(null);
        });

        String expectedMessage = "Task shouldn't be null";
        String actualMessage = exception.getMessage();

        assert (actualMessage.contains(expectedMessage));
    }

    @Test
    void add_task_success(){
        Task t = mock(Task.class);
        arrayTaskList.add(t);

        assert(arrayTaskList.getTask(0) == t);
    }
}