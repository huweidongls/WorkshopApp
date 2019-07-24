package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/7/24.
 */

public class CategoryQueryChildListBean {

    /**
     * status : 200
     * data : [{"id":9,"categoryName":"属性名称1","pid":1,"appCategoryPic":"upload/workshop/2019-07-23/f941871721cc4298a6345ae93b2e64a6.jpg","productAttributeList":[]},{"id":10,"categoryName":"车间2","pid":1,"appCategoryPic":"upload/workshop/2019-07-24/24ceea213cd142149f2705ef0b97e5c2.jpg","productAttributeList":[]}]
     * totalPage : 1
     * totalCount : 2
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
         * id : 9
         * categoryName : 属性名称1
         * pid : 1
         * appCategoryPic : upload/workshop/2019-07-23/f941871721cc4298a6345ae93b2e64a6.jpg
         * productAttributeList : []
         */

        private int id;
        private String categoryName;
        private int pid;
        private String appCategoryPic;
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

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getAppCategoryPic() {
            return appCategoryPic;
        }

        public void setAppCategoryPic(String appCategoryPic) {
            this.appCategoryPic = appCategoryPic;
        }

        public List<?> getProductAttributeList() {
            return productAttributeList;
        }

        public void setProductAttributeList(List<?> productAttributeList) {
            this.productAttributeList = productAttributeList;
        }
    }
}
