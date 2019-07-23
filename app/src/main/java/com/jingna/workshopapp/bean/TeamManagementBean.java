package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/7/23.
 */

public class TeamManagementBean {

    /**
     * status : 200
     * errorMsg :
     * data : [{"id":48,"memAge":"19","gender":"0","memBirthday":"2000-01-01","memName":"嘻嘻","headPhoto":"/upload/b8212ae920a44cee846fb4b2a8179689.png","username":"18686817318","password":"e10adc3949ba59abbe56e057f20f883e","memBalance":"","memIntegral":"","phoneNum":"18686817318","isFrozen":0,"newTime":"2019年07月23日","memStatus":0,"personalInvitationCode":"savd16sz","superiorInvitationCode":"1c87ccc8"},{"id":46,"memAge":"","gender":"1","memBirthday":"2019-07-27","memName":"哈哈","headPhoto":"/upload/b8212ae920a44cee846fb4b2a8179689.png","username":"18686817319","password":"e10adc3949ba59abbe56e057f20f883e","memBalance":"","memIntegral":"","phoneNum":"18686817319","isFrozen":0,"newTime":"2019年07月23日","memStatus":0,"personalInvitationCode":"1c87ccc8","superiorInvitationCode":"zhcjsyst"}]
     * totalPage :
     * totalCount :
     * userNameFromToken :
     */

    private String status;
    private String errorMsg;
    private String totalPage;
    private String totalCount;
    private String userNameFromToken;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getUserNameFromToken() {
        return userNameFromToken;
    }

    public void setUserNameFromToken(String userNameFromToken) {
        this.userNameFromToken = userNameFromToken;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 48
         * memAge : 19
         * gender : 0
         * memBirthday : 2000-01-01
         * memName : 嘻嘻
         * headPhoto : /upload/b8212ae920a44cee846fb4b2a8179689.png
         * username : 18686817318
         * password : e10adc3949ba59abbe56e057f20f883e
         * memBalance :
         * memIntegral :
         * phoneNum : 18686817318
         * isFrozen : 0
         * newTime : 2019年07月23日
         * memStatus : 0
         * personalInvitationCode : savd16sz
         * superiorInvitationCode : 1c87ccc8
         */

        private int id;
        private String memAge;
        private String gender;
        private String memBirthday;
        private String memName;
        private String headPhoto;
        private String username;
        private String password;
        private String memBalance;
        private String memIntegral;
        private String phoneNum;
        private int isFrozen;
        private String newTime;
        private int memStatus;
        private String personalInvitationCode;
        private String superiorInvitationCode;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMemAge() {
            return memAge;
        }

        public void setMemAge(String memAge) {
            this.memAge = memAge;
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

        public String getMemBalance() {
            return memBalance;
        }

        public void setMemBalance(String memBalance) {
            this.memBalance = memBalance;
        }

        public String getMemIntegral() {
            return memIntegral;
        }

        public void setMemIntegral(String memIntegral) {
            this.memIntegral = memIntegral;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        public int getIsFrozen() {
            return isFrozen;
        }

        public void setIsFrozen(int isFrozen) {
            this.isFrozen = isFrozen;
        }

        public String getNewTime() {
            return newTime;
        }

        public void setNewTime(String newTime) {
            this.newTime = newTime;
        }

        public int getMemStatus() {
            return memStatus;
        }

        public void setMemStatus(int memStatus) {
            this.memStatus = memStatus;
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
    }
}
