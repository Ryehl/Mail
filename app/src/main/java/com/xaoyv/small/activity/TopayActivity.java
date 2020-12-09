package com.xaoyv.small.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.xaoyv.small.R;
import com.xaoyv.small.adapter.TopayShopAdap;
import com.xaoyv.small.base.BaseActivity;
import com.xaoyv.small.bean.ConstantParameter;
import com.xaoyv.small.bean.DiyBean;
import com.xaoyv.small.bean.JsonAddressBean;
import com.xaoyv.small.bean.JsonShopInfoBean;
import com.xaoyv.small.diyview.DiyPopupWindowPay;
import com.xaoyv.small.presenter.TopayPresenter;

import java.util.ArrayList;
import java.util.List;

public class TopayActivity extends BaseActivity<TopayPresenter> {

    private ListView lv;
    private TextView name;
    private TextView phone;
    private TextView address;
    private Button topay;
    private boolean a = false, b = false;
    private int addressId = -1;
    private int price = -1;


    @Override
    public void initView() {
        lv = findViewById(R.id.topay_lv);
        name = findViewById(R.id.topay_address_name);
        phone = findViewById(R.id.topay_address_phone);
        address = findViewById(R.id.topay_address_address);
        topay = findViewById(R.id.topay_btn_topay);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String info = intent.getStringExtra("info");
        int count = intent.getIntExtra(ConstantParameter.count, -1);
        price = intent.getIntExtra(ConstantParameter.price, -1);
        JsonParser parser = new JsonParser();
        JsonElement parse = parser.parse(info);
        JsonArray asJsonArray = parse.getAsJsonArray();
        Gson gson = new Gson();
        ArrayList<DiyBean> list = new ArrayList<>();
        for (JsonElement e :
                asJsonArray) {
            DiyBean diyBean = gson.fromJson(e, DiyBean.class);
            list.add(diyBean);
        }
        //get address
        pre.getAddress();
        //get shopinfo
        pre.getShopsInfo(list);

        topay.setOnClickListener(v -> {
            if (!(a && b))
                return;
            //创建订单 去付款
            pre.createOrder(info, price, addressId);
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_topay;
    }

    @Override
    public TopayPresenter initPresenter() {
        return new TopayPresenter();
    }

    public void setAddress(JsonAddressBean addressBean) {
        for (JsonAddressBean.ResultBean resultBean : addressBean.getResult()) {
            if (resultBean.getWhetherDefault() == 1) {
                //is default address
                name.setText(resultBean.getRealName());
                phone.setText(resultBean.getPhone());
                address.setText(resultBean.getAddress());
                a = true;
                addressId = resultBean.getId();
                return;
            }
        }
        a = true;
    }

    public void setAdap(List<JsonShopInfoBean> list) {
        TopayShopAdap adap = new TopayShopAdap(list, this);
        lv.setAdapter(adap);
        b = true;
    }

    public void pay(String orderId) {
        DiyPopupWindowPay pw = new DiyPopupWindowPay(this);
        pw.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        pw.setPrice(price);
        pw.setOnClick(new DiyPopupWindowPay.OnClick() {
            @Override
            public void diss() {
                pw.dismiss();
                Toast.makeText(TopayActivity.this, "取消支付", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void pay() {
                pre.payNow(orderId);
                Toast.makeText(TopayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                pw.dismiss();
            }
        });
    }
}
