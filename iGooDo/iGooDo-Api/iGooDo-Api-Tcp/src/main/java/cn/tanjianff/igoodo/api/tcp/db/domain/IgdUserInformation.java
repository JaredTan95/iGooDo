package cn.tanjianff.igoodo.api.tcp.db.domain;


import java.sql.Date;
import java.sql.Timestamp;

public class IgdUserInformation {

    private double userPhone;
    private Date infBirthday;
    private long infWeightKg;
    private long infHeightCm;
    private String infReservedField01;
    private String infReservedField02;
    private String infReservedField03;
    private String infReservedField04;
    private String infReservedField05;
    private String infReservedField06;
    private String infReservedField07;
    private String infReservedField08;
    private Timestamp updateTime;

    public IgdUserInformation() {
    }

    public IgdUserInformation(double userPhone, Date infBirthday, long infWeightKg, long infHeightCm,
                              String infReservedField01, String infReservedField02, String infReservedField03,
                              String infReservedField04, String infReservedField05, String infReservedField06,
                              String infReservedField07, String infReservedField08, Timestamp infUpdateTime) {
        this.userPhone = userPhone;
        this.infBirthday = infBirthday;
        this.infWeightKg = infWeightKg;
        this.infHeightCm = infHeightCm;
        this.infReservedField01 = infReservedField01;
        this.infReservedField02 = infReservedField02;
        this.infReservedField03 = infReservedField03;
        this.infReservedField04 = infReservedField04;
        this.infReservedField05 = infReservedField05;
        this.infReservedField06 = infReservedField06;
        this.infReservedField07 = infReservedField07;
        this.infReservedField08 = infReservedField08;
        this.updateTime = infUpdateTime;
    }

    public double getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(double userPhone) {
        this.userPhone = userPhone;
    }


    public Date getInfBirthday() {
        return infBirthday;
    }

    public void setInfBirthday(Date infBirthday) {
        this.infBirthday = infBirthday;
    }


    public long getInfWeightKg() {
        return infWeightKg;
    }

    public void setInfWeightKg(long infWeightKg) {
        this.infWeightKg = infWeightKg;
    }


    public long getInfHeightCm() {
        return infHeightCm;
    }

    public void setInfHeightCm(long infHeightCm) {
        this.infHeightCm = infHeightCm;
    }


    public String getInfReservedField01() {
        return infReservedField01;
    }

    public void setInfReservedField01(String infReservedField01) {
        this.infReservedField01 = infReservedField01;
    }


    public String getInfReservedField02() {
        return infReservedField02;
    }

    public void setInfReservedField02(String infReservedField02) {
        this.infReservedField02 = infReservedField02;
    }


    public String getInfReservedField03() {
        return infReservedField03;
    }

    public void setInfReservedField03(String infReservedField03) {
        this.infReservedField03 = infReservedField03;
    }


    public String getInfReservedField04() {
        return infReservedField04;
    }

    public void setInfReservedField04(String infReservedField04) {
        this.infReservedField04 = infReservedField04;
    }


    public String getInfReservedField05() {
        return infReservedField05;
    }

    public void setInfReservedField05(String infReservedField05) {
        this.infReservedField05 = infReservedField05;
    }


    public String getInfReservedField06() {
        return infReservedField06;
    }

    public void setInfReservedField06(String infReservedField06) {
        this.infReservedField06 = infReservedField06;
    }


    public String getInfReservedField07() {
        return infReservedField07;
    }

    public void setInfReservedField07(String infReservedField07) {
        this.infReservedField07 = infReservedField07;
    }


    public String getInfReservedField08() {
        return infReservedField08;
    }

    public void setInfReservedField08(String infReservedField08) {
        this.infReservedField08 = infReservedField08;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "IgdUserInformation{" +
                "userPhone=" + userPhone +
                ", infBirthday=" + infBirthday +
                ", infWeightKg=" + infWeightKg +
                ", infHeightCm=" + infHeightCm +
                ", infReservedField01='" + infReservedField01 + '\'' +
                ", infReservedField02='" + infReservedField02 + '\'' +
                ", infReservedField03='" + infReservedField03 + '\'' +
                ", infReservedField04='" + infReservedField04 + '\'' +
                ", infReservedField05='" + infReservedField05 + '\'' +
                ", infReservedField06='" + infReservedField06 + '\'' +
                ", infReservedField07='" + infReservedField07 + '\'' +
                ", infReservedField08='" + infReservedField08 + '\'' +
                ", infUpdateTime=" + updateTime +
                '}';
    }
}
