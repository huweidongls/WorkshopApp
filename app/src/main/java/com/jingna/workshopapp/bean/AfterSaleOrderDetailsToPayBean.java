package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/28.
 */

public class AfterSaleOrderDetailsToPayBean {

    /**
     * status : 200
     * data : {"id":"20190826104446656","repairId":"49","deviceId":"1,2","userId":"46","addresId":"1","addresCoordinate":"126.63238520689535,45.7427950567895","addresPhone":"18686817319","addresUname":"哈哈哈","addresName":"汉广街41号","addresCode":"150000","orderRealPrice":0,"orderPrice":0,"carMoney":0,"orderStatus":"2","createTime":"2019-08-26 11:41:05","updateTime":"2019-08-28T02:03:48.000+0000","isDelete":0,"repairTime":3,"repairMoney":0,"appAfterSaleEquipments":[],"afterSaleOrderItems":[{"id":1,"orderId":"20190826104446656","name":"电钻","money":10,"company":"天","num":5}],"repairTimeMoney":147}
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
         * id : 20190826104446656
         * repairId : 49
         * deviceId : 1,2
         * userId : 46
         * addresId : 1
         * addresCoordinate : 126.63238520689535,45.7427950567895
         * addresPhone : 18686817319
         * addresUname : 哈哈哈
         * addresName : 汉广街41号
         * addresCode : 150000
         * orderRealPrice : 0
         * orderPrice : 0
         * carMoney : 0
         * orderStatus : 2
         * createTime : 2019-08-26 11:41:05
         * updateTime : 2019-08-28T02:03:48.000+0000
         * isDelete : 0
         * repairTime : 3
         * repairMoney : 0
         * appAfterSaleEquipments : []
         * afterSaleOrderItems : [{"id":1,"orderId":"20190826104446656","name":"电钻","money":10,"company":"天","num":5}]
         * repairTimeMoney : 147
         */

        private String id;
        private String repairId;
        private String deviceId;
        private String userId;
        private String addresId;
        private String addresCoordinate;
        private String addresPhone;
        private String addresUname;
        private String addresName;
        private String addresCode;
        private double orderRealPrice;
        private double orderPrice;
        private double carMoney;
        private String orderStatus;
        private String createTime;
        private String updateTime;
        private int isDelete;
        private int repairTime;
        private double repairMoney;
        private double repairTimeMoney;
        private List<?> appAfterSaleEquipments;
        private List<AfterSaleOrderItemsBean> afterSaleOrderItems;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRepairId() {
            return repairId;
        }

        public void setRepairId(String repairId) {
            this.repairId = repairId;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getAddresId() {
            return addresId;
        }

        public void setAddresId(String addresId) {
            this.addresId = addresId;
        }

        public String getAddresCoordinate() {
            return addresCoordinate;
        }

        public void setAddresCoordinate(String addresCoordinate) {
            this.addresCoordinate = addresCoordinate;
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

        public String getAddresCode() {
            return addresCode;
        }

        public void setAddresCode(String addresCode) {
            this.addresCode = addresCode;
        }

        public double getOrderRealPrice() {
            return orderRealPrice;
        }

        public void setOrderRealPrice(double orderRealPrice) {
            this.orderRealPrice = orderRealPrice;
        }

        public double getOrderPrice() {
            return orderPrice;
        }

        public void setOrderPrice(double orderPrice) {
            this.orderPrice = orderPrice;
        }

        public double getCarMoney() {
            return carMoney;
        }

        public void setCarMoney(double carMoney) {
            this.carMoney = carMoney;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public int getRepairTime() {
            return repairTime;
        }

        public void setRepairTime(int repairTime) {
            this.repairTime = repairTime;
        }

        public double getRepairMoney() {
            return repairMoney;
        }

        public void setRepairMoney(double repairMoney) {
            this.repairMoney = repairMoney;
        }

        public double getRepairTimeMoney() {
            return repairTimeMoney;
        }

        public void setRepairTimeMoney(double repairTimeMoney) {
            this.repairTimeMoney = repairTimeMoney;
        }

        public List<?> getAppAfterSaleEquipments() {
            return appAfterSaleEquipments;
        }

        public void setAppAfterSaleEquipments(List<?> appAfterSaleEquipments) {
            this.appAfterSaleEquipments = appAfterSaleEquipments;
        }

        public List<AfterSaleOrderItemsBean> getAfterSaleOrderItems() {
            return afterSaleOrderItems;
        }

        public void setAfterSaleOrderItems(List<AfterSaleOrderItemsBean> afterSaleOrderItems) {
            this.afterSaleOrderItems = afterSaleOrderItems;
        }

        public static class AfterSaleOrderItemsBean {
            /**
             * id : 1
             * orderId : 20190826104446656
             * name : 电钻
             * money : 10
             * company : 天
             * num : 5
             */

            private int id;
            private String orderId;
            private String name;
            private int money;
            private String company;
            private int num;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getMoney() {
                return money;
            }

            public void setMoney(int money) {
                this.money = money;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }
        }
    }
}
