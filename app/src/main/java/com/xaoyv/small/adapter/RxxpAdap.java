package com.xaoyv.small.adapter;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xaoyv.small.R;
import com.xaoyv.small.activity.ShopInfoActivity;
import com.xaoyv.small.bean.JsonHomeBean;

import java.util.ArrayList;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:热销新品适配器</p>
 *
 * @author Xaoyv
 * date 2020/10/21 09:08
 */
public class RxxpAdap extends RecyclerView.Adapter<RxxpAdap.Holder> {

    private ArrayList<JsonHomeBean.ResultBean.RxxpBean.CommodityListBean> list;

    RxxpAdap(ArrayList<JsonHomeBean.ResultBean.RxxpBean.CommodityListBean> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        //设置数据
        holder.sdv.setImageURI(list.get(position).getMasterPic());
        holder.name.setText(list.get(position).getCommodityName());
        holder.price.setText("￥：" + list.get(position).getPrice());
        //设置监听器
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ShopInfoActivity.class);
            intent.putExtra("commodityId", list.get(position).getCommodityId());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(View.inflate(parent.getContext(), R.layout.recy_item_rxxp, null));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        SimpleDraweeView sdv;
        TextView name;
        TextView price;

        Holder(@NonNull View itemView) {
            super(itemView);
            sdv = itemView.findViewById(R.id.item_rxxp_sdv);
            name = itemView.findViewById(R.id.item_rxxp_tv_name);
            price = itemView.findViewById(R.id.item_rxxp_tv_price);
        }
    }
}
