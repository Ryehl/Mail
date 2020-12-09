package com.xaoyv.small.presenter;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tencent.mmkv.MMKV;
import com.xaoyv.small.activity.MainActivity;
import com.xaoyv.small.application.MyApplication;
import com.xaoyv.small.base.BasePresenter;
import com.xaoyv.small.bean.Constant;
import com.xaoyv.small.bean.ConstantMMkv;
import com.xaoyv.small.bean.ConstantParameter;
import com.xaoyv.small.bean.JsonLoginBean;
import com.xaoyv.small.bean.JsonUpdateBean;
import com.xaoyv.small.utils.AppInfoUtils;
import com.xaoyv.small.utils.AppUtils;
import com.xaoyv.small.utils.InternetUtil;
import com.xaoyv.small.utils.NetUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:ActivityMainPresenter</p>
 *
 * @author Xaoyv
 * date 2020/10/15 16:11
 */
public class ActivityMainPresenter extends BasePresenter<MainActivity> {

    public void upDate() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("appVersionNum", AppInfoUtils.getVersionCode(iView));
        NetUtils.getNetUtils().mIApi.getInfo(Constant.UPDATE, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Log.d("Main", json);
                            JsonUpdateBean updateBean = new Gson().fromJson(json, JsonUpdateBean.class);
                            if (updateBean.getFlag() == 1) {
                                //需要更新 调取弹窗
                                iView.startPwForUpdate(updateBean.getResult().getDownloadUrl());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        String message = e.getMessage();
                        Log.e("Main", message);
                        Toast.makeText(iView, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void downLoad(String url) {
        //下载文件
        NetUtils.getNetUtils().mIApi.downLoadFile(url)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        boolean b = writeResponseBodyToDisk(response.body());
                        if (b) {
                            Toast.makeText(MyApplication.context, "下载完成", Toast.LENGTH_SHORT).show();
                            AppUtils.installApk(iView, Environment.getExternalStorageDirectory() + "newVersion.apk");
                        } else {
                            Toast.makeText(MyApplication.context, "下载出错", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        String message = t.getMessage();
                        Toast.makeText(MyApplication.context, message + "", Toast.LENGTH_SHORT).show();
                        Log.e("MainError", message + "");
                    }
                });
    }

    /**
     * 下载新版本
     *
     * @param body body
     * @return false
     */
    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            File futureStudioIconFile = new File(Environment.getExternalStorageDirectory() + File.separator + "newVersion.apk");
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];
                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);
                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1)
                        break;
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                    Log.d("Main", "file download: " + fileSizeDownloaded + " of " + fileSize);
                }
                outputStream.flush();
                return true;
            } catch (IOException e) {
                return false;
            } catch (NullPointerException e) {
                e.printStackTrace();
                return false;
            } finally {
                if (inputStream != null)
                    inputStream.close();
                if (outputStream != null)
                    outputStream.close();
            }
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * method to login
     *
     * @param phone 13630807408
     * @param pwd   a123456
     */
    public void updateUserIdAndSessionID(String phone, String pwd) {
        //to login, then update userId and sessionId
        if (InternetUtil.getNetworkState(iView) != InternetUtil.NETWORN_NONE) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put(ConstantParameter.phone, phone);
                jsonObject.put(ConstantParameter.password, pwd);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            NetUtils.getNetUtils().mIApi.postInfoWithBody(Constant.LOGIN, RequestBody.create(MediaType.parse(Constant.RequestBodyMediaType), jsonObject.toString()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ResponseBody>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(ResponseBody responseBody) {
                            try {
                                String json = responseBody.string();
                                JsonLoginBean loginBean = new Gson().fromJson(json, JsonLoginBean.class);

                                //set header with userId and sessionId
                                JsonLoginBean.ResultBean result = loginBean.getResult();
                                NetUtils.getNetUtils().setHeader(result.getSessionId(), String.valueOf(result.getUserId()));
                                //set to MMKV
                                MMKV kv = MMKV.defaultMMKV();
                                kv.putString(ConstantMMkv.Key_SessionId, result.getSessionId());
                                kv.putString(ConstantMMkv.Key_UserId, String.valueOf(result.getUserId()));
                                //save info
                                kv.putString(ConstantMMkv.Key_HeadPic, result.getHeadPic());
                                kv.putString(ConstantMMkv.Key_NickName, result.getNickName());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }
    }
}
