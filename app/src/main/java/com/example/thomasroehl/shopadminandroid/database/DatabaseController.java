package com.example.thomasroehl.shopadminandroid.database;

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
     * @param username
     * @param password
     * @param email
     * @return return if user was created
     */
    @Override
    public boolean createUser(String username, String password, String email) {
        return false;
    }
}
