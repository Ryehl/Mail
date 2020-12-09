package com.xaoyv.small.fragment;

import android.content.Intent;
import android.widget.LinearLayout;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tencent.mmkv.MMKV;
import com.xaoyv.small.R;
import com.xaoyv.small.activity.AddressActivity;
import com.xaoyv.small.activity.FootActivity;
import com.xaoyv.small.activity.InfomationActivity;
import com.xaoyv.small.activity.LoginActivity;
import com.xaoyv.small.activity.MycircleActivity;
import com.xaoyv.small.activity.WalletActivity;
import com.xaoyv.small.base.BaseFragment;
import com.xaoyv.small.bean.ConstantMMkv;
import com.xaoyv.small.presenter.FragMainMyPresenter;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:MainMyFrag</p>
 *
 * @author Xaoyv
 * date 2020/10/20 10:00
 */
public class MainMyFrag extends BaseFragment<FragMainMyPresenter> {
    private SimpleDraweeView sdv_hd_small;
    private SimpleDraweeView sdv_hd_big;

    private LinearLayout information;
    private LinearLayout circle;
    private LinearLayout foot;
    private LinearLayout wallet;
    private LinearLayout address;

    @Override
    public void initView() {
        sdv_hd_small = getView().findViewById(R.id.frag_my_hd_small);
        sdv_hd_big = getView().findViewById(R.id.frag_my_hd_big);
        information = getView().findViewById(R.id.frag_my_ll_profile);
        circle = getView().findViewById(R.id.frag_my_ll_circle);
        foot = getView().findViewById(R.id.frag_my_ll_foot);
        wallet = getView().findViewById(R.id.frag_my_ll_wallet);
        address = getView().findViewById(R.id.frag_my_ll_address);
    }

    @Override
    public void initData() {
        //判断有没有登录
        MMKV kv = MMKV.defaultMMKV();
        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources())
                .setRoundingParams(RoundingParams.asCircle())
                .setPlaceholderImage(R.drawable.hd_loading)
                .setFailureImage(R.mipmap.ic_launcher)
                .build();
        if (kv.decodeBool(ConstantMMkv.Key_IsLogin)) {
            //set icon
            sdv_hd_small.setHierarchy(hierarchy);
            sdv_hd_small.setImageURI(kv.decodeString(ConstantMMkv.Key_HeadPic));
            sdv_hd_big.setImageURI(kv.decodeString(ConstantMMkv.Key_HeadPic));
            //set listener
            sdv_hd_small.setOnClickListener(v -> {
                Intent in = new Intent(getActivity(), InfomationActivity.class);
                startActivity(in);
            });
            information.setOnClickListener(v -> {
                //个人信息
                Intent intent = new Intent(getActivity(), InfomationActivity.class);
                startActivity(intent);
            });
            circle.setOnClickListener(v -> {
                //我的圈子
                Intent intent = new Intent(getActivity(), MycircleActivity.class);
                startActivity(intent);
            });
            foot.setOnClickListener(v -> {
                //我的足迹
                Intent intent = new Intent(getActivity(), FootActivity.class);
                startActivity(intent);
            });
            wallet.setOnClickListener(v -> {
                //我的钱包
                Intent intent = new Intent(getActivity(), WalletActivity.class);
                startActivity(intent);
            });
            address.setOnClickListener(v -> {
                //我的地址
                Intent intent = new Intent(getActivity(), AddressActivity.class);
                startActivity(intent);
            });
        } else {
            //未登录
            sdv_hd_small.setHierarchy(hierarchy);
            sdv_hd_small.setImageResource(R.drawable.my_nolog);
            sdv_hd_big.setImageResource(R.drawable.my_nolog);
            //set listener to login
            sdv_hd_small.setOnClickListener(v -> toLogin());
            information.setOnClickListener(v -> toLogin());
            circle.setOnClickListener(v -> toLogin());
            foot.setOnClickListener(v -> toLogin());
            wallet.setOnClickListener(v -> toLogin());
            address.setOnClickListener(v -> toLogin());
        }
    }

    private void toLogin() {
        Intent in = new Intent(getContext(), LoginActivity.class);
        startActivity(in);
    }

    @Override
    public int getLayout() {
        //TODO 个人中心布局
        return R.layout.frag_main_my;
    }

    @Override
    public FragMainMyPresenter initPresenter() {
        return new FragMainMyPresenter();
    }
}
