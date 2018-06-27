--AUTHENTICATION
INSERT into user(id, username, password, name, email, active) VALUES
(1001, 'dti', '$2a$10$Qji2/icFWIGGQEAv8bbwNuKGrSZyiUUPqE/0SNO2M.BpU.NA6xPhW', 'Departamento da Tecnolocia da Informação','dti@alvorada.ifrs.edu.br', TRUE),
(1002, 'cassiano.doneda', '$2a$10$Qji2/icFWIGGQEAv8bbwNuKGrSZyiUUPqE/0SNO2M.BpU.NA6xPhW', 'Cassiano Doneda','cassiano.doneda@alvorada.ifrs.edu.br', TRUE),
(1003, 'nilo.alvira', '$2a$10$Qji2/icFWIGGQEAv8bbwNuKGrSZyiUUPqE/0SNO2M.BpU.NA6xPhW', 'Nilo Alvira','nilo.alvira@alvorada.ifrs.edu.br', TRUE),
(1004, 'vinicius.seus', '$2a$10$Qji2/icFWIGGQEAv8bbwNuKGrSZyiUUPqE/0SNO2M.BpU.NA6xPhW', 'Vinicius Seus','vinicius.seus@alvorada.ifrs.edu.br', TRUE);

--ROLES
insert into role(id, role) values
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

-- USER_ROLES
insert into user_roles (user_id, roles_id) values
(1001, 1),
(1001, 2),
(1002, 1),
(1003, 1),
(1004, 1);

insert into item (id, description, name, status, type) values
(100, 'Notebook Acer', 'Notebook DAP 04', 0, 3),
(101, 'Projetor Epson', 'Projetor Epson DAP 01', 0, 1),
(102, 'Projetor Epson 2 ', 'Projetor Epson DAP 02', 0, 1),
(103, 'Projetor Epson 3', 'Projetor Epson DAP 03', 0, 1),
(104, 'Notebook ACER', 'Notebook DAP 1', 0, 3),
(105, 'Notebook ACER', 'Notebook DAP 2', 0, 3),
(106, 'Notebook ACER', 'Notebook DAP 3', 0, 3),
(107, 'Notebook ACER', 'Notebook DAP 4', 0, 3);

insert into loan (id, date_time_loan, date_time_return, item_id, user_id, status_loan, user_return_id) values
(494, '2018-01-01 12:30:00', '2018-01-02 10:34:00', 105, 1002, 0, 1002),
(495, '2018-06-24 11:30:00', '2018-06-24 12:34:00', 107, 1003, 0, 1003),
(496, '2018-06-05 08:30:00', '2018-06-06 12:34:00', 102, 1002, 0, 1002),
(497, '2018-06-24 08:30:00', '2018-06-24 14:23:00', 106, 1002, 0, 1002),
(498, '2018-05-01 12:30:00', '2018-05-03 12:34:00', 102, 1002, 0, 1002),
(499, '2018-05-02 12:30:00', '2018-05-04 12:34:00', 101, 1002, 0, 1002);


insert into loan (id, date_time_loan, item_id, user_id, status_loan) values
(500, '2018-05-07 12:30:00', 100, 1002, 1),
(501, '2018-05-08 12:30:00', 101, 1003, 1);




