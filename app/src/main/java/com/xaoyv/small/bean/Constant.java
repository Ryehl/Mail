package com.xaoyv.small.bean;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:常量</p>
 *
 * @author Xaoyv
 * date 2020/10/15 23:29
 */
public class Constant {
    public static final String TAG = "TAG";
    public static final String RequestBodyMediaType = "application/json;charset=UTF-8";
    public static final String BASE_URL = "http://mobile.bwstudent.com/small/";
    //更新
    public static final String UPDATE = "user/v1/findAppVersion";
    //Banner轮播图
    public static final String BANNER_URL = "commodity/v1/bannerShow";
    //首页多布局数据
    public static final String HOME_INFO = "commodity/v1/commodityList";
    //商品类别
    public static final String SHOP_TYPE = "commodity/v1/findCategory";
    //根据二级列表查询商品
    public static final String BYCATEGORY = "commodity/v1/findCommodityByCategory";
    //圈子列表
    public static final String CIRCLE = "circle/v1/findCircleList";
    //圈子点赞
    public static final String GREAT = "circle/verify/v1/addCircleGreat";
    //登录
    public static final String LOGIN = "user/v1/login";
    //注册
    public static final String REGISTER = "user/v1/register";
    //通过关键字进行查找
    public static final String ByKeyworid = "commodity/v1/findCommodityByKeyword";
    //购物车 查询
    public static final String SHOPCART = "order/verify/v1/findShoppingCart";
    //订单
    public static final String Order_List = "order/verify/v1/findOrderListByStatus";
    //我的圈子
    public static final String MyCircle = "circle/verify/v1/findMyCircleById";
    //我的足迹
    public static final String MyFoot = "commodity/verify/v1/browseList";
    //我的钱包
    public static final String MyWallet = "user/verify/v1/findUserWallet";
    //我的地址
    public static final String Address = "user/verify/v1/receiveAddressList";
    //商品详情
    public static final String ShopInfo = "commodity/v1/findCommodityDetailsById";
    //创建订单
    public static final String CreateOrder = "order/verify/v1/createOrder";
    //pay
    public static final String Pay = "order/verify/v1/pay";
    //评论
    public static final String Comment = "commodity/v1/CommodityCommentList";
}
