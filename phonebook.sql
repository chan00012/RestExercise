--------------------------------------------------------
--  File created - Monday-November-06-2017   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table COMPANY
--------------------------------------------------------

  CREATE TABLE "PHONEBOOK"."COMPANY" 
   (	"COMPANY_ID" NUMBER, 
	"COMPANY_NAME" VARCHAR2(20 BYTE), 
	"COMPANY_CODE" CHAR(5 BYTE), 
	"COMPANY_DESCRIPTION" VARCHAR2(500 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table CONTACT
--------------------------------------------------------

  CREATE TABLE "PHONEBOOK"."CONTACT" 
   (	"CONTACT_ID" NUMBER, 
	"CONTACT_NAME" VARCHAR2(30 BYTE), 
	"CONTACT_BDAY" DATE, 
	"CONTACT_VIP" NUMBER(1,0) DEFAULT 0, 
	"COMPANY_ID" NUMBER DEFAULT 0, 
	"CONTACT_NO" VARCHAR2(11 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Sequence COMPID_SQ
--------------------------------------------------------

   CREATE SEQUENCE  "PHONEBOOK"."COMPID_SQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence CONTID_SQ
--------------------------------------------------------

   CREATE SEQUENCE  "PHONEBOOK"."CONTID_SQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
REM INSERTING into PHONEBOOK.COMPANY
SET DEFINE OFF;
Insert into PHONEBOOK.COMPANY (COMPANY_ID,COMPANY_NAME,COMPANY_CODE,COMPANY_DESCRIPTION) values (1,'Betica','BETCA','Software quality assurance provider.');
Insert into PHONEBOOK.COMPANY (COMPANY_ID,COMPANY_NAME,COMPANY_CODE,COMPANY_DESCRIPTION) values (2,'Lotus Labs','LOTUS','Online games provider');
Insert into PHONEBOOK.COMPANY (COMPANY_ID,COMPANY_NAME,COMPANY_CODE,COMPANY_DESCRIPTION) values (3,'Idolo Corp.','OLODI','Funeral service provider.');
Insert into PHONEBOOK.COMPANY (COMPANY_ID,COMPANY_NAME,COMPANY_CODE,COMPANY_DESCRIPTION) values (4,'Petmalu Inc.','PTMLU','Inventory systems provider.');
Insert into PHONEBOOK.COMPANY (COMPANY_ID,COMPANY_NAME,COMPANY_CODE,COMPANY_DESCRIPTION) values (0,null,null,null);
REM INSERTING into PHONEBOOK.CONTACT
SET DEFINE OFF;
Insert into PHONEBOOK.CONTACT (CONTACT_ID,CONTACT_NAME,CONTACT_BDAY,CONTACT_VIP,COMPANY_ID,CONTACT_NO) values (1,'chan',to_date('10-OCT-94','DD-MON-RR'),1,3,'96');
Insert into PHONEBOOK.CONTACT (CONTACT_ID,CONTACT_NAME,CONTACT_BDAY,CONTACT_VIP,COMPANY_ID,CONTACT_NO) values (2,'lodilordsz',to_date('10-OCT-19','DD-MON-RR'),0,1,'234');
Insert into PHONEBOOK.CONTACT (CONTACT_ID,CONTACT_NAME,CONTACT_BDAY,CONTACT_VIP,COMPANY_ID,CONTACT_NO) values (10,'chanchan',to_date('10-OCT-10','DD-MON-RR'),0,0,'123');
--------------------------------------------------------
--  DDL for Index CONTACT_UK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "PHONEBOOK"."CONTACT_UK1" ON "PHONEBOOK"."CONTACT" ("CONTACT_NAME") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index CONTID_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "PHONEBOOK"."CONTID_PK" ON "PHONEBOOK"."CONTACT" ("CONTACT_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index COMPANY_UK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "PHONEBOOK"."COMPANY_UK1" ON "PHONEBOOK"."COMPANY" ("COMPANY_CODE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index COMPID_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "PHONEBOOK"."COMPID_PK" ON "PHONEBOOK"."COMPANY" ("COMPANY_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  Constraints for Table CONTACT
--------------------------------------------------------

  ALTER TABLE "PHONEBOOK"."CONTACT" MODIFY ("CONTACT_NO" NOT NULL ENABLE);
  ALTER TABLE "PHONEBOOK"."CONTACT" ADD CONSTRAINT "CONTACT_UK1" UNIQUE ("CONTACT_NAME")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "PHONEBOOK"."CONTACT" MODIFY ("CONTACT_NAME" NOT NULL ENABLE);
  ALTER TABLE "PHONEBOOK"."CONTACT" MODIFY ("CONTACT_VIP" NOT NULL ENABLE);
  ALTER TABLE "PHONEBOOK"."CONTACT" ADD CONSTRAINT "CONTID_PK" PRIMARY KEY ("CONTACT_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
--------------------------------------------------------
--  Constraints for Table COMPANY
--------------------------------------------------------

  ALTER TABLE "PHONEBOOK"."COMPANY" ADD CONSTRAINT "COMPANY_UK1" UNIQUE ("COMPANY_CODE")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "PHONEBOOK"."COMPANY" ADD CONSTRAINT "COMPID_PK" PRIMARY KEY ("COMPANY_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table CONTACT
--------------------------------------------------------

  ALTER TABLE "PHONEBOOK"."CONTACT" ADD CONSTRAINT "COMPANY_ID" FOREIGN KEY ("COMPANY_ID")
	  REFERENCES "PHONEBOOK"."COMPANY" ("COMPANY_ID") ON DELETE CASCADE ENABLE;
