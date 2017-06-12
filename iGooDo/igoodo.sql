/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/6/11 19:08:19                           */
/*==============================================================*/


drop table if exists IGD_REFUND_APPLICATION;

drop table if exists IGD_USER;

drop table if exists IGD_USER_INFORMATION;

drop table if exists IGD_WATER_DISPENSER;

drop table if exists IGD_WATER_PRICE;

/*==============================================================*/
/* Table: IGD_REFUND_APPLICATION                                */
/*==============================================================*/
create table IGD_REFUND_APPLICATION
(
   USER_phone           numeric(11,0) not null,
   REF_UUID             char(65) not null,
   REF_apply_date       date not null,
   REF_dealed           smallint not null default 0,
   REF_commit           text,
   REF_feedback         varchar(65),
   update_time      timestamp,
   primary key (USER_phone, REF_UUID)
);

/*==============================================================*/
/* Table: IGD_USER                                              */
/*==============================================================*/
create table IGD_USER
(
   USER_phone           numeric(11,0) not null,
   USER_pwd             varchar(65) not null,
   USER_sex             smallint not null default 1,
   USER_nickname        char(24) not null,
   USER_regdate         date not null,
   USER_icon            varchar(300),
   USER_alipay_account  varchar(65),
   USER_credit          int not null,
   USER_reserved_field_01 varchar(1),
   USER_reserved_field_02 varchar(1),
   USER_reserved_field_03 varchar(1),
   update_time     timestamp not null,
   primary key (USER_phone)
);

/*==============================================================*/
/* Table: IGD_USER_INFORMATION                                  */
/*==============================================================*/
create table IGD_USER_INFORMATION
(
   USER_phone           numeric(11,0) not null,
   INF_birthday         date,
   INF_weight_kg        smallint,
   INF_height_cm        smallint,
   INF_reserved_field_01 varchar(1),
   INF_reserved_field_02 varchar(1),
   INF_reserved_field_03 varchar(1),
   INF_reserved_field_04 varchar(1),
   INF_reserved_field_05 varchar(1),
   INF_reserved_field_06 varchar(1),
   INF_reserved_field_07 varchar(1),
   INF_reserved_field_08 varchar(1),
   update_time      timestamp not null,
   primary key (USER_phone)
);

/*==============================================================*/
/* Table: IGD_WATER_DISPENSER                                   */
/*==============================================================*/
create table IGD_WATER_DISPENSER
(
   WD_UUID              char(65) not null,
   WD_Serialnum         varchar(11) not null,
   WD_lat               char(12) not null,
   WD_lon               char(12) not null,
   WD_placed_date       date not null,
   WD_reserved_field_01 varchar(1),
   WD_reserved_field_02 varchar(1),
   WD_reserved_field_03 varchar(1),
   WD_reserved_field_04 varchar(1),
   WD_update_time       timestamp not null,
   WD_IP                varchar(20),
   primary key (WD_UUID)
);

/*==============================================================*/
/* Table: IGD_WATER_PRICE                                       */
/*==============================================================*/
create table IGD_WATER_PRICE
(
   WATER_id             char(65) not null,
   WATER_describe       text,
   WATER_price          smallint not null default 0,
   update_time    timestamp,
   primary key (WATER_id)
);

alter table IGD_REFUND_APPLICATION add constraint FK_REL_USER_REFUND foreign key (USER_phone)
      references IGD_USER (USER_phone) on delete restrict on update restrict;

alter table IGD_USER_INFORMATION add constraint FK_REL_USER_INF foreign key (USER_phone)
      references IGD_USER (USER_phone) on delete restrict on update restrict;

