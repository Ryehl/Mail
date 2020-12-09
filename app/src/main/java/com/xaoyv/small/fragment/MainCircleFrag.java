package com.xaoyv.small.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xaoyv.small.R;
import com.xaoyv.small.adapter.CircleAdap;
import com.xaoyv.small.base.BaseFragment;
import com.xaoyv.small.bean.JsonCircleBean;
import com.xaoyv.small.presenter.FragMainCirclePresenter;
import com.xaoyv.small.utils.InternetUtil;

import java.util.ArrayList;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:圈子</p>
 *
 * @author Xaoyv
 * date 2020/10/20 09:50
 */
public class MainCircleFrag extends BaseFragment<FragMainCirclePresenter> {

    private int page = 1, count = 5;
    ArrayList<JsonCircleBean.ResultBean> resultBeans = new ArrayList<>();//没有数据
    XRecyclerView xRecy;
    private CircleAdap circleAdap;

    @Override
    public void initView() {
        View view = getView();
        if (view != null)
            xRecy = view.findViewById(R.id.frag_circle_xrecy);
    }

    @Override
    public void initData() {
        circleAdap = new CircleAdap(resultBeans, getContext());
        xRecy.setAdapter(circleAdap);
        xRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        //加载圈子列表
        if (InternetUtil.getNetworkState(getContext()) != InternetUtil.NETWORN_NONE) {
            pre.getData(page, count);
            //set listenter
            xRecy.setPullRefreshEnabled(true);
            xRecy.setLoadingMoreEnabled(true);
            xRecy.setLoadingListener(new XRecyclerView.LoadingListener() {
                @Override
                public void onRefresh() {
                    page = 1;
                    count = 5;
                    resultBeans.clear();
                    pre.getData(page, count);
                    xRecy.refreshComplete();
                }

                @Override
                public void onLoadMore() {
                    page++;
                    pre.getData(page, count);
                    xRecy.loadMoreComplete();
                }
            });
        }
    }

    public void setAdap(JsonCircleBean circleBean) {
        //set circle's adapter
        resultBeans.addAll(circleBean.getResult());
        circleAdap.notifyDataSetChanged();
    }

    @Override
    public int getLayout() {
        return R.layout.frag_main_circle;
    }

    @Override
    public FragMainCirclePresenter initPresenter() {
        return new FragMainCirclePresenter();
    }
}
