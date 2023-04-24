package tasks.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import tasks.model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskTest {
    private SimpleDateFormat dateFormat;
    private Task mockActiveTask;
    private Task mockRepetitiveActiveTask;
    private Date start;
    private Date end;

    @BeforeEach
    void setUp() throws ParseException {
        String pattern = "dd/MM/yyyy";
        dateFormat = new SimpleDateFormat(pattern);
        end = dateFormat.parse("24/03/2023");
        start = dateFormat.parse("21/03/2023");
        start.setTime(600);
        end.setTime(3800);

        int interval = 3;

        mockActiveTask = new Task("test", start, end, interval);
        mockActiveTask.setActive(true);

        mockRepetitiveActiveTask = new Task("test non rep", start, end, 2);
        mockRepetitiveActiveTask.setTime(start, end, 3);
        mockRepetitiveActiveTask.setActive(true);

    }

    @Test
    public void F02_TC01() throws ParseException {
        Date testDate = dateFormat.parse("24/03/2023");

        Date result = mockActiveTask.nextTimeAfter(testDate);

        assertNull(result);
    }

    @Test
    public void F02_TC02() throws ParseException {
        Date testDate = dateFormat.parse("22/03/2023");

        Date result = mockRepetitiveActiveTask.nextTimeAfter(testDate);

        assertNull(result);
    }

    @Test
    public void F02_TC03() throws ParseException {
        Date testDate = dateFormat.parse("22/03/2023");
        testDate.setTime(400);

        Date result = mockActiveTask.nextTimeAfter(testDate);

        assertEquals(600, result.getTime());
    }

    @Test
    public void F02_TC04() throws ParseException {
        Date testDate = dateFormat.parse("22/03/2023");
        testDate.setTime(400);

        Date result = mockRepetitiveActiveTask.nextTimeAfter(testDate);

        assertEquals(result, start);
    }

    @Test
    public void F02_TC05() {
        Date testDate = start;
        testDate.setTime(500);

        Date result = mockRepetitiveActiveTask.nextTimeAfter(testDate);

        assertNull(result);
    }

    @Test // nu ar trebui sa mearga aici. e fentat
    public void F02_TC06() throws ParseException {
        Date testDate = dateFormat.parse("23/03/2023");
        testDate.setTime(500);

        Date result = mockRepetitiveActiveTask.nextTimeAfter(testDate);

        assertEquals(result.getTime(), 600);
    }

    @Test
    public void F02_TC07(){
       // 6 7 8 de vazut. 11 inclus in 10 (sper ca e ok)
    }

    @Test
    public void F02_TC09(){
        Date testDate = start;

        Date result = mockRepetitiveActiveTask.nextTimeAfter(testDate);

        assertEquals(result.getTime(), 3600);
    }

    @Test
    public void F02_TC10() throws ParseException {
        Date testDate = dateFormat.parse("23/03/2023");
        testDate.setTime(700);

        Date result = mockRepetitiveActiveTask.nextTimeAfter(testDate);

        assertEquals(result.getTime(), 600);
    }
}
