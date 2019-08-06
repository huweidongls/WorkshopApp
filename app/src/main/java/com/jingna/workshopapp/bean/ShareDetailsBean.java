package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/6.
 */

public class ShareDetailsBean {

    /**
     * status : 200
     * data : {"id":9,"categoryName":"车间1","pid":1,"categoryTextApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","appCategoryPic":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","bannerApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","money":5000,"workshopAddress":"属性名称1","workshopInformationApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","supportingServicesApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","nearbyFacilities":"滑滑梯，蹦蹦床，哼哼哼","positionalCoordinates":"17.369,896.123","instructionsUseApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","safetyInstructionsApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","expectedDay":31,"productAttributeList":[],"times":[{"month":"七月","time":["1","2","3","4","5","6","7"]},{"month":"八月","time":["1","2","3","4","5"]}],"sysUserInfos":[],"shopGoodsEvaluates":[{"id":3,"goodsEvaluate":"这个众筹商品意外的好","likesNum":1,"headPhoto":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","memName":"精纳科技头子"}],"additionalCosts":[],"supportingEquipments":[{"equipmentName":"测试?","equipmentIconApp":"upload/equipmentIcon/2019-08-05/d6760e835b41414a94405b410aac1971.jpg"},{"equipmentName":"测试","equipmentIconApp":"upload/equipmentIcon/2019-08-05/d88bb8c3960f47d9bc28809d311b2b7a.jpg"}]}
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
         * id : 9
         * categoryName : 车间1
         * pid : 1
         * categoryTextApp : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
         * appCategoryPic : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
         * bannerApp : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
         * money : 5000
         * workshopAddress : 属性名称1
         * workshopInformationApp : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
         * supportingServicesApp : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
         * nearbyFacilities : 滑滑梯，蹦蹦床，哼哼哼
         * positionalCoordinates : 17.369,896.123
         * instructionsUseApp : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
         * safetyInstructionsApp : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
         * expectedDay : 31
         * productAttributeList : []
         * times : [{"month":"七月","time":["1","2","3","4","5","6","7"]},{"month":"八月","time":["1","2","3","4","5"]}]
         * sysUserInfos : []
         * shopGoodsEvaluates : [{"id":3,"goodsEvaluate":"这个众筹商品意外的好","likesNum":1,"headPhoto":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","memName":"精纳科技头子"}]
         * additionalCosts : []
         * supportingEquipments : [{"equipmentName":"测试?","equipmentIconApp":"upload/equipmentIcon/2019-08-05/d6760e835b41414a94405b410aac1971.jpg"},{"equipmentName":"测试","equipmentIconApp":"upload/equipmentIcon/2019-08-05/d88bb8c3960f47d9bc28809d311b2b7a.jpg"}]
         */

        private int id;
        private String categoryName;
        private int pid;
        private String categoryTextApp;
        private String appCategoryPic;
        private String bannerApp;
        private int money;
        private String workshopAddress;
        private String workshopInformationApp;
        private String supportingServicesApp;
        private String nearbyFacilities;
        private String positionalCoordinates;
        private String instructionsUseApp;
        private String safetyInstructionsApp;
        private int expectedDay;
        private List<?> productAttributeList;
        private List<TimesBean> times;
        private List<?> sysUserInfos;
        private List<ShopGoodsEvaluatesBean> shopGoodsEvaluates;
        private List<?> additionalCosts;
        private List<SupportingEquipmentsBean> supportingEquipments;

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

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getCategoryTextApp() {
            return categoryTextApp;
        }

        public void setCategoryTextApp(String categoryTextApp) {
            this.categoryTextApp = categoryTextApp;
        }

        public String getAppCategoryPic() {
            return appCategoryPic;
        }

        public void setAppCategoryPic(String appCategoryPic) {
            this.appCategoryPic = appCategoryPic;
        }

        public String getBannerApp() {
            return bannerApp;
        }

        public void setBannerApp(String bannerApp) {
            this.bannerApp = bannerApp;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getWorkshopAddress() {
            return workshopAddress;
        }

        public void setWorkshopAddress(String workshopAddress) {
            this.workshopAddress = workshopAddress;
        }

        public String getWorkshopInformationApp() {
            return workshopInformationApp;
        }

        public void setWorkshopInformationApp(String workshopInformationApp) {
            this.workshopInformationApp = workshopInformationApp;
        }

        public String getSupportingServicesApp() {
            return supportingServicesApp;
        }

        public void setSupportingServicesApp(String supportingServicesApp) {
            this.supportingServicesApp = supportingServicesApp;
        }

        public String getNearbyFacilities() {
            return nearbyFacilities;
        }

        public void setNearbyFacilities(String nearbyFacilities) {
            this.nearbyFacilities = nearbyFacilities;
        }

        public String getPositionalCoordinates() {
            return positionalCoordinates;
        }

        public void setPositionalCoordinates(String positionalCoordinates) {
            this.positionalCoordinates = positionalCoordinates;
        }

        public String getInstructionsUseApp() {
            return instructionsUseApp;
        }

        public void setInstructionsUseApp(String instructionsUseApp) {
            this.instructionsUseApp = instructionsUseApp;
        }

        public String getSafetyInstructionsApp() {
            return safetyInstructionsApp;
        }

        public void setSafetyInstructionsApp(String safetyInstructionsApp) {
            this.safetyInstructionsApp = safetyInstructionsApp;
        }

        public int getExpectedDay() {
            return expectedDay;
        }

        public void setExpectedDay(int expectedDay) {
            this.expectedDay = expectedDay;
        }

        public List<?> getProductAttributeList() {
            return productAttributeList;
        }

        public void setProductAttributeList(List<?> productAttributeList) {
            this.productAttributeList = productAttributeList;
        }

        public List<TimesBean> getTimes() {
            return times;
        }

        public void setTimes(List<TimesBean> times) {
            this.times = times;
        }

        public List<?> getSysUserInfos() {
            return sysUserInfos;
        }

        public void setSysUserInfos(List<?> sysUserInfos) {
            this.sysUserInfos = sysUserInfos;
        }

        public List<ShopGoodsEvaluatesBean> getShopGoodsEvaluates() {
            return shopGoodsEvaluates;
        }

        public void setShopGoodsEvaluates(List<ShopGoodsEvaluatesBean> shopGoodsEvaluates) {
            this.shopGoodsEvaluates = shopGoodsEvaluates;
        }

        public List<?> getAdditionalCosts() {
            return additionalCosts;
        }

        public void setAdditionalCosts(List<?> additionalCosts) {
            this.additionalCosts = additionalCosts;
        }

        public List<SupportingEquipmentsBean> getSupportingEquipments() {
            return supportingEquipments;
        }

        public void setSupportingEquipments(List<SupportingEquipmentsBean> supportingEquipments) {
            this.supportingEquipments = supportingEquipments;
        }

        public static class TimesBean {
            /**
             * month : 七月
             * time : ["1","2","3","4","5","6","7"]
             */

            private String month;
            private List<String> time;

            public String getMonth() {
                return month;
            }

            public void setMonth(String month) {
                this.month = month;
            }

            public List<String> getTime() {
                return time;
            }

            public void setTime(List<String> time) {
                this.time = time;
            }
        }

        public static class ShopGoodsEvaluatesBean {
            /**
             * id : 3
             * goodsEvaluate : 这个众筹商品意外的好
             * likesNum : 1
             * headPhoto : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
             * memName : 精纳科技头子
             */

            private int id;
            private String goodsEvaluate;
            private int likesNum;
            private String headPhoto;
            private String memName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getGoodsEvaluate() {
                return goodsEvaluate;
            }

            public void setGoodsEvaluate(String goodsEvaluate) {
                this.goodsEvaluate = goodsEvaluate;
            }

            public int getLikesNum() {
                return likesNum;
            }

            public void setLikesNum(int likesNum) {
                this.likesNum = likesNum;
            }

            public String getHeadPhoto() {
                return headPhoto;
            }

            public void setHeadPhoto(String headPhoto) {
                this.headPhoto = headPhoto;
            }

            public String getMemName() {
                return memName;
            }

            public void setMemName(String memName) {
                this.memName = memName;
            }
        }

        public static class SupportingEquipmentsBean {
            /**
             * equipmentName : 测试?
             * equipmentIconApp : upload/equipmentIcon/2019-08-05/d6760e835b41414a94405b410aac1971.jpg
             */

            private String equipmentName;
            private String equipmentIconApp;

            public String getEquipmentName() {
                return equipmentName;
            }

            public void setEquipmentName(String equipmentName) {
                this.equipmentName = equipmentName;
            }

            public String getEquipmentIconApp() {
                return equipmentIconApp;
            }

            public void setEquipmentIconApp(String equipmentIconApp) {
                this.equipmentIconApp = equipmentIconApp;
            }
        }
    }
}
