package edu.kh.dgc.user.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.dgc.user.model.dao.AjaxMapper;
import edu.kh.dgc.user.model.dto.User;
@Service
public class AjaxServiceImpl implements AjaxService{

	@Autowired
	private AjaxMapper mapper;
	
	// 아이디 중복 검사
	@Override
	public int checkId(String id) {
		return mapper.checkId(id);
	}

	// 이메일 중복 검사
	@Override
	public int checkEmail(String email) {
		return mapper.checkEmail(email);
	}

	// 닉네임 중복 검사
	@Override
	public int checkNickname(String nickname) {
		return mapper.checkNickname(nickname);
	}

	@Override
	public String idFind(User inputUser) {
		return mapper.idFind(inputUser);
	}

	


	
}
