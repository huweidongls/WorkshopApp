package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/1.
 */

public class CrowdPopularBean {

    /**
     * status : 200
     * data : [{"id":1,"allMoney":0,"allPeople":0,"list":[],"gearPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","gearTitle":"档位标题","gearSubtitle":"档位标题","gearMoney":1000,"percentage":"0%","shopGoodsEvaluates":[]},{"id":2,"allMoney":0,"allPeople":0,"list":[],"gearPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","gearTitle":"1档位标题","gearSubtitle":"档位标题","gearMoney":0,"percentage":"0%","shopGoodsEvaluates":[]}]
     * totalPage : 2
     * totalCount : 3
     */

    private String status;
    private int totalPage;
    private int totalCount;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * allMoney : 0
         * allPeople : 0
         * list : []
         * gearPictureApp : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
         * gearTitle : 档位标题
         * gearSubtitle : 档位标题
         * gearMoney : 1000
         * percentage : 0%
         * shopGoodsEvaluates : []
         */

        private int id;
        private int allMoney;
        private int allPeople;
        private String gearPictureApp;
        private String gearTitle;
        private String gearSubtitle;
        private int gearMoney;
        private String percentage;
        private List<?> list;
        private List<?> shopGoodsEvaluates;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
