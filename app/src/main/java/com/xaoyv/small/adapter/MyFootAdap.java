package com.xaoyv.small.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xaoyv.small.R;
import com.xaoyv.small.bean.JsonMyfootBean;
import com.xaoyv.small.utils.TypeConversionUtils;

import java.util.List;

/**
 * <p>Project's name:维度商城</p>
 * <p>tag:Foot adapter</p>
 *
 * @author Xaoyv
 * date 2020/11/2 16:38
 */
public class MyFootAdap extends RecyclerView.Adapter<MyFootAdap.Holder> {

    private List<JsonMyfootBean.ResultBean> list;

    public MyFootAdap(List<JsonMyfootBean.ResultBean> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        //TODO set data
        JsonMyfootBean.ResultBean resultBean = list.get(position);
        holder.sdv.setImageURI(resultBean.getMasterPic());
        holder.name.setText(resultBean.getCommodityName());
        holder.price.setText("$" + resultBean.getPrice());
        holder.count.setText("已浏览" + resultBean.getBrowseNum() + "次");
        //最后一次浏览的时间
        holder.date.setText(TypeConversionUtils.long2String(list.get(position).getBrowseTime()));
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(View.inflate(parent.getContext(), R.layout.item_myfoot, null));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        SimpleDraweeView sdv;
        TextView name;
        TextView price;
        TextView count;
        TextView date;

        Holder(@NonNull View itemView) {
            super(itemView);
            sdv = itemView.findViewById(R.id.item_myfoot_sdv);
            name = itemView.findViewById(R.id.item_myfoot_name);
            price = itemView.findViewById(R.id.item_myfoot_price);
            count = itemView.findViewById(R.id.item_myfoot_count);
            date = itemView.findViewById(R.id.item_myfoot_date);
        }
    }
}
