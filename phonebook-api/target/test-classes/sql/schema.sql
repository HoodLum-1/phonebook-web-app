create table if not exists contacts (
    id bigint NOT NULL AUTO_INCREMENT,
    created_date timestamp,
    email varchar(255),
    name varchar(255),
    number varchar(255),
    type varchar(255),
    primary key (id)
);
