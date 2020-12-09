package com.xaoyv.small.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xaoyv.small.R;
import com.xaoyv.small.bean.JsonAddressBean;

import java.util.List;

/**
 * <p>Project's name:维度商城</p>
 * <p>tag:address adap</p>
 *
 * @author Xaoyv
 * date 2020/11/2 18:24
 */
public class AddressAdap extends BaseAdapter {
    private List<JsonAddressBean.ResultBean> list;
private Context context;

    public AddressAdap(List<JsonAddressBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = View.inflate(context, R.layout.item_address, null);
        TextView name = convertView.findViewById(R.id.item_address_name);
        TextView phone = convertView.findViewById(R.id.item_address_phone);
        TextView address = convertView.findViewById(R.id.item_address_address);
        //set data
        name.setText(list.get(position).getRealName());
        phone.setText(list.get(position).getPhone());
        address.setText(list.get(position).getAddress());
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
