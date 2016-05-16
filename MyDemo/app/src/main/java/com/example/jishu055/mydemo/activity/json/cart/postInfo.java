package com.example.jishu055.mydemo.activity.json.cart;

import java.util.List;

/**
 * Created by jishu055 on 2016/5/13.
 */
public class postInfo {
    private String addressId;//收货地址ID	number	<必须>
    private String devCode;//客户端设备编码	string	orderClient为3或4的时候，必传
    private String orderChannel;//订单渠道	number	必选,10：B2C商城，20：O2O，30：O店40：B2B分销，50：千城万店
    private String orderClient;//提交订单的客户端	number	必选,1：pc端,2 ：手机浏览器端， 3:Android端， 4:ios端， 5:未知来源
    private String receiptContent;//	发票内容	string	<可选>
    private String receiptTitle;//发票抬头	string	<可选>

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getDevCode() {
        return devCode;
    }

    public void setDevCode(String devCode) {
        this.devCode = devCode;
    }

    public String getOrderClient() {
        return orderClient;
    }

    public void setOrderClient(String orderClient) {
        this.orderClient = orderClient;
    }

    public String getOrderChannel() {
        return orderChannel;
    }

    public void setOrderChannel(String orderChannel) {
        this.orderChannel = orderChannel;
    }

    public String getReceiptContent() {
        return receiptContent;
    }

    public void setReceiptContent(String receiptContent) {
        this.receiptContent = receiptContent;
    }

    public String getReceiptTitle() {
        return receiptTitle;
    }

    public void setReceiptTitle(String receiptTitle) {
        this.receiptTitle = receiptTitle;
    }

    public List<ListItemInfo> getSuppList() {
        return suppList;

    }

    public void setSuppList(List<ListItemInfo> suppList) {
        this.suppList = suppList;
    }

    private List<ListItemInfo> suppList;
}
