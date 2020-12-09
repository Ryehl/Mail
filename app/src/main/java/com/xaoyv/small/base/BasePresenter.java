package com.xaoyv.small.base;

import com.xaoyv.small.interfaces.IView;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:Presenter基类</p>
 *
 * @author Xaoyv
 * date 2020/10/14 16:54
 */
abstract public class BasePresenter<V extends IView> {

    public V iView;

    public void attachView(V iView){
        this.iView = iView;
    }

    void detachView(){
        this.iView = null;
    }

}
