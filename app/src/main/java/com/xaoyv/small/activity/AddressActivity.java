package com.xaoyv.small.activity;

import android.widget.ListView;

import com.xaoyv.small.R;
import com.xaoyv.small.adapter.AddressAdap;
import com.xaoyv.small.base.BaseActivity;
import com.xaoyv.small.bean.JsonAddressBean;
import com.xaoyv.small.presenter.ActivityAddressPresenter;

import java.util.List;

public class AddressActivity extends BaseActivity<ActivityAddressPresenter> {

    private ListView lv;

    @Override
    public void initView() {
        lv = findViewById(R.id.address_lv);
    }

    @Override
    public void initData() {
        pre.getData();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_address;
    }

    @Override
    public ActivityAddressPresenter initPresenter() {
        return new ActivityAddressPresenter();
    }

    public void setData(List<JsonAddressBean.ResultBean> result) {
        lv.setAdapter(new AddressAdap(result, this));
    }
}
