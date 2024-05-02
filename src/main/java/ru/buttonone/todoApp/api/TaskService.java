package ru.buttonone.todoApp.api;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.extern.slf4j.Slf4j;
import ru.buttonone.todoApp.data.Task;
import ru.buttonone.todoApp.spec.Spec;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static ru.buttonone.todoApp.constants.Endpoints.TODO;
import static ru.buttonone.todoApp.constants.Endpoints.TODO_BY_ID;
import static ru.buttonone.todoApp.constants.TestValue.PASSWORD;
import static ru.buttonone.todoApp.constants.TestValue.USERNAME;

@Slf4j
public class TaskService {
    public static final RequestSpecification REQUEST_SPEC = Spec.requestSpec();
    public static final ResponseSpecification RESPONSE_SPEC = Spec.responseSpec();

    @Step("Отправка запроса на добавление задачи")
    public TaskService addNewTask(Task newTask, long taskId) {
        log.info("Добавление новой задачи с ID = " + taskId);
        given()
                .spec(REQUEST_SPEC)
                .contentType(JSON)
                .body(newTask)
                .when()
                .post(TODO)
                .then()
                .statusCode(201);

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
                .statusCode(200);

        return this;
    }

    @Step("Отправка запроса на внесение изменений в задачу")
    public TaskService updateTask(Task updateData, long taskId) {
        log.info("Отправка запроса на внесение изменений в задачу");
        given()
                .spec(REQUEST_SPEC)
                .pathParam("id", taskId)
                .contentType(JSON)
                .body(updateData)
                .when()
                .put(TODO_BY_ID)
                .then()
                .spec(RESPONSE_SPEC)
                .statusCode(200);

        return this;
    }

    @Step("Отправка запроса на удаление задачи по id = {taskId}")
    public void deleteTaskById(long taskId) {
        log.info("Удаление задачи по ID = " + taskId);
        if (true) { //todo добавить проверку наличия задачи в БД
            given()
                    .auth().preemptive().basic(USERNAME, PASSWORD)
                    .spec(REQUEST_SPEC)
                    .pathParam("id", taskId)
                    .when()
                    .delete(TODO_BY_ID)
                    .then()
                    .spec(RESPONSE_SPEC)
                    .statusCode(204);

        } else {
            log.error("Задачи НЕ существует -> ID = " + taskId);
            throw new RuntimeException("Задачи с ID = " + taskId + " НЕ существует");
        }
    }

    @Step("Отправка запроса на удаление задачи по id = {taskId}")
    public void cleanTaskData(long taskId) {
        log.info("CLEAN_TASK_DATA by ID = " + taskId);
        given()
                .auth().preemptive().basic(USERNAME, PASSWORD)
                .spec(REQUEST_SPEC)
                .pathParam("id", taskId)
                .when()
                .delete(TODO_BY_ID)
                .then()
                .spec(RESPONSE_SPEC)
                .statusCode(204);
        log.info("CLEAN_TASK_DATA by ID = " + taskId + " -> SUCCESS");
    }
}