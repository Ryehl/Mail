package com.xaoyv.small.presenter;

import com.google.gson.Gson;
import com.xaoyv.small.activity.WalletActivity;
import com.xaoyv.small.base.BasePresenter;
import com.xaoyv.small.bean.Constant;
import com.xaoyv.small.bean.ConstantParameter;
import com.xaoyv.small.bean.JsonWalletBean;
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
 * <p>tag:Wallet presenter</p>
 *
 * @author Xaoyv
 * date 2020/11/2 08:17
 */
public class ActivityWalletPresenter extends BasePresenter<WalletActivity> {
    public void getData(int page, int count) {
        if (InternetUtil.getNetworkState(iView) != InternetUtil.NETWORN_NONE){
            HashMap<String, Object> map = new HashMap<>();
            map.put(ConstantParameter.page, page);
            map.put(ConstantParameter.count, count);
            NetUtils.getNetUtils().mIApi.getInfo(Constant.MyWallet, map)
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
                                JsonWalletBean walletBean = new Gson().fromJson(json, JsonWalletBean.class);
                                iView.setData(walletBean.getResult());
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
