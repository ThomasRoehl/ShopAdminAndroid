package com.example.thomasroehl.shopadminandroid.startscreen;

import android.content.Context;
import android.content.Intent;
import android.widget.TableLayout;

/**
 * Created by Thomas Roehl on 04.12.2015.
 */
public interface StartScreenControllerInterf {
    public void setTableLayout(TableLayout tableLayout);
    public void setCurrentActivityContext(Context context);

    public Intent screenFlowScan();
    public Intent screenFlowTable();
    public void getTable(int amount);
}
