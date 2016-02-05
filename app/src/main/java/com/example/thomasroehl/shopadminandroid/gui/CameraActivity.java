package com.example.thomasroehl.shopadminandroid.gui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;

import com.example.thomasroehl.shopadminandroid.R;
import com.example.thomasroehl.shopadminandroid.camera.CameraController;
import com.example.thomasroehl.shopadminandroid.camera.CameraControllerInterf;
import com.example.thomasroehl.shopadminandroid.statics.StorageAdmin;

import java.io.File;

/**
 * Created by SZC on 10.12.2015.
 */
public class CameraActivity extends AppCompatActivity {
    final static int        CAMERA_OUTPUT = 1848;
    CameraController        myController = null;
    private final String DATA_PATH = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/OCR/";
    private final String TAG = "CAMACT.java";
    private String _path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_camera);
        String path = DATA_PATH;
        File dir = new File(path);
        Log.v(TAG, "PATH: " + path);

        if (!dir.exists()) {
            Log.v(TAG, "PATH EXISTS");
            if (!dir.mkdirs()) {
                Log.v(TAG, "ERROR: Creation of directory " + path + " on sdcard failed");
                return;
            } else {
                Log.v(TAG, "Created directory " + path + " on sdcard");
            }
        }
        _path = DATA_PATH + "/ocr.jpg";

        myController = (CameraController)StorageAdmin.CAMERACONTROLLER;

        myController.setCurrentActivityContext(this);

        StorageAdmin.register(this);  // for app exit (tanja)

//        Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
        //Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //i.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        if (_path == null) Log.v(TAG, "path null");
        File file = new File(_path);
        Uri outputFileUri = Uri.fromFile(file);

        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

        startActivityForResult(intent, 0);

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        Log.i("Camera", "onActivityResult");
//
//        if (requestCode == CAMERA_OUTPUT && resultCode == RESULT_OK) {
//
//            Bundle extras = data.getExtras();
//
//            this.myController.setPicture((Bitmap)extras.get("data"));
//
//            startActivity(this.myController.screenFlowOCR());
//        }
//        else if (requestCode == CAMERA_OUTPUT && resultCode == RESULT_CANCELED) {
//            finish();
//        }
        Log.i(TAG, "resultCode: " + resultCode);

        if (resultCode == -1) {
            StorageAdmin.CAMERACONTROLLER.savePicture(_path);
            startActivity(this.myController.screenFlowOCR());
        } else {
            Log.v(TAG, "User cancelled");
        }

    }

}
