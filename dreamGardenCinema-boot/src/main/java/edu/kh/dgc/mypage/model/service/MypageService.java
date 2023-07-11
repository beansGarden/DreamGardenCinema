package edu.kh.dgc.mypage.model.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import edu.kh.dgc.mypage.model.dto.Coupon;
import edu.kh.dgc.qna.model.dto.Qna;
import edu.kh.dgc.qna.model.dto.QnaImage;
import edu.kh.dgc.ticketing.model.dto.Ticket;
import edu.kh.dgc.user.model.dto.User;

public interface MypageService {

	/** 나의 문의 내역 리스트 조회
	 * @param userNo
	 * @return
	 */
	List<Qna> myQnaList(int userNo);

	/** 닉네임 변경
	 * @param loginuser
	 * @return
	 */
	int changeNickName(User loginuser);

	/** 회원 정보 수정(이메일,주소)
	 * @param updateUser
	 * @return
	 */
	int changeInfo(User updateUser);

	/** 회원 비밀번호 수정
	 * @param checkPw
	 * @param userNo
	 * @param userPw
	 * @return
	 */
	int changePw(String checkPw, int userNo, String userPw);

	/** 회원 쿠폰 리스트 조회
	 * @param userNo
	 * @return
	 */
	List<Coupon> couponList(int userNo);

	/** 비밀번호 조회
	 * @param userNo
	 * @param secessionPwValue 
	 * @return
	 */
	int secessionCheck(int userNo, String secessionPwValue);

	/** 회원 탈퇴
	 * @param userNo
	 * @return
	 */
	int secessionUser(int userNo);

	List<Ticket> reservation(int userNo);

	List<QnaImage> myqnaImageList(int qnano);

	/** 예매 취소 내역
	 * @param userNo
	 * @return
	 */
	List<Ticket> cancleReservation(int userNo);

	/** 해당 예매 내역의 영화 시간 조회
	 * @return
	 */
	LocalDateTime movieTime(String ticketId);

	/** 해당 티켓 보다 후에 예매한 티켓의 등급보다 높은 쿠폰 사용 여부
	 * @param ticket
	 * @return
	 */
	int countXCoupon(Ticket ticket);

	/** 매달 1일 유저 쿠폰 전체 삭제
	 * @return
	 */
	int deleteAllCoupon();

	/** 누적 금액 4만원 미만 고객 등급 브론즈 업데이트
	 * @return
	 */
	int updateAllBronze();
	
	
	/** 누적 금액 4만원 미만 고객 userNo전체 조회 
	 * @return
	 */
	List<User> selectBronzeUserNo();

	/** 누적 금액 4만원 이상 10만원 미만 고객 실버 업데이트
	 * @return
	 */
	int updateAllSilver();

	
	/**실버 등급 고객 userNo 전체 조회
	 * @return
	 */
	List<User> selectSilverUserNo();

	/** 브론즈 쿠폰 1개,실버 쿠폰 2개 insert
	 * @param userNo
	 * @return
	 */
	int insertSilverCoupon(int userNo);

	/** 누적 금액 10만원 이상 20만원 미만 고객 골드 업데이트
	 * @return
	 */
	int updateAllGold();

	/** 골드 등급 고객 userNo 전체 조회
	 * @return
	 */
	List<User> selectGoldUserNo();

	/** 브론즈 쿠폰 1개,실버 쿠폰 2개,골드 쿠폰 3개 insert
	 * @param userNo
	 * @return
	 */
	int insertAllGoldCoupon(int userNo);

	/** 누적 금액 20만원 이상 고객 플래티넘 업데이트
	 * @return
	 */
	int updateAllPlatinum();

	/** 플래티넘 등급 고객 userNo 전체 조회
	 * @return
	 */
	List<User> platinumNoList();

	/** 브론즈 쿠폰 1개,실버 쿠폰 2개,골드 쿠폰 3개,플래티넘 쿠폰 2개 insert
	 * @param userNo
	 * @return
	 */
	int insertAllPlatinumCoupon(int userNo);

	/** 모든 유저 누적 금액 초기화
	 * @return
	 */
	int updateAllAmount();

	/** 유저 전체 RATING 1 업데이트
	 * @return
	 */
	int updateRating1();

	/** 해당 티켓에 사용된 쿠폰번호 조회
	 * @param ticketNo
	 * @return
	 */
	int selectCouponNo(String ticketNo);

	/** 취소시 사용한 쿠폰 환불
	 * @param couponNo
	 * @return
	 */
	int returnCoupon(int couponNo);


}
