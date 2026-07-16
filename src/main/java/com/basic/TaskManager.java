package com.basic;
import java.util.ArrayList;
import java.util.List;


public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();

    private static void validateDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Task description cannot be null or blank");
        }
    }

    /**
     * Adds a new task with the given description.
     *
     * @param description the task description, must not be null or blank
     * @throws IllegalArgumentException if the description is null or blank
     */
    public void addTask(String description) {
        validateDescription(description);
        tasks.add(new Task(description));
    }

    /**
     * Returns an unmodifiable snapshot of the current tasks.
     *
     * @return the list of tasks
     */
    public List<Task> listTasks() {
        return List.copyOf(tasks);
    }

    /**
     * Marks the first task with the given description as done.
     *
     * @param description the task description to mark as done
     * @return true if a matching task was found and updated, false otherwise
     */
    public boolean markTaskDone(String description) {
        if (description == null || description.isBlank()) {
            return false;
        }
        for (Task task : tasks) {
            if (description.equals(task.getDescription())) {
                task.markDone();
                return true;
            }
        }
        return false;
    }

    /**
     * Removes all tasks with the given description.
     *
     * @param description the task description to remove
     * @throws IllegalArgumentException if the description is null or blank
     */
    public void removeTask(String description) {
        validateDescription(description);
        tasks.removeIf(task -> description.equals(task.getDescription()));
    }
}
