insert into customers (email, pwd) VALUES
                                    ('accounts@correo.com', 'to_be_encoded'),
                                    ('cards@correo.com', 'to_be_encoded'),
                                    ('loans@correo.com', 'to_be_encoded'),
                                    ('balance@correo.com', 'to_be_encoded');

insert into roles (role_name, description, id_customer) VALUES
    ('ROLE_ADMIN', 'Cant view account endpoint', 1),
    ('ROLE_ADMIN', 'Cant view cards endpoint', 2),
    ('ROLE_USER', 'Cant view loans endpoints', 3),
    ('ROLE_USER', 'Cant view balance endpoints', 4);



