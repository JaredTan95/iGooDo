package cn.tanjianff.igoodo.common.db.domain;


import java.sql.Timestamp;

public class IgdWaterPrice {

    private String waterId;
    private String waterDescribe;
    private long waterPrice;
    private java.sql.Timestamp waterUpdateTime;

    public IgdWaterPrice() {
    }

    public IgdWaterPrice(String waterId, String waterDescribe, long waterPrice, Timestamp waterUpdateTime) {
        this.waterId = waterId;
        this.waterDescribe = waterDescribe;
        this.waterPrice = waterPrice;
        this.waterUpdateTime = waterUpdateTime;
    }

    public String getWaterId() {
        return waterId;
    }

    public void setWaterId(String waterId) {
        this.waterId = waterId;
    }


    public String getWaterDescribe() {
        return waterDescribe;
    }

    public void setWaterDescribe(String waterDescribe) {
        this.waterDescribe = waterDescribe;
    }


    public long getWaterPrice() {
        return waterPrice;
    }

    public void setWaterPrice(long waterPrice) {
        this.waterPrice = waterPrice;
    }


    public java.sql.Timestamp getWaterUpdateTime() {
        return waterUpdateTime;
    }

    public void setWaterUpdateTime(java.sql.Timestamp waterUpdateTime) {
        this.waterUpdateTime = waterUpdateTime;
    }

}
