package com.android.systemproperties;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.sysproperties.GetProperties;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'system-properties' library on application startup.
//    static {
//
//        System.loadLibrary("system-properties");
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
//        tv.setText(native_get("ro.build.display.id") + "\n" + getProperty("ro.product.device"));
        tv.setText(GetProperties.getString("ro.product.brand"));
    }

    /**
     * A native method that is implemented by the 'system-properties' native library,
     * which is packaged with this application.
     */


//    private static native String native_get(String key);
//
//    private static native String native_get(String key, String def);


    public static String getProperty(String key) {
        String value = "";
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            value = (String) (get.invoke(c, key, "unknown"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return value;
        }
    }

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

}
