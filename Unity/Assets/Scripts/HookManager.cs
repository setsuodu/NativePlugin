using System;
using System.Collections.Generic;
using UnityEngine;

public class HookManager : MonoBehaviour
{
    private const string activityName = "com.moegijinka.noactivity.TestClass"; //TestClass.java
    private const string className = "com.moegijinka.noactivity.TestClass"; //TestClass.java
    private AndroidJavaClass jc = null;
    private AndroidJavaObject jo = null;

    void TestBasic()
    {
        // Retrieve the UnityPlayer class.
        //AndroidJavaClass unityPlayerClass = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
        // Retrieve the UnityPlayerActivity object ( a.k.a. the current context )
        //AndroidJavaObject unityActivity = unityPlayerClass.GetStatic<AndroidJavaObject>("currentActivity");

        try
        {
            AndroidJavaClass testC = new AndroidJavaClass(className);
            Debug.Log($"testC exist: {testC != null}"); //True
            //testC.Call("Test1"); //错误
            testC.CallStatic("Test2"); //正确
        }
        catch (Exception e)
        {
            Debug.LogError($"errorC: {e}");
        }

        try
        {
            AndroidJavaObject testO = new AndroidJavaObject(className);
            Debug.Log($"testO exist: {testO != null}"); //True
            //testO.Call("Test1"); //正确 //public void Test1()
            testO.CallStatic("Test2"); //正确 //public static void Test2()
        }
        catch (Exception e)
        {
            Debug.LogError($"errorO: {e}");
        }
    }

    void TestExtend()
    {
        /*
        try
        {
            // 错误
            Debug.Log("使用标准");
            //jc = new AndroidJavaClass("com.moegijinka.noactivity.MainActivity");
            //jo.CallStatic("GetInstance", gameObject.name);

            jc = new AndroidJavaClass("com.moegijinka.noactivity.MainActivity");
            Debug.Log($"标准jc exist: {jc != null}"); //
            jo = jc.GetStatic<AndroidJavaObject>("currentActivity");
            Debug.Log($"标准jo exist: {jo != null}"); //
            jo.CallStatic("GetInstance", gameObject.name);
        }
        catch (Exception e)
        {
            Debug.LogError($"error 使用标准: {e}");
        }
        */

        try
        {
            Debug.Log("使用jc");
            jc = new AndroidJavaClass("com.moegijinka.noactivity.MainActivity");
            Debug.Log($"jc exist: {jc != null}"); //
            jc.CallStatic("GetInstance", gameObject.name);
        }
        catch (Exception e)
        {
            Debug.LogError($"error 使用jc: {e}");
        }

        try
        {
            Debug.Log("使用jo");
            jo = new AndroidJavaObject("com.moegijinka.noactivity.MainActivity");
            Debug.Log($"jo exist: {jo != null}"); //
            jo.CallStatic("GetInstance", gameObject.name);
        }
        catch (Exception e)
        {
            Debug.LogError($"error 使用jo: {e}");
        }
    }

    void Start()
    {
        var unityClass = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
        Debug.Log($"unityClass exist: {unityClass != null}"); //True
        var unityActivity = unityClass.GetStatic<AndroidJavaObject>("currentActivity");
        Debug.Log($"unityActivity exist: {unityActivity != null}"); //True

        //AndroidJavaClass javaClass = new AndroidJavaClass(className);
        //Debug.Log($"javaClass exist: {javaClass != null}"); //True
        //javaClass.CallStatic("CallAndroid", unityActivity); //正确

        //AndroidJavaObject javaObject = new AndroidJavaObject(className);
        //Debug.Log($"javaObject exist: {javaObject != null}"); //True
        //javaObject.CallStatic("CallAndroid", unityActivity); //正确

        Debug.Log("AAA");

        try
        {
            //var javaActivity = new AndroidJavaObject(activityName);
            //Debug.Log($"javaActivity exist: {javaActivity != null}"); //
            //javaActivity.CallStatic("Test1");
            //javaActivity.Call("Test2"); //
        }
        catch (Exception e)
        {
            Debug.LogError($"error javaActivity: {e}");
        }

        Debug.Log("BBB");
    }

    public void Dispose()
    {
        jc?.Dispose();
        jc = null;
        jo?.Dispose();
        jo = null;
    }
}