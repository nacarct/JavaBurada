package javaburada.core.jLogger.concretes;

import javaburada.core.jLogger.abstracts.JLoggerService;

public class JLoggerManager implements JLoggerService {
    @Override
    public void log(String message) {
        System.out.println("LOG : " + message);
    }
}
