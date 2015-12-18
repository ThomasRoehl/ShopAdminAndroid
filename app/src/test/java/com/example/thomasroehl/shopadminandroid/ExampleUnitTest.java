package com.example.thomasroehl.shopadminandroid;

import com.example.thomasroehl.shopadminandroid.container.ExampleContainer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    public ExampleContainer testObj;
    public int testObjAccNr;
    public String testObjName;

    @Before
    public void setUp(){
        testObjAccNr = 1234;
        testObjName = "tester";
        testObj = new ExampleContainer(testObjAccNr);
        testObj.setName(testObjName);
    }

    @Test
    public void equalsTest(){
        ExampleContainer eqObj = new ExampleContainer(1234);
        eqObj.setName(testObjName);
        assertEquals(eqObj, testObj);
    }

    @Test
    public void hashCodeTest(){
        assertEquals(testObj.hashCode(), testObjAccNr);
    }

    @Test
    public void toStringTest(){
        assertEquals(("name: " + testObjName + "\taccNr: " + testObjAccNr), testObj.toString());
    }

    @Test(expected = AssertionError.class)
    public void setNullTest(){
       ExampleContainer testObj2 = new ExampleContainer(1234);
       testObj2.setName(null);
    }

    @Test(expected = AssertionError.class)
    public void toStringNullTest(){
        ExampleContainer testObj2 = new ExampleContainer(1234);
        testObj2.toString();
    }

    @Test(expected = AssertionError.class)
    public void getNameNullTest(){
        ExampleContainer testObj2 = new ExampleContainer(1234);
        testObj2.getName();
    }
}