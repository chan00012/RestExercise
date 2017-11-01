CREATE USER phonebook IDENTIFIED BY password;
GRANT ALL PRIVILEGES TO phonebook;

CREATE TABLE phonebook.company(
company_id NUMBER,
company_name VARCHAR2(20),
company_code CHAR(5),
company_description VARCHAR2(500),
CONSTRAINT compid_pk PRIMARY KEY(company_id)
);

CREATE TABLE phonebook.contact(
contact_id NUMBER,
contact_name VARCHAR2(30),
contact_bday DATE,
contact_vip NUMBER(1),
company_id NUMBER,
CONSTRAINT contid_pk PRIMARY KEY(contact_id),
CONSTRAINT company_id FOREIGN KEY(company_id)
REFERENCES phonebook.company (company_id)
ON DELETE CASCADE);


CREATE SEQUENCE phonebook.compid_sq
INCREMENT BY 1;

CREATE SEQUENCE phonebook.contid_sq
INCREMENT BY 1;
