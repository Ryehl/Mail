package com.xaoyv.small.diyview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.google.android.material.tabs.TabLayout;
import com.xaoyv.small.R;
import com.xaoyv.small.bean.JsonShoptypeBean;
import com.xaoyv.small.interfaces.IView;
import com.xaoyv.small.presenter.DivShowtypePwPresenter;

import java.util.ArrayList;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:自定义商品类目视图</p>
 *
 * @author Xaoyv
 * date 2020/10/21 11:45
 */
public class DivShopTypePopupWindow extends PopupWindow implements IView<DivShowtypePwPresenter> {
    private DivShowtypePwPresenter pre;
    private TabLayout tab;
    private DivFlowLayout myflow;

    public DivShopTypePopupWindow(Context context) {
        super(View.inflate(context, R.layout.div_shoptype, null), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        initView();
        initData();
    }

    public void setData(JsonShoptypeBean shoptypeBean) {
        ArrayList<JsonShoptypeBean.ResultBean> result = new ArrayList<>();
        result.addAll(shoptypeBean.getResult());
        for (JsonShoptypeBean.ResultBean res : result) {
            String name = res.getName();
            tab.addTab(tab.newTab().setText(name));
        }
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                myflow.setViews(new ArrayList<>(result.get(tab.getPosition()).getSecondCategoryVo()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                myflow.setViews(new ArrayList<>(result.get(tab.getPosition()).getSecondCategoryVo()));
            }
        });
        tab.getTabAt(0).select();
    }

    @Override
    public void initView() {
        tab = getContentView().findViewById(R.id.div_showType_tab);
        myflow = getContentView().findViewById(R.id.div_showType_divflow);
    }

    @Override
    public void initData() {
        pre = initPresenter();
        pre.attachView(this);
        pre.initTab();
        //设置横向滑动
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    public int getLayout() {
        return 0;
    }

    @Override
    public DivShowtypePwPresenter initPresenter() {
        return new DivShowtypePwPresenter();
    }
}
