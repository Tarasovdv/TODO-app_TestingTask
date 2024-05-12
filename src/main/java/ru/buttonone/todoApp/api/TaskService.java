package ru.buttonone.todoApp.api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.extern.slf4j.Slf4j;
import ru.buttonone.todoApp.data.Task;
import ru.buttonone.todoApp.spec.Spec;

import java.io.File;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.buttonone.todoApp.constants.Endpoints.TODO;
import static ru.buttonone.todoApp.constants.Endpoints.TODO_BY_ID;
import static ru.buttonone.todoApp.constants.HttpStatusCode.*;
import static ru.buttonone.todoApp.constants.TestValue.PASSWORD;
import static ru.buttonone.todoApp.constants.TestValue.USERNAME;
import static ru.buttonone.todoApp.spec.Spec.BASE_URI;

@Slf4j
public class TaskService {
    public static final RequestSpecification REQUEST_SPEC = Spec.requestSpec();
    public static final ResponseSpecification RESPONSE_SPEC = Spec.responseSpec();

    public boolean checkTaskExistById(Task newTask) {
        log.info("Проверка существования задачи c ID = " + newTask.getId());
        for (Task task : getListAllTasks()) {
            if (Objects.equals(task.getId(), newTask.getId())) {
                log.info("Задача c ID = " + newTask.getId() + " -> СУЩЕСТВУЕТ");
                return true;
            }
        }
        log.info("Задачи c ID = " + newTask.getId() + " -> НЕ СУЩЕСТВУЕТ");

        return false;
    }


    @Step("Отправка запроса на добавление задачи")
    public TaskService addNewTask(Task newTask) {
        log.info("Добавление новой задачи с ID = " + newTask.getId());
        if (checkTaskExistById(newTask)) {
            log.error("Задача уже существует -> ID = " + newTask.getId());
            throw new RuntimeException("Задача с ID = " + newTask.getId() + " уже существует");
        }

        given()
                .spec(REQUEST_SPEC)
                .contentType(JSON)
                .body(newTask)
                .when()
                .post(TODO)
                .then()
                .spec(RESPONSE_SPEC)
                .statusCode(SUCCESS_CREATE.getCode());

        log.info("Добавление новой задачи с ID = " + newTask.getId() + "-> SUCCESS");

        return this;
    }

    @Step("Отправка запроса на получение всех задач")
    public TaskService getAllTasks() {
        log.info("Предоставление всех задач");
        given()
                .spec(REQUEST_SPEC)
                .when()
                .get(TODO)
                .then()
                .spec(RESPONSE_SPEC)
                .statusCode(SUCCESS_REQ.getCode());

        return this;
    }

    public List<Task> getListAllTasks() {
        Response response = given()
                .baseUri(BASE_URI)
                .when()
                .get(TODO);

        return List.of(response.getBody().as(Task[].class));
    }

    @Step("Отправка запроса на получение {limit} задач")
    public List<Task> getLimitTasks(BigInteger limit) {
        log.info("Предоставление первых " + limit + " задач");
        Response response = given()
                .spec(REQUEST_SPEC)
                .queryParam("limit", limit)
                .when()
                .get(TODO);

        response.then()
                .spec(RESPONSE_SPEC);

        return List.of(response.getBody().as(Task[].class));
    }

    @Step("Отправка запроса на получение Status Code при добавлении задачи")
    public int getStatusCodeAddNewTask(Task newTask) {
        log.info("Запрос на добавление задачи");
        Response response = given()
                .baseUri(BASE_URI)
                .body(newTask)
                .when()
                .post(TODO);

        log.info("Запрос на получение Status Code");
        return response.getStatusCode();
    }

    @Step("Отправка запроса на получение Status Code использую фильтр limit = {limit}")
    public int getStatusLimitTasks(BigInteger limit) {
        log.info("Запрос на получение Status Code");
        Response response = given()
                .baseUri(BASE_URI)
                .queryParam("limit", limit)
                .when()
                .get(TODO);

        return response.getStatusCode();
    }

    @Step("Отправка запроса на получение Status Code использую фильтр offset = {offset}")
    public int getStatusCodeOffsetTasks(BigInteger offset) {
        log.info("Запрос на получение Status Code");
        Response response = given()
                .baseUri(BASE_URI)
                .queryParam("offset", offset)
                .when()
                .get(TODO);

        return response.getStatusCode();
    }

    @Step("Отправка запроса на получение Status Code использую фильтр limit = {limit}. offset = {offset}")
    public int getStatusCodeLimitAndOffsetTasks(BigInteger limit, BigInteger offset) {
        log.info("Запрос на получение Status Code");
        Response response = given()
                .baseUri(BASE_URI)
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .when()
                .get(TODO);

        return response.getStatusCode();
    }

    @Step("Отправка запроса на получение задач, исключив {offset}")
    public List<Task> getOffsetTasks(BigInteger offset) {
        log.info("Предоставление задач, исключив: " + offset);
        Response response = given()
                .spec(REQUEST_SPEC)
                .queryParam("offset", offset)
                .when()
                .get(TODO);
        response.then()
                .spec(RESPONSE_SPEC);

        return List.of(response.getBody().as(Task[].class));
    }

    @Step("Отправка запроса на получение {limit} задач, исключив {offset}")
    public List<Task> getLimitAndOffsetTasks(BigInteger limit, BigInteger offset) {
        log.info("Предоставление " + limit + " задач, исключив: " + offset);
        Response response = given()
                .spec(REQUEST_SPEC)
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .when()
                .get(TODO);

        response.then()
                .spec(RESPONSE_SPEC);

        return List.of(response.getBody().as(Task[].class));
    }


    @Step("Отправка запроса на получение Status Code при изменении задачи")
    public int getStatusCodeUpdateTasks(Task task, Task updateTask) {
        log.info("Запрос на получение Status Code");
        Response response = given()
                .spec(REQUEST_SPEC)
                .pathParam("id", task.getId())
                .contentType(JSON)
                .body(updateTask)
                .when()
                .put(TODO_BY_ID);

        return response.getStatusCode();
    }

    @Step("Отправка запроса на внесение изменений в задачу")
    public TaskService updateTask(Task task, Task updateTask) {
        log.info("Отправка запроса на внесение изменений в задачу");
        given()
                .spec(REQUEST_SPEC)
                .pathParam("id", task.getId())
                .contentType(JSON)
                .body(updateTask)
                .when()
                .put(TODO_BY_ID)
                .then()
                .spec(RESPONSE_SPEC)
                .statusCode(SUCCESS_REQ.getCode());

        return this;
    }

    @Step("Отправка запроса на удаление задачи по id = {taskId}")
    public void deleteTaskById(Task task) {
        log.info("Удаление задачи по ID = " + task.getId());
        if (checkTaskExistById(task)) {
            given()
                    .baseUri(BASE_URI)
                    .auth().preemptive().basic(USERNAME, PASSWORD)
                    .log().method()
                    .pathParam("id", task.getId())
                    .when()
                    .delete(TODO_BY_ID)
                    .then()
                    .spec(RESPONSE_SPEC)
                    .statusCode(SUCCESS_DELETE.getCode());

            if (!checkTaskExistById(task)) log.info("Удаление задачи по ID = " + task.getId() + " -> SUCCESS");

        } else {
            log.error("Задачи НЕ существует -> ID = " + task.getId());
            throw new RuntimeException("Задачи с ID = " + task.getId() + " НЕ существует");
        }
    }

    @Step("Отправка запроса на удаление задачи по id = {taskId}")
    public TaskService cleanTaskData(Task task) {
        log.info("CLEAN_TASK_DATA by ID = " + task.getId());
        given()
                .baseUri(BASE_URI)
                .auth().preemptive().basic(USERNAME, PASSWORD)
                .log().method()
                .pathParam("id", task.getId())
                .when()
                .delete(TODO_BY_ID)
                .then()
                .statusCode(SUCCESS_DELETE.getCode());
        log.info("CLEAN_TASK_DATA by ID = " + task.getId() + " -> SUCCESS");

        return this;
    }

    @Step("Проверка данных задачи")
    public TaskService checkTaskData(Task expectTask) {
        log.info("Проверка данных задачи с ID = " + expectTask.getId());
        List<Task> taskList = getListAllTasks();
        Task actualTask = taskList.get(taskList.indexOf(expectTask));

        assertAll(
                () -> assertEquals(expectTask.getId(), actualTask.getId(),
                        "Actual TASK ID = " + actualTask.getId()
                                + "\nExpect TASK ID = " + expectTask.getId()),
                () -> assertEquals(expectTask.getText(), actualTask.getText(),
                        "Actual TASK TEXT = " + actualTask.getText() +
                                "\nExpect TASK TEXT = " + expectTask.getText()),
                () -> assertEquals(expectTask.getCompleted(), actualTask.getCompleted(),
                        "Actual TASK STATUS = " + actualTask.getCompleted() +
                                "\nExpect TASK STATUS = " + expectTask.getCompleted()));

        log.info("Проверка данных задачи с ID = " + expectTask.getId() + "-> SUCCESS");

        return this;
    }

    public TaskService addAllTasks(List<Task> taskList) {
        for (Task task : taskList) {
            addNewTask(task);
        }
        return this;
    }

    public boolean checkAllTasks(List<Task> taskList) {
        if (taskList.isEmpty()) {
            return true;
        } else {
            for (Task task : taskList) {
                checkTaskData(task);
            }
        }
        return false;
    }


    public TaskService clearAllTasks(List<Task> taskList) {
        for (Task task : taskList) {
            cleanTaskData(task);
        }
        return this;
    }

    @Step("Проверка структуры данных о задаче id = {petId}")
    public TaskService checkJsonScheme(Task task, String filePath) {
        log.info("Отправка запроса для Проверки структуры данных о задаче ID = " + task.getId());
        given()
                .spec(REQUEST_SPEC)
                .when()
                .get(TODO)
                .then()
                .spec(RESPONSE_SPEC)
                .statusCode(SUCCESS_REQ.getCode())
                .contentType(ContentType.JSON)
                .assertThat()
                .body(matchesJsonSchema(new File(filePath)));

        log.info("Проверка структуры данных о задаче ID" + task.getId() + " -> SUCCESS");

        return this;
    }
}