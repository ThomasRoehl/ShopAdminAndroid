package com.example.thomasroehl.shopadminandroid.startscreen;

import android.content.Context;
import android.content.Intent;
import android.widget.TableLayout;

import com.example.thomasroehl.shopadminandroid.container.Receipt;

import java.util.ArrayList;

/**
 * Created by Thomas Roehl on 04.12.2015.
 */
public interface StartScreenControllerInterf {
    public void setTableLayout(TableLayout tableLayout);
    public void setCurrentActivityContext(Context context);

    public Intent screenFlowScan();
    public Intent screenFlowTable();
    public void getTable(int amount);
    public void fillReceiptTable();
    public ArrayList<Receipt> getAllReceipts(int id);
}
