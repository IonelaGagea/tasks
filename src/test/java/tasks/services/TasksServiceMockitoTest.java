package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import tasks.model.LinkedTaskList;
import tasks.model.Task;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TasksServiceMockitoTest {

    private LinkedTaskList taskList;

    private TasksService service;

    @BeforeAll
    void setUp(){
        taskList = Mockito.mock(LinkedTaskList.class);
        service = new TasksService(taskList);
    }

    @Test
    void getObservableListTest(){
        Task firstMock = Mockito.mock(Task.class);
        Task secondMock = Mockito.mock(Task.class);
        Mockito.when(service.getObservableList()).thenReturn(FXCollections.observableArrayList(Arrays.asList(firstMock, secondMock)));

        ObservableList<Task> result = service.getObservableList();

        assertEquals(result.size(), 2);
    }

    @Test
    void getStringToSecondsTest(){
        int result = service.parseFromStringToSeconds("10:00");

        assertEquals(result, 36000);
    }
}
