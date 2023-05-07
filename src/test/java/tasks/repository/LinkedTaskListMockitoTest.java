package tasks.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import tasks.model.LinkedTaskList;
import tasks.model.Task;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LinkedTaskListMockitoTest {
    private LinkedTaskList taskList;

    private Task mockTask;

    @BeforeAll
    void setUp(){
        taskList = Mockito.mock(LinkedTaskList.class);
        mockTask = Mockito.mock(Task.class);
        Date startDate = new GregorianCalendar(2023, Calendar.MAY, 5, 13, 10, 0).getTime();

        Mockito.when(mockTask.getTitle()).thenReturn("mockTask");
        Mockito.when(mockTask.getStartTime()).thenReturn(startDate);
    }

    @Test
    public void addTaskTest(){
        Mockito.doNothing().when(taskList).add(mockTask);
        Mockito.when(taskList.size()).thenReturn(1);

        assertEquals(taskList.size(), 1);
    }

    @Test
    public void removeTaskTest(){
        Mockito.doNothing().when(taskList).add(mockTask);
        Mockito.when(taskList.remove(mockTask)).thenReturn(true);

        assertEquals(taskList.size(), 0);
    }
}
