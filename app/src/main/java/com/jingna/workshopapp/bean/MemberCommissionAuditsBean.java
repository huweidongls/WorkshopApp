package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/4/10.
 */

public class MemberCommissionAuditsBean {

    /**
     * status : 200
     * data : {"memberCommissionAudits":[{"id":15,"auditMoney":1,"createTime":"2020-04-09 10:43:26","isPass":0}]}
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
        private List<MemberCommissionAudits> memberCommissionAudits;

        public List<MemberCommissionAudits> getMemberCommissionAudits() {
            return memberCommissionAudits;
        }

        public void setMemberCommissionAudits(List<MemberCommissionAudits> memberCommissionAudits) {
            this.memberCommissionAudits = memberCommissionAudits;
        }

        public static class MemberCommissionAudits {
            /**
             * id : 15
             * auditMoney : 1
             * createTime : 2020-04-09 10:43:26
             * isPass : 0
             */

            private int id;
            private double auditMoney;
            private String createTime;
            private int isPass;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public double getAuditMoney() {
                return auditMoney;
            }

            public void setAuditMoney(double auditMoney) {
                this.auditMoney = auditMoney;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getIsPass() {
                return isPass;
            }

            public void setIsPass(int isPass) {
                this.isPass = isPass;
            }
        }
    }
}
