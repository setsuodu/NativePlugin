using UnityEngine;
using UnityEngine.UI;
using Newtonsoft.Json;

public class HookManager : MonoBehaviour
{
    public static HookManager Get;

    private AndroidHook hook;

    public Button btnJump;

    void Awake()
    {
        Get = this;
        DontDestroyOnLoad(this.gameObject);

#if UNITY_ANDROID
        hook = new AndroidHook(gameObject);
        btnJump.onClick.AddListener(hook.JumpActivity);
#elif UNITY_IOS
        //hook = new iOSHook(gameObject);
#else
        hook = null;
#endif
    }

    void OnDestroy()
    {
        hook?.Dispose();
    }

    // 字符串消息返回
    public void JavaToUnity(string message)
    {
        Debug.Log($"Unity recv: {message}");
    }
    // json消息返回
    public void JsonToUnity(string json)
    {
        var obj = JsonConvert.DeserializeObject<MoeCallback>(json);
        switch (obj.code)
        {
            case 0:

                break;

            case 1:
                break;
        }
    }
}