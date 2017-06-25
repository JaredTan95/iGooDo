package cn.tanjianff.igoodo.common.db.domain;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class IgdUser implements Serializable{
  private static final long serialVersionUID = 7247714666080613254L;
  private String user_phone;
  private String user_pwd;
  private int user_sex;
  private String user_nickname;
  private Date user_regdate;
  private String user_icon;
  private String user_alipay_account;
  private Long user_credit;
  private String user_reserved_field_01;
  private String user_reserved_field_02;
  private String user_reserved_field_03;
  private Timestamp update_time;

  public IgdUser() {
  }

  public IgdUser(String user_phone, String user_pwd, int user_sex, String user_nickname, Date user_regdate,
                 String user_icon, String user_alipay_account, Long user_credit, String user_reserved_field_01,
                 String user_reserved_field_02, String user_reserved_field_03, Timestamp update_time) {
    this.user_phone = user_phone;
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
    this.update_time = update_time;
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public String getUser_phone() {
    return user_phone;
  }

  public void setUser_phone(String user_phone) {
    this.user_phone = user_phone;
  }

  public String getUser_pwd() {
    return user_pwd;
  }

  public void setUser_pwd(String user_pwd) {
    this.user_pwd = user_pwd;
  }

  public int getUser_sex() {
    return user_sex;
  }

  public void setUser_sex(int user_sex) {
    this.user_sex = user_sex;
  }

  public String getUser_nickname() {
    return user_nickname;
  }

  public void setUser_nickname(String user_nickname) {
    this.user_nickname = user_nickname;
  }

  public Date getUser_regdate() {
    return user_regdate;
  }

  public void setUser_regdate(Date user_regdate) {
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

  public Timestamp getUpdate_time() {
    return update_time;
  }

  public void setUpdate_time(Timestamp update_time) {
    this.update_time = update_time;
  }

  @Override
  public String toString() {
    return "{" +
            "user_phone:'" + user_phone + '\'' +
            ", user_pwd:'" + user_pwd + '\'' +
            ", user_sex:" + user_sex +
            ", user_nickname:'" + user_nickname + '\'' +
            ", user_regdate:" + user_regdate +
            ", user_icon:'" + user_icon + '\'' +
            ", user_alipay_account:'" + user_alipay_account + '\'' +
            ", user_credit:" + user_credit +
            ", user_reserved_field_01:'" + user_reserved_field_01 + '\'' +
            ", user_reserved_field_02:'" + user_reserved_field_02 + '\'' +
            ", user_reserved_field_03:'" + user_reserved_field_03 + '\'' +
            ", update_time:" + update_time +
            '}';
  }
}
