package com.xaoyv.small.bean;

import java.util.List;

/**
 * <p>项目名称:Yanxiaoyu20201030</p>
 * <p>简述:Shopping cart info</p>
 *
 * @author Xaoyv
 * date 2020/10/30 09:46
 */
public class JsonShopCartBean {

    /**
     * result : [{"categoryName":"女鞋","shoppingCartList":[{"commodityId":23,"commodityName":"小白鞋 女款 时尚百搭休闲板鞋","count":1,"pic":"http://mobile.bwstudent.com/images/small/commodity/nx/bx/6/1.jpg","price":139},{"commodityId":21,"commodityName":"【加绒休闲 舒适轻便】秋冬增高休闲鞋厚底棉鞋运动户外通勤简约韩版女鞋","count":1,"pic":"http://mobile.bwstudent.com/images/small/commodity/nx/bx/4/1.jpg","price":189},{"commodityId":32,"commodityName":"唐狮女鞋冬季女鞋休闲鞋子女士女鞋百搭帆布鞋女士休闲鞋子女款板鞋休闲女鞋帆布鞋","count":1,"pic":"http://mobile.bwstudent.com/images/small/commodity/nx/fbx/1/1.jpg","price":88}]},{"categoryName":"美妆护肤","shoppingCartList":[{"commodityId":6,"commodityName":"轻柔系自然裸妆假睫毛","count":3,"pic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/4/1.jpg","price":39},{"commodityId":15,"commodityName":"玻儿精灵美妆蛋一个","count":1,"pic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/mzgj/5/1.jpg","price":6},{"commodityId":7,"commodityName":"蓝色之恋","count":1,"pic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/5/1.jpg","price":29}]}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * categoryName : 女鞋
         * shoppingCartList : [{"commodityId":23,"commodityName":"小白鞋 女款 时尚百搭休闲板鞋","count":1,"pic":"http://mobile.bwstudent.com/images/small/commodity/nx/bx/6/1.jpg","price":139},{"commodityId":21,"commodityName":"【加绒休闲 舒适轻便】秋冬增高休闲鞋厚底棉鞋运动户外通勤简约韩版女鞋","count":1,"pic":"http://mobile.bwstudent.com/images/small/commodity/nx/bx/4/1.jpg","price":189},{"commodityId":32,"commodityName":"唐狮女鞋冬季女鞋休闲鞋子女士女鞋百搭帆布鞋女士休闲鞋子女款板鞋休闲女鞋帆布鞋","count":1,"pic":"http://mobile.bwstudent.com/images/small/commodity/nx/fbx/1/1.jpg","price":88}]
         */

        private String categoryName;
        private List<ShoppingCartListBean> shoppingCartList;
        private boolean isCheck = false;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public List<ShoppingCartListBean> getShoppingCartList() {
            return shoppingCartList;
        }

        public void setShoppingCartList(List<ShoppingCartListBean> shoppingCartList) {
            this.shoppingCartList = shoppingCartList;
        }

        public static class ShoppingCartListBean {
            /**
             * commodityId : 23
             * commodityName : 小白鞋 女款 时尚百搭休闲板鞋
             * count : 1
             * pic : http://mobile.bwstudent.com/images/small/commodity/nx/bx/6/1.jpg
             * price : 139
             */

            private int commodityId;
            private String commodityName;
            private int count;
            private String pic;
            private int price;
            private boolean isItemCkeck = false;

            public boolean isItemCkeck() {
                return isItemCkeck;
            }

            public void setItemCkeck(boolean itemCkeck) {
                isItemCkeck = itemCkeck;
            }

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }
        }
    }
}
