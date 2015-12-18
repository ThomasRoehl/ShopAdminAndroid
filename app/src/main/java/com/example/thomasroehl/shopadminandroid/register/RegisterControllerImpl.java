package com.example.thomasroehl.shopadminandroid.register;

import android.content.Context;
import android.content.Intent;

import com.example.thomasroehl.shopadminandroid.database.DatabaseController;
import com.example.thomasroehl.shopadminandroid.gui.EditActivity;

import java.util.HashMap;

/**
 * Created by altug on 08.12.15.
 */
public class RegisterControllerImpl implements RegisterControllerInterf {

    private static DatabaseController databaseController = new DatabaseController();
    private static RegisterControllerImpl registerController = null;
    Context currentActivityContext;

    public static RegisterControllerImpl getRegisterController() {
        if (registerController == null) {
            registerController = new RegisterControllerImpl();
        }
        return registerController;
    }

    /**
     * Reference to the activity context
     *
     * @param context Reference on View Context
     */
    public void setCurrentActivityContext(Context context) {
        this.currentActivityContext = context;
    }

    @Override
    public boolean verifyPassword(String pw, String pwR) {
        if (pw.equals(pwR))
            return true;
        else
            return false;
    }

    /**
     * Screen Flow to MainView
     *
     * @return Intent to EditView
     */
    @Override
    public Intent screenFlowMain() {
        Intent i = new Intent(
                this.currentActivityContext,
                EditActivity.class);
        return i;
    }

    @Override
    public String screenFlowLogin() {
        return null;
    }

    @Override
    public boolean checkUsername(String username) {
        //TODO: Hier muss von der Datenbank eine Hashmap aller usernames geholt werden.
        HashMap<Integer,String> namesMap = new HashMap<Integer,String>();
        namesMap.put(0,"Katia");
        namesMap.put(1,"Altug");
        namesMap.put(2,"Julia");

        /*if(databaseController.checkUsername(username) == true)
        {*/
        if (namesMap.containsValue(username)){
            return true;
        }
        return false;
    }

    public boolean checkNameIsEmpty(String name)
    {
        if(name == null || name.equals(""))
            return true;
        else
            return false;
    }

    public boolean checkPasswordIsEmpty(String password)
    {
        if(password == null || password.equals(""))
            return true;
        else
            return false;
    }

    public boolean checkEmailIsEmpty(String email)
    {
        if(email == null || email.equals(""))
            return true;
        else
            return false;
    }

    public boolean checkEmailIsValid(String email)
    {
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return true;
        else
            return false;
    }

    public boolean checkPasswordIsTooShort(String password)
    {
        if( password.length() < 5 )
            return true;
        else
            return false;
    }
    public boolean checkPasswordIsTooLong(String password)
    {
        if( password.length() > 10 )
            return true;
        else
            return false;
    }

    @Override
    public String getFeedbackMessage() {

        return null;
    }

    @Override
    public boolean createUser(String username, String email, String password) {
        return false;
    }

}
