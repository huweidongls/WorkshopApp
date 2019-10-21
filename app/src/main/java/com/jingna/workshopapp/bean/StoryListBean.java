package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/7/24.
 */

public class StoryListBean {

    /**
     * status : 200
     * data : [{"id":10,"storyType":0,"storyTitlePictureApp":"upload/story/2019-10-21/f9a8a9cfd69842f18b441d27d80ecb23.jpg","storyTitle":"公司故事"},{"id":9,"storyType":0,"storyTitlePictureApp":"upload/story/2019-10-18/b70c01101d6f46aca6d082820360bb03.jpg","storyTitle":"111112222"},{"id":7,"storyType":0,"storyTitlePictureApp":"upload/story/2019-10-18/88c85c9a8d5441e5afbffec544252222.jpg","storyTitle":"1111"}]
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
         * id : 10
         * storyType : 0
         * storyTitlePictureApp : upload/story/2019-10-21/f9a8a9cfd69842f18b441d27d80ecb23.jpg
         * storyTitle : 公司故事
         */

        private int id;
        private int storyType;
        private String storyTitlePictureApp;
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

        public String getStoryTitlePictureApp() {
            return storyTitlePictureApp;
        }

        public void setStoryTitlePictureApp(String storyTitlePictureApp) {
            this.storyTitlePictureApp = storyTitlePictureApp;
        }

        public String getStoryTitle() {
            return storyTitle;
        }

        public void setStoryTitle(String storyTitle) {
            this.storyTitle = storyTitle;
        }
    }
}
