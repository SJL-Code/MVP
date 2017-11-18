package com.gzwlw.mvp.common.utils;

import android.net.ParseException;
import android.util.Log;

import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import retrofit2.HttpException;

/**
 * 异常
 */
public class ExceptionUtil {

    private static String TAG = "ExceptionUtil";

    public static String handleException(Throwable e) {

        if (e instanceof HttpException) {

            HttpException he = (HttpException) e;
            Log.e(TAG, "HTTP错误: " + he.getMessage() + "Code: " + he.code());
            return "HTTP错误";

        } else if (e instanceof ConnectException) {

            Log.e(TAG, "网络连接异常: " + e.getMessage());
            return "网络连接异常";

        } else if (e instanceof SocketTimeoutException) {

            Log.e(TAG, "网络连接超时异常: " + e.getMessage());
            return "网络连接超时异常";

        } else if (e instanceof UnknownHostException) {

            Log.e(TAG, "未知主机异常: " + e.getMessage());
            return "未知主机异常";

        } else if (e instanceof IllegalArgumentException) {

            Log.e(TAG, "非法参数异常: " + e.getMessage());
            return "非法参数异常";

        } else if (e instanceof SSLHandshakeException) {

            Log.e(TAG, "证书验证失败异常: " + e.getMessage());
            return "证书验证失败异常";

        } else if (e instanceof JSONException
                || e instanceof ParseException
                || e instanceof JsonParseException
                || e instanceof MalformedJsonException) {

            Log.e(TAG, "数据解析异常: " + e.getMessage());
            return "数据解析异常";

        } else {

            Log.e(TAG, "未知错误Debug调试: " + e.getMessage());
            return "未知错误Debug调试";

        }
    }
}
