package com.basic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {

    private TaskManager manager;

    @BeforeEach
    void setUp() {
        manager = new TaskManager();
    }

    @Test
    void addTask_withValidDescription_addsTask() {
        manager.addTask("Write unit tests");

        List<Task> tasks = manager.listTasks();

        assertEquals(1, tasks.size());
        Task task = tasks.get(0);
        assertEquals("Write unit tests", task.getDescription());
        assertFalse(task.isDone());
    }

    @Test
    void addTask_withNullDescription_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> manager.addTask(null));
        assertEquals("Task description cannot be null or blank", exception.getMessage());
    }

    @Test
    void addTask_withBlankDescription_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> manager.addTask("   "));
        assertEquals("Task description cannot be null or blank", exception.getMessage());
    }

    @Test
    void listTasks_returnsUnmodifiableList() {
        manager.addTask("Immutable list test");

        List<Task> tasks = manager.listTasks();

        assertThrows(UnsupportedOperationException.class, () -> tasks.add(new Task("Another task")));
    }

    @Test
    void markTaskDone_withExistingDescription_marksFirstTaskDone() {
        manager.addTask("Complete code review");

        boolean result = manager.markTaskDone("Complete code review");

        assertTrue(result);
        assertTrue(manager.listTasks().get(0).isDone());
    }

    @Test
    void markTaskDone_withNullDescription_returnsFalse() {
        assertFalse(manager.markTaskDone(null));
    }

    @Test
    void markTaskDone_withBlankDescription_returnsFalse() {
        assertFalse(manager.markTaskDone("   "));
    }

    @Test
    void markTaskDone_withNonExistingDescription_returnsFalse() {
        manager.addTask("Prepare presentation");

        assertFalse(manager.markTaskDone("Write report"));
    }

    @Test
    void markTaskDone_withDuplicateDescriptions_marksOnlyFirstTask() {
        manager.addTask("Duplicate task");
        manager.addTask("Duplicate task");

        boolean result = manager.markTaskDone("Duplicate task");

        assertTrue(result);
        List<Task> tasks = manager.listTasks();
        assertTrue(tasks.get(0).isDone());
        assertFalse(tasks.get(1).isDone());
    }

    @Test
    void removeTask_withExistingDescription_removesTask() {
        manager.addTask("Task to remove");

        manager.removeTask("Task to remove");

        assertTrue(manager.listTasks().isEmpty());
    }

    @Test
    void removeTask_withDuplicateDescriptions_removesAllMatchingTasks() {
        manager.addTask("Duplicate task");
        manager.addTask("Duplicate task");
        manager.addTask("Keep task");

        manager.removeTask("Duplicate task");

        List<Task> tasks = manager.listTasks();
        assertEquals(1, tasks.size());
        assertEquals("Keep task", tasks.get(0).getDescription());
    }

    @Test
    void taskConstructor_withNullDescription_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Task(null));
        assertEquals("Task description cannot be null or blank", exception.getMessage());
    }

    @Test
    void taskConstructor_withBlankDescription_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Task("   "));
        assertEquals("Task description cannot be null or blank", exception.getMessage());
    }

    @Test
    void testRemoveTask_withNullDescription_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> manager.removeTask(null));
        assertEquals("Task description cannot be null or blank", exception.getMessage());
    }

    @Test
    void testRemoveTask_withBlankDescription_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> manager.removeTask("   "));
        assertEquals("Task description cannot be null or blank", exception.getMessage());
    }

}