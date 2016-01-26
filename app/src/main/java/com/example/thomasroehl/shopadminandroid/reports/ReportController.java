package com.example.thomasroehl.shopadminandroid.reports;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.thomasroehl.shopadminandroid.container.Receipt;
import com.example.thomasroehl.shopadminandroid.database.DatabaseInterf;
import com.example.thomasroehl.shopadminandroid.gui.MainActivity;
import com.example.thomasroehl.shopadminandroid.statics.StorageAdmin;

import java.util.ArrayList;

/**
 * Created by SZC on 12.12.2015.
 */
public class ReportController implements ReportControllerInterf {
    DatabaseInterf db;
    Context currentActivityContext;
    TableLayout ShopSummary = null;
    TableLayout CategorySummary = null;

    ArrayList<Receipt> receiptsByName = new ArrayList<Receipt>();

    ArrayList<Receipt> receiptsByCategory = new ArrayList<Receipt>();

    private final float ratioColumnShop     = 0.6f;
    private final float ratioColumnCategory = 0.6f;
    private final float ratioColumnAmount   = 0.4f;

    //private final float ratioColumnDate     = 0.25f;

    private final String txt_Shop = "Shop";
    private final String txt_Category = "Category";
    private final String txt_Amount = "Amount";
    private final String txt_Date = "Date";

    private static ReportController instance = null;

    /**
     * Construktor
     */
    protected ReportController() {
        this.db = StorageAdmin.DBCONTROLLER;
        this.currentActivityContext = null;
    }

    /**
     * Returns the created instance of the controller
     *
     * @return Controller instance
     */
    public static ReportController getInstance() {
        if (instance == null) {
            instance = new ReportController();
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

    public Intent screenFlowMain() {
        //Wozu eigentlich? Geht über Back-Button
        Intent i = new Intent(
                this.currentActivityContext,
                MainActivity.class);

        return i;
    }

    public boolean setFilter(String filter) {
        return false;
    }

    public void getTable(String name, TableLayout tl) {
        switch (name) {
            case StorageAdmin.REPORT_SHOP_SUMMARY:
                this.ShopSummary = tl;
                getShop_Summary();
                break;
            case StorageAdmin.REPORT_CATEGORY_SUMMARY:
                this.CategorySummary = tl;
                getCategory_Summary();
                break;
            default:
                break;

        }

    }

    private void getShop_Summary() {
        System.out.println("ReportController getShop_Summary");
        addHeader(StorageAdmin.REPORT_SHOP_SUMMARY);

        this.fillReceiptTableByName();
    }

    @Override
    public ArrayList<Receipt> getAllReceiptGroupByName(int id) {
        return StorageAdmin.DBCONTROLLER.getAllReceiptsGroupByName(id);
    }

    @Override
    public ArrayList<Receipt> getAllReceiptGroupByCategory(int id) {
        return StorageAdmin.DBCONTROLLER.getAllReceiptsGroupByCategory(id);
    }

    @Override
    public ArrayList<Receipt> getReceiptsBySpecialShopname(int id, String shopname) {
        return StorageAdmin.DBCONTROLLER.getReceiptsBySpecialShopname(id, shopname);
    }

    @Override
    public void fillReceiptTableByName() {
        int id = 1;
        receiptsByName = this.getAllReceiptGroupByName(id);
        for (int i = 0; i < receiptsByName.size(); i++) {
            TableRow row = new TableRow(this.currentActivityContext);

            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            TextView tv_shopname = new TextView(this.currentActivityContext);
            tv_shopname.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv_shopname.setText(receiptsByName.get(i).getShopname());
            row.addView(tv_shopname);

            TextView tv_amount = new TextView(this.currentActivityContext);
            tv_amount.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv_amount.setText(String.valueOf(receiptsByName.get(i).getAmount()));
            row.addView(tv_amount);

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("xxxxxxxxx ReportController fillReceiptTableByName row onClick xxxxxxxxx");
                    TableRow t = (TableRow) view;
                    TextView firstTextView = (TextView) t.getChildAt(0);
                    firstTextView.setTextColor(Color.BLACK);
                    firstTextView.setTypeface(null, Typeface.BOLD);
                    TextView secondTextView = (TextView) t.getChildAt(1);
                    secondTextView.setTextColor(Color.BLACK);
                    secondTextView.setTypeface(null, Typeface.BOLD);

                    String shopname = firstTextView.getText().toString();
                    String amount = secondTextView.getText().toString();
                    System.out.println("ReportController fillReceiptTableByName row onClick firstText ---> " + shopname);
                    System.out.println("ReportController fillReceiptTableByName row onClick secondText ---> " + amount);
                    int nIndex = ShopSummary.indexOfChild(t);
                    System.out.println("nIndex ------> " + nIndex);
                    showExpandedShoptable(shopname, nIndex + 1);
                }
            });

            this.ShopSummary.addView(row);
        }
    }

    private void getCategory_Summary() {

        addHeader(StorageAdmin.REPORT_CATEGORY_SUMMARY);
        this.fillReceiptTableByCategory();
    }

    @Override
    public void fillReceiptTableByCategory() {
        int id = 1;
        receiptsByCategory = this.getAllReceiptGroupByCategory(StorageAdmin.getSession().getUserID());
        for (int i = 0; i < receiptsByCategory.size(); i++) {
            String testGetCategory = receiptsByCategory.get(i).getCategory();
            System.out.println("testGetCategory ----> " + testGetCategory);

            TableRow row = new TableRow(this.currentActivityContext);

            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            TextView tv_category = new TextView(this.currentActivityContext);
            tv_category.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv_category.setText(receiptsByCategory.get(i).getCategory());
            row.addView(tv_category);

            TextView tv_amount = new TextView(this.currentActivityContext);
            tv_amount.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv_amount.setText(String.valueOf(receiptsByCategory.get(i).getAmount()));
            row.addView(tv_amount);

            this.CategorySummary.addView(row);

        }
    }

    public void showExpandedShoptable(String shopName, int index){

        ArrayList<Receipt> receiptsBySpecialName = this.getReceiptsBySpecialShopname(StorageAdmin.getSession().getUserID(),shopName);
        System.out.println("ReportController showExpandedShoptable receiptsBySpecialName = " + receiptsBySpecialName);


        System.out.println("ReportController showExpandedShoptable ShopSummary.getChildCount()= " + this.ShopSummary.getChildCount());

        for (int i = 0; i < receiptsByName.size(); i++) {
            if(receiptsByName.get(i).getShopname() == shopName){
              /* TableRow row = new TableRow(this.currentActivityContext);

                row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                TextView tv_shopname = new TextView(this.currentActivityContext);
                tv_shopname.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv_shopname.setText(receiptsByName.get(i).getShopname());
                row.addView(tv_shopname);

                TextView tv_amount = new TextView(this.currentActivityContext);
                tv_amount.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv_amount.setText(String.valueOf(receiptsByName.get(i).getAmount()));
                row.addView(tv_amount);

                this.ShopSummary.addView(row);*/
                for (int j = 0; j < receiptsBySpecialName.size(); j++){
                    TableRow rowExpanded = new TableRow(this.currentActivityContext);

                    rowExpanded.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT));

                    TextView tv_shopnameExpanded = new TextView(this.currentActivityContext);
                    tv_shopnameExpanded.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    tv_shopnameExpanded.setText(receiptsBySpecialName.get(j).getShopname());
                    rowExpanded.addView(tv_shopnameExpanded);

                    TextView tv_amountExpanded = new TextView(this.currentActivityContext);
                    tv_amountExpanded.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    tv_amountExpanded.setText(String.valueOf(receiptsBySpecialName.get(j).getAmount()));
                    rowExpanded.addView(tv_amountExpanded);

                    TextView tv_dateExpanded = new TextView(this.currentActivityContext);
                    tv_dateExpanded.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    tv_dateExpanded.setText(String.valueOf(receiptsBySpecialName.get(j).getDate()));
                    rowExpanded.addView(tv_dateExpanded);

                    this.ShopSummary.addView(rowExpanded, index);
                }
            }
        }
    }
    /**
     * Generate Headerline for TableLayout
     */
    private void addHeader(String Name) {
        TableRow tr;
        int pixels = 0;

        TableLayout tl = null;

        int maxpixels_width = (int) (this.currentActivityContext.getResources().getDisplayMetrics().widthPixels * 0.9f);
        int maxpixels_height = (int) (this.currentActivityContext.getResources().getDisplayMetrics().heightPixels * 0.6f);

        LinearLayout Ll;
        LinearLayout.LayoutParams params;

        int height_pixels = (int) maxpixels_height / 11;

        Log.i("MaxPixels (Width):", String.valueOf(maxpixels_width));
        Log.i("MaxPixels (Height):", String.valueOf(maxpixels_height));

        Log.i("Height per cell:", String.valueOf(height_pixels));

        if (Name == StorageAdmin.REPORT_SHOP_SUMMARY) {
            tl = this.ShopSummary;
            float dpi_column_shopname = maxpixels_width * this.ratioColumnShop;

            //pixels = (int)(dpi_column_shopname * scaleRatio + 0.5f);
            pixels = (int)dpi_column_shopname;

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
            Ll = new LinearLayout(this.currentActivityContext);
            params = new LinearLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 5, 5, 5);
            Ll.addView(shopname,params);
            tr.addView(Ll); // Adding textView to tablerow.

        } else {
            tl = this.CategorySummary;
            float dpi_column_category = maxpixels_width * this.ratioColumnCategory;

            //pixels = (int)(dpi_column_shopname * scaleRatio + 0.5f);
            pixels = (int)dpi_column_category;

            Log.i("Pixel Category:", String.valueOf(pixels));

            tr = new TableRow(this.currentActivityContext);

            /** Creating a TextView as Column Description for the Shopname **/
            TextView category = new TextView(this.currentActivityContext);

            category.setText(this.txt_Category);
            //shopname.setTextSize(myTextSize);
            category.setWidth(pixels);
            category.setHeight(height_pixels);
            category.setTextColor(Color.WHITE);

            category.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            category.setPadding(5, 5, 5, 5);
            category.setBackgroundColor(Color.argb(255, 249, 125, 123));
            Ll = new LinearLayout(this.currentActivityContext);
            params = new LinearLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 5, 5, 5);
            Ll.addView(category,params);
            tr.addView(Ll); // Adding textView to tablerow.

        }
        float dpi_column_amount = maxpixels_width * this.ratioColumnAmount;
       //float dpi_column_date = maxpixels_width * this.ratioColumnDate;


        /** Creating a TextView as Column Description for the Amount **/
        TextView amount = new TextView(this.currentActivityContext);
        amount.setText(this.txt_Amount);
        //pixels = (int)(dpi_column_amount * scaleRatio + 0.5f);
        pixels = (int)dpi_column_amount;
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
        Ll.addView(amount,params);
        tr.addView(Ll); // Adding textview to tablerow.


        /** Creating a TextView as Column Description for the Amount **/
       /* TextView date = new TextView(this.currentActivityContext);
        date.setText(this.txt_Date);
        //date.setTextSize(myTextSize);
        //pixels = (int)(dpi_column_date * scaleRatio + 0.5f);

        pixels = (int)dpi_column_date;
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
        Ll.addView(date,params);
        tr.addView(Ll); // Adding textview to tablerow.
        */

        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));


    }


}
