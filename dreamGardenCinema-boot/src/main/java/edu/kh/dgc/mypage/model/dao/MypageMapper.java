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

}
