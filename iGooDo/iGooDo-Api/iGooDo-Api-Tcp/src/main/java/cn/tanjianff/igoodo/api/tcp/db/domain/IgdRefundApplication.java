package cn.tanjianff.igoodo.api.tcp.db.domain;


public class IgdRefundApplication {
    private double userPhone;
    private String refUuid;
    private java.sql.Date refApplyDate;
    private long refDealed;
    private String refCommit;
    private String refFeedback;
    private java.sql.Timestamp refUpdateTime;


    public double getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(double userPhone) {
        this.userPhone = userPhone;
    }


    public String getRefUuid() {
        return refUuid;
    }

    public void setRefUuid(String refUuid) {
        this.refUuid = refUuid;
    }


    public java.sql.Date getRefApplyDate() {
        return refApplyDate;
    }

    public void setRefApplyDate(java.sql.Date refApplyDate) {
        this.refApplyDate = refApplyDate;
    }


    public long getRefDealed() {
        return refDealed;
    }

    public void setRefDealed(long refDealed) {
        this.refDealed = refDealed;
    }


    public String getRefCommit() {
        return refCommit;
    }

    public void setRefCommit(String refCommit) {
        this.refCommit = refCommit;
    }


    public String getRefFeedback() {
        return refFeedback;
    }

    public void setRefFeedback(String refFeedback) {
        this.refFeedback = refFeedback;
    }


    public java.sql.Timestamp getRefUpdateTime() {
        return refUpdateTime;
    }

    public void setRefUpdateTime(java.sql.Timestamp refUpdateTime) {
        this.refUpdateTime = refUpdateTime;
    }

}
