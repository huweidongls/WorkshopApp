package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/19.
 */

public class OrderListBean {
    /**
     * status : 200
     * data : [{"id":"20190816170335946","userName":"哈哈","userId":"46","addresId":"14","addresPhone":"13652525252","addresUname":"都疼嗯嗯","addresName":"mottled","addresCode":"125252","sellerId":"1","orderStatus":"0","invoiceId":"0","createTime":"2019-08-16 17:03:35","orderPrice":1015,"orderRealPrice":1015,"isDelete":"0","startTime":"2019-08-17 11:50:11","endTime":"2019-09-16 14:58:30","goodsPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","goodsTitle":"测试商品","remarks":"备注","goodsNum":"1","list":[]},{"id":"20190816145830902","userName":"18686817319","userId":"46","addresId":"14","addresPhone":"13652525252","addresUname":"都疼嗯嗯","addresName":"mottled","addresCode":"125252","goodsId":"16","orderStatus":"0","invoiceId":"0","createTime":"2019-08-16 14:58:30","orderPrice":2890005,"orderRealPrice":2890005,"isDelete":"0","startTime":"2019-08-17 14:58:30","endTime":"2019-09-16 14:58:30","goodsPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","goodsTitle":"测试商品","goodsNum":"1","list":[]},{"id":"20190815161811510","userName":"18686817319","userId":"46","goodsId":"9","orderStatus":"0","createTime":"2019-08-15 16:18:11","orderPrice":5030,"orderRealPrice":5030,"isDelete":"0","startTime":"2019-08-15 00:00:00","endTime":"2019-08-15 00:00:00","goodsPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","goodsTitle":"测试商品","goodsNum":"1","list":[]},{"id":"20190815161707587","userName":"18686817319","userId":"46","goodsId":"9","orderStatus":"0","createTime":"2019-08-15 16:17:07","orderPrice":5030,"orderRealPrice":5030,"isDelete":"0","startTime":"2019-08-15 00:00:00","endTime":"2019-08-15 00:00:00","goodsPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","goodsTitle":"测试商品","goodsNum":"1","list":[]},{"id":"20190815161633455","userName":"18686817319","userId":"46","goodsId":"9","orderStatus":"0","createTime":"2019-08-15 16:16:33","orderPrice":5030,"orderRealPrice":5030,"isDelete":"0","startTime":"2019-08-15 00:00:00","endTime":"2019-08-15 00:00:00","goodsPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","goodsTitle":"测试商品","goodsNum":"1","list":[]},{"id":"20190815161548062","userName":"18686817319","userId":"46","goodsId":"9","orderStatus":"0","createTime":"2019-08-15 16:15:48","orderPrice":5030,"orderRealPrice":5030,"isDelete":"0","startTime":"2019-08-15 00:00:00","endTime":"2019-08-15 00:00:00","goodsPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","goodsTitle":"测试商品","goodsNum":"1","list":[]},{"id":"20190815161214750","userName":"18686817319","userId":"46","goodsId":"9","orderStatus":"0","createTime":"2019-08-15 16:12:14","orderPrice":5030,"orderRealPrice":5030,"isDelete":"0","startTime":"2019-08-15 00:00:00","endTime":"2019-08-15 00:00:00","goodsPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","goodsTitle":"测试商品","goodsNum":"1","list":[]},{"id":"20190815160334930","userName":"18686817319","userId":"46","goodsId":"9","orderStatus":"0","createTime":"2019-08-15 16:03:34","orderPrice":5030,"orderRealPrice":5030,"isDelete":"0","startTime":"2019-08-15 00:00:00","endTime":"2019-08-15 00:00:00","goodsPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","goodsTitle":"测试商品","goodsNum":"1","list":[]},{"id":"20190815160107739","userName":"18686817319","userId":"46","goodsId":"9","orderStatus":"0","createTime":"2019-08-15 16:01:07","orderPrice":5030,"orderRealPrice":5030,"isDelete":"0","startTime":"2019-08-15 00:00:00","endTime":"2019-08-15 00:00:00","goodsPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","goodsTitle":"测试商品","goodsNum":"1","list":[]},{"id":"20190815153726557","userName":"18686817319","userId":"46","goodsId":"9","orderStatus":"0","createTime":"2019-08-15 15:37:26","orderPrice":5030,"orderRealPrice":5030,"isDelete":"0","startTime":"2019-08-15 00:00:00","endTime":"2019-08-15 00:00:00","goodsPictureApp":"upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg","goodsTitle":"测试商品","goodsNum":"1","list":[]}]
     * totalPage : 3
     * totalCount : 24
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
         * id : 20190816170335946
         * userName : 哈哈
         * userId : 46
         * addresId : 14
         * addresPhone : 13652525252
         * addresUname : 都疼嗯嗯
         * addresName : mottled
         * addresCode : 125252
         * sellerId : 1
         * orderStatus : 0
         * invoiceId : 0
         * createTime : 2019-08-16 17:03:35
         * orderPrice : 1015
         * orderRealPrice : 1015
         * isDelete : 0
         * startTime : 2019-08-17 11:50:11
         * endTime : 2019-09-16 14:58:30
         * goodsPictureApp : upload/workshop/2019-07-24/110ce9940c444b73aad0b61445520a00.jpg
         * goodsTitle : 测试商品
         * remarks : 备注
         * goodsNum : 1
         * list : []
         * goodsId : 16
         */

        private String id;
        private String userName;
        private String userId;
        private String addresId;
        private String addresPhone;
        private String addresUname;
        private String addresName;
        private String addresCode;
        private String sellerId;
        private String orderStatus;
        private String invoiceId;
        private String createTime;
        private double orderPrice;
        private double orderRealPrice;
        private String isDelete;
        private String startTime;
        private String endTime;
        private String goodsPictureApp;
        private String goodsTitle;
        private String remarks;
        private String goodsNum;
        private String goodsId;
        private List<?> list;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getAddresId() {
            return addresId;
        }

        public void setAddresId(String addresId) {
            this.addresId = addresId;
        }

        public String getAddresPhone() {
            return addresPhone;
        }

        public void setAddresPhone(String addresPhone) {
            this.addresPhone = addresPhone;
        }

        public String getAddresUname() {
            return addresUname;
        }

        public void setAddresUname(String addresUname) {
            this.addresUname = addresUname;
        }

        public String getAddresName() {
            return addresName;
        }

        public void setAddresName(String addresName) {
            this.addresName = addresName;
        }

        public String getAddresCode() {
            return addresCode;
        }

        public void setAddresCode(String addresCode) {
            this.addresCode = addresCode;
        }

        public String getSellerId() {
            return sellerId;
        }

        public void setSellerId(String sellerId) {
            this.sellerId = sellerId;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getInvoiceId() {
            return invoiceId;
        }

        public void setInvoiceId(String invoiceId) {
            this.invoiceId = invoiceId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public double getOrderPrice() {
            return orderPrice;
        }

        public void setOrderPrice(double orderPrice) {
            this.orderPrice = orderPrice;
        }

        public double getOrderRealPrice() {
            return orderRealPrice;
        }

        public void setOrderRealPrice(double orderRealPrice) {
            this.orderRealPrice = orderRealPrice;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
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

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(String goodsNum) {
            this.goodsNum = goodsNum;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public List<?> getList() {
            return list;
        }

        public void setList(List<?> list) {
            this.list = list;
        }
    }
}
