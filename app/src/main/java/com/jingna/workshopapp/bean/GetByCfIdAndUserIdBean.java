package com.jingna.workshopapp.bean;

/**
 * Created by Administrator on 2019/9/25.
 */

public class GetByCfIdAndUserIdBean {

    /**
     * status : 200
     * data : {"crowdFundingNum":"1","isCollect":"1","evaluteNum":"5"}
     */

    private String status;
    private DataBean data;

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

    public static class DataBean {
        /**
         * crowdFundingNum : 1
         * isCollect : 1
         * evaluteNum : 5
         */

        private String crowdFundingNum;
        private String isCollect;
        private String evaluteNum;

        public String getCrowdFundingNum() {
            return crowdFundingNum;
        }

        public void setCrowdFundingNum(String crowdFundingNum) {
            this.crowdFundingNum = crowdFundingNum;
        }

        public String getIsCollect() {
            return isCollect;
        }

        public void setIsCollect(String isCollect) {
            this.isCollect = isCollect;
        }

        public String getEvaluteNum() {
            return evaluteNum;
        }

        public void setEvaluteNum(String evaluteNum) {
            this.evaluteNum = evaluteNum;
        }
    }
}
