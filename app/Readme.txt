表达能力不强，直接上项目

MVP
    MVC 耦合度较高 各个模块依赖性较大
    MVP View层比较独立

欢迎页
    随机展示图片
    倒计时 handle 递归算法
    3秒
    权限  移除handle finish（销毁）

主页
    更新 popup window
    tab+FrameLayout
    沉浸式 改变

首页
    banner addHeader
    XRecyclerView onRefresh onLoadMore
    Bug -- 放入RecyclerView的控件不能含有父控件
        ViewHolder views must not be attached when created.

NotNull
    不说语法问题
    Java --
            万事万物皆对象
            代码多
            空指针  @Nullable @NotNull
    kotlin --
            万事万物皆表达式
            代码简洁 -- 出Bug的可能性降低
            特有的扩展属性
            声明的时候 是不是空

搜索
    二级联动 tabLayout+自定义流式布局
    自定义流式布局
        onMeasure 自定义View尺寸的规则 onLayout 用于放置子View的位置
        对外提供setViews和addViews方法 接口回调
    上拉加载下拉刷新

圈子
    Fresco 圆形图，渐变加载
    多图片多布局
    时间 -> 工具类
        网络判断，判断状态，2345G WiFi
        View操作，沉浸式
        获取App、手机信息、手机版本
        类型转换的工具类

Retrofit
    到现在还是有人去更新、维护的
    框架已经帮我们构建好了
    以前的HttpUrlConnection OkHttpClient需要手动拼接Url，Retrofit Map 拼接
    RxJava  BaseUrl

购物车
    全选 接口回调
    左滑删除
    HorizontalScrollView

支付界面
    支付 popup Window

订单
    TabLayout + XRecyclerView
    多布局

个人中心
    Fresco 加载
    ListView 内存占用少