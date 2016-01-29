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
    public ArrayList<Receipt> getReceiptsBySpecialCategory(int id, String category) {
        return StorageAdmin.DBCONTROLLER.getReceiptsBySpecialCategory(id, category);
    }

    @Override
    public void fillReceiptTableByName() {
        receiptsByName = this.getAllReceiptGroupByName(StorageAdmin.getSession().getUserID());
        for (int i = 0; i < receiptsByName.size(); i++) {
            TableRow row = new TableRow(this.currentActivityContext);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            this.addColumnToRow(row, i, receiptsByName, "shop");
            this.addColumnToRow(row, i, receiptsByName, "amount");
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TableRow t = (TableRow) view;
                    TextView firstTextView = (TextView) t.getChildAt(0);
                    firstTextView.setTypeface(null, Typeface.BOLD);
                    TextView secondTextView = (TextView) t.getChildAt(1);
                    secondTextView.setTypeface(null, Typeface.BOLD);
                    int nIndex = ShopSummary.indexOfChild(t);
                    System.out.println("nIndex ------> " + nIndex);
                    showExpandedShoptable(firstTextView.getText().toString(), nIndex + 1);
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
        receiptsByCategory = this.getAllReceiptGroupByCategory(StorageAdmin.getSession().getUserID());
        for (int i = 0; i < receiptsByCategory.size(); i++) {
            TableRow row = new TableRow(this.currentActivityContext);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            this.addColumnToRow(row, i, receiptsByCategory, "category");
            this.addColumnToRow(row, i, receiptsByCategory, "amount");
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TableRow t = (TableRow) view;
                    TextView firstTextView = (TextView) t.getChildAt(0);
                    firstTextView.setTypeface(null, Typeface.BOLD);
                    TextView secondTextView = (TextView) t.getChildAt(1);
                    secondTextView.setTypeface(null, Typeface.BOLD);
                    int nIndex = CategorySummary.indexOfChild(t);
                    showExpandedCategorytable(firstTextView.getText().toString(), nIndex + 1);

                }
            });
            this.CategorySummary.addView(row);
        }
    }

    public ArrayList<String> expandedCategories = new ArrayList<String>();
    private ArrayList<TableRow> expandedCategoryTableRows = new ArrayList<TableRow>();
    private void showExpandedCategorytable(String category, int index) {
        ArrayList<Receipt> receiptsBySpecialCategory = this.getReceiptsBySpecialCategory(StorageAdmin.getSession().getUserID(), category);
        System.out.println("ReportController showExpandedCategorytable receiptsBySpecialCategory ---> "+ receiptsBySpecialCategory);

        if(!expandedCategories.contains(category)){
            System.out.println("ReportController showExpandedCategorytable 'must add rows now'");
            System.out.println("ReportController showExpandedCategorytable 'receiptsByCategory.size()' = " + receiptsByCategory.size());
            for (int i = 0; i < receiptsByCategory.size(); i++) {
                if(receiptsByCategory.get(i).getCategory() == category){
                    System.out.println("ReportController showExpandedCategorytable, adding rows for 'category' = " + category);
                    for (int j = 0; j < receiptsBySpecialCategory.size(); j++) {
                        TableRow rowExpanded = new TableRow(this.currentActivityContext);
                        rowExpanded.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        this.addColumnToRow(rowExpanded, j, receiptsBySpecialCategory, "shop");
                        this.addColumnToRow(rowExpanded, j, receiptsBySpecialCategory, "amount");
                        this.addColumnToRow(rowExpanded, j, receiptsBySpecialCategory, "date");
                        this.expandedCategoryTableRows.add(rowExpanded);
                        this.CategorySummary.addView(rowExpanded, index);
                    }
                }
            }
            expandedCategories.add(category);
        }else if (expandedCategories.contains(category)){
            System.out.println("ReportController showExpandedCategorytable, deleting rows for 'category' = " + category);
            for (int i = 0; i < expandedCategoryTableRows.size(); i++){
                this.CategorySummary.removeView(expandedCategoryTableRows.get(i));
            }
            expandedCategories.remove(category);
        }
    }


    public ArrayList<String> expandedShopNames = new ArrayList<String>();
    private ArrayList<TableRow> expandedTableRows = new ArrayList<TableRow>();
    private void showExpandedShoptable(String shopName, int index){
        ArrayList<Receipt> receiptsBySpecialName = this.getReceiptsBySpecialShopname(StorageAdmin.getSession().getUserID(),shopName);
        if(!expandedShopNames.contains(shopName)){
            for (int i = 0; i < receiptsByName.size(); i++) {
                if(receiptsByName.get(i).getShopname() == shopName){
                    for (int j = 0; j < receiptsBySpecialName.size(); j++) {
                        TableRow rowExpanded = new TableRow(this.currentActivityContext);
                        rowExpanded.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        this.addColumnToRow(rowExpanded, j, receiptsBySpecialName, "shop");
                        this.addColumnToRow(rowExpanded, j, receiptsBySpecialName, "amount");
                        this.addColumnToRow(rowExpanded, j, receiptsBySpecialName, "date");
                        this.expandedTableRows.add(rowExpanded);
                        this.ShopSummary.addView(rowExpanded, index);
                    }
                }
            }
            expandedShopNames.add(shopName);
        }else if (expandedShopNames.contains(shopName)){
            for (int i = 0; i < expandedTableRows.size(); i++){
                this.ShopSummary.removeView(expandedTableRows.get(i));
            }
            expandedShopNames.remove(shopName);
       }
    }

    private void addColumnToRow(TableRow rowExpanded, int position, ArrayList<Receipt> receiptsByNameOrCategory, String columnName){
        TextView tv = new TextView(this.currentActivityContext);
        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        if(columnName.equals("shop")){
            tv.setText(receiptsByNameOrCategory.get(position).getShopname());
        }else if(columnName.equals("amount")){
            tv.setText(String.valueOf(receiptsByNameOrCategory.get(position).getAmount()));
        }else if(columnName.equals("date")) {
            tv.setText(String.valueOf(receiptsByNameOrCategory.get(position).getDate()));
        }else if(columnName.equals("category")){
            tv.setText(receiptsByCategory.get(position).getCategory());
        }
        rowExpanded.addView(tv);
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
