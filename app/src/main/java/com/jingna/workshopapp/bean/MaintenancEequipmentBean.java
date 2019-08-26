package com.jingna.workshopapp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2019/8/26.
 */

public class MaintenancEequipmentBean implements Serializable {
    /**
     * status : 200
     * data : [{"id":3,"equipmentName":"污水处理设备","equipmentModel":"型号003"},{"id":2,"equipmentName":"污水处理设备","equipmentModel":"型号002"},{"id":1,"equipmentName":"污水处理设备","equipmentModel":"型号001"}]
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

    public static class DataBean implements Serializable {
        /**
         * id : 3
         * equipmentName : 污水处理设备
         * equipmentModel : 型号003
         */

        private int id;
        private String equipmentName;
        private String equipmentModel;
        private int isSelect = 0;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEquipmentName() {
            return equipmentName;
        }

        public int getIsSelect() {
            return isSelect;
        }
        public void setIsSelect(int isSelect) {
            this.isSelect = isSelect;
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
