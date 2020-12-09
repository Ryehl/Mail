package com.xaoyv.small.bean;

import java.util.List;

/**
 * <p>Project's name:维度商城</p>
 * <p>tag:My Foot bean</p>
 *
 * @author Xaoyv
 * date 2020/11/2 16:45
 */
public class JsonMyfootBean {

    /**
     * result : [{"browseNum":1,"browseTime":1604017687000,"commodityId":7,"commodityName":"蓝色之恋","masterPic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/5/1.jpg","price":29,"userId":79150},{"browseNum":1,"browseTime":1604017683000,"commodityId":15,"commodityName":"玻儿精灵美妆蛋一个","masterPic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/mzgj/5/1.jpg","price":6,"userId":79150},{"browseNum":1,"browseTime":1604017681000,"commodityId":6,"commodityName":"轻柔系自然裸妆假睫毛","masterPic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/4/1.jpg","price":39,"userId":79150},{"browseNum":12,"browseTime":1604017676000,"commodityId":32,"commodityName":"唐狮女鞋冬季女鞋休闲鞋子女士女鞋百搭帆布鞋女士休闲鞋子女款板鞋休闲女鞋帆布鞋","masterPic":"http://mobile.bwstudent.com/images/small/commodity/nx/fbx/1/1.jpg","price":88,"userId":79150},{"browseNum":3,"browseTime":1604017673000,"commodityId":21,"commodityName":"【加绒休闲 舒适轻便】秋冬增高休闲鞋厚底棉鞋运动户外通勤简约韩版女鞋","masterPic":"http://mobile.bwstudent.com/images/small/commodity/nx/bx/4/1.jpg","price":189,"userId":79150},{"browseNum":6,"browseTime":1603462649000,"commodityId":83,"commodityName":"秋季新款韩版女装淑女风荷叶边短款长袖连衣裙","masterPic":"http://mobile.bwstudent.com/images/small/commodity/nz/qz/5/1.jpg","price":168,"userId":79150},{"browseNum":1,"browseTime":1603252774000,"commodityId":105,"commodityName":"小米8 256GB 全面屏 双频GPS智能拍照游戏手机","masterPic":"http://mobile.bwstudent.com/images/small/commodity/sjsm/sj/6/1.jpg","price":3099,"userId":79150},{"browseNum":1,"browseTime":1603252713000,"commodityId":24,"commodityName":"百搭小白鞋 女款 时尚舒适板鞋","masterPic":"http://mobile.bwstudent.com/images/small/commodity/nx/bx/7/1.jpg","price":149,"userId":79150},{"browseNum":1,"browseTime":1603252653000,"commodityId":95,"commodityName":"秋冬加绒拼接色 不规则套头 假两件休闲短款保暖女装卫衣上衣","masterPic":"http://mobile.bwstudent.com/images/small/commodity/nz/wy/3/1.jpg","price":99,"userId":79150},{"browseNum":2,"browseTime":1603252533000,"commodityId":18,"commodityName":"白色经典 秋季新款简约百搭轻便休闲女鞋板鞋小白鞋","masterPic":"http://mobile.bwstudent.com/images/small/commodity/nx/bx/1/1.jpg","price":79,"userId":79150}]
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
         * browseNum : 1
         * browseTime : 1604017687000
         * commodityId : 7
         * commodityName : 蓝色之恋
         * masterPic : http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/5/1.jpg
         * price : 29
         * userId : 79150
         */

        private int browseNum;
        private long browseTime;
        private int commodityId;
        private String commodityName;
        private String masterPic;
        private int price;
        private int userId;

        public int getBrowseNum() {
            return browseNum;
        }

        public void setBrowseNum(int browseNum) {
            this.browseNum = browseNum;
        }

        public long getBrowseTime() {
            return browseTime;
        }

        public void setBrowseTime(long browseTime) {
            this.browseTime = browseTime;
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

        public String getMasterPic() {
            return masterPic;
        }

        public void setMasterPic(String masterPic) {
            this.masterPic = masterPic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
