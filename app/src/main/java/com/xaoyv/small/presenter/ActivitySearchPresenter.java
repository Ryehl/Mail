package com.xaoyv.small.presenter;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xaoyv.small.activity.SearchActivity;
import com.xaoyv.small.application.MyApplication;
import com.xaoyv.small.base.BasePresenter;
import com.xaoyv.small.bean.Constant;
import com.xaoyv.small.bean.ConstantParameter;
import com.xaoyv.small.bean.JsonShopinfoByCategoryBean;
import com.xaoyv.small.bean.JsonShopinfoByKeywordBean;
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
 * <p>简述:ActivitySearchPresenter</p>
 *
 * @author Xaoyv
 * date 2020/10/21 11:37
 */
public class ActivitySearchPresenter extends BasePresenter<SearchActivity> {
    public void byLabel(String id, int page, int count) {
        //判断网络
        if (InternetUtil.getNetworkState(iView) != InternetUtil.NETWORN_NONE) {
            //参数
            HashMap<String, Object> map = new HashMap<>();
            map.put(ConstantParameter.categoryId, id);
            map.put(ConstantParameter.count, count);
            map.put(ConstantParameter.page, page);
            //进行请求
            NetUtils.getNetUtils().mIApi.getInfo(Constant.BYCATEGORY, map)
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
                                JsonShopinfoByCategoryBean jsonShopinfoByCategoryBean = new Gson().fromJson(json, JsonShopinfoByCategoryBean.class);
                                iView.setCategoryAdap(jsonShopinfoByCategoryBean.getResult());
                            } catch (Exception ignored) {
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(MyApplication.context, "加载失败", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }
    }

    public void byKeyword(String keyWord, int page, int count) {
        if (InternetUtil.getNetworkState(iView) != InternetUtil.NETWORN_NONE) {
            //有网
            HashMap<String, Object> map = new HashMap<>();
            map.put(ConstantParameter.keyword, keyWord);
            map.put(ConstantParameter.page, page);
            map.put(ConstantParameter.count, count);
            //请求数据
            NetUtils.getNetUtils().mIApi.getInfo(Constant.ByKeyworid, map)
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
                                JsonShopinfoByKeywordBean keyowordBean = new Gson().fromJson(json, JsonShopinfoByKeywordBean.class);
                                iView.setKeywordAdap(keyowordBean.getResult());
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
