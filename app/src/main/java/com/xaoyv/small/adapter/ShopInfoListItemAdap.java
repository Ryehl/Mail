package com.xaoyv.small.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xaoyv.small.R;
import com.xaoyv.small.bean.JsonCommentBean;
import com.xaoyv.small.utils.TypeConversionUtils;

import java.util.List;

/**
 * <p>Project's name:维度商城</p>
 * <p>tag:评论 Adapter</p>
 *
 * @author Xaoyv
 * date 2020/11/3 08:56
 */
public class ShopInfoListItemAdap extends BaseAdapter {

    private List<JsonCommentBean.ResultBean> list;
    private Context context;

    ShopInfoListItemAdap(List<JsonCommentBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = View.inflate(context, R.layout.recy_item_circle_1, null);
        //find view by id
        SimpleDraweeView hd = convertView.findViewById(R.id.item_circle1_sdv_hd);
        TextView name = convertView.findViewById(R.id.item_circle1_tv_name);
        TextView date = convertView.findViewById(R.id.item_circle1_tv_date);
        TextView content = convertView.findViewById(R.id.item_circle1_tv_content);
        SimpleDraweeView show = convertView.findViewById(R.id.item_circle1_sdv_show0);
        //set data
        hd.setImageURI(list.get(position).getHeadPic());
        name.setText(list.get(position).getNickName());
        date.setText(TypeConversionUtils.long2String(list.get(position).getCreateTime()));
        content.setText(list.get(position).getContent());
        String[] images = list.get(position).getImage().split(",");
        show.setImageURI(images[0]);
        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
