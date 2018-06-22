--AUTHENTICATION
INSERT into user(id, username, password, name, email, active) VALUES
(1001, 'dti', '$2a$10$Qji2/icFWIGGQEAv8bbwNuKGrSZyiUUPqE/0SNO2M.BpU.NA6xPhW', 'Departamento da Tecnolocia da Informação','dti@alvorada.ifrs.edu.br', TRUE),
(1002, 'cassiano.doneda', '$2a$10$Qji2/icFWIGGQEAv8bbwNuKGrSZyiUUPqE/0SNO2M.BpU.NA6xPhW', 'Cassiano Doneda','cassiano.doneda@alvorada.ifrs.edu.br', TRUE);

--ROLES
insert into role(id, role) values
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

-- USER_ROLES
insert into user_roles (user_id, roles_id) values
(1001, 1),
(1002, 1);

insert into item (id, description, name, status, type) values
(100, 'Notebook Acer', 'Notebook DAP 04', 0, 3),
(101, 'Projetor Epson', 'Projetor Epson DAP 01', 0, 1),
(102, 'Projetor Epson 2 ', 'Projetor Epson DAP 02', 0, 1),
(103, 'Projetor Epson 3', 'Projetor Epson DAP 03', 0, 1),
(104, 'Projetor Epson 3', 'Projetor Epson DAP 03', 1, 1);

insert into loan (id, date_time_loan, date_time_return, item_id, user_id, status_loan) values
(498, '2018-05-01 12:30:00', '2018-05-03 12:34:00', 102, 1001, 0),
(499, '2018-05-02 12:30:00', '2018-05-04 12:34:00', 101, 1001, 0);


insert into loan (id, date_time_loan, item_id, user_id, status_loan) values
(500, '2018-05-07 12:30:00', 100, 1001, 1),
(501, '2018-05-08 12:30:00', 101, 1001, 1);



