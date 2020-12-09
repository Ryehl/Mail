package com.xaoyv.small.fragment;

import android.graphics.Color;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xaoyv.small.R;
import com.xaoyv.small.adapter.OrderListAdap;
import com.xaoyv.small.base.BaseFragment;
import com.xaoyv.small.bean.JsonOrderListBean;
import com.xaoyv.small.presenter.FragMainListPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:MainListFrag</p>
 *
 * @author Xaoyv
 * date 2020/10/20 09:58
 */
public class MainListFrag extends BaseFragment<FragMainListPresenter> {

    private TabLayout tab;
    private XRecyclerView recy;
    private boolean tabIsLoad = false;

    private OrderListAdap adap;
    private List<JsonOrderListBean.OrderListBean> list = new ArrayList<>();
    private int page = 1, count = 5;
    int i = -1;

    @Override
    public void initView() {
        tab = getView().findViewById(R.id.frag_list_tab);
        recy = getView().findViewById(R.id.frag_list_recy);
    }

    @Override
    public void initData() {
        //init tab
        initTab();
        tab.setTabMode(TabLayout.MODE_FIXED);
        //TODO set tab's on click listener
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //select tab
                pre.getOrderList(tab.getPosition(), page, count);
                i = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //unselected tab, nothing to do
                list.clear();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //reselect tab same as select
                pre.getOrderList(tab.getPosition(), page, count);
                i = tab.getPosition();
            }
        });
        //select index for 0
        tab.selectTab(tab.getTabAt(0));
        //TODO get info set adapter. if not data?
        recy.setPullRefreshEnabled(true);
        recy.setLoadingMoreEnabled(true);
        recy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                if (pre != null)
                    pre.getOrderList(i, page, count);
                recy.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                if (pre != null)
                    pre.getOrderList(i, page, count);
                recy.loadMoreComplete();
            }
        });
    }

    /**
     * int tab set text and icons
     */
    private void initTab() {
        if (tabIsLoad)
            return;
        //set text
        tab.addTab(tab.newTab().setText("全部订单"));
        tab.addTab(tab.newTab().setText("待付款"));
        tab.addTab(tab.newTab().setText("待收货"));
        tab.addTab(tab.newTab().setText("待评价"));
        tab.addTab(tab.newTab().setText("已完成"));
        //set icons
        tab.getTabAt(0).setIcon(R.mipmap.common_icon_all);
        tab.getTabAt(1).setIcon(R.mipmap.common_icon_pay);
        tab.getTabAt(2).setIcon(R.mipmap.commom_icon_comment);
        tab.getTabAt(3).setIcon(R.mipmap.common_icon_receive);
        tab.getTabAt(4).setIcon(R.mipmap.common_icon_finish);
        tabIsLoad = true;
    }

    @Override
    public int getLayout() {
        return R.layout.frag_main_list;
    }

    @Override
    public FragMainListPresenter initPresenter() {
        return new FragMainListPresenter();
    }

    /**
     * set recyclerView's adapter
     *
     * @param orderList list
     */
    public void setAdap(List<JsonOrderListBean.OrderListBean> orderList) {
        if (orderList != null)
            list.addAll(orderList);
        if (list.size() != 0) {
            if (adap == null) {
                adap = new OrderListAdap(list);
                recy.setAdapter(adap);
                recy.setLayoutManager(new LinearLayoutManager(getContext()));
                return;
            }
            adap.notifyDataSetChanged();
            recy.setBackgroundColor(Color.WHITE);
        } else {
            if (adap != null)
                adap.notifyDataSetChanged();
            recy.setBackgroundResource(R.drawable.nodata);
        }
    }
}
