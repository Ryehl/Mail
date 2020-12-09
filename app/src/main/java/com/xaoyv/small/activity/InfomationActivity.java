package com.xaoyv.small.activity;

import android.net.Uri;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tencent.mmkv.MMKV;
import com.xaoyv.small.R;
import com.xaoyv.small.base.BaseActivity;
import com.xaoyv.small.bean.ConstantMMkv;
import com.xaoyv.small.presenter.InfomationPresenter;

public class InfomationActivity extends BaseActivity<InfomationPresenter> {

    SimpleDraweeView hd;
    TextView nickName;
    ImageView back;
    Button disLogin;

    @Override
    public void initView() {
        //find view by id
        hd = findViewById(R.id.infomation_img_hd);
        nickName = findViewById(R.id.infomation_tv_nickname);
        back = findViewById(R.id.infomation_img_back);
        disLogin = findViewById(R.id.infomation_btn_disLogin);
    }

    @Override
    public void initData() {
        //TODO 娱乐
        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources())
                .setRoundingParams(RoundingParams.asCircle())
                .build();
        hd.setHierarchy(hierarchy);
        MMKV kv = MMKV.defaultMMKV();
        String headPic = kv.decodeString(ConstantMMkv.Key_HeadPic);
        hd.setImageURI(Uri.parse(headPic));
        nickName.setText(kv.decodeString(ConstantMMkv.Key_NickName));
        //返回
        back.setOnClickListener(v -> finish());

        //TODO 点击上传头像、更改姓名
        disLogin.setOnClickListener(v -> {
            kv.putBoolean(ConstantMMkv.Key_IsLogin, false);
            finish();
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_infomation;
    }

    @Override
    public InfomationPresenter initPresenter() {
        return new InfomationPresenter();
    }
}
