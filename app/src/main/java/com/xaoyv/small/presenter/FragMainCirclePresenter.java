package com.xaoyv.small.presenter;

import android.widget.Toast;

import com.google.gson.Gson;
import com.xaoyv.small.application.MyApplication;
import com.xaoyv.small.base.BasePresenter;
import com.xaoyv.small.bean.Constant;
import com.xaoyv.small.bean.JsonCircleBean;
import com.xaoyv.small.fragment.MainCircleFrag;
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
 * <p>简述:FragMainCirclePresenter</p>
 *
 * @author Xaoyv
 * date 2020/10/20 09:51
 */
public class FragMainCirclePresenter extends BasePresenter<MainCircleFrag> {
    public void getData(int page, int count) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("count", count);
        //获取圈子数据
        NetUtils.getNetUtils().mIApi.getInfo(Constant.CIRCLE, map)
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
                            JsonCircleBean circleBean = new Gson().fromJson(json, JsonCircleBean.class);
                            iView.setAdap(circleBean);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MyApplication.context, "获取圈子列表失败", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
