package com.example.thomasroehl.shopadminandroid.reports;

import android.content.Context;
import android.content.Intent;
import android.widget.TableLayout;

/**
 * Created by Thomas Roehl on 04.12.2015.
 */
public interface ReportControllerInterf {
    public void setTableLayout(TableLayout tableLayout);
    public void setCurrentActivityContext(Context context);

    public Intent screenFlowMain();
    public boolean setFilter(String filter);
    public void getTable(String name);

}
