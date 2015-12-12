package com.example.thomasroehl.shopadminandroid.startscreen;

import android.content.Intent;

/**
 * Created by Thomas Roehl on 04.12.2015.
 */
public interface StartScreenControllerInterf {
    public Intent screenFlowScan();
    public Intent screenFlowTable();
    public void getTable(int amount);
}
