create table articlescrap
(
   id                   bigint(19) not null auto_increment,
   title                varchar(255) not null,
   content              longtext not null,
   show_time            datetime not null,
   type                 tinyint(3) not null comment '1.大国正史
            2.大国野史',
   author               varchar(50) not null,
   status               tinyint(3) not null comment '1.上架
            0.下架',
   create_time          datetime not null,
   update_time          datetime not null,
   primary key (id)
);

/*==============================================================*/
/* Table: carousel                                              */
/*==============================================================*/
create table carousel
(
   id                   int(5) not null auto_increment,
   image_url            varchar(255) not null,
   sort                 int(3) not null,
   status               tinyint(3) not null comment '0:下架
            1:上架',
   link_url             varchar(255),
   image_desc           varchar(255),
   primary key (id)
);

/*==============================================================*/
/* Table: recommed_articlescrap                                 */
/*==============================================================*/
create table recommed_articlescrap
(
   id                   int(5) not null auto_increment,
   articlescrap_id      bigint(19) not null,
   sort                 int(5) not null,
   status               tinyint(3) not null,
   primary key (id)
);