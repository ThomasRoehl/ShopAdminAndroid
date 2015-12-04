package com.example.thomasroehl.shopadminandroid.database;

/**
 * Created by Thomas Roehl on 04.12.2015.
 */
public interface Database {
    public boolean checkUsername(String username);
    public boolean checkPassword(String password);
    public boolean createUser(String username, String password, String email);

}
