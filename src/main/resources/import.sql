INSERT INTO "user" (name, username, password, roles, enabled) VALUES('Carlos Perez', 'carlos.45', '$2a$12$xVd5cbERJ/P6/.106oskg.FyYhFEuWJovjn/u2kzeLMab/IKLI0lm', 'CUSTOMER', true);
INSERT INTO "user" (name, username, password, roles, enabled) VALUES('Moises Ortega', 'moi054.ort', '$2a$12$cuisC8zAzD5rlbAeenhfiOj2gokUwqkD3vp1oMpThrRWfZHGTT3Sq', 'USER', true);
INSERT INTO "user" (name, username, password, roles, enabled) VALUES('Felix Gondola', 'felix.23god', '$2a$12$sLyslGcjMtwkaSQ1MSnGie1xC9rM9cA8IYsKeh5cluTUA.obFwhX.', 'ADMIN', true);


INSERT INTO product (name, price, amount) VALUES ('Teclado Mecanico', 59.99, 10);
INSERT INTO product (name, price, amount) VALUES ('Mouse Gamer', 29.90, 20);
INSERT INTO product (name, price, amount) VALUES('Monitor 24"', 149.99, 5);
INSERT INTO product (name, price, amount) VALUES('Auriculares Bluetooth', 39.50, 15);
INSERT INTO product (name, price, amount) VALUES('Webcam Full HD', 74.25, 12);
INSERT INTO product (name, price, amount) VALUES('Base Refrigerante Laptop', 29.99, 20);


--INSERT INTO product_user (user_id, product_id) VALUES(1, 1);
--INSERT INTO product_user (user_id, product_id) VALUES(1, 2);
--INSERT INTO product_user (user_id, product_id) VALUES(1, 3);
--INSERT INTO product_user (user_id, product_id) VALUES(1,4);