package com.xaoyv.small.diyview;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xaoyv.small.R;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:更新弹窗</p>
 *
 * @author Xaoyv
 * date 2020/10/16 08:56
 */
public class DivUpdatePopupWindow extends PopupWindow {

    public DivUpdatePopupWindow(Context context) {
        super(View.inflate(context, R.layout.div_pw_update, null), WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
    }

    public void setListener(View.OnClickListener listener){
        TextView ok = getContentView().findViewById(R.id.div_update_pw_tv_ok);
        ok.setOnClickListener(listener);
    }

    public void setInfoText(String text){
        TextView info = getContentView().findViewById(R.id.div_update_pw_tv_info);
        info.setText(text);
    }
}
