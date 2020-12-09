package com.xaoyv.small.bean;

import java.util.List;

/**
 * <p>Project's name:维度商城</p>
 * <p>tag:MyCircle json bean</p>
 *
 * @author Xaoyv
 * date 2020/11/2 16:05
 */
public class JsonMyCircleBean {

    /**
     * result : [{"commodityId":119,"content":"你抓鲁迅关我周树人什么事","createTime":1602616867000,"greatNum":1,"headPic":"http://mobile.bwstudent.com/images/small/head_pic/2020-10-19/20201019084735.jpg","id":4929,"image":"http://mobile.bwstudent.com/images/small/circle_pic/2020-10-13/8459120201013142107.jpg","nickName":"ov_517Wa","userId":79150,"whetherGreat":1},{"commodityId":176,"content":"来给秀儿削个苹果","createTime":1602616741000,"greatNum":1,"headPic":"http://mobile.bwstudent.com/images/small/head_pic/2020-10-19/20201019084735.jpg","id":4928,"image":"http://mobile.bwstudent.com/images/small/circle_pic/2020-10-13/6425020201013141901.jpg","nickName":"ov_517Wa","userId":79150,"whetherGreat":1}]
     * message : 查詢成功
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
         * commodityId : 119
         * content : 你抓鲁迅关我周树人什么事
         * createTime : 1602616867000
         * greatNum : 1
         * headPic : http://mobile.bwstudent.com/images/small/head_pic/2020-10-19/20201019084735.jpg
         * id : 4929
         * image : http://mobile.bwstudent.com/images/small/circle_pic/2020-10-13/8459120201013142107.jpg
         * nickName : ov_517Wa
         * userId : 79150
         * whetherGreat : 1
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
