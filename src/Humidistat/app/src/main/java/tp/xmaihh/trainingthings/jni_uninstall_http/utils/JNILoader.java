package tp.xmaihh.trainingthings.jni_uninstall_http.utils;

/**
 * Create by Administrator on 2018-12-25
 **/
public class JNILoader {
    static {
        System.loadLibrary("uninstall");
    }

    /**
     * 【Native】核心方法 只支持POST 可以自己更改 [不处理响应]【请处理异常】
     *
     * @param path 包名data/data 的包路径
     * @param path
     * @param cs
     * @param host
     * @param port
     * @return
     */
    public static native int uninstall(String androidPath, String path, String cs, String host, int port);

    /**
     * 【Native】发送http请求 [不处理响应]【请处理异常】
     *
     * @param method 请求方式 大写
     * @param host   主机地址 域名或ip [不需要携带协议名]
     * @param path   请求路径 /开头
     * @param cs     携带参数 仅POST使用
     * @param port   端口 目前固定 80
     */
    public static native int httpConnect(String method, String path, String cs, String host, int port);


    /**
     * 调用示例
      */
//    String host = 192.168.11.10;//host地址【域名也可以不要带协议头】
//    String path = "/unistall";
//    String cs = "ver=1.1";
//    return JNILoader.unistl("/data/data/" + context.getPackageName(), path, cs, host, port);


}
