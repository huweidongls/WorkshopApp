package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/16.
 */

public class CrowOrderBean {
    /**
     * status : 200
     * data : {"sellerId":"1","gearPositionPicture":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","gearPositionName":"众筹车间01","gearPositionTitle":"档位标题","gearPositionMoney":1015,"gearPositionSubTitle":"档位描述","freight":15,"deliveryTime":"30","gearPositionNum":1,"list":[]}
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
         * sellerId : 1
         * gearPositionPicture : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
         * gearPositionName : 众筹车间01
         * gearPositionTitle : 档位标题
         * gearPositionMoney : 1015
         * gearPositionSubTitle : 档位描述
         * freight : 15
         * deliveryTime : 30
         * gearPositionNum : 1
         * list : []
         */

        private String sellerId;
        private String gearPositionPicture;
        private String gearPositionName;
        private String gearPositionTitle;
        private int gearPositionMoney;
        private String gearPositionSubTitle;
        private int freight;
        private String deliveryTime;
        private int gearPositionNum;
        private List<?> list;

        public String getSellerId() {
            return sellerId;
        }

        public void setSellerId(String sellerId) {
            this.sellerId = sellerId;
        }

        public String getGearPositionPicture() {
            return gearPositionPicture;
        }

        public void setGearPositionPicture(String gearPositionPicture) {
            this.gearPositionPicture = gearPositionPicture;
        }

        public String getGearPositionName() {
            return gearPositionName;
        }

        public void setGearPositionName(String gearPositionName) {
            this.gearPositionName = gearPositionName;
        }

        public String getGearPositionTitle() {
            return gearPositionTitle;
        }

        public void setGearPositionTitle(String gearPositionTitle) {
            this.gearPositionTitle = gearPositionTitle;
        }

        public int getGearPositionMoney() {
            return gearPositionMoney;
        }

        public void setGearPositionMoney(int gearPositionMoney) {
            this.gearPositionMoney = gearPositionMoney;
        }

        public String getGearPositionSubTitle() {
            return gearPositionSubTitle;
        }

        public void setGearPositionSubTitle(String gearPositionSubTitle) {
            this.gearPositionSubTitle = gearPositionSubTitle;
        }

        public int getFreight() {
            return freight;
        }

        public void setFreight(int freight) {
            this.freight = freight;
        }

        public String getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(String deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public int getGearPositionNum() {
            return gearPositionNum;
        }

        public void setGearPositionNum(int gearPositionNum) {
            this.gearPositionNum = gearPositionNum;
        }

        public List<?> getList() {
            return list;
        }

        public void setList(List<?> list) {
            this.list = list;
        }
    }
}
