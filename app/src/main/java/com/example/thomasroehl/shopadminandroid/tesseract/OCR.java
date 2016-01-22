package com.example.thomasroehl.shopadminandroid.tesseract;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.thomasroehl.shopadminandroid.R;
import com.example.thomasroehl.shopadminandroid.gui.EditActivity;
import com.example.thomasroehl.shopadminandroid.statics.StorageAdmin;
import com.googlecode.tesseract.android.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Created by Thomas Roehl on 10.01.2016.
 */
public class OCR extends Activity{

    private final String DATA_PATH = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/OCR/";
    private final String lang = "deu";
    private final String TAG = "OCR.java";
    private Bitmap bitmap;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr);
        bitmap = StorageAdmin.CAMERACONTROLLER.getCurrentPicture();
        Button start = (Button) findViewById(R.id.ocr_button);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.progressText);


        String path = DATA_PATH + "tessdata/";

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

        if (!(new File(DATA_PATH + "tessdata/" + lang + ".traineddata")).exists()) {
            try {

                AssetManager assetManager = getAssets();
                InputStream in = assetManager.open("tessdata/" + lang + ".traineddata");
                //GZIPInputStream gin = new GZIPInputStream(in);
                OutputStream out = new FileOutputStream(DATA_PATH
                        + "tessdata/" + lang + ".traineddata");

                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                //while ((lenf = gin.read(buff)) > 0) {
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                //gin.close();
                out.close();

                Log.v(TAG, "Copied " + lang + " traineddata");
            } catch (IOException e) {
                Log.e(TAG, "Was unable to copy " + lang + " traineddata " + e.toString());
            }
        }

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String results = getText();
                Log.v(TAG, "TESSERACT RESULTS: " + results);
                screenflowToEdit();
            }
        });


    }

    public void screenflowToEdit(){
        startActivity(new Intent(this, EditActivity.class));
    }

    public String getText(){

//        Bitmap bmp = toStrictBlackWhite(bitmap);


        TessBaseAPI baseApi = new TessBaseAPI();
        baseApi.setDebug(true);
        baseApi.init(DATA_PATH, lang);
        baseApi.setPageSegMode(TessBaseAPI.PageSegMode.PSM_SINGLE_BLOCK);
//        try{
//            baseApi.setImage(bmp);
//        }
//        catch (Exception e){
//            Log.v(TAG, "USE ORG BITMAP");
            baseApi.setImage(bitmap);
//        }

        String recognizedText = baseApi.getUTF8Text();

        baseApi.end();

        return recognizedText;
    }

    public Bitmap toStrictBlackWhite(Bitmap bmp){
        Bitmap imageOut = bmp;
        int tempColorRed;
        for(int y=0; y<bmp.getHeight(); y++){
            for(int x=0; x<bmp.getWidth(); x++){
                tempColorRed = Color.red(imageOut.getPixel(x, y));
                Log.v(TAG, "COLOR: "+tempColorRed);

                if(imageOut.getPixel(x,y) < 127){
                    imageOut.setPixel(x, y, 0xffffff);
                }
                else{
                    imageOut.setPixel(x, y, 0x000000);
                }
            }
        }
        return imageOut;
    }

}
