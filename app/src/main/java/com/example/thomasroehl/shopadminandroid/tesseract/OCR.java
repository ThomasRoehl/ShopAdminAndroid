package com.example.thomasroehl.shopadminandroid.tesseract;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;

import com.googlecode.tesseract.android.*;


/**
 * Created by Thomas Roehl on 10.01.2016.
 */
public class OCR{


    public String getText(Bitmap bitmap){
        TessBaseAPI tessBaseAPI = new TessBaseAPI();

        String path = "/mnt/sdcard/packagename/tessdata/eng.traineddata";

        tessBaseAPI.setDebug(true);
        tessBaseAPI.init(path, "eng"); //Init the Tess with the trained data file, with english language

        //For example if we want to only detect numbers
        tessBaseAPI.setVariable(TessBaseAPI.VAR_CHAR_WHITELIST, "1234567890");
        tessBaseAPI.setVariable(TessBaseAPI.VAR_CHAR_BLACKLIST, "!@#$%^&*()_+=-qwertyuiop[]}{POIU" +
                "YTREWQasdASDfghFGHjklJKLl;L:'\"\\|~`xcvXCVbnmBNM,./<>?");


        tessBaseAPI.setImage(bitmap);

        String text = tessBaseAPI.getUTF8Text();

        tessBaseAPI.end();

        return text;
    }

}
