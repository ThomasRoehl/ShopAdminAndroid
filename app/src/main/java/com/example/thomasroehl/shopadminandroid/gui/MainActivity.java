package com.example.thomasroehl.shopadminandroid.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;

import com.example.thomasroehl.shopadminandroid.R;
import com.example.thomasroehl.shopadminandroid.startscreen.MainViewController;
import com.example.thomasroehl.shopadminandroid.statics.StorageAdmin;

public class MainActivity extends AppCompatActivity {
    MainViewController  myController = null;
    TableLayout         mainViewReceipts = null;
    Boolean             backFromScan = false;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.idToolbarEdit1);
        setSupportActionBar(toolbar);

        StorageAdmin.register(this);   // for app exit (tanja)

        //GetController Instance
        myController = (MainViewController)StorageAdmin.STARTSCREENCONTROLLER;

        //SetContext
        myController.setCurrentActivityContext(this);

        //Fill receipt overview table
        mainViewReceipts = (TableLayout) findViewById(R.id.tbl_Main);
        myController.setTableLayout(mainViewReceipts);



        /*
         * TODO: Steuerung von Login/Register und vorhalten der Benutzerdaten zur Laufzeit
         * Redefiniton der Application-Klasse
         * http://stackoverflow.com/questions/5725152/android-proper-way-to-handle-activities
         */

        myController.getTable(10);

    }

    // settings (tanja)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // settings (tanja)
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_exit){
            StorageAdmin.finishAll();
            return true;
        }

        if (id == R.id.action_help){
            // TO DO
            return true;
        }

        if (id == R.id.action_logout){
            //TO DO
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickScan(View view) {
        Log.i("clicks", "You clicked Button Scan");
        this.backFromScan = true;
        startActivity(this.myController.screenFlowScan());

    }

    public void onClickTable(View view) {
        System.out.println("xxxxxxxxxxxxxxxxx MainActivity onClickTable Event handler xxxxxxxxxxxxxxxx");
        Log.i("clicks", "You clicked the Table");
        startActivity(this.myController.screenFlowTable());
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (this.backFromScan) {
            Log.i("OnResume", "BackFromScan");

            //this.myCameraController = CameraController.getInstance();
        }

    }


}
