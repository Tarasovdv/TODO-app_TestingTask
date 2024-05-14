package todoApp;

import org.junit.jupiter.params.provider.Arguments;
import ru.buttonone.todoApp.data.Task;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Stream;

import static constants_test.TestValue.*;

public class TestData {

    private static Task createTaskData(BigInteger taskId, String taskText, Boolean taskStatus) {
        return new Task(taskId, taskText, taskStatus);
    }

    public static Stream<Arguments> addNewTaskTestData() {
        Task newTask = createTaskData(FIRST_TASK_ID, FIRST_TASK_TEXT, false);

        return Stream.of(Arguments.of(newTask));
    }

    public static Stream<Arguments> addNewTaskWithMinAllowValueIdTestData() {
        Task newTask = createTaskData(ID_MIN_ALLOW_VALUE, "task#0", false);

        return Stream.of(Arguments.of(newTask));
    }

    public static Stream<Arguments> addNewTaskWithMaxAllowValueIdTestData() {
        Task newTask = createTaskData(ID_MAX_ALLOW_VALUE, "task#max", false);

        return Stream.of(Arguments.of(newTask));
    }

    public static Stream<Arguments> addNewTaskWithLessMinAllowValueIdTestData() {
        Task newTask = createTaskData(ID_LESS_MIN_ALLOW_VALUE, "task#lessmin", false);

        return Stream.of(Arguments.of(newTask));
    }

    public static Stream<Arguments> addNewTaskWithMoreMaxAllowValueIdTestData() {
        Task newTask = createTaskData(ID_MORE_MAX_ALLOW_VALUE, "task#moremax", false);

        return Stream.of(Arguments.of(newTask));
    }

    public static Stream<Arguments> addNewTaskWithEmptyValueIdTestData() {
        Task newTask = createTaskData(null, "task#moremax", false);

        return Stream.of(Arguments.of(newTask));
    }

    public static Stream<Arguments> deleteTaskTestData() {
        Task newTask = createTaskData(FOURTH_TASK_ID, FOURTH_TASK_TEXT, false);

        return Stream.of(Arguments.of(newTask));
    }

    public static Stream<Arguments> updateTextTaskTestData() {
        Task newTask = createTaskData(FIRST_TASK_ID, FIRST_TASK_TEXT, false);
        Task updateTask = createTaskData(FIRST_TASK_ID, "UpdateTask#1000", false);

        return Stream.of(Arguments.of(newTask, updateTask));
    }

    public static Stream<Arguments> updateStatusTaskTestData() {
        Task newTask = createTaskData(SECOND_TASK_ID, SECOND_TASK_TEXT, false);
        Task updateTask = createTaskData(SECOND_TASK_ID, SECOND_TASK_TEXT, true);

        return Stream.of(Arguments.of(newTask, updateTask));
    }

    public static Stream<Arguments> updateIdTaskTestData() {
        Task newTask = createTaskData(THIRD_TASK_ID, THIRD_TASK_TEXT, false);
        Task updateTask = createTaskData((new BigInteger("3333")), THIRD_TASK_TEXT, false);

        return Stream.of(Arguments.of(newTask, updateTask));
    }

    public static Stream<Arguments> updateTaskIdToIdExistTaskTestData() {
        Task fifthTask = createTaskData(FIFTH_TASK_ID, FIFTH_TASK_TEXT, false);
        Task sixthTask = createTaskData(SIXTH_TASK_ID, SIXTH_TASK_TEXT, false);
        Task updateSixthTask = createTaskData(FIFTH_TASK_ID, SIXTH_TASK_TEXT, false);
        List<Task> taskList = List.of(fifthTask, sixthTask);

        return Stream.of(Arguments.of(taskList, sixthTask, updateSixthTask));
    }

    public static Stream<Arguments> getTasksTestData() {
        Task firstTask = createTaskData(FIRST_TASK_ID, FIRST_TASK_TEXT, false);
        Task secondTask = createTaskData(SECOND_TASK_ID, SECOND_TASK_TEXT, false);
        Task thirdTask = createTaskData(THIRD_TASK_ID, THIRD_TASK_TEXT, false);
        List<Task> taskList = List.of(firstTask, secondTask, thirdTask);

        return Stream.of(Arguments.of(taskList));
    }

    public static Stream<Arguments> getTasksFilterLimitMinimumAllowValueTestData() {
        Task firstTask = createTaskData(FIRST_TASK_ID, FIRST_TASK_TEXT, false);
        Task secondTask = createTaskData(SECOND_TASK_ID, SECOND_TASK_TEXT, false);
        Task thirdTask = createTaskData(THIRD_TASK_ID, THIRD_TASK_TEXT, false);
        List<Task> taskList = List.of(firstTask, secondTask, thirdTask);

        return Stream.of(Arguments.of(taskList, FILTER_MIN_ALLOW_VALUE));
    }

    public static Stream<Arguments> getTasksFilterLimitMaximumAllowValueTestData() {
        Task firstTask = createTaskData(FIRST_TASK_ID, FIRST_TASK_TEXT, false);
        Task secondTask = createTaskData(SECOND_TASK_ID, SECOND_TASK_TEXT, false);
        Task thirdTask = createTaskData(THIRD_TASK_ID, THIRD_TASK_TEXT, false);
        List<Task> taskList = List.of(firstTask, secondTask, thirdTask);

        return Stream.of(Arguments.of(taskList, FILTER_MAX_ALLOW_VALUE));
    }

    public static Stream<Arguments> getTasksFilterOffsetMinimumAllowValueTestData() {
        Task firstTask = createTaskData(FIRST_TASK_ID, FIRST_TASK_TEXT, false);
        Task secondTask = createTaskData(SECOND_TASK_ID, SECOND_TASK_TEXT, false);
        Task thirdTask = createTaskData(THIRD_TASK_ID, THIRD_TASK_TEXT, false);
        List<Task> taskList = List.of(firstTask, secondTask, thirdTask);

        return Stream.of(Arguments.of(taskList, FILTER_MIN_ALLOW_VALUE));
    }

    public static Stream<Arguments> getTasksFilterOffsetMaximumAllowValueTestData() {
        Task firstTask = createTaskData(FIRST_TASK_ID, FIRST_TASK_TEXT, false);
        Task secondTask = createTaskData(SECOND_TASK_ID, SECOND_TASK_TEXT, false);
        Task thirdTask = createTaskData(THIRD_TASK_ID, THIRD_TASK_TEXT, false);
        List<Task> taskList = List.of(firstTask, secondTask, thirdTask);

        return Stream.of(Arguments.of(taskList, FILTER_MAX_ALLOW_VALUE));
    }

    public static Stream<Arguments> getTasksFilterOffsetMoreMaximumAllowValueTestData() {
        Task firstTask = createTaskData(FIRST_TASK_ID, FIRST_TASK_TEXT, false);
        Task secondTask = createTaskData(SECOND_TASK_ID, SECOND_TASK_TEXT, false);
        Task thirdTask = createTaskData(THIRD_TASK_ID, THIRD_TASK_TEXT, false);
        List<Task> taskList = List.of(firstTask, secondTask, thirdTask);

        return Stream.of(Arguments.of(taskList, FILTER_MORE_MAX_ALLOW_VALUE));
    }

    public static Stream<Arguments> getTasksFilterLimitMoreMaximumAllowValueTestData() {
        Task firstTask = createTaskData(FIRST_TASK_ID, FIRST_TASK_TEXT, false);
        Task secondTask = createTaskData(SECOND_TASK_ID, SECOND_TASK_TEXT, false);
        Task thirdTask = createTaskData(THIRD_TASK_ID, THIRD_TASK_TEXT, false);
        List<Task> taskList = List.of(firstTask, secondTask, thirdTask);

        return Stream.of(Arguments.of(taskList, FILTER_MORE_MAX_ALLOW_VALUE));
    }

    public static Stream<Arguments> getTasksFilterLimitLessMinimumAllowValueTestData() {
        Task firstTask = createTaskData(FIRST_TASK_ID, FIRST_TASK_TEXT, false);
        Task secondTask = createTaskData(SECOND_TASK_ID, SECOND_TASK_TEXT, false);
        Task thirdTask = createTaskData(THIRD_TASK_ID, THIRD_TASK_TEXT, false);
        List<Task> taskList = List.of(firstTask, secondTask, thirdTask);

        return Stream.of(Arguments.of(taskList, FILTER_LESS_MIN_ALLOW_VALUE));
    }

    public static Stream<Arguments> getTasksFilterOffsetLessMinimumAllowValueTestData() {
        Task firstTask = createTaskData(FIRST_TASK_ID, FIRST_TASK_TEXT, false);
        Task secondTask = createTaskData(SECOND_TASK_ID, SECOND_TASK_TEXT, false);
        Task thirdTask = createTaskData(THIRD_TASK_ID, THIRD_TASK_TEXT, false);
        List<Task> taskList = List.of(firstTask, secondTask, thirdTask);

        return Stream.of(Arguments.of(taskList, FILTER_LESS_MIN_ALLOW_VALUE));
    }

    public static Stream<Arguments> getTasksFilterLimitMaxAllowValOffsetMiddlRangeValTestData() {
        Task firstTask = createTaskData(FIRST_TASK_ID, FIRST_TASK_TEXT, false);
        Task secondTask = createTaskData(SECOND_TASK_ID, SECOND_TASK_TEXT, false);
        Task thirdTask = createTaskData(THIRD_TASK_ID, THIRD_TASK_TEXT, false);
        List<Task> taskList = List.of(firstTask, secondTask, thirdTask);

        return Stream.of(Arguments.of(taskList, FILTER_MAX_ALLOW_VALUE, FILTER_MIDDLE_RANGE_VALUE));
    }

    public static Stream<Arguments> getTasksFilterLimitMiddleRangeValueTestData() {
        Task firstTask = createTaskData(FIRST_TASK_ID, FIRST_TASK_TEXT, false);
        Task secondTask = createTaskData(SECOND_TASK_ID, SECOND_TASK_TEXT, false);
        Task thirdTask = createTaskData(THIRD_TASK_ID, THIRD_TASK_TEXT, false);
        List<Task> taskList = List.of(firstTask, secondTask, thirdTask);

        return Stream.of(Arguments.of(taskList, FILTER_MIDDLE_RANGE_VALUE));
    }

    public static Stream<Arguments> getTasksFilterOffsetMiddleRangeValueTestData() {
        Task firstTask = createTaskData(FIRST_TASK_ID, FIRST_TASK_TEXT, false);
        Task secondTask = createTaskData(SECOND_TASK_ID, SECOND_TASK_TEXT, false);
        Task thirdTask = createTaskData(THIRD_TASK_ID, THIRD_TASK_TEXT, false);
        List<Task> taskList = List.of(firstTask, secondTask, thirdTask);

        return Stream.of(Arguments.of(taskList, FILTER_MIDDLE_RANGE_VALUE));
    }

    public static Stream<Arguments> checkTaskSchemaTestData() {
        Task newTask = createTaskData(FIRST_TASK_ID, FIRST_TASK_TEXT, false);

        return Stream.of(Arguments.of(newTask, TASK_SCHEMA_PATH));
    }
}