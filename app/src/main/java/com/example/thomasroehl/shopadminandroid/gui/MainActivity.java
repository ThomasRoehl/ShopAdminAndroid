package com.example.thomasroehl.shopadminandroid.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;

import com.example.thomasroehl.shopadminandroid.R;
import com.example.thomasroehl.shopadminandroid.camera.CameraController;
import com.example.thomasroehl.shopadminandroid.startscreen.MainViewController;

public class MainActivity extends AppCompatActivity {
    MainViewController  myController = null;
    TableLayout         mainViewReceipts = null;
    Boolean             backFromScan = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //GetController Instance
        myController = MainViewController.getInstance();

        //SetContext
        myController.setCurrentActivityContext(this);

        //Fill receipt overview table
        mainViewReceipts = (TableLayout) findViewById(R.id.tbl_Main);

        myController.setTableLayout(mainViewReceipts);

        myController.getTable(10);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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
        Log.i("clicks", "You clicked the Table");
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (this.backFromScan) {
            CameraController test = new CameraController();
            test.setCurrentActivityContext(this);

            startActivity(test.screenFlowEdit());
        }

    }
}
