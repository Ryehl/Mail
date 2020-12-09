package com.xaoyv.small.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xaoyv.small.R;
import com.xaoyv.small.application.MyApplication;
import com.xaoyv.small.bean.JsonShopCartBean;
import com.xaoyv.small.diyview.DivNumberAdder;

import java.util.List;

/**
 * <p>项目名称:Yanxiaoyu20201030</p>
 * <p>简述:shopcart item adapter</p>
 *
 * @author Xaoyv
 * date 2020/10/30 09:58
 */
public class ShopCartItemAdap extends RecyclerView.Adapter<ShopCartItemAdap.Holder> {

    private List<JsonShopCartBean.ResultBean.ShoppingCartListBean> list;

    ShopCartItemAdap(List<JsonShopCartBean.ResultBean.ShoppingCartListBean> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        JsonShopCartBean.ResultBean.ShoppingCartListBean bean = list.get(position);
        //set data
        holder.cb.setChecked(bean.isItemCkeck());
        holder.sdv.setImageURI(bean.getPic());
        holder.name.setText(bean.getCommodityName());
        holder.price.setText("$:" + bean.getPrice());
        holder.adder.setAdderCount(bean.getCount());

        //set listener
        holder.adder.setNumberAdderListener(new DivNumberAdder.NumberAdderListener() {
            @Override
            public void onCountCut() {
                int count_old = bean.getCount();
                if (count_old <= 1) {
                    Toast.makeText(MyApplication.context, "已经是最小数量了，不能再减少了", Toast.LENGTH_SHORT).show();
                    return;
                }
                //change bean
                bean.setCount(count_old - 1);
                //change view
                holder.adder.setAdderCount(bean.getCount());
                //all of price was changed
                if (itemListener != null)
                    itemListener.onPriceChanged();
            }

            @Override
            public void onCountPut() {
                int count_old = bean.getCount();
                //change bean
                bean.setCount(count_old + 1);
                //change view
                holder.adder.setAdderCount(bean.getCount());
                //all of price was changed
                if (itemListener != null)
                    itemListener.onPriceChanged();
            }
        });
        holder.cb.setOnClickListener(v -> {
            //set bean
            bean.setItemCkeck(holder.cb.isChecked());
            //do listener
            if (itemListener != null) {
                itemListener.onSelectChanged();
                itemListener.onPriceChanged();
            }
        });
        holder.del.setOnClickListener(v -> {
            //TODO delete shopCart item
            list.remove(position);
            notifyDataSetChanged();
        });
    }

    private ItemListener itemListener;

    void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    interface ItemListener {
        void onPriceChanged();

        void onSelectChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(View.inflate(parent.getContext(), R.layout.item_shop_recy, null));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        CheckBox cb;
        SimpleDraweeView sdv;
        TextView name;
        TextView price;
        DivNumberAdder adder;
        TextView del;

        Holder(@NonNull View itemView) {
            super(itemView);
            //find view by id
            cb = itemView.findViewById(R.id.item_recy_cb);
            sdv = itemView.findViewById(R.id.item_recy_sdv);
            name = itemView.findViewById(R.id.item_recy_name);
            price = itemView.findViewById(R.id.item_recy_price);
            adder = itemView.findViewById(R.id.item_recy_adder);
            del = itemView.findViewById(R.id.item_shop_left_del);
        }
    }
}
