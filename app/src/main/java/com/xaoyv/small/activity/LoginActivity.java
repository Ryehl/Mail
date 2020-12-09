package com.xaoyv.small.activity;

import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tencent.mmkv.MMKV;
import com.xaoyv.small.R;
import com.xaoyv.small.base.BaseActivity;
import com.xaoyv.small.bean.JsonLoginBean;
import com.xaoyv.small.bean.ConstantMMkv;
import com.xaoyv.small.presenter.ActivityLoginPresenter;

import java.util.regex.Pattern;

public class LoginActivity extends BaseActivity<ActivityLoginPresenter> {

    private EditText et_phone;
    private EditText et_pwd;
    private Button btn_login;
    private ImageView img;
    private TextView skip;
    private boolean hide = true;

    @Override
    public void initView() {
        //find view by id
        et_phone = findViewById(R.id.login_et_phone);
        et_pwd = findViewById(R.id.login_et_pwd);
        btn_login = findViewById(R.id.login_btn_login);
        img = findViewById(R.id.login_img_hide);
        skip = findViewById(R.id.login_tv_skipToReg);
    }

    @Override
    public void initData() {
        //从缓存读取数据
        MMKV kv = MMKV.defaultMMKV();
        String phone = kv.decodeString(ConstantMMkv.Key_Phone);
        String pwd = kv.decodeString(ConstantMMkv.Key_Pwd);
        et_phone.setText(phone);
        et_pwd.setText(pwd);

        //点击进行注册
        btn_login.setOnClickListener(v -> {
            String get_phone = et_phone.getText().toString().trim();
            String get_pwd = et_pwd.getText().toString().trim();
            //not null
            if (get_phone.isEmpty() || get_pwd.isEmpty()) {
                Toast.makeText(this, "请输入账号密码", Toast.LENGTH_SHORT).show();
                return;
            }
            // check format
            if (!checkPhone(get_phone)) {
                Toast.makeText(this, "手机号不合法", Toast.LENGTH_SHORT).show();
                return;
            }
            pre.getData(get_phone, get_pwd);
            kv.putString(ConstantMMkv.Key_Phone, get_phone);
            kv.putString(ConstantMMkv.Key_Pwd, get_pwd);
        });

        //点击显示、隐藏密码
        img.setOnClickListener(v -> {
            if (hide) {
                HideReturnsTransformationMethod hideReturnsTransformationMethod = new HideReturnsTransformationMethod();
                et_pwd.setTransformationMethod(hideReturnsTransformationMethod);
                img.setImageResource(R.drawable.open_eyes);
                hide = false;
            } else {
                PasswordTransformationMethod passwordTransformationMethod = new PasswordTransformationMethod();
                et_pwd.setTransformationMethod(passwordTransformationMethod);
                img.setImageResource(R.drawable.close_eyes);
                hide = true;
            }

            //设置 输入的下标
            et_pwd.setSelection(et_pwd.getText().length());
        });

        //点击注册
        skip.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    /**
     * 获得到Json数据判断是不是登录成功了
     *
     * @param json 获得到的数据
     */
    public void getData(String json) {
        JsonLoginBean loginBean = new Gson().fromJson(json, JsonLoginBean.class);
        Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        MMKV kv = MMKV.defaultMMKV();
        kv.putBoolean(ConstantMMkv.Key_IsLogin, true);
        if (loginBean.getStatus().equals("0000")) {
            //存储UserId和SessionId
            kv.putString(ConstantMMkv.Key_SessionId, loginBean.getResult().getSessionId());
            kv.putString(ConstantMMkv.Key_UserId, String.valueOf(loginBean.getResult().getUserId()));
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    /**
     * 检查手机号是否合法
     *
     * @param phone 需要判断的手机号
     * @return true
     */
    private boolean checkPhone(String phone) {
        return Pattern.matches("^1[356789]\\d{9}$", phone);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public ActivityLoginPresenter initPresenter() {
        return new ActivityLoginPresenter();
    }
}
