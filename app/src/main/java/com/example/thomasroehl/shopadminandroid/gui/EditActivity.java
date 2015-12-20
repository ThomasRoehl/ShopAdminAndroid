package com.example.thomasroehl.shopadminandroid.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.thomasroehl.shopadminandroid.R;
import com.example.thomasroehl.shopadminandroid.edit.EditControllerImpl;
import com.example.thomasroehl.shopadminandroid.statics.StorageAdmin;

/**
 * Created by Roger M. Nabinger on 06.12.2015.
 *
 *
 */



public class EditActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditControllerImpl  myController = null;
    private String category;

    Spinner categorySpinner;

    public void setShop(String x){
        EditText shop = (EditText)findViewById(R.id.editShop);
        shop.setText(x);
    }
    public String getShop(){
        EditText shop = (EditText)findViewById(R.id.editShop);
        return shop.getText().toString();
    }

    public void setSum(String x){
        EditText sum = (EditText)findViewById(R.id.editSum);
        sum.setText(x);
    }
    public String getSum(){
        EditText sum = (EditText)findViewById(R.id.editSum);
        return sum.getText().toString();
    }

    public void setDate(String x){
        EditText date = (EditText)findViewById(R.id.editDate);
        date.setText(x);
    }
    public String getDate(){
        EditText date = (EditText)findViewById(R.id.editDate);
        return date.getText().toString();
    }





    public void setCategory(String category){
        this.category = category;
    }
    public String getCategory(){
        return category;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        categorySpinner = (Spinner) findViewById(R.id.spinnerCategory);
        populateSpinner();
        categorySpinner.setOnItemSelectedListener(this);

        //GetController Instance
        myController = (EditControllerImpl) StorageAdmin.EDITCONTROLLER;

        //SetContext
        myController.setCurrentActivityContext(this);
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
        startActivity(new Intent(EditActivity.this, MainActivity.class));
        //startActivity(this.myController.screenFlowMain());
    }

    public void rescanData(View view) {
        // TODO: Roger - Testimplementierung
        //Toast.makeText(EditActivity.this, "Goto rescan", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditActivity.this, CameraActivity.class));
        //startActivity(this.myController.screenFlowCamera());
    }








    // Aus Interface

/*
    @Override
    public boolean saveData(String shop, String category, String sum, String date) {
        return false;
    }

    @Override
    public Intent screenFlowCamera() {
        // TODO: Roger
        return null;
    }

    @Override
    public String screenFlowMain() {
        // TODO: Roger
        return null;
    }
*/
}
