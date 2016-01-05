package com.example.thomasroehl.shopadminandroid.edit;

import android.content.Intent;

import com.example.thomasroehl.shopadminandroid.container.Receipt;

import java.sql.Date;

/**
 * Created by Thomas Roehl on 04.12.2015.
 */
public interface EditControllerInterf {
    // TODO: Thomas - statt saveData wäre hier besser editData, da Speichern erst im editView erfolgt.
    public boolean saveData(Receipt receipt);
    public Intent screenFlowCamera();
    public Intent screenFlowMain();
    public boolean isValidDate(String dateString);

}
