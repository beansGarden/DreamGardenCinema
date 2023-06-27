SELECT SEQ_USER_NO.NEXTVAL FROM DUAL;
SELECT SEQ_USER_NO.CURRVAL FROM DUAL;

INSERT INTO "USER_INFO"
VALUES(SEQ_USER_NO.NEXTVAL, 'TEST2ID', 'TEST2PW', 'TEST2NICK', '01099999999', 'TES2T@TEST2.com'
		, SYSDATE, DEFAULT, DEFAULT, DEFAULT, DEFAULT, '20220222', 'F');
	
INSERT INTO "USER_INFO"
VALUES(SEQ_USER_NO.NEXTVAL
, #{userId}
, #{userPw}
, #{userNickname}
, #{userTel}
, #{userEmail}
, SYSDATE
, DEFAULT
, DEFAULT
, DEFAULT
, DEFAULT
, #{userBirht}
, #{userGender});

COMMIT;
ROLLBACK;

SELECT * FROM USER_INFO;
SELECT * FROM TICKETING_INFO ORDER BY 1;
SELECT * FROM SEAT_CHECK;

SELECT TO_CHAR(TICKETING_TIME, 'DAY', 'NLS_DATE_LANGUAGE = ENGLISH') AS TICKETING_DAY, SUM(PAY_AMOUNT) AS TOTAL_SALES, TRUNC(TICKETING_TIME) AS TICKETING_DATE
FROM TICKETING_INFO
WHERE TICKETING_TIME >= TRUNC(SYSDATE, 'IW') - 7 AND TICKETING_TIME < TRUNC(SYSDATE, 'IW')
GROUP BY TO_CHAR(TICKETING_TIME, 'DAY', 'NLS_DATE_LANGUAGE = ENGLISH'), TRUNC(TICKETING_TIME)
ORDER BY TRUNC(TICKETING_TIME);

SELECT COUNT(*) FROM TICKETING_INFO WHERE TICKET_ID LIKE '953624-0626%';

UPDATE TICKETING_INFO SET TICKET_ID = '54360-0626-031112' WHERE TICKET_NO = '102';

UPDATE TICKETING_INFO SET REASON_CANCELLATION_PAYMENT = '결제 금액 오류' WHERE TICKET_ID = '953624-0626-03001';

UPDATE TICKETING_INFO SET TICKEY_IMP_UID = '힝' WHERE TICKET_ID = '953624-0626-03001';

UPDATE TICKETING_INFO SET TICKET_FL = 'Y' WHERE TICKET_ID = '953624-0626-03001';

UPDATE TICKETING_INFO SET PAY_AMOUNT = 12000 * #{seatListSize}
		WHERE TICKET_NO = #{ticketNo}

SELECT USER_NO, USER_ID, USER_PW, USER_NICKNAME, USER_TEL, USER_EMAIL, 
		TO_CHAR(USER_ENROLL_DT, 'YYYY"년" MM"월" DD"일" HH24"시" MI"분" SS"초"')
		USER_ENROLL_DT, USER_AMOUNT, USER_ROLE, USER_RATING, USER_CLOSE, USER_BIRTH, USER_GENDER
FROM "USER_INFO"
WHERE USER_CLOSE = 'N'
AND USER_ID = 'DGCadmin';

SELECT COUNT(*)
FROM "USER_INFO"
WHERE USER_EMAIL  = '123123@123123.com'
AND USER_CLOSE = 'N';

SELECT COUNT(*)
FROM "USER_INFO"
WHERE USER_ID  = '123123'
AND USER_CLOSE = 'N';

SELECT COUNT(*)
FROM "USER_INFO"
WHERE USER_NICKNAME  = '123123'
AND USER_CLOSE = 'N';

SELECT USER_ID 
FROM "USER_INFO"
WHERE USER_BIRTH  = '20220202'
AND USER_TEL  = '01012341234'
AND USER_CLOSE = 'N';

UPDATE "USER_INFO" SET
USER_PW  = '1231'
WHERE USER_ID = '123123';


















