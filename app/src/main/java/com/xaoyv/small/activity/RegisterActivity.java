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
import com.xaoyv.small.bean.JsonRegisterBean;
import com.xaoyv.small.presenter.ActivityRegisterPresenter;

import java.util.regex.Pattern;

public class RegisterActivity extends BaseActivity<ActivityRegisterPresenter> {

    private Button register;
    private EditText et_phone;
    private EditText et_pwd;
    private ImageView img;
    private TextView skip;

    boolean hide = true;

    @Override
    public void initView() {
        register = findViewById(R.id.reg_bt_reg);
        et_phone = findViewById(R.id.reg_et_phone);
        et_pwd = findViewById(R.id.reg_et_pwd);
        img = findViewById(R.id.reg_img_hide);
        skip = findViewById(R.id.reg_tv_skipToLog);
    }

    @Override
    public void initData() {
        //点击进行登录
        register.setOnClickListener(view -> {
            //获取phone 和 pwd
            String phone = et_phone.getText().toString().trim();
            String pwd = et_pwd.getText().toString().trim();
            if (phone.isEmpty() || pwd.isEmpty()) {
                Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!checkPhone(phone)) {
                Toast.makeText(this, "手机号不合法", Toast.LENGTH_SHORT).show();
                return;
            }
            pre.getData(phone, pwd);
        });

        //设置隐藏显示密码
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

            //设置
            et_pwd.setSelection(et_pwd.getText().length());
        });

        //跳转到登录页面
        skip.setOnClickListener(v -> {
            Intent in = new Intent(this, LoginActivity.class);
            startActivity(in);
        });
    }

    /**
     * 根据获取到的数据判断是否注册成功
     * 如果注册成功了，自动进行登录
     *
     * @param json 获取到的数据
     */
    public void getData(String json) {
        JsonRegisterBean registerBean = new Gson().fromJson(json, JsonRegisterBean.class);
        Toast.makeText(this, registerBean.getMessage(), Toast.LENGTH_SHORT).show();

        if (registerBean.getStatus().equals("0000")) {
            String get_phone = et_phone.getText().toString().trim();
            String get_pwd = et_pwd.getText().toString().trim();
            //将手机号和密码存储到数据库
            MMKV kv = MMKV.defaultMMKV();
            kv.putString("phone", get_phone);
            kv.putString("pwd", get_pwd);
            pre.login(get_phone, get_pwd);
            Intent in = new Intent(this, MainActivity.class);
            startActivity(in);
        }
    }

    /**
     * 检查手机号是否合法
     *
     * @param phone 要判断的手机号
     * @return true
     */
    private boolean checkPhone(String phone) {
        return Pattern.matches("^1[356789]\\d{9}$", phone);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    public ActivityRegisterPresenter initPresenter() {
        return new ActivityRegisterPresenter();
    }
}
