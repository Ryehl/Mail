package com.xaoyv.small.presenter;

import com.google.gson.Gson;
import com.xaoyv.small.application.MyApplication;
import com.xaoyv.small.base.BasePresenter;
import com.xaoyv.small.bean.Constant;
import com.xaoyv.small.bean.JsonShoptypeBean;
import com.xaoyv.small.diyview.DivShopTypePopupWindow;
import com.xaoyv.small.utils.InternetUtil;
import com.xaoyv.small.utils.NetUtils;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:自定义商品类目presenter</p>
 *
 * @author Xaoyv
 * date 2020/10/22 10:55
 */
public class DivShowtypePwPresenter extends BasePresenter<DivShopTypePopupWindow> {
    public void initTab() {
        if (InternetUtil.getNetworkState(MyApplication.context) != InternetUtil.NETWORN_NONE){
            NetUtils.getNetUtils().mIApi.getInfo(Constant.SHOP_TYPE, new HashMap<>())
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
                                JsonShoptypeBean shoptypeBean = new Gson().fromJson(json, JsonShoptypeBean.class);
                                iView.setData(shoptypeBean);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }
    }
}
