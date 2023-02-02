create table users(
    username varchar_ignorecase(50) not null primary key,
    password varchar_ignorecase(500) not null,
    enabled boolean not null
);

create table authorities (
        username varchar_ignorecase(50) not null,
        authority varchar_ignorecase(50) not null,
        foreign key(username) references users(username)
);

INSERT INTO users(username, password, enabled)
VALUES ('user','$2a$12$a/H8E.aLF0K9T28kh.W5ue9A7OLGg/SpSSPOrGYURyWwOU4KYB9Cy',true);


INSERT INTO authorities(username, authority)
VALUES ('user', 'ROLE_USER')
