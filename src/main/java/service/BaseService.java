package service;

/**
 * Base service
 * @param <T>
 */
public interface BaseService <T> {
    /**
     * Persist entity
     * @param entity
     */
    void save (T entity);

    /**
     * Merge entity
     * @param entity
     */
    void update (T entity);

    /**
     * find entity by id
     * @param id
     * @return
     */
    T findById(Long id);
}
