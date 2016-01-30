package com.example.thomasroehl.shopadminandroid;

import android.test.ActivityInstrumentationTestCase2;

import com.example.thomasroehl.shopadminandroid.container.Receipt;
import com.example.thomasroehl.shopadminandroid.container.User;
import com.example.thomasroehl.shopadminandroid.edit.EditControllerImpl;
import com.example.thomasroehl.shopadminandroid.gui.EditActivity;
import com.example.thomasroehl.shopadminandroid.gui.Login_Register_Activity;
import com.example.thomasroehl.shopadminandroid.register.RegisterControllerImpl;
import com.example.thomasroehl.shopadminandroid.statics.StorageAdmin;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by SZC on 21.01.2016.
 */
public class Edit_ControllerTest extends ActivityInstrumentationTestCase2<EditActivity> {
    public Edit_ControllerTest() {
        super(EditActivity.class);
    }

    @Test
    public void testContollerExists() {
        EditActivity activity = getActivity();
        //get registerController Instance
        //registerController = RegisterControllerImpl.getRegisterController();
        EditControllerImpl editController = (EditControllerImpl) StorageAdmin.EDITCONTROLLER;
        //set context
        editController.setCurrentActivityContext(activity);

        assertNotNull(editController);
    }

    @Test
    public void testisValidDate() {
        EditActivity activity = getActivity();

        EditControllerImpl editController = (EditControllerImpl) StorageAdmin.EDITCONTROLLER;
        //set context
        editController.setCurrentActivityContext(activity);

        assertEquals(true, editController.isValidDate("01.01.2016"));

    }

    @Test
    public void testisInvalidDate() {
        EditActivity activity = getActivity();

        EditControllerImpl editController = (EditControllerImpl) StorageAdmin.EDITCONTROLLER;
        //set context
        editController.setCurrentActivityContext(activity);

        assertEquals(false, editController.isValidDate("01/01/2016"));

    }

    @Test
    public void testSaveReceipt() {
        Receipt testReceipt = new Receipt("MyShop", "MyCategory", 18.48, "01.01.2016", 1848);

        EditActivity activity = getActivity();

        StorageAdmin.DBCONTROLLER.setDBContext(activity);

        EditControllerImpl editController = (EditControllerImpl) StorageAdmin.EDITCONTROLLER;
        //set context
        editController.setCurrentActivityContext(activity);

        assertEquals("Speichern des Beleges fehlgeschlagen", true, editController.saveData(testReceipt));

        ArrayList<Receipt> savedReceipts = StorageAdmin.DBCONTROLLER.getAllReceipts(1848);

        assertNotNull("Keine Belege gelesen", savedReceipts);

        Receipt savedReceipt = savedReceipts.get(1);

        assertEquals("Gelesener Beleg nicht gleich dem gespeicherten Beleg", testReceipt, savedReceipt);

    }

}
