package com.xaoyv.small.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xaoyv.small.R;
import com.xaoyv.small.bean.JsonHomeBean;

import java.util.ArrayList;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:首页多布局适配器</p>
 *
 * @author Xaoyv
 * date 2020/10/21 08:51
 */
public class HomeXRecyAdap extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<JsonHomeBean.ResultBean.RxxpBean.CommodityListBean> rxxpList = new ArrayList<>();
    private ArrayList<JsonHomeBean.ResultBean.MlssBean.CommodityListBeanXX> mlssList = new ArrayList<>();
    private ArrayList<JsonHomeBean.ResultBean.PzshBean.CommodityListBeanX> pzshList = new ArrayList<>();

    class RxxpHolder extends RecyclerView.ViewHolder {

        RecyclerView rxxpRecy;

        public RxxpHolder(@NonNull View itemView) {
            super(itemView);
            rxxpRecy = itemView.findViewById(R.id.item_rxxp_recy);
        }
    }

    class MlssHolder extends RecyclerView.ViewHolder {

        RecyclerView mlssRecy;

        public MlssHolder(@NonNull View itemView) {
            super(itemView);
            mlssRecy = itemView.findViewById(R.id.item_mlss_recy);
        }
    }

    class PzshHolder extends RecyclerView.ViewHolder {

        RecyclerView pzshRecy;

        public PzshHolder(@NonNull View itemView) {
            super(itemView);
            pzshRecy = itemView.findViewById(R.id.item_pzsh_recy);
        }
    }

    public HomeXRecyAdap(JsonHomeBean homeBean, Context context) {
        this.context = context;
        //拆分集合
        if (homeBean == null)
            return;
        rxxpList.addAll(homeBean.getResult().getRxxp().getCommodityList());
        mlssList.addAll(homeBean.getResult().getMlss().getCommodityList());
        pzshList.addAll(homeBean.getResult().getPzsh().getCommodityList());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (position) {
            case 0:
                RxxpHolder rxxpHolder = (RxxpHolder) holder;
                rxxpHolder.rxxpRecy.setAdapter(new RxxpAdap(rxxpList));
                rxxpHolder.rxxpRecy.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                break;
            case 1:
                MlssHolder mlssHolder = (MlssHolder) holder;
                mlssHolder.mlssRecy.setAdapter(new MlssAdap(mlssList));
                mlssHolder.mlssRecy.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                break;
            case 2:
                PzshHolder pzshHolder = (PzshHolder) holder;
                pzshHolder.pzshRecy.setAdapter(new PzshAdap(pzshList));
                pzshHolder.pzshRecy.setLayoutManager(new GridLayoutManager(context, 2));
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new RxxpHolder(View.inflate(parent.getContext(), R.layout.item_rxxp, null));
            case 1:
                return new MlssHolder(View.inflate(parent.getContext(), R.layout.item_mlss, null));
            default:
                return new PzshHolder(View.inflate(parent.getContext(), R.layout.item_pzsh, null));
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
