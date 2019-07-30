package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/7/30.
 */

public class CrowdTuijianBean {

    /**
     * status : 200
     * data : [{"id":1,"allMoney":0,"allPeople":0,"list":[],"gearPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","gearTitle":"档位标题","gearSubtitle":"档位标题","gearMoney":0,"percentage":"0%"},{"id":2,"allMoney":0,"allPeople":0,"list":[],"gearPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","gearTitle":"档位标题","gearSubtitle":"档位标题","gearMoney":0,"percentage":"0%"}]
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
         * id : 1
         * allMoney : 0
         * allPeople : 0
         * list : []
         * gearPictureApp : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
         * gearTitle : 档位标题
         * gearSubtitle : 档位标题
         * gearMoney : 0
         * percentage : 0%
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
    }
}
