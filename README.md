# [2021.最新Native插件制作



- [ ] github.com/setsuodu/NativePluginDemo.git 删除
- [ ] [Android Studio与unity交互](https://bbs.huaweicloud.com/blogs/285723)
- [ ] [官方示例](https://docs.unity3d.com/2019.3/Documentation/Manual/AndroidUnityPlayerActivity.html)



## 开发环境

1. Untiy2021.3.16f1c1

2. AndroidStudio 2021.1.1 P3

3. Gradle

4. Android SDK

   - Unity（5.1 API 22 ~ API 33）
   - 插件建议（API 22~29）
   
5. class.jar 位置：

   ``\Editor\Data\PlaybackEngines\AndroidPlayer\Variations\mono\Release\Classes\classes.jar``

6. UntiyPlayerActivity.java 位置：

   ``\Editor\Data\PlaybackEngines\AndroidPlayer\Source\com\unity3d\player\UnityPlayerActivity.java``

   - （Unity2019后，UnityPlayerActivity.java不在class里面了，需要单独拷贝）
   - Android 工程中，只能有一个 class 继承它。``class MainActivity extends UnityPlayerActivity``
   
   

## Gradle设置



### Android Sudtio 项目设置

  - 注意：6.X 与 7.X 写法差异，使用 6.X 写法

    ```
    apply plugin: 'com.android.library'
    ```

  - build.gradle //删除新版所有代码

    ```java
    
    buildscript {
        repositories {
            google()
            jcenter()
    
        }
        dependencies {
            classpath 'com.android.tools.build:gradle:4.0.0'
    
            // NOTE: Do not place your application dependencies here; they belong
            // in the individual module build.gradle files
        }
    }
    
    task clean(type: Delete) {
        delete rootProject.buildDir
    }
    
    allprojects {
        repositories {
            google()
            jcenter()
            gradle.projectsEvaluated {
                tasks.withType(JavaCompile) {
                    options.compilerArgs << "-Xlint:unchecked" << "-Xlint:deprecation"
                }
            }
        }
    }
    
    ```
    
    - settings.gradle //删除新版所有代码

      ```
      rootProject.name = "noactivity"
      include ':app'
      ```



### Unity 项目设置

  - Unity / Player Settings / Publishing Settings / Build / baseProjectTemplate [√]，最后面添加
  - 解决问题：``请使用-Xlint:deprecation重新编译``

  ```java
  allprojects {
      repositories {
          google()
          jcenter()
          gradle.projectsEvaluated {
              tasks.withType(JavaCompile) {
                  options.compilerArgs << "-Xlint:unchecked" << "-Xlint:deprecation"
              }
          }
      }
  }
  ```



- Gradle templates

- [Unity与Gradle版本对应](https://docs.unity.cn/cn/2020.3/Manual/android-gradle-overview.html)

| **Unity 版本**                                | **Gradle 版本** |
| :-------------------------------------------- | :-------------- |
| 2020.3 始自 2020.3.15f1                       | 6.1.1           |
| 2020.1、2020.2、2020.3 直至并包含 2020.3.14f1 | 5.6.4           |
| 2019.4                                        | 5.1.1           |

- [Android Gradle Plugin Version 对应 Gradle Version](https://developer.android.com/studio/releases/gradle-plugin)
- [Gradle for Android](https://docs.unity3d.com/Manual/android-gradle-overview.html)
- 设置：Project Structure / Project

| **Android Gradle Plugin V**ersion | **Gradle V**ersion |
| :-------------------------------- | :----------------- |
| 3.5.0                             | 5.4.1              |
| 4.0.0 / 4.0.2                     | 6.1.1              |
| 7.1.3                             | 7.2                |



## Manifest设置





## 存在问题

gradle版本和新版本设置

unityplayeractivity 过时了，继承问题

## 参考

[Unity与Android交互（Unity2019+）](https://blog.csdn.net/yinhun2012/article/details/124455611)

[继承UnityPlayerActivity](https://guneyozsan.github.io/extending-the-unity-player-activity-on-android/)

[UnityPlayerActivity.java uses or overrides a deprecated API.](https://forum.unity.com/threads/unityplayeractivity-java-uses-or-overrides-a-deprecated-api.875770/)

[Extending the UnityPlayerActivity Java Code](https://docs.unity3d.com/2019.3/Documentation/Manual/AndroidUnityPlayerActivity.html)

https://zenn.dev/tkada/articles/6a295dbc3092ff