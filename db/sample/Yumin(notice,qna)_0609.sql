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