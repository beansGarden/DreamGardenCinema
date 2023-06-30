--회원 조회--
SELECT * FROM USER_INFO ;

--번호,아이디,이메일,닉네임,가입일,등급,상태
SELECT USER_NO,USER_ID,USER_EMAIL,USER_NICKNAME,USER_ENROLL_DT,USER_RATING,USER_CLOSE FROM USER_INFO
WHERE USER_CLOSE ='N';

--qna조회--
SELECT * FROM QNA;

--번호,카테고리, 제목,등록인

			SELECT QNA_NO,QNA_TITLE,QNA_ENROLL_DATE,USER_NICKNAME 
		FROM QNA
		JOIN "USER_INFO" 
		ON USER_NO=USER_NO2;
	
--번호,제목
	SELECT QNA_NO,QNA_CATEGORY,USER_NO2,QNA_TITLE,QNA_CONTENT,TO_CHAR(QNA_ENROLL_DATE,'YYYY-MM-DD') QNA_ENROLL_DATE,USER_NICKNAME 
	FROM QNA
	JOIN "USER_INFO" ON USER_NO=USER_NO2
	WHERE QNA_NO ='1';

	UPDATE "QNA"
	SET QNA_TITLE = '영화 상영 시작하고 20분 지난 후에도 입장이 가능한가요???',
		QNA_CONTENT = '당일에 조금 늦을 거 같은데 20분이 지나도 입장시켜주나요?'
	WHERE QNA_NO ='1';
	
COMMIT;

SELECT * FROM "QNA";


UPDATE "qna"
SET QNA_DELETE_FL = 'Y'
WHERE QNA_NO ='1';

INSERT INTO "QNA"
VALUES (SEQ_QNA_NO.NEXTVAL,'문의 테스트1','문의 테스트 내용',SYSDATE,DEFAULT,DEFAULT,'P','11');


SELECT * FROM "QNA";

SELECT * FROM "QNA _COMMENT";

INSERT INTO "QNA _COMMENT"
VALUES (SEQ_QNACOMMENT_NO.NEXTVAL,DEFAULT,DEFAULT,1,17,'처리했습니다');

COMMIT;

UPDATE "QNA"
SET QNA_CHECK_FL = 'Y'
WHERE QNA_NO = 17;

SELECT USER_NO FROM "QNA _COMMENT"
WHERE QNA_NO ='2';

UPDATE "QNA _COMMENT"
SET QNA_COMMENT ='핸드폰 번호 또는 연락처 남겨주시면 직원이 처리하여 연락드리겠습니다...'
WHERE QNA_COMMENT_NO ='12';

COMMIT;

SELECT * FROM USER_INFO;

SELECT USER_NO,USER_ID,USER_EMAIL,USER_NICKNAME,USER_ENROLL_DT,USER_RATING,USER_ROLE  
FROM USER_INFO
ORDER BY USER_NO DESC;


SELECT * FROM USER_INFO;

SELECT *
FROM "USER_INFO";
	
UPDATE "USER_INFO"
SET USER_CLOSE ='N'
WHERE USER_NO = '19';

SELECT * FROM MOVIE m ;

--번호	제목	감독	개봉일	매출
	
SELECT MOVIE_NO,MOVIE_TITLE,PRODUCER,RELEASE_DATE,MOVIE_PRICE 
FROM MOVIE
ORDER BY MOVIE_NO DESC;

--공지사항 조회

SELECT * FROM NOTICE ;

SELECT NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,NOTICE_ENROLL_DATE
FROM NOTICE
WHERE NOTICE_NO = '2';

INSERT INTO NOTICE 
VALUES (SEQ_NOTICE_NO.NEXTVAL,'상영관 안내','안녕하세요. 드림가든시네마 상영관은 총 세 관 입니다.',SYSDATE,DEFAULT,DEFAULT);

UPDATE NOTICE 
SET 
NOTICE_TITLE = '상영관 정기 소방 점검',
NOTICE_CONTENT = '안녕하세요. 드림가든시네마 입니다. 정기 소방 점검 안내 드립니다.'
WHERE 
NOTICE_NO ='15';

COMMIT;

UPDATE NOTICE 
SET NOTICE_FL = 'Y'
WHERE NOTICE_NO ='15';


		SELECT QNA_NO,QNA_CATEGORY,USER_NO2,QNA_TITLE,QNA_CONTENT,TO_CHAR(QNA_ENROLL_DATE,'YYYY-MM-DD') QNA_ENROLL_DATE,USER_NICKNAME,QNA_DELETE_FL
	FROM QNA
	JOIN "USER_INFO" ON USER_NO=USER_NO2
	WHERE QNA_NO = '1';


SELECT * FROM USER_INFO ui ;

SELECT * 
FROM USER_INFO
WHERE USER_ROLE = 'A';


INSERT INTO "QNA"
VALUES (SEQ_QNA_NO.NEXTVAL,'로그인문제','문의 테스트',SYSDATE,DEFAULT,DEFAULT,'p','11');

SELECT * FROM "FAQ";

SELECT FAQ_NO,FAQ_TITLE,FAQ_CONTENT,FAQ_DELETE_FL,FAQ_CATEGORY
FROM "FAQ";


INSERT INTO "FAQ"
VALUES (SEQ_FAQ_NO.NEXTVAL,'로그인문제','문의 테스트',DEFAULT,'p');

UPDATE "FAQ"
SET
FAQ_DELETE_FL ='N'
WHERE FAQ_NO = '19';

SELECT * FROM NOTICE ;

UPDATE NOTICE
SET NOTICE_FL ='N'
WHERE NOTICE_NO = '23';

	
SELECT * FROM "QNA _COMMENT";
	
UPDATE "QNA _COMMENT"
SET QNA_COMMENT = '처리했습니다. 감사합니다'
WHERE QNA_COMMENT_NO = '1';	
		
ALTER TABLE "QNA _COMMENT"
MODIFY QNA_COMMENT VARCHAR2(3000) NOT NULL;

SELECT *FROM "QNA _COMMENT"; 

INSERT INTO "QNA _COMMENT"
VALUES (SEQ_QNACOMMENT_NO.NEXTVAL,DEFAULT,DEFAULT,(SELECT USER_NO FROM "QNA _COMMENT" WHERE QNA_NO = '24'),'24','아');

SELECT FAQ.FAQ_NO, FAQ.FAQ_TITLE, FAQ_CONTENT, FAQ_DELETE_FL, FAQ_CATEGORY
FROM FAQ
WHERE FAQ_DELETE_FL = 'N';


SELECT NOTICE_NO, NOTICE_TITLE, NOTICE_CONTENT, TO_CHAR(NOTICE_ENROLL_DATE, 'YYYY-MM-DD') AS NOTICE_ENROLL_DATE, NOTICE_VIEWPOINT, NOTICE_FL
FROM NOTICE
WHERE NOTICE_NO LIKE '%1%';

SELECT M.MOVIE_NO, M.MOVIE_TITLE, M.RUNNING_TIME,M.RELEASE_DATE,TO_CHAR(S.MOVIE_TIME, 'YYYY-MM-DD') AS MOVIE_DAY, TO_CHAR(S.MOVIE_TIME, 'HH24:MI') AS MOVIE_TIME, S.MOVIE_THEATER
FROM MOVIE M
JOIN MOVIE_SCHEDULE S ON M.MOVIE_NO = S.MOVIE_NO;

SELECT
  M.MOVIE_NO,
  M.MOVIE_TITLE,
  M.RUNNING_TIME,
  M.RELEASE_DATE,
  TO_CHAR(S.MOVIE_TIME, 'YYYY-MM-DD') AS MOVIE_DAY,
  TO_CHAR(S.MOVIE_TIME, 'HH24:MI') AS MOVIE_TIME,
  S.MOVIE_THEATER
FROM
  MOVIE M
JOIN (
  SELECT
    MOVIE_NO,
    MIN(MOVIE_TIME) AS MOVIE_TIME,
    MOVIE_THEATER
  FROM
    MOVIE_SCHEDULE
  GROUP BY
    MOVIE_NO, MOVIE_THEATER
) S ON M.MOVIE_NO = S.MOVIE_NO;


SELECT
  M.MOVIE_NO,
  M.MOVIE_TITLE,
  M.RUNNING_TIME,
  M.RELEASE_DATE,
  TO_CHAR(S.MOVIE_TIME, 'YYYY-MM-DD') AS MOVIE_DAY,
  LISTAGG(TO_CHAR(S.MOVIE_TIME, 'HH24:MI'), ', ') WITHIN GROUP (ORDER BY S.MOVIE_TIME) AS MOVIE_TIME,
  S.MOVIE_THEATER
FROM
  MOVIE M
JOIN MOVIE_SCHEDULE S ON M.MOVIE_NO = S.MOVIE_NO
WHERE MOVIE_THEATER = '2'
GROUP BY
  M.MOVIE_NO,
  M.MOVIE_TITLE,
  M.RUNNING_TIME,
  M.RELEASE_DATE,
  TO_CHAR(S.MOVIE_TIME, 'YYYY-MM-DD'),
  S.MOVIE_THEATER
  ORDER BY MOVIE_DAY DESC;


 SELECT * FROM MOVIE_SCHEDULE;
SELECT * FROM MOVIE;

INSERT INTO MOVIE_SCHEDULE
VALUES (TO_TIMESTAMP('2023-06-27 14:00:00.000', 'YYYY-MM-DD HH24:MI:SS.FF'), '3', 3);

SELECT MOVIE_TITLE,RELEASE_DATE
FROM MOVIE
ORDER BY RELEASE_DATE DESC;


--INSERT INTO MOVIE_SCHEDULE
  --VALUES (
    --TO_TIMESTAMP(#{movieTime}, 'YYYY-MM-DD HH24:MI:SS.FF'),
   -- #{movieTheater},
   -- #{movieNo}
 -- )
  
SELECT MOVIE_NO || ' - ' || MOVIE_TITLE AS MOVIE_SELECT
FROM MOVIE
ORDER BY MOVIE_NO DESC;

SELECT count(SELECT
		  M.MOVIE_NO,
		  M.MOVIE_TITLE,
		  M.RUNNING_TIME,
		  M.RELEASE_DATE,
		  TO_CHAR(S.MOVIE_TIME, 'YYYY-MM-DD') AS MOVIE_DAY,
		  LISTAGG(TO_CHAR(S.MOVIE_TIME, 'HH24:MI'), ', ') WITHIN GROUP (ORDER BY S.MOVIE_TIME) AS MOVIE_TIME,
		  S.MOVIE_THEATER
		)FROM
		  MOVIE M
		JOIN MOVIE_SCHEDULE S ON M.MOVIE_NO = S.MOVIE_NO
		GROUP BY
		  M.MOVIE_NO,
		  M.MOVIE_TITLE,
		  M.RUNNING_TIME,
		  M.RELEASE_DATE,
		  TO_CHAR(S.MOVIE_TIME, 'YYYY-MM-DD'),
		  S.MOVIE_THEATER
		  ORDER BY M.RELEASE_DATE DESC;
		 
		  SELECT
		  M.MOVIE_NO,
		  M.MOVIE_TITLE,
		  M.RUNNING_TIME,
		  M.RELEASE_DATE,
		  TO_CHAR(S.MOVIE_TIME, 'YYYY-MM-DD') AS MOVIE_DAY,
		  LISTAGG(TO_CHAR(S.MOVIE_TIME, 'HH24:MI'), ', ') WITHIN GROUP (ORDER BY S.MOVIE_TIME) AS MOVIE_TIME,
		  S.MOVIE_THEATER
		FROM
		  MOVIE M
		JOIN MOVIE_SCHEDULE S ON M.MOVIE_NO = S.MOVIE_NO
		GROUP BY
		  M.MOVIE_NO,
		  M.MOVIE_TITLE,
		  M.RUNNING_TIME,
		  M.RELEASE_DATE,
		  TO_CHAR(S.MOVIE_TIME, 'YYYY-MM-DD'),
		  S.MOVIE_THEATER
		  ORDER BY M.RELEASE_DATE DESC;

		 
		 SELECT
  M.MOVIE_NO,
  M.MOVIE_TITLE,
  M.RUNNING_TIME,
  M.RELEASE_DATE,
  TO_CHAR(S.MOVIE_TIME, 'YYYY-MM-DD') AS MOVIE_DAY,
  LISTAGG(TO_CHAR(S.MOVIE_TIME, 'HH24:MI'), ', ') WITHIN GROUP (ORDER BY S.MOVIE_TIME) AS MOVIE_TIME,
  S.MOVIE_THEATER
FROM
  MOVIE M
JOIN MOVIE_SCHEDULE S ON M.MOVIE_NO = S.MOVIE_NO
WHERE TO_CHAR(S.MOVIE_TIME, 'YYYY-MM-DD') = '2023-06-27'
GROUP BY
  M.MOVIE_NO,
  M.MOVIE_TITLE,
  M.RUNNING_TIME,
  M.RELEASE_DATE,
  TO_CHAR(S.MOVIE_TIME, 'YYYY-MM-DD'),
  S.MOVIE_THEATER
ORDER BY M.RELEASE_DATE DESC;

SELECT * FROM TICKETING_INFO;

SELECT PAY_AMOUNT 
FROM TICKETING_INFO
WHERE MOVIE_NO = '3';

SELECT SUM(PAY_AMOUNT) AS TOTAL_AMOUNT
FROM TICKETING_INFO
WHERE MOVIE_NO = '3';

SELECT QNA_NO, QNA_TITLE, QNA_ENROLL_DATE, USER_NICKNAME, QNA_DELETE_FL
FROM (
   SELECT QNA_NO, QNA_TITLE, QNA_ENROLL_DATE, USER_NICKNAME, QNA_DELETE_FL,
          ROW_NUMBER() OVER (ORDER BY QNA_NO DESC) AS rn
   FROM QNA
   JOIN "USER_INFO" ON USER_NO = USER_NO2
   WHERE QNA_DELETE_FL ='N'
) 
WHERE rn <= 5;

SELECT * FROM MOVIE_SCHEDULE ms ;

SELECT MOVIE_NO,MOVIE_TITLE,RELEASE_DATE,MOVIE_PRICE 
FROM MOVIE
ORDER BY MOVIE_NO DESC;

SELECT * FROM "QNA";


UPDATE "QNA"
SET QNA_DELETE_FL = 'N'
WHERE QNA_NO = '4';

SELECT * FROM PERSON_INFO;

SELECT p.PERSON_ROLE
FROM MOVIE m 
JOIN PERSON_INFO p ON (m.MOVIE_NO = p.MOVIE_NO)
WHERE PERSON_ROLE = '감독';

SELECT MOVIE_NO,MOVIE_TITLE,RELEASE_DATE,MOVIE_PRICE,(SELECT p.PERSON_NAME
		FROM MOVIE m 
		JOIN PERSON_INFO p ON (m.MOVIE_NO = p.MOVIE_NO)
		WHERE PERSON_ROLE = '감독')
		FROM MOVIE
		ORDER BY MOVIE_NO DESC;
		
SELECT m.MOVIE_NO, m.MOVIE_TITLE, m.RELEASE_DATE, m.MOVIE_PRICE, 
       (SELECT p.PERSON_NAME
        FROM PERSON_INFO p 
        WHERE p.MOVIE_NO = m.MOVIE_NO AND p.PERSON_ROLE = '감독') AS PERSON_NAME
FROM MOVIE m
ORDER BY m.MOVIE_NO DESC;

SELECT * FROM "QNA";

UPDATE "QNA"
SET QNA_CHECK_FL ='Y'
WHERE QNA_NO ='2';

 		SELECT
		  M.MOVIE_NO,
		  M.MOVIE_TITLE,
		  M.RUNNING_TIME,
		  M.RELEASE_DATE,
		  TO_CHAR(S.MOVIE_TIME, 'YYYY-MM-DD') AS MOVIE_DAY,
		  LISTAGG(TO_CHAR(S.MOVIE_TIME, 'HH24:MI'), ', ') WITHIN GROUP (ORDER BY S.MOVIE_TIME) AS MOVIE_TIME,
		  S.MOVIE_THEATER
		FROM
		  MOVIE M
		JOIN MOVIE_SCHEDULE S ON M.MOVIE_NO = S.MOVIE_NO
		GROUP BY
		  M.MOVIE_NO,
		  M.MOVIE_TITLE,
		  M.RUNNING_TIME,
		  M.RELEASE_DATE,
		  TO_CHAR(S.MOVIE_TIME, 'YYYY-MM-DD'),
		  S.MOVIE_THEATER
		ORDER BY M.RELEASE_DATE DESC;
           
	
	SELECT
  MOVIE_NO,
  MOVIE_TITLE,
  RELEASE_DATE,
  MOVIE_PRICE,
  (
    SELECT LISTAGG(PERSON_NAME, ', ') WITHIN GROUP (ORDER BY PERSON_NAME)
    FROM PERSON_INFO
    WHERE PERSON_ROLE = '감독' AND MOVIE.MOVIE_NO = PERSON_INFO.MOVIE_NO
  ) AS PERSON_ROLE
FROM MOVIE
ORDER BY MOVIE_NO DESC;

SELECT * FROM PERSON_INFO;

SELECT PERSON_ROLE 
FROM MOVIE M
JOIN PERSON_INFO P ON M.MOVIE_NO = P.MOVIE_NO;

SELECT MOVIE_NO,MOVIE_TITLE,RELEASE_DATE,MOVIE_PRICE,
FROM MOVIE;
		 
		 
		 SELECT
		  M.MOVIE_NO,
		  M.MOVIE_TITLE,
		  M.RUNNING_TIME,
		  M.RELEASE_DATE,
		  TO_CHAR(S.MOVIE_TIME, 'YYYY-MM-DD') AS MOVIE_DAY,
		  LISTAGG(TO_CHAR(S.MOVIE_TIME, 'HH24:MI'), ', ') WITHIN GROUP (ORDER BY S.MOVIE_TIME) AS MOVIE_TIME,
		  S.MOVIE_THEATER
		FROM
		  MOVIE M
		JOIN MOVIE_SCHEDULE S ON M.MOVIE_NO = S.MOVIE_NO
		WHERE MOVIE_THEATER = '1'
		GROUP BY
		  M.MOVIE_NO,
		  M.MOVIE_TITLE,
		  M.RUNNING_TIME,
		  M.RELEASE_DATE,
		  TO_CHAR(S.MOVIE_TIME, 'YYYY-MM-DD'),
		  S.MOVIE_THEATER
		  ORDER BY M.RELEASE_DATE DESC;

SELECT QNA_IMG_PATH,QNA_NO FROM QNA_IMG qi ;

SELECT QNA_NO,QNA_TITLE,TO_CHAR(QNA_ENROLL_DATE,'YYYY-MM-DD') QNA_ENROLL_DATE,USER_NICKNAME ,QNA_DELETE_FL,QNA_CHECK_FL
		FROM QNA
		JOIN "USER_INFO" 
		ON USER_NO=USER_NO2
		ORDER BY QNA_NO DESC;
		
SELECT
  QNA.QNA_NO,
  QNA.QNA_TITLE,
  TO_CHAR(QNA.QNA_ENROLL_DATE, 'YYYY-MM-DD') AS QNA_ENROLL_DATE,
  USER_INFO.USER_NICKNAME,
  QNA.QNA_DELETE_FL,
  QNA.QNA_CHECK_FL,
  QNA_IMG.QNA_IMG_PATH
FROM
  QNA
  JOIN USER_INFO ON QNA.USER_NO2 = USER_INFO.USER_NO
  LEFT JOIN (
    SELECT QNA_NO, MAX(QNA_IMG_PATH) AS QNA_IMG_PATH
    FROM QNA_IMG
    GROUP BY  QNA_NO
  ) QNA_IMG ON QNA.QNA_NO = QNA_IMG.QNA_NO
WHERE QNA.QNA_NO = '1'
ORDER BY
  QNA.QNA_NO DESC;
 
 	SELECT
	  M.MOVIE_NO,
	  M.MOVIE_TITLE,
	  M.RUNNING_TIME,
	  M.RELEASE_DATE,
	  TO_CHAR(S.MOVIE_TIME, 'YYYY-MM-DD') AS MOVIE_DAY,
	  LISTAGG(TO_CHAR(S.MOVIE_TIME, 'HH24:MI'), ', ') WITHIN GROUP (ORDER BY S.MOVIE_TIME) AS MOVIE_TIME,
	  S.MOVIE_THEATER
	FROM
	  MOVIE M
	JOIN MOVIE_SCHEDULE S ON M.MOVIE_NO = S.MOVIE_NO
	WHERE MOVIE_THEATER = '1'
	GROUP BY
	  M.MOVIE_NO,
	  M.MOVIE_TITLE,
	  M.RUNNING_TIME,
	  M.RELEASE_DATE,
	  TO_CHAR(S.MOVIE_TIME, 'YYYY-MM-DD'),
	  S.MOVIE_THEATER
	ORDER BY M.RELEASE_DATE DESC;

 
 SELECT * FROM USER_INFO;


SELECT USER_NO,
	  QNA.QNA_NO,
	  QNA.QNA_TITLE,
	  TO_CHAR(QNA.QNA_ENROLL_DATE, 'YYYY-MM-DD') AS QNA_ENROLL_DATE,
	  USER_INFO.USER_NICKNAME,
	  QNA.QNA_DELETE_FL,
	  QNA.QNA_CHECK_FL,
	  QNA_IMG.QNA_IMG_PATH
	FROM
	  QNA
	  JOIN USER_INFO ON QNA.USER_NO2 = USER_INFO.USER_NO
	  LEFT JOIN (
	    SELECT QNA_NO, MAX(QNA_IMG_PATH) AS QNA_IMG_PATH
	    FROM QNA_IMG
	    GROUP BY  QNA_NO
	  ) QNA_IMG ON QNA.QNA_NO = QNA_IMG.QNA_NO
	WHERE QNA.QNA_NO = '24'
	ORDER BY
	  QNA.QNA_NO DESC;
	  
	 	SELECT
		  MOVIE_NO,
		  MOVIE_TITLE,
		  RELEASE_DATE,
		  MOVIE_PRICE,
		  (
		    SELECT LISTAGG(PERSON_NAME, ', ') WITHIN GROUP (ORDER BY PERSON_NAME)
		    FROM PERSON_INFO
		    WHERE PERSON_ROLE = '감독' AND MOVIE.MOVIE_NO = PERSON_INFO.MOVIE_NO
		  ) AS PERSON_NAME
		FROM MOVIE
		ORDER BY MOVIE_NO DESC;
		
	
	 SELECT M.MOVIE_NO,M.MOVIE_TITLE,M.RELEASE_DATE,PERSON_NAME
	 FROM MOVIE M
	 JOIN PERSON_INFO P ON M.MOVIE_NO = P.MOVIE_NO
	 WHERE M.MOVIE_NO = '2';

SELECT M.MOVIE_NO, M.MOVIE_TITLE, M.RELEASE_DATE, LISTAGG(P.PERSON_NAME, ', ') WITHIN GROUP (ORDER BY P.PERSON_NAME) AS PERSON_NAMES
FROM MOVIE M
JOIN PERSON_INFO P ON M.MOVIE_NO = P.MOVIE_NO
WHERE  P.PERSON_NAME LIKE '%다니엘%'
GROUP BY M.MOVIE_NO, M.MOVIE_TITLE, M.RELEASE_DATE;

SELECT SUM(PAY_AMOUNT) AS PAY_AMOUNT 
FROM TICKETING_INFO
WHERE MOVIE_NO ='3';

SELECT FAQ_NO,FAQ_TITLE,FAQ_CONTENT,FAQ_DELETE_FL,FAQ_CATEGORY
FROM "FAQ"
WHERE FAQ_NO = '21';

SELECT * FROM QNA;
SELECT * FROM QNA_IMG; 

SELECT Q.QNA_NO,Q.QNA_TITLE,I.QNA_IMG_NO,I.QNA_IMG_PATH
FROM "QNA" Q
JOIN "QNA_IMG" I ON (Q.QNA_NO = I.QNA_NO); 


	SELECT USER_NO,
		QNA.QNA_NO,
		QNA.QNA_TITLE,QNA.QNA_CATEGORY,
		TO_CHAR(QNA.QNA_ENROLL_DATE, 'YYYY-MM-DD') AS QNA_ENROLL_DATE,
		USER_INFO.USER_NICKNAME,
		QNA.QNA_DELETE_FL,
		QNA.QNA_CHECK_FL,
		QNA_IMG.QNA_IMG_PATH
		FROM
		QNA
		JOIN USER_INFO ON QNA.USER_NO2 = USER_INFO.USER_NO
		LEFT JOIN (
		SELECT QNA_NO, MAX(QNA_IMG_PATH) AS QNA_IMG_PATH
		FROM QNA_IMG
		GROUP BY QNA_NO
		) QNA_IMG ON QNA.QNA_NO = QNA_IMG.QNA_NO
		WHERE QNA.QNA_NO = '65'
		ORDER BY
		QNA.QNA_NO DESC;
	
SELECT * FROM MOVIE_REVIEW;	
SELECT * FROM REPORT r ;	

			SELECT  R.REPORT_NO,R.REPORT_TITLE,R.REPORT_CONTNENT,R.REPORT_ENROLL_DATE,R.REPORT_FL,
			R.REPORT_DELETE_FL,R.REVIEW_NO,R.REPORT_WIRTER,R.REPORTED_USER_NO,M.REVIEW_CONTENT
			FROM REPORT R
			JOIN MOVIE_REVIEW M ON R.REVIEW_NO = M.REVIEW_NO
			WHERE R.REPORT_NO = '1';

ALTER TABLE MOVIE_REVIEW ADD REVIEW_DELETE_FL CHAR(1) DEFAULT 'N';

ALTER TABLE MOVIE_REVIEW DROP COLUMN REVIEW_DELETEFL;

UPDATE MOVIE_REVIEW
SET	REVIEW_DELETE_FL ='N'
WHERE REVIEW_NO ='1';

UPDATE REPORT
SET	REPORT_FL ='Y'
WHERE REVIEW_NO ='1';

INSERT INTO "REPORT"
VALUES (SEQ_REPORT_NO.NEXTVAL,'스포당했어요','제곧내2',SYSDATE,DEFAULT,DEFAULT,'2','10','24');



INSERT INTO MOVIE_SCHEDULE (MOVIE_TIME, MOVIE_THEATER, MOVIE_NO)
VALUES (TO_DATE('2023-06-26 13:00:00', 'YYYY-MM-DD HH24:MI:SS'), '1', '3');


SELECT REVIEW_NO,SCORE,TO_CHAR(REVIEW_DATE, 'YYYY-MM-DD') AS REVIEW_DATE
		,REVIEW_CONTENT,USER_NO,REVIEW_DELETE_FL,
		((SELECT MOVIE_NO || ' - ' || MOVIE_TITLE) AS MOVIE_SELECT FROM MOVIE ORDER BY MOVIE_NO DES)
FROM MOVIE_REVIEW
ORDER BY REVIEW_NO DESC;

SELECT MR.REVIEW_NO, MR.SCORE, TO_CHAR(MR.REVIEW_DATE, 'YYYY-MM-DD') AS REVIEW_DATE,
       MR.REVIEW_CONTENT, MR.USER_NO, MR.REVIEW_DELETE_FL,
       (SELECT M.MOVIE_NO || ' - ' || M.MOVIE_TITLE FROM MOVIE M WHERE M.MOVIE_NO = MR.MOVIE_NO) AS MOVIE_NO 
FROM MOVIE_REVIEW MR
JOIN MOVIE M ON M.MOVIE_NO = MR.MOVIE_NO
ORDER BY MR.REVIEW_NO DESC;


	SELECT MR.REVIEW_NO, MR.SCORE, TO_CHAR(MR.REVIEW_DATE, 'YYYY-MM-DD') AS REVIEW_DATE,
       MR.REVIEW_CONTENT, MR.USER_NO, MR.REVIEW_DELETE_FL, M.MOVIE_TITLE 
		FROM MOVIE_REVIEW MR
		JOIN MOVIE M ON M.MOVIE_NO = MR.MOVIE_NO
		ORDER BY MR.REVIEW_NO DESC;

	SELECT R.REVIEW_NO,R.SCORE,R.REVIEW_CONTENT,R.USER_NO,M.MOVIE_TITLE
	FROM REPORT R
	JOIN MOVIE M USING (MOVIE_NO)  ;



SELECT SUM(PAY_AMOUNT) AS TOTAL_AMOUNT
FROM TICKETING_INFO
WHERE MOVIE_NO = '3';

SELECT * FROM TICKETING_INFO;

SELECT MOVIE_NO || ' - ' || MOVIE_TITLE AS MOVIE_TITLE,MOVIE_NO
FROM MOVIE
WHERE SCREENING = 'C'
ORDER BY MOVIE_NO DESC;