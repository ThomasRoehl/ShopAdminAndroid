package com.example.thomasroehl.shopadminandroid;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.example.thomasroehl.shopadminandroid.gui.Login_Register_Activity;
import com.example.thomasroehl.shopadminandroid.gui.MainActivity;

import org.junit.Test;

/**
 * Created by SZC on 21.01.2016.
 */
public class Login_Register_ActivityTest extends ActivityInstrumentationTestCase2<Login_Register_Activity> {
    public Login_Register_ActivityTest() {
        super(Login_Register_Activity.class);
    }

    @Test
    public void testActivityExists() {
        Login_Register_Activity activity = getActivity();
        assertNotNull(activity);
    }

    @Test
    public void testLogin() {
        Login_Register_Activity activity = getActivity();

        final EditText editTextUsername = (EditText) activity.findViewById(R.id.editTextUsername);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                editTextUsername.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("Jake");
        getInstrumentation().waitForIdleSync();

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                editTextUsername.requestFocus();
            }
        });

        final EditText editTextPassword = (EditText) activity.findViewById(R.id.editTextPassword);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                editTextPassword.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("1234");
        getInstrumentation().waitForIdleSync();

    }
}
