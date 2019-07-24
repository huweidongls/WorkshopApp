package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/7/24.
 */

public class StoryListBean {

    /**
     * status : 200
     * data : [{"id":1,"storyType":0,"storyTitlePictrueApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","storyTitle":"车间故事1"},{"id":2,"storyType":1,"storyTitlePictrueApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","storyTitle":"车间故事2"},{"id":3,"storyType":1,"storyTitlePictrueApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","storyTitle":"车间故事13"}]
     * totalPage : 0
     * totalCount : 3
     */

    private String status;
    private int totalPage;
    private int totalCount;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
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
         * id : 1
         * storyType : 0
         * storyTitlePictrueApp : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
         * storyTitle : 车间故事1
         */

        private int id;
        private int storyType;
        private String storyTitlePictrueApp;
        private String storyTitle;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStoryType() {
            return storyType;
        }

        public void setStoryType(int storyType) {
            this.storyType = storyType;
        }

        public String getStoryTitlePictrueApp() {
            return storyTitlePictrueApp;
        }

        public void setStoryTitlePictrueApp(String storyTitlePictrueApp) {
            this.storyTitlePictrueApp = storyTitlePictrueApp;
        }

        public String getStoryTitle() {
            return storyTitle;
        }

        public void setStoryTitle(String storyTitle) {
            this.storyTitle = storyTitle;
        }
    }
}
