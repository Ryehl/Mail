package com.xaoyv.small.activity;

import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xaoyv.small.R;
import com.xaoyv.small.adapter.MyCircleAdap;
import com.xaoyv.small.base.BaseActivity;
import com.xaoyv.small.bean.JsonMyCircleBean;
import com.xaoyv.small.presenter.ActivityMycirclePresenter;

import java.util.ArrayList;
import java.util.List;

public class MycircleActivity extends BaseActivity<ActivityMycirclePresenter> {

    private ImageView back;
    private ImageView delete;
    private RecyclerView recy;
    private MyCircleAdap adap;

    private List<JsonMyCircleBean.ResultBean> list;

    private int page = 1, count = 10;

    @Override
    public void initView() {
        //find view by id
        back = findViewById(R.id.mycircle_img_back);
        delete = findViewById(R.id.mycircle_img_del);
        recy = findViewById(R.id.mycircle_recy);
    }

    @Override
    public void initData() {
        //TODO
        back.setOnClickListener(v -> finish());
        delete.setOnClickListener(v -> {
        });
        if (pre != null)
            pre.getInfo(page, count);
    }

    /**
     * set recycler's adapter
     * @param list data for result
     */
    public void setAdap(List<JsonMyCircleBean.ResultBean> list) {
        if (list == null) {
            if (adap != null) {
                list = new ArrayList<>();
                adap.notifyDataSetChanged();
            }
            return;
        }
        this.list = list;
        if (adap == null) {
            adap = new MyCircleAdap(list);
            recy.setAdapter(adap);
            recy.setLayoutManager(new LinearLayoutManager(this));
            return;
        }
        adap.notifyDataSetChanged();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_mycircle;
    }

    @Override
    public ActivityMycirclePresenter initPresenter() {
        return new ActivityMycirclePresenter();
    }
}
