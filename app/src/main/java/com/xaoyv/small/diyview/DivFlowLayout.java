package com.xaoyv.small.diyview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xaoyv.small.bean.JsonShoptypeBean;

import java.util.ArrayList;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:自定义流式布局</p>
 *
 * @author Xaoyv
 * date 2020/10/21 13:31
 */
public class DivFlowLayout extends ViewGroup {
    //间距
    int lineWidth = 70, lineHeight = 20;

    public DivFlowLayout(Context context) {
        super(context);
    }

    public DivFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DivFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //获取布局宽度和高度的数值
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //当前宽度高度
        int curWidth = 0, curHeight = getPaddingTop() + getPaddingBottom();
        //最大宽度
        int widthMax = 0;

        int i = 0;
        while (i < getChildCount()) {
            View childView = getChildAt(i);

            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            if (curWidth == 0) {
                curWidth = getPaddingLeft() + getPaddingRight();
                curHeight += childHeight + lineHeight;
            }
            if (curWidth + childWidth + lineWidth <= widthSize) {
                curWidth += childWidth + lineWidth;
                i++;
            } else {
                widthMax = Math.max(widthMax, curWidth);
                curWidth = 0;
            }
            if (i == getChildCount()) {
                widthMax = Math.max(widthMax, curWidth);
                curHeight += childHeight + lineHeight;
            }
        }

        //设置值
        setMeasuredDimension(
                widthMode == MeasureSpec.EXACTLY ? widthSize : widthMax,
                heightMode == MeasureSpec.EXACTLY ? heightSize : curHeight
        );
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int curWidth = getPaddingLeft();
        int curHeight = getPaddingTop();

        int i = 0;
        while (i < getChildCount()) {
            View childView = getChildAt(i);

            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();
            if (curWidth + getPaddingRight() + childWidth <= getWidth()) {
                childView.layout(
                        curWidth,
                        curHeight,
                        curWidth + childWidth,
                        curHeight + childHeight
                );
                curWidth += childWidth + lineWidth;
                i++;
            } else {
                curHeight += childHeight + lineHeight + getPaddingBottom();
                curWidth = getPaddingLeft();
            }
        }
    }

    public void addViews(ArrayList<JsonShoptypeBean.ResultBean.SecondCategoryVoBean> list) {
        for (JsonShoptypeBean.ResultBean.SecondCategoryVoBean bean :
                list) {
            TextView tempTv = new TextView(getContext());
            tempTv.setText(bean.getName());
            tempTv.setTextSize(20);
            tempTv.setOnClickListener(v -> {
                String id = bean.getId();
                if (listener != null)
                    listener.byLabel(id);
            });
            addView(tempTv);
        }
    }

    public void setViews(ArrayList<JsonShoptypeBean.ResultBean.SecondCategoryVoBean> list) {
        removeAllViews();
        for (JsonShoptypeBean.ResultBean.SecondCategoryVoBean bean :
                list) {
            TextView tempTv = new TextView(getContext());
            tempTv.setText(bean.getName());
            tempTv.setTextSize(20);
            tempTv.setTextColor(Color.WHITE);
            tempTv.setOnClickListener(v -> {
                String id = bean.getId();
                if (listener != null)
                    listener.byLabel(id);
            });
            addView(tempTv);
        }
    }

    public interface MyListener {
        void byLabel(String id);
    }

    private MyListener listener;

    /**
     * 设置监听器
     *
     * @param listener 监听
     */
    public void setListener(MyListener listener) {
        this.listener = listener;
    }
}
