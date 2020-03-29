;              
CREATE USER IF NOT EXISTS "DMN" SALT 'dc18dbaccfaf6ff6' HASH 'bb575f2eb7a8c8409b3a7fc6860f9e379ce3984f030dff270daf8fa39e0d88ec' ADMIN;         
CREATE USER IF NOT EXISTS "USER" SALT '9efc8f911cca48dd' HASH '9e94f6e773caf1a1e6047d79f230d397635c1bd1dd4ff5e36aae944a45aa40ee' ADMIN;        
CREATE CACHED TABLE "PUBLIC"."BUTTON"(
    "ID" INT NOT NULL,
    "TEXT" TEXT,
    "COMMAND_ID" INT,
    "URL" TEXT,
    "REQUEST_CONTACT" BOOLEAN
);          
ALTER TABLE "PUBLIC"."BUTTON" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_7" PRIMARY KEY("ID");        
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.BUTTON;   
INSERT INTO "PUBLIC"."BUTTON" VALUES
(1, STRINGDECODE('\u0412\u0432\u0435\u0441\u0442\u0438'), 1, NULL, FALSE);
CREATE CACHED TABLE "PUBLIC"."COMMAND"(
    "ID" INT NOT NULL,
    "COMMAND_TYPE_ID" INT,
    "MESSAGE_ID" INT
);              
ALTER TABLE "PUBLIC"."COMMAND" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_6" PRIMARY KEY("ID");       
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.COMMAND;  
INSERT INTO "PUBLIC"."COMMAND" VALUES
(1, 1, 0);               
CREATE CACHED TABLE "PUBLIC"."MESSAGE"(
    "ID" INT NOT NULL,
    "TEXT" TEXT,
    "PHOTO" TEXT,
    "KEYBOARD_ID" INT
);     
ALTER TABLE "PUBLIC"."MESSAGE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_63" PRIMARY KEY("ID");      
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.MESSAGE;  
INSERT INTO "PUBLIC"."MESSAGE" VALUES
(1, STRINGDECODE('\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u043c\u0438\u043d\u0443\u0442\u044b'), NULL, 0);           
CREATE CACHED TABLE "PUBLIC"."KEYBOARD"(
    "ID" INT NOT NULL,
    "BUTTON_IDS" TEXT,
    "INLINE" BOOLEAN,
    "COMMENT" TEXT
);             
ALTER TABLE "PUBLIC"."KEYBOARD" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_4" PRIMARY KEY("ID");      
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.KEYBOARD; 
INSERT INTO "PUBLIC"."KEYBOARD" VALUES
(1, '1', FALSE, 'main menu');           
