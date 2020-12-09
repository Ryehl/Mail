package com.xaoyv.small.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xaoyv.small.R;
import com.xaoyv.small.bean.JsonShopInfoBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * <p>Project's name:维度商城</p>
 * <p>tag:BaseAdap</p>
 *
 * @author Xaoyv
 * date 2020/11/3 04:26
 */
public class TopayShopAdap extends BaseAdapter {
    private List<JsonShopInfoBean> list;
    private Context context;

    public TopayShopAdap(List<JsonShopInfoBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = View.inflate(context, R.layout.item_topay, null);

        //find view by id
        SimpleDraweeView sdv = convertView.findViewById(R.id.item_topay_sdv);
        TextView name = convertView.findViewById(R.id.item_topay_name);
        TextView price = convertView.findViewById(R.id.item_topay_price);

        //get picture
        JsonShopInfoBean.ResultBean result = list.get(position).getResult();
        String[] split = result.getPicture().split(",");

        //set data
        sdv.setImageURI(split[0]);
        name.setText(result.getCommodityName());
        price.setText("$:" + result.getPrice());
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
