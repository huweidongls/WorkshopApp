package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/19.
 */

public class ZhongchouPopBean {

    /**
     * status : 200
     * data : [{"id":1,"freight":15,"deliveryTime":"30","list":[],"gearPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","gearSubtitle":"档位描述","gearMoney":1000,"shopGoodsEvaluates":[]},{"id":2,"freight":15,"deliveryTime":"30","list":[],"gearPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","gearSubtitle":"档位标题","gearMoney":2000,"shopGoodsEvaluates":[]},{"id":3,"freight":15,"deliveryTime":"30","list":[],"gearPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","gearSubtitle":"档位标题","gearMoney":500,"shopGoodsEvaluates":[]}]
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
         * freight : 15
         * deliveryTime : 30
         * list : []
         * gearPictureApp : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
         * gearSubtitle : 档位描述
         * gearMoney : 1000
         * shopGoodsEvaluates : []
         */

        private int id;
        private int freight;
        private String deliveryTime;
        private String gearPictureApp;
        private String gearSubtitle;
        private int gearMoney;
        private List<?> list;
        private List<?> shopGoodsEvaluates;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getGearPictureApp() {
            return gearPictureApp;
        }

        public void setGearPictureApp(String gearPictureApp) {
            this.gearPictureApp = gearPictureApp;
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
