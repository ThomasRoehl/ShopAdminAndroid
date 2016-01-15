package com.example.thomasroehl.shopadminandroid.login;

import android.content.Intent;

/**
 * Created by Thomas Roehl on 04.12.2015.
 */
public interface LoginControllerInterf {

    public boolean checkUserinput();
    public Intent screenFlowMain();
    public String screenFlowRegister();
    public String getFeedbackMessage();
    public boolean checkPasswordByName(String password, String username);
    public boolean checkUsername(String username);
}
