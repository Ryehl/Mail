package com.xaoyv.small.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xaoyv.small.R;
import com.xaoyv.small.bean.JsonShopCartBean;

import java.util.List;

import siney.cn.leftslideview.LeftSlideLinearManager;
import siney.cn.leftslideview.LeftSlideView;

/**
 * <p>项目名称:Yanxiaoyu20201030</p>
 * <p>简述:adapter with shopcart</p>
 *
 * @author Xaoyv
 * date 2020/10/30 09:51
 */
public class ShopCartAdap extends RecyclerView.Adapter<ShopCartAdap.Holder> {
    private JsonShopCartBean shopCartBean;

    public ShopCartAdap(JsonShopCartBean shopCartBean) {
        this.shopCartBean = shopCartBean;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        JsonShopCartBean.ResultBean resultBean = shopCartBean.getResult().get(position);
        //set data
        holder.name.setText(resultBean.getCategoryName());
        ShopCartItemAdap itemAdap = new ShopCartItemAdap(resultBean.getShoppingCartList());
        holder.left.setLayout(R.id.item_shop_root);
        holder.left.setItems(R.id.item_shop_left_del);
        holder.left.setAdapter(itemAdap);
        holder.left.setLayoutManager(new LeftSlideLinearManager(holder.itemView.getContext()));
        //set listener
        itemAdap.setItemListener(new ShopCartItemAdap.ItemListener() {
            @Override
            public void onPriceChanged() {
                //replace price
                if (groupListener != null)
                    groupListener.replaceCountAndPrice();
            }

            @Override
            public void onSelectChanged() {
                //check all item selected status
                boolean b = isItemAllSelect(position);
                //set bean
                resultBean.setCheck(b);

                //check all group select status
                boolean group = isGroupSelectAll();
                if (groupListener != null)
                    groupListener.groupSelectAll(group);
            }
        });
    }

    /**
     * is all item select?
     *
     * @param position list's position
     * @return boo
     */
    private boolean isItemAllSelect(int position) {
        List<JsonShopCartBean.ResultBean.ShoppingCartListBean> list = shopCartBean.getResult().get(position).getShoppingCartList();
        for (JsonShopCartBean.ResultBean.ShoppingCartListBean bean : list) {
            if (!bean.isItemCkeck()) {
                return false;
            }
        }
        //end
        return true;
    }

    /**
     * is group view select all?
     *
     * @return boo
     */
    private boolean isGroupSelectAll() {
        List<JsonShopCartBean.ResultBean> list = shopCartBean.getResult();
        for (JsonShopCartBean.ResultBean bean : list) {
            if (!bean.isCheck())
                return false;
        }
        //foreach end
        return true;
    }

    private GroupListener groupListener;

    public void setGroupListener(GroupListener groupListener) {
        this.groupListener = groupListener;
    }

    public interface GroupListener {
        void groupSelectAll(boolean boo);

        void replaceCountAndPrice();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(View.inflate(parent.getContext(), R.layout.item_shop, null));
    }

    @Override
    public int getItemCount() {
        if (shopCartBean.getResult() == null)
            return 0;
        return shopCartBean.getResult().size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView name;
        LeftSlideView left;

        Holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_main_cb);
            left = itemView.findViewById(R.id.item_main_recy);
        }
    }
}
