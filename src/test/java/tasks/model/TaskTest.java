package tasks.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import tasks.model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


class TaskTest {
    private SimpleDateFormat dateFormat;
    private Task mockActiveTask;
    private Task mockTask;
    private Task mockRepetitiveActiveTask;
    private Date start;
    private Date end;

    @BeforeEach
    void setUp() throws ParseException {
        String pattern = "dd/MM/yyyy";
        dateFormat = new SimpleDateFormat(pattern);
        end = dateFormat.parse("24/03/2023");
        start = dateFormat.parse("21/03/2023");

        int interval = 3;

        mockActiveTask = new Task("test", start, end, interval);
        mockActiveTask.setActive(true);

        mockRepetitiveActiveTask = new Task("test non rep", start, end, 2);
        mockRepetitiveActiveTask.setTime(start, end, 3);
        mockRepetitiveActiveTask.setActive(true);

        mockTask = new Task("test", start, end, interval);
        mockTask.setActive(false);

    }
    @AfterEach
    void tearDown(){

    }

    @Test
    void F02_TC01() throws ParseException {
        Date testDate = dateFormat.parse("24/03/2023");

        Date result = mockActiveTask.nextTimeAfter(testDate);

        assertNull(result);
    }

    @Test
    void F02_TC02() throws ParseException {
        Date testDate = dateFormat.parse("20/03/2023");

        Date result = mockTask.nextTimeAfter(testDate);

        assertNull(result);
    }

    @Test
    void F02_TC03() throws ParseException {
        mockTask.setActive(true);
        mockTask.setTime(start, end, 0);
        Date testDate = dateFormat.parse("20/03/2023");

        Date result = mockTask.nextTimeAfter(testDate);

        assertEquals(start, result);
    }

    @Test
    void F02_TC04() throws ParseException {
        Date testDate = dateFormat.parse("22/03/2023");
        testDate.setTime(400);

        Date result = mockRepetitiveActiveTask.nextTimeAfter(testDate);

        assertEquals(result, start);
    }

    @Test
    void F02_TC05() throws ParseException {
        Date testDate = dateFormat.parse("20/03/2023");

        Date result = mockTask.nextTimeAfter(testDate);

        assertNull(result);
    }

    @Test
    void F02_TC06() throws ParseException {
        mockActiveTask.setTime(start, end, 0);
        Date testDate = dateFormat.parse("20/03/2023");

        Date result = mockActiveTask.nextTimeAfter(testDate);

        assertEquals(start, result);
    }


    @Test
    void F02_TC09() {
        Date testDate = start;

        Date result = mockRepetitiveActiveTask.nextTimeAfter(testDate);

        assertEquals(start.getTime() + 3 * 1000, result.getTime());
    }

    @Test
    void F02_TC10() throws ParseException {
        Date testDate = dateFormat.parse("23/03/2023");

        Date result = mockRepetitiveActiveTask.nextTimeAfter(testDate);

        assertEquals(testDate.getTime() + 3 * 1000, result.getTime());
    }
}
