package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/7/30.
 */

public class CrowdDetailsBean {

    /**
     * status : 200
     * data : {"id":1,"title":"众筹车间01","subtitle":"特别好的车间,地方贼大!","story":"测试小故事","presetAmount":10000,"endTime":"2019-07-28T09:59:41.000+0000","backgroundPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","list":[{"id":1,"crowdFundingId":1,"goodsPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","goodsTitle":"档位标题","goodsNum":0,"goodsMoney":1000},{"id":2,"crowdFundingId":1,"goodsPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","goodsTitle":"档位标题","goodsNum":0,"goodsMoney":2000},{"id":3,"crowdFundingId":1,"goodsPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","goodsTitle":"档位标题","goodsNum":0,"goodsMoney":3000}],"floorPrice":1000,"shopGoodsEvaluates":[{"id":1,"goodsEvaluate":"不好差评","headPhoto":"1","memName":"1"},{"id":2,"goodsEvaluate":"很好 好棒","headPhoto":"1","memName":"1"},{"id":3,"goodsEvaluate":"这个众筹商品意外的好","headPhoto":"1","memName":"1"}]}
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
         * story : 测试小故事
         * presetAmount : 10000
         * endTime : 2019-07-28T09:59:41.000+0000
         * backgroundPictureApp : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
         * list : [{"id":1,"crowdFundingId":1,"goodsPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","goodsTitle":"档位标题","goodsNum":0,"goodsMoney":1000},{"id":2,"crowdFundingId":1,"goodsPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","goodsTitle":"档位标题","goodsNum":0,"goodsMoney":2000},{"id":3,"crowdFundingId":1,"goodsPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","goodsTitle":"档位标题","goodsNum":0,"goodsMoney":3000}]
         * floorPrice : 1000
         * shopGoodsEvaluates : [{"id":1,"goodsEvaluate":"不好差评","headPhoto":"1","memName":"1"},{"id":2,"goodsEvaluate":"很好 好棒","headPhoto":"1","memName":"1"},{"id":3,"goodsEvaluate":"这个众筹商品意外的好","headPhoto":"1","memName":"1"}]
         */

        private int id;
        private String title;
        private String subtitle;
        private String story;
        private int presetAmount;
        private String endTime;
        private String backgroundPictureApp;
        private int floorPrice;
        private List<ListBean> list;
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

        public int getPresetAmount() {
            return presetAmount;
        }

        public void setPresetAmount(int presetAmount) {
            this.presetAmount = presetAmount;
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

        public int getFloorPrice() {
            return floorPrice;
        }

        public void setFloorPrice(int floorPrice) {
            this.floorPrice = floorPrice;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<ShopGoodsEvaluatesBean> getShopGoodsEvaluates() {
            return shopGoodsEvaluates;
        }

        public void setShopGoodsEvaluates(List<ShopGoodsEvaluatesBean> shopGoodsEvaluates) {
            this.shopGoodsEvaluates = shopGoodsEvaluates;
        }

        public static class ListBean {
            /**
             * id : 1
             * crowdFundingId : 1
             * goodsPictureApp : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
             * goodsTitle : 档位标题
             * goodsNum : 0
             * goodsMoney : 1000
             */

            private int id;
            private int crowdFundingId;
            private String goodsPictureApp;
            private String goodsTitle;
            private int goodsNum;
            private int goodsMoney;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCrowdFundingId() {
                return crowdFundingId;
            }

            public void setCrowdFundingId(int crowdFundingId) {
                this.crowdFundingId = crowdFundingId;
            }

            public String getGoodsPictureApp() {
                return goodsPictureApp;
            }

            public void setGoodsPictureApp(String goodsPictureApp) {
                this.goodsPictureApp = goodsPictureApp;
            }

            public String getGoodsTitle() {
                return goodsTitle;
            }

            public void setGoodsTitle(String goodsTitle) {
                this.goodsTitle = goodsTitle;
            }

            public int getGoodsNum() {
                return goodsNum;
            }

            public void setGoodsNum(int goodsNum) {
                this.goodsNum = goodsNum;
            }

            public int getGoodsMoney() {
                return goodsMoney;
            }

            public void setGoodsMoney(int goodsMoney) {
                this.goodsMoney = goodsMoney;
            }
        }

        public static class ShopGoodsEvaluatesBean {
            /**
             * id : 1
             * goodsEvaluate : 不好差评
             * headPhoto : 1
             * memName : 1
             */

            private int id;
            private String goodsEvaluate;
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
