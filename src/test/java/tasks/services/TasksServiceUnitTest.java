package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import tasks.model.LinkedTaskList;
import tasks.model.Task;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TasksServiceUnitTest {
    private LinkedTaskList taskList;
    private TasksService service;
    private Date start, end;

    @BeforeEach
    void setUp(){
        taskList = Mockito.mock(LinkedTaskList.class);
        service = new TasksService(taskList);
        start = new GregorianCalendar(2023, Calendar.JULY, 5, 9, 10, 0).getTime();
        end = new GregorianCalendar(2023, Calendar.JULY, 7, 9, 10, 0).getTime();
    }

    @Test
    void getObservableListTest(){
        Task firstTask = new Task("t1", start, end, 5);
        Task secondTask = new Task("t2", start, end, 5);
        Mockito.when(taskList.getAll()).thenReturn(FXCollections.observableArrayList(Arrays.asList(firstTask, secondTask)));

        ObservableList<Task> result = service.getObservableList();

        assertEquals(result.size(), 2);
    }

    @Test
    void getStringsToSecondsTest(){
        int result = service.parseFromStringToSeconds("10:00");

        assertEquals(result, 36000);
    }
}
