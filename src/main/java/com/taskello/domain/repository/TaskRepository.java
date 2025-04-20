package com.taskello.domain.repository;

import com.taskello.domain.exception.TaskNotFoundException;
import com.taskello.domain.model.Task;

import java.util.List;

/**
 * Repository interface for accessing and manipulating {@link Task} aggregates in the domain layer.
 *
 * <p>
 * This interface abstracts the persistence mechanism, allowing the domain to remain decoupled from
 * any specific database, ORM framework, or implementation detail. Concrete implementations may use
 * technologies such as JPA, JDBC, or in-memory storage.
 * </p>
 *
 * <p>
 * All input and output types are domain models. Internally, implementations may perform conversions
 * to and from persistence models (e.g., JPA entities).
 * </p>
 *
 * <p>
 * Returned domain objects are <strong>detached from the persistence context</strong>. They are
 * fully mapped representations of the underlying data and are not managed by any ORM or session.
 * Modifications to these objects will not be automatically persisted.
 * </p>
 */
public interface TaskRepository {

    /**
     * Retrieves all tasks from the persistence store.
     *
     * @return a list of all tasks; never {@code null}, but may be empty
     */
    List<Task> findAll();

    /**
     * Saves the given task to the persistence store.
     * <p>
     * If the task has an ID, it is treated as an update; otherwise, it is treated as a new creation.
     * During persistence, certain fields may be normalized — for example, leading/trailing whitespace may be trimmed,
     * and empty strings may be converted to {@code null}.
     * </p>
     *
     * @param task the task to save; must not be {@code null}
     * @return the persisted task
     * @throws TaskNotFoundException if no {@link Task} exists with the given ID during an update
     */
    Task save(final Task task);

    /**
     * Retrieves a task by its ID.
     *
     * @param taskId the unique identifier of the task to retrieve; must not be {@code null}
     * @return the task associated with the given ID
     * @throws TaskNotFoundException if no {@link Task} exists with the given ID
     */
    Task findById(final Long taskId);

    /**
     * Deletes the task with the given ID from the persistence store.
     *
     * @param taskId the unique identifier of the task to delete; must not be {@code null}
     * @throws TaskNotFoundException if no task exists with the given ID
     */
    void deleteById(final Long taskId);
}
