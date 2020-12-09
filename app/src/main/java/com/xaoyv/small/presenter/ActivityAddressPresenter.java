package com.xaoyv.small.presenter;

import com.google.gson.Gson;
import com.xaoyv.small.activity.AddressActivity;
import com.xaoyv.small.base.BasePresenter;
import com.xaoyv.small.bean.Constant;
import com.xaoyv.small.bean.JsonAddressBean;
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
 * <p>Project's name:维度商城</p>
 * <p>tag:Address presenter</p>
 *
 * @author Xaoyv
 * date 2020/11/2 09:02
 */
public class ActivityAddressPresenter extends BasePresenter<AddressActivity> {
    public void getData() {
        if(InternetUtil.getNetworkState(iView) != InternetUtil.NETWORN_NONE){
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
                                iView.setData(addressBean.getResult());
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
