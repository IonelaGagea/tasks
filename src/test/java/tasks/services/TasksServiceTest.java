package tasks.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import tasks.model.ArrayTaskList;
import tasks.model.LinkedTaskList;
import tasks.model.Task;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class TasksServiceTest {

    TasksService service;

    @BeforeEach
    void setUp() {
        service = new TasksService(new LinkedTaskList());

    }

    @Test
    void parseFromStringToSeconds_ECP_valid() {
        String seconds = "4:23";

        int result = service.parseFromStringToSeconds(seconds);

        assertEquals(15780, result);
    }

    @Test
    void parseFromStringToSeconds_ECP_invalid() {
        String seconds = "j4:23";

        try {
            service.parseFromStringToSeconds(seconds);
        } catch (NumberFormatException ex) {
            assert (true);
        }
    }

    @Test
    void parseFromStringToSeconds_BVA_valid_lowerBound() {
        String seconds = "00:00";

        int result = service.parseFromStringToSeconds(seconds);

        assertEquals(0, result);
    }

    @Test
    void parseFromStringToSeconds_BVA_valid_upperBound() {
        String seconds = "23:59";

        int result = service.parseFromStringToSeconds(seconds);

        assertEquals(86340, result);
    }

    @Test
    void parseFromStringToSeconds_BVA_invalid_wrongInput() {
        String seconds = "12.00";

        try {
            service.parseFromStringToSeconds(seconds);
        } catch (NumberFormatException ex) {
            assert (true);
        }
    }

    @Test
    @Disabled
    void parseFromStringToSeconds_BVA_invalid_outOfRange() {
        String seconds = "24:00";

        try {
            service.parseFromStringToSeconds(seconds);
        } catch (IllegalArgumentException ex) {
            assert (true);
        }

    }


}