package com.xaoyv.small.fragment;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xaoyv.small.R;
import com.xaoyv.small.activity.TopayActivity;
import com.xaoyv.small.adapter.ShopCartAdap;
import com.xaoyv.small.application.MyApplication;
import com.xaoyv.small.base.BaseFragment;
import com.xaoyv.small.bean.Constant;
import com.xaoyv.small.bean.ConstantParameter;
import com.xaoyv.small.bean.JsonShopCartBean;
import com.xaoyv.small.presenter.FragMainShopPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:MainShopFragment</p>
 *
 * @author Xaoyv
 * date 2020/10/20 09:54
 */
public class MainShopFragment extends BaseFragment<FragMainShopPresenter> {
    private RecyclerView recy;
    private CheckBox cb;
    private TextView priceOfAll;
    private TextView countOfAll;
    private JsonShopCartBean bean;
    private Button tobuy;

    @Override
    public void initView() {
        //find view by id
        recy = getView().findViewById(R.id.frag_shop_lv);
        cb = getView().findViewById(R.id.frag_shop_cb_allshop);
        priceOfAll = getView().findViewById(R.id.frag_shop_tv_price);
        countOfAll = getView().findViewById(R.id.frag_shop_tv_count);
        tobuy = getView().findViewById(R.id.frag_shop_btn_toPay);
    }

    @Override
    public void initData() {
        //TODO 获取购物车数据
        pre.getShopcartInfo();
        //TODO 设置监听
        tobuy.setOnClickListener(v -> {
            //获取被选中的数据
            if (getCount() < 0) {
                Toast.makeText(MyApplication.context, "获取数据失败", Toast.LENGTH_SHORT).show();
                return;
            }
            if (getCount() == 0) {
                Toast.makeText(MyApplication.context, "请选中商品", Toast.LENGTH_SHORT).show();
                return;
            }

            String checkedShopJson = getCheckedShopJson();
            Log.d(Constant.TAG, "initData: " + checkedShopJson);
            Intent intent = new Intent(getActivity(), TopayActivity.class);
            intent.putExtra("info", checkedShopJson);
            intent.putExtra(ConstantParameter.count, getCount());
            intent.putExtra(ConstantParameter.price, getPrice());
            startActivity(intent);
        });
    }

    private String getCheckedShopJson() {
        if (bean == null)
            return null;
        JSONArray arr = new JSONArray();
        for (JsonShopCartBean.ResultBean resultBean : bean.getResult()) {
            for (JsonShopCartBean.ResultBean.ShoppingCartListBean clb : resultBean.getShoppingCartList()) {
                if (clb.isItemCkeck()) {
                    JSONObject jsob = new JSONObject();
                    try {
                        jsob.put("commodityId", clb.getCommodityId());
                        jsob.put("amount", clb.getCount());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    arr.put(jsob);
                }
            }
        }
        return arr.toString();
    }

    /**
     * 设置适配器
     *
     * @param shopCartBean 数据源
     */
    public void setAdap(JsonShopCartBean shopCartBean) {
        //展示二级列表
        bean = shopCartBean;
        ShopCartAdap adapter = new ShopCartAdap(shopCartBean);
        setCountText();
        setPriceText();
        adapter.setGroupListener(new ShopCartAdap.GroupListener() {
            @Override
            public void groupSelectAll(boolean boo) {
                cb.setChecked(boo);
            }

            @Override
            public void replaceCountAndPrice() {
                setCountText();
                setPriceText();
            }
        });
        //select all item
        cb.setOnClickListener(v -> {
            for (JsonShopCartBean.ResultBean result : shopCartBean.getResult()) {
                //set bean group
                result.setCheck(cb.isChecked());
                for (JsonShopCartBean.ResultBean.ShoppingCartListBean bean : result.getShoppingCartList())
                    //set item bean group
                    bean.setItemCkeck(cb.isChecked());
            }
            adapter.notifyDataSetChanged();
            //update count and price
            setCountText();
            setPriceText();
        });
        recy.setAdapter(adapter);
        recy.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    /**
     * put price
     */
    private void setPriceText() {
        int price = 0;
        if (bean == null)
            return;
        if (bean.getResult() == null)
            return;
        for (JsonShopCartBean.ResultBean resultBean : bean.getResult()) {
            for (JsonShopCartBean.ResultBean.ShoppingCartListBean item : resultBean.getShoppingCartList()) {
                if (item.isItemCkeck()) {
                    price += item.getPrice() * item.getCount();
                }
            }
        }
        //set text
        priceOfAll.setText("总价格：" + price);
    }

    /**
     * 获取被选中 的价格
     *
     * @return -1 error
     */
    private int getPrice() {
        int price = 0;
        if (bean == null)
            return -1;
        for (JsonShopCartBean.ResultBean resultBean : bean.getResult()) {
            for (JsonShopCartBean.ResultBean.ShoppingCartListBean item : resultBean.getShoppingCartList()) {
                if (item.isItemCkeck()) {
                    price += item.getPrice() * item.getCount();
                }
            }
        }
        return price;
    }

    /**
     * put count
     */
    private void setCountText() {
        int count = 0;
        if (bean == null)
            return;
        if(bean.getResult() == null)
            return;
        for (JsonShopCartBean.ResultBean resultBean : bean.getResult()) {
            for (JsonShopCartBean.ResultBean.ShoppingCartListBean item : resultBean.getShoppingCartList()) {
                if (item.isItemCkeck()) {
                    count += item.getCount();
                }
            }
        }
        countOfAll.setText("总数量是：" + count);
    }

    /**
     * 获取被选中的数量
     *
     * @return -1 error
     */
    private int getCount() {
        int count = 0;
        if (bean == null)
            return -1;
        for (JsonShopCartBean.ResultBean resultBean : bean.getResult()) {
            for (JsonShopCartBean.ResultBean.ShoppingCartListBean item : resultBean.getShoppingCartList()) {
                if (item.isItemCkeck()) {
                    count += item.getCount();
                }
            }
        }
        return count;
    }

    @Override
    public int getLayout() {
        return R.layout.frag_main_shop;
    }

    @Override
    public FragMainShopPresenter initPresenter() {
        return new FragMainShopPresenter();
    }
}
