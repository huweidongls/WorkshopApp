package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/22.
 */

public class OrderDetailsBean {
    /**
     * status : 200
     * data : {"id":"20190820144500935","addresPhone":"13652525252","addresUname":"都疼嗯嗯","addresName":"mottled","goodsId":"9","orderStatus":"0","invoiceId":"0","paymentTime":"2019-08-21 16:22:28","paymentMode":"微信支付","createTime":"2019-08-20 14:45:00","orderPrice":5250,"startTime":"2019-08-20 00:00:00","endTime":"2019-08-20 00:00:00","goodsPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","goodsTitle":"车间1","freightMoney":0,"list":[]}
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
         * id : 20190820144500935
         * addresPhone : 13652525252
         * addresUname : 都疼嗯嗯
         * addresName : mottled
         * goodsId : 9
         * orderStatus : 0
         * invoiceId : 0
         * paymentTime : 2019-08-21 16:22:28
         * paymentMode : 微信支付
         * createTime : 2019-08-20 14:45:00
         * orderPrice : 5250
         * startTime : 2019-08-20 00:00:00
         * endTime : 2019-08-20 00:00:00
         * goodsPictureApp : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
         * goodsTitle : 车间1
         * freightMoney : 0
         * list : []
         */

        private String id;
        private String addresPhone;
        private String addresUname;
        private String addresName;
        private String goodsId;
        private String orderStatus;
        private String invoiceId;
        private String paymentTime;
        private String paymentMode;
        private String createTime;
        private int orderPrice;
        private String startTime;
        private String endTime;
        private String goodsPictureApp;
        private String goodsTitle;
        private int freightMoney;
        private List<?> list;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAddresPhone() {
            return addresPhone;
        }

        public void setAddresPhone(String addresPhone) {
            this.addresPhone = addresPhone;
        }

        public String getAddresUname() {
            return addresUname;
        }

        public void setAddresUname(String addresUname) {
            this.addresUname = addresUname;
        }

        public String getAddresName() {
            return addresName;
        }

        public void setAddresName(String addresName) {
            this.addresName = addresName;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getInvoiceId() {
            return invoiceId;
        }

        public void setInvoiceId(String invoiceId) {
            this.invoiceId = invoiceId;
        }

        public String getPaymentTime() {
            return paymentTime;
        }

        public void setPaymentTime(String paymentTime) {
            this.paymentTime = paymentTime;
        }

        public String getPaymentMode() {
            return paymentMode;
        }

        public void setPaymentMode(String paymentMode) {
            this.paymentMode = paymentMode;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getOrderPrice() {
            return orderPrice;
        }

        public void setOrderPrice(int orderPrice) {
            this.orderPrice = orderPrice;
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

        public String getGoodsPictureApp() {
            return goodsPictureApp;
        }

        public void setGoodsPictureApp(String goodsPictureApp) {
            this.goodsPictureApp = goodsPictureApp;
        }

        public String getGoodsTitle() {
            return goodsTitle;
        }

        public void setGoodsTitle(String goodsTitle) {
            this.goodsTitle = goodsTitle;
        }

        public int getFreightMoney() {
            return freightMoney;
        }

        public void setFreightMoney(int freightMoney) {
            this.freightMoney = freightMoney;
        }

        public List<?> getList() {
            return list;
        }

        public void setList(List<?> list) {
            this.list = list;
        }
    }
}
