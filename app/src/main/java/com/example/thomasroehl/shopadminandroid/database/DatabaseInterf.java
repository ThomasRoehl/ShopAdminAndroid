package com.example.thomasroehl.shopadminandroid.database;

/**
 * Created by Thomas Roehl on 04.12.2015.
 */
public interface DatabaseInterf {

    /**
     * check weather username is equals parameter
     * @param username
     * @return true if equals, else false
     */
    public boolean checkUsername(String username);

    /**
     * check weather password is equals parameter
     * @param password
     * @return true if password is equals, else false
     */
    public boolean checkPassword(String password);

    /**
     * create user in database with given entries
     * @param username
     * @param password
     * @param email
     * @return return if user was created
     */
    public boolean createUser(String username, String password, String email);

}
