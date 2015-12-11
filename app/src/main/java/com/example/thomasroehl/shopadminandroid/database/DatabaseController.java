package com.example.thomasroehl.shopadminandroid.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thomasroehl.shopadminandroid.container.User;

/**
 * Created by Thomas Roehl on 05.12.2015.
 */
public class DatabaseController implements DatabaseInterf {

    private final String USERTABLE =  "UserTable";
    private final String RECEIPTTABLE = "ReceiptTable";
    private final String USERNIDCOLUMN = "UserIdColumn";
    private final String USERNAMECOLUMN = "UserNameColumn";
    private final String USERPASSWORDCOLUMN = "UserPasswordColumn";
    private final String USEREMAILCOLUMN = "UserEmailColumn";


    private final String CHECKUSERBYNAME = "SELECT * FROM " + USERTABLE + " WHERE " + USERNAMECOLUMN + " = ";
    private final String CHECKUSERPASSWORD = "SELECT * FROM " + USERTABLE + " WHERE " + USERPASSWORDCOLUMN + " = ";


    private final String CHECKUSERBYID = "SELECT FROM ...";
    private final String INSERTUSER = "INSERT INTO ...";
    private SQLiteDatabase db;
    private final DatabaseModel DBModel;
    private Context applicationContext;


    public DatabaseController(){
        DBModel = new DatabaseModel(applicationContext);
    }

    private boolean connect(){
        db = DBModel.getWritableDatabase();
        if (db.isOpen()) return true;
        else return false;
    }

    private boolean isDisconnected(){
       return !db.isOpen();
    }

    private boolean disconnect(){
        db.close();
        if (db.isOpen()) return false;
        else return true;
    }

    /**
     * check weather username is equals parameter
     *
     * @param username
     * @return true if equals, else false
     */
    @Override
    public boolean checkUsername(String username) {
        connect();
        Cursor cursor = db.rawQuery(CHECKUSERBYNAME + username + ";", null);
        if (cursor.moveToFirst()) {
            disconnect();
            return true;
        }
        disconnect();
        return false;

    }

    /**
     * check weather password is equals parameter
     *
     * @param password
     * @return true if password is equals, else false
     */
    @Override
    public boolean checkPassword(String password) {
        connect();
        Cursor cursor = db.rawQuery(CHECKUSERPASSWORD + password + ";", null);
        if (cursor.moveToFirst()) {
            disconnect();
            return true;
        }
        disconnect();
        return false;
    }

    /**
     * create user in database with given entries
     *
     * @param user
     * @return return if user was created
     */
    @Override
    public boolean createUser(User user) {

        return false;
    }

}
