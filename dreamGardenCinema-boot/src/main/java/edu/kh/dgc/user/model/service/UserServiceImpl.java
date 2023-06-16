package edu.kh.dgc.user.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.dgc.user.model.dao.UserMapper;
import edu.kh.dgc.user.model.dto.User;
import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	@Override
	public User login(User inputUser) {

		User loginUser = mapper.login(inputUser);
//		System.out.println("암호화 확인 : " + bcrypt.encode(inputUser.getUserPw()));
//
//		if (inputUser != null) {
//
//			if (bcrypt.matches(inputUser.getUserPw(), inputUser.getUserPw())) {
//
//				inputUser.setUserPw(null);
//
//			} else { // 다를 경우
//				inputUser = null;
//			}
//		}
		return loginUser;
	}

	// 회원 가입 서비스
	@Transactional(rollbackFor = { Exception.class })

	@Override
	public int signup(User inputUser) {
//		String encPw = bcrypt.encode(inputMember.getMemberPw());
//		inputMember.setMemberPw(encPw);

		int result = mapper.signup(inputUser);

		return result;
	}

	@Override
	public boolean checkOverlap(@Valid User inputUser) {
		int idreSult = mapper.checkOverlapId(inputUser);
		int emailreSult = mapper.checkOverlapEmail(inputUser);

		if (idreSult != 0 || emailreSult != 0) {
			return false;
		}
		return true;

	}

}
