create table if not exists users
(
    id       bigserial primary key,
    email     varchar(255) not null unique,
    password varchar(255) not null
    );

create table if not exists tasks
(
    id              bigserial primary key,
    title           varchar(255) not null,
    description     varchar(255) null,
    status          varchar(255) not null,
    priority          varchar(255) not null,
    owner          varchar(255) not null,
    executor          varchar(255) not null
    );

create table if not exists comments
(
    id              bigserial primary key,
    text           varchar(255),
    author           varchar(255)
    );

create table if not exists users_tasks
(
    user_id bigint not null,
    task_id bigint not null,
    primary key (user_id, task_id),
    constraint fk_users_tasks_users foreign key (user_id) references users (id) on delete cascade on update no action,
    constraint fk_users_tasks_tasks foreign key (task_id) references tasks (id) on delete cascade on update no action
    );

create table if not exists tasks_comments
(
    task_id bigint not null,
    comment_id bigint not null,
    primary key (task_id, comment_id),
    constraint fk_tasks_comments_tasks foreign key (task_id) references tasks (id) on delete cascade on update no action,
    constraint fk_tasks_comments_comments foreign key (comment_id) references comments (id) on delete cascade on update no action
    );

create table if not exists users_roles
(
    user_id bigint       not null,
    role    varchar(255) not null,
    primary key (user_id, role),
    constraint fk_users_tasks_users foreign key (user_id) references users (id) on delete cascade on update no action
    );