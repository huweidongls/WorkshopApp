package com.jingna.workshopapp.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2019/4/8.
 */

public class WxPayBean {

    /**
     * status : 200
     * data : {"appid":"wxc67fb969dcedd5a0","partnerid":"1530281181","package":"Sign=WXPay","noncestr":"974a8a8f798c42bea259d26134fa3a26","timestamp":"1565856105","prepayid":"wx15160146297154da326410a11697102600","paySign":"AF488663B5FC67AC2D89C3E0278861A2","tradetype":"APP"}
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
         * appid : wxc67fb969dcedd5a0
         * partnerid : 1530281181
         * package : Sign=WXPay
         * noncestr : 974a8a8f798c42bea259d26134fa3a26
         * timestamp : 1565856105
         * prepayid : wx15160146297154da326410a11697102600
         * paySign : AF488663B5FC67AC2D89C3E0278861A2
         * tradetype : APP
         */

        private String appid;
        private String partnerid;
        @SerializedName("package")
        private String packageX;
        private String noncestr;
        private String timestamp;
        private String prepayid;
        private String paySign;
        private String tradetype;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getPaySign() {
            return paySign;
        }

        public void setPaySign(String paySign) {
            this.paySign = paySign;
        }

        public String getTradetype() {
            return tradetype;
        }

        public void setTradetype(String tradetype) {
            this.tradetype = tradetype;
        }
    }
}
