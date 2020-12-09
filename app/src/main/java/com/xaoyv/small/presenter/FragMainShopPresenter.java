package com.xaoyv.small.presenter;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xaoyv.small.application.MyApplication;
import com.xaoyv.small.base.BasePresenter;
import com.xaoyv.small.bean.Constant;
import com.xaoyv.small.bean.JsonShopCartBean;
import com.xaoyv.small.fragment.MainShopFragment;
import com.xaoyv.small.utils.InternetUtil;
import com.xaoyv.small.utils.NetUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:FragMainShopPresenter</p>
 *
 * @author Xaoyv
 * date 2020/10/20 09:56
 */
public class FragMainShopPresenter extends BasePresenter<MainShopFragment> {
    public void getShopcartInfo() {
        if (InternetUtil.getNetworkState(Objects.requireNonNull(iView.getContext())) != InternetUtil.NETWORN_NONE) {
            //有网
            NetUtils.getNetUtils().mIApi.getInfo(Constant.SHOPCART, new HashMap<>())
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
                                Log.d(Constant.TAG, "onNext: " + json);
                                JsonShopCartBean shopCartBean = new Gson().fromJson(json, JsonShopCartBean.class);
                                if (iView != null)
                                    iView.setAdap(shopCartBean);
                            } catch (IOException ignored) {
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            if (iView != null)
                                Toast.makeText(iView.getContext(), "数据获取失败", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        } else {
            Toast.makeText(MyApplication.context, "没有网络，加载失败", Toast.LENGTH_SHORT).show();
        }
    }
}
