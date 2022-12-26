package com.moegijinka.noactivity;

import android.app.Activity;
import android.util.Log;
import java.util.Locale; //public final class Locale

public class TestClass {

    public void Test1() {
//        Log.d("Log.d: ", "Test1..."); //log.d无效
        System.out.println("println: Test111...");
    }

    public static void Test2() {
//        Log.d("Log.d: ", "Test2..."); //log.d无效
        System.out.println("println: Test222...");
    }

    public void Callback() {
//        UnityPlayer.UnitySendMessage("Activity","NativeCallback","Haha TestActivity!");
//        UnityPlayer.UnitySendMessage("gameobjectname", "methodname", "message");
    }

    static Activity _activity;

    public static void CallAndroid(Activity activity)
    {
        _activity = activity;
        System.out.println("#####CallAndroid() - Telemetry is fine.");

        //Continue doing what you need...
    }
}
