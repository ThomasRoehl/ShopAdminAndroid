package com.example.thomasroehl.shopadminandroid.gui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.thomasroehl.shopadminandroid.R;
import com.example.thomasroehl.shopadminandroid.container.User;
import com.example.thomasroehl.shopadminandroid.statics.StorageAdmin;

/**
 * Created by tetiana on 20.02.16.
 */
public class MakeFoto extends AppCompatActivity {

    ImageView image;
    ImageView image2;
    Button buttonBack;
    Context c;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_foto);
        image = (ImageView) findViewById(R.id.idFoto);
        image.setImageResource(R.drawable.picture2);
        c = this.getApplicationContext();
        image2 = (ImageView) findViewById(R.id.idFoto2);
        image2.setImageResource(R.drawable.picture4);

        buttonBack = (Button) findViewById(R.id.idback);



        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end();


            }
        });
    }

    public void end(){
        this.finish();
    }


}
