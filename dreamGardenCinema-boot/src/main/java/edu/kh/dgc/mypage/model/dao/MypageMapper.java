package edu.kh.dgc.mypage.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.dgc.qna.model.dto.Qna;
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


}
