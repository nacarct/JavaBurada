package javaburada.core.jMail.concretes;

import javaburada.core.jMail.abstracts.MailService;

public class MailManager implements MailService {
    @Override
    public void sendEmail(String email, String message) {
        System.out.println("To : " + email + "\nMessage : " + message);
    }
}
