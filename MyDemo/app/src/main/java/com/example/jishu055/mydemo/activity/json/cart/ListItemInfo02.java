package com.example.jishu055.mydemo.activity.json.cart;

import java.util.List;

/**
 * Created by jishu055 on 2016/5/16.
 */
public class ListItemInfo02 {

    List expressList;//	配送方式列表	array	<必须>

    public List getExpressList() {
        return expressList;
    }

    public void setExpressList(List expressList) {
        this.expressList = expressList;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    String templateId;	//配送模板ID	number	<必须>
}
