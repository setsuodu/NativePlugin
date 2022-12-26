package com.moegijinka.noactivity;

//import androidx.appcompat.app.AppCompatActivity;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends UnityPlayerActivity {
    public static Activity currentActivity;
    private static final String TAG = "TimPlugin";
    private static String gameObjectName;
    public static void GetInstance(String _gameObjectName) {
        gameObjectName = _gameObjectName;
        System.out.println("GetInstance gameObjectName=" + gameObjectName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        currentActivity = MainActivity.this;
        System.out.println("MainActivity.onCreate:");
    }
    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("MainActivity.onPause: 切到后台");
    }
    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("MainActivity.onResume: 切回来激活");
        onListen();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("MainActivity.onDestroy: 销毁");
    }

    // 监听外部唤起本App
    void onListen() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String name = bundle.getString("name");
            String birthday = bundle.getString("birthday");
            if (name != null && birthday != null) {
                Toast.makeText(getApplicationContext(), "name:" + name + " birthday:" + birthday, Toast.LENGTH_SHORT).show();
            }
        }
    }
}