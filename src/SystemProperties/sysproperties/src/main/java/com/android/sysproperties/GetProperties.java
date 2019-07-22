package com.android.sysproperties;

/**
 * Gives access to the system properties store by native_get.
 * <p>
 * The system properties
 * store contains a list of string key-value pairs.
 * <p>
 * {@SystemProperties}
 */
public class GetProperties {
    // Used to load the 'system-properties' library on application startup.
    static {

        System.loadLibrary("system-properties");

    }

    /**
     * A native method that is implemented by the 'system-properties' native library,
     * which is packaged with this application.
     */

    private static native String native_get(String key);

    private static native String native_get(String key, String def);



    public static String getString(String key){
        return native_get(key);
    }
    public static String getString(String key,String def){
        return native_get(key,def);
    }
}
