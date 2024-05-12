package todoApp;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.buttonone.todoApp.api.TaskService;
import ru.buttonone.todoApp.data.Task;

import java.math.BigInteger;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.buttonone.todoApp.constants.Endpoints.TODO;
import static ru.buttonone.todoApp.constants.HttpStatusCode.INCORRECT_INPUT;
import static ru.buttonone.todoApp.constants.HttpStatusCode.UNSUPPORT_TYPE;
import static ru.buttonone.todoApp.spec.Spec.BASE_URI;

@Slf4j
public class NegativeTodoAppTest {
    private final TaskService taskService = new TaskService();

    @DisplayName("Вывод задач. Фильтр limit = {-1}")
    @Description("TC-19 Отправить запрос на вывод задач, используя фильтр limit меньше минимального допустимого значения")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#getTasksFilterLimitLessMinimumAllowValueTestData")
    public void getTasksFilterLimitLessMinimumAllowValue(List<Task> taskList, BigInteger limit) {
        taskService.addAllTasks(taskList);

        int actualStatusCode = taskService.getStatusLimitTasks(limit);
        assertEquals(INCORRECT_INPUT.getCode(), actualStatusCode,
                "Actual Status Code = " + actualStatusCode
                        + "\nExpect Status Code = " + INCORRECT_INPUT.getCode());

        taskService.clearAllTasks(taskList);
    }

    @DisplayName("Вывод задач. Фильтр offset = {-1}")
    @Description("TC-20 Отправить запрос на вывод задач, используя фильтр offset меньше минимального допустимого значения")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#getTasksFilterOffsetLessMinimumAllowValueTestData")
    public void getTasksFilterOffsetLessMinimumAllowValue(List<Task> taskList, BigInteger offset) {
        taskService.addAllTasks(taskList);

        int actualStatusCode = taskService.getStatusCodeOffsetTasks(offset);
        assertEquals(INCORRECT_INPUT.getCode(), actualStatusCode,
                "Actual Status Code = " + actualStatusCode
                        + "\nExpect Status Code = " + INCORRECT_INPUT.getCode());

        taskService.clearAllTasks(taskList);
    }

    @Disabled
    @DisplayName("Изменение идентификатора задачи на идентификатор существующей задачи")//todo find bag (see bag-report)
    @Description("TC-21 Отправить запрос на изменение идентификатора задачи на идентификатор существующей задачи")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#updateTaskIdToIdExistTaskTestData")
    public void updateTaskIdToIdExistTask(List<Task> taskList, Task task, Task updateTask) {
        taskService.addAllTasks(taskList);
        int actualStatusCode = taskService.getStatusCodeUpdateTasks(task, updateTask);
        assertEquals(INCORRECT_INPUT.getCode(), actualStatusCode,
                "Actual Status Code = " + actualStatusCode
                        + "\nExpect Status Code = " + INCORRECT_INPUT.getCode());
        taskService.cleanTaskData(updateTask);
    }

    @DisplayName("Отправить запрос на добавление новой задачи с идентификатором меньше минимального допустимого значения")
    @Description("TC-22 Отправить запрос на добавление новой задачи с идентификатором меньше минимального допустимого значения")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#addNewTaskWithLessMinAllowValueIdTestData")
    public void addNewTaskWithLessMinAllowValueId(Task newTask) {
        int actualStatusCode = taskService.getStatusCodeAddNewTask(newTask);
        assertEquals(UNSUPPORT_TYPE.getCode(), actualStatusCode,
                "Actual Status Code = " + actualStatusCode
                        + "\nExpect Status Code = " + UNSUPPORT_TYPE.getCode());
    }

    @DisplayName("Отправить запрос на добавление новой задачи с идентификатором больше максимального допустимого значения")
    @Description("TC-23 Отправить запрос на добавление новой задачи с идентификатором больше максимального допустимого значения")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#addNewTaskWithMoreMaxAllowValueIdTestData")
    public void addNewTaskWithMoreMaxAllowValueId(Task newTask) {
        int actualStatusCode = taskService.getStatusCodeAddNewTask(newTask);
        assertEquals(UNSUPPORT_TYPE.getCode(), actualStatusCode,
                "Actual Status Code = " + actualStatusCode
                        + "\nExpect Status Code = " + UNSUPPORT_TYPE.getCode());
    }

    @DisplayName("Отправить запрос на добавление новой задачи с пустым значением идентификатора")
    @Description("TC-24  Отправить запрос на добавление новой задачи с пустым значением идентификатора")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#addNewTaskWithEmptyValueIdTestData")
    public void addNewTaskWithEmptyValueId(Task newTask) {
        int actualStatusCode = taskService.getStatusCodeAddNewTask(newTask);
        assertEquals(UNSUPPORT_TYPE.getCode(), actualStatusCode,
                "Actual Status Code = " + actualStatusCode
                        + "\nExpect Status Code = " + UNSUPPORT_TYPE.getCode());
    }

    @Test
    @DisplayName("Отправить запрос на добавление новой задачи с текстовым значением идентификатора")
    @Description("TC-25 Отправить запрос на добавление новой задачи с текстовым значением идентификатора")
    public void addNewTaskWithEmptyValueId() {
        log.info("Запрос на добавление задачи");
        Response response = given()
                .baseUri(BASE_URI)
                .body("{\n" +
                        "   \"id\": \"id\",\n" +
                        "   \"text\": \"task#string_id\",\n" +
                        "   \"completed\": false\n" +
                        "}")
                .when()
                .post(TODO);

        log.info("Запрос на получение Status Code");
        assertEquals(UNSUPPORT_TYPE.getCode(), response.getStatusCode(),
                "Actual Status Code = " + response.getStatusCode()
                        + "\nExpect Status Code = " + UNSUPPORT_TYPE.getCode());
    }
}