package com.example.thomasroehl.shopadminandroid.statics;

import android.app.Activity;

import com.example.thomasroehl.shopadminandroid.camera.CameraController;
import com.example.thomasroehl.shopadminandroid.camera.CameraControllerInterf;
import com.example.thomasroehl.shopadminandroid.container.Session;
import com.example.thomasroehl.shopadminandroid.container.User;
import com.example.thomasroehl.shopadminandroid.database.DatabaseController;
import com.example.thomasroehl.shopadminandroid.database.DatabaseInterf;
import com.example.thomasroehl.shopadminandroid.edit.EditControllerImpl;
import com.example.thomasroehl.shopadminandroid.edit.EditControllerInterf;
import com.example.thomasroehl.shopadminandroid.login.LoginControllerImpl;
import com.example.thomasroehl.shopadminandroid.login.LoginControllerInterf;
import com.example.thomasroehl.shopadminandroid.register.RegisterControllerImpl;
import com.example.thomasroehl.shopadminandroid.register.RegisterControllerInterf;
import com.example.thomasroehl.shopadminandroid.reports.ReportController;
import com.example.thomasroehl.shopadminandroid.reports.ReportControllerInterf;
import com.example.thomasroehl.shopadminandroid.startscreen.MainViewController;
import com.example.thomasroehl.shopadminandroid.startscreen.StartScreenControllerInterf;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas Roehl on 11.12.2015.
 */
public abstract class StorageAdmin {
    public static final String REPORT_SHOP_SUMMARY      = "REPORT_SHOP_SUMMARY";
    public static final String REPORT_CATEGORY_SUMMARY  = "REPORT_CATEGORY_SUMMARY";

    public static final DatabaseInterf DBCONTROLLER = new DatabaseController();
    public static final CameraControllerInterf CAMERACONTROLLER = CameraController.getInstance();
    public static EditControllerInterf EDITCONTROLLER = EditControllerImpl.getInstance();
    public static final LoginControllerInterf LOGINCONTROLLER =new  LoginControllerImpl();
    public static final RegisterControllerInterf REGISTERCONTROLLER = RegisterControllerImpl.getRegisterController();
    public static ReportControllerInterf REPORTCONTROLLER = ReportController.getInstance();
    public static final StartScreenControllerInterf STARTSCREENCONTROLLER = MainViewController.getInstance();
    private static Session session;

    private static List<Activity> _activities;   // for app exit (tanja)

    // for app exit (tanja)
    public static void register(Activity activity) {
        if(_activities == null) {
            _activities = new ArrayList<Activity>();
        }
        _activities.add(activity);
    }

    // for app exit (tanja)
    public static void finishAll() {
        for (Activity activity : _activities) {
            activity.finish();
        }
    }

    public static void newSession(User user){
        // Anhand des gegebenen Users eine Session anlegen
        session = new Session(user.getName(), user.getId(), user.hashCode()); //HashCode als ID

    }

    public static Session getSession(){
        return session;
    }


}
