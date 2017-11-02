CREATE USER phonebook IDENTIFIED BY password;
GRANT ALL PRIVILEGES TO phonebook;

CREATE TABLE phonebook.company(
company_id NUMBER,
company_name VARCHAR2(20),
company_code CHAR(5),
company_description VARCHAR(100),
CONSTRAINT compid_pk PRIMARY KEY(company_id),
CONSTRAINT ccode_un UNIQUE (company_code));

CREATE SEQUENCE compid_sq