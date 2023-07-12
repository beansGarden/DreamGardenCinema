package edu.kh.dgc.user.model.service;

import edu.kh.dgc.user.model.dto.User;
import jakarta.validation.Valid;

public interface UserService {
	
	User login(User inputMember);

	int signup(User inputUser);

	boolean checkOverlap(@Valid User inputUser);

	int changePw(String userPw, String userRePw, String userId);

	/** 가입한 회원 회원 번호 가져오기
	 * @param userId
	 * @return
	 */
	String selectNo(String userId);

	/** 가입한 회원 쿠폰 8개 삽입
	 * @param selectNo
	 * @return
	 */
	int insertCoupon(String selectNo);

}
