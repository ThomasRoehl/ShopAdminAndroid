package com.example.thomasroehl.shopadminandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thomasroehl.shopadminandroid.container.Receipt;
import com.example.thomasroehl.shopadminandroid.container.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas Roehl on 05.12.2015.
 */


public class DatabaseController implements DatabaseInterf {

    // Table User
    public final String USERTABLE =  "UserTable";
    public final String USERNIDCOLUMN = "Id";
    public final String USERNAMECOLUMN = "Name";
    public final String USERPASSWORDCOLUMN = "Password";
    public final String USEREMAILCOLUMN = "Email";
    // Katia & Iuliia 19.01
    public final String USERLOGGEDCOLUMN = "Logged_In";


    // Table Receipt
    public final String RECEIPTTABLE = "ReceiptTable";
    // Katia & Iuliia 04.01
    public final String RECEIPTIDCOLUMN = "Receipt_Id";
    public final String SHOPNAMECOLUMN = "Shopname";
    public final String AMOUNTCOLUMN = "Amount";
    public final String CATEGORYCOLUMN = "Category";
    public final String DATECOLUMN = "Date";
    // Katia & Iuliia 19.01
    public final String USERIDCOLUMN = "User_Id";

    // Katia & Iuliia 17.01
    // Table Category
    public final String CATEGORYTABLE = "CategoryTable";
    public final String GENERALIDCOLUMN = "General_Id";
    public final String SHOPNAMECOLUMN2 = "Shopname";
    public final String CATEGORYCOLUMN2= "Category";

     // Database statements
    private final String CHECKUSERBYNAME = "SELECT " + USERNAMECOLUMN + " FROM " + USERTABLE + " WHERE " + USERNAMECOLUMN + " = ";
    private final String CHECKUSERPASSWORD = "SELECT " + USERPASSWORDCOLUMN + " FROM " + USERTABLE + " WHERE " + USERPASSWORDCOLUMN + " = ";
    private final String GETUSER = "SELECT * FROM " + USERTABLE + " WHERE " + USERNAMECOLUMN + " = ";
    private final String DELETEUSER = "DELETE FROM " + USERTABLE + " WHERE " + USERNAMECOLUMN + " = ";
    // Katia & Iuliia 04.01
    private final String CHECKPASSWORDBYNAME1 = "SELECT " + USERPASSWORDCOLUMN + " FROM " + USERTABLE + " WHERE " + USERPASSWORDCOLUMN + " = ";
    private final String CHECKPASSWORDBYNAME2 = " AND " + USERNAMECOLUMN + " = ";

    private final String GETRECEIPT1= "SELECT " + SHOPNAMECOLUMN + ", " + AMOUNTCOLUMN + ", " + CATEGORYCOLUMN + ", " + DATECOLUMN +", "+ USERIDCOLUMN + "  FROM " +  RECEIPTTABLE + " WHERE " + USERIDCOLUMN + " = ";
    private final String GETRECEIPT2= " ORDER BY " + RECEIPTIDCOLUMN + " DESC LIMIT 20";

    private final String GETRECEIPTGROUPBYNAME1 = "SELECT " + SHOPNAMECOLUMN + ", " + " SUM(" + AMOUNTCOLUMN + ")" + ", " + CATEGORYCOLUMN + ", " + DATECOLUMN + ", "+ USERIDCOLUMN +  " FROM " + RECEIPTTABLE + " WHERE " + USERIDCOLUMN + " = ";
    private final String GETRECEIPTGROUPBYNAME2 =" GROUP BY " + SHOPNAMECOLUMN;

    private final String GETRECEIPTGROUPBYCATEGORY1 = "SELECT " + CATEGORYCOLUMN + ", " + "SUM(" + AMOUNTCOLUMN + ")" +  ", " + SHOPNAMECOLUMN + ", " + DATECOLUMN + ", "+ USERIDCOLUMN + " FROM " + RECEIPTTABLE + " WHERE " + USERIDCOLUMN + " = ";
    private final String GETRECEIPTGROUPBYCATEGORY2 =" GROUP BY " + CATEGORYCOLUMN;

    private final String GETRECEIPTSBYSPECIALSHOPNAME1 = "SELECT " + SHOPNAMECOLUMN + ", " + AMOUNTCOLUMN + ", " + CATEGORYCOLUMN + ", " + DATECOLUMN + ", "+ USERIDCOLUMN + " FROM " + RECEIPTTABLE + " WHERE " + USERIDCOLUMN + " = ";
    private final String GETRECEIPTSBYSPECIALSHOPNAME2 = " AND " + SHOPNAMECOLUMN + " = ";

    private final String GETRECEIPTSBYSPECIALCATEGORY1 = "SELECT " + SHOPNAMECOLUMN + ", " + AMOUNTCOLUMN + ", " + CATEGORYCOLUMN + ", " + DATECOLUMN + ", "+ USERIDCOLUMN + " FROM " + RECEIPTTABLE + " WHERE " + USERIDCOLUMN + " = ";
    private final String GETRECEIPTSBYSPECIALCATEGORY2 = " AND " + CATEGORYCOLUMN + " = ";

    // SELECT Shopname, Amount, Category, date
   // FROM RECEIPTTable
    //WHERE Shopname = 'Rewe'
    //AND User_Id = 1

    // Database
    private SQLiteDatabase db;
    private  DatabaseModel DBModel;

    // DatabaseController
//    public DatabaseController(){
//        DBModel = new DatabaseModel(applicationContext);
//    }
//
//    // Katia & Iuliia 04.01
//    public DatabaseController(Context c){
//        applicationContext = c;
//        DBModel = new DatabaseModel(applicationContext);
//    }

    /**
     * connect database
     *
     * @return true if database is open, else false
     */
    private boolean connect(){
        try {
            db = DBModel.getWritableDatabase();
            if (db.isOpen()) return true;
            else return false;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * check database is close
     *
     * @return true if database is close, else false
     */
    private boolean isDisconnected(){

        try {
            return !db.isOpen();
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * close database connection
     *
     * @return true if database is close, else false
     */
    private boolean disconnect(){
        try {
            db.close();
            if (db.isOpen()) return false;
            else return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * check whether username is equals parameter
     *
     * @param username
     * @return true if equals, else false
     */
    @Override
    public boolean checkUsername(String username) {
        try {
            connect();
            Cursor cursor = db.rawQuery(CHECKUSERBYNAME + "'" + username + "'" + ";", null);
            if (cursor.moveToFirst()) {
                System.out.println("username exists");
                disconnect();
                return true;
            }
            else {
                disconnect();
                return false;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            if (!isDisconnected()) {
                disconnect();
            }
            return false;
        }
    }

    /**
     * check whether password is equals parameter
     *
     * @param password
     * @return true if password is equals, else false
     */
    @Override
    public boolean checkPassword(String password) {
        try {

            connect();
            Cursor cursor = db.rawQuery(CHECKUSERPASSWORD + password + ";", null);
            if (cursor.moveToFirst()) {
                disconnect();
                return true;
            }
            disconnect();
            return false;
        } catch (Exception e){
            e.printStackTrace();
            if (!isDisconnected()) {
                disconnect();
            }
            return false;
        }
    }

    // Katia & Iuliia 04.01
    /**
     * check whether password is equals parameter
     *
     * @param password, name
     * @return true whether name from database matches with password
     * */
    public boolean checkPasswordByName(String password, String name) {
        try {
            connect();
            System.out.println(CHECKPASSWORDBYNAME1 + password + CHECKPASSWORDBYNAME2 + name + ";");
            Cursor cursor = db.rawQuery(CHECKPASSWORDBYNAME1 + "'" + password + "'" + CHECKPASSWORDBYNAME2 + "'" + name + "'" + ";", null);
            if (cursor.moveToFirst()) {
                disconnect();
                return true;
            }
            disconnect();
            return false;
        } catch (Exception e){
            e.printStackTrace();
            if (!isDisconnected()) {
                disconnect();
            }
            return false;
        }
    }

    /**
     * create user in database with given entries
     *
     * @param user
     * @return return if user was created
     */
    @Override
    public boolean createUser(User user) {
        try {
            connect();

            // create ContentValues to add key "column"/value
            ContentValues values = new ContentValues();
            values.put(USERNAMECOLUMN, user.getName());
            values.put(USEREMAILCOLUMN, user.getEmail());
            values.put(USERPASSWORDCOLUMN, user.getPassword());

            //TODO --> true or false for Status
            values.put(USERLOGGEDCOLUMN, user.getLoggedIn());

            // insert
            db.insert(USERTABLE, null, values);
            disconnect();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            if (!isDisconnected()) {
                disconnect();
            }
            return false;
        }
    }

    /**
     * get user (username, e-mail, password) from table
     *
     * @param username
     * @return if exist user, else null
     */
     public User getUserFromTable(String username) {
        try {
            connect();
            Cursor cursor = db.rawQuery(GETUSER  + "'" +  username  + "'" + ";", null);
            User user = null;
            if (cursor.moveToFirst()) {
                do {
                    user = new User();
                    user.setId(Integer.parseInt(cursor.getString(0)));
                    user.setName(cursor.getString(1));
                    user.setEmail(cursor.getString(2));
                    user.setPassword(cursor.getString(3));
                    //Katia & Iuliia 19.01.2016
                    user.setLoggedIn(cursor.getString(4));
                } while (cursor.moveToNext());
            }
            disconnect();
            System.out.println("DatabaseController getUserFromTable, 'user' ---> " + user);
            return user;

        }
        catch (Exception e){
            e.printStackTrace();
            if (!isDisconnected()) {
                disconnect();
            }
            return null;
        }

    }

    /**
     * delete user (username, e-mail, password) from table
     *
     * @param username
     * @return if true, else false
     */
    public boolean deleteUser(String username) {
        try {
            connect();
            db.execSQL(DELETEUSER + "'" + username+ "'" + ";", null);
            disconnect();
            return  true;
        }
        catch (Exception e){
            e.printStackTrace();
            if (!isDisconnected()) {
                disconnect();
            }
            return false;
        }
    }

    // Katia & Iuliia 04.01
    /**
     * create receipt entry in database
     *
     * @param  receipt
     * @return return if receipt was created
     */
    @Override
    public boolean createReceipt(Receipt receipt) {
        try {
            connect();
            System.out.println("createReceipt() receipt ---> " + receipt);
            ContentValues values = new ContentValues();
            values.put(SHOPNAMECOLUMN, receipt.getShopname());
            values.put(AMOUNTCOLUMN, receipt.getAmount());
            values.put(CATEGORYCOLUMN, receipt.getCategory());
            values.put(DATECOLUMN, receipt.getDate());
            //neu
            values.put(USERIDCOLUMN, receipt.getuserId());

            db.insert(RECEIPTTABLE, null, values);

            System.out.println("Insert is done");



            ////HACK BEGIN
            System.out.println("---------------------------HACK------------------------------------");
            Cursor cursor = db.rawQuery("SELECT * FROM ReceiptTable", null);
            if (cursor.moveToFirst()) {
                do {
                    System.out.println(
                                    "cursor.getString(0) = " + cursor.getString(0) +
                                    " cursor.getString(1) = " + cursor.getString(1) +
                                    " cursor.getString(2) = " + cursor.getString(2) +
                                    " cursor.getString(3) = " + cursor.getString(3) +
                                    " cursor.getString(4) = " + cursor.getString(4) +
                                    " cursor.getString(5) = " + cursor.getString(5)
                    );
                } while (cursor.moveToNext());
            }
            System.out.println("----------------------------HACK-----------------------------------");
            //HACK END


                    disconnect();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            if (!isDisconnected()) {
                disconnect();
            }
            return false;
        }
    }

    /**
     * set Context for Database
     *
     * @param c
     * @return True if successful, else false
     */
    @Override
    public boolean setDBContext(Context c) {
        DBModel = new DatabaseModel(c);
        if (DBModel != null) return true;
        return false;
    }


    public ArrayList<Receipt> getAllReceipts(int id) {
        try {
            connect();
            ArrayList<Receipt> receiptList = new ArrayList<Receipt>();
            // Select All Query
            System.out.println("SELECT STATEMENT --->" + GETRECEIPT1 + id + GETRECEIPT2);
            Cursor cursor = db.rawQuery(GETRECEIPT1 + id + GETRECEIPT2 + ";", null);
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    //From here you just fill your receipt
                                               //String shopname, String category, double amount, String date

                    System.out.println("Receipt getAllReceipts ---> " + cursor.getString(0) + ", " +cursor.getString(2) +", "+
                            cursor.getDouble(1)+ ", "+ cursor.getString(3) + ", " + cursor.getInt(4));
                    receiptList.add(new Receipt(cursor.getString(0), cursor.getString(2), cursor.getDouble(1), cursor.getString(3), cursor.getInt(4)));
                } while (cursor.moveToNext());
            }// return contact list
            cursor.close();
            disconnect();
            return receiptList;
        }
        catch (Exception e){
            e.printStackTrace();
            if (!isDisconnected()) {
                disconnect();
            }
            return null;
        }
    }

    public ArrayList<Receipt> getAllReceiptsGroupByName(int id) {
        try {
            connect();
            ArrayList<Receipt> receiptList = new ArrayList<Receipt>();
            // Select All Query
            Cursor cursor = db.rawQuery(GETRECEIPTGROUPBYNAME1 + id + GETRECEIPTGROUPBYNAME2 + ";", null);
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    //From here you just fill your receipt
                    System.out.println("Receipt getAllReceiptsGroupByName ---> " + cursor.getString(0) + ", " +cursor.getString(2) +", "+
                            cursor.getDouble(1)+ ", "+ cursor.getString(3) + ", " + cursor.getInt(4));
                    receiptList.add(new Receipt(cursor.getString(0), cursor.getString(2), cursor.getDouble(1), cursor.getString(3),cursor.getInt(4)));
                } while (cursor.moveToNext());
            }
            cursor.close();
            disconnect();



            return receiptList;
        }
        catch (Exception e){
            e.printStackTrace();
            if (!isDisconnected()) {
                disconnect();
            }
            return null;
        }
    }

    public ArrayList<Receipt> getAllReceiptsGroupByCategory(int id) {
        try {
            connect();
            ArrayList<Receipt> receiptList = new ArrayList<Receipt>();
            // Select All Query
            Cursor cursor = db.rawQuery(GETRECEIPTGROUPBYCATEGORY1 + id + GETRECEIPTGROUPBYCATEGORY2 + ";", null);
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    System.out.println("Receipt getAllReceiptsGroupByCategory ---> " + cursor.getString(0) + ", " +cursor.getString(2) +", "+
                            cursor.getDouble(1)+ ", "+ cursor.getString(3) + ", " + cursor.getInt(4));
                    receiptList.add(new Receipt(cursor.getString(2), cursor.getString(0), cursor.getDouble(1), cursor.getString(3), cursor.getInt(4)));
                } while (cursor.moveToNext());
            }
            cursor.close();
            disconnect();
            return receiptList;
        }
        catch (Exception e){
            e.printStackTrace();
            if (!isDisconnected()) {
                disconnect();
            }
            return null;
        }
    }

    public ArrayList<Receipt> getReceiptsBySpecialShopname(int id, String shopname) {
        try {
            connect();
            ArrayList<Receipt> receiptList = new ArrayList<Receipt>();
            // Select All Query
            Cursor cursor = db.rawQuery(GETRECEIPTSBYSPECIALSHOPNAME1 + id + GETRECEIPTSBYSPECIALSHOPNAME2  + "'" + shopname  + "'" + ";", null);
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    System.out.println("Receipt getReceiptsBySpecialShopname ---> " + cursor.getString(0) + ", " +cursor.getString(2) +", "+
                            cursor.getDouble(1)+ ", "+ cursor.getString(3) + ", " + cursor.getInt(4));
                    //From here you just fill a receipt
                    receiptList.add(new Receipt(cursor.getString(0), cursor.getString(2), cursor.getDouble(1), cursor.getString(3), cursor.getInt(4)));
                } while (cursor.moveToNext());
            }// return contact list
            cursor.close();
            disconnect();
            return receiptList;
        }
        catch (Exception e){
            e.printStackTrace();
            if (!isDisconnected()) {
                disconnect();
            }
            return null;
        }
    }

    public ArrayList<Receipt> getReceiptsBySpecialCategory(int id, String category) {
        try {
            connect();
            ArrayList<Receipt> receiptList = new ArrayList<Receipt>();
            // Select All Query
            Cursor cursor = db.rawQuery(GETRECEIPTSBYSPECIALCATEGORY1 + id + GETRECEIPTSBYSPECIALCATEGORY2  + "'" + category  + "'" + ";", null);
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    System.out.println("Receipt getReceiptsBySpecialCategory ---> " + cursor.getString(0) + ", " +cursor.getString(2) +", "+
                            cursor.getDouble(1)+ ", "+ cursor.getString(3) + ", " + cursor.getInt(4));
                    //From here you just fill a receipt
                    receiptList.add(new Receipt(cursor.getString(0), cursor.getString(2), cursor.getDouble(1), cursor.getString(3), cursor.getInt(4)));
                } while (cursor.moveToNext());
            }// return contact list
            cursor.close();
            disconnect();
            return receiptList;
        }
        catch (Exception e){
            e.printStackTrace();
            if (!isDisconnected()) {
                disconnect();
            }
            return null;
        }
    }

}

