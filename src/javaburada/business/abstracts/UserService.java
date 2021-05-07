package javaburada.business.abstracts;

import javaburada.entities.concretes.User;

import java.util.List;

public interface UserService {
    void add(User user);
    void update(User user);
    boolean checkUser(String email, String password);
    boolean checkEmail(String email);
    boolean checkEmailChar(String email);
    void login(String email, String password);
    void signUpWithGoogle(String email, String password);
    List<User> getAll();
    void confirmMail(String email);
}
