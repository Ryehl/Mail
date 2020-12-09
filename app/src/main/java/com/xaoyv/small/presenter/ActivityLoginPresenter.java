package com.xaoyv.small.presenter;

import android.widget.Toast;

import com.xaoyv.small.activity.LoginActivity;
import com.xaoyv.small.base.BasePresenter;
import com.xaoyv.small.bean.Constant;
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
 * <p>简述:登录presenter</p>
 *
 * @author Xaoyv
 * date 2020/10/23 19:38
 */
public class ActivityLoginPresenter extends BasePresenter<LoginActivity> {
    public void getData(String phone, String pwd) {
        if (InternetUtil.getNetworkState(iView) != InternetUtil.NETWORN_NONE) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("phone", phone);
                jsonObject.put("pwd", pwd);
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
}
