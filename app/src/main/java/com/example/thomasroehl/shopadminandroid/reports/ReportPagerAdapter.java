package com.example.thomasroehl.shopadminandroid.reports;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by SZC on 12.12.2015.
 */
public class ReportPagerAdapter extends FragmentStatePagerAdapter {
    int NumOfReports = 0;

    public ReportPagerAdapter(FragmentManager fm, int NumOfReports) {
        super(fm);
        this.NumOfReports = NumOfReports;
    }

    @Override
    public Fragment getItem(int position) {

        /*
        Fragment fragment = new ReportShopSummaryFragment();
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt(ReportShopSummaryFragment.ARG_OBJECT, position + 1);
        fragment.setArguments(args);*/

        switch (position) {
            case 0:
                ReportShopSummaryFragment tab1 = new ReportShopSummaryFragment();
                return tab1;
            case 1:
                ReportCategorySummaryFragment tab2 = new ReportCategorySummaryFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.NumOfReports;
    }

}
