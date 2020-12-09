package com.xaoyv.small.activity;

import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xaoyv.small.R;
import com.xaoyv.small.adapter.MyFootAdap;
import com.xaoyv.small.base.BaseActivity;
import com.xaoyv.small.bean.JsonMyfootBean;
import com.xaoyv.small.presenter.ActivityFootPresenter;

import java.util.ArrayList;
import java.util.List;

public class FootActivity extends BaseActivity<ActivityFootPresenter> {

    private ImageView back;
    private ImageView delete;
    private RecyclerView recy;
    private MyFootAdap adap;
    private List<JsonMyfootBean.ResultBean> list;
    private int page = 1, count = 5;

    @Override
    public void initView() {
        //find view by id
        back = findViewById(R.id.foot_img_back);
        delete = findViewById(R.id.foot_img_del);
        recy = findViewById(R.id.foot_recy);
    }

    @Override
    public void initData() {
        //TODO
        pre.getData(page, count);

        //set listener
        back.setOnClickListener(v -> finish());
        delete.setOnClickListener(v ->{
            list = new ArrayList<>();
            setAdap(list);
        });
    }

    /**
     * set my foot adapter
     *
     * @param list data
     */
    public void setAdap(List<JsonMyfootBean.ResultBean> list) {
        if (list == null) {
            if (adap != null) {
                this.list = new ArrayList<>();
                adap.notifyDataSetChanged();
            }
            return;
        }
        this.list = list;
        if (adap == null) {
            recy.setAdapter(adap = new MyFootAdap(this.list));
            recy.setLayoutManager(new LinearLayoutManager(this));
            return;
        }
        adap.notifyDataSetChanged();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_foot;
    }

    @Override
    public ActivityFootPresenter initPresenter() {
        return new ActivityFootPresenter();
    }
}
