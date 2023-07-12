INSERT INTO "NOTICE"
VALUES (SEQ_NOTICE_NO.NEXTVAL,'시스템 점검 안내','드림가든시네마를 사랑해 주시는 고객 여러분 감사드립니다.
당사 서비스의 원활한 제공을 위해 아래 일정 중 서버 정기 점검이 있을 예정입니다.',DEFAULT, DEFAULT, DEFAULT);


INSERT INTO "NOTICE"
VALUES (SEQ_NOTICE_NO.NEXTVAL,'드림가든시네마개인정보처리방침 개정 안내','안녕하십니까, 드림가든시네마입니다.

당사는 2023년 6월 9일 자로 아래와 같이 개인정보 처리방침을 개정 운영할 예정이오니, 변경 사항을 확인하시어 이용에 불편 없으시길 바랍니다.',DEFAULT, DEFAULT, DEFAULT);

INSERT INTO "NOTICE"
VALUES (SEQ_NOTICE_NO.NEXTVAL,'시스템 장애 복구 안내','안녕하십니까.드림가든시네마 입니다.

6월 1일 홈페이지의 시스템 상태가 불안정하여 장애가 발생한 부분에 대해 사과드립니다.
현재 복구되어 정상적으로 서비스 이용이 가능합니다.
서비스이용에 불편을 드린점 다시 한번 사과드리며, 장애 발생시 빠르게 해결할 수 있도록 최선을 다하겠습니다.
감사합니다.',DEFAULT, DEFAULT, DEFAULT);


INSERT INTO "NOTICE"
VALUES (SEQ_NOTICE_NO.NEXTVAL,'영화관람권 재판매 관련 안내','안녕하십니까, 드림가든시네마입니다.
최근 중고거래 사이트 등 공식 판매처가 아닌 곳에서영화관람권 재판매 사기 등으로 인해 각종 피해 사례가 발생하고 있어,고객님들의 피해가 우려됩니다.
당사는 고객 권리 보호를 위해 영화관람권 재판매자로 확인될 경우,회원 약관에 의거하여 ID 사용 제한 및 강제 탈퇴 조치될 수 있으며,법적 조치를 통해 고객들의 권리 보호에 적극 노력할 예정입니다.
피해 예방을 위해 영화관람권은 드림가든시네마에서 운영하는 공식적인 채널에서 구매하시기를 권장 드리며,당사는 앞으로도 고객 여러분의 권리 보호와 불법 재판매 근절을 위해 노력하겠습니다. 감사합니다.',DEFAULT, DEFAULT, DEFAULT);


INSERT INTO "NOTICE"
VALUES (SEQ_NOTICE_NO.NEXTVAL,'<문화가 있는 날 영화 관람료 조정 안내문>','정부의 정책 변경에 따라 문화가 있는 날 관람료가

아래와 같이 조정될 예정이오니,

이용에 참고하여 주시기 바랍니다.',DEFAULT, DEFAULT, DEFAULT);




SELECT * FROM "NOTICE";
SELECT * FROM "USER_INFO";

/*회원 데이터*/
INSERT INTO "USER_INFO"
VALUES(SEQ_USER_NO.NEXTVAL, 'user01', 'password', '유저일', '01012341111', 'user01@email.com'
      , DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, '880101-1');

     
INSERT INTO "USER_INFO"
VALUES(SEQ_USER_NO.NEXTVAL, 'user02', 'password', '유저이', '01012342222', 'user02@email.com'
      , DEFAULT, DEFAULT, DEFAULT, 4, DEFAULT, '790202-2');
     
INSERT INTO "USER_INFO"
VALUES(SEQ_USER_NO.NEXTVAL, 'user03', 'password', '유저삼', '01012343333', 'user03@email.com'
      , DEFAULT, DEFAULT, DEFAULT, 3, DEFAULT, '980303-1');

INSERT INTO "USER_INFO"
VALUES(SEQ_USER_NO.NEXTVAL, 'user04', 'password', '유저사', '01012344444', 'user04@email.com'
      , DEFAULT, DEFAULT, DEFAULT, 2, DEFAULT, '910404-2');
     
INSERT INTO "USER_INFO"
VALUES(SEQ_USER_NO.NEXTVAL, 'user05', 'password', '유저오', '01012345555', 'user05@email.com'
      , DEFAULT, DEFAULT, DEFAULT, 2, DEFAULT, '810505-2');
     
INSERT INTO "USER_INFO"
VALUES(SEQ_USER_NO.NEXTVAL, 'user06', 'password', '유저육', '01012346666', 'user06@email.com'
      , DEFAULT, DEFAULT, DEFAULT, 3, DEFAULT, '770606-1');
     
INSERT INTO "USER_INFO"
VALUES(SEQ_USER_NO.NEXTVAL, 'user07', 'password', '유저칠', '01012347777', 'user07@email.com'
      , DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, '000707-3');
     
INSERT INTO "USER_INFO"
VALUES(SEQ_USER_NO.NEXTVAL, 'user08', 'password', '유저팔', '01012348888', 'user08@email.com'
      , DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, '960808-2');
     
INSERT INTO "USER_INFO"
VALUES(SEQ_USER_NO.NEXTVAL, 'user09', 'password', '유저구', '01012349999', 'user09@email.com'
      , DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, '930909-1');
     
INSERT INTO "USER_INFO"
VALUES(SEQ_USER_NO.NEXTVAL, 'user10', 'password', '유저십', '01012341010', 'user10@email.com'
      , DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, '021010-4');

COMMIT;

--0623
SELECT * FROM "USER_INFO";

INSERT INTO "USER_INFO"
VALUES(SEQ_USER_NO.NEXTVAL, 'user11', 'password', '유일일', '01012341111', 'user11@email.com'
      , DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, '1988-11-11','M','서울시 중랑구');
      
INSERT INTO "USER_INFO"
VALUES(SEQ_USER_NO.NEXTVAL, 'user12', 'password', '유일이', '01012341212', 'user12@email.com'
      , DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, '2001-12-12','F','제주시 서귀포구');
     
INSERT INTO "USER_INFO"
VALUES(SEQ_USER_NO.NEXTVAL, 'user13', 'password', '유일삼', '01012341313', 'user13@email.com'
      , DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, '1979-12-13','M','경기도 하남시 망월동');
     
INSERT INTO "USER_INFO"
VALUES(SEQ_USER_NO.NEXTVAL, 'user14', 'password', '유일사', '01012341414', 'user14@email.com'
      , DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, '1960-05-14','F','경기도 수원시 팔달구');
     
INSERT INTO "USER_INFO"
VALUES(SEQ_USER_NO.NEXTVAL, 'user15', 'password', '유일오', '01012341515', 'user15@email.com'
      , DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, '1995-05-15','M','경기도 고양시 덕양구');
     
INSERT INTO "USER_INFO"
VALUES(SEQ_USER_NO.NEXTVAL, 'user15', 'password', '유일육', '01012341616', 'user16@email.com'
      , DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, '1991-10-16','F','서울시 서대문구 홍제동');

INSERT INTO "USER_INFO"
VALUES(SEQ_USER_NO.NEXTVAL, 'user17', 'password', '유일칠', '01012341717', 'user17@email.com'
      , DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, '1996-08-17','M','서울시 중구');
     
INSERT INTO "USER_INFO"
VALUES(SEQ_USER_NO.NEXTVAL, 'user19', 'password', '유일구', '01012341919', 'user19@email.com'
      , DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, '1991-08-19','F','서울시 중구');

/*qna 등록*/

INSERT INTO "QNA"
VALUES(SEQ_QNA_NO.NEXTVAL, '영화 상영 시작하고 20분 지난 후에도 입장이 가능한가요?', '당일에 조금 늦을 거 같은데 20분이 지나도 입장 시켜주나요??'
		,'2023-05-01', DEFAULT, DEFAULT,'P',14);

INSERT INTO "QNA"
VALUES(SEQ_QNA_NO.NEXTVAL, '영화관 안에서 햄버거 먹을 수 있나요?', '외부음식 가능하다고 써있는데 햄버거는 가능할까요?'
		,'2023-05-10', DEFAULT, DEFAULT,'P',14);	
	
INSERT INTO "QNA"
VALUES(SEQ_QNA_NO.NEXTVAL, '영화관 안에서 지갑을 잃어버렸어요.', '12:30 1관에서 상영한 범죄도시3을 보고 나왔는데 지갑이 없어진 걸 몇 시간 뒤에 알았습니다.
가서 찾을 수 있을까요?'
		,'2023-05-12', DEFAULT, DEFAULT,'P',13);	
	
INSERT INTO "QNA"
VALUES(SEQ_QNA_NO.NEXTVAL, '10분 전에 예매하고 바로 취소 가면 환불 가능한가요?', '영화 시작 10분전에 예매하고 바로 취소하면 환불 가능할까요?'
		,'2023-05-12', DEFAULT, DEFAULT,'B',12);	

INSERT INTO "QNA"
VALUES(SEQ_QNA_NO.NEXTVAL, '로그인 문제', '안녕하세요. 로그인이 잘 안 돼서 연락 드립니다. 비밀번호도 맞는데 자꾸 틀리다고 하네요.
확인 좀 해주세요.','2023-05-13', DEFAULT, DEFAULT,'H',11);		

INSERT INTO "QNA"
VALUES(SEQ_QNA_NO.NEXTVAL, '회원탈퇴 시켜주세요', '안녕하세요. 회원탈퇴하는데 오류가 자꾸 나요. 탈퇴 시켜주세요.','2023-05-15', DEFAULT, DEFAULT,'A',11);	

SELECT * FROM "QNA";

COMMIT;

/*qna 댓글*/

/*문의 댓글 테이블 컬럼 추가*/
ALTER TABLE "QNA _COMMENT" ADD QNA_COMMENT VARCHAR2(3000) NOT NULL;

COMMENT ON COLUMN "QNA _COMMENT"."QNA_COMMENT" IS '문의댓글';

COMMIT;

SELECT * FROM "QNA _COMMENT";

INSERT INTO "QNA _COMMENT"
VALUES(SEQ_QNA_NO.NEXTVAL,'2023-05-01',DEFAULT,14,1,' 안녕하세요. 드림가든시네마 입니다. 20분 지나도 입장가능합니다.');	

INSERT INTO "QNA _COMMENT"
VALUES(SEQ_QNA_NO.NEXTVAL,'2023-05-10',DEFAULT,14,2,' 안녕하세요. 드림가든시네마 입니다. 외부음식 가능합니다.');	

INSERT INTO "QNA _COMMENT"
VALUES(SEQ_QNA_NO.NEXTVAL,'2023-05-12',DEFAULT,13,3,'안녕하세요. 드림가든시네마 입니다. 상영 후 청소할 때 분실물은 창구에 맡겨두오니

창구에서 확인 하시기 바랍니다. 그래도 찾지 못 하시는 경우에는 직원에게 문의 바랍니다.');

INSERT INTO "QNA _COMMENT"
VALUES(SEQ_QNA_NO.NEXTVAL,'2023-05-12',DEFAULT,12,4,'영화 환불은 10분전까지만 가능합니다. 남은 시간이 10분 이하가 되면 환불은 불가능하오니
이 점 유의하시기 바랍니다.');	

INSERT INTO "QNA _COMMENT"
VALUES(SEQ_QNA_NO.NEXTVAL,'2023-05-13',DEFAULT,11,5,'핸드폰 번호 또는 연락처 남겨주시면 직원이 처리하여 연락드리겠습니다.');	

INSERT INTO "QNA _COMMENT"
VALUES(SEQ_QNA_NO.NEXTVAL,'2023-05-15',DEFAULT,11,6,'핸드폰 번호 또는 연락처 남겨주시면 직원이 처리하여 연락드리겠습니다.');	

COMMIT;

/*FAQ*/

INSERT INTO "FAQ"
VALUES(SEQ_FAQ_NO.NEXTVAL,'인터넷 예매시 부분환불/교환이 가능한가요?','인터넷 예매시에는 예매매수의 전체환불 및 교환만 가능합니다.',DEFAULT,'B');	

INSERT INTO "FAQ"
VALUES(SEQ_FAQ_NO.NEXTVAL,'아이디를 바꾸고 싶은데 수정할 수 있나요?','회원가입시 입력한 아이디는 변경이 불가하며,
탈퇴 후 재가입(신규가입)을 통해 새로운 아이디로 지정할 수 있습니다.

단, 탈퇴 시 재가입은 30일이후부터 가능합니다.',DEFAULT,'A');	

SELECT * FROM "FAQ";


COMMIT;


ALTER TABLE USER_INFO ADD USER_STATUS CHAR(1) DEFAULT 'A' CONSTRAINT USER_STATUS_CHK CHECK(USER_STATUS IN('A','B','D'));

COMMENT ON COLUMN "USER_INFO"."USER_STATUS" IS '탈퇴여부(A:활동가능 ,B:활동중지 , D: 탈퇴)';

ALTER TABLE USER_INFO DROP COLUMN USER_STATUS;


