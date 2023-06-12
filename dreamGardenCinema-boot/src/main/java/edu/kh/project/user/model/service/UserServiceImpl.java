package edu.kh.project.user.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


	
}
