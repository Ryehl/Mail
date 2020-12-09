package com.xaoyv.small.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xaoyv.small.R;
import com.xaoyv.small.bean.JsonShopinfoByKeywordBean;

import java.util.ArrayList;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:搜索</p>
 *
 * @author Xaoyv
 * date 2020/10/28 09:38
 */
public class SearchKeywordAdap extends RecyclerView.Adapter<SearchKeywordAdap.KeywordHolder> {

    class KeywordHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView sdv;
        TextView name;
        TextView price;

        KeywordHolder(@NonNull View itemView) {
            super(itemView);
            sdv = itemView.findViewById(R.id.itemsearch_sdv_img);
            name = itemView.findViewById(R.id.itemsearch_tv_name);
            price = itemView.findViewById(R.id.itemsearch_tv_price);
        }
    }

    private ArrayList<JsonShopinfoByKeywordBean.ResultBean> list;

    public SearchKeywordAdap(ArrayList<JsonShopinfoByKeywordBean.ResultBean> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public KeywordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KeywordHolder(View.inflate(parent.getContext(), R.layout.item_search, null));
    }

    @Override
    public void onBindViewHolder(@NonNull KeywordHolder holder, int position) {
        //设置数据
        holder.sdv.setImageURI(list.get(position).getMasterPic());
        holder.name.setText(list.get(position).getCommodityName());
        holder.price.setText("￥：" + list.get(position).getPrice());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
