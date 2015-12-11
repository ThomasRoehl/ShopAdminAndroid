package com.example.thomasroehl.shopadminandroid.gui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
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

/**
 * Created by SZC on 10.12.2015.
 */
public class CameraActivity extends AppCompatActivity {
    final static int        CAMERA_OUTPUT = 1848;
    CameraController        myController = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_camera);

        myController = (CameraController)StorageAdmin.CAMERACONTROLLER;

        myController.setCurrentActivityContext(this);

        Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
        //Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //i.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        startActivityForResult(i, CAMERA_OUTPUT);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i("Camera", "onActivityResult");

        if (requestCode == CAMERA_OUTPUT && resultCode == RESULT_OK) {

            Bundle extras = data.getExtras();

            this.myController.setPicture((Bitmap)extras.get("data"));

            startActivity(this.myController.screenFlowEdit());
        }
        else if (requestCode == CAMERA_OUTPUT && resultCode == RESULT_CANCELED) {
            finish();
        }

    }

}
