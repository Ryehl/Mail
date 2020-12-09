package com.xaoyv.small.activity;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xaoyv.small.R;
import com.xaoyv.small.adapter.WalletAdap;
import com.xaoyv.small.base.BaseActivity;
import com.xaoyv.small.bean.JsonWalletBean;
import com.xaoyv.small.presenter.ActivityWalletPresenter;

public class WalletActivity extends BaseActivity<ActivityWalletPresenter> {

    private ImageView back;
    private TextView wallet;
    private ListView lv;

    int page = 1, count = 10;

    @Override
    public void initView() {
        //find view by id
        back = findViewById(R.id.wallet_img_back);
        wallet = findViewById(R.id.wallet_tv_wallet);
        lv = findViewById(R.id.wallet_lv);
    }

    @Override
    public void initData() {
        //TODO
        if (pre != null)
            pre.getData(page, count);
        back.setOnClickListener(v -> finish());
    }

    @Override
    public int getLayout() {
        return R.layout.activity_wallet;
    }

    @Override
    public ActivityWalletPresenter initPresenter() {
        return new ActivityWalletPresenter();
    }

    /**
     * set data
     *
     * @param result bean data
     */
    public void setData(JsonWalletBean.ResultBean result) {
        wallet.setText(String.valueOf(result.getBalance()));
        lv.setAdapter(new WalletAdap(result.getDetailList(), this));
    }
}
