package com.xaoyv.small.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xaoyv.small.R;
import com.xaoyv.small.bean.JsonWalletBean;
import com.xaoyv.small.utils.TypeConversionUtils;

import java.util.List;

/**
 * <p>Project's name:维度商城</p>
 * <p>tag:Wallet adapter</p>
 *
 * @author Xaoyv
 * date 2020/11/2 17:30
 */
public class WalletAdap extends BaseAdapter {
    private List<JsonWalletBean.ResultBean.DetailListBean> list;
    private Context context;

    public WalletAdap(List<JsonWalletBean.ResultBean.DetailListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = View.inflate(context, R.layout.item_wallet, null);
        //find view by id
        TextView price = convertView.findViewById(R.id.item_wallet_price);
        TextView time = convertView.findViewById(R.id.item_wallet_time);
        //set text
        price.setText("$"+list.get(position).getAmount());
        time.setText(TypeConversionUtils.long2String(list.get(position).getConsumerTime()));
        return convertView;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
