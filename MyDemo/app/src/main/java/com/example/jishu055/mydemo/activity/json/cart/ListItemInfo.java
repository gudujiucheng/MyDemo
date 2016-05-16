package com.example.jishu055.mydemo.activity.json.cart;

import java.util.List;

/**
 * Created by jishu055 on 2016/5/16.
 * 供应商拆分bean
 */
public class ListItemInfo {
    String remark;	//备注信息	string	<可选>
    String sid;	//供应商ID	number	<必须>
    List<ListItemInfo02> templateList;//	按配送模板拆分列表	array	<必须>

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public List<ListItemInfo02> getTemplateList() {
        return templateList;
    }

    public void setTemplateList(List<ListItemInfo02> templateList) {
        this.templateList = templateList;
    }
}
