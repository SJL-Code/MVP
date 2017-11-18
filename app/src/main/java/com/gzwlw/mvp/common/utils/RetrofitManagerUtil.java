package com.gzwlw.mvp.common.utils;

import android.util.Log;

import com.gzwlw.mvp.common.constants.HttpURLs;
import com.gzwlw.mvp.common.interfaces.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManagerUtil {

    private String TAG = "RetrofitManagerUtil";

    private static Retrofit retrofit;

    private static RetrofitManagerUtil instance;

    public static RetrofitManagerUtil getInstance() {
        if (null == instance) {
            synchronized (RetrofitManagerUtil.class) {
                if (null == instance) {
                    instance = new RetrofitManagerUtil();
                }
            }
        }
        return instance;
    }

    private RetrofitManagerUtil() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(
                new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.e(TAG, "OkHttpLog: " + message);
                    }
                })
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(loggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(HttpURLs.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public ApiService getApiService() {
        // 得到联网工具对象
        ApiService apiService = retrofit.create(ApiService.class);

        return apiService;
    }
}
