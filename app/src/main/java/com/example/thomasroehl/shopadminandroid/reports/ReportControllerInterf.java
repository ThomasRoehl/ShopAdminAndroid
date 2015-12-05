package com.example.thomasroehl.shopadminandroid.reports;

/**
 * Created by Thomas Roehl on 04.12.2015.
 */
public interface ReportControllerInterf {
    public String screenFlowMain();
    public boolean setFilter(String filter);
    public Object getTable();
    public Object getTable(String name);
}
