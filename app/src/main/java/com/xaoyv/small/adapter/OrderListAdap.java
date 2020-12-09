package com.xaoyv.small.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xaoyv.small.R;
import com.xaoyv.small.bean.JsonOrderListBean;
import com.xaoyv.small.utils.TypeConversionUtils;

import java.util.List;

/**
 * <p>Project's name:维度商城</p>
 * <p>tag:order list adapter</p>
 *
 * @author Xaoyv
 * date 2020/10/31 10:59
 */
public class OrderListAdap extends RecyclerView.Adapter {
    private List<JsonOrderListBean.OrderListBean> list;

    public OrderListAdap(List<JsonOrderListBean.OrderListBean> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //TODO
        switch (getItemViewType(position)) {
            case 1:
                //order list of none pay
                PayHolder payHolder = (PayHolder) holder;
                payHolder.orderCode.setText("订单号" + list.get(position).getOrderId());
                //set create time
                payHolder.createTime.setText(TypeConversionUtils.long2String(list.get(position).getOrderTime()));
                //set list
                payHolder.recy.setAdapter(new OrderListItemAdap(list.get(position).getDetailList()));
                payHolder.recy.setLayoutManager(new LinearLayoutManager(payHolder.itemView.getContext()));
                //TODO set buttons's listener
                payHolder.btn_del.setOnClickListener(v -> {
                    //delete
                });
                payHolder.btn_toPay.setOnClickListener(v -> {
                    //pay
                });
                break;
            case 2:
                ReceiveHolder receiveHolder = (ReceiveHolder) holder;
                receiveHolder.orderCode.setText("订单号" + list.get(position).getOrderId());
                receiveHolder.createTime.setText(TypeConversionUtils.long2String(list.get(position).getOrderTime()));
                receiveHolder.express.setText("快递单号" + list.get(position).getExpressSn());
                receiveHolder.company.setText("派件公司：" + list.get(position).getExpressCompName());
                //set list's data
                receiveHolder.recy.setAdapter(new OrderListItemAdap(list.get(position).getDetailList()));
                receiveHolder.recy.setLayoutManager(new LinearLayoutManager(receiveHolder.itemView.getContext()));
                //TODO set button listener
                receiveHolder.receive.setOnClickListener(v -> {
                    //receive
                });
                break;
            case 3:
                CommentHolder commentHolder = (CommentHolder) holder;
                commentHolder.orderCode.setText("订单号" + list.get(position).getOrderId());
                commentHolder.recy.setAdapter(new OrderListItemAdap(list.get(position).getDetailList()));
                commentHolder.recy.setLayoutManager(new LinearLayoutManager(commentHolder.itemView.getContext()));
                commentHolder.img.setOnClickListener(v -> {
                    //TODO popup window
                });
                commentHolder.comment.setOnClickListener(v -> {
                    //comment
                });
                break;
            default:
                FinishHolder finishHolder = (FinishHolder) holder;
                finishHolder.orderCode.setText("订单号" + list.get(position).getOrderId());
                finishHolder.recy.setAdapter(new OrderListItemAdap(list.get(position).getDetailList()));
                finishHolder.recy.setLayoutManager(new LinearLayoutManager(finishHolder.itemView.getContext()));
                finishHolder.sandian.setOnClickListener(v -> {
                    //popup window
                });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                return new PayHolder(View.inflate(parent.getContext(), R.layout.item_order_pay, null));
            case 2:
                return new ReceiveHolder(View.inflate(parent.getContext(), R.layout.item_order_receive, null));
            case 3:
                return new CommentHolder(View.inflate(parent.getContext(), R.layout.item_order_comment, null));
            default:
                return new FinishHolder(View.inflate(parent.getContext(), R.layout.item_order_finish, null));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        //get status
        return list.get(position).getOrderStatus();
    }

    //pay
    class PayHolder extends RecyclerView.ViewHolder {
        TextView orderCode;
        TextView createTime;
        RecyclerView recy;
        TextView countAndPrice;
        Button btn_del;
        Button btn_toPay;

        PayHolder(@NonNull View itemView) {
            super(itemView);
            orderCode = itemView.findViewById(R.id.item_order_pay_orderCode);
            createTime = itemView.findViewById(R.id.item_order_pay_createTime);
            recy = itemView.findViewById(R.id.item_order_pay_recy);
            countAndPrice = itemView.findViewById(R.id.item_order_pay_countAndPrice);
            btn_del = itemView.findViewById(R.id.item_order_pay_btn_delete);
            btn_toPay = itemView.findViewById(R.id.item_order_pay_Pay);
        }
    }

    //receive
    class ReceiveHolder extends RecyclerView.ViewHolder {
        TextView orderCode;
        TextView createTime;
        RecyclerView recy;
        TextView company;
        TextView express;
        Button receive;

        ReceiveHolder(@NonNull View itemView) {
            super(itemView);
            orderCode = itemView.findViewById(R.id.item_order_receive_orderCode);
            createTime = itemView.findViewById(R.id.item_order_receive_createTime);
            recy = itemView.findViewById(R.id.item_order_receive_recy);
            company = itemView.findViewById(R.id.item_order_receive_expressCompany);
            express = itemView.findViewById(R.id.item_order_receive_express);
            receive = itemView.findViewById(R.id.item_order_receive_receive);
        }
    }

    //comment
    class CommentHolder extends RecyclerView.ViewHolder {
        TextView orderCode;
        ImageView img;
        RecyclerView recy;
        Button comment;

        CommentHolder(@NonNull View itemView) {
            super(itemView);
            orderCode = itemView.findViewById(R.id.item_order_comment_orderCode);
            img = itemView.findViewById(R.id.item_order_comment_sandian);
            recy = itemView.findViewById(R.id.item_order_comment_recy);
            comment = itemView.findViewById(R.id.item_order_comment_comment);
        }
    }

    //finish
    class FinishHolder extends RecyclerView.ViewHolder {
        TextView orderCode;
        ImageView sandian;
        RecyclerView recy;

        FinishHolder(@NonNull View itemView) {
            super(itemView);
            orderCode = itemView.findViewById(R.id.item_order_finish_orderCode);
            sandian = itemView.findViewById(R.id.item_order_finish_sandian);
            recy = itemView.findViewById(R.id.item_order_finish_recy);
        }
    }
}
