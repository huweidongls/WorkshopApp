package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/15.
 */

public class CollectionListBean {

    /**
     * status : 200
     * data : [{"id":49,"categoryName":"测试车间8151141","appCategoryPic":"upload/workshop/2019-08-15/425f20b4cc8b4dd99fb25d91ec2ac79b.jpg","money":5000,"productAttributeList":[],"times":[],"sysUserInfos":[],"shopGoodsEvaluates":[],"additionalCosts":[],"supportingEquipments":[]},{"id":47,"categoryName":"测试车间815917","appCategoryPic":"upload/workshop/2019-08-15/425f20b4cc8b4dd99fb25d91ec2ac79b.jpg","money":5000,"productAttributeList":[],"times":[],"sysUserInfos":[],"shopGoodsEvaluates":[],"additionalCosts":[],"supportingEquipments":[]}]
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
         * id : 49
         * categoryName : 测试车间8151141
         * appCategoryPic : upload/workshop/2019-08-15/425f20b4cc8b4dd99fb25d91ec2ac79b.jpg
         * money : 5000
         * productAttributeList : []
         * times : []
         * sysUserInfos : []
         * shopGoodsEvaluates : []
         * additionalCosts : []
         * supportingEquipments : []
         */

        private int id;
        private String categoryName;
        private String appCategoryPic;
        private int money;
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

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
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
