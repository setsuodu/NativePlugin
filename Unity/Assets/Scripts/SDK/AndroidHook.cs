using System;
using UnityEngine;

public class AndroidHook : IDisposable
{
    private const string activityName = "com.moegijinka.noactivity.OverrideExample"; //OverrideExample extends UnityPlayerActivity
    private const string className = "com.moegijinka.noactivity.TestClass"; //TestClass.java
    private const string jumpActivityName = "com.moegijinka.gamecenter";
    private const string jumpClassName = "com.moegijinka.gamecenter.NormalActivity";

    private AndroidJavaClass jc = null;
    private AndroidJavaObject jo = null;
    public AndroidHook(GameObject go)
    {
        try
        {
            var javaActivity = new AndroidJavaObject(activityName);
            Debug.Log($"javaActivity exist: {javaActivity != null}");
            javaActivity.CallStatic("GetInstance", go.name);
        }
        catch (Exception e)
        {
            Debug.LogError(e);
        }
    }

    public void Dispose()
    {
        jc.Dispose();
        jc = null;
        jo.Dispose();
        jo = null;
    }

    #region 测试
    public void TestBasic()
    {
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
    public void TestExtend(string objName)
    {
        try
        {
            Debug.Log("使用jc");
            jc = new AndroidJavaClass("com.moegijinka.noactivity.MainActivity");
            Debug.Log($"jc exist: {jc != null}"); //
            jc.CallStatic("GetInstance", objName);
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
            jo.CallStatic("GetInstance", objName);
        }
        catch (Exception e)
        {
            Debug.LogError($"error 使用jo: {e}");
        }
    }
    #endregion

    #region 方法
    public void CheckInstall()
    {
        try
        {
            var javaActivity = new AndroidJavaObject(activityName);
            Debug.Log($"javaActivity exist: {javaActivity != null}");
            var result = javaActivity.Call<bool>("checkInstall", jumpActivityName);
            Debug.Log($"checkInstall: {result}");
        }
        catch (Exception e)
        {
            Debug.LogError($"error javaActivity: {e}");
        }
    }
    public void JumpActivity()
    {
        try
        {
            var javaActivity = new AndroidJavaObject(activityName);
            Debug.Log($"javaActivity exist: {javaActivity != null}"); //
            javaActivity.Call("onJumpActivity", jumpActivityName, jumpClassName, "");
            Debug.Log($"onJumpActivity done.");
        }
        catch (Exception e)
        {
            Debug.LogError($"error javaActivity: {e}");
        }
    }
    #endregion
}