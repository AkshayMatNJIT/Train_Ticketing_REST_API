create table ticket_tbl (ticket_id integer not null auto_increment,
destination varchar(255),
fare double precision not null,
origin varchar(255),
wallet_id varchar(255),
primary key (ticket_id));

create table wallet_tbl (id varchar(255) not null,
balance double precision not null,
primary key (id));

alter table ticket_tbl add constraint FKt9rib0tvhk8dl5d85q6m1lpo4 foreign key (wallet_id) references wallet_tbl (id);

select * from wallet_tbl;
select * from ticket_tbl;