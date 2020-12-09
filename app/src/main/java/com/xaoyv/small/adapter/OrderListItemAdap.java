package com.xaoyv.small.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xaoyv.small.R;
import com.xaoyv.small.bean.JsonOrderListBean;

import java.util.List;

/**
 * <p>Project's name:维度商城</p>
 * <p>tag:order list -- item</p>
 *
 * @author Xaoyv
 * date 2020/11/1 19:11
 */
public class OrderListItemAdap extends RecyclerView.Adapter<OrderListItemAdap.Holder> {
    private List<JsonOrderListBean.OrderListBean.DetailListBean> list;

    OrderListItemAdap(List<JsonOrderListBean.OrderListBean.DetailListBean> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        //set data
        String pics = list.get(position).getCommodityPic();
        String[] images = pics.split(",");
        holder.sdv.setImageURI(images[0]);
        holder.name.setText(list.get(position).getCommodityName());
        //set price and count !!! -> price and count is int(type)
        holder.price.setText(String.valueOf(list.get(position).getCommodityPrice()));
        holder.count.setText(String.valueOf(list.get(position).getCommodityCount()));
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(View.inflate(parent.getContext(), R.layout.item_order_item, null));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //Holder of  orderList's item view
    class Holder extends RecyclerView.ViewHolder {
        SimpleDraweeView sdv;
        TextView name;
        TextView price;
        TextView count;

        Holder(@NonNull View itemView) {
            super(itemView);
            sdv = itemView.findViewById(R.id.item_order_item_sdv);
            name = itemView.findViewById(R.id.item_order_item_tv_name);
            price = itemView.findViewById(R.id.item_order_item_tv_price);
            count = itemView.findViewById(R.id.item_order_item_count);
        }
    }
}
