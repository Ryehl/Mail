package com.xaoyv.small.presenter;

import android.widget.Toast;

import com.google.gson.Gson;
import com.xaoyv.small.application.MyApplication;
import com.xaoyv.small.base.BasePresenter;
import com.xaoyv.small.bean.Constant;
import com.xaoyv.small.bean.ConstantParameter;
import com.xaoyv.small.bean.JsonOrderListBean;
import com.xaoyv.small.fragment.MainListFrag;
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
 * <p>简述:FragMainListPresenter</p>
 *
 * @author Xaoyv
 * date 2020/10/20 09:58
 */
public class FragMainListPresenter extends BasePresenter<MainListFrag> {
    public void getOrderList(int status, int page, int count) {
        if (InternetUtil.getNetworkState(MyApplication.context) != InternetUtil.NETWORN_NONE) {
            // if status == 4, set it to 9
            status = status == 4 ? 9 : status;

            HashMap<String, Object> map = new HashMap<>();
            map.put(ConstantParameter.status, status);
            map.put(ConstantParameter.page, page);
            map.put(ConstantParameter.count, count);
            //get order info
            NetUtils.getNetUtils().mIApi
                    .getInfo(Constant.Order_List, map)
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
                                JsonOrderListBean orderListBean = new Gson().fromJson(json, JsonOrderListBean.class);
                                //send to fragment
                                if (iView != null)
                                    iView.setAdap(orderListBean.getOrderList());
                            } catch (IOException e) {
                                e.printStackTrace();
                                iView.setAdap(null);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            //error
                            Toast.makeText(MyApplication.context, e.getMessage(), Toast.LENGTH_SHORT).show();
                            iView.setAdap(null);
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        } else {
            //no data. list's size is zero
            iView.setAdap(null);
        }
    }
}
