package DAO;

import model.Appointment;

import java.util.List;

public class AppointmentDao implements DAO<Appointment>{

    @Override
    public List<Appointment> getAll() {
        return null;
    }

    @Override
    public Appointment getById(int id) {
        return null;
    }

    @Override
    public boolean add(Appointment item) {
        return false;
    }

    @Override
    public boolean update(Appointment item) {
        return false;
    }

    @Override
    public boolean delete(Appointment item) {
        return false;
    }
}
