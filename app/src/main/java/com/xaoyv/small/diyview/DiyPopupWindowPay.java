package com.xaoyv.small.diyview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xaoyv.small.R;

/**
 * <p>Project's name:维度商城</p>
 * <p>tag:支付</p>
 *
 * @author Xaoyv
 * date 2020/11/3 06:09
 */
public class DiyPopupWindowPay extends PopupWindow {
    private TextView price;
    private ImageView img;
    private Button btn;

    public DiyPopupWindowPay(Context context) {
        super(View.inflate(context, R.layout.diy_pw_pay, null),
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        initView(context);
    }

    private void initView(Context context) {
        price = getContentView().findViewById(R.id.diy_pw_price);
        img = getContentView().findViewById(R.id.diy_pw_diss);
        btn = getContentView().findViewById(R.id.diy_pw_payNow);

        img.setOnClickListener(v -> {
            if (onClick != null)
                onClick.diss();
        });
        btn.setOnClickListener(v -> {
            if (onClick != null)
                onClick.pay();
        });
    }

    public void setPrice(int pri) {
        price.setText("请在2小时内完成付款，总金额 ： " + String.valueOf(pri) + "  元");
    }

    private OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public interface OnClick {
        void diss();

        void pay();
    }
}
