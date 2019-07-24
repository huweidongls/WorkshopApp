package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/7/24.
 */

public class EntrustListBean {

    /**
     * status : 200
     * data : [{"id":16,"categoryName":"设备1","appCategoryPic":"upload/workshop/2019-07-24/80b414f453b34e529939758cd889d3ad.jpg","processingMoney":207,"productAttributeList":[]},{"id":20,"categoryName":"设备5","appCategoryPic":"upload/workshop/2019-07-24/80b414f453b34e529939758cd889d3ad.jpg","processingMoney":207,"productAttributeList":[]}]
     */

    private String status;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 16
         * categoryName : 设备1
         * appCategoryPic : upload/workshop/2019-07-24/80b414f453b34e529939758cd889d3ad.jpg
         * processingMoney : 207
         * productAttributeList : []
         */

        private int id;
        private String categoryName;
        private String appCategoryPic;
        private int processingMoney;
        private List<?> productAttributeList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getAppCategoryPic() {
            return appCategoryPic;
        }

        public void setAppCategoryPic(String appCategoryPic) {
            this.appCategoryPic = appCategoryPic;
        }

        public int getProcessingMoney() {
            return processingMoney;
        }

        public void setProcessingMoney(int processingMoney) {
            this.processingMoney = processingMoney;
        }

        public List<?> getProductAttributeList() {
            return productAttributeList;
        }

        public void setProductAttributeList(List<?> productAttributeList) {
            this.productAttributeList = productAttributeList;
        }
    }
}
