package todoApp;

import org.junit.jupiter.params.provider.Arguments;
import ru.buttonone.todoApp.data.Task;

import java.util.stream.Stream;

import static ru.buttonone.todoApp.constants.TestValue.TASK_ID;
import static ru.buttonone.todoApp.constants.TestValue.TASK_TEXT;

public class TestData {
    private static Task createTaskData(long taskId, String taskText, Boolean taskStatus) {
        return new Task(taskId, taskText, taskStatus);
    }

    public static Stream<Arguments> addNewTaskTestData() {
        Task newTask = createTaskData(TASK_ID, TASK_TEXT, false);
        return Stream.of(Arguments.of(newTask, TASK_ID));
    }

    public static Stream<Arguments> updateTextTaskTestData() {
        Task newTask = createTaskData(TASK_ID, TASK_TEXT, false);
        Task updateTask = createTaskData(TASK_ID, "updateText", false);
        return Stream.of(Arguments.of(newTask, updateTask, TASK_ID));
    }
}