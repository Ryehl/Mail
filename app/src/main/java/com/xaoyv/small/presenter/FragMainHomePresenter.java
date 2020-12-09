package com.xaoyv.small.presenter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.xaoyv.small.R;
import com.xaoyv.small.adapter.HomeXRecyAdap;
import com.xaoyv.small.base.BasePresenter;
import com.xaoyv.small.bean.Constant;
import com.xaoyv.small.bean.JsonBannerBean;
import com.xaoyv.small.bean.JsonHomeBean;
import com.xaoyv.small.fragment.MainHomeFrag;
import com.xaoyv.small.utils.NetUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:首页Presenter</p>
 *
 * @author Xaoyv
 * date 2020/10/17 09:42
 */
public class FragMainHomePresenter extends BasePresenter<MainHomeFrag> {

    /**
     * 轮播图
     */
    public void getBannerInfo() {
        //请求数据
        NetUtils.getNetUtils().mIApi.getInfo(Constant.BANNER_URL, new HashMap<>())
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
                            Log.i("BannerInfo", json);
                            JsonBannerBean bannerBean = new Gson().fromJson(json, JsonBannerBean.class);
                            List<JsonBannerBean.ResultBean> result = bannerBean.getResult();
                            ArrayList<String> images = new ArrayList<>();
                            ArrayList<String> titles = new ArrayList<>();
                            for (JsonBannerBean.ResultBean it : result) {
                                images.add(it.getImageUrl());
                                titles.add(it.getTitle());
                            }
                            //自定义banner布局
                            View view = LayoutInflater.from(iView.getContext()).inflate(R.layout.view_banner, null, false);
                            Banner banner = view.findViewById(R.id.div_view_banner);
                            banner.setImages(images);
                            banner.setBannerTitles(titles);
                            //设置内置样式 -- 可以不设置
                            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
                            //设置动画特效 -- 可以不设置
                            banner.setBannerAnimation(Transformer.ZoomOutSlide);
                            //设置轮播间隔时间
                            banner.setDelayTime(2500);
                            //是否自动轮播
                            banner.isAutoPlay(true);
                            //设置加载类
                            banner.setImageLoader(new ImageLoader() {
                                @Override
                                public void displayImage(Context context, Object path, ImageView imageView) {
                                    //加载图片
                                    Glide.with(context).load((String) path).into(imageView);
                                }
                            });
                            //开始轮播
                            banner.start();

                            iView.setXRecyHead(view);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(iView.getContext(), "轮播图加载失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 获取首页多布局数据
     */
    public void getHomeData() {
        NetUtils.getNetUtils().mIApi.getInfo(Constant.HOME_INFO, new HashMap<>())
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
                            Log.i("MainHomeFrag", json);
                            iView.setAdap(json);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(iView.getContext(), "首页加载失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
