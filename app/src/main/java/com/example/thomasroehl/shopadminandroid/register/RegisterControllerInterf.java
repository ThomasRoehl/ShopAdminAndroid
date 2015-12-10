package com.example.thomasroehl.shopadminandroid.register;

import android.content.Intent;

/**
 * Created by Thomas Roehl on 04.12.2015.
 */
public interface RegisterControllerInterf {
    public boolean verifyPassword(String pw, String pwR);
    //public String screenFlowMain();
    public Intent screenFlowMain();
    public String screenFlowLogin();
    public boolean checkUsername(String username);
    public String getFeedbackMessage();
    public boolean createUser(String username, String email, String password);
}
