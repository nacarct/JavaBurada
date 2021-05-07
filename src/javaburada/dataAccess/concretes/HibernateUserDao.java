package javaburada.dataAccess.concretes;

import javaburada.dataAccess.abstracts.UserDao;
import javaburada.entities.concretes.User;

import java.util.ArrayList;
import java.util.List;

public class HibernateUserDao implements UserDao {

    List<User> userList = new ArrayList<User>();

    @Override
    public void add(User user) {
        userList.add(user);
        System.out.println("Kullanıcı eklendi : " + user.getFullName());
    }

    @Override
    public void update(User user) {

        for (User oUser:userList){
            if (oUser.getId()==user.getId()){
                oUser.setEmail(user.getEmail());
                oUser.setPassword(user.getPassword());
                oUser.setFirstName(user.getFirstName());
                oUser.setLastName(user.getLastName());
            }
        }

        System.out.println("Kullanıcı güncellendi : " + user.getFullName());
    }

    @Override
    public void delete(User user) {
        userList.remove(user);
        System.out.println("Kullanıcı silindi : " + user.getFullName());
    }

    @Override
    public User get() {
        return null;
    }

    @Override
    public List<User> getAll() {
        return userList;
    }
}
