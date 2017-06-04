package cn.tanjianff.igoodo.db.domain;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class IgdUser implements Serializable{
  private static final long serialVersionUID = 7247714666080613254L;
  private Double user_phone;
  private Double igd_user_phone;
  private String user_pwd;
  private Long user_sex;
  private String user_nickname;
  private java.sql.Date user_regdate;
  private String user_icon;
  private String user_alipay_account;
  private Long user_credit;
  private String user_reserved_field_01;
  private String user_reserved_field_02;
  private String user_reserved_field_03;
  private java.sql.Timestamp user_update_time;

  public IgdUser() {
  }

  public IgdUser(Double user_phone, Double igd_user_phone, String user_pwd, Long user_sex, String user_nickname,
                 Date user_regdate, String user_icon, String user_alipay_account, Long user_credit,
                 String user_reserved_field_01, String user_reserved_field_02, String user_reserved_field_03,
                 Timestamp user_update_time) {
    this.user_phone = user_phone;
    this.igd_user_phone = igd_user_phone;
    this.user_pwd = user_pwd;
    this.user_sex = user_sex;
    this.user_nickname = user_nickname;
    this.user_regdate = user_regdate;
    this.user_icon = user_icon;
    this.user_alipay_account = user_alipay_account;
    this.user_credit = user_credit;
    this.user_reserved_field_01 = user_reserved_field_01;
    this.user_reserved_field_02 = user_reserved_field_02;
    this.user_reserved_field_03 = user_reserved_field_03;
    this.user_update_time = user_update_time;
  }

  public Double getUser_phone() {
    return user_phone;
  }

  public void setUser_phone(Double user_phone) {
    this.user_phone = user_phone;
  }

  public Double getIgd_user_phone() {
    return igd_user_phone;
  }

  public void setIgd_user_phone(Double igd_user_phone) {
    this.igd_user_phone = igd_user_phone;
  }

  public String getUser_pwd() {
    return user_pwd;
  }

  public void setUser_pwd(String user_pwd) {
    this.user_pwd = user_pwd;
  }

  public Long getUser_sex() {
    return user_sex;
  }

  public void setUser_sex(Long user_sex) {
    this.user_sex = user_sex;
  }

  public String getUser_nickname() {
    return user_nickname;
  }

  public void setUser_nickname(String user_nickname) {
    this.user_nickname = user_nickname;
  }

  public java.sql.Date getUser_regdate() {
    return user_regdate;
  }

  public void setUser_regdate(java.sql.Date user_regdate) {
    this.user_regdate = user_regdate;
  }

  public String getUser_icon() {
    return user_icon;
  }

  public void setUser_icon(String user_icon) {
    this.user_icon = user_icon;
  }

  public String getUser_alipay_account() {
    return user_alipay_account;
  }

  public void setUser_alipay_account(String user_alipay_account) {
    this.user_alipay_account = user_alipay_account;
  }

  public Long getUser_credit() {
    return user_credit;
  }

  public void setUser_credit(Long user_credit) {
    this.user_credit = user_credit;
  }

  public String getUser_reserved_field_01() {
    return user_reserved_field_01;
  }

  public void setUser_reserved_field_01(String user_reserved_field_01) {
    this.user_reserved_field_01 = user_reserved_field_01;
  }

  public String getUser_reserved_field_02() {
    return user_reserved_field_02;
  }

  public void setUser_reserved_field_02(String user_reserved_field_02) {
    this.user_reserved_field_02 = user_reserved_field_02;
  }

  public String getUser_reserved_field_03() {
    return user_reserved_field_03;
  }

  public void setUser_reserved_field_03(String user_reserved_field_03) {
    this.user_reserved_field_03 = user_reserved_field_03;
  }

  public java.sql.Timestamp getUser_update_time() {
    return user_update_time;
  }

  public void setUser_update_time(java.sql.Timestamp user_update_time) {
    this.user_update_time = user_update_time;
  }
}
