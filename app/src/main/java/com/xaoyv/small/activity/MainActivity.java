package com.xaoyv.small.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.tencent.mmkv.MMKV;
import com.xaoyv.small.R;
import com.xaoyv.small.base.BaseActivity;
import com.xaoyv.small.bean.Constant;
import com.xaoyv.small.bean.ConstantMMkv;
import com.xaoyv.small.diyview.DivUpdatePopupWindow;
import com.xaoyv.small.fragment.MainCircleFrag;
import com.xaoyv.small.fragment.MainHomeFrag;
import com.xaoyv.small.fragment.MainListFrag;
import com.xaoyv.small.fragment.MainMyFrag;
import com.xaoyv.small.fragment.MainShopFragment;
import com.xaoyv.small.presenter.ActivityMainPresenter;
import com.xaoyv.small.utils.AppUtils;
import com.xaoyv.small.utils.InternetUtil;
import com.xaoyv.small.utils.ViewUtils;

public class MainActivity extends BaseActivity<ActivityMainPresenter> {

    public DivUpdatePopupWindow pw;
    TabLayout tab;
    //点击返回时的时间
    Long exitTime = 0L;

    @Override
    public void initView() {
        tab = findViewById(R.id.main_tab_bottom);
    }

    @Override
    public void initData() {
        //设置沉浸式
        ViewUtils.setViewTransparent(this);

        //判断是否登录，是否保存密码
        MMKV kv = MMKV.defaultMMKV();
        boolean isLogin = kv.decodeBool(ConstantMMkv.Key_IsLogin);
        Log.d(Constant.TAG, "initData: " + isLogin);
        //if (isLogin) {
        String phone = kv.decodeString(ConstantMMkv.Key_Phone);
        Log.d(Constant.TAG, "initData: " + phone);
        String pwd = kv.decodeString(ConstantMMkv.Key_Pwd);
        Log.d(Constant.TAG, "initData: " + pwd);
        pre.updateUserIdAndSessionID(phone, pwd);
        //}

        //初始化Tabs
        initTab();

        //动态加载Fragment首页
        FragmentManager fm = getSupportFragmentManager();

        //设置TabLayout的监听
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选择 -- 重置Fragment
                FragmentTransaction temp = fm.beginTransaction();
                switch (tab.getPosition()) {
                    case 0:
                        temp.replace(R.id.main_frame_show, new MainHomeFrag());
                        tab.setIcon(R.drawable.common_tab_btn_home_s_xxhdpi);
                        ViewUtils.setViewTransparent(MainActivity.this);
                        break;
                    case 1:
                        temp.replace(R.id.main_frame_show, new MainCircleFrag());
                        tab.setIcon(R.drawable.common_tab_btn_circle_s_xxhdpi);
                        break;
                    case 2:
                        temp.replace(R.id.main_frame_show, new MainShopFragment());
                        break;
                    case 3:
                        temp.replace(R.id.main_frame_show, new MainListFrag());
                        tab.setIcon(R.drawable.common_tab_btn_list_s_xxhdpi);
                        break;
                    case 4:
                        temp.replace(R.id.main_frame_show, new MainMyFrag());
                        tab.setIcon(R.drawable.common_tab_btn_my_s_xxhdpi);
                        ViewUtils.setViewTransparent(MainActivity.this);
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "程序异常！", Toast.LENGTH_SHORT).show();
                }
                temp.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //取消选择，设置图片
                switch (tab.getPosition()) {
                    case 0:
                        tab.setIcon(R.drawable.common_tab_btn_home_n_xxhdpi);
                        ViewUtils.unSetViewTransparent(MainActivity.this);
                        break;
                    case 1:
                        tab.setIcon(R.drawable.common_tab_btn_circle_n_xxhdpi);
                        break;
                    case 2:
                        break;
                    case 3:
                        tab.setIcon(R.drawable.common_tab_btn_list_n_xxhdi);
                        break;
                    case 4:
                        tab.setIcon(R.drawable.common_tab_btn_my_n_xxhdpi);
                        ViewUtils.unSetViewTransparent(MainActivity.this);
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "程序异常！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //重新选择
                FragmentTransaction temp = fm.beginTransaction();
                switch (tab.getPosition()) {
                    case 0:
                        temp.replace(R.id.main_frame_show, new MainHomeFrag());
                        tab.setIcon(R.drawable.common_tab_btn_home_s_xxhdpi);
                        break;
                    case 1:
                        temp.replace(R.id.main_frame_show, new MainCircleFrag());
                        tab.setIcon(R.drawable.common_tab_btn_circle_s_xxhdpi);
                        break;
                    case 2:
                        temp.replace(R.id.main_frame_show, new MainShopFragment());
                        break;
                    case 3:
                        temp.replace(R.id.main_frame_show, new MainListFrag());
                        tab.setIcon(R.drawable.common_tab_btn_list_s_xxhdpi);
                        break;
                    case 4:
                        temp.replace(R.id.main_frame_show, new MainMyFrag());
                        tab.setIcon(R.drawable.common_tab_btn_my_s_xxhdpi);
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "程序异常！", Toast.LENGTH_SHORT).show();
                }
                temp.commit();
            }
        });

        //选中第一个   debug. delete this at later
        tab.selectTab(tab.getTabAt(0));

        //获取版本更新
        pre.upDate();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public ActivityMainPresenter initPresenter() {
        return new ActivityMainPresenter();
    }

    /**
     * 初始化Tab
     */
    private void initTab() {
        tab.addTab(tab.newTab().setIcon(R.drawable.common_tab_btn_home_s_xxhdpi));
        tab.addTab(tab.newTab().setIcon(R.drawable.common_tab_btn_circle_n_xxhdpi));
        tab.addTab(tab.newTab().setIcon(R.drawable.commom_tab_btn_shopping_cart_n_xxhdpi));
        tab.addTab(tab.newTab().setIcon(R.drawable.common_tab_btn_list_n_xxhdi));
        tab.addTab(tab.newTab().setIcon(R.drawable.common_tab_btn_my_n_xxhdpi));
    }

    /**
     * 开始显示Pw
     *
     * @param path path of download
     */
    public void startPwForUpdate(String path) {
        pw = new DivUpdatePopupWindow(this);
        pw.setListener(v -> {
            int networkState = InternetUtil.getNetworkState(this);
            if (networkState == 0) {
                Toast.makeText(this, "没有网络连接", Toast.LENGTH_SHORT).show();
                return;
            }
            //流量提醒
            if (networkState >= 2) {
                //设置文本 重新显示
                pw.dismiss();
                pw.setInfoText("目前手机不是WiFi状态，请注意您的流量消耗");
                pw.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                //刷新监听器
                pw.setListener(view -> pw.dismiss());
            } else pw.dismiss();
            //TODO 下载
            //pre.downLoad(path);
        });
        new Thread() {
            @Override
            public void run() {
                do {
                    try {
                        sleep(100);
                    } catch (InterruptedException ignored) {
                    }
                } while (!hasFocus);
                runOnUiThread(() -> pw.showAtLocation(MainActivity.this.getWindow().getDecorView(), Gravity.CENTER, 0, 0));
            }
        }.start();
    }

    private void installApkO(Context context, String downloadApkPath) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //是否有安装位置来源的权限
            boolean haveInstallPermission = getPackageManager().canRequestPackageInstalls();
            if (haveInstallPermission) {
                AppUtils.installApk(context, downloadApkPath);
            }
        } else {
            AppUtils.installApk(context, downloadApkPath);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //劫持返回键 -- 返回键需要按两次
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
                return true;
            } else {
                //finish();
                //System.exit(0);
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
