package com.xaoyv.small.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.xaoyv.small.R;
import com.xaoyv.small.base.BaseActivity;
import com.xaoyv.small.base.BasePresenter;
import com.xaoyv.small.utils.PhoneInfo;
import com.xaoyv.small.utils.ViewUtils;

public class WelcomeActivity extends BaseActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private ImageView img;
    private TextView cd;
    private Button getper;

    int time = 4;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                if (time > 1){
                    time--;
                    cd.setText("跳过 "+time);
                    //循环发送
                    handler.sendEmptyMessageDelayed(0, 1000);
                }else {
                    cd.setText("跳过 0");
                    skip();
                }
            }
        }
    };

    @Override
    public int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initView() {
        img =  findViewById(R.id.welcome_img_showImg);
        cd = findViewById(R.id.welcome_tv_countDown);
        getper = findViewById(R.id.welcome_btn_getPermission);
    }

    @Override
    public void initData() {
        //获取权限
        getPermission();

        //设置通知栏透明
        ViewUtils.setViewTransparent(this);
        getper.setEnabled(false);
        getper.setAlpha(0);

        //初始化欢迎页
        initWelcomePic();

        //开始倒计时
        handler.sendEmptyMessage(0);

        //设置监听
        cd.setOnClickListener(v -> skip());
    }

    //启动intent，跳转下一个界面
    private void skip() {
        if (!checkPer()){
            reGetPer();
            return;
        }
        handler.removeCallbacksAndMessages(null);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void reGetPer() {
        getper.setEnabled(true);
        getper.setAlpha(1);
        TextView show = findViewById(R.id.welcome_tv_showWhy);
        show.setText("移动网络信息、WiFi信息、GPS信息是本软件所需要的基础服务，如果App未被授予权限，系统会拒绝向App提供数据服务，无法展示数据及提供优质服务等，所以会在第一次使用本App的时候需要您进行以下配置：\n" +
                "\t\t授权获取本机信息\n" +
                "\t\t授权获取本地文件读写权限\n" +
                "\t\t获取定位权限来对您进行个性化商品推荐\n" +
                "\t\t获取摄像头使用权，来进行头像上传等");
        getper.setOnClickListener(v -> {
            getPermission();
            skip();
        });
    }

    /**
     * 检查权限
     * @return true 代表有权限
     */
    private boolean checkPer() {
        return
                PackageManager.PERMISSION_GRANTED == getPackageManager().checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, getPackageName()) &&
                PackageManager.PERMISSION_GRANTED == getPackageManager().checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, getPackageName()) &&
                PackageManager.PERMISSION_GRANTED == getPackageManager().checkPermission(Manifest.permission.ACCESS_NETWORK_STATE, getPackageName())&&
                PackageManager.PERMISSION_GRANTED == getPackageManager().checkPermission(Manifest.permission.CAMERA, getPackageName());
    }

    /**
     * 获取手机权限
     */
    private void getPermission() {
        //获取手机权限，先判断手机版本
        if (PhoneInfo.getAndroidSdkVersion() >= 23){
            //请求权限
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.INTERNET,//完全的网络访问权限
                    Manifest.permission.ACCESS_NETWORK_STATE,//判断网络的权限
                    Manifest.permission.READ_EXTERNAL_STORAGE,//读取文件
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,//写入文件
                    Manifest.permission.ACCESS_FINE_LOCATION,//精确位置 GPS
                    Manifest.permission.ACCESS_COARSE_LOCATION,//粗略位置 基于网络
                    Manifest.permission.CAMERA
            },1);
        }
    }

    /**
     * 初始化欢迎页图片
     */
    private void initWelcomePic() {
        //随机选择图片
        switch ((int) (Math.random() * 6)){
            case 0:
                img.setImageResource(R.drawable.start_0);
                break;
            case 1:
                img.setImageResource(R.drawable.start_1);
                break;
            case 2:
                img.setImageResource(R.drawable.start_2);
                break;
            case 3:
                img.setImageResource(R.drawable.start_3);
                break;
            case 4:
                img.setImageResource(R.drawable.start_4);
                break;
            case 5:
                img.setImageResource(R.drawable.start_5);
        }
        //设置拉伸展示
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                //Toast.makeText(this, "请求成功", Toast.LENGTH_SHORT).show();
                break;

            default:
                //Toast.makeText(this, "请求失败", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
