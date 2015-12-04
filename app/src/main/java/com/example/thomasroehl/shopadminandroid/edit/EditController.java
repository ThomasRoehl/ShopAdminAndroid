package com.example.thomasroehl.shopadminandroid.edit;

import java.sql.Date;

/**
 * Created by Thomas Roehl on 04.12.2015.
 */
public interface EditController {
    public boolean saveData(String shop, String category, double sum, Date date);
    public String screenFlowCamera();
    public String screenFlowMain();

}
