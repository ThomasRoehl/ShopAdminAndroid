package com.example.thomasroehl.shopadminandroid.tesseract;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.thomasroehl.shopadminandroid.R;
import com.example.thomasroehl.shopadminandroid.gui.CameraActivity;
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
    private final String lang = "deu_frak";
    private final String TAG = "OCR.java";
    private final String PICTURENAME = "rewe2.bmp";
    private Bitmap bitmap;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    private ImageView imgV;
    private Classificator cls = new Classificator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr);
        bitmap = StorageAdmin.CAMERACONTROLLER.getCurrentPicture();
        Button start = (Button) findViewById(R.id.ocr_button);
        Button rescan = (Button) findViewById(R.id.ocrRescanBtn);
        imgV = (ImageView) findViewById(R.id.imageView);

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

//        copyPicture();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String results = getText();
                Log.v(TAG, "TESSERACT RESULTS: " + results);
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
                catch(Exception e){
                    StorageAdmin.EDITCONTROLLER.setSum(-1.0);
                }

//                screenflowToEdit();
            }
        });
    }

    public void rescan(View view){
        startActivity(new Intent(OCR.this, CameraActivity.class));
    }

    public void edit(View view){
        startActivity(new Intent(OCR.this, EditActivity.class));
    }

    public void screenflowToEdit(){
        startActivity(new Intent(this, EditActivity.class));
    }

    public String getText(){

        Bitmap bmp = toBinary(bitmap);
        try{
            imgV.setImageBitmap(bmp);
        }
        catch(Exception e){
            Log.v(TAG, "BINARY IMAGE BROKEN");
        }

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

        return recognizedText;
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
        return bmpBinary;
    }

    private void copyPicture(){
        try {
            File path = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES);
            File file = new File(path, PICTURENAME);
            // the Pictures directory exists?
            path.mkdirs();
            InputStream is = getResources().openRawResource(+ R.drawable.rewe2);
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
}
