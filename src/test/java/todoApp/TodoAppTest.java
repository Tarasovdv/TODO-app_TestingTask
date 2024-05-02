package todoApp;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.parallel.Isolated;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.buttonone.todoApp.api.TaskService;
import ru.buttonone.todoApp.data.Task;

@Isolated
public class TodoAppTest {
    private final TaskService taskService = new TaskService();

    @DisplayName("Добавление новой задачи")
    @Description("TC-1 Отправка запроса на добавление новой задачи")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#addNewTaskTestData")
    public void addNewTask(Task newTask, long taskId) {
        taskService
                .addNewTask(newTask, taskId)
                .cleanTaskData(taskId);
    }

    @DisplayName("Предоставление списка всех задач")
    @Description("TC-2 Отправка запроса на предоставление всех задач")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#addNewTaskTestData")
    public void getAllTasks(Task newTask, long taskId) {
        taskService
                .addNewTask(newTask, taskId)
                .getAllTasks()
                .cleanTaskData(taskId);
    }

    @Description("TC-3 Отправка запроса на частичное изменение данных (text) задачи")
    @DisplayName("Изменение text задачи")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#updateTextTaskTestData")
    public void updateTextTask(Task newTask, Task updateTask, long taskId) {
        taskService
                .addNewTask(newTask, taskId)
                .getAllTasks()
                .updateTask(updateTask, taskId)
                .cleanTaskData(taskId);
    }

    @Description("TC-4 Отправка запроса на удаление задачи")
    @DisplayName("Удаление задачи")
    @ParameterizedTest
    @MethodSource("todoApp.TestData#addNewTaskTestData")
    public void deleteTask(Task newTask, long taskId) {
        taskService
                .addNewTask(newTask, taskId)
                .deleteTaskById(taskId);
    }
}