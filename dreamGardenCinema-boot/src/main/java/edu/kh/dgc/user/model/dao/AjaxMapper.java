package edu.kh.dgc.user.model.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AjaxMapper {
	
	// 아이디 중복 검사
	public int checkId(String id);
	
	// 이메일 중복 검사
	public int checkEmail(String email);

	// 닉네임 중복 검사
	public int checkNickname(String nickname);


}