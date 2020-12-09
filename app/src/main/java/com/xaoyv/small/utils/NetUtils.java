package com.xaoyv.small.utils;

import android.util.Log;

import com.xaoyv.small.bean.Constant;
import com.xaoyv.small.bean.ConstantMMkv;
import com.xaoyv.small.interfaces.IApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:网络工具类</p>   Model
 *
 * @author Xaoyv
 * date 2020/10/14 16:01
 */
public class NetUtils {
    private static NetUtils netUtils;
    public IApi mIApi;
    private Retrofit retrofit;

    private NetUtils() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mIApi = retrofit.create(IApi.class);
    }

    public static NetUtils getNetUtils() {
        //单例模式
        return netUtils == null ? netUtils = new NetUtils() : netUtils;
    }

    public void setHeader(String sessionId, String userId) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient build = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(chain ->
                        chain.proceed(chain.request()
                                .newBuilder()
                                .addHeader(ConstantMMkv.Key_SessionId, sessionId)
                                .addHeader(ConstantMMkv.Key_UserId, userId)
                                .build())
                )
                .connectTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(build)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mIApi = retrofit.create(IApi.class);
        Log.d(Constant.TAG, "setHeader: success");
    }
}
