package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/21.
 */

public class CommissionIncomeBean {

    /**
     * status : 200
     * data : {"zhuanchu":2,"zhuanru":2041020,"commissionRevenues":[{"id":18,"type":"转出","userId":47,"money":0,"isDelete":0,"createTime":"2020-04-09 10:41:20"},{"id":9,"type":"转出","userId":47,"money":2,"isDelete":0,"createTime":"2020-04-07 16:34:06"},{"id":5,"type":"转出","userId":47,"money":0,"isDelete":0,"createTime":"2020-04-03 13:40:14"},{"id":6,"type":"转入","userId":47,"money":2040000,"orderId":"20200407142619927","isDelete":0,"createTime":"2020-04-03 13:40:14"},{"id":8,"type":"转入","userId":47,"money":0,"orderId":"20200407154534559","isDelete":0,"createTime":"2020-04-03 13:40:14"},{"id":11,"type":"转入","userId":47,"money":0,"orderId":"20200409093654576","isDelete":0,"createTime":"2020-04-03 13:40:14"},{"id":17,"type":"转入","userId":47,"money":1020,"orderId":"20200409093654576","isDelete":0,"createTime":"2020-04-03 13:40:14"},{"id":4,"type":"转出","userId":47,"money":0,"isDelete":0,"createTime":"2020-04-03 13:36:19"},{"id":3,"type":"转出","userId":47,"money":0,"isDelete":0,"createTime":"2020-04-02 11:52:32"},{"id":2,"type":"转出","userId":47,"money":0,"isDelete":0,"createTime":"2020-04-02 11:52:29"},{"id":1,"type":"转出","userId":47,"money":0,"isDelete":0,"createTime":"2020-04-02 11:52:26"}]}
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
         * zhuanchu : 2
         * zhuanru : 2041020
         * commissionRevenues : [{"id":18,"type":"转出","userId":47,"money":0,"isDelete":0,"createTime":"2020-04-09 10:41:20"},{"id":9,"type":"转出","userId":47,"money":2,"isDelete":0,"createTime":"2020-04-07 16:34:06"},{"id":5,"type":"转出","userId":47,"money":0,"isDelete":0,"createTime":"2020-04-03 13:40:14"},{"id":6,"type":"转入","userId":47,"money":2040000,"orderId":"20200407142619927","isDelete":0,"createTime":"2020-04-03 13:40:14"},{"id":8,"type":"转入","userId":47,"money":0,"orderId":"20200407154534559","isDelete":0,"createTime":"2020-04-03 13:40:14"},{"id":11,"type":"转入","userId":47,"money":0,"orderId":"20200409093654576","isDelete":0,"createTime":"2020-04-03 13:40:14"},{"id":17,"type":"转入","userId":47,"money":1020,"orderId":"20200409093654576","isDelete":0,"createTime":"2020-04-03 13:40:14"},{"id":4,"type":"转出","userId":47,"money":0,"isDelete":0,"createTime":"2020-04-03 13:36:19"},{"id":3,"type":"转出","userId":47,"money":0,"isDelete":0,"createTime":"2020-04-02 11:52:32"},{"id":2,"type":"转出","userId":47,"money":0,"isDelete":0,"createTime":"2020-04-02 11:52:29"},{"id":1,"type":"转出","userId":47,"money":0,"isDelete":0,"createTime":"2020-04-02 11:52:26"}]
         */

        private int zhuanchu;
        private int zhuanru;
        private List<CommissionRevenuesBean> commissionRevenues;

        public int getZhuanchu() {
            return zhuanchu;
        }

        public void setZhuanchu(int zhuanchu) {
            this.zhuanchu = zhuanchu;
        }

        public int getZhuanru() {
            return zhuanru;
        }

        public void setZhuanru(int zhuanru) {
            this.zhuanru = zhuanru;
        }

        public List<CommissionRevenuesBean> getCommissionRevenues() {
            return commissionRevenues;
        }

        public void setCommissionRevenues(List<CommissionRevenuesBean> commissionRevenues) {
            this.commissionRevenues = commissionRevenues;
        }

        public static class CommissionRevenuesBean {
            /**
             * id : 18
             * type : 转出
             * userId : 47
             * money : 0
             * isDelete : 0
             * createTime : 2020-04-09 10:41:20
             * orderId : 20200407142619927
             */

            private int id;
            private String type;
            private int userId;
            private int money;
            private int isDelete;
            private String createTime;
            private String orderId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getMoney() {
                return money;
            }

            public void setMoney(int money) {
                this.money = money;
            }

            public int getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(int isDelete) {
                this.isDelete = isDelete;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }
        }
    }
}
