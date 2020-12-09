package com.xaoyv.small.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xaoyv.small.R;
import com.xaoyv.small.bean.JsonMyCircleBean;

import java.util.List;

/**
 * <p>Project's name:维度商城</p>
 * <p>tag:My circle adapter</p>
 *
 * @author Xaoyv
 * date 2020/11/2 16:02
 */
public class MyCircleAdap extends RecyclerView.Adapter<MyCircleAdap.Holder> {
    private List<JsonMyCircleBean.ResultBean> list;

    public MyCircleAdap(List<JsonMyCircleBean.ResultBean> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        JsonMyCircleBean.ResultBean resultBean = list.get(position);
        //TODO set data
        holder.content.setText(resultBean.getContent());
        String images = resultBean.getImage();
        String[] split = images.split(",");
        holder.sdv.setImageURI(split[0]);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(View.inflate(parent.getContext(), R.layout.item_mycircle, null));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView content;
        SimpleDraweeView sdv;

        Holder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.item_mycircle_content);
            sdv = itemView.findViewById(R.id.item_mycircle_sdv);
        }
    }
}
