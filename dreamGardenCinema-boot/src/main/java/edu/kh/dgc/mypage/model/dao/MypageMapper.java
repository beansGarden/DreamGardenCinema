package edu.kh.dgc.mypage.model.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.dgc.mypage.model.dto.Coupon;
import edu.kh.dgc.qna.model.dto.Qna;
import edu.kh.dgc.qna.model.dto.QnaImage;
import edu.kh.dgc.ticketing.model.dto.Ticket;
import edu.kh.dgc.user.model.dto.User;

@Mapper
public interface MypageMapper {

	/** 내 문의 내역 조회
	 * @param userNo
	 * @return
	 */
	public List<Qna> myQnaList(int userNo);

	/** 닉네임 변경
	 * @param loginuser
	 * @return
	 */
	public int changeNickName(User loginuser);

	/** 내 정보 수정(이메일,주소)
	 * @param updateUser
	 * @return
	 */
	public int changeInfo(User updateUser);

	/** 회원 번호가 일치하는 회원 비밀번호 조회
	 * @param userNo
	 * @return
	 */
	public String selectEncPw(int userNo);

	/** 회원 비밀번호 변경
	 * @param user
	 * @return
	 */
	public int changePw(User user);

	/** 회원 쿠폰 리스트 조회
	 * @param userNo
	 * @return
	 */
	public List<Coupon> couponList(int userNo);

	/** 회원 탈퇴
	 * @param userNo
	 * @return
	 */
	public int secessionUser(int userNo);

	/** 회원이 예매한 티켓 조회
	 * @param userNo
	 * @return
	 */
	public List<Ticket> ticketList(int userNo);

	/** 나의 문의내역 이미지 조회
	 * @param qnano
	 * @return
	 */
	public List<QnaImage> myqnaImageList(int qnano);

	/** 취소 내역 조회
	 * @param userNo
	 * @return
	 */
	public List<Ticket> cancleReservation(int userNo);

	/** 해당 예매 내역의 영화 시간 조회
	 * @param ticketId
	 * @return
	 */
	public LocalDateTime movieTime(String ticketId);

	/** 해당 티켓 보다 후에 예매한 티켓의 등급보다 높은 쿠폰 사용 여부
	 * @param ticket
	 * @return
	 */
	public int countXCoupon(Ticket ticket);

	/** 매달 1일 아침 5시 마다 유저 쿠폰 전체 삭제
	 * @return
	 */
	public int deleteAllCoupon();

	/** 누적 금액 4만원 미만 고객 등급 브론즈 업데이트
	 * @return
	 */
	public int updateAllBronze();

	/** 누적 금액 4만원 미만 고객 userNo전체 조회 
	 * @return
	 */
	public List<User> selectBronzeUserNo();

	/** 누적 금액 4만원 이상 10만원 미만 고객 실버 업데이트
	 * @return
	 */
	public int updateAllSilver();

	/** 실버 등급 고객 userNo 전체 조회
	 * @return
	 */
	public List<User> selectSilverUserNo();

	/** 브론즈 쿠폰 1개,실버 쿠폰 2개 insert
	 * @param userNo
	 * @return
	 */
	public int insertSilverCoupon(int userNo);

	/** 누적 금액 10만원 이상 20만원 미만 고객 골드 업데이트
	 * @return
	 */
	public int updateAllGold();

	/** 골드 등급 고객 userNo 전체 조회
	 * @return
	 */
	public List<User> selectGolduserNo();

	/** 브론즈 쿠폰 1개,실버 쿠폰 2개,골드 쿠폰 3개 insert
	 * @param userNo
	 * @return
	 */
	public int insertAllGoldCoupon(int userNo);

	/** 누적 금액 20만원 이상 고객 플래티넘 업데이트
	 * @return
	 */
	public int updateAllPlatinum();

	/** 플래티넘 등급 고객 userNo 전체 조회
	 * @return
	 */
	public List<User> platinumNoList();

	/** 브론즈 쿠폰 1개,실버 쿠폰 2개,골드 쿠폰 3개,플래티넘 쿠폰 2개 insert
	 * @param userNo
	 * @return
	 */
	public int insertAllPlatinumCoupon(int userNo);

	/** 모든 유저 누적 금액 초기화
	 * @return
	 */
	public int updateAllAmount();

	/** 유저 전체 RATING 1 업데이트
	 * @return
	 */
	public int updateRating1();

	/** 해당 취소 티켓에 사용된 쿠폰 조회
	 * @param ticketNo
	 * @return
	 */
	public int selectCouponNo(String ticketNo);

	/**취소시 사용한 쿠폰 환불
	 * @param couponNo
	 * @return
	 */
	public int returnCoupon(int couponNo);


}
