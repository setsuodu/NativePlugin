package com.moegijinka.noactivity;

import android.util.Log;

public class TestClass {

    public void Test1() {
//        Log.d("Log.d: ", "Test1..."); //log.d无效
        System.out.println("println: Test1...");
    }

    public static void Test2() {
//        Log.d("Log.d: ", "Test2..."); //log.d无效
        System.out.println("println: Test2...");
    }

    public void Callback() {
//        UnityPlayer.UnitySendMessage("Activity","NativeCallback","Haha TestActivity!");
//        UnityPlayer.UnitySendMessage("gameobjectname", "methodname", "message");
    }
}
