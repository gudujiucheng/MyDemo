package com.example.jishu055.mydemo.activity.json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jishu055 on 2016/5/13.
 */
public class Group {
    private String name;
    private List<User> users = new ArrayList<User>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
