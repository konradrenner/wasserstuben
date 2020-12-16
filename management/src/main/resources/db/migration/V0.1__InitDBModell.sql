/* 
 * Copyright (C) 2020 Konrad Renner
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * Author:  Konrad Renner
 * Created: 01.12.2020
 */

CREATE TABLE AGGREGATE_LOG
(
    ID                  CHAR(36) NOT NULL,
    EVENTTIMESTAMP      TIMESTAMP NOT NULL,
    AGGREGATEID         CHAR(36) NOT NULL,
    AGGREGATETYPE       VARCHAR(1000) NOT NULL,

    PRIMARY KEY (ID)
);

CREATE TABLE ENTITY_LOG
(
    ID                  CHAR(36) NOT NULL,
    AGGREGATE_LOG_ID    CHAR(36) NOT NULL,
    EVENTTYPE           VARCHAR(100) NOT NULL,
    ENTITYID            CHAR(36) NOT NULL,
    ENTITYVERSION       BIGINT NOT NULL,
    ENTITY              TEXT NOT NULL,

    PRIMARY KEY (ID, AGGREGATE_LOG_ID),

    CONSTRAINT AGGREGATE_LOG_FOREIGN_KEY 
                FOREIGN KEY(AGGREGATE_LOG_ID)
                    REFERENCES AGGREGATE_LOG(ID)
);


CREATE TABLE REALESTATE
(
    VERSION             BIGINT NOT NULL,
    ID                  CHAR(36) NOT NULL,
    CADASTRALTOWNSHIP   BIGINT NOT NULL,
    ESTATEID            VARCHAR(100) NOT NULL,
    DEPOSITID           BIGINT NOT NULL,
    
    PRIMARY KEY (ID),
    UNIQUE (CADASTRALTOWNSHIP, ESTATEID, DEPOSITID)
);

CREATE TABLE OWNER
(
    VERSION             BIGINT NOT NULL,
    ID                  CHAR(36) NOT NULL,
    FIRSTNAME           VARCHAR(100) NOT NULL,
    LASTNAME            VARCHAR(100) NOT NULL,

    PRIMARY KEY (ID)
);

CREATE TABLE REALESTATE_OWNER
(
    REALESTATE_ID       CHAR(36) NOT NULL,
    OWNER_ID            CHAR(36) NOT NULL,

    PRIMARY KEY (REALESTATE_ID, OWNER_ID),

    CONSTRAINT REALESTATE_OWNER_REALESTATE_FOREIGN_KEY 
            FOREIGN KEY(REALESTATE_ID)
                REFERENCES REALESTATE(ID),

    CONSTRAINT REALESTATE_OWNER_OWNER_FOREIGN_KEY 
            FOREIGN KEY(OWNER_ID)
                REFERENCES OWNER(ID)
);

CREATE TABLE COUNTERFITTING
(
    VERSION             BIGINT NOT NULL,
    ID                  CHAR(36) NOT NULL,
    REALESTATE_ID       CHAR(36) NOT NULL,
    DESCRIPTION         VARCHAR(30) NOT NULL,
    INSTALLATION        TIMESTAMP NOT NULL,
    MANUFACTURER        VARCHAR(50) NOT NULL,
    
    PRIMARY KEY (ID),

    CONSTRAINT COUNTERFITTING_REALESTATE_FOREIGN_KEY 
            FOREIGN KEY(REALESTATE_ID)
                REFERENCES REALESTATE(ID)

);

CREATE TABLE COUNTERFITTING_CALIBRATION
(
    VERSION             BIGINT NOT NULL,
    COUNTERFITTING_ID   CHAR(36) NOT NULL,
    CALIBRATION         TIMESTAMP NOT NULL,

    PRIMARY KEY (COUNTERFITTING_ID, CALIBRATION),

    CONSTRAINT COUNTERFITTING_FOREIGN_KEY 
        FOREIGN KEY(COUNTERFITTING_ID)
            REFERENCES COUNTERFITTING(ID)
);

insert into REALESTATE (VERSION, ID, CADASTRALTOWNSHIP, ESTATEID, DEPOSITID) 
values (1, '926e5684-a795-4820-9a52-f7ee9a2828f5', 1, 'abcd', 2);

insert into OWNER (VERSION, ID, FIRSTNAME, LASTNAME) 
values (1, '5bef8aab-80d3-4b98-b3ce-dfcdf43ce6b1', 'Max', 'Mustermann');

insert into REALESTATE_OWNER (REALESTATE_ID, OWNER_ID) 
values ('926e5684-a795-4820-9a52-f7ee9a2828f5', '5bef8aab-80d3-4b98-b3ce-dfcdf43ce6b1');

insert into COUNTERFITTING (VERSION, ID, REALESTATE_ID, DESCRIPTION, INSTALLATION, MANUFACTURER) 
values (1, '1964ae29-f2e1-4d70-a0b2-eb42a430e71b', '926e5684-a795-4820-9a52-f7ee9a2828f5', 'test fitting', '2004-10-19 10:23:54', 'Super Mario');

insert into COUNTERFITTING_CALIBRATION (VERSION, COUNTERFITTING_ID, CALIBRATION) 
values (1, '1964ae29-f2e1-4d70-a0b2-eb42a430e71b', '2004-10-19 10:23:54');

insert into COUNTERFITTING_CALIBRATION (VERSION, COUNTERFITTING_ID, CALIBRATION) 
values (1, '1964ae29-f2e1-4d70-a0b2-eb42a430e71b', '2009-09-10 12:32:45');