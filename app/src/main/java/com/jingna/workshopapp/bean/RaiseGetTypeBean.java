package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/7/26.
 */

public class RaiseGetTypeBean {

    /**
     * status : 200
     * data : [{"id":103,"name":"平台推荐"},{"id":104,"name":"项目"},{"id":105,"name":"厂房"},{"id":106,"name":"车间"}]
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
         * id : 103
         * name : 平台推荐
         */

        private int id;
        private String name;

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
    }
}
