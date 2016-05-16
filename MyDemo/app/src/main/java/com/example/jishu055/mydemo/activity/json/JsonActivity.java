package com.example.jishu055.mydemo.activity.json;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.example.jishu055.mydemo.R;
import com.example.jishu055.mydemo.activity.BaseActivity;
import com.example.jishu055.mydemo.activity.json.cart.ListItemInfo;
import com.example.jishu055.mydemo.activity.json.cart.ListItemInfo02;
import com.example.jishu055.mydemo.activity.json.cart.ListItemInfo03;
import com.example.jishu055.mydemo.activity.json.cart.postInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用fastjson框架，进行json和bean之间的转换
 */
public class JsonActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

    }

    public void switchNow(View view) {

        // 构建用户geust
        User guestUser = new User();
        guestUser.setName("guest");
        guestUser.setAge(28);
        // 构建用户root
        User rootUser = new User();
        rootUser.setName("root");
        guestUser.setAge(35);
        // 构建用户组对象
        Group group = new Group();
        group.setName("admin");
        group.getUsers().add(guestUser);
        group.getUsers().add(rootUser);
        // 含有List集合的对象转JSON串
        String jsonString = JSON.toJSONString(group);
        Log.e("Test", "含有List集合的对象转JSON串:" + jsonString);

        // JSON串转回含有集合的对象
        Group group2 = JSON.parseObject(jsonString, Group.class);
        Log.e("Test", "JSON串转回含有集合的对象:" + group2);

        // 构建用户对象数组
        User[] users = new User[2];
        users[0] = guestUser;
        users[1] = rootUser;

        // 对象数组转JSON串
        String jsonString2 = JSON.toJSONString(users);
        Log.e("Test", "对象数组转JSON串:" + jsonString2);


        // JSON串转对象数组
        List<User> users2 = JSON.parseArray(jsonString2, User.class);
        Log.e("Test", "JSON串转对象数组:" + users2);


        /**上面的日志打印对象的时候是直接打印的对象，没有复写tostring方法，如有需求可自行复写*/


    }


    public void cartTOJson(View v) {
        postInfo post = new postInfo();

        post.setAddressId("收货地址ID");
        post.setDevCode("客户端设备编码");
        post.setOrderChannel("订单渠道");
        post.setOrderClient("提交订单的客户端");
        post.setReceiptContent("发票内容");
        post.setReceiptTitle("发票抬头");
        List<ListItemInfo> listone =new ArrayList();
        for (int i = 0; i <2 ; i++) {
            ListItemInfo item = new ListItemInfo();
            item.setRemark("备注信息"+i);
            item.setSid("供应商ID"+i);

            List listTwo = new ArrayList<ListItemInfo02>();
            for (int j = 0; j <2 ; j++) {
                ListItemInfo02 item2 = new ListItemInfo02();
                item2.setTemplateId("配送模板ID"+i+j);

                List list3 = new ArrayList<ListItemInfo03>();
                for (int a = 0; a <2 ; a++) {
                    ListItemInfo03 item3 = new ListItemInfo03();
                    item3.setCartIds("购物车Id"+i+j+a);
                    item3.setExpresscode("配送方式编码"+i+j+a);
                    list3.add(item3);
                }
                item2.setExpressList(list3);

                listTwo.add(item2);
            }

            item.setTemplateList(listTwo);
            listone.add(item);
        }


        post.setSuppList(listone);

        String jsonString = JSON.toJSONString(post);
        Log.e("Test", "提交订单拼接json:" + jsonString);
    }


}
