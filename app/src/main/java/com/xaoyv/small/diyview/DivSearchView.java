package com.xaoyv.small.diyview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.xaoyv.small.R;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:自定义组合式搜索框</p>
 *
 * @author Xaoyv
 * date 2020/10/21 19:31
 */
public class DivSearchView extends LinearLayout {
    public DivSearchView(Context context) {
        super(context);
        initView(context);
    }

    public DivSearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public DivSearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.div_search, this);
        ImageView menu = view.findViewById(R.id.div_search_img);
        EditText input = view.findViewById(R.id.div_search_et);
        Button btn = view.findViewById(R.id.div_search_btn);

        menu.setOnClickListener(v -> {
            if (listener != null)
                listener.meun2ShowPw();
        });
        btn.setOnClickListener(v -> {
            String text = input.getText().toString().trim();
            if (text.isEmpty() || listener == null)
                return;
            listener.searchShopinfo(text);
        });
    }

    public interface DivSearchListener {
        void meun2ShowPw();

        void searchShopinfo(String text);
    }

    private DivSearchListener listener;

    public void setListener(DivSearchListener listener) {
        this.listener = listener;
    }
}
