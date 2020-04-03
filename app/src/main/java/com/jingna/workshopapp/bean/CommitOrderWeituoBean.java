package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/16.
 */

public class CommitOrderWeituoBean {

    /**
     * status : 200
     * data : {"id":16,"categoryName":"设备1","appCategoryPic":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","money":1700,"productAttributeList":[],"times":[],"sysUserInfos":[],"shopGoodsEvaluates":[],"additionalCosts":[],"supportingEquipments":[],"startTime":"2019-08-15T06:41:17.640+0000","endTime":"2019-09-14T06:41:17.640+0000"}
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
         * id : 16
         * categoryName : 设备1
         * appCategoryPic : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
         * money : 1700
         * productAttributeList : []
         * times : []
         * sysUserInfos : []
         * shopGoodsEvaluates : []
         * additionalCosts : []
         * supportingEquipments : []
         * startTime : 2019-08-15T06:41:17.640+0000
         * endTime : 2019-09-14T06:41:17.640+0000
         */

        private int id;
        private String categoryName;
        private String appCategoryPic;
        private double money;
        private String startTime;
        private String endTime;
        private List<?> productAttributeList;
        private List<?> times;
        private List<?> sysUserInfos;
        private List<?> shopGoodsEvaluates;
        private List<?> additionalCosts;
        private List<?> supportingEquipments;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getAppCategoryPic() {
            return appCategoryPic;
        }

        public void setAppCategoryPic(String appCategoryPic) {
            this.appCategoryPic = appCategoryPic;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public List<?> getProductAttributeList() {
            return productAttributeList;
        }

        public void setProductAttributeList(List<?> productAttributeList) {
            this.productAttributeList = productAttributeList;
        }

        public List<?> getTimes() {
            return times;
        }

        public void setTimes(List<?> times) {
            this.times = times;
        }

        public List<?> getSysUserInfos() {
            return sysUserInfos;
        }

        public void setSysUserInfos(List<?> sysUserInfos) {
            this.sysUserInfos = sysUserInfos;
        }

        public List<?> getShopGoodsEvaluates() {
            return shopGoodsEvaluates;
        }

        public void setShopGoodsEvaluates(List<?> shopGoodsEvaluates) {
            this.shopGoodsEvaluates = shopGoodsEvaluates;
        }

        public List<?> getAdditionalCosts() {
            return additionalCosts;
        }

        public void setAdditionalCosts(List<?> additionalCosts) {
            this.additionalCosts = additionalCosts;
        }

        public List<?> getSupportingEquipments() {
            return supportingEquipments;
        }

        public void setSupportingEquipments(List<?> supportingEquipments) {
            this.supportingEquipments = supportingEquipments;
        }
    }
}
