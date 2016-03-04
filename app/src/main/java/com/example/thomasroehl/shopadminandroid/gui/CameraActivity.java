package com.example.thomasroehl.shopadminandroid.gui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
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
import com.example.thomasroehl.shopadminandroid.tesseract.Classificator;
import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by SZC on 10.12.2015.
 */
public class CameraActivity extends AppCompatActivity {
    final static int        CAMERA_OUTPUT = 1848;
    CameraController        myController = null;
    private String _path;
    private final String DATA_PATH = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/OCR/";
    private final String lang = "deu_frak";
    private final String TAG = "OCR.java";
    private final String PICTURENAME = "rewe1.bmp";
    private Bitmap bitmap;
    private Classificator cls = new Classificator();
    private Thread ocrThread;
    private String results = "";

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

        path = DATA_PATH + "tessdata/";

        dir = new File(path);
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
            bitmap = StorageAdmin.CAMERACONTROLLER.getCurrentPicture();
            getText();
            cls.setText(results);
            cls.classificate();
            String shopName = cls.getShopName();
            String sum = cls.getSum();
            Log.v(TAG, "SHOPNAME: " + shopName);
            Log.v(TAG, "SUM: " + sum);
            StorageAdmin.EDITCONTROLLER.setShopName(shopName);
            try{
                StorageAdmin.EDITCONTROLLER.setSum(Double.parseDouble(sum));
            }
            catch(Exception e) {
                StorageAdmin.EDITCONTROLLER.setSum(-1.0);
            }
            startActivity(this.myController.screenFlowOCR());
        } else {
            Log.v(TAG, "User cancelled");
        }

    }

    public void getText(){
        Bitmap bmp = toBinary(bitmap);
//        bmp = getResizedBitmap(bmp);

        TessBaseAPI baseApi = new TessBaseAPI();
        baseApi.setDebug(true);
        baseApi.init(DATA_PATH, lang);
        baseApi.setPageSegMode(TessBaseAPI.PageSegMode.PSM_SINGLE_BLOCK);
        try{
            baseApi.setImage(bmp);
        }
        catch (Exception e){
            Log.v(TAG, "USE ORG BITMAP");
            baseApi.setImage(bitmap);
        }
        String recognizedText = baseApi.getUTF8Text();

        baseApi.end();

        this.results = recognizedText;
    }

    public Bitmap toBinary(Bitmap bmpOriginal) {
        int width, height, threshold;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();
        threshold = 127;
        Bitmap bmpBinary = bmpOriginal.copy(Bitmap.Config.ARGB_8888, true);
        Log.v(TAG, "BITMAP: " + bmpBinary.isMutable());

        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                // get one pixel color
                int pixel = bmpOriginal.getPixel(x, y);
                int green = Color.green(pixel);
                int blue = Color.blue(pixel);
                int red = Color.red(pixel);
                int gray = (int)(red * 0.3 + green * 0.59 + blue * 0.11);

                //get binary value
                if(gray < threshold){
                    bmpBinary.setPixel(x, y, 0xFF000000);
                } else{
                    bmpBinary.setPixel(x, y, 0xFFFFFFFF);
                }

            }
        }
        Log.v(TAG, "PICTURE CHANGED TO BINARY");
        return bmpBinary;
    }

    private void copyPicture(){
        try {
            File path = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES);
            File file = new File(path, PICTURENAME);
            // the Pictures directory exists?
            path.mkdirs();
            InputStream is = getResources().openRawResource(+ R.drawable.rewe1);
            OutputStream os = new FileOutputStream(file);
            byte[] data = new byte[is.available()];
            is.read(data);
            os.write(data);
            is.close();
            os.close();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            bitmap = BitmapFactory.decodeFile(path + "/" + PICTURENAME , options);
//            imgV.setImageBitmap(bitmap);
        }
        catch(Exception e){
            Log.v(TAG, "copy picture failed");
        }
    }

    public Bitmap getResizedBitmap(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        if (width > 1200) return bm;
        if (height > 2000) return bm;
        float dw = 1200.0F / ((float)width);
        float dh = 2000.0F / ((float)height);
        Log.v(TAG, "DH: " + dh + "\t" + width);
        Log.v(TAG, "DW: " + dw + "\t" + height);
        float scaleWidth = ((float) 1200) / width;
        float scaleHeight = ((float) 2000) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        Log.v(TAG, "RESIZED TO : Width:" + resizedBitmap.getWidth() + " Height: " + resizedBitmap.getHeight());
        return resizedBitmap;
    }

}
