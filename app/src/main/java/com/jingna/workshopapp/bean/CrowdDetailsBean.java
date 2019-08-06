package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/7/30.
 */

public class CrowdDetailsBean {

    /**
     * status : 200
     * data : {"id":1,"title":"众筹车间01","subtitle":"特别好的车间,地方贼大!","story":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg,upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","storyApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg,upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","endTime":"19","backgroundPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","allMoney":0,"allPeople":0,"list":[],"gearPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","gearTitle":"档位标题","gearSubtitle":"档位标题","gearMoney":500,"percentage":"0%","shopGoodsEvaluates":[{"id":1,"goodsEvaluate":"不好差评","likesNum":3,"createTime":"2019-07-25T07:02:30.000+0000","headPhoto":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","memName":"精纳科技头子"},{"id":2,"goodsEvaluate":"很好 好棒","likesNum":1,"createTime":"2019-07-25T07:05:54.000+0000","headPhoto":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","memName":"精纳科技头子"}]}
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
         * id : 1
         * title : 众筹车间01
         * subtitle : 特别好的车间,地方贼大!
         * story : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg,upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
         * storyApp : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg,upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
         * endTime : 19
         * backgroundPictureApp : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
         * allMoney : 0
         * allPeople : 0
         * list : []
         * gearPictureApp : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
         * gearTitle : 档位标题
         * gearSubtitle : 档位标题
         * gearMoney : 500
         * percentage : 0%
         * shopGoodsEvaluates : [{"id":1,"goodsEvaluate":"不好差评","likesNum":3,"createTime":"2019-07-25T07:02:30.000+0000","headPhoto":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","memName":"精纳科技头子"},{"id":2,"goodsEvaluate":"很好 好棒","likesNum":1,"createTime":"2019-07-25T07:05:54.000+0000","headPhoto":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","memName":"精纳科技头子"}]
         */

        private int id;
        private String title;
        private String subtitle;
        private String story;
        private String storyApp;
        private String endTime;
        private String backgroundPictureApp;
        private int allMoney;
        private int allPeople;
        private String gearPictureApp;
        private String gearTitle;
        private String gearSubtitle;
        private int gearMoney;
        private String percentage;
        private List<?> list;
        private List<ShopGoodsEvaluatesBean> shopGoodsEvaluates;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getStory() {
            return story;
        }

        public void setStory(String story) {
            this.story = story;
        }

        public String getStoryApp() {
            return storyApp;
        }

        public void setStoryApp(String storyApp) {
            this.storyApp = storyApp;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getBackgroundPictureApp() {
            return backgroundPictureApp;
        }

        public void setBackgroundPictureApp(String backgroundPictureApp) {
            this.backgroundPictureApp = backgroundPictureApp;
        }

        public int getAllMoney() {
            return allMoney;
        }

        public void setAllMoney(int allMoney) {
            this.allMoney = allMoney;
        }

        public int getAllPeople() {
            return allPeople;
        }

        public void setAllPeople(int allPeople) {
            this.allPeople = allPeople;
        }

        public String getGearPictureApp() {
            return gearPictureApp;
        }

        public void setGearPictureApp(String gearPictureApp) {
            this.gearPictureApp = gearPictureApp;
        }

        public String getGearTitle() {
            return gearTitle;
        }

        public void setGearTitle(String gearTitle) {
            this.gearTitle = gearTitle;
        }

        public String getGearSubtitle() {
            return gearSubtitle;
        }

        public void setGearSubtitle(String gearSubtitle) {
            this.gearSubtitle = gearSubtitle;
        }

        public int getGearMoney() {
            return gearMoney;
        }

        public void setGearMoney(int gearMoney) {
            this.gearMoney = gearMoney;
        }

        public String getPercentage() {
            return percentage;
        }

        public void setPercentage(String percentage) {
            this.percentage = percentage;
        }

        public List<?> getList() {
            return list;
        }

        public void setList(List<?> list) {
            this.list = list;
        }

        public List<ShopGoodsEvaluatesBean> getShopGoodsEvaluates() {
            return shopGoodsEvaluates;
        }

        public void setShopGoodsEvaluates(List<ShopGoodsEvaluatesBean> shopGoodsEvaluates) {
            this.shopGoodsEvaluates = shopGoodsEvaluates;
        }

        public static class ShopGoodsEvaluatesBean {
            /**
             * id : 1
             * goodsEvaluate : 不好差评
             * likesNum : 3
             * createTime : 2019-07-25T07:02:30.000+0000
             * headPhoto : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
             * memName : 精纳科技头子
             */

            private int id;
            private String goodsEvaluate;
            private int likesNum;
            private String createTime;
            private String headPhoto;
            private String memName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getGoodsEvaluate() {
                return goodsEvaluate;
            }

            public void setGoodsEvaluate(String goodsEvaluate) {
                this.goodsEvaluate = goodsEvaluate;
            }

            public int getLikesNum() {
                return likesNum;
            }

            public void setLikesNum(int likesNum) {
                this.likesNum = likesNum;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getHeadPhoto() {
                return headPhoto;
            }

            public void setHeadPhoto(String headPhoto) {
                this.headPhoto = headPhoto;
            }

            public String getMemName() {
                return memName;
            }

            public void setMemName(String memName) {
                this.memName = memName;
            }
        }
    }
}
