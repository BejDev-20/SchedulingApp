package DAO;

import javafx.collections.ObservableList;

/**
 *
 */
public interface DAO<T> {

    public ObservableList<T> getAll();
    public T getById(int id);
    public boolean add(T item);
    public boolean update(T item);
    public boolean delete(T item);
}
