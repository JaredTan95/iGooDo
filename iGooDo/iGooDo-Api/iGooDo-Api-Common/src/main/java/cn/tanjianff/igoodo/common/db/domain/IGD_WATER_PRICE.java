package cn.tanjianff.igoodo.common.db.domain;

import java.sql.Timestamp;

/**
 * Created by tanjian on 2017/7/31.
 */
public class IGD_WATER_PRICE {
    private String WATER_id;
    private String WATER_describe;
    private long WATER_price;
    private Timestamp update_time;

    public IGD_WATER_PRICE(String WATER_id, String WATER_describe, long WATER_price, Timestamp update_time) {
        this.WATER_id = WATER_id;
        this.WATER_describe = WATER_describe;
        this.WATER_price = WATER_price;
        this.update_time = update_time;
    }

    public String getWATER_id() {
        return WATER_id;
    }

    public void setWATER_id(String WATER_id) {
        this.WATER_id = WATER_id;
    }

    public String getWATER_describe() {
        return WATER_describe;
    }

    public void setWATER_describe(String WATER_describe) {
        this.WATER_describe = WATER_describe;
    }

    public long getWATER_price() {
        return WATER_price;
    }

    public void setWATER_price(long WATER_price) {
        this.WATER_price = WATER_price;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }
}
