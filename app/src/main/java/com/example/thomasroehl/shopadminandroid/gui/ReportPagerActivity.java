package com.example.thomasroehl.shopadminandroid.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.thomasroehl.shopadminandroid.R;
import com.example.thomasroehl.shopadminandroid.reports.ReportPagerAdapter;
import com.example.thomasroehl.shopadminandroid.statics.StorageAdmin;

/**
 * Created by SZC on 12.12.2015.
 */
public class ReportPagerActivity extends AppCompatActivity {
    ReportPagerAdapter myPagerAdapter = null;
    ViewPager myViewPager = null;
    TabLayout myTabLayout = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        Toolbar toolbar = (Toolbar) findViewById(R.id.idToolbarEdit1);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Shop Overview"));
        tabLayout.addTab(tabLayout.newTab().setText("Category Overview"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        StorageAdmin.register(this);  // for app exit (tanja)


        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
        myPagerAdapter = new ReportPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        myViewPager = (ViewPager) findViewById(R.id.reportPager);
        myViewPager.setAdapter(myPagerAdapter);

        myViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                myViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public void onClickTable(View view){
        System.out.println("xxxxxxxxxxxxxx ReportPagerActivity onClickTable xxxxxxxxxxxxxx");
    }

    // settings (tanja)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_report_pager, menu);
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
            Intent i = new Intent(
                    this.getApplicationContext(),
                    MakeFoto.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.action_logout){
            //TO DO
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
