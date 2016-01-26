package com.example.thomasroehl.shopadminandroid.reports;

import android.content.Context;
import android.content.Intent;
import android.widget.TableLayout;

import com.example.thomasroehl.shopadminandroid.container.Receipt;

import java.util.ArrayList;

/**
 * Created by Thomas Roehl on 04.12.2015.
 */
public interface ReportControllerInterf {
    public void setCurrentActivityContext(Context context);

    public Intent screenFlowMain();
    public boolean setFilter(String filter);
    public void getTable(String name, TableLayout tl);
    public void fillReceiptTableByName();
    public void fillReceiptTableByCategory();
    public ArrayList<Receipt> getAllReceiptGroupByName(int id);
    public ArrayList<Receipt> getAllReceiptGroupByCategory(int id);
    public ArrayList<Receipt> getReceiptsBySpecialShopname(int id, String shopname);

}
