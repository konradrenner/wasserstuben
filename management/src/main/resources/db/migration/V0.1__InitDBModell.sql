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
    ID                  UUID NOT NULL,
    EVENTTIMESTAMP      TIMESTAMP NOT NULL,
    AGGREGATEID         UUID NOT NULL,
    AGGREGATETYPE       VARCHAR(1000) NOT NULL,

    PRIMARY KEY (ID)
);

CREATE TABLE ENTITY_LOG
(
    ID                  UUID NOT NULL,
    AGGREGATE_LOG_ID    UUID NOT NULL,
    EVENTTYPE           VARCHAR(100) NOT NULL,
    ENTITYID            UUID NOT NULL,
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
    ID                  UUID NOT NULL,
    CADASTRALTOWNSHIP   BIGINT NOT NULL,
    ESTATEID            VARCHAR(100) NOT NULL,
    DEPOSTID            BIGINT NOT NULL,
    
    PRIMARY KEY (ID),
    UNIQUE (CADASTRALTOWNSHIP, ESTATEID, DEPOSTID)
);

CREATE TABLE REALESTATE_OWNER
(
    REALESTATE_ID       UUID NOT NULL,
    OWNER_ID            UUID NOT NULL,

    PRIMARY KEY (REALESTATE_ID, OWNER_ID),

    CONSTRAINT REALESTATE_FOREIGN_KEY 
            FOREIGN KEY(REALESTATE_ID)
                REFERENCES REALESTATE(ID),

    CONSTRAINT OWNER_FOREIGN_KEY 
            FOREIGN KEY(OWNER_ID)
                REFERENCES OWNER(ID)
);

CREATE TABLE OWNER
(
    VERSION             BIGINT NOT NULL,
    ID                  UUID NOT NULL,
    FIRSTNAME           VARCHAR(100) NOT NULL,
    LASTNAME            VARCHAR(100) NOT NULL,

    PRIMARY KEY (ID)
);

CREATE TABLE COUNTERFITTING
(
    VERSION             BIGINT NOT NULL,
    ID                  UUID NOT NULL UNIQUE,
    CADASTRALTOWNSHIP   BIGINT NOT NULL,
    ESTATEID            VARCHAR(100) NOT NULL,
    DEPOSTID            BIGINT NOT NULL,
    DESCRIPTION         VARCHAR(30) NOT NULL,
    INSTALLATION        TIMESTAMP NOT NULL,
    MANUFACTURER        VARCHAR(50) NOT NULL,
    
    PRIMARY KEY (ID)

);

CREATE TABLE COUNTERFITTING_CALIBRATION
(
    VERSION             BIGINT NOT NULL,
    COUNTERFITTING_ID   UUID NOT NULL,
    CALIBRATION         TIMESTAMP NOT NULL,

    PRIMARY KEY (COUNTERFITTING_ID, CALIBRATION),

    CONSTRAINT COUNTERFITTING_FOREIGN_KEY 
        FOREIGN KEY(COUNTERFITTING_ID)
            REFERENCES COUNTERFITTING(ID)
);
