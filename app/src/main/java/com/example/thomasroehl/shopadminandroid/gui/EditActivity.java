package com.example.thomasroehl.shopadminandroid.gui;

import com.example.thomasroehl.shopadminandroid.edit.EditControllerInterf;
import com.example.thomasroehl.shopadminandroid.R;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Date;

/**
 * Created by Roger M. Nabinger on 06.12.2015.
 *
 *
 */



public class EditActivity extends AppCompatActivity implements EditControllerInterf, AdapterView.OnItemSelectedListener {

    private String shop;
    private String category;
    private double sum;
    private String today;

    Spinner categorySpinner;
//    EditText editCategory;

    public void setCategory(String category) {
        this.category = category;
    }
    public String getCategory() {
        return category;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getShop() {
        return shop;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getSum() {
        return sum;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getToday() {
        return today;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        categorySpinner = (Spinner) findViewById(R.id.spinnerCategory);
        populateSpinner();
        categorySpinner.setOnItemSelectedListener(this);
    }

    public void populateSpinner(){
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
        categorySpinner.setAdapter(categoryAdapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        String category = parent.getItemAtPosition(position).toString();
        setCategory(category);
    }

    public void onNothingSelected(AdapterView<?> parentView){

    }

    public void saveMyData(View view) {
        // TODO: Roger - Testimplementierung
        // Daten übernehmen und in Variablen speichern
        // Methode zum Speichern in Datenbank aufrufen

        EditText editShop = (EditText)findViewById(R.id.editShop);
        setShop(editShop.getText().toString());

        EditText editSum = (EditText)findViewById(R.id.editSum);
        setSum(Double.parseDouble(editSum.getText().toString()));

        EditText editDate = (EditText)findViewById(R.id.editDate);
        setToday(editDate.getText().toString());


        Toast.makeText(EditActivity.this, "Saving Data:" +
                "\nShop: " + getShop() +
                "\nSum: " + getSum() +
                "\nCategory: " + getCategory() +
                "\nDate: " + getToday(),
                Toast.LENGTH_SHORT).show();
    }

    public void rescanData(View view) {
        // TODO: Roger - Testimplementierung
        Toast.makeText(EditActivity.this, "Goto rescan", Toast.LENGTH_SHORT).show();
    }








    // Aus Interface


    @Override
    public boolean saveData(String shop, String category, double sum, Date date) {
        // TODO: Roger
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
