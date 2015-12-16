package com.example.thomasroehl.shopadminandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thomasroehl.shopadminandroid.container.User;

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

    // Table Receipt
    private final String RECEIPTTABLE = "ReceiptTable";

     // Database statements
    private final String CHECKUSERBYNAME = "SELECT " + USERNAMECOLUMN + " FROM " + USERTABLE + " WHERE " + USERNAMECOLUMN + " = ";
    private final String CHECKUSERPASSWORD = "SELECT " + USERPASSWORDCOLUMN + " FROM " + USERTABLE + " WHERE " + USERPASSWORDCOLUMN + " = ";
    private final String GETUSER = "SELECT * FROM " + USERTABLE + " WHERE " + USERNAMECOLUMN + " = ";
    private final String DELETEUSER = "DELETE FROM " + USERTABLE + " WHERE " + USERNAMECOLUMN + " = ";

    // Database
    private SQLiteDatabase db;
    private final DatabaseModel DBModel;
    private Context applicationContext;

    // DatabaseController
    public DatabaseController(){
        DBModel = new DatabaseModel(applicationContext);
    }


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
     * check weather username is equals parameter
     *
     * @param username
     * @return true if equals, else false
     */
    @Override
    public boolean checkUsername(String username) {
        try {
            connect();
            Cursor cursor = db.rawQuery(CHECKUSERBYNAME + username + ";", null);
            if (cursor.moveToFirst()) {
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
     * check weather password is equals parameter
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
            Cursor cursor = db.rawQuery(GETUSER + username + ";", null);
            User user = null;
            if (cursor.moveToFirst()) {
                do {
                    user = new User();
                    user.setId(Integer.parseInt(cursor.getString(0)));
                    user.setName(cursor.getString(1));
                    user.setEmail(cursor.getString(2));
                    user.setPassword(cursor.getString(3));
                } while (cursor.moveToNext());
            }
            disconnect();
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
            db.execSQL(DELETEUSER + username + ";", null);
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

}

