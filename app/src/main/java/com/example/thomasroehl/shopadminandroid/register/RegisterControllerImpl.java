package com.example.thomasroehl.shopadminandroid.register;

import android.content.Context;
import android.content.Intent;

import com.example.thomasroehl.shopadminandroid.container.User;
import com.example.thomasroehl.shopadminandroid.database.DatabaseController;
import com.example.thomasroehl.shopadminandroid.gui.MainActivity;
import com.example.thomasroehl.shopadminandroid.statics.StorageAdmin;

import java.util.HashMap;

/**
 * Created by altug on 08.12.15.
 */
public class RegisterControllerImpl implements RegisterControllerInterf {
    private static RegisterControllerImpl registerController = null;
    Context currentActivityContext;

    /**
     * Returns the created instance of the controller
     *
     * @return registerController instance
     */
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

    /**
     * the method check the equality of two passwords
     *
     * @param pw
     * @param pwR
     * @return true whether two passwords match
     */
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
     * @return Intent to MainView
     */
    @Override
    public Intent screenFlowMain() {
        Intent i = new Intent(
                this.currentActivityContext,
                MainActivity.class);
        return i;
    }

    /**
     *
     *
     * @return
     */
    @Override
    public String screenFlowLogin() {
        return null;
    }

    /**
     * check username from database
     *
     * @param username
     * @return true whether username exists in database
     */
    @Override
    public boolean checkUsername(String username) {

        if(StorageAdmin.DBCONTROLLER.checkUsername(username) == true){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * check whether username input isn't empty
     *
     * @param name
     * @return true whether username input isn't empty
     */
    @Override
    public boolean checkNameIsEmpty(String name){
        if(name == null || name.equals(""))
            return true;
        else
            return false;
    }

    /**
     * check whether password input isn't empty
     *
     * @param password
     * @return true whether username input isn't empty
     */
    @Override
    public boolean checkPasswordIsEmpty(String password){
        if(password == null || password.equals(""))
            return true;
        else
            return false;
    }

    /**
     * check whether email input isn't empty
     *
     * @param email
     * @return true whether email input isn't empty
     */
    @Override
    public boolean checkEmailIsEmpty(String email){
        if(email == null || email.equals(""))
            return true;
        else
            return false;
    }

    /**
     * check whether email input is valid
     *
     * @param email
     * @return true whether email input is valid
     */
    @Override
    public boolean checkEmailIsValid(String email){
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return true;
        else
            return false;
    }

    /**
     * check length of password input
     *
     * @param password
     * @return true whether length of password is more than four characters
     */
    @Override
    public boolean checkPasswordIsTooShort(String password){
        if( password.length() < 4)
            return true;
        else
            return false;
    }

    /**
     * check length of password input
     *
     * @param password
     * @return true whether length of password is less than ten characters
     */
    @Override
    public boolean checkPasswordIsTooLong(String password){
        if( password.length() > 10 )
            return true;
        else
            return false;
    }

    /**
     *
     * @return
     */
    @Override
    public String getFeedbackMessage() {
        return null;
    }

    /**
     * creates new user
     *
     * @param user
     * @return true whether a new user is created
     */
    @Override
    public boolean createUser(User user) {
        if(StorageAdmin.DBCONTROLLER.createUser(user)) {
            System.out.println("user wird in DB gespeichert --------> " + true);
            return true;
        }
        System.out.println("user wird nicht in DB gespeichert --------> " + false);
        return false;
    }
}
