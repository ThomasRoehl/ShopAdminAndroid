package com.example.thomasroehl.shopadminandroid.reports;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.thomasroehl.shopadminandroid.R;
import com.example.thomasroehl.shopadminandroid.startscreen.MainViewController;
import com.example.thomasroehl.shopadminandroid.statics.StorageAdmin;

import java.util.ArrayList;

/**
 * Created by SZC on 12.12.2015.
 */
public class ReportShopSummaryFragment extends Fragment {
    ReportController myController = null;
    TableLayout ShopSummaryFragmentTable = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.report_fragment_shop_summary, container, false);

        //GetController Instance
        myController = (ReportController) StorageAdmin.REPORTCONTROLLER;

        //SetContext
        myController.setCurrentActivityContext(getActivity());

        myController.expandedShopNames.clear();

        //Fill receipt overview table
        ShopSummaryFragmentTable = (TableLayout) rootView.findViewById(R.id.tbl_Report_Shop_Summary);

        myController.getTable(StorageAdmin.REPORT_SHOP_SUMMARY, ShopSummaryFragmentTable);

        //for jeden row in ShopSummaryFragmentTable add listener
        View tableLayout = ShopSummaryFragmentTable.getTouchables().get(0);
        ArrayList<View> tableRows = tableLayout.getTouchables();
        int numOfTableRows = tableRows.size();
        System.out.println("ReportShopSummaryFragment onCreateView numOfTableRows ---> " + numOfTableRows);
        for(int i = 0; i < numOfTableRows; i++){
            System.out.println("ReportShopSummaryFragment onCreateView tableRows.get(i) ----> " + tableRows.get(i));
        }

        return rootView;
    }
}
