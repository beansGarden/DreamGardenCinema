package edu.kh.dgc.mypage.model.service;

import java.util.List;

import edu.kh.dgc.qna.model.dto.Qna;
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

}
