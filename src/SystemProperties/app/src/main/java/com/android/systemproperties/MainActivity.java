package com.android.systemproperties;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.sysproperties.GetProperties;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);

        appendColoredText(tv, "By native get", Color.RED);
        tv.append("\n" + GetProperties.getString("ro.product.brand"));
        tv.append("\n" + GetProperties.getString("ro.build.display.id"));
        tv.append("\n" + GetProperties.getString("ro.product.model"));
        tv.append("\n" + GetProperties.getString("ro.build.version.sdk"));
        tv.append("\n" + GetProperties.getString("ro.product.cpu.abi"));
        tv.append("\n" + GetProperties.getString("ro.build.type"));


        tv.append("\n");
        appendColoredText(tv, "By using reflection", Color.BLUE);
        tv.append("\n" + getProperty("ro.product.brand"));
        tv.append("\n" + getProperty("ro.build.display.id"));
        tv.append("\n" + getProperty("ro.product.model"));
        tv.append("\n" + getProperty("ro.build.version.sdk"));
        tv.append("\n" + getProperty("ro.product.cpu.abi"));
        tv.append("\n" + getProperty("ro.build.type"));
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

    private static void appendColoredText(TextView tv, String text, int color) {
        int start = tv.getText().length();
        tv.append(text);
        int end = tv.getText().length();

        Spannable spannableText = (Spannable) tv.getText();
        spannableText.setSpan(new ForegroundColorSpan(color), start, end, 0);
    }
}
