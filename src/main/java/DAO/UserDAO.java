package DAO;

import model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO<T> {

    List<T> getAll();
    T getById(int id);
    void delete(int id);
    void add(T t);
    void update(T t);

}
