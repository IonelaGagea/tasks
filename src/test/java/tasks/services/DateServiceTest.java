package tasks.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.model.ArrayTaskList;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateServiceTest {

    DateService dateService;
    private String formatErrMsg = "Invalid time format.";
    private String boundsErrMsg = "time unit exceeds bounds";
    private String valueErrMsg = "Invalid values for hh or mm.";
    private String dateErrMsg = "Invalid Date.";


    @BeforeEach
    void setUp() {
        ArrayTaskList arrayTaskList = new ArrayTaskList();
        TasksService tasksService = new TasksService(arrayTaskList);
        this.dateService = new DateService(tasksService);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getDateMergedWithTime_validData_OK_ECP_1() {
        try {
            // setup
            String time = "20:30";
            String pattern = "dd/MM/yyyy";
            String timePattern = "HH:mm";
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            SimpleDateFormat resultFormat = new SimpleDateFormat(pattern + " " + timePattern);
            Date date = dateFormat.parse("21/03/2023");

            // act
            Date result = dateService.getDateMergedWithTime(time, date);

            // assert
            String formattedResult = resultFormat.format(result);
            assert (formattedResult.equals("21/03/2023 20:30"));
        } catch (Exception ex) {
            assert (false);
        }
    }

    @Test
    void getDateMergedWithTime_validData_OK_ECP_2() {
        try {
            // setup
            String time = "10:59";
            String pattern = "dd/MM/yyyy";
            String timePattern = "HH:mm";
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            SimpleDateFormat resultFormat = new SimpleDateFormat(pattern + " " + timePattern);
            Date date = dateFormat.parse("21/03/2023");

            // act
            Date result = dateService.getDateMergedWithTime(time, date);

            // assert
            String formattedResult = resultFormat.format(result);
            assert (formattedResult.equals("21/03/2023 10:59"));

        } catch (Exception ex) {
            assert (false);
        }
    }

    @Test
    void getDateMergedWithTime_InvalidTime_Exception_ECP_1() {
        Exception exception = assertThrows(Exception.class, () -> {
            dateService.getDateMergedWithTime("20.30", new Date());
        });

        String errorMessage = exception.getMessage();
        assertEquals(formatErrMsg, errorMessage);
    }

    @Test
    void getDateMergedWithTime_InvalidTime_Exception_ECP_2() {
        Exception exception = assertThrows(Exception.class, () -> {
            dateService.getDateMergedWithTime("aa:59", new Date());
        });

        String errorMessage = exception.getMessage();
        assertEquals(valueErrMsg, errorMessage);
    }

    @Test
    void getDateMergedWithTime_validData_OK_BVA_1() {
        try {
            // setup
            String time = "23:59";
            String pattern = "dd/MM/yyyy";
            String timePattern = "HH:mm";
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            SimpleDateFormat resultFormat = new SimpleDateFormat(pattern + " " + timePattern);
            Date date = dateFormat.parse("21/03/2023");

            // act
            Date result = dateService.getDateMergedWithTime(time, date);

            // assert
            String formattedResult = resultFormat.format(result);
            assert (formattedResult.equals("21/03/2023 23:59"));

        } catch (Exception ex) {
            assert (false);
        }
    }

    @Test
    void getDateMergedWithTime_validData_OK_BVA_2() {
        try {
            // setup
            String time = "00:00";
            String pattern = "dd/MM/yyyy";
            String timePattern = "HH:mm";
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            SimpleDateFormat resultFormat = new SimpleDateFormat(pattern + " " + timePattern);
            Date date = dateFormat.parse("21/03/2023");

            // act
            Date result = dateService.getDateMergedWithTime(time, date);

            // assert
            String formattedResult = resultFormat.format(result);
            assert (formattedResult.equals("21/03/2023 00:00"));

        } catch (Exception ex) {
            assert(false);
        }
    }

    @Test
    void getDateMergedWithTime_InvalidDate_Exception_BVA_1(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dateService.getDateMergedWithTime("20:30", null);
        });

        String errorMessage = exception.getMessage();
        assertEquals(dateErrMsg, errorMessage);
    }

    @Test
    void getDateMergedWithTime_InvalidTime_Exception_BVA_2(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
           dateService.getDateMergedWithTime("23:67", new Date());
        });

        String errorMessage = exception.getMessage();
        assertEquals(boundsErrMsg, errorMessage);
    }
}