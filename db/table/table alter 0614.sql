-- before movie table alter(back up)

-- DDL generated by DBeaver
-- WARNING: It may differ from actual native database DDL
CREATE TABLE "beansGarden".MOVIE (
	MOVIE_NO NUMBER(38,-127) NULL,
	MOVIE_TITLE VARCHAR2(100) NULL,
	POSTER VARCHAR2(300) NULL,
	SYNOPSIS VARCHAR2(2000) NULL,
	RUNNING_TIME VARCHAR2(100) NULL,
	RATING VARCHAR2(30) NULL,
	RELEASE_DATE VARCHAR2(100) NULL,
	PRODUCER VARCHAR2(100) NULL,
	MOVIE_PRICE NUMBER(38,-127) NULL,
	SCREENING CHAR(1) NULL,
	MAIN_POSTER VARCHAR2(300) NULL
);

INSERT INTO "beansGarden".MOVIE
(MOVIE_NO, MOVIE_TITLE, POSTER, SYNOPSIS, RUNNING_TIME, RATING, RELEASE_DATE, PRODUCER, MOVIE_PRICE, SCREENING, MAIN_POSTER)
VALUES(1001, '플래시', '/images/common/main/포스터/플래시.jpg', '시공간이 붕괴된 세계, 차원이 다른 히어로가 온다', '144', '/images/common/main/12세.png', '2023-06-14', '안드레스 무시에티', 12000, 'Y', '/images/common/main/메인슬라이드/플래시.jpg');
INSERT INTO "beansGarden".MOVIE
(MOVIE_NO, MOVIE_TITLE, POSTER, SYNOPSIS, RUNNING_TIME, RATING, RELEASE_DATE, PRODUCER, MOVIE_PRICE, SCREENING, MAIN_POSTER)
VALUES(1002, '엘리멘탈', '/images/common/main/포스터/엘리멘탈.jpg', '디즈니,픽사의 놀라운 상상력! 올여름, 세상이 살아 숨 쉰다', '109', '/images/common/main/ALL.png', '2023-06-14', '피터 손', 12000, 'Y', NULL);
INSERT INTO "beansGarden".MOVIE
(MOVIE_NO, MOVIE_TITLE, POSTER, SYNOPSIS, RUNNING_TIME, RATING, RELEASE_DATE, PRODUCER, MOVIE_PRICE, SCREENING, MAIN_POSTER)
VALUES(1000, '범죄도시3', '/images/common/main/포스터/범죄도시.jpg', '대체불가 괴물형사 마석도, 서울 광수대로 발탁
베트남 납치 살해범 검거 후 7년 뒤,
‘마석도’(마동석)는 새로운 팀원들과 함께 살인사건을 조사한다.
사건 조사 중, ‘마석도’는 신종 마약 사건이 연루되었음을 알게 되고 수사를 확대한다.', '105', '/images/common/main/15세.png', '2023-05-31', '이상용', 12000, 'Y', NULL);


-- movie table alter
COMMENT ON COLUMN "beansGarden".MOVIE.SCREENING IS 'IN( C: CURRENT, P: PROMISE, D:DOWN)';

COMMENT ON COLUMN "beansGarden".MOVIE.MAIN_POSTER IS '영화 메인 포스터 저장 경로';

ALTER TABLE "beansGarden".MOVIE ADD GENRE VARCHAR2(100) NULL;
COMMENT ON COLUMN "beansGarden".MOVIE.GENRE IS '영화장르';

ALTER TABLE "beansGarden".MOVIE MODIFY SCREENING CHAR(1) DEFAULT 'C';










