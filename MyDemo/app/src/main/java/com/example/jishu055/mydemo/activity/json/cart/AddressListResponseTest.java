package com.example.jishu055.mydemo.activity.json.cart;

import java.util.List;

/**
 * 不用泛型
 */
public class AddressListResponseTest extends BaseResponseTest{

    private data mdata;



    public data getData() {
        return mdata;
    }

    public void setData(data qdata) {
        this.mdata = qdata;
    }


    public class data{
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

}
