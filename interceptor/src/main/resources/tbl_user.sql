CREATE TABLE tbl_user (
    userid      VARCHAR2(10) CONSTRAINT tbl_user_pk PRIMARY KEY,
    userpw      VARCHAR2(10) NOT NULL,
    uname       VARCHAR2(50) NOT NULL,
    upoint      INT DEFAULT 0 NOT NULL
);


INSERT INTO tbl_user (userid, userpw, uname, upoint)
SELECT
    'USER_'||level,
    'PASS_'||level,
    'UNAME_'||level,
    level
FROM dual
CONNECT BY level <= 10;

COMMIT;

SELECT *
FROM tbl_user;