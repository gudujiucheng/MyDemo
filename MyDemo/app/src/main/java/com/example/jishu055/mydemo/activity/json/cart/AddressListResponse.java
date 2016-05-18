package com.example.jishu055.mydemo.activity.json.cart;

import java.util.List;

/**
 * 收货地址列表对象封装
 * Created by zc on 2016/5/10.
 */
public class AddressListResponse extends BaseResponse<AddressListResponse> {

    private int num;

    private List<AddressInfo> list;

    public int getNum() {
        return num;
    }


    public void setNum(int num) {
        this.num = num;
    }

    public List<AddressInfo> getList() {
        return list;
    }

    public void setList(List<AddressInfo> list) {
        this.list = list;
    }

}
