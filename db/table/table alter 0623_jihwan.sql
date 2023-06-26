DROP TABLE "beansGarden".GENRE_GENRE;
DROP TABLE "beansGarden".GENRE;

DROP TABLE "beansGarden".MOVIE_ACTOR;
DROP TABLE "beansGarden".ACTOR;

DROP TABLE "beansGarden".MOVIE_DIRECTOR;
DROP TABLE "beansGarden".DIRECTOR;

CREATE TABLE "PERSON_INFO" (
	"PERSON_NO"	NUMBER		NOT NULL,
	"MOVIE_NO"	NUMBER		NOT NULL,
	"PERSON_NAME"	VARCHAR2(30)		NOT NULL,
	"PERSON_ROLE"	VARCHAR2(100)		NOT NULL,
	"PERSON_IMG"	VARCHAR2(2000)		NULL
);

COMMENT ON COLUMN "PERSON_INFO"."PERSON_NO" IS '영화인번호(SEQ_PERSON_NO)';

COMMENT ON COLUMN "PERSON_INFO"."MOVIE_NO" IS '영화번호(SEQ_MOVIE_NO)';

COMMENT ON COLUMN "PERSON_INFO"."PERSON_NAME" IS '영화인 이름';

COMMENT ON COLUMN "PERSON_INFO"."PERSON_ROLE" IS 'IN (ACTOR, DIRECTOR)';

COMMENT ON COLUMN "PERSON_INFO"."PERSON_IMG" IS '영화인 이미지 경로';

ALTER TABLE "PERSON_INFO" ADD CONSTRAINT "PK_PERSON_INFO" PRIMARY KEY (
	"PERSON_NO",
	"MOVIE_NO"
);

ALTER TABLE "PERSON_INFO" ADD CONSTRAINT "FK_MOVIE_TO_PERSON_INFO_1" FOREIGN KEY (
	"MOVIE_NO"
)
REFERENCES "MOVIE" (
	"MOVIE_NO"
);


ALTER TABLE "beansGarden".PERSON_INFO MODIFY PERSON_ROLE VARCHAR2(100) NULL;