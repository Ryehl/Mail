package com.xaoyv.small.presenter;

import com.xaoyv.small.base.BasePresenter;
import com.xaoyv.small.fragment.MainMyFrag;
import com.xaoyv.small.utils.NetUtils;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:FragMainMyPresenter</p>
 *
 * @author Xaoyv
 * date 2020/10/20 10:00
 */
public class FragMainMyPresenter extends BasePresenter<MainMyFrag> {
    public void autoLogin(String phone, String pwd) {
        //自动登录
        if (phone.isEmpty() || pwd.isEmpty())
            return;
    }
}
