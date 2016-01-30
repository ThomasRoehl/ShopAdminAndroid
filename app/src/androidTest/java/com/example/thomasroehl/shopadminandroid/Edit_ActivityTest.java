package com.example.thomasroehl.shopadminandroid;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.example.thomasroehl.shopadminandroid.gui.EditActivity;
import com.example.thomasroehl.shopadminandroid.gui.Login_Register_Activity;

import org.junit.Test;

/**
 * Created by SZC on 21.01.2016.
 */
public class Edit_ActivityTest extends ActivityInstrumentationTestCase2<EditActivity> {
    public Edit_ActivityTest() {
        super(EditActivity.class);
    }

    @Test
    public void testActivityExists() {
        EditActivity activity = getActivity();
        assertNotNull(activity);
    }

    @Test
    public void testInputShopname() {
        EditActivity activity = getActivity();

        final EditText editShopname = (EditText) activity.findViewById(R.id.editShop);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                editShopname.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("MyShop");
        getInstrumentation().waitForIdleSync();

        String testOne = activity.getShop().toString();

        assertEquals("MyShop", testOne);

        activity.setShop("MyShopTwo");

        String testTwo = activity.getShop().toString();

        assertEquals("MyShopTwo", testTwo);
    }

    @Test
    public void testInputSum() {
        EditActivity activity = getActivity();

        final EditText editSum = (EditText) activity.findViewById(R.id.editSum);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                editSum.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("18,48");
        getInstrumentation().waitForIdleSync();

        String testOne = activity.getSum().toString();

        assertEquals("18,48", testOne);

        activity.setSum("18,48");

        String testTwo = activity.getShop().toString();

        assertEquals("18,48", testTwo);
    }

    @Test
    public void testInputSetDate() {
        EditActivity activity = getActivity();

        final EditText editDate = (EditText) activity.findViewById(R.id.editDate);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                editDate.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("01.01.2016");
        getInstrumentation().waitForIdleSync();

        String testOne = activity.getDate().toString();

        assertEquals("01.01.2016", testOne);

        activity.setDate("02.01.2016");

        String testTwo = activity.getDate().toString();

        assertEquals("02.01.2016", testTwo);
    }

}
