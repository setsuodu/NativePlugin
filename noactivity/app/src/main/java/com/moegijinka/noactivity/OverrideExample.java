package com.moegijinka.noactivity;

import com.unity3d.player.UnityPlayerActivity;

import android.app.Activity;
import android.os.Bundle;

public class OverrideExample extends UnityPlayerActivity {
    static Activity _activity;
    public static Activity getActivity() {
        return _activity;
    }

    protected void onCreate(Bundle savedInstanceState) {
        // Calls UnityPlayerActivity.onCreate()
        super.onCreate(savedInstanceState);
        // Prints debug message to Logcat
        System.out.println("OverrideExample.onCreate called!");
        _activity = this;
    }

    public void onBackPressed() {
        // Instead of calling UnityPlayerActivity.onBackPressed(), this example ignores the back button event
        // super.onBackPressed();
        System.out.println("OverrideExample.onBackPressed");
    }

    public boolean checkInstall(String packageName) {
//        String packageName = "com.moegijinka.gamecenter";
//        String packageName = "com.king.zxing.app";
        return AppBridge.checkInstall(getActivity(), packageName);
    }

    public void onJumpActivity(String packageName, String className) {
        System.out.println("OverrideExample.onJumpActivity: " + packageName);
        AppBridge.JumpFunc1(getActivity(), packageName, className); //√√√
//        AppBridge.JumpFunc2(getActivity(), packageName, className); //√√√
    }
}