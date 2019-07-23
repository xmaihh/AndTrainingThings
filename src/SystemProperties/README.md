# SystemProperties

通过JNI的方式获取SystemProperties属性

[Restrictions on non-SDK interfaces](https://developer.android.com/distribute/best-practices/develop/restrictions-non-sdk-interfaces)

对于`JNIEnv *env`来说，在C中调用：

```
(*env)->NewStringUTF(env, "Hello from JNI!");
```

而在C++中如果按照上述调用则会发生`'base operand of '->' has non-pointer type '_JNIEnv''`错误，需要如下调用：

```
env->NewStringUTF("Hello from JNI!");
```

原因：参见jni.h中对于JNIEnv的定义：

```
#if defined(__cplusplus)

typedef _JNIEnv JNIEnv;

#else

typedef const struct JNINativeInterface* JNIEnv;

#endif

```

# 反射方法

```java
    public static String getProperty(String key, String defaultValue) {
        String value = defaultValue;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            value = (String) (get.invoke(c, key, defaultValue));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return value;
        }
    }

    public static void setProperty(String key, String value) {
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method set = c.getMethod("set", String.class, String.class);
            set.invoke(c, key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
```


# 使用依赖

- Gradle引用

```groovy
 implementation 'tp.xmaihh:sysproperties:1.0'
```

- Maven引用

```maven
<dependency>
  <groupId>tp.xmaihh</groupId>
  <artifactId>sysproperties</artifactId>
  <version>1.0</version>
  <type>pom</type>
</dependency>
```



<img src="https://raw.githubusercontent.com/xmaihh/AndroidTrainingThings/master/src/SystemProperties/art/art.png" width="270" height="480" alt="演示效果"/>