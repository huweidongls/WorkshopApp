package com.jingna.workshopapp.bean;

/**
 * Created by Administrator on 2019/8/1.
 */

public class PrivacyPolicyBean {

    /**
     * status : 200
     * data : {"id":1,"privacyPolicy":"哈哈哈哈哈哈哈哈,测试,嘻嘻嘻,啦啦啦啦啦啦啊"}
     * totalPage : 0
     * totalCount : 1
     */

    private String status;
    private DataBean data;
    private int totalPage;
    private int totalCount;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public static class DataBean {
        /**
         * id : 1
         * privacyPolicy : 哈哈哈哈哈哈哈哈,测试,嘻嘻嘻,啦啦啦啦啦啦啊
         */

        private int id;
        private String privacyPolicy;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPrivacyPolicy() {
            return privacyPolicy;
        }

        public void setPrivacyPolicy(String privacyPolicy) {
            this.privacyPolicy = privacyPolicy;
        }
    }
}
