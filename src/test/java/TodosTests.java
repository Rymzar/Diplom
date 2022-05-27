import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class TodosTests {
    Todos sut;

    @BeforeEach
    public void init() {
        sut = new Todos();
    }

    @Test
    public void testTodos_add() {
        String task1 = "Пойти в качалку";
        String task2 = "Выполнить диплом";
        String expected = "Выполнить диплом Пойти в качалку";

        sut.addTask(task1);
        sut.addTask(task2);
        String result = sut.getAllTasks();

        assertThat(result, equalTo(expected));
    }

    @Test
    public void testTodos_remove() {
        String task1 = "Пойти в качалку";
        String task2 = "Выполнить диплом";
        String expected = "Выполнить диплом";

        sut.addTask(task1);
        sut.addTask(task2);
        sut.removeTask("Пойти в качалку");
        String result = sut.getAllTasks();

        assertThat(result, equalTo(expected));
    }

    @Test
    public void testTodos_getAllTasks() {
        String task1 = "Велопробег";
        String task2 = "Бег";
        String task3 = "Хотьба";
        String expected = "Бег Велопробег Хотьба";

        sut.addTask(task1);
        sut.addTask(task2);
        sut.addTask(task3);
        String result = sut.getAllTasks();

        assertThat(result, equalTo(expected));
    }
}
