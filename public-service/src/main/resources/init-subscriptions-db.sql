CREATE DATABASE IF NOT EXISTS subscriptions;
USE subscriptions;
CREATE TABLE IF NOT EXISTS MEMBER_SUBSCRIPTIONS(EMAIL VARCHAR(255),
                                                POINTS INTEGER,
                                                REGISTRATION_DATE TIMESTAMP,
                                                DELETED BOOLEAN default 0);
CREATE TABLE IF NOT EXISTS NON_MEMBER_SUBSCRIPTIONS(EMAIL VARCHAR(255),
                                                    REGISTRATION_DATE TIMESTAMP,
                                                    DELETED BOOLEAN default 0);