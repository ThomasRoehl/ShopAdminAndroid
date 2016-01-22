package com.example.thomasroehl.shopadminandroid.camera;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.thomasroehl.shopadminandroid.gui.CameraActivity;
import com.example.thomasroehl.shopadminandroid.gui.EditActivity;
import com.example.thomasroehl.shopadminandroid.tesseract.OCR;

import java.io.IOException;
import java.sql.Date;

/**
 * Created by SZC on 10.12.2015.
 */
public class CameraController implements CameraControllerInterf {
    private static CameraController instance = null;
    private Bitmap currentPicture;
    Context currentActivityContext;
    private final String TAG = "CAMCON.java";

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
    public Intent screenFlowOCR() {
        Intent i = new Intent(
                this.currentActivityContext,
                OCR.class);

        return i;
    }


    /**
     * Save the given picture to ...
     *
     */
    @Override
    public boolean savePicture(String _path) {
//        if (this.currentPicture == null)
//            return false;
//        else {
//            /*
//             * TODO: Wo soll das Bild gespeichert werden?
//             * Eine Methode zum temporären speichern in der Datenbank wäre nicht schlecht,
//             * da Singleton in Android nicht hundertprozentig zuverlässig. Siehe:
//             * https://portabledroid.wordpress.com/2012/05/04/singletons-in-android/
//             * Wird das Bild allerdings in der Datenbank gespeichert, wird ebenfalls eine
//             * Logik zum löschen benötigt, sobald die Daten extrahiert wurden. Ansonsten
//             * wird das Handy zugemüllt.
//             * Allerdings kann das Bild doch auch direkt ohne speichern verarbeitet werden?
//             */
//
//            return true;
//        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;

        Bitmap bitmap = BitmapFactory.decodeFile(_path, options);

        try {
            ExifInterface exif = new ExifInterface(_path);
            int exifOrientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);

            Log.v(TAG, "Orient: " + exifOrientation);

            int rotate = 0;

            switch (exifOrientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
            }

            Log.v(TAG, "Rotation: " + rotate);

            if (rotate != 0) {

                // Getting width & height of the given image.
                int w = bitmap.getWidth();
                int h = bitmap.getHeight();

                // Setting pre rotate
                Matrix mtx = new Matrix();
                mtx.preRotate(rotate);

                // Rotating Bitmap
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, false);
            }

            // Convert to ARGB_8888, required by tess
            currentPicture = bitmap.copy(Bitmap.Config.ARGB_8888, true);

        } catch (IOException e) {
            Log.e(TAG, "Couldn't correct orientation: " + e.toString());
            return false;
        }
        return true;
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

    @Override
    public Bitmap getCurrentPicture() {
        return currentPicture;
    }
}
