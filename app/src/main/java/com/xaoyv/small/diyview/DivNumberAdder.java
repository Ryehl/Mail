package com.xaoyv.small.diyview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.xaoyv.small.R;

/**
 * <p>项目名称:Yanxiaoyu20201030</p>
 * <p>简述:NumberAdder</p>
 *
 * @author Xaoyv
 * date 2020/10/30 09:27
 */
public class DivNumberAdder extends LinearLayout {
    TextView tv_count;

    public DivNumberAdder(Context context) {
        super(context);
        initView(context);
    }

    public DivNumberAdder(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public DivNumberAdder(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.div_number_adder, this);
        ImageView cut = view.findViewById(R.id.adder_img_cut);
        ImageView put = view.findViewById(R.id.adder_img_put);
        tv_count = view.findViewById(R.id.adder_tv_count);

        //set default number
        setAdderCount(1);

        //setListener
        cut.setOnClickListener(v -> {
            if (numberAdderListener != null)
                numberAdderListener.onCountCut();
        });
        put.setOnClickListener(v -> {
            if (numberAdderListener != null)
                numberAdderListener.onCountPut();
        });
    }

    private NumberAdderListener numberAdderListener;

    /**
     * set adder listener
     *
     * @param numberAdderListener new listener
     */
    public void setNumberAdderListener(NumberAdderListener numberAdderListener) {
        this.numberAdderListener = numberAdderListener;
    }

    public interface NumberAdderListener {
        void onCountCut();

        void onCountPut();
    }

    public void setAdderCount(int count) {
        if (tv_count != null)
            tv_count.setText(String.valueOf(count));
    }
}
