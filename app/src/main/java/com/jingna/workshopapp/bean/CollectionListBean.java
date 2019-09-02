package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/15.
 */

public class CollectionListBean {


    /**
     * status : 200
     * data : [{"name":"车间4","id":"12","type":"0","picture":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg"},{"name":"众筹车间01","id":"1","type":"1","picture":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg"}]
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
         * name : 车间4
         * id : 12
         * type : 0
         * picture : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
         */

        private String name;
        private String id;
        private String type;
        private String picture;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}
