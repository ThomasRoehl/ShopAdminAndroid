package com.example.thomasroehl.shopadminandroid.register;

import java.util.HashMap;

/**
 * Created by altug on 08.12.15.
 */
public class RegisterControllerImpl implements RegisterControllerInterf {


    public RegisterControllerImpl(){

    }

    @Override
    public boolean verifyPassword(String pw, String pwR) {
        if (pw.equals(pwR))
            return true;
        else
            return false;
    }

    @Override
    public void screenFlowMain() {
        //startActivity(new Intent(packageContext,MainActivity.class));
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

        if (namesMap.containsValue(username)){
            return true;
        }
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
