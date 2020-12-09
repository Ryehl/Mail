package com.xaoyv.small.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xaoyv.small.interfaces.IView;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:Activity基类</p>
 *
 * @author Xaoyv
 * date 2020/10/14 15:38
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IView<P> {

    public P pre;
    protected boolean hasFocus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        pre = initPresenter();
        if (pre != null)
            pre.attachView(this);
        initData();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        this.hasFocus = hasFocus;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //内存溢出处理
        if (pre != null)
            pre.detachView();
        pre = null;
    }
}
