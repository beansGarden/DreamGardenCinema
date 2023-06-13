package edu.kh.project.user.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.user.model.dao.UserMapper;
import edu.kh.project.user.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

//	@Autowired
//	private BCryptPasswordEncoder bcrypt;

	@Override
	public User login(User inputUser) {

		User loginUser = mapper.login(inputUser);
//		System.out.println("암호화 확인 : " + bcrypt.encode(inputMember.getUserPw()));

//		if (loginMember != null) {
//
//			if (bcrypt.matches(inputMember.getUserPw(), loginMember.getUserPw())) {
//
//				loginMember.setUserPw(null);
//
//			} else { // 다를 경우
//				loginMember = null;
//			}
//		}
		return loginUser;
	}

	// 회원 가입 서비스
	@Transactional(rollbackFor = { Exception.class })
	// 예외 발생 시 rollback
	// 발생 안하면 서비스 종료 시 commit

	@Override
	public int signup(User inputUser) {
//		String encPw = bcrypt.encode(inputMember.getMemberPw());
//		inputMember.setMemberPw(encPw);

		// DAO 호출
		int result = mapper.signup(inputUser);

		return result;
	}

}
