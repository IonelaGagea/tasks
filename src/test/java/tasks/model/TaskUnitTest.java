package tasks.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskUnitTest {
    private Task mockTask;
    private Date start, end;
    private String title;

    @BeforeEach
    void setUp(){
        title = "mockTask";
        start = new GregorianCalendar(2023, Calendar.JULY, 5, 9, 10, 0).getTime();
        end = new GregorianCalendar(2023, Calendar.JULY, 7, 9, 10, 0).getTime();

    }

    @Test
    void createTaskTest(){
        mockTask = new Task(title, start, end, 10);

        assertEquals(mockTask.getTitle(), title);
        assertEquals(mockTask.getStartTime(), start);
        assertEquals(mockTask.getEndTime(), end);
        assertEquals(mockTask.getRepeatInterval(), 10);
    }

    @Test
    void getStartTimeTest(){
        mockTask = new Task(title, start, end, 5);
        Date result = mockTask.getStartTime();

        assertEquals(result, start);
    }

}
