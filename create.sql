create table tb_product (price float(53), created_at TIMESTAMP WITHOUT TIME ZONE, id bigserial not null, updated_at TIMESTAMP WITHOUT TIME ZONE, category varchar(255), img varchar(255), name varchar(255), primary key (id));
INSERT INTO tb_user (name, email, password) VALUES ('John', 'John@mail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Maria', 'admin@mail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_product(name, category, price, img, created_At) VALUES ('Hamburguer', 'Sanduíches', 14, 'https://i.imgur.com/Vng6VzV.png', NOW());
INSERT INTO tb_product(name, category, price, img, created_At) VALUES ('X-Burguer', 'Sanduíches', 16, 'https://i.imgur.com/soOUeeW.png', NOW());
INSERT INTO tb_product(name, category, price, img, created_At) VALUES ('Big Kenzie', 'Sanduíches', 18, 'https://i.imgur.com/eEzZzcF.png', NOW());
INSERT INTO tb_product(name, category, price, img, created_At) VALUES ('Fanta Guaraná', 'Bebidas', 5, 'https://i.imgur.com/YuIbfCi.png', NOW());
INSERT INTO tb_product(name, category, price, img, created_At) VALUES ('Coca-Cola', 'Bebidas', 4.99, 'https://i.imgur.com/KC2ihEN.png', NOW());
INSERT INTO tb_product(name, category, price, img, created_At) VALUES ('Milkshake Ovomaltine', 'Bebidas', 4.99, 'https://i.imgur.com/iNkD4Pq.png', NOW());
