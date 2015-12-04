package com.example.thomasroehl.shopadminandroid.camera;

import java.sql.Date;

/**
 * Created by Thomas Roehl on 04.12.2015.
 */
public interface CameraController {
    public String screenFlowEdit();
    public boolean savePicture();
    public String getShopname();
    public double getSum();
    public Date getDate();
    
}
