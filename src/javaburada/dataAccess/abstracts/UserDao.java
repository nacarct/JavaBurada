package javaburada.dataAccess.abstracts;

import javaburada.entities.concretes.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    void update(User user);
    void delete(User user);
    User get();
    List<User> getAll();
}
