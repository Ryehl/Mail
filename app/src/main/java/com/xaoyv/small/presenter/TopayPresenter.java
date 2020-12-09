package com.xaoyv.small.presenter;

import android.net.Uri;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xaoyv.small.activity.TopayActivity;
import com.xaoyv.small.base.BasePresenter;
import com.xaoyv.small.bean.Constant;
import com.xaoyv.small.bean.ConstantParameter;
import com.xaoyv.small.bean.DiyBean;
import com.xaoyv.small.bean.JsonAddressBean;
import com.xaoyv.small.bean.JsonCreateOrderBean;
import com.xaoyv.small.bean.JsonPayBean;
import com.xaoyv.small.bean.JsonShopInfoBean;
import com.xaoyv.small.utils.InternetUtil;
import com.xaoyv.small.utils.NetUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.http.Url;

/**
 * <p>Project's name:维度商城</p>
 * <p>tag:to pay presenter</p>
 *
 * @author Xaoyv
 * date 2020/11/3 03:43
 */
public class TopayPresenter extends BasePresenter<TopayActivity> {
    public void getAddress() {
        if (InternetUtil.getNetworkState(iView) != InternetUtil.NETWORN_NONE) {
            NetUtils.getNetUtils().mIApi.getInfo(Constant.Address, new HashMap<>())
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
                                JsonAddressBean addressBean = new Gson().fromJson(json, JsonAddressBean.class);
                                iView.setAddress(addressBean);
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

    public void getShopsInfo(ArrayList<DiyBean> list) {
        List<JsonShopInfoBean> beans = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (InternetUtil.getNetworkState(iView) != InternetUtil.NETWORN_NONE) {
                HashMap<String, Object> map = new HashMap<>();
                map.put(ConstantParameter.commodityId, list.get(i).getCommodityId());
                int finalI = i;
                NetUtils.getNetUtils().mIApi.getInfo(Constant.ShopInfo, map)
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
                                    beans.add(shopInfoBean);
                                    if (finalI == list.size() - 1) {
                                        iView.setAdap(beans);
                                    }
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

    public void createOrder(String info, int price, int addressId) {
        if (InternetUtil.getNetworkState(iView) != InternetUtil.NETWORN_NONE) {
            HashMap<String, Object> map = new HashMap<>();
            try {
                info = URLDecoder.decode(info, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            map.put(ConstantParameter.orderInfo, info);
            map.put(ConstantParameter.totalPrice, price);
            map.put(ConstantParameter.addressId, addressId);
            NetUtils.getNetUtils().mIApi.postInfo(Constant.CreateOrder, map)
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
                                JsonCreateOrderBean createOrderBean = new Gson().fromJson(json, JsonCreateOrderBean.class);
                                iView.pay(createOrderBean.getOrderId());
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

    public void payNow(String orderId) {
        if (InternetUtil.getNetworkState(iView) != InternetUtil.NETWORN_NONE) {
            HashMap<String, Object> map = new HashMap<>();
            map.put(ConstantParameter.orderId, orderId);
            map.put(ConstantParameter.payType, 1);
            NetUtils.getNetUtils().mIApi.postInfo(Constant.Pay, map)
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
                                JsonPayBean payBean = new Gson().fromJson(json, JsonPayBean.class);
                                Toast.makeText(iView, payBean.getMessage(), Toast.LENGTH_SHORT).show();
                                iView.finish();
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
