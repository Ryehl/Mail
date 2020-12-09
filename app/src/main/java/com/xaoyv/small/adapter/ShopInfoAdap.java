package com.xaoyv.small.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.xaoyv.small.R;
import com.xaoyv.small.bean.Constant;
import com.xaoyv.small.bean.ConstantParameter;
import com.xaoyv.small.bean.JsonCommentBean;
import com.xaoyv.small.bean.JsonShopInfoBean;
import com.xaoyv.small.utils.NetUtils;
import com.xaoyv.small.utils.Utils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * <p>Project's name:维度商城</p>
 * <p>tag:商品详情</p>
 *
 * @author Xaoyv
 * date 2020/11/3 08:06
 */
public class ShopInfoAdap extends BaseAdapter {
    private JsonShopInfoBean bean;
    private Context context;
    private int commodityId;

    public ShopInfoAdap(JsonShopInfoBean bean, Context context, int commodityId) {
        this.bean = bean;
        this.context = context;
        this.commodityId = commodityId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch (position) {
            case 0:
                convertView = View.inflate(context, R.layout.shopinfo_banner, null);
                Banner banner = convertView.findViewById(R.id.shopinfo_lv_banner);
                String picture = bean.getResult().getPicture();
                String[] arrOfImg = picture.split(",");
                ArrayList<String> images = new ArrayList<>(Arrays.asList(arrOfImg));
                banner.setImages(images);
                banner.setViewPagerIsScroll(true);
                banner.setDelayTime(3000);
                banner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load((String) path).into(imageView);
                    }
                });
                banner.start();
                break;
            case 1:
                convertView = View.inflate(context, R.layout.shopinfo_webview, null);
                WebView web = convertView.findViewById(R.id.shopinfo_lv_web);
//                web.loadDataWithBaseURL(null, bean.getResult().getDetails(), "text/html", "UTF-8", "");
//                web.setWebChromeClient(new WebChromeClient());
//                web.setWebViewClient(new WebViewClient());
//                web.getSettings().setJavaScriptEnabled(true);

                String details = bean.getResult().getDetails();
                if (details.contains("http:http")) {
                    details = details.replaceAll("http:http", "http");
                }
                Utils.setttingWebView(web);
                web.loadDataWithBaseURL(null, details, "textml",
                        "UTF-8", null);
                break;
            case 2:
                convertView = View.inflate(context, R.layout.shopinfo_listview, null);
                ListView lv = convertView.findViewById(R.id.shopinfo_lv_lv);
                HashMap<String, Object> map = new HashMap<>();
                map.put(ConstantParameter.page, 1);
                map.put(ConstantParameter.commodityId, commodityId);
                map.put(ConstantParameter.count, 10);
                NetUtils.getNetUtils().mIApi.getInfo(Constant.Comment, map)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ResponseBody>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onNext(ResponseBody responseBody) {
                                try {
                                    String json = responseBody.string();
                                    JsonCommentBean commentBean = new Gson().fromJson(json, JsonCommentBean.class);
                                    setData(lv, commentBean.getResult());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                Toast.makeText(context, "评论列表加载失败", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
        }
        return convertView;
    }

    private void setData(ListView lv, List<JsonCommentBean.ResultBean> list) {
        lv.setAdapter(new ShopInfoListItemAdap(list, context));
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
