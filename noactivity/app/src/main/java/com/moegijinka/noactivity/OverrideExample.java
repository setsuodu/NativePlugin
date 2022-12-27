package com.moegijinka.noactivity;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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
        SendToUnity("GetInstance.gameObjectName");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume.监听外部传入参数:" + getActivity().getPackageName());


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String appName = bundle.getString("appName");
            String token = bundle.getString("token");
            System.out.println("bundle.appName:" + appName + ", bundle.token:" + token);
            if (appName != null && appName.isEmpty() == false) {
                System.out.println("收到来自" + appName + "的消息");
                SendToUnity(token);
            }
        } else {
            System.out.println("onResume.error: 没有bundle");
        }
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

    public void onJumpActivity(String packageName, String className, String token) {
        System.out.println("OverrideExample.onJumpActivity: " + packageName + ", " + className + ", " + token);
        AppBridge.JumpFunc1(getActivity(), packageName, className, token); //√√√
//        AppBridge.JumpFunc2(getActivity(), packageName, className); //√√√
    }

    public void SendToUnity(String message) {
        UnityPlayer.UnitySendMessage("Hook", "JavaToUnity", message);
    }
}