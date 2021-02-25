create table if not exists products
(
    id int auto_increment
        primary key,
    created_at datetime null,
    name varchar(255) null,
    price int null,
    status varchar(255) null
)
    engine=MyISAM;

create table orders
(
    id int auto_increment
        primary key,
    created_at datetime null,
    status varchar(255) null,
    user_id int null
)
    engine=MyISAM;

create table order_items
(
    quantity int not null,
    product_id int not null,
    order_id int not null,
    primary key (product_id, order_id)
)
    engine=MyISAM;

create index FKorderIdProductId
    on order_items (order_id);


insert into products values (3, '2021-02-09 11:38:03', 'Socks', 2, 'RUNNING_LOW');
insert into products values (4, '2020-12-11 23:10:46', 'Mouse', 17, 'IN_STOCK');
insert into products values (5, '2021-01-02 08:33:10', 'Keyboard', 20, 'OUT_OF_STOCK');
insert into products values (6, '2021-01-29 14:41:00', 'HyperX Fury 8Gb', 88, 'RUNNING_LOW');
insert into products values (7, '2021-02-06 15:22:23', 'Samsung Galaxy S20', 900, 'IN_STOCK');
insert into products values (8, '2021-01-19 16:43:52', 'iPhone 12', 750, 'RUNNING_LOW');
insert into products values (9, '2020-12-04 12:17:53', 'Headphones', 12, 'IN_STOCK');
insert into products values (10, '2020-12-14 17:08:24', 'AirPods 2', 110, 'IN_STOCK');
insert into products values (11, '2020-10-22 18:11:17', 'Pixel Buds', 80, 'OUT_OF_STOCK');
insert into products values (12, '2020-11-29 19:38:32', 'Homepod', 200, 'IN_STOCK');
insert into products values (13, '2020-11-30 21:26:32', 'Alexa', 176, 'RUNNING_LOW');