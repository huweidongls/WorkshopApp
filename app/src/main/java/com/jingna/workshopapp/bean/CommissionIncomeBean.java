package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/21.
 */

public class CommissionIncomeBean {

    /**
     * status : 200
     * data : {"zhuanchu":20,"zhuanru":0,"commissionRevenues":[{"id":2,"type":"转出","userId":46,"money":10,"isDelete":0,"createTime":"2019-08-21 11:49:54"}]}
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
         * zhuanchu : 20
         * zhuanru : 0
         * commissionRevenues : [{"id":2,"type":"转出","userId":46,"money":10,"isDelete":0,"createTime":"2019-08-21 11:49:54"}]
         */

        private double zhuanchu;
        private double zhuanru;
        private List<CommissionRevenuesBean> commissionRevenues;

        public double getZhuanchu() {
            return zhuanchu;
        }

        public void setZhuanchu(double zhuanchu) {
            this.zhuanchu = zhuanchu;
        }

        public double getZhuanru() {
            return zhuanru;
        }

        public void setZhuanru(double zhuanru) {
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
             * id : 2
             * type : 转出
             * userId : 46
             * money : 10
             * isDelete : 0
             * createTime : 2019-08-21 11:49:54
             */

            private int id;
            private String type;
            private int userId;
            private double money;
            private int isDelete;
            private String createTime;

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

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
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
        }
    }
}
