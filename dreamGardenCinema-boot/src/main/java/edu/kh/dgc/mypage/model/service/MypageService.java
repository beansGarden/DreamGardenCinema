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

}
