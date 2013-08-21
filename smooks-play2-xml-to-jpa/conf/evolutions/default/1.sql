# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table customer_order (
  id                        bigint not null,
  header_id                 bigint,
  constraint pk_customer_order primary key (id))
;

create table header (
  id                        bigint not null,
  date                      timestamp,
  customer_number           bigint,
  customer_name             varchar(255),
  constraint pk_header primary key (id))
;

create table order_item (
  id                        bigint not null,
  product_id                bigint,
  quantity                  integer,
  price                     double,
  customer_order_id         bigint,
  constraint pk_order_item primary key (id))
;

create sequence customer_order_seq;

create sequence header_seq;

create sequence order_item_seq;

alter table customer_order add constraint fk_customer_order_header_1 foreign key (header_id) references header (id) on delete restrict on update restrict;
create index ix_customer_order_header_1 on customer_order (header_id);
alter table order_item add constraint fk_order_item_customerOrder_2 foreign key (customer_order_id) references customer_order (id) on delete restrict on update restrict;
create index ix_order_item_customerOrder_2 on order_item (customer_order_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists customer_order;

drop table if exists header;

drop table if exists order_item;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists customer_order_seq;

drop sequence if exists header_seq;

drop sequence if exists order_item_seq;

