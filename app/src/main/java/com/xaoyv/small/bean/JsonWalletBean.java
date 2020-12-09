package com.xaoyv.small.bean;

import java.util.List;

/**
 * <p>Project's name:维度商城</p>
 * <p>tag:wallet bean</p>
 *
 * @author Xaoyv
 * date 2020/11/2 17:32
 */
public class JsonWalletBean {

    /**
     * result : {"balance":9998565,"detailList":[{"amount":47,"consumerTime":1602687741000,"orderId":"2020101423021839779150","userId":79150},{"amount":44,"consumerTime":1602676676000,"orderId":"2020101419573206079150","userId":79150},{"amount":88,"consumerTime":1602675610000,"orderId":"2020101419400794379150","userId":79150},{"amount":88,"consumerTime":1602674238000,"orderId":"2020101419171646579150","userId":79150},{"amount":763,"consumerTime":1602578803000,"orderId":"2020101316464063079150","userId":79150},{"amount":78,"consumerTime":1602570020000,"orderId":"2020101314201827879150","userId":79150},{"amount":119,"consumerTime":1602569911000,"orderId":"2020101314175562079150","userId":79150},{"amount":119,"consumerTime":1602569887000,"orderId":"2020101314175288879150","userId":79150},{"amount":88,"consumerTime":1602569627000,"orderId":"2020101314133006179150","userId":79150}]}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean {
        /**
         * balance : 9998565
         * detailList : [{"amount":47,"consumerTime":1602687741000,"orderId":"2020101423021839779150","userId":79150},{"amount":44,"consumerTime":1602676676000,"orderId":"2020101419573206079150","userId":79150},{"amount":88,"consumerTime":1602675610000,"orderId":"2020101419400794379150","userId":79150},{"amount":88,"consumerTime":1602674238000,"orderId":"2020101419171646579150","userId":79150},{"amount":763,"consumerTime":1602578803000,"orderId":"2020101316464063079150","userId":79150},{"amount":78,"consumerTime":1602570020000,"orderId":"2020101314201827879150","userId":79150},{"amount":119,"consumerTime":1602569911000,"orderId":"2020101314175562079150","userId":79150},{"amount":119,"consumerTime":1602569887000,"orderId":"2020101314175288879150","userId":79150},{"amount":88,"consumerTime":1602569627000,"orderId":"2020101314133006179150","userId":79150}]
         */

        private int balance;
        private List<DetailListBean> detailList;

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public List<DetailListBean> getDetailList() {
            return detailList;
        }

        public void setDetailList(List<DetailListBean> detailList) {
            this.detailList = detailList;
        }

        public static class DetailListBean {
            /**
             * amount : 47
             * consumerTime : 1602687741000
             * orderId : 2020101423021839779150
             * userId : 79150
             */

            private int amount;
            private long consumerTime;
            private String orderId;
            private int userId;

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public long getConsumerTime() {
                return consumerTime;
            }

            public void setConsumerTime(long consumerTime) {
                this.consumerTime = consumerTime;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }
        }
    }
}
