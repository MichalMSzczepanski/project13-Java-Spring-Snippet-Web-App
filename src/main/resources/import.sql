-- insert roles
insert into role values (1, 'ROLE_USER');
insert into role values (2, 'ROLE_ADMIN');

-- insert users
insert into users values (1, null, CURRENT_TIMESTAMP, null, null, 'michal.m.szczepanski@gmail.com', 1, '$2a$10$RkSnA5JvaJnueiA54OAu3.oFvbvtzP464kiM5Fvx5srqmcaARDWuu', 'michal', 1);
-- insert into users values (2, null, CURRENT_TIMESTAMP, null, null, 'michal.m.szczepanski@gmail.com', 1, '$2a$10$RkSnA5JvaJnueiA54OAu3.oFvbvtzP464kiM5Fvx5srqmcaARDWuu', 'michal', 1);
-- password: michal
