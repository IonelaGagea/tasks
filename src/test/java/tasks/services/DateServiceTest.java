package tasks.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tasks.model.ArrayTaskList;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateServiceTest {

    DateService dateService;
    private static final String formatErrMsg = "Invalid time format.";
    private static final String boundsErrMsg = "time unit exceeds bounds";
    private static final String valueErrMsg = "Invalid values for hh or mm.";
    private static final String dateErrMsg = "Invalid Date.";


    @BeforeEach
    void setUp() {
        ArrayTaskList arrayTaskList = new ArrayTaskList();
        TasksService tasksService = new TasksService(arrayTaskList);
        this.dateService = new DateService(tasksService);
    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest
    @ValueSource(strings = { "20:30", "21:30" })
    @Tag("valid")
    void getDateMergedWithTime_validData_OK_ECP_1(String time) throws Exception {
        // setup
        String pattern = "dd/MM/yyyy";
        String timePattern = "HH:mm";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        SimpleDateFormat resultFormat = new SimpleDateFormat(pattern + " " + timePattern);
        Date date = dateFormat.parse("21/03/2023");

        // act
        Date result = dateService.getDateMergedWithTime(time, date);

        // assert
        String formattedResult = resultFormat.format(result);
        assertEquals("21/03/2023 " + time, formattedResult);

    }

    @Tag("valid")
    @Test
    void getDateMergedWithTime_validData_OK_ECP_2() throws Exception {
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
        assertEquals("21/03/2023 10:59", formattedResult);


    }

    @Tag("invalid")
    @Test
    void getDateMergedWithTime_InvalidTime_Exception_ECP_1() {
        Exception exception = assertThrows(Exception.class, () -> {
            dateService.getDateMergedWithTime("20.30", new Date());
        });

        String errorMessage = exception.getMessage();
        assertEquals(formatErrMsg, errorMessage);
    }

    @RepeatedTest(value = 3, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("RepeatingTest")
    @Tag("invalid")
    void getDateMergedWithTime_InvalidTime_Exception_ECP_2() {
        Exception exception = assertThrows(Exception.class, () -> {
            dateService.getDateMergedWithTime("aa:59", new Date());
        });

        String errorMessage = exception.getMessage();
        assertEquals(valueErrMsg, errorMessage);
    }

    @Tag("valid")
    @Test
    void getDateMergedWithTime_validData_OK_BVA_1() throws Exception {
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
        assertEquals("21/03/2023 23:59", formattedResult);

    }

    @Tag("valid")
    @Test
    void getDateMergedWithTime_validData_OK_BVA_2() throws Exception {
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
        assertEquals("21/03/2023 00:00", formattedResult);

    }

    @Tag("invalid")
    @Test
    void getDateMergedWithTime_InvalidDate_Exception_BVA_1() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dateService.getDateMergedWithTime("20:30", null);
        });

        String errorMessage = exception.getMessage();
        assertEquals(dateErrMsg, errorMessage);
    }

    @Tag("invalid")
    @Test
    @Disabled
    void getDateMergedWithTime_InvalidTime_Exception_BVA_2() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dateService.getDateMergedWithTime("23:67", new Date());
        });

        String errorMessage = exception.getMessage();
        assertEquals(boundsErrMsg, errorMessage);
    }
}