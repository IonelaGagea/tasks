package tasks.repository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tasks.model.LinkedTaskList;
import tasks.model.Task;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedTaskListUnitTest {
    private static LinkedTaskList taskList;

    private static Task mockTask;

    @BeforeAll
    static void setUp(){
        taskList =   new LinkedTaskList();
        Date start = new GregorianCalendar(2023, Calendar.JULY, 5, 9, 10, 0).getTime();
        Date end = new GregorianCalendar(2023, Calendar.JULY, 7, 9, 10, 0).getTime();
        mockTask = new Task("mock", start, end, 5);
    }

    @AfterAll
    static void tearDown(){

    }

    @Test
    void addTaskTest(){
        int initialSize = taskList.size();

        taskList.add(mockTask);

        assertEquals(taskList.size(), initialSize + 1);
    }

    @Test
    void removeTaskTest(){
        taskList.add(mockTask);
        int initialSize = taskList.size();

        taskList.remove(mockTask);

        assertEquals(taskList.size(), initialSize - 1);
    }
}
