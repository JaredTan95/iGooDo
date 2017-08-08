package cn.tanjianff.igoodo.common.db.domain;

import java.sql.Timestamp;

public class Igd_personnal_cost_records {
  private String _id;
  private Double user_phone;
  private Long waterintake_ml;
  private Double cost;
  private java.sql.Timestamp update_time;

  public Igd_personnal_cost_records(String _id, Double user_phone,
                                    Long waterintake_ml, Double cost,
                                    Timestamp update_time) {
    this._id = _id;
    this.user_phone = user_phone;
    this.waterintake_ml = waterintake_ml;
    this.cost = cost;
    this.update_time = update_time;
  }

  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public Double getUser_phone() {
    return user_phone;
  }

  public void setUser_phone(Double user_phone) {
    this.user_phone = user_phone;
  }

  public Long getWaterintake_ml() {
    return waterintake_ml;
  }

  public void setWaterintake_ml(Long waterintake_ml) {
    this.waterintake_ml = waterintake_ml;
  }

  public Double getCost() {
    return cost;
  }

  public void setCost(Double cost) {
    this.cost = cost;
  }

  public java.sql.Timestamp getUpdate_time() {
    return update_time;
  }

  public void setUpdate_time(java.sql.Timestamp update_time) {
    this.update_time = update_time;
  }
}
