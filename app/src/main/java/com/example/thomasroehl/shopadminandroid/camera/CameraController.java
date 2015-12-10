package com.example.thomasroehl.shopadminandroid.camera;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.thomasroehl.shopadminandroid.gui.CameraActivity;
import com.example.thomasroehl.shopadminandroid.gui.EditActivity;

import java.sql.Date;

/**
 * Created by SZC on 10.12.2015.
 */
public class CameraController implements CameraControllerInterf {
    private static CameraController instance = null;
    private Bitmap currentPicture;
    Context currentActivityContext;

    protected  CameraController() {
        this.currentActivityContext = null;
        this.currentPicture = null;
    }

    /**
     * Returns the created instance of the controller
     *
     * @return Controller instance
     */
    public static CameraController getInstance() {
        if (instance == null) {
            instance = new CameraController();
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

    /**
     * Reference to the activity context
     *
     * @param picture Reference on View Context
     */
    public void setPicture(Bitmap picture) {
        this.currentPicture = picture;
    }

    /**
     * Screen Flow to EditView
     *
     * @return Intent to EditView
     */
    @Override
    public Intent screenFlowEdit() {
        Intent i = new Intent(
                this.currentActivityContext,
                EditActivity.class);

        return i;
    }


    /**
     * Save the given picture to ...
     *
     */
    @Override
    public boolean savePicture() {
        if (this.currentPicture == null)
            return false;
        else {
            /*
             * TODO: Wo soll das Bild gespeichert werden?
             * Eine Methode zum temporären speichern in der Datenbank wäre nicht schlecht,
             * da Singleton in Android nicht hundertprozentig zuverlässig. Siehe:
             * https://portabledroid.wordpress.com/2012/05/04/singletons-in-android/
             * Wird das Bild allerdings in der Datenbank gespeichert, wird ebenfalls eine
             * Logik zum löschen benötigt, sobald die Daten extrahiert wurden. Ansonsten
             * wird das Handy zugemüllt.
             * Allerdings kann das Bild doch auch direkt ohne speichern verarbeitet werden?
             */
            return true;
        }
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
