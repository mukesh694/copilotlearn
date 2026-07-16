package com.basic;

import java.util.Objects;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    private final String description;
    private boolean done;

    /**
     * Constructs a task with the given description.
     *
     * @param description the task description, must not be null or blank
     * @throws IllegalArgumentException if the description is null or blank
     */
    public Task(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Task description cannot be null or blank");
        }
        this.description = description;
        this.done = false;
    }

    /**
     * Returns the task description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns true if the task is completed.
     *
     * @return true if done, false otherwise
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param done the new completion status
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Marks the task as complete.
     */
    public void markDone() {
        this.done = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", done=" + done +
                '}';
    }
}
