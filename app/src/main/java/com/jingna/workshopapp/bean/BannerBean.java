package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/7/23.
 */

public class BannerBean {

    /**
     * status : 200
     * errorMsg :
     * data : [{"id":"","operator":"","pictureTitle":"","picture":"","appPic":"upload/sysBanner/2019-04-25/05f53dad01ba4e7489a7aec9e194439b.jpg","skipSite":"www.baidu.com","weight":"","state":"","createTime":"","updateTime":"","isDelete":""},{"id":"","operator":"","pictureTitle":"","picture":"","appPic":"upload/sysBanner/2019-04-25/d38eb81bdc294a6a952d39a07a35e536.jpg","skipSite":"www.baidu.com","weight":"","state":"","createTime":"","updateTime":"","isDelete":""}]
     * totalPage :
     * totalCount :
     */

    private String status;
    private String errorMsg;
    private String totalPage;
    private String totalCount;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id :
         * operator :
         * pictureTitle :
         * picture :
         * appPic : upload/sysBanner/2019-04-25/05f53dad01ba4e7489a7aec9e194439b.jpg
         * skipSite : www.baidu.com
         * weight :
         * state :
         * createTime :
         * updateTime :
         * isDelete :
         */

        private String id;
        private String operator;
        private String pictureTitle;
        private String picture;
        private String appPic;
        private String skipSite;
        private String weight;
        private String state;
        private String createTime;
        private String updateTime;
        private String isDelete;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public String getPictureTitle() {
            return pictureTitle;
        }

        public void setPictureTitle(String pictureTitle) {
            this.pictureTitle = pictureTitle;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getAppPic() {
            return appPic;
        }

        public void setAppPic(String appPic) {
            this.appPic = appPic;
        }

        public String getSkipSite() {
            return skipSite;
        }

        public void setSkipSite(String skipSite) {
            this.skipSite = skipSite;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
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

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }
    }
}
