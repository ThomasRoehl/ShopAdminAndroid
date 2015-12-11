package com.example.thomasroehl.shopadminandroid.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.thomasroehl.shopadminandroid.container.User;

/**
 * Created by Thomas Roehl on 05.12.2015.
 */
public class DatabaseController implements DatabaseInterf {

    private final String CHECKUSERBYNAME = "SELECT FROM ...";
    private final String CHECKUSERBYID = "SELECT FROM ...";
    private final String INSERTUSER = "INSERT INTO ...";


    private boolean connect(){
        //TODO connect to database
        return false;
    }

    private boolean isDisconnected(){
        //TODO check if database is not connected
        return true;
    }

    private boolean disconnect(){
        //TODO disconnect database
        return false;
    }

    /**
     * check weather username is equals parameter
     *
     * @param username
     * @return true if equals, else false
     */
    @Override
    public boolean checkUsername(String username) {
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


    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    public void onCreate(SQLiteDatabase db) {

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
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
