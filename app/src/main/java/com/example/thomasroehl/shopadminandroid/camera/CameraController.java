package com.example.thomasroehl.shopadminandroid.camera;

import android.content.Context;
import android.content.Intent;

import com.example.thomasroehl.shopadminandroid.gui.CameraActivity;
import com.example.thomasroehl.shopadminandroid.gui.EditActivity;

import java.sql.Date;

/**
 * Created by SZC on 10.12.2015.
 */
public class CameraController implements CameraControllerInterf {
    Context currentActivityContext;

    /**
     * Reference to the activity context
     *
     * @param context Reference on View Context
     */
    public void setCurrentActivityContext(Context context) {
        this.currentActivityContext = context;
    }

    @Override
    public Intent screenFlowEdit() {
        Intent i = new Intent(
                this.currentActivityContext,
                EditActivity.class);

        return i;
    }

    @Override
    public boolean savePicture() {
        return false;
    }

    @Override
    public String getShopname() {
        return null;
    }

    @Override
    public double getSum() {
        return 0;
    }

    @Override
    public Date getDate() {
        return null;
    }
}
