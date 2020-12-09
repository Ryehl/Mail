package com.xaoyv.small.bean;

/**
 * <p>Project's name:维度商城</p>
 * <p>tag:创建订单</p>
 *
 * @author Xaoyv
 * date 2020/11/3 05:44
 */
public class JsonCreateOrderBean {

    /**
     * orderId : 2020110305433832579150
     * message : 创建订单成功
     * status : 0000
     */

    private String orderId;
    private String message;
    private String status;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
}
