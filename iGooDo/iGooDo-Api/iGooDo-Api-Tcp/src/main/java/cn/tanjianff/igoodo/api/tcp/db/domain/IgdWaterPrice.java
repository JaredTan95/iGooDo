package cn.tanjianff.igoodo.api.tcp.db.domain;


public class IgdWaterPrice {

    private String waterId;
    private String waterDescribe;
    private long waterPrice;
    private java.sql.Timestamp waterUpdateTime;


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
