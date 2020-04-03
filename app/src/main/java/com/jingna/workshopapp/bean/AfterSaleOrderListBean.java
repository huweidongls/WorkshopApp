package com.jingna.workshopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/27.
 */

public class AfterSaleOrderListBean {

    /**
     * status : 200
     * data : [{"id":"20190826104446656","deviceId":"1,2","addresCoordinate":"126.63238520689535,45.7427950567895","addresPhone":"18686817319","addresName":"汉广街41号","orderRealPrice":0,"orderStatus":"2","deviceName":"污水处理设备型号001,污水处理设备型号002,","appAfterSaleEquipments":[{"equipmentName":"污水处理设备","equipmentModel":"型号001"},{"equipmentName":"污水处理设备","equipmentModel":"型号002"}]},{"id":"20190826105907227","deviceId":"1,2","addresCoordinate":"126.63238520689535,45.7427950567895","addresPhone":"18686817319","addresName":"汉广街41号","orderRealPrice":0,"orderStatus":"2","deviceName":"污水处理设备型号001,污水处理设备型号002,","appAfterSaleEquipments":[{"equipmentName":"污水处理设备","equipmentModel":"型号001"},{"equipmentName":"污水处理设备","equipmentModel":"型号002"}]},{"id":"20190826154805486","deviceId":"2,1","addresCoordinate":"null,null","addresPhone":"13652525252","addresName":"北京市-北京市-东城区mottled","orderRealPrice":0,"orderStatus":"2","deviceName":"污水处理设备型号002,污水处理设备型号001,","appAfterSaleEquipments":[{"equipmentName":"污水处理设备","equipmentModel":"型号002"},{"equipmentName":"污水处理设备","equipmentModel":"型号001"}]},{"id":"20190826163453334","deviceId":"2,1","addresCoordinate":"116.4224009776628,39.93482727239599","addresPhone":"13652525252","addresName":"北京市-北京市-东城区mottled","orderRealPrice":0,"orderStatus":"1","deviceName":"污水处理设备型号002,污水处理设备型号001,","appAfterSaleEquipments":[{"equipmentName":"污水处理设备","equipmentModel":"型号002"},{"equipmentName":"污水处理设备","equipmentModel":"型号001"}]}]
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
         * id : 20190826104446656
         * deviceId : 1,2
         * addresCoordinate : 126.63238520689535,45.7427950567895
         * addresPhone : 18686817319
         * addresName : 汉广街41号
         * orderRealPrice : 0
         * orderStatus : 2
         * deviceName : 污水处理设备型号001,污水处理设备型号002,
         * appAfterSaleEquipments : [{"equipmentName":"污水处理设备","equipmentModel":"型号001"},{"equipmentName":"污水处理设备","equipmentModel":"型号002"}]
         */

        private String id;
        private String deviceId;
        private String addresCoordinate;
        private String addresPhone;
        private String addresName;
        private double orderRealPrice;
        private String orderStatus;
        private String deviceName;
        private List<AppAfterSaleEquipmentsBean> appAfterSaleEquipments;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getAddresCoordinate() {
            return addresCoordinate;
        }

        public void setAddresCoordinate(String addresCoordinate) {
            this.addresCoordinate = addresCoordinate;
        }

        public String getAddresPhone() {
            return addresPhone;
        }

        public void setAddresPhone(String addresPhone) {
            this.addresPhone = addresPhone;
        }

        public String getAddresName() {
            return addresName;
        }

        public void setAddresName(String addresName) {
            this.addresName = addresName;
        }

        public double getOrderRealPrice() {
            return orderRealPrice;
        }

        public void setOrderRealPrice(double orderRealPrice) {
            this.orderRealPrice = orderRealPrice;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public List<AppAfterSaleEquipmentsBean> getAppAfterSaleEquipments() {
            return appAfterSaleEquipments;
        }

        public void setAppAfterSaleEquipments(List<AppAfterSaleEquipmentsBean> appAfterSaleEquipments) {
            this.appAfterSaleEquipments = appAfterSaleEquipments;
        }

        public static class AppAfterSaleEquipmentsBean {
            /**
             * equipmentName : 污水处理设备
             * equipmentModel : 型号001
             */

            private String equipmentName;
            private String equipmentModel;

            public String getEquipmentName() {
                return equipmentName;
            }

            public void setEquipmentName(String equipmentName) {
                this.equipmentName = equipmentName;
            }

            public String getEquipmentModel() {
                return equipmentModel;
            }

            public void setEquipmentModel(String equipmentModel) {
                this.equipmentModel = equipmentModel;
            }
        }
    }
}
