package DAO;

import model.User;

import java.util.List;

public class UsersDao implements DAO<User> {

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public boolean add(User item) {
        return false;
    }

    @Override
    public boolean update(User item) {
        return false;
    }

    @Override
    public boolean delete(User item) {
        return false;
    }
}
