package com.example.jishu055.mydemo.activity.java.enumtest;

/**
 * 有属性的枚举 范例
 * Created by zc on 2016/7/19.
 */
public enum EnumTestWithValue {
    MON(1){
        @Override
        public boolean isRest() {
            return true;
        }
    }, TUE(2), WED(3), THU(4), FRI(5), SAT(6);
    private int value;

    /**
     * 构造函数
     * @param value
     */
    EnumTestWithValue(int value) {
        this.value = value;
    }

    /**
     * 获取属性值的方法
     * @return
     */
    public int getValue() {
        return value;
    }

    /**
     * 测试方法的重写
     * @return
     */
    public boolean isRest() {
        return false;
    }
}
