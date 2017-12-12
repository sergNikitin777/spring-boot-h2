CREATE TABLE user_details
(
   id          INT            NOT NULL AUTO_INCREMENT,
   email       VARCHAR(255),
   first_name  VARCHAR(255),
   last_name   VARCHAR(255),
   password    VARCHAR(255),
   PRIMARY KEY (id)
);

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

INSERT INTO user_details(email,first_Name,last_Name,password) VALUES ('admin@admin.com','admin','admin','admin');
INSERT INTO user_details(email,first_Name,last_Name,password) VALUES ('john@gmail.com','john','doe','johndoe');
INSERT INTO user_details(email,first_Name,last_Name,password) VALUES ('sham@yahoo.com','sham','tis','shamtis');

INSERT INTO clients(first_Name) VALUES ('Client1');
INSERT INTO phones(client_id, phone_number) VALUES (1,'34534534534534');
INSERT INTO phones(client_id, phone_number) VALUES (1,'34534534534534');