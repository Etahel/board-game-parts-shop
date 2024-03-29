CREATE SCHEMA IF NOT EXISTS auth;
CREATE SCHEMA IF NOT EXISTS business;

create table if not exists business.publishers
(
    id      bigserial   not null,
    version bigint      not null default 0,
    name    varchar(50) not null,
    constraint publishers_unique_name
        unique (name),
    constraint publishers_pkey
        primary key (id)
);

create table if not exists business.board_games
(
    id           bigserial   not null,
    version      bigint      not null default 0,
    description  varchar(2000),
    photo_url    varchar(2048),
    title        varchar(50) not null,
    author       varchar(60),
    year         integer,
    min_players  integer,
    max_players  integer,
    active       boolean     not null,
    publisher_id bigint,
    constraint board_games_pkey
        primary key (id),
    constraint fk_boardgames_publishers
        foreign key (publisher_id) references business.publishers

);

create table if not exists business.stock
(
    id         bigserial not null,
    version    bigint,
    available  boolean   not null,
    stock_size integer   not null,
    constraint stock_pkey
        primary key (id)
);

create table if not exists business.elements
(
    id            bigserial        not null,
    version       bigint           not null default 0,
    description   varchar(500),
    photo_url     varchar(2048),
    name          varchar(50)      not null,
    price         double precision not null,
    category      varchar(1)       not null,
    active        boolean          not null,
    stock_id      bigint           not null,
    board_game_id bigint           not null,
    constraint elements_pkey
        primary key (id),
    constraint unique_stock_id
        unique (stock_id),
    constraint fk_elements_stock
        foreign key (stock_id) references business.stock,
    constraint fk_elements_boardgames
        foreign key (board_game_id) references business.board_games
);

create table if not exists business.tags
(
    id      bigserial   not null,
    version bigint      not null default 0,
    name    varchar(30) not null,
    constraint tags_unique_name
        unique (name),
    constraint tags_pkey
        primary key (id)
);


create table if not exists business.boardgames_tags
(
    board_game_id bigint not null,
    tag_id        bigint not null,
    constraint fk_bgtags_tags
        foreign key (tag_id) references business.tags,
    constraint fk_bgtags_boardgames
        foreign key (board_game_id) references business.board_games
);


create table if not exists business.addresses
(
    id          bigserial not null,
    version     bigint    not null default 0,
    city        varchar(50),
    flat_no     varchar(10),
    house_no    varchar(10),
    postal_code varchar(6),
    street      varchar(50),
    constraint addresses_pkey
        primary key (id)
);

create table if not exists business.clients
(
    id         bigserial   not null,
    version    bigint,
    first_name varchar(50),
    last_name  varchar(50),
    username   varchar(15) not null,
    address_id bigint      not null,
    constraint clients_pkey
        primary key (id),
    constraint unique_username
        unique (username),
    constraint fk_clients_addresses
        foreign key (address_id) references business.addresses
);

create table if not exists business.orders
(
    id               bigserial        not null,
    version          bigint,
    date             timestamp        not null,
    order_first_name varchar(50)      not null,
    order_last_name  varchar(50)      not null,
    status           varchar(1)       not null,
    value            double precision not null,
    address_id       bigint           not null,
    client_id        bigint           not null,
    constraint orders_pkey
        primary key (id),
    constraint fk_orders_addresses
        foreign key (address_id) references business.addresses,
    constraint fk_orders_clients
        foreign key (client_id) references business.clients
);

create table if not exists business.order_items
(
    id             bigserial not null,
    version        bigint,
    element_id     bigint,
    elements_count integer   not null,
    order_id       bigint,
    constraint order_items_pkey
        primary key (id),
    constraint fk_order_items_elements
        foreign key (element_id) references business.elements,
    constraint fk_order_items_orders
        foreign key (order_id) references business.orders
);

COMMIT;

DO
$$
    DECLARE
        DECLARE
        stockId      bigint;
        DECLARE bgId bigint;
    BEGIN
        INSERT INTO business.stock (available, stock_size, version) VALUES (true, 1000, 0) returning id INTO stockId;
        INSERT INTO business.board_games(title, active) VALUES ('TEST GAME', true) returning id INTO bgId;
        INSERT INTO business.elements (name, description, price, category, stock_id, board_game_id, active, version)
        values ('name', 'desc', 200, 'T', stockId, bgId, true, 0);
    END
$$;

COMMIT;
