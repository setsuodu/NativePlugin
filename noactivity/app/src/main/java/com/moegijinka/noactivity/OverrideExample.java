package com.moegijinka.noactivity;

import com.unity3d.player.UnityPlayerActivity;
import android.os.Bundle;

public class OverrideExample extends UnityPlayerActivity {
    protected void onCreate(Bundle savedInstanceState) {
        // Calls UnityPlayerActivity.onCreate()
        super.onCreate(savedInstanceState);
        // Prints debug message to Logcat
        System.out.println("OverrideActivity.onCreate called!");
    }

    public void onBackPressed()
    {
        // Instead of calling UnityPlayerActivity.onBackPressed(), this example ignores the back button event
        // super.onBackPressed();
    }
}