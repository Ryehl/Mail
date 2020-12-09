package com.xaoyv.small.activity;

import android.content.Intent;
import android.widget.ListView;

import com.google.android.material.tabs.TabLayout;
import com.xaoyv.small.R;
import com.xaoyv.small.adapter.ShopInfoAdap;
import com.xaoyv.small.base.BaseActivity;
import com.xaoyv.small.bean.ConstantParameter;
import com.xaoyv.small.bean.JsonShopInfoBean;
import com.xaoyv.small.presenter.ActivityShopInfoPresenter;

/**
 * 商品详情
 *
 * @author Xaoyv
 * date 2020年10月27日 19点03分
 */
public class ShopInfoActivity extends BaseActivity<ActivityShopInfoPresenter> {

    private TabLayout tab;
    private ListView lv;
    private int commodityId;

    @Override
    public void initView() {
        //find view by id
        tab = findViewById(R.id.shopinfo_tab);
        lv = findViewById(R.id.shopinfo_lv);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        commodityId = intent.getIntExtra(ConstantParameter.commodityId, -1);
        initTab();
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                lv.smoothScrollToPosition(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                lv.smoothScrollToPosition(tab.getPosition());
            }
        });
        pre.getInfo(commodityId);
    }

    private void initTab() {
        tab.addTab(tab.newTab().setText("商品"));
        tab.addTab(tab.newTab().setText("详情"));
        tab.addTab(tab.newTab().setText("评论"));
    }

    @Override
    public int getLayout() {
        return R.layout.activity_shop_info;
    }

    @Override
    public ActivityShopInfoPresenter initPresenter() {
        return new ActivityShopInfoPresenter();
    }

    public void setAdap(JsonShopInfoBean shopInfoBean) {
        lv.setAdapter(new ShopInfoAdap(shopInfoBean, this, commodityId));
    }
}
