package com.example.thomasroehl.shopadminandroid.register;

import android.content.Context;
import android.content.Intent;

import com.example.thomasroehl.shopadminandroid.container.User;

/**
 * Created by Thomas Roehl on 04.12.2015.
 */
public interface RegisterControllerInterf {
    public boolean verifyPassword(String pw, String pwR);
    public Intent screenFlowMain();
    public String screenFlowLogin();
    public boolean checkUsername(String username);
    public String getFeedbackMessage();
    public boolean createUser(User user);
    public boolean checkPasswordIsTooLong(String password);
    public boolean checkPasswordIsTooShort(String password);
    public boolean checkEmailIsValid(String email);
    public boolean checkEmailIsEmpty(String email);
    public boolean checkPasswordIsEmpty(String password);
    public boolean checkNameIsEmpty(String name);
    public void setCurrentActivityContext(Context context);
}
