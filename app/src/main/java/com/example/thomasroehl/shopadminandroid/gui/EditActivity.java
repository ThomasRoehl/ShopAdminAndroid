package com.example.thomasroehl.shopadminandroid.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.thomasroehl.shopadminandroid.R;
import com.example.thomasroehl.shopadminandroid.edit.EditControllerInterf;
import java.sql.Date;

/**
 * Created by Roger M. Nabinger on 06.12.2015.
 */

public class EditActivity extends AppCompatActivity implements EditControllerInterf{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }

    @Override
    public boolean saveData(String shop, String category, double sum, Date date) {
        /*
         * TODO: Roger - Schreiben in Datenbank
         * Ich benoetige eine Methode um die Daten in die DB zu schreiben. Finde ich diese
         * dann im DatabaseInterf?
         */
        return false;
    }

    @Override
    public String screenFlowCamera() {
        // TODO: Roger
        return null;
    }

    @Override
    public String screenFlowMain() {
        // TODO: Roger
        return null;
    }
}
