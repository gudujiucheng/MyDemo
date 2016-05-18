package com.example.jishu055.mydemo.activity.json.cart;

import java.io.Serializable;

/**
 * 地址信息model
 */
public class AddressInfo implements Serializable {

    private String address;//收货地址
    private int city;//市ID
    private int def;//是否是默认收货地址 	0表示不是，1表示是
    private int district;//区ID
    private int id;//记录ID
    private String name;//收货人姓名
    private String phone;//固定电话
    private int province;//省ID
    private String tel;//手机号
    private String zip;//邮编
    private String provinceCityDistict;//已经选择的省市区文字组合

    public String getProvinceCityDistict() {
        return provinceCityDistict;
    }

    public void setProvinceCityDistict(String provinceCityDistict) {
        this.provinceCityDistict = provinceCityDistict;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
