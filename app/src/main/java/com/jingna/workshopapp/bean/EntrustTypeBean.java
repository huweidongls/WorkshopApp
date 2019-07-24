package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/7/24.
 */

public class EntrustTypeBean {

    /**
     * status : 200
     * data : [{"id":99,"goodsAttributeCategoryId":4,"name":"环保设备","isDelete":0},{"id":100,"goodsAttributeCategoryId":4,"name":"污水处理设备","isDelete":0},{"id":101,"goodsAttributeCategoryId":4,"name":"净水处理设备","isDelete":0},{"id":102,"goodsAttributeCategoryId":4,"name":"换热设备","isDelete":0}]
     */

    private String status;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 99
         * goodsAttributeCategoryId : 4
         * name : 环保设备
         * isDelete : 0
         */

        private int id;
        private int goodsAttributeCategoryId;
        private String name;
        private int isDelete;

        public DataBean(int id, int goodsAttributeCategoryId, String name, int isDelete) {
            this.id = id;
            this.goodsAttributeCategoryId = goodsAttributeCategoryId;
            this.name = name;
            this.isDelete = isDelete;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGoodsAttributeCategoryId() {
            return goodsAttributeCategoryId;
        }

        public void setGoodsAttributeCategoryId(int goodsAttributeCategoryId) {
            this.goodsAttributeCategoryId = goodsAttributeCategoryId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }
    }
}
