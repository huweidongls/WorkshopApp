package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/16.
 */

public class CommitOrderChejianBean {

    /**
     * status : 200
     * data : {"goodsId":"9","orderPrice":5000,"equipmentMoney":101,"workshopPicture":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","workshopName":"车间1","list":[]}
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
         * goodsId : 9
         * orderPrice : 5000
         * equipmentMoney : 101
         * workshopPicture : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
         * workshopName : 车间1
         * list : []
         */

        private String goodsId;
        private int orderPrice;
        private int equipmentMoney;
        private String workshopPicture;
        private String workshopName;
        private List<?> list;

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public int getOrderPrice() {
            return orderPrice;
        }

        public void setOrderPrice(int orderPrice) {
            this.orderPrice = orderPrice;
        }

        public int getEquipmentMoney() {
            return equipmentMoney;
        }

        public void setEquipmentMoney(int equipmentMoney) {
            this.equipmentMoney = equipmentMoney;
        }

        public String getWorkshopPicture() {
            return workshopPicture;
        }

        public void setWorkshopPicture(String workshopPicture) {
            this.workshopPicture = workshopPicture;
        }

        public String getWorkshopName() {
            return workshopName;
        }

        public void setWorkshopName(String workshopName) {
            this.workshopName = workshopName;
        }

        public List<?> getList() {
            return list;
        }

        public void setList(List<?> list) {
            this.list = list;
        }
    }
}
