package com.example.thomasroehl.shopadminandroid.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.thomasroehl.shopadminandroid.statics.StorageAdmin;

import java.util.HashMap;

/**
 * Created by Thomas Roehl on 08.12.2015.
 */
public class DatabaseModel extends SQLiteOpenHelper{

    private static final int DBVERSION = 1;
    private static final String DBNAME = "ShopAdminDB";
    private DatabaseController dbController;
    private ReadXMLFile readXML;

    public DatabaseModel (Context ctx){
        super(ctx, DBNAME, null, DBVERSION);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbController = (DatabaseController)StorageAdmin.DBCONTROLLER;
        System.out.println("readXML = " +readXML);
        readXML = new ReadXMLFile();
        HashMap<String, String> entryDicionary = readXML.readXML();

        String query = "CREATE TABLE " + dbController.USERTABLE + "(" +
                dbController.USERNIDCOLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                dbController.USERNAMECOLUMN + " TEXT, " +
                dbController.USEREMAILCOLUMN + " TEXT, " +
                dbController.USERPASSWORDCOLUMN + " TEXT, " +
                dbController.USERLOGGEDCOLUMN + " TEXT );";
        db.execSQL(query);

        // Katia & Iuliia 04.01
        String queryReceipt = "CREATE TABLE " + dbController.RECEIPTTABLE + "(" +
                dbController.RECEIPTIDCOLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                dbController.SHOPNAMECOLUMN + " TEXT, " +
                dbController.AMOUNTCOLUMN + " DOUBLE, " +
                dbController.CATEGORYCOLUMN + " TEXT, " +
                dbController.DATECOLUMN + " TEXT, " +
                dbController.USERIDCOLUMN + " INTEGER NOT NULL );";
        db.execSQL(queryReceipt);
        // Katia & Iuliia 04.01

        // Katia & Iuliia 17.01
        String queryCategory= "CREATE TABLE " + dbController.CATEGORYTABLE + "(" +
                dbController.GENERALIDCOLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                dbController.SHOPNAMECOLUMN2 + " TEXT, " +
                dbController.CATEGORYCOLUMN2 + " TEXT );";
        db.execSQL(queryCategory);


        for (HashMap.Entry<String, String> entry : entryDicionary.entrySet()) {
            System.out.println("In for-loop Hashmap START");
            String key = entry.getKey();
            String value = entry.getValue();
            String insertQueryShopname = "INSERT INTO " + dbController.CATEGORYTABLE +
                    "(" + dbController.SHOPNAMECOLUMN2 + ", " + dbController.CATEGORYCOLUMN2 + ")" +
                    " VALUES (" + "'" + key + "'" + ", " + "'" +value + "'" +");";

            db.execSQL(insertQueryShopname);
        }
        System.out.println("Successfull");
    }



    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p/>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.err.println("DATABASE UPGRADE: old Version: " + oldVersion + ", new Version: " + newVersion);
    }
}
