CREATE TABLE clients
(
   id          INT            NOT NULL AUTO_INCREMENT,
   first_name  VARCHAR(255),
   PRIMARY KEY (id)
);

CREATE TABLE phones
(
   id          INT            NOT NULL AUTO_INCREMENT,
   client_id   INT,
   phoneNumber VARCHAR(255),
   PRIMARY KEY (id)
);

INSERT INTO clients(first_Name) VALUES ('Client1');
INSERT INTO phones(client_id, phone_number) VALUES (1,'34534534534534');
INSERT INTO phones(client_id, phone_number) VALUES (1,'34534534534534');