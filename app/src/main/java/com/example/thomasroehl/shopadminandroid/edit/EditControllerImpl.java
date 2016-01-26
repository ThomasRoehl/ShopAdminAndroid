package com.example.thomasroehl.shopadminandroid.edit;

import android.content.Context;
import android.content.Intent;

import com.example.thomasroehl.shopadminandroid.container.Receipt;
import com.example.thomasroehl.shopadminandroid.gui.CameraActivity;
import com.example.thomasroehl.shopadminandroid.gui.MainActivity;
import com.example.thomasroehl.shopadminandroid.statics.StorageAdmin;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by altug on 17.12.15.
 */
public class EditControllerImpl implements EditControllerInterf {

    Context currentActivityContext;
    private static EditControllerImpl instance = null;
    private String shopname;
    private Double sum;

    protected EditControllerImpl(){
        this.currentActivityContext = null;

    }

    /**
     * Returns the created instance of the controller
     *
     * @return Controller instance
     */
    public static EditControllerImpl getInstance() {
        if (instance == null) {
            instance = new EditControllerImpl();
        }
        return instance;
    }

    /**
     * Reference to the activity context
     *
     * @param context Reference on View Context
     */
    public void setCurrentActivityContext(Context context) {
        this.currentActivityContext = context;
    }

    @Override
    public boolean saveData(Receipt receipt) {
        if(StorageAdmin.DBCONTROLLER.createReceipt(receipt)){
            return  true;
        }
        else{
            return false;
        }
    }

    //katia & julia 05.01.2016
    /**
     * check input date format is "dd/MM/yyyy"
     *
     * @param dateString
     * @return
     */
    public boolean isValidDate(String dateString) {
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        try {
            if(dateString.matches("\\d{2}.\\d{2}.\\d{4}")) {
                df.setLenient(false);
                df.parse(dateString);
                return true;
            }
            else
                return false;
        } catch (ParseException e) {
            return false;
        }
    }

    @Override
    public void setShopName(String name) {
        this.shopname = name;
    }

    @Override
    public void setSum(Double sum) {
        this.sum = sum;
    }

    @Override
    public String getShopName() {
        return shopname;
    }

    @Override
    public Double getSum() {
        return sum;
    }


    @Override
    public Intent screenFlowCamera() {
        Intent i = new Intent(
                this.currentActivityContext,
                CameraActivity.class);

        return i;
    }

    @Override
    public Intent screenFlowMain() {
        Intent i = new Intent(
                this.currentActivityContext,
                MainActivity.class);
        return i;
    }
}
