package com.xaoyv.small.presenter;

import android.widget.Toast;

import com.google.gson.Gson;
import com.tencent.mmkv.MMKV;
import com.xaoyv.small.activity.RegisterActivity;
import com.xaoyv.small.base.BasePresenter;
import com.xaoyv.small.bean.Constant;
import com.xaoyv.small.bean.JsonLoginBean;
import com.xaoyv.small.bean.ConstantMMkv;
import com.xaoyv.small.utils.InternetUtil;
import com.xaoyv.small.utils.NetUtils;

import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:注册Presenter</p>
 *
 * @author Xaoyv
 * date 2020/10/23 20:14
 */
public class ActivityRegisterPresenter extends BasePresenter<RegisterActivity> {
    public void getData(String phone, String pwd) {
        if (InternetUtil.getNetworkState(iView) != InternetUtil.NETWORN_NONE) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put(ConstantMMkv.Key_Phone, phone);
                jsonObject.put(ConstantMMkv.Key_Pwd, pwd);
                RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), jsonObject.toString());
                NetUtils.getNetUtils().mIApi.postInfoWithBody(Constant.REGISTER, body)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ResponseBody>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onNext(ResponseBody responseBody) {
                                try {
                                    iView.getData(responseBody.string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(iView, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onComplete() {
                            }
                        });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 进行登录操作
     *
     * @param get_phone 手机号
     * @param get_pwd   密码
     */
    public void login(String get_phone, String get_pwd) {
        if (InternetUtil.getNetworkState(iView) != InternetUtil.NETWORN_NONE) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(ConstantMMkv.Key_Phone, get_phone);
                jsonObject.put(ConstantMMkv.Key_Pwd, get_pwd);
                RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), jsonObject.toString());
                NetUtils.getNetUtils().mIApi.postInfoWithBody(Constant.LOGIN, body)
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
                                    String sessionId = loginBean.getResult().getSessionId();
                                    int userId = loginBean.getResult().getUserId();
                                    String valueOf = String.valueOf(userId);
                                    MMKV kv = MMKV.defaultMMKV();
                                    kv.putString(ConstantMMkv.Key_UserId, valueOf);
                                    kv.putString(ConstantMMkv.Key_SessionId, sessionId);
                                    //设置登录
                                    kv.putBoolean(ConstantMMkv.Key_IsLogin, true);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(iView, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onComplete() {
                            }
                        });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
