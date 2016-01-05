package com.example.thomasroehl.shopadminandroid.login;

import android.content.Context;
import android.content.Intent;

import com.example.thomasroehl.shopadminandroid.gui.MainActivity;

/**
 * Created by Katia on 05.01.2016.
 */
public class LoginControllerImpl implements LoginControllerInterf{

    Context currentActivityContext;

    @Override
    public boolean checkUserinput() {

        return false;
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

    @Override
    public String screenFlowRegister() {
        return null;
    }

    @Override
    public String getFeedbackMessage() {
        return null;
    }
}