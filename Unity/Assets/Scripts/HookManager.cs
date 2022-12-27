using UnityEngine;
using UnityEngine.UI;

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
        /*
        var obj = JsonConvert.DeserializeObject<TimCallback>(json);
        switch ((TimSdkMessage)obj.msg)
        {
            case TimSdkMessage.OnForceOffline: //顶号
                {
                    PanelManager.Instance.CloseAll();
                    PanelManager.Instance.CreatePanel<UI_Login>();
                    ToastManager.Show("账号在别处登录", 0.5f, MaterialUIManager.UIRoot);
                }
                break;
            case TimSdkMessage.Logout: //登出
                if (obj.code == 0)
                {
                    Debug.Log($"<color=green>登出.成功：{obj.data}</color>");

                    PanelManager.Instance.CloseAll();
                    PanelManager.Instance.CreatePanel<UI_Login>();
                }
                else
                {
                    Debug.LogError($"登出.失败：code={obj.code}, data={obj.data}");
                }
                break;
            default:
                Debug.Log($"[Notify] msg=[{obj.msg}]{(TimSdkMessage)obj.msg}, code={obj.code}, data={obj.data}");
                TimEventManager.Notify((TimSdkMessage)obj.msg, obj); //对UIWidget推送消息
                break;
        }
        */
    }
}