package DAO;

import java.util.List;

/**
 *
 */
public interface DAO<T> {

    public List<T> getAll();
    public T getById(int id);
    public boolean add(T item);
    public boolean update(T item);
    public boolean delete(T item);
}
