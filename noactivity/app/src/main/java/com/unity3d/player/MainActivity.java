package com.moegijinka.moelibs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.unity3d.player.UnityPlayer;
//import com.unity3d.player.UnityPlayerActivity;

//public class MainActivity extends UnityPlayerActivity {
public class MainActivity extends Activity {
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
        Log.d(TAG, "MainActivity.onCreate: ");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG", "MainActivity.onPause: 切到后台");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG", "MainActivity.onResume: 切回来激活");
        onListen();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "MainActivity.onDestroy: 销毁");
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