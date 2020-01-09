package com.jingna.workshopapp.bean;

/**
 * Created by Administrator on 2019/7/23.
 */

public class GetOneBean {

    /**
     * status : 200
     * data : {"goodsNum":1,"sellerNum":0,"browseRecord":0,"memberUserInfo":{"id":52,"gender":"0","memBirthday":"1995-08-30","memName":"茶派呀","headPhoto":"/upload/appHeadPhoto/ea226f14584147a18535c4f3f21b5056.png","username":"15561817068","password":"e10adc3949ba59abbe56e057f20f883e","phoneNum":"15561817068","newTime":"2019年08月30日","personalInvitationCode":"e1288466","superiorInvitationCode":"savd16sz","totalAmount":0,"withdrawableCash":0,"historicalCashWithdrawal":0}}
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
         * goodsNum : 1
         * sellerNum : 0
         * browseRecord : 0
         * memberUserInfo : {"id":52,"gender":"0","memBirthday":"1995-08-30","memName":"茶派呀","headPhoto":"/upload/appHeadPhoto/ea226f14584147a18535c4f3f21b5056.png","username":"15561817068","password":"e10adc3949ba59abbe56e057f20f883e","phoneNum":"15561817068","newTime":"2019年08月30日","personalInvitationCode":"e1288466","superiorInvitationCode":"savd16sz","totalAmount":0,"withdrawableCash":0,"historicalCashWithdrawal":0}
         */

        private int goodsNum;
        private int sellerNum;
        private int browseRecord;
        private MemberUserInfoBean memberUserInfo;

        public int getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(int goodsNum) {
            this.goodsNum = goodsNum;
        }

        public int getSellerNum() {
            return sellerNum;
        }

        public void setSellerNum(int sellerNum) {
            this.sellerNum = sellerNum;
        }

        public int getBrowseRecord() {
            return browseRecord;
        }

        public void setBrowseRecord(int browseRecord) {
            this.browseRecord = browseRecord;
        }

        public MemberUserInfoBean getMemberUserInfo() {
            return memberUserInfo;
        }

        public void setMemberUserInfo(MemberUserInfoBean memberUserInfo) {
            this.memberUserInfo = memberUserInfo;
        }

        public static class MemberUserInfoBean {
            /**
             * id : 52
             * gender : 0
             * memBirthday : 1995-08-30
             * memName : 茶派呀
             * headPhoto : /upload/appHeadPhoto/ea226f14584147a18535c4f3f21b5056.png
             * username : 15561817068
             * password : e10adc3949ba59abbe56e057f20f883e
             * phoneNum : 15561817068
             * newTime : 2019年08月30日
             * personalInvitationCode : e1288466
             * superiorInvitationCode : savd16sz
             * totalAmount : 0
             * withdrawableCash : 0
             * historicalCashWithdrawal : 0
             */

            private int id;
            private String gender;
            private String memBirthday;
            private String memName;
            private String headPhoto;
            private String username;
            private String password;
            private String phoneNum;
            private String newTime;
            private String personalInvitationCode;
            private String superiorInvitationCode;
            private int totalAmount;
            private int withdrawableCash;
            private int historicalCashWithdrawal;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getMemBirthday() {
                return memBirthday;
            }

            public void setMemBirthday(String memBirthday) {
                this.memBirthday = memBirthday;
            }

            public String getMemName() {
                return memName;
            }

            public void setMemName(String memName) {
                this.memName = memName;
            }

            public String getHeadPhoto() {
                return headPhoto;
            }

            public void setHeadPhoto(String headPhoto) {
                this.headPhoto = headPhoto;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getPhoneNum() {
                return phoneNum;
            }

            public void setPhoneNum(String phoneNum) {
                this.phoneNum = phoneNum;
            }

            public String getNewTime() {
                return newTime;
            }

            public void setNewTime(String newTime) {
                this.newTime = newTime;
            }

            public String getPersonalInvitationCode() {
                return personalInvitationCode;
            }

            public void setPersonalInvitationCode(String personalInvitationCode) {
                this.personalInvitationCode = personalInvitationCode;
            }

            public String getSuperiorInvitationCode() {
                return superiorInvitationCode;
            }

            public void setSuperiorInvitationCode(String superiorInvitationCode) {
                this.superiorInvitationCode = superiorInvitationCode;
            }

            public int getTotalAmount() {
                return totalAmount;
            }

            public void setTotalAmount(int totalAmount) {
                this.totalAmount = totalAmount;
            }

            public int getWithdrawableCash() {
                return withdrawableCash;
            }

            public void setWithdrawableCash(int withdrawableCash) {
                this.withdrawableCash = withdrawableCash;
            }

            public int getHistoricalCashWithdrawal() {
                return historicalCashWithdrawal;
            }

            public void setHistoricalCashWithdrawal(int historicalCashWithdrawal) {
                this.historicalCashWithdrawal = historicalCashWithdrawal;
            }
        }
    }
}
