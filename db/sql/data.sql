insert into customers (email, pwd) VALUES
                                    ('accounts@correo.com', 'to_be_encoded'),
                                    ('cards@correo.com', 'to_be_encoded'),
                                    ('loans@correo.com', 'to_be_encoded'),
                                    ('balance@correo.com', 'to_be_encoded');

insert into roles (role_name, description, id_customer) VALUES
    ('VIEW_ACCOUNT', 'Cant view account endpoint', 1),
    ('VIEW_CARDS', 'Cant view cards endpoint', 2),
    ('VIEW_LOANS', 'Cant view loans endpoints', 3),
    ('VIEW_BALANCE', 'Cant view balance endpoints', 4);



