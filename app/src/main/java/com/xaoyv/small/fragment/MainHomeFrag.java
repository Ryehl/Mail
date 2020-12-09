package com.xaoyv.small.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xaoyv.small.R;
import com.xaoyv.small.activity.SearchActivity;
import com.xaoyv.small.adapter.HomeXRecyAdap;
import com.xaoyv.small.application.MyApplication;
import com.xaoyv.small.base.BaseFragment;
import com.xaoyv.small.bean.JsonHomeBean;
import com.xaoyv.small.presenter.FragMainHomePresenter;
import com.xaoyv.small.utils.InternetUtil;
import com.xaoyv.small.utils.ViewUtils;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:首页数据</p>
 *
 * @author Xaoyv
 * date 2020/10/17 09:41
 */
public class MainHomeFrag extends BaseFragment<FragMainHomePresenter> {

    private ImageView menu;
    private ImageView searchImg;
    private XRecyclerView xRecy;
    private LinearLayout ll;

    @Override
    public void initView() {
        menu = getView().findViewById(R.id.frag_home_menu);
        searchImg = getView().findViewById(R.id.frag_home_skipToSearch);
        xRecy = getView().findViewById(R.id.frag_home_xrecy);
        ll = getView().findViewById(R.id.main_ll_changeBg);
    }

    @Override
    public void initData() {
        //菜单按键 点击跳转
        menu.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            intent.putExtra("showPw", true);
            startActivity(intent);
        });

        //搜索小按钮 点击跳转
        searchImg.setOnClickListener(v -> {
            Intent in = new Intent(getActivity(), SearchActivity.class);
            startActivity(in);
        });

        //从网络获取 数据 并 设置数据和轮播图
        if (InternetUtil.getNetworkState(MyApplication.context) != InternetUtil.NETWORN_NONE) {
            pre.getBannerInfo();
            pre.getHomeData();
        } else {
            Toast.makeText(MyApplication.context, "手机暂时没有网络呢", Toast.LENGTH_SHORT).show();
            return;
        }

        //设置监听器 -- 启用下拉刷新，禁用上拉加载
        xRecy.setLoadingMoreEnabled(false);
        xRecy.setPullRefreshEnabled(true);
        xRecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                if (InternetUtil.getNetworkState(getContext()) != InternetUtil.NETWORN_NONE) {
                    pre.getHomeData();
                    pre.getBannerInfo();
                }
                //刷新完成
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            sleep(1000);
                            getActivity().runOnUiThread(() -> xRecy.refreshComplete());
                        } catch (InterruptedException ignored) {
                        }
                    }
                }.start();
            }

            @Override
            public void onLoadMore() {
                //加载更多，未启用
            }
        });

        //设置滑动监听，滑动改变头部透明度
        final int Max = 220;
        xRecy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int i = 0;

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                i += dy;

                //Log.d("位移", "i:" + i);
                if (i > Max) {
                    ll.setBackgroundColor(Color.WHITE);
                    ViewUtils.unSetViewTransparent(getActivity());
                } else {
                    ll.setBackgroundColor(Color.WHITE);
                    ll.getBackground().setAlpha(i * 255 / Max);
                    if (i < Max / 2)
                        ViewUtils.setViewTransparent(getActivity());
                }
            }
        });
    }

    /**
     * 设置头部布局
     *
     * @param view
     */
    public void setXRecyHead(View view) {
        //添加头部
        xRecy.removeAllHeaderView();
        xRecy.addHeaderView(view);
    }

    /**
     * 设置适配器
     *
     * @param json 获取到的json数据
     */
    public void setAdap(String json) {
        JsonHomeBean homeBean = new Gson().fromJson(json, JsonHomeBean.class);
        xRecy.setAdapter(new HomeXRecyAdap(homeBean, getActivity()));
        xRecy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public int getLayout() {
        return R.layout.frag_main_home;
    }

    @Override
    public FragMainHomePresenter initPresenter() {
        return new FragMainHomePresenter();
    }
}
