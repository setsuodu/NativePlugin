using System;
using System.Collections.Generic;
using UnityEngine;

public class HookManager : MonoBehaviour
{
    private const string className = "com.moegijinka.noactivity.TestClass"; //TestClass.java
    private AndroidJavaClass jc = null;
    private AndroidJavaObject jo = null;

    void Start()
    {
        /*
        try
        {
            //jc = new AndroidJavaClass(className);
            //jo = jc.GetStatic<AndroidJavaObject>("currentActivity");
            //jo.CallStatic("Test1"); //public void Test1()

            // Retrieve the UnityPlayer class.
            //AndroidJavaClass unityPlayerClass = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
            // Retrieve the UnityPlayerActivity object ( a.k.a. the current context )
            //AndroidJavaObject unityActivity = unityPlayerClass.GetStatic<AndroidJavaObject>("currentActivity");
        }
        catch (Exception e)
        {
            Debug.LogError(e);
        }
        */


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
            //testO.Call("Test1"); //正确
            testO.CallStatic("Test2"); //正确
        }
        catch (Exception e)
        {
            Debug.LogError($"errorO: {e}");
        }
    }

    public void Dispose()
    {
        jc?.Dispose();
        jc = null;
        jo?.Dispose();
        jo = null;
    }
}