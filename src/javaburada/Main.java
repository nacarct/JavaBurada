package javaburada;

import javaburada.business.abstracts.UserService;
import javaburada.business.concretes.UserManager;
import javaburada.core.jLogger.concretes.JLoggerManager;
import javaburada.core.jMail.concretes.MailManager;
import javaburada.core.otherAuth.GoogleAuthAdapter;
import javaburada.dataAccess.concretes.HibernateUserDao;
import javaburada.entities.concretes.User;

import java.util.List;

public class Main {

    public static void main(String[] args) {


        UserService userService = new UserManager(new HibernateUserDao(),new JLoggerManager(),new MailManager(), new GoogleAuthAdapter());
        List<User> userList = userService.getAll();
        for (User user: userList){
            System.out.println(user.getFullName());
        }

        User user1 = new User(1,"Temuçin","Nacar","nacarct@gmail.com","123456");
        User user2 = new User(2,"Temuçin","Nacar","nacarct@gmail.com","123456");
        User user3 = new User(3,"Emre","Tatar","emre@gmail.com","12345");
        User user4 = new User(4,"Mehmet","Şığva","mehmet.gmail.com","123456");
        User user5 = new User(5,"S","Can","selim@gmail.com","123456");
        User user6 = new User(6,"Selim","C","selim@gmail.com","123456");

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);
        userService.add(user5);
        userService.add(user6);

        userService.confirmMail(user1.getEmail());

        userService.login(user1.getEmail(), user1.getPassword());
        userService.login(user3.getEmail(), user3.getPassword());

        userService.signUpWithGoogle("yusuf@gmail.com","123456");
        userService.confirmMail("yusuf@gmail.com");

        userService.login("yusuf@gmail.com", "123456");

    }
}
