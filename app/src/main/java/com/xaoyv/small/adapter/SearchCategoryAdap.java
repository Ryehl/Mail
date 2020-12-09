package com.xaoyv.small.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xaoyv.small.R;
import com.xaoyv.small.bean.JsonShopinfoByCategoryBean;

import java.util.ArrayList;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:搜索</p>
 *
 * @author Xaoyv
 * date 2020/10/28 09:38
 */
public class SearchCategoryAdap extends RecyclerView.Adapter<SearchCategoryAdap.Holder> {


    class Holder extends RecyclerView.ViewHolder {
        SimpleDraweeView sdv;
        TextView name;
        TextView price;

        Holder(@NonNull View itemView) {
            super(itemView);
            sdv = itemView.findViewById(R.id.itemsearch_sdv_img);
            name = itemView.findViewById(R.id.itemsearch_tv_name);
            price = itemView.findViewById(R.id.itemsearch_tv_price);
        }
    }

    private ArrayList<JsonShopinfoByCategoryBean.ResultBean> list;

    public SearchCategoryAdap(ArrayList<JsonShopinfoByCategoryBean.ResultBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(View.inflate(parent.getContext(), R.layout.item_search, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        JsonShopinfoByCategoryBean.ResultBean categoryBean = list.get(position);
        //设置数据
        holder.sdv.setImageURI(categoryBean.getMasterPic());
        holder.name.setText(categoryBean.getCommodityName());
        holder.price.setText("￥：" + categoryBean.getPrice());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
