package com.example.thomasroehl.shopadminandroid.startscreen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.thomasroehl.shopadminandroid.container.Receipt;
import com.example.thomasroehl.shopadminandroid.database.DatabaseInterf;
import com.example.thomasroehl.shopadminandroid.gui.CameraActivity;
import com.example.thomasroehl.shopadminandroid.gui.ReportPagerActivity;
import com.example.thomasroehl.shopadminandroid.statics.StorageAdmin;

import java.util.ArrayList;

/**
 * Created by SZC on 10.12.2015.
 * First try: MainViewController as Singleton -> Issue regarding Statics and SDK 16?
 */
public class MainViewController implements StartScreenControllerInterf {
    TableLayout tl;
    DatabaseInterf db;
    Context currentActivityContext;

    private final float ratioColumnShop = 0.5f;
    private final float ratioColumnAmount = 0.25f;
    private final float ratioColumnDate = 0.25f;

    private final String txt_Shop = "Shop";
    private final String txt_Amount = "Amount";
    private final String txt_Date = "Date";

    private static MainViewController instance = null;

    /**
     * Construktor
     */
    protected MainViewController() {
        this.tl = null;
        this.db = StorageAdmin.DBCONTROLLER;
        this.currentActivityContext = null;
    }

    /**
     * Returns the created instance of the controller
     *
     * @return Controller instance
     */
    public static MainViewController getInstance() {
        if (instance == null) {
            instance = new MainViewController();
        }
        return instance;
    }

    /**
     * Reference to the tableLayout from the activity
     *
     * @param tableLayout Reference on Views TableLayout
     */
    public void setTableLayout(TableLayout tableLayout) {
        this.tl = tableLayout;
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
    public Intent screenFlowScan() {
        Intent i = new Intent(
                this.currentActivityContext,
                CameraActivity.class);

        return i;
    }

    @Override
    public Intent screenFlowTable() {
        Intent i = new Intent(
                this.currentActivityContext,
                ReportPagerActivity.class);

        return i;
    }


    /**
     * Provides content for the TableLayout displayed on the MainActivity
     * Therefore following steps will be executed:
     * 1) Generate Header Row
     * 2) Read Data from Database for current user and current group
     * ReadData is Shop, Amount, Date -> preferably in descending order regarding data
     * 3) Close Database
     *
     * @param amount Specify the numbers of datasets to be read from the databaase
     */
    @Override
    public void getTable(int amount) {
        // 1) Generate Header Row
        this.addHeader();

        // 2) ReadData from Database
        /*
         * TODO: Lesen von Datenbank
         * Ich benoetige eine Methode um die Belegdaten von der Datenbank zu bekommen
         * Weiterhin benötige ich einen entsprechenden Container und ich muss der Datenbank
         * bestimmt auch den aktuellen Benutzer/Gruppe mitteilen
         * Diesen sollte ich auch bekommen
         */
        this.fillReceiptTable();
    }


    @Override
    public ArrayList<Receipt> getAllReceipts() {
        return StorageAdmin.DBCONTROLLER.getAllReceipts();
    }

    @Override
    public void fillReceiptTable() {
        ArrayList<Receipt> allReceipts = this.getAllReceipts();
        for (int i = 0; i < allReceipts.size(); i++) {

            TableRow row = new TableRow(this.currentActivityContext);

            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            TextView tv_shopname = new TextView(this.currentActivityContext);
            tv_shopname.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv_shopname.setText(allReceipts.get(i).getShopname());
            row.addView(tv_shopname);

            TextView tv_amount = new TextView(this.currentActivityContext);
            tv_amount.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv_amount.setText(String.valueOf(allReceipts.get(i).getAmount()));
            row.addView(tv_amount);

            TextView tv_date = new TextView(this.currentActivityContext);
            tv_date.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv_date.setText(allReceipts.get(i).getDate());
            row.addView(tv_date);

            tl.addView(row);
        }
    }

    /**
     * Generate Headerline for TableLayout
     */
    private void addHeader() {
        TableRow tr;
        int pixels = 0;

        int maxpixels_width = (int) (this.currentActivityContext.getResources().getDisplayMetrics().widthPixels * 0.9f);
        int maxpixels_height = (int) (this.currentActivityContext.getResources().getDisplayMetrics().heightPixels * 0.6f);


        int height_pixels = (int) maxpixels_height / 11;

        Log.i("MaxPixels (Width):", String.valueOf(maxpixels_width));
        Log.i("MaxPixels (Height):", String.valueOf(maxpixels_height));

        Log.i("Height per cell:", String.valueOf(height_pixels));

        float dpi_column_shopname = maxpixels_width * this.ratioColumnShop;
        float dpi_column_amount = maxpixels_width * this.ratioColumnAmount;
        float dpi_column_date = maxpixels_width * this.ratioColumnDate;

        //pixels = (int)(dpi_column_shopname * scaleRatio + 0.5f);
        pixels = (int) dpi_column_shopname;

        Log.i("Pixel ShopName:", String.valueOf(pixels));

        tr = new TableRow(this.currentActivityContext);

        /** Creating a TextView as Column Description for the Shopname **/
        TextView shopname = new TextView(this.currentActivityContext);

        shopname.setText(this.txt_Shop);
        //shopname.setTextSize(myTextSize);
        shopname.setWidth(pixels);
        shopname.setHeight(height_pixels);
        shopname.setTextColor(Color.WHITE);

        shopname.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        shopname.setPadding(5, 5, 5, 5);
        shopname.setBackgroundColor(Color.argb(255,249,125,123));
        LinearLayout Ll = new LinearLayout(this.currentActivityContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(5, 5, 5, 5);
        Ll.addView(shopname, params);
        tr.addView(Ll); // Adding textView to tablerow.

        /** Creating a TextView as Column Description for the Amount **/
        TextView amount = new TextView(this.currentActivityContext);
        amount.setText(this.txt_Amount);
        //pixels = (int)(dpi_column_amount * scaleRatio + 0.5f);
        pixels = (int) dpi_column_amount;
        Log.i("Pixel Amount:", String.valueOf(pixels));

        amount.setWidth(pixels);
        amount.setHeight(height_pixels);
        //amount.setTextSize(myTextSize);
        amount.setTextColor(Color.WHITE);
        amount.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        amount.setPadding(5, 5, 5, 5);
        amount.setBackgroundColor(Color.argb(255,249,125,123));
        Ll = new LinearLayout(this.currentActivityContext);
        params = new LinearLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 5, 5, 5);
        Ll.addView(amount, params);
        tr.addView(Ll); // Adding textview to tablerow.

        /** Creating a TextView as Column Description for the Amount **/
        TextView date = new TextView(this.currentActivityContext);
        date.setText(this.txt_Date);
        //date.setTextSize(myTextSize);
        //pixels = (int)(dpi_column_date * scaleRatio + 0.5f);

        pixels = (int) dpi_column_date;
        Log.i("Pixel Date:", String.valueOf(pixels));

        date.setWidth(pixels);
        date.setHeight(height_pixels);
        date.setTextColor(Color.WHITE);
        date.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        date.setPadding(5, 5, 5, 5);
        date.setBackgroundColor(Color.argb(255,249,125,123));
        Ll = new LinearLayout(this.currentActivityContext);
        params = new LinearLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 5, 5, 5);
        Ll.addView(date, params);
        tr.addView(Ll); // Adding textview to tablerow.

        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

    }
}
