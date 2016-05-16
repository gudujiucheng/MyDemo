package com.example.jishu055.mydemo.activity.json.cart;

/**
 * Created by jishu055 on 2016/5/16.
 */
public class ListItemInfo03 {
    String cartIds;//	购物车Id	string	<必须>多条记录用逗号连接

    public String getExpresscode() {
        return expresscode;
    }

    public void setExpresscode(String expresscode) {
        this.expresscode = expresscode;
    }

    public String getCartIds() {
        return cartIds;
    }

    public void setCartIds(String cartIds) {
        this.cartIds = cartIds;
    }

    String expresscode;//配送方式编码	string	<必须>
}
