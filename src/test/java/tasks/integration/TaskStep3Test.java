package tasks.integration;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
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
public class TaskStep3Test {
    private LinkedTaskList taskList;
    private TasksService service;

    private Task firstMock, secondMock;

    @BeforeEach
    void setUp(){
        taskList = new LinkedTaskList();
        firstMock = Mockito.mock(Task.class);
        secondMock = Mockito.mock(Task.class);

        Date startMock = new GregorianCalendar(2023, Calendar.JULY, 4, 9, 10, 0).getTime();
        Date nextTimeAfterMock = new GregorianCalendar(2023, Calendar.JULY, 5, 9, 10, 0).getTime();

        Mockito.when(firstMock.nextTimeAfter(startMock)).thenReturn(nextTimeAfterMock);

        service = new TasksService(taskList);
    }

    @AfterEach
    void tearDown(){
        taskList = new LinkedTaskList();
        service = new TasksService(taskList);
    }

    @Test
    void getObservableListTest(){
        service.addTask(firstMock);
        service.addTask(secondMock);

        ObservableList<Task> result = service.getObservableList();

        assertEquals(result.size(), 2);
    }

    @Test
    void getFilteredTasksTest(){
        Date start = new GregorianCalendar(2023, Calendar.JULY, 4, 9, 10, 0).getTime();
        Date end = new GregorianCalendar(2023, Calendar.JULY, 7, 9, 10, 0).getTime();
        service.addTask(firstMock);
        service.addTask(secondMock);

        ArrayList<Task> result = (ArrayList<Task>) service.filterTasks(start, end);

        assertEquals(result.size(), 1);
    }
}
