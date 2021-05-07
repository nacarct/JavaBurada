package javaburada.core.otherAuth;

import javaburada.googleAuth.GoogleAuthManager;

public class GoogleAuthAdapter implements GoogleAuthService{
    @Override
    public void signUp(String email, String password) {
        GoogleAuthManager googleAuthManager = new GoogleAuthManager();
        googleAuthManager.googleSignUp(email,password);
    }
}
