package todoApp;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.buttonone.todoApp.data.Task;

import java.util.List;

import static io.restassured.RestAssured.given;
import static ru.buttonone.todoApp.constants.Endpoints.TODO;
import static ru.buttonone.todoApp.constants.Endpoints.TODO_BY_ID;
import static ru.buttonone.todoApp.constants.Headers.BASIC_AUTHORIZATION;
import static ru.buttonone.todoApp.constants.HttpStatusCode.SUCCESS_DELETE;
import static ru.buttonone.todoApp.spec.Spec.BASE_URI;

@Slf4j
public class BaseTest {

    @BeforeEach
    public void cleanDataBeforeTest() {
        log.info("CLEAN_DATA_BEFORE_TEST");
        List<Task> taskList = getListAllTasks();
        if (!taskList.isEmpty()) {
            cleanAllTasks(taskList);
            log.info("CLEAN_DATA_BEFORE_TEST --> SUCCESS");
        }
        log.info("DATA_IS_CLEAN");
    }

    @AfterEach
    public void cleanDataAfterTest() {
        log.info("CLEAN_DATA_AFTER_TEST");
        List<Task> taskList = getListAllTasks();
        if (!taskList.isEmpty()) {
            cleanAllTasks(taskList);
            log.info("CLEAN_DATA_AFTER_TEST --> SUCCESS");
        }
        log.info("DATA_IS_CLEAN");
    }

    private List<Task> getListAllTasks() {
        Response response = given()
                .baseUri(BASE_URI)
                .when()
                .get(TODO);

        return List.of(response.getBody().as(Task[].class));
    }

    private void cleanAllTasks(List<Task> taskList) {
        taskList.forEach(this::cleanTaskData);
    }

    private void cleanTaskData(Task task) {
        log.info("CLEAN_TASK_DATA by ID = " + task.getId());
        given()
                .baseUri(BASE_URI)
                .header(BASIC_AUTHORIZATION)
                .log().method()
                .pathParam("id", task.getId())
                .when()
                .delete(TODO_BY_ID)
                .then()
                .statusCode(SUCCESS_DELETE.getCode());
        log.info("CLEAN_TASK_DATA by ID = " + task.getId() + " -> SUCCESS");
    }
}