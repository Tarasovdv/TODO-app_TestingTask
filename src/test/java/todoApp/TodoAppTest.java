package todoApp;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.parallel.Isolated;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.buttonone.todoApp.api.TaskService;
import ru.buttonone.todoApp.data.Task;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.buttonone.todoApp.constants.HttpStatusCode.SUCCESS_REQ;

@Isolated
public class TodoAppTest {
    private final TaskService taskService = new TaskService();

    @DisplayName("Добавление новой задачи")
    @Description("TC-1 Отправить запрос на добавление новой задачи")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#addNewTaskTestData")
    public void addNewTask(Task newTask) {
        taskService
                .addNewTask(newTask)
                .checkTaskData(newTask)
                .cleanTaskData(newTask);
    }

    @DisplayName("Вывод списка всех задач")
    @Description("TC-2 Отправить запрос на вывод всех задач")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#getTasksTestData")
    public void getAllTasks(List<Task> taskList) {
        taskService
                .addAllTasks(taskList)
                .getAllTasks()
                .checkAllTasks(taskList);
        taskService.clearAllTasks(taskList);
    }

    @DisplayName("Изменение описания задачи")
    @Description("TC-3 Отправить запрос на изменение описания задачи")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#updateTextTaskTestData")
    public void updateTextTask(Task task, Task updateTask) {
        taskService
                .addNewTask(task)
                .getAllTasks()
                .updateTask(task, updateTask)
                .checkTaskData(updateTask)
                .cleanTaskData(updateTask);
    }

    @DisplayName("Изменение статуса задачи")
    @Description("TC-4 Отправить запрос на изменение статуса задачи")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#updateStatusTaskTestData")
    public void updateStatusTask(Task task, Task updateTask) {
        taskService
                .addNewTask(task)
                .getAllTasks()
                .updateTask(task, updateTask)
                .checkTaskData(updateTask)
                .cleanTaskData(updateTask);
    }

    @DisplayName("Изменение идентификатора задачи")
    @Description("TC-5 Отправить запрос на изменение идентификатора задачи")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#updateIdTaskTestData")
    public void updateIdTask(Task task, Task updateTask) {
        taskService
                .addNewTask(task)
                .getAllTasks()
                .updateTask(task, updateTask)
                .checkTaskData(updateTask)
                .cleanTaskData(updateTask);
    }

    @DisplayName("Удаление задачи")
    @Description("TC-6 Отправить запрос на удаление задачи")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#deleteTaskTestData")
    public void deleteTask(Task newTask) {
        taskService
                .addNewTask(newTask)
                .deleteTaskById(newTask);
    }

    @DisplayName("Вывод задач. Фильтр limit = {0}")
    @Description("TC-7 Отправить запрос на вывод задач, используя фильтр limit минимального допустимого значения")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#getTasksFilterLimitMinimumAllowValueTestData")
    public void getTasksFilterLimitMinimumAllowValue(List<Task> taskList, BigInteger limit) {
        taskService.addAllTasks(taskList);
        int actualStatusCode = taskService.getStatusLimitTasks(limit);
        assertEquals(SUCCESS_REQ.getCode(), actualStatusCode,
                "Actual Status Code = " + actualStatusCode
                        + "\nExpect Status Code = " + SUCCESS_REQ.getCode());
        assertTrue(taskService.checkAllTasks(taskService.getLimitTasks(limit)));
        taskService.clearAllTasks(taskList);
    }

    @DisplayName("Вывод задач. Фильтр limit = {3}")
    @Description("TC-8 Отправить запрос на вывод задач, используя фильтр limit максимального допустимого значения")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#getTasksFilterLimitMaximumAllowValueTestData")
    public void getTasksFilterLimitMaximumAllowValue(List<Task> taskList, BigInteger limit) {
        taskService.addAllTasks(taskList);
        int actualStatusCode = taskService.getStatusLimitTasks(limit);
        assertEquals(SUCCESS_REQ.getCode(), actualStatusCode,
                "Actual Status Code = " + actualStatusCode
                        + "\nExpect Status Code = " + SUCCESS_REQ.getCode());
        taskService.checkAllTasks(taskService.getLimitTasks(limit));
        taskService.clearAllTasks(taskList);
    }

    @DisplayName("Вывод задач. Фильтр limit = {2}")
    @Description("TC-9 Отправить запрос на вывод задач, используя для фильтра limit середину диапазона допустимых значения")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#getTasksFilterLimitMiddleRangeValueTestData")
    public void getTasksFilterLimitMiddleRangeValue(List<Task> taskList, BigInteger limit) {
        taskService.addAllTasks(taskList);
        int actualStatusCode = taskService.getStatusLimitTasks(limit);
        assertEquals(SUCCESS_REQ.getCode(), actualStatusCode,
                "Actual Status Code = " + actualStatusCode
                        + "\nExpect Status Code = " + SUCCESS_REQ.getCode());
        taskService.checkAllTasks(taskService.getLimitTasks(limit));
        taskService.clearAllTasks(taskList);
    }

    @DisplayName("Вывод задач. Фильтр limit = {4}")
    @Description("TC-10 Отправить запрос на вывод задач, используя фильтр limit больше максимального допустимого значения")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#getTasksFilterLimitMoreMaximumAllowValueTestData")
    public void getTasksFilterLimitMoreMaximumAllowValue(List<Task> taskList, BigInteger limit) {
        taskService.addAllTasks(taskList);
        int actualStatusCode = taskService.getStatusLimitTasks(limit);
        assertEquals(SUCCESS_REQ.getCode(), actualStatusCode,
                "Actual Status Code = " + actualStatusCode
                        + "\nExpect Status Code = " + SUCCESS_REQ.getCode());
        taskService.checkAllTasks(taskService.getLimitTasks(limit));
        taskService.clearAllTasks(taskList);
    }

    @DisplayName("Вывод задач. Фильтр offset = {0}")
    @Description("TC-11 Отправить запрос на вывод задач, используя фильтр limit минимального допустимого значения")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#getTasksFilterOffsetMinimumAllowValueTestData")
    public void getTasksFilterOffsetMinimumAllowValue(List<Task> taskList, BigInteger offset) {
        taskService.addAllTasks(taskList);
        int actualStatusCode = taskService.getStatusCodeOffsetTasks(offset);
        assertEquals(SUCCESS_REQ.getCode(), actualStatusCode,
                "Actual Status Code = " + actualStatusCode
                        + "\nExpect Status Code = " + SUCCESS_REQ.getCode());
        taskService.checkAllTasks(taskService.getOffsetTasks(offset));
        taskService.clearAllTasks(taskList);
    }

    @DisplayName("Вывод задач. Фильтр offset = {3}")
    @Description("TC-12 Отправить запрос на вывод задач, используя фильтр offset максимального допустимого значения")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#getTasksFilterOffsetMaximumAllowValueTestData")
    public void getTasksFilterOffsetMaximumAllowValue(List<Task> taskList, BigInteger offset) {
        taskService.addAllTasks(taskList);
        int actualStatusCode = taskService.getStatusCodeOffsetTasks(offset);
        assertEquals(SUCCESS_REQ.getCode(), actualStatusCode,
                "Actual Status Code = " + actualStatusCode
                        + "\nExpect Status Code = " + SUCCESS_REQ.getCode());
        assertTrue(taskService.checkAllTasks(taskService.getOffsetTasks(offset)));
        taskService.clearAllTasks(taskList);
    }

    @DisplayName("Вывод задач. Фильтр offset = {2}")
    @Description("TC-13 Отправить запрос на вывод задач, используя для фильтра offset середину диапазона допустимых значения")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#getTasksFilterOffsetMiddleRangeValueTestData")
    public void getTasksFilterOffsetMiddleRangeValue(List<Task> taskList, BigInteger offset) {
        taskService.addAllTasks(taskList);
        int actualStatusCode = taskService.getStatusCodeOffsetTasks(offset);
        assertEquals(SUCCESS_REQ.getCode(), actualStatusCode,
                "Actual Status Code = " + actualStatusCode
                        + "\nExpect Status Code = " + SUCCESS_REQ.getCode());
        taskService.checkAllTasks(taskService.getOffsetTasks(offset));
        taskService.clearAllTasks(taskList);
    }

    @DisplayName("Вывод задач. Фильтр offset = {4}")
    @Description("TC-14 Отправить запрос на вывод задач, используя фильтр offset больше максимального допустимого значения")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#getTasksFilterOffsetMoreMaximumAllowValueTestData")
    public void getTasksFilterOffsetMoreMaximumAllowValue(List<Task> taskList, BigInteger offset) {
        taskService.addAllTasks(taskList);
        int actualStatusCode = taskService.getStatusCodeOffsetTasks(offset);
        assertEquals(SUCCESS_REQ.getCode(), actualStatusCode,
                "Actual Status Code = " + actualStatusCode
                        + "\nExpect Status Code = " + SUCCESS_REQ.getCode());
        assertTrue(taskService.checkAllTasks(taskService.getOffsetTasks(offset)));
        taskService.clearAllTasks(taskList);
    }

    @DisplayName("Вывод задач. Фильтр limit = {3}. Фильтр offset = {2}")
    @Description("TC-15 Отправить запрос на вывод задач, используя фильтр limit максимального допустимого значения\n" +
            "и offset середину диапазона допустимых значений")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#getTasksFilterLimitMaxAllowValOffsetMiddlRangeValTestData")
    public void getTasksFilterLimitMaxAllowValOffsetMiddlRangeVal(List<Task> taskList, BigInteger limit, BigInteger offset) {
        taskService.addAllTasks(taskList);
        int actualStatusCode = taskService.getStatusCodeLimitAndOffsetTasks(limit, offset);
        assertEquals(SUCCESS_REQ.getCode(), actualStatusCode,
                "Actual Status Code = " + actualStatusCode
                        + "\nExpect Status Code = " + SUCCESS_REQ.getCode());
        taskService.checkAllTasks(taskService.getLimitAndOffsetTasks(limit, offset));
        taskService.clearAllTasks(taskList);
    }

    @DisplayName("Проверка структуры данных о задаче")
    @Description("TC-16 Проверка структуры данных о задаче")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#checkTaskSchemaTestData")
    public void checkTaskScheme(Task newTask, String jsonSchemaPath) {
        taskService
                .addNewTask(newTask)
                .checkJsonScheme(newTask, jsonSchemaPath)
                .cleanTaskData(newTask);
    }

    @DisplayName("Отправить запрос на добавление новой задачи с идентификатором минимального допустимого значения")
    @Description("TC-17 Отправить запрос на добавление новой задачи с идентификатором минимального допустимого значения")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#addNewTaskWithMinAllowValueIdTestData")
    public void addNewTaskWithMinAllowValueId(Task newTask) {
        taskService
                .addNewTask(newTask)
                .checkTaskData(newTask)
                .cleanTaskData(newTask);
    }

    @DisplayName("Отправить запрос на добавление новой задачи с идентификатором максимального допустимого значения")
    @Description("TC-18 Отправить запрос на добавление новой задачи с идентификатором максимального допустимого значения")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#addNewTaskWithMaxAllowValueIdTestData")
    public void addNewTaskWithMaxAllowValueId(Task newTask) {
        taskService
                .addNewTask(newTask)
                .checkTaskData(newTask)
                .cleanTaskData(newTask);
    }
}