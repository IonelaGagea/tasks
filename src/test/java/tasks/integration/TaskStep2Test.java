package tasks.integration;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import tasks.model.LinkedTaskList;
import tasks.model.Task;
import tasks.services.TasksService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TaskStep2Test {
    private LinkedTaskList taskList;
    private TasksService service;
    private Date start, end;

    @BeforeAll
    void setUp(){
        taskList = new LinkedTaskList();
        Task firstMock = Mockito.mock(Task.class);
        Task secondMock = Mockito.mock(Task.class);

        start = new GregorianCalendar(2023, Calendar.JULY, 4, 9, 10, 0).getTime();
        end = new GregorianCalendar(2023, Calendar.JULY, 6, 10, 10, 0).getTime();
        Date nextTimeAfterMock = new GregorianCalendar(2023, Calendar.JULY, 5, 10, 10, 0).getTime();

        Mockito.when(firstMock.getStartTime()).thenReturn(start);
        Mockito.when(firstMock.getEndTime()).thenReturn(end);
        Mockito.when(firstMock.nextTimeAfter(start)).thenReturn(nextTimeAfterMock);

        taskList.add(firstMock); taskList.add(secondMock);
        service = new TasksService(taskList);
    }

    @Test
    void getObservableListTest(){
        ObservableList<Task> result = service.getObservableList();

        assertEquals(result.size(), 2);
    }

    @Test
    void getFilteredTaskList(){
        ArrayList<Task> result = (ArrayList<Task>) service.filterTasks(start, end);

        assertEquals(result.size(), 1);
    }

}
