package com.xaoyv.small.presenter;

import com.google.gson.Gson;
import com.xaoyv.small.activity.ShopInfoActivity;
import com.xaoyv.small.base.BasePresenter;
import com.xaoyv.small.bean.Constant;
import com.xaoyv.small.bean.ConstantParameter;
import com.xaoyv.small.bean.JsonShopInfoBean;
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
 * <p>简述:商品详情</p>
 *
 * @author Xaoyv
 * date 2020/10/27 19:04
 */
public class ActivityShopInfoPresenter extends BasePresenter<ShopInfoActivity> {
    public void getInfo(int commodityId) {
        if (InternetUtil.getNetworkState(iView) != InternetUtil.NETWORN_NONE){
            HashMap<String, Object> map = new HashMap<>();
            map.put(ConstantParameter.commodityId, commodityId);
            NetUtils.getNetUtils().mIApi
                    .getInfo(Constant.ShopInfo, map)
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
                                JsonShopInfoBean shopInfoBean = new Gson().fromJson(json, JsonShopInfoBean.class);
                                iView.setAdap(shopInfoBean);
                            } catch (IOException e) {
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
