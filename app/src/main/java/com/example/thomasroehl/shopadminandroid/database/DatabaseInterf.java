package com.example.thomasroehl.shopadminandroid.database;

import com.example.thomasroehl.shopadminandroid.container.User;

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





}
