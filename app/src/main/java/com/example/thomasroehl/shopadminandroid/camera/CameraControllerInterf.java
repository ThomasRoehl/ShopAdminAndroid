package com.example.thomasroehl.shopadminandroid.camera;

import java.sql.Date;

/**
 * Created by Thomas Roehl on 04.12.2015.
 * Test Change for commit(SZ04122015)
 */
public interface CameraControllerInterf {
    /**
     * return screenflow destination for EditView
     * @return name of destination
     */
    public String screenFlowEdit();

    /**
     * save picture from camera
     * @return true if picture save was successful, else false
     */
    public boolean savePicture();

    /**
     * get shopname from OCR
     * @return evaluated shopname form OCR
     */
    public String getShopname();

    /**
     * get sum from OCR
     * @return evaluated sum from OCR
     */
    public double getSum();

    /**
     * get date from OCR
     * @return evaluated date from OCR
     */
    public Date getDate();
    
}
