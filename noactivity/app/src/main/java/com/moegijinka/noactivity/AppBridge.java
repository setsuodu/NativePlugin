package com.moegijinka.noactivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

public class AppBridge {

    // 检查app是否安装
    public static Boolean checkInstall(Context context, String packgeName) {
        if(isApkInstalled(context, packgeName)){
            System.out.println(packgeName + "，已安装");
            return true;
        }else {
            System.out.println(packgeName + "，没有安装过");
            return false;
        }
        /*
        ComponentName componentName = new ComponentName(packgeName, "com.example.administrator.imagetest.OneActivity"); //这里是 包名  以及 页面类的全称
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("type", "110");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        */
//        1.在Activity上下文之外启动Activity需要给Intent设置FLAG_ACTIVITY_NEW_TASK标志，不然会报异常。
//        2.加了该标志，如果在同一个应用中进行Activity跳转，不会创建新的Task，只有在不同的应用中跳转才会创建新的Task
    }

    /**
     * 判断要打开的app是否安装
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isApkInstalled(Context context, String packageName) {
        ApplicationInfo packageInfo = null;

        if (TextUtils.isEmpty(packageName)) {
            return false;
        } else {
            //已安装
        }

        try {
            packageInfo = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();
        }

        if(packageInfo == null) {
//            Toast.makeText(context, "应用没有安装过", Toast.LENGTH_SHORT).show();
            return false;
        } else {
//            Toast.makeText(context, "应用已经安装", Toast.LENGTH_SHORT).show();
            return true; //true为安装了，false为未安装
        }
    }

    // 跳转到另一个app的首页
    public static void goMain(Activity activity, String packageName, String className) {
        Intent intent = new Intent();
        intent.setClassName(packageName, className);
        Bundle bundle = new Bundle();
        bundle.putString("FIRST_APP_KEY", "Hello LoginActivity");
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }





    // 方案1.跳转指定页面（目标的Activity，需要设置android:exported="true"）
    public static void JumpFunc1(Activity activity, String packageName, String className, String token) {
        ComponentName componentName = new ComponentName(packageName, className);//这里是 包名  以及 页面类的全称
        Intent intent = new Intent();
        intent.setComponent(componentName);
        String selfName = activity.getPackageName();
        System.out.println("selfName = " + selfName);
        intent.putExtra("appName", selfName);
        intent.putExtra("token", token);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
    // 方案2.跳转指定页面
    public static void JumpFunc2(Activity activity, String packageName, String className, String token) {
        Intent intent = activity.getPackageManager().getLaunchIntentForPackage(packageName);
        // 这里如果intent为空，就说名没有安装要跳转的应用嘛
        if (intent != null) {
            // 这里跟Activity传递参数一样的嘛，不要担心怎么传递参数，还有接收参数也是跟Activity和Activity传参数一样
//            intent.putExtra("name", "Liu xiang");
//            intent.putExtra("birthday", "1983-7-13");
            String selfName = activity.getPackageName();
            System.out.println("selfName = " + selfName);
            intent.putExtra("appName", selfName);
            intent.putExtra("token", token);
            if(className.isEmpty() == false)
                intent.setClassName(packageName, className);
            activity.startActivity(intent);
        } else {
            // 没有安装要跳转的app应用，提醒一下
            Toast.makeText(activity, "哟，赶紧下载安装这个APP吧", Toast.LENGTH_LONG).show();
        }
    }
}
