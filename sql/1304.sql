;              
CREATE USER IF NOT EXISTS "DMN" SALT 'dc18dbaccfaf6ff6' HASH 'bb575f2eb7a8c8409b3a7fc6860f9e379ce3984f030dff270daf8fa39e0d88ec' ADMIN;         
CREATE USER IF NOT EXISTS "USER" SALT '93878ba7e17ad4c8' HASH 'ad32455a328185ac8d06b50180bceefd1bc4927ec73d9ada560e4faa736a693e' ADMIN;        
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_5C1B0621_9805_44B2_A2B2_581E9FC90650" START WITH 62 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_19FE18A7_7050_4C74_A4D0_070AA913CF18" START WITH 5 BELONGS_TO_TABLE; 
CREATE CACHED TABLE "PUBLIC"."RESULT"(
    "ID" BIGINT DEFAULT (NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_5C1B0621_9805_44B2_A2B2_581E9FC90650") NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_5C1B0621_9805_44B2_A2B2_581E9FC90650",
    "USER_ID" BIGINT,
    "DATE" TEXT,
    "HOUR" INT,
    "MINUTES" INT,
    "HOUR_ID" INT
);              
-- 33 +/- SELECT COUNT(*) FROM PUBLIC.RESULT;  
INSERT INTO "PUBLIC"."RESULT" VALUES
(1, 293188753, STRINGDECODE('30.\ufffd\ufffd.20'), 0, 15, NULL),
(2, 293188753, '03.04.20', 0, 15, NULL),
(3, 293188753, '03.04.20', 1, 20, NULL),
(4, 293188753, '03.04.20', 1, 10, NULL),
(5, 293188753, '03.04.20', 1, 4, NULL),
(6, 293188753, '03.04.20', 1, 6, NULL),
(7, 293188753, '03.04.20', 1, 7, NULL),
(8, 293188753, '03.04.20', 1, 7, NULL),
(9, 293188753, '03.04.20', 1, 6, NULL),
(10, 293188753, '03.04.20', 1, 5, NULL),
(11, 293188753, '03.04.20', 1, 6, NULL),
(12, 293188753, '03.04.20', 1, 6, NULL),
(13, 293188753, '03.04.20', 1, 6, NULL),
(14, 293188753, '03.04.20', 1, 7, NULL),
(15, 293188753, '03.04.20', 22, 4, NULL),
(16, 293188753, '07.04.20', 23, 4, 1),
(17, 293188753, '07.04.20', 23, 7, 0),
(18, 293188753, '08.04.20', 0, 8, 1),
(19, 293188753, '08.04.20', 0, 7, 0),
(51, 293188753, '10.04.20', 22, 5, 1),
(52, 293188753, '10.04.20', 22, 5, 0),
(53, 293188753, '10.04.20', 22, 5, 0),
(54, 293188753, '10.04.20', 22, 6, 1),
(55, 293188753, '10.04.20', 22, 20, 1),
(56, 293188753, '10.04.20', 23, 5, 2),
(57, 293188753, '10.04.20', 23, 5, 2),
(58, 293188753, '10.04.20', 23, 15, 2),
(59, 293188753, '12.04.20', 23, 5, 1),
(60, 293188753, '12.04.20', 23, 25, 1),
(61, 123, '12.04.20', 10, 25, 1),
(61, 123, '12.04.20', 11, 25, 2),
(61, 123, '12.04.20', 12, 50, 3),
(61, 123, '12.04.20', 13, 50, 4);     
CREATE CACHED TABLE "PUBLIC"."BUTTON"(
    "ID" INT NOT NULL,
    "TEXT" TEXT,
    "COMMAND_ID" INT,
    "URL" TEXT,
    "REQUEST_CONTACT" BOOLEAN
);          
ALTER TABLE "PUBLIC"."BUTTON" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_7" PRIMARY KEY("ID");        
-- 4 +/- SELECT COUNT(*) FROM PUBLIC.BUTTON;   
INSERT INTO "PUBLIC"."BUTTON" VALUES
(1, STRINGDECODE('\u0412\u0432\u0435\u0441\u0442\u0438'), 1, NULL, FALSE),
(2, STRINGDECODE('\u0420\u0435\u0437\u0443\u043b\u044c\u0442\u0430\u0442\u044b'), 2, NULL, FALSE),
(3, STRINGDECODE('\u041d\u0435\u0434\u0435\u043b\u044f'), 0, NULL, FALSE),
(4, STRINGDECODE('\u041d\u0430\u0437\u0430\u0434'), 3, NULL, FALSE);             
CREATE CACHED TABLE "PUBLIC"."MESSAGE"(
    "ID" INT NOT NULL,
    "TEXT" TEXT,
    "PHOTO" TEXT,
    "KEYBOARD_ID" INT
);     
ALTER TABLE "PUBLIC"."MESSAGE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_63" PRIMARY KEY("ID");      
-- 4 +/- SELECT COUNT(*) FROM PUBLIC.MESSAGE;  
INSERT INTO "PUBLIC"."MESSAGE" VALUES
(1, STRINGDECODE('\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u043c\u0438\u043d\u0443\u0442\u044b'), NULL, 0),
(2, STRINGDECODE('\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u043f\u0435\u0440\u0438\u043e\u0434'), NULL, 2),
(3, STRINGDECODE('\u041d\u0435\u0434\u0435\u043b\u044f'), NULL, 0),
(4, STRINGDECODE('\u0413\u043b\u0430\u0432\u043d\u043e\u0435 \u043c\u0435\u043d\u044e'), NULL, 1);               
CREATE CACHED TABLE "PUBLIC"."KEYBOARD"(
    "ID" INT NOT NULL,
    "BUTTON_IDS" TEXT,
    "INLINE" BOOLEAN,
    "COMMENT" TEXT
);             
ALTER TABLE "PUBLIC"."KEYBOARD" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_4" PRIMARY KEY("ID");      
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.KEYBOARD; 
INSERT INTO "PUBLIC"."KEYBOARD" VALUES
(1, '1,2', FALSE, 'main menu'),
(2, '3;4', FALSE, 'period');            
CREATE CACHED TABLE "PUBLIC"."SCORE"(
    "ID" BIGINT DEFAULT (NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_19FE18A7_7050_4C74_A4D0_070AA913CF18") NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_19FE18A7_7050_4C74_A4D0_070AA913CF18",
    "USER_ID" BIGINT,
    "DATE" TEXT,
    "SCORE" INT
);    
-- 4 +/- SELECT COUNT(*) FROM PUBLIC.SCORE;    
INSERT INTO "PUBLIC"."SCORE" VALUES
(1, 293188753, '12.04.20', -30),
(2, 123, '12.04.20', 70),
(3, 293188753, '12.04.20', -30),
(4, 123, '12.04.20', 70);      