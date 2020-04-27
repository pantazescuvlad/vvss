package tasks.services;

import javafx.collections.FXCollections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.model.Task;
import tasks.repository.ArrayTaskList;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class IntegrationServiceTest {
    private TasksService service;
    private ArrayTaskList mockedRepository;

    @BeforeEach
    void setUp() {
        this.mockedRepository = mock(ArrayTaskList.class);
        this.service = new TasksService(this.mockedRepository);
    }

    @Test
    void test_get_observable_list() {
        Task t1 = new Task("title1", new Date("2020/4/27"));
        Task t2 = new Task("title2", new Date("2020/4/29"));
        Task t3 = new Task("title3", new Date("2020/5/10"));

        when(this.mockedRepository.size()).thenReturn(3);
        when(this.service.getObservableList()).thenReturn(FXCollections.observableArrayList(Arrays.asList(t1, t2, t3)));
        doNothing().when(this.mockedRepository).add(t1);

        this.mockedRepository.add(t1);
        this.mockedRepository.add(t2);
        this.mockedRepository.add(t3);

        verify(this.mockedRepository, times(1)).add(t1);
        assertEquals(3, this.service.getObservableList().size());
    }

    @Test
    void test_get_interval_in_hours() {
        Task t = new Task("title1", new Date("2020/4/27"), new Date("2020/4/30"), 5160);

        doNothing().when(this.mockedRepository).add(t);
        this.mockedRepository.add(t);
        verify(this.mockedRepository, times(1)).add(t);
        assertEquals("01:26", this.service.getIntervalInHours(t));
    }
}
