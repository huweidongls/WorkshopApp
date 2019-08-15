package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/15.
 */

public class LikeGoodsBean {

    /**
     * status : 200
     * data : {"shopCategories":{"shopCategorys":[{"id":9,"categoryName":"车间1","appCategoryPic":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","productAttributeList":[],"times":[],"sysUserInfos":[],"shopGoodsEvaluates":[],"additionalCosts":[],"supportingEquipments":[],"evaluate":0,"intEvalute":0},{"id":49,"categoryName":"测试车间8151141","appCategoryPic":"upload/workshop/2019-08-15/425f20b4cc8b4dd99fb25d91ec2ac79b.jpg","productAttributeList":[],"times":[],"sysUserInfos":[],"shopGoodsEvaluates":[],"additionalCosts":[],"supportingEquipments":[],"evaluate":0,"intEvalute":0},{"id":50,"categoryName":"测试车间08151345","appCategoryPic":"","productAttributeList":[],"times":[],"sysUserInfos":[],"shopGoodsEvaluates":[],"additionalCosts":[],"supportingEquipments":[],"evaluate":0,"intEvalute":0}],"crowdFundings":[]},"shopCategoriesWtjg":{"shopCategorys":[{"id":16,"categoryName":"设备1","appCategoryPic":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","money":1700,"productAttributeList":[],"times":[],"sysUserInfos":[],"shopGoodsEvaluates":[],"additionalCosts":[],"supportingEquipments":[],"evaluate":0,"intEvalute":0}],"crowdFundings":[]},"list":{"shopCategorys":[],"crowdFundings":[{"id":1,"endTime":"2019-08-26 12:14:37","allMoney":0,"allPeople":0,"list":[],"gearPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","gearTitle":"档位标题","gearSubtitle":"档位标题","gearMoney":500,"percentage":"0%","percentageNum":0,"shopGoodsEvaluates":[]}]}}
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
         * shopCategories : {"shopCategorys":[{"id":9,"categoryName":"车间1","appCategoryPic":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","productAttributeList":[],"times":[],"sysUserInfos":[],"shopGoodsEvaluates":[],"additionalCosts":[],"supportingEquipments":[],"evaluate":0,"intEvalute":0},{"id":49,"categoryName":"测试车间8151141","appCategoryPic":"upload/workshop/2019-08-15/425f20b4cc8b4dd99fb25d91ec2ac79b.jpg","productAttributeList":[],"times":[],"sysUserInfos":[],"shopGoodsEvaluates":[],"additionalCosts":[],"supportingEquipments":[],"evaluate":0,"intEvalute":0},{"id":50,"categoryName":"测试车间08151345","appCategoryPic":"","productAttributeList":[],"times":[],"sysUserInfos":[],"shopGoodsEvaluates":[],"additionalCosts":[],"supportingEquipments":[],"evaluate":0,"intEvalute":0}],"crowdFundings":[]}
         * shopCategoriesWtjg : {"shopCategorys":[{"id":16,"categoryName":"设备1","appCategoryPic":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","money":1700,"productAttributeList":[],"times":[],"sysUserInfos":[],"shopGoodsEvaluates":[],"additionalCosts":[],"supportingEquipments":[],"evaluate":0,"intEvalute":0}],"crowdFundings":[]}
         * list : {"shopCategorys":[],"crowdFundings":[{"id":1,"endTime":"2019-08-26 12:14:37","allMoney":0,"allPeople":0,"list":[],"gearPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","gearTitle":"档位标题","gearSubtitle":"档位标题","gearMoney":500,"percentage":"0%","percentageNum":0,"shopGoodsEvaluates":[]}]}
         */

        private ShopCategoriesBean shopCategories;
        private ShopCategoriesWtjgBean shopCategoriesWtjg;
        private ListBean list;

        public ShopCategoriesBean getShopCategories() {
            return shopCategories;
        }

        public void setShopCategories(ShopCategoriesBean shopCategories) {
            this.shopCategories = shopCategories;
        }

        public ShopCategoriesWtjgBean getShopCategoriesWtjg() {
            return shopCategoriesWtjg;
        }

        public void setShopCategoriesWtjg(ShopCategoriesWtjgBean shopCategoriesWtjg) {
            this.shopCategoriesWtjg = shopCategoriesWtjg;
        }

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ShopCategoriesBean {
            private List<ShopCategorysBean> shopCategorys;
            private List<?> crowdFundings;

            public List<ShopCategorysBean> getShopCategorys() {
                return shopCategorys;
            }

            public void setShopCategorys(List<ShopCategorysBean> shopCategorys) {
                this.shopCategorys = shopCategorys;
            }

            public List<?> getCrowdFundings() {
                return crowdFundings;
            }

            public void setCrowdFundings(List<?> crowdFundings) {
                this.crowdFundings = crowdFundings;
            }

            public static class ShopCategorysBean {
                /**
                 * id : 9
                 * categoryName : 车间1
                 * appCategoryPic : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
                 * productAttributeList : []
                 * times : []
                 * sysUserInfos : []
                 * shopGoodsEvaluates : []
                 * additionalCosts : []
                 * supportingEquipments : []
                 * evaluate : 0
                 * intEvalute : 0
                 */

                private int id;
                private String categoryName;
                private String appCategoryPic;
                private int evaluate;
                private int intEvalute;
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

                public int getEvaluate() {
                    return evaluate;
                }

                public void setEvaluate(int evaluate) {
                    this.evaluate = evaluate;
                }

                public int getIntEvalute() {
                    return intEvalute;
                }

                public void setIntEvalute(int intEvalute) {
                    this.intEvalute = intEvalute;
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

        public static class ShopCategoriesWtjgBean {
            private List<ShopCategorysBeanX> shopCategorys;
            private List<?> crowdFundings;

            public List<ShopCategorysBeanX> getShopCategorys() {
                return shopCategorys;
            }

            public void setShopCategorys(List<ShopCategorysBeanX> shopCategorys) {
                this.shopCategorys = shopCategorys;
            }

            public List<?> getCrowdFundings() {
                return crowdFundings;
            }

            public void setCrowdFundings(List<?> crowdFundings) {
                this.crowdFundings = crowdFundings;
            }

            public static class ShopCategorysBeanX {
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
                 * evaluate : 0
                 * intEvalute : 0
                 */

                private int id;
                private String categoryName;
                private String appCategoryPic;
                private int money;
                private int evaluate;
                private int intEvalute;
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

                public int getEvaluate() {
                    return evaluate;
                }

                public void setEvaluate(int evaluate) {
                    this.evaluate = evaluate;
                }

                public int getIntEvalute() {
                    return intEvalute;
                }

                public void setIntEvalute(int intEvalute) {
                    this.intEvalute = intEvalute;
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

        public static class ListBean {
            private List<?> shopCategorys;
            private List<CrowdFundingsBean> crowdFundings;

            public List<?> getShopCategorys() {
                return shopCategorys;
            }

            public void setShopCategorys(List<?> shopCategorys) {
                this.shopCategorys = shopCategorys;
            }

            public List<CrowdFundingsBean> getCrowdFundings() {
                return crowdFundings;
            }

            public void setCrowdFundings(List<CrowdFundingsBean> crowdFundings) {
                this.crowdFundings = crowdFundings;
            }

            public static class CrowdFundingsBean {
                /**
                 * id : 1
                 * endTime : 2019-08-26 12:14:37
                 * allMoney : 0
                 * allPeople : 0
                 * list : []
                 * gearPictureApp : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
                 * gearTitle : 档位标题
                 * gearSubtitle : 档位标题
                 * gearMoney : 500
                 * percentage : 0%
                 * percentageNum : 0
                 * shopGoodsEvaluates : []
                 */

                private int id;
                private String endTime;
                private int allMoney;
                private int allPeople;
                private String gearPictureApp;
                private String gearTitle;
                private String gearSubtitle;
                private int gearMoney;
                private String percentage;
                private int percentageNum;
                private List<?> list;
                private List<?> shopGoodsEvaluates;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getEndTime() {
                    return endTime;
                }

                public void setEndTime(String endTime) {
                    this.endTime = endTime;
                }

                public int getAllMoney() {
                    return allMoney;
                }

                public void setAllMoney(int allMoney) {
                    this.allMoney = allMoney;
                }

                public int getAllPeople() {
                    return allPeople;
                }

                public void setAllPeople(int allPeople) {
                    this.allPeople = allPeople;
                }

                public String getGearPictureApp() {
                    return gearPictureApp;
                }

                public void setGearPictureApp(String gearPictureApp) {
                    this.gearPictureApp = gearPictureApp;
                }

                public String getGearTitle() {
                    return gearTitle;
                }

                public void setGearTitle(String gearTitle) {
                    this.gearTitle = gearTitle;
                }

                public String getGearSubtitle() {
                    return gearSubtitle;
                }

                public void setGearSubtitle(String gearSubtitle) {
                    this.gearSubtitle = gearSubtitle;
                }

                public int getGearMoney() {
                    return gearMoney;
                }

                public void setGearMoney(int gearMoney) {
                    this.gearMoney = gearMoney;
                }

                public String getPercentage() {
                    return percentage;
                }

                public void setPercentage(String percentage) {
                    this.percentage = percentage;
                }

                public int getPercentageNum() {
                    return percentageNum;
                }

                public void setPercentageNum(int percentageNum) {
                    this.percentageNum = percentageNum;
                }

                public List<?> getList() {
                    return list;
                }

                public void setList(List<?> list) {
                    this.list = list;
                }

                public List<?> getShopGoodsEvaluates() {
                    return shopGoodsEvaluates;
                }

                public void setShopGoodsEvaluates(List<?> shopGoodsEvaluates) {
                    this.shopGoodsEvaluates = shopGoodsEvaluates;
                }
            }
        }
    }
}
