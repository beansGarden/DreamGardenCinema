

INSERT INTO "beansGarden".MOVIE
(MOVIE_NO, MOVIE_TITLE, POSTER, SYNOPSIS, RUNNING_TIME, RATING, RELEASE_DATE, PRODUCER, MOVIE_PRICE, SCREENING, MAIN_POSTER, GENRE)
VALUES(1001, '플래시', '/images/common/main/포스터/플래시.jpg', '시공간이 붕괴된 세계, 차원이 다른 히어로가 온다', '144', '/images/common/main/12세.png', '2023-06-14', '안드레스 무시에티', 12000, 'Y', '/images/common/main/메인슬라이드/플래시.jpg', NULL);

INSERT INTO "beansGarden".MOVIE
		(MOVIE_NO, MOVIE_TITLE, POSTER, SYNOPSIS, RUNNING_TIME, RATING,
		RELEASE_DATE, PRODUCER, MOVIE_PRICE, SCREENING, MAIN_POSTER, GENRE)
		VALUES(SEQ_MOVIE_NO, #{movieTitle}, #{poster}, #{synopsis}, 
		#{runningTime}, #{rating}, #{releaseDate}, NULL , 12000 , #{screening} , NULL, #{genre})
;
		

ALTER TABLE "beansGarden".MOVIE MODIFY SYNOPSIS VARCHAR2(4000);
