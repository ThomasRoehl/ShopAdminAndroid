package com.example.thomasroehl.shopadminandroid;

import android.test.ActivityInstrumentationTestCase2;

import com.example.thomasroehl.shopadminandroid.container.User;
import com.example.thomasroehl.shopadminandroid.gui.Login_Register_Activity;
import com.example.thomasroehl.shopadminandroid.register.RegisterControllerImpl;
import com.example.thomasroehl.shopadminandroid.statics.StorageAdmin;

import org.junit.Test;

/**
 * Created by SZC on 21.01.2016.
 */
public class Login_Register_ControllerTest extends ActivityInstrumentationTestCase2<Login_Register_Activity> {
    public Login_Register_ControllerTest() {
        super(Login_Register_Activity.class);
    }

    @Test
    public void testContollerExists() {
        Login_Register_Activity activity = getActivity();
        //get registerController Instance
        //registerController = RegisterControllerImpl.getRegisterController();
        RegisterControllerImpl registerController = (RegisterControllerImpl) StorageAdmin.REGISTERCONTROLLER;
        //set context
        registerController.setCurrentActivityContext(activity);

        assertNotNull(registerController);
    }

    @Test
    public void testUserDoesNotExist() {
        Login_Register_Activity activity = getActivity();
        //get registerController Instance
        //registerController = RegisterControllerImpl.getRegisterController();
        RegisterControllerImpl registerController = (RegisterControllerImpl) StorageAdmin.REGISTERCONTROLLER;
        //set context
        registerController.setCurrentActivityContext(activity);

        assertEquals(false, registerController.checkUsername("NotExisting"));

    }

    @Test
    public void testUserDoesExist() {
        User testUser = new User("Jake", "jake@test.de", "12345678");

        Login_Register_Activity activity = getActivity();
        //get registerController Instance
        //registerController = RegisterControllerImpl.getRegisterController();
        RegisterControllerImpl registerController = (RegisterControllerImpl) StorageAdmin.REGISTERCONTROLLER;
        //set context
        registerController.setCurrentActivityContext(activity);

        assertEquals(true, registerController.createUser(testUser));

        assertEquals(true, registerController.checkUsername("Jake"));

    }



}
