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


}
