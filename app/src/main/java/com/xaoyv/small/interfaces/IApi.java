package com.xaoyv.small.interfaces;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:网络接口</p>
 *
 * @author Xaoyv
 * date 2020/10/15 23:56
 */
public interface IApi {
    /**
     * GET请求
     *
     * @param path 网址
     * @param map  参数
     * @return Observable
     */
    @GET
    Observable<ResponseBody> getInfo(@Url String path, @QueryMap HashMap<String, Object> map);

    @GET
    Call<ResponseBody> downLoadFile(@Url String filePath);

    @Multipart
    @POST
    Observable<ResponseBody> uploadHd(@Url String path, @Part MultipartBody.Part part);

    @POST
    Observable<ResponseBody> postInfo(@Url String path, @QueryMap HashMap<String, Object> map);

    @POST
    Observable<ResponseBody> postInfoWithBody(@Url String path, @Body RequestBody body);
}
