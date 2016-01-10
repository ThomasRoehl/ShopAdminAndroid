package com.example.thomasroehl.shopadminandroid.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.thomasroehl.shopadminandroid.R;
import com.example.thomasroehl.shopadminandroid.container.Receipt;
import com.example.thomasroehl.shopadminandroid.database.DatabaseController;
import com.example.thomasroehl.shopadminandroid.edit.EditControllerImpl;
import com.example.thomasroehl.shopadminandroid.statics.StorageAdmin;

/**
 * Created by Roger M. Nabinger on 06.12.2015.
 *
 *
 */



public class EditActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Katja 31.12 declare buttons and edittexts
    EditText editShopname;
    EditText editSum;
    EditText editDate;
    Button buttonRescan;
    Button buttonSave;
    // Katja 31.12

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

        //katja & Julia 05.01.2016

        //definition edittexts and buttons
        editShopname = (EditText)findViewById(R.id.editShop);
        editSum = (EditText)findViewById(R.id.editSum);
        editDate = (EditText)findViewById(R.id.editDate);
        buttonRescan = (Button)findViewById(R.id.buttonRescan);
        buttonSave = (Button)findViewById(R.id.buttonSave);
        //katja & Julia 05.01.2015

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
        //startActivity(new Intent(EditActivity.this, MainActivity.class));
        //startActivity(this.myController.screenFlowMain());

        //katja & julia 05.01.2015
        String shop = editShopname.getText().toString();
        double finalSum = Double.parseDouble(editSum.getText().toString());
        String date = editDate.getText().toString();
        String category = categorySpinner.getSelectedItem().toString();
        //check input dateformat
        if(StorageAdmin.EDITCONTROLLER.isValidDate(date)){
            //new receipt object created
            Receipt receipt = new Receipt(shop, category, finalSum, date);
            System.out.println("receipt ---> " + receipt);
            //add new receipt data in Database
            //if(StorageAdmin.DBCONTROLLER.createReceipt(receipt))
            if (StorageAdmin.DBCONTROLLER.createReceipt(receipt)) {
                Toast.makeText(EditActivity.this, "Receipt is saved", Toast.LENGTH_LONG).show();
                startActivity(StorageAdmin.EDITCONTROLLER.screenFlowMain());
            } else {
                Toast.makeText(EditActivity.this, "Database connection has failed!", Toast.LENGTH_LONG).show();
                return;
            }
        }
        else{
            Toast.makeText(EditActivity.this, "Invalid date input, use format (dd/MM/yyyy)", Toast.LENGTH_LONG).show();
            return;

        }
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
