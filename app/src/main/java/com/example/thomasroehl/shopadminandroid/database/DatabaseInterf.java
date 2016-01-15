package com.example.thomasroehl.shopadminandroid.database;

import android.content.Context;

import com.example.thomasroehl.shopadminandroid.container.Receipt;
import com.example.thomasroehl.shopadminandroid.container.User;

import java.util.ArrayList;

/**
 * Created by Thomas Roehl on 04.12.2015.
 */
public interface DatabaseInterf {


    /**
     * check whether username is equals parameter
     * @param username
     * @return true if equals, else false
     */
    public boolean checkUsername(String username);


    /**
     * check whether password is equals parameter
     * @param password
     * @return true if password is equals, else false
     */
    public boolean checkPassword(String password);


    /**
     * create user in database with given entries
     * @param user
     * @return return if user was created
     */
    public boolean createUser(User user);


    /**
     * delete user (username, e-mail, password) from table
     *
     * @param username
     * @return if true, else false
     */
    public boolean deleteUser(String username);


    /**
     * get user (username, e-mail, password) from table
     *
     * @param username
     * @return if exist user, else null
     */
    public User getUserFromTable(String username);

    /**
     * create receipt entry in database
     *
     * @param  receipt
     * @return return if receipt was created
     */
    public boolean createReceipt(Receipt receipt);

    /**
     * set Context for Database
     * @param c
     * @return True if successful, else false
     */
    public boolean setDBContext(Context c);

    public boolean checkPasswordByName(String password, String name);

    public ArrayList<Receipt> getAllReceipts();

}
