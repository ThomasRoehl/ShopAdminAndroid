package com.example.thomasroehl.shopadminandroid.register;

/**
 * Created by Thomas Roehl on 04.12.2015.
 */
public interface RegisterController {
    public boolean verifyPassword(String pw, String pwR);
    public String screenFlowMain();
    public String screenFlowLogin();
    public boolean checkUsername(String username);
    public String getFeedbackMessage();
    public boolean createUser(String username, String email, String password);
}
