package com.example.thomasroehl.shopadminandroid.statics;

import com.example.thomasroehl.shopadminandroid.camera.CameraController;
import com.example.thomasroehl.shopadminandroid.camera.CameraControllerInterf;
import com.example.thomasroehl.shopadminandroid.container.Session;
import com.example.thomasroehl.shopadminandroid.container.User;
import com.example.thomasroehl.shopadminandroid.database.DatabaseController;
import com.example.thomasroehl.shopadminandroid.database.DatabaseInterf;
import com.example.thomasroehl.shopadminandroid.edit.EditControllerInterf;
import com.example.thomasroehl.shopadminandroid.login.LoginControllerInterf;
import com.example.thomasroehl.shopadminandroid.register.RegisterControllerImpl;
import com.example.thomasroehl.shopadminandroid.register.RegisterControllerInterf;
import com.example.thomasroehl.shopadminandroid.reports.ReportControllerInterf;
import com.example.thomasroehl.shopadminandroid.startscreen.MainViewController;
import com.example.thomasroehl.shopadminandroid.startscreen.StartScreenControllerInterf;

/**
 * Created by Thomas Roehl on 11.12.2015.
 */
public abstract class StorageAdmin {
    public static final DatabaseInterf DBCONTROLLER = new DatabaseController();
    public static final CameraControllerInterf CAMERACONTROLLER = CameraController.getInstance();
    public static EditControllerInterf EDITCONTROLLER;
    public static LoginControllerInterf LOGINCONTROLLER;
    public static final RegisterControllerInterf REGISTERCONTROLLER = new RegisterControllerImpl();
    public static ReportControllerInterf REPORTCONTROLLER;
    public static final StartScreenControllerInterf STARTSCREENCONTROLLER = MainViewController.getInstance();
    private static Session session;

    public static void newSession(User user){

    }

    public static Session getSession(){
        return session;
    }
}