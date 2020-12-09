package com.xaoyv.small.bean;

import java.util.List;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:圈子列表</p>
 *
 * @author Xaoyv
 * date 2020/10/23 09:10
 */
public class JsonCircleBean {

    /**
     * result : [{"commodityId":12,"content":"你好啊","createTime":1600354543000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/head_pic/2020-09-16/20200916152102.jpeg","id":39734,"image":"http://172.17.8.100/images/small/circle_pic/2020-09-17/5386220200917095543.jpeg","nickName":"aaa15","userId":13888,"whetherGreat":2},{"commodityId":1,"content":"给大家推荐一个好商品","createTime":1578538770000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/head_pic/2020-01-12/20200112162528.png","id":39733,"image":"http://172.17.8.100/images/small/circle_pic/2020-01-08/6093120200108205930.jpg","nickName":"Aslan324","userId":11700,"whetherGreat":2},{"commodityId":11,"content":"holle","createTime":1577295053000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/head_pic/2019-09-19/20190919153225.jpg","id":39732,"image":"http://172.17.8.100/images/small/circle_pic/2019-12-25/6985120191225113053.png","nickName":"吾儿王毅","userId":9548,"whetherGreat":2},{"commodityId":11,"content":"holle","createTime":1577295009000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/head_pic/2019-09-19/20190919153225.jpg","id":39731,"image":"http://172.17.8.100/images/small/circle_pic/2019-12-25/8536920191225113009.png","nickName":"吾儿王毅","userId":9548,"whetherGreat":2},{"commodityId":1,"content":"不错呦","createTime":1576891240000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":39729,"image":"http://172.17.8.100/images/small/circle_pic/2019-12-20/2864520191220192040.jpg","nickName":"xI_YK6oO","userId":6334,"whetherGreat":2}]
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
         * commodityId : 12
         * content : 你好啊
         * createTime : 1600354543000
         * greatNum : 0
         * headPic : http://172.17.8.100/images/small/head_pic/2020-09-16/20200916152102.jpeg
         * id : 39734
         * image : http://172.17.8.100/images/small/circle_pic/2020-09-17/5386220200917095543.jpeg
         * nickName : aaa15
         * userId : 13888
         * whetherGreat : 2
         */

        private int commodityId;
        private String content;
        private long createTime;
        private int greatNum;
        private String headPic;
        private int id;
        private String image;
        private String nickName;
        private int userId;
        private int whetherGreat;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhetherGreat() {
            return whetherGreat;
        }

        public void setWhetherGreat(int whetherGreat) {
            this.whetherGreat = whetherGreat;
        }
    }
}
