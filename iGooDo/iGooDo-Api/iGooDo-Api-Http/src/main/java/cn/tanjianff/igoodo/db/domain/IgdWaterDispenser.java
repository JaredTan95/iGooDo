package cn.tanjianff.igoodo.db.domain;


import java.sql.Date;
import java.sql.Timestamp;

public class IgdWaterDispenser {

    private String wdUuid;
    private double wdSerialnum;
    private String wdIp;
    private String wdLat;
    private String wdLon;
    private Date wdPlacedDate;
    private String wdReservedField01;
    private String wdReservedField02;
    private String wdReservedField03;
    private String wdReservedField04;
    private Timestamp updateTime;

    public IgdWaterDispenser() {
    }

    public IgdWaterDispenser(String wdUuid, double wdSerialnum, String wdIp, String wdLat, String wdLon,
                             Date wdPlacedDate, String wdReservedField01,
                             String wdReservedField02, String wdReservedField03,
                             String wdReservedField04, Timestamp wdUpdateTime) {
        this.wdUuid = wdUuid;
        this.wdSerialnum = wdSerialnum;
        this.wdIp = wdIp;
        this.wdLat = wdLat;
        this.wdLon = wdLon;
        this.wdPlacedDate = wdPlacedDate;
        this.wdReservedField01 = wdReservedField01;
        this.wdReservedField02 = wdReservedField02;
        this.wdReservedField03 = wdReservedField03;
        this.wdReservedField04 = wdReservedField04;
        this.updateTime = wdUpdateTime;
    }

    public String getWdIp() {
        return wdIp;
    }

    public void setWdIp(String wdIp) {
        this.wdIp = wdIp;
    }

    public String getWdUuid() {
        return wdUuid;
    }

    public void setWdUuid(String wdUuid) {
        this.wdUuid = wdUuid;
    }


    public double getWdSerialnum() {
        return wdSerialnum;
    }

    public void setWdSerialnum(double wdSerialnum) {
        this.wdSerialnum = wdSerialnum;
    }


    public String getWdLat() {
        return wdLat;
    }

    public void setWdLat(String wdLat) {
        this.wdLat = wdLat;
    }


    public String getWdLon() {
        return wdLon;
    }

    public void setWdLon(String wdLon) {
        this.wdLon = wdLon;
    }


    public Date getWdPlacedDate() {
        return wdPlacedDate;
    }

    public void setWdPlacedDate(Date wdPlacedDate) {
        this.wdPlacedDate = wdPlacedDate;
    }


    public String getWdReservedField01() {
        return wdReservedField01;
    }

    public void setWdReservedField01(String wdReservedField01) {
        this.wdReservedField01 = wdReservedField01;
    }


    public String getWdReservedField02() {
        return wdReservedField02;
    }

    public void setWdReservedField02(String wdReservedField02) {
        this.wdReservedField02 = wdReservedField02;
    }


    public String getWdReservedField03() {
        return wdReservedField03;
    }

    public void setWdReservedField03(String wdReservedField03) {
        this.wdReservedField03 = wdReservedField03;
    }


    public String getWdReservedField04() {
        return wdReservedField04;
    }

    public void setWdReservedField04(String wdReservedField04) {
        this.wdReservedField04 = wdReservedField04;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
