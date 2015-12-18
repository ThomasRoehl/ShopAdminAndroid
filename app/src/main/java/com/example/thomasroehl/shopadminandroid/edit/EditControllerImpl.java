package com.example.thomasroehl.shopadminandroid.edit;

import android.content.Context;
import android.content.Intent;

import com.example.thomasroehl.shopadminandroid.gui.CameraActivity;

import java.sql.Date;

/**
 * Created by altug on 17.12.15.
 */
public class EditControllerImpl implements EditControllerInterf {

    Context currentActivityContext;
    private static EditControllerImpl instance = null;

    protected EditControllerImpl(){
        this.currentActivityContext = null;

    }

    /**
     * Returns the created instance of the controller
     *
     * @return Controller instance
     */
    public static EditControllerImpl getInstance() {
        if (instance == null) {
            instance = new EditControllerImpl();
        }
        return instance;
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
    public boolean saveData(String shop, String category, double sum, Date date) {
        return false;
    }

    @Override
    public Intent screenFlowCamera() {
        Intent i = new Intent(
                this.currentActivityContext,
                CameraActivity.class);

        return i;
    }

    @Override
    public String screenFlowMain() {
        return null;
    }
}
