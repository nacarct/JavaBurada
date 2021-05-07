package javaburada.business.concretes;

import javaburada.business.abstracts.UserService;
import javaburada.core.jLogger.abstracts.JLoggerService;
import javaburada.core.jMail.abstracts.MailService;
import javaburada.core.otherAuth.GoogleAuthService;
import javaburada.dataAccess.abstracts.UserDao;
import javaburada.entities.concretes.User;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserManager implements UserService {
    private UserDao userDao;
    private JLoggerService jLoggerService;
    private MailService mailService;
    private GoogleAuthService googleAuthService;

    public UserManager(UserDao userDao, JLoggerService jLoggerService,MailService mailService, GoogleAuthService googleAuthService) {
        this.userDao = userDao;
        this.jLoggerService = jLoggerService;
        this.mailService=mailService;
        this.googleAuthService=googleAuthService;
    }

    @Override
    public void add(User user) {
        String message = "";

        if (!checkEmail(user.getEmail()) && checkEmailChar(user.getEmail()) && user.getPassword().length()>5 && user.getFirstName().length()>1 && user.getLastName().length()>1){
            user.setEmailActive(false);
            userDao.add(user);
            mailService.sendEmail(user.getEmail(),"Lütfen mail adresinizi doğrulayınız.");
            message="Kayıt başarılı.";
            jLoggerService.log("Kullanıcı eklendi : " + user.getFullName());

        }
        else if (checkEmail(user.getEmail())){
            message= user.getEmail()+ " : Email sisteme kayıtlı. Kayıt başarısız. ";
        }
        else if (!checkEmailChar(user.getEmail())){
            message= user.getEmail() + " : Email formatı yanlış.";
        }
        else if (user.getPassword().length()<6){
            message= user.getPassword() + " : Parola en az 6 karakterden oluşmalıdır.";
        }
        else if(user.getFirstName().length()<2){
            message= user.getFirstName() + " : Adınız en az 2 karakterden oluşmalıdır.";
        }
        else if (user.getLastName().length()<2){
            message= user.getLastName()+ " : Soyadınız en az 2 karakterden oluşmalıdır.";
        }

        System.out.println(message);
    }

    @Override
    public boolean checkEmail(String email) {
        for (User user : getAll()){
            if (email.equals(user.getEmail())){
                return true;
            }
            else
                return false;
        }

        return false;
    }

    @Override
    public boolean checkEmailChar(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return  matcher.matches();
    }

    @Override
    public void update(User user) {
        userDao.update(user);
        jLoggerService.log("Kullanıcı güncellendi : " + user.getFullName());
    }

    @Override
    public boolean checkUser(String email, String password) {
        boolean result = false;

        for (User user : getAll()){
            if (email.equals(user.getEmail()) && password.equals(user.getPassword()) && user.isEmailActive())
                result=true;
            else
                result=false;
        }

        return result;
    }

    @Override
    public void login(String email, String password) {
        String message;
        if (checkUser(email,password)){
            message = "Giriş Başarılı";
            jLoggerService.log("Kullanıcı girişi başarılı : " + email);
        }
        else{
            message = "Giriş Başarısız";
            jLoggerService.log("Kullanıcı girişi başarısız : " + email);
        }

        System.out.println(message);

    }

    @Override
    public void signUpWithGoogle(String email,String password){
        googleAuthService.signUp(email,password);
        User user = new User(99,"Yusuf","Hekim",email,password);
        user.setEmailActive(true);
        add(user);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void confirmMail(String email) {
        for (User user : getAll()){
            if (email.equals(user.getEmail())){
                user.setEmailActive(true);
                System.out.println(email + " adresiniz doğrulandı");
            }
        }
    }
}
