package com.jingna.workshopapp.bean;

/**
 * Created by Administrator on 2019/8/1.
 */

public class SpecialStatementBean {

    /**
     * status : 200
     * data : {"id":1,"statementsContent":"这是一条测试特别声明2"}
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
         * statementsContent : 这是一条测试特别声明2
         */

        private int id;
        private String statementsContent;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStatementsContent() {
            return statementsContent;
        }

        public void setStatementsContent(String statementsContent) {
            this.statementsContent = statementsContent;
        }
    }
}
