package com.xaoyv.small.activity;

import android.content.Intent;

import androidx.recyclerview.widget.GridLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xaoyv.small.R;
import com.xaoyv.small.adapter.SearchCategoryAdap;
import com.xaoyv.small.adapter.SearchKeywordAdap;
import com.xaoyv.small.base.BaseActivity;
import com.xaoyv.small.bean.JsonShopinfoByCategoryBean;
import com.xaoyv.small.bean.JsonShopinfoByKeywordBean;
import com.xaoyv.small.diyview.DivFlowLayout;
import com.xaoyv.small.diyview.DivSearchView;
import com.xaoyv.small.diyview.DivShopTypePopupWindow;
import com.xaoyv.small.presenter.ActivitySearchPresenter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity<ActivitySearchPresenter> {

    DivShopTypePopupWindow shopTypePw;
    private DivSearchView mySearchView;
    private XRecyclerView recy_show;
    //是否展示
    boolean isShow = false;
    int page = 1, count = 10;
    int status = 0;
    String temp = "";

    ArrayList<JsonShopinfoByCategoryBean.ResultBean> categoryList = new ArrayList<>();
    ArrayList<JsonShopinfoByKeywordBean.ResultBean> keywordList = new ArrayList<>();
    SearchKeywordAdap keywordAdap;
    SearchCategoryAdap categoryAdap;

    @Override
    public void initView() {
        //find view by id
        mySearchView = findViewById(R.id.search_divSearch);
        recy_show = findViewById(R.id.search_XRecy);
    }

    @Override
    public void initData() {
        //初始化Pw
        shopTypePw = new DivShopTypePopupWindow(this);
        DivFlowLayout flow = shopTypePw.getContentView().findViewById(R.id.div_showType_divflow);
        //设置pw 的监听器
        flow.setListener(id -> {
            pre.byLabel(temp = id, page = 1, count);
            isShow = false;
            shopTypePw.dismiss();
        });

        //设置XRecyclerView的上下拉
        recy_show.setLoadingMoreEnabled(true);
        recy_show.setPullRefreshEnabled(true);
        recy_show.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                page = 1;
                categoryList.clear();
                if (categoryAdap != null)
                    categoryAdap.notifyDataSetChanged();
                keywordList.clear();
                if (keywordAdap != null)
                    keywordAdap.notifyDataSetChanged();
                if (temp == "") {
                    //加载完成
                    recy_show.refreshComplete();
                    return;
                }
                switch (status) {
                    case 1:
                        pre.byLabel(temp, page, count);
                        break;
                    case 2:
                        pre.byKeyword(temp, page, count);
                        break;
                    case 0:
                    default:
                }
                //刷新完成
                recy_show.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                //加载更多
                page++;
                if (temp == "") {
                    //加载完成
                    recy_show.loadMoreComplete();
                    return;
                }
                switch (status) {
                    case 1:
                        pre.byLabel(temp, page, count);
                        break;
                    case 2:
                        pre.byKeyword(temp, page, count);
                        break;
                    case 0:
                    default:
                }
                //加载完成
                recy_show.loadMoreComplete();
            }
        });

        //设置自定义搜索框的监听
        mySearchView.setListener(new DivSearchView.DivSearchListener() {
            @Override
            public void meun2ShowPw() {
                //展示、取下展示pw
                if (isShow) {
                    shopTypePw.dismiss();
                    isShow = false;
                } else {
                    shopTypePw.showAsDropDown(mySearchView);
                    isShow = true;
                }
            }

            @Override
            public void searchShopinfo(String text) {
                page = 1;
                categoryList.clear();
                keywordList.clear();
                //搜索然后展示数据
                pre.byKeyword(temp = text, page, count);
            }
        });

        //判断是否要展示 pw
        Intent intent = getIntent();
        isShow = intent.getBooleanExtra("showPw", false);
        if (isShow) {
            //展示pw
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    //延时
                    try {
                        do {
                            sleep(100);
                        } while (!hasFocus);
                    } catch (InterruptedException ignored) {
                    }
                    SearchActivity.this.runOnUiThread(() -> shopTypePw.showAsDropDown(mySearchView));
                }
            }.start();
        }
    }

    //设置适配器
    public void setKeywordAdap(List<JsonShopinfoByKeywordBean.ResultBean> list) {
        status = 2;
        keywordList.addAll(list);
        if (keywordAdap != null) {
            keywordAdap.notifyDataSetChanged();
            categoryAdap = null;
            return;
        }
        keywordAdap = new SearchKeywordAdap(keywordList);
        recy_show.setAdapter(keywordAdap);
        recy_show.setLayoutManager(new GridLayoutManager(this, 2));
    }

    //设置适配器
    public void setCategoryAdap(List<JsonShopinfoByCategoryBean.ResultBean> list) {
        status = 1;
        categoryList.addAll(list);
        if (categoryAdap != null) {
            categoryAdap.notifyDataSetChanged();
            keywordAdap = null;
            return;
        }
        categoryAdap = new SearchCategoryAdap(categoryList);
        recy_show.setAdapter(categoryAdap);
        recy_show.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    public int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    public ActivitySearchPresenter initPresenter() {
        return new ActivitySearchPresenter();
    }
}
