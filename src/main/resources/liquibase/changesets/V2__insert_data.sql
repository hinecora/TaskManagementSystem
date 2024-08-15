insert into users (email, password)
values ('johndoe@gmail.com', '$2a$10$E0Yz21hNwz/wIuzv8CCT2ezTsAQo67.TWBJitsPWhgwpzHJvNcWrG'),
       ( 'IvanIvanov@gmail.com', '$2a$10$M0RZy1bq5dgbF0oJV6Lh2e5dS.uXUHum9UFZib4y0sFJIMv9E/N0W');

insert into tasks (title, description, status, priority, executor, owner)
values ('Buy cheese', null, 'TODO','HIGH', 'IvanIvanov@gmail.com', 'johndoe@gmail.com'),
       ('Do homework', 'Math', 'IN_PROGRESS', 'MIDDLE', 'IvanIvanov@gmail.com', 'johndoe@gmail.com'),
       ('Clean rooms', null, 'DONE','LOW', 'IvanIvanov@gmail.com', 'johndoe@gmail.com'),
       ('Call Mike', 'Ask about meeting', 'TODO','HIGH', 'johndoe@gmail.com', 'IvanIvanov@gmail.com');

insert into comments (author, text)
values ('IvanIvanov@gmail.com', 'hey this is 1 comment'),
       ('johndoe@gmail.com', 'hey this is 2 comment'),
       ('johndoe@gmail.com', 'hey this is 3 comment'),
       ('johndoe@gmail.com', 'hey this is 4 comment');

insert into tasks_comments (comment_id, task_id)
values (1, 2),
       (2, 2),
       (3, 2),
       (4, 1);

insert into users_tasks (task_id, user_id)
values (1, 2),
       (2, 2),
       (3, 2),
       (4, 1);

insert into users_roles (user_id, role)
values (1, 'ROLE_OWNER'),
       (1, 'ROLE_EXECUTOR'),
       (2, 'ROLE_EXECUTOR');