package edu.kh.dgc.mypage.model.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.dgc.mypage.model.dao.MypageMapper;
import edu.kh.dgc.mypage.model.dto.Coupon;
import edu.kh.dgc.qna.model.dto.Qna;
import edu.kh.dgc.qna.model.dto.QnaImage;
import edu.kh.dgc.ticketing.model.dao.TicketingMapper;
import edu.kh.dgc.ticketing.model.dto.Ticket;
import edu.kh.dgc.user.model.dto.User;

@Service
public class MypageServiceImpl implements MypageService{

	@Autowired
	private MypageMapper mapper;

	@Autowired 
	private BCryptPasswordEncoder bcrypt;
	//내 문의 내역 조회
	@Override
	public List<Qna> myQnaList(int userNo) {
		
		return mapper.myQnaList(userNo);
	}

	//닉네임 변경
	@Override
	public int changeNickName(User loginuser) {
		
		return mapper.changeNickName(loginuser);
	}

	//정보 수정 (이메일,주소)
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int changeInfo(User updateUser) {
		return mapper.changeInfo(updateUser);
	}
	
	//비밀 번호 수정
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int changePw(String checkPw, int userNo, String userPw) {
		
		String encPw = mapper.selectEncPw(userNo);
		
		String encodedPw;
		
		if(bcrypt.matches(userPw, encPw)) {
			
			encodedPw = userPw;
			
		}else {
			encodedPw = bcrypt.encode(userPw);
			
		}
		
		if(bcrypt.matches(encodedPw, encPw)) {
			
			User user = new User();
			user.setUserNo(userNo);
			user.setUserPw(bcrypt.encode(checkPw));
			
			return mapper.changePw(user);
		}
		
		return 0;
	}
	
	//회원 보유 쿠폰 리스트 조회
	@Override
	public List<Coupon> couponList(int userNo) {
		return mapper.couponList(userNo);
	}

	@Override
	public int secessionCheck(int userNo, String secessionPwValue) {
		
		int result = 0;

		String encPw = mapper.selectEncPw(userNo);
		
		if(bcrypt.matches(secessionPwValue,encPw)) {
			result = 1;
			
		}else {

			result = 0;
		}
		
		return result;
	}

	// 회원 탈퇴
	@Override
	public int secessionUser(int userNo) {
		return mapper.secessionUser(userNo);
	}

	// 예매 내역 조회
	@Override
	public List<Ticket> reservation(int userNo) {
		
		return mapper.ticketList(userNo);
	}

	// 나의 문의 내역 이미지 조회
	@Override
	public List<QnaImage> myqnaImageList(int qnano) {
	
		return mapper.myqnaImageList(qnano);
	}

	// 취소 내역 조회
	@Override
	public List<Ticket> cancleReservation(int userNo) {
		
		return mapper.cancleReservation(userNo);
	}

	// 해당 예매 내역 영화 시간 조회
	@Override
	public LocalDateTime movieTime(String ticketId) {
		return mapper.movieTime(ticketId);
	}

	// 해당 티켓 보다 후에 예매한 티켓의 등급보다 높은 쿠폰 사용 여부
	@Override
	public int countXCoupon(Ticket ticket) {
		return mapper.countXCoupon(ticket);
	}
	
	// 매달 1일 아침 5시 마다 유저 쿠폰 전체 삭제
	@Override
	public int deleteAllCoupon() {
		return mapper.deleteAllCoupon();
	}

	//누적 금액 4만원 미만 고객 등급 브론즈 업데이트
	@Override
	public int updateAllBronze() {
		return mapper.updateAllBronze();
	}

	//누적 금액 4만원 미만 고객 userNo전체 조회 
	@Override
	public List<User> selectBronzeUserNo() {
		return mapper.selectBronzeUserNo();
	}
	
	// 누적 금액 4만원 이상 10만원 미만 고객 실버 업데이트
	@Override
	public int updateAllSilver() {
		return mapper.updateAllSilver();
	}

	// 실버 등급 고객 userNo 전체 조회
	@Override
	public List<User> selectSilverUserNo() {
		return mapper.selectSilverUserNo();
	}

	// 브론즈 쿠폰 1개,실버 쿠폰 2개 insert
	@Override
	public int insertSilverCoupon(int userNo) {
		return mapper.insertSilverCoupon(userNo);
	}

	//누적 금액 10만원 이상 20만원 미만 고객 골드 업데이트
	@Override
	public int updateAllGold() {
		return mapper.updateAllGold();
	}

	// 골드 등급 고객 userNo 전체 조회
	@Override
	public List<User> selectGoldUserNo() {
		return mapper.selectGolduserNo();
	}

	//브론즈 쿠폰 1개,실버 쿠폰 2개,골드 쿠폰 3개 insert
	@Override
	public int insertAllGoldCoupon(int userNo) {
		return mapper.insertAllGoldCoupon(userNo);
	}

	// 누적 금액 20만원 이상 고객 플래티넘 업데이트
	@Override
	public int updateAllPlatinum() {
		return mapper.updateAllPlatinum();
	}

	//플래티넘 등급 고객 userNo 전체 조회
	@Override
	public List<User> platinumNoList() {
		return mapper.platinumNoList();
	}

	//브론즈 쿠폰 1개,실버 쿠폰 2개,골드 쿠폰 3개,플래티넘 쿠폰 2개 insert
	@Override
	public int insertAllPlatinumCoupon(int userNo) {
		return mapper.insertAllPlatinumCoupon(userNo);
	}

	//모든 유저 누적 금액 초기화
	@Override
	public int updateAllAmount() {
		return mapper.updateAllAmount();
	}

	//유저 전체 RATING 1 업데이트
	@Override
	public int updateRating1() {
		return mapper.updateRating1();
	}

	//해당 취소 티켓에 사용된 쿠폰 조회 
	@Override
	public int selectCouponNo(String ticketNo) {
		return mapper.selectCouponNo(ticketNo);
	}

	//취소시 사용한 쿠폰 환불
	@Override
	public int returnCoupon(int couponNo) {
		return mapper.returnCoupon(couponNo);
	}
	
	// 취소가능한지 확인
	@Override
	public int selectTicketCancelInfo(String ticketNo, int userNo) {
		
		int result = 0;
		
		// 현재 유저 정보 조회
		User currentUser = mapper.selectCurrentRating(userNo);
		// 현재 취소할 티켓 정보 조회
		Ticket cancelTicket = mapper.selectCancelPrice(ticketNo);
		
		int cancelPrice = Integer.parseInt(cancelTicket.getPayAmount());
		
		int afterAmount = currentUser.getUserAmount() - cancelPrice;
		
		int afterRating = 0;
		
		if(afterAmount >= 200000) {
			afterRating = 4;
		} else if (afterAmount >= 100000) {
			afterRating = 3;
		} else if (afterAmount >= 40000) {
			afterRating = 2;
		} else {
			afterRating = 1;
		}
		// 현재 유저 등급과 금액 차감했을 때 등급이 같은지 확인 
		// -> 금액이 다르다면 등급 업데이트 전 결제한 내역을 취소하는 것
		if(currentUser.getUserRating() != afterRating) {
			result = 1;
		}
		
		return result;
	}

}
