package com.example.thomasroehl.shopadminandroid.startscreen;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.thomasroehl.shopadminandroid.database.DatabaseController;

/**
 * Created by SZC on 10.12.2015.
 * First try: MainViewController as Singleton -> Issue regarding Statics and SDK 16?
 */
public class MainViewController implements StartScreenControllerInterf {
    TableLayout tl;
    DatabaseController db;
    Context currentActivityContext;

    private final String txt_Shop = "Shop";
    private final String txt_Amount = "Amount";
    private final String txt_Date = "Date";

    private static MainViewController instance = null;

    /**
     * Construktor
     */
    protected MainViewController() {
        this.tl = null;
        this.db = new DatabaseController();
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
    public String screenFlowScan() {
        return null;
    }

    @Override
    public String screenFlowTable() {
        return null;
    }


    /**
     * Provides content for the TableLayout displayed on the MainActivity
     * Therefore following steps will be executed:
     * 1) Generate Header Row
     * 2) Read Data from Database for current user and current group
     *  ReadData is Shop, Amount, Date -> preferably in descending order regarding data
     * 3) Close Database
     *
     * @param amount Specify the numbers of datasets to be read from the databaase
     */
    @Override
    public Object getTable(int amount) {
        // 1) Generate Header Row
        addHeader();

        // 2) ReadData from Database
    /*
         * TODO: Lesen von Datenbank
         * Ich benoetige eine Methode um die Belegdaten von der Datenbank zu bekommen
         * Weiterhin benötige ich einen entsprechenden Container und ich muss der Datenbank
         * bestimmt auch den aktuellen Benutzer/Gruppe mitteilen
         * Diesen sollte ich auch bekommen
         */


        return null;
    }


    /**
     * Generate Headerline for TableLayout
     */
    private void addHeader() {
        TableRow tr;
        TextView label;

        tr = new TableRow(this.currentActivityContext);

        /** Creating a TextView as Column Description for the Shopname **/
        label = new TextView(this.currentActivityContext);

        label.setText(this.txt_Shop);
        label.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        label.setPadding(5, 5, 5, 5);
        label.setBackgroundColor(Color.MAGENTA);
        LinearLayout Ll = new LinearLayout(this.currentActivityContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(5, 5, 5, 5);
        Ll.addView(label,params);
        tr.addView(Ll); // Adding textView to tablerow.

        /** Creating a TextView as Column Description for the Amount **/
        TextView amount = new TextView(this.currentActivityContext);
        amount.setText(this.txt_Amount);
        amount.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        amount.setPadding(5, 5, 5, 5);
        amount.setBackgroundColor(Color.MAGENTA);
        Ll = new LinearLayout(this.currentActivityContext);
        params = new LinearLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 5, 5, 5);
        Ll.addView(amount,params);
        tr.addView(Ll); // Adding textview to tablerow.

        /** Creating a TextView as Column Description for the Amount **/
        TextView date = new TextView(this.currentActivityContext);
        date.setText(this.txt_Date);
        date.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        date.setPadding(5, 5, 5, 5);
        date.setBackgroundColor(Color.MAGENTA);
        Ll = new LinearLayout(this.currentActivityContext);
        params = new LinearLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 5, 5, 5);
        Ll.addView(date,params);
        tr.addView(Ll); // Adding textview to tablerow.

        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

    }
}
