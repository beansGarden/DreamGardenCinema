package edu.kh.dgc.mypage.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.dgc.mypage.model.dao.MypageMapper;
import edu.kh.dgc.mypage.model.dto.Coupon;
import edu.kh.dgc.qna.model.dto.Qna;
import edu.kh.dgc.ticketing.model.dao.TicketingMapper;
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

}
