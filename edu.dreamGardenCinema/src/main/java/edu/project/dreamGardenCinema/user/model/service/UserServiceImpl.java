package edu.project.dreamGardenCinema.user.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.project.dreamGardenCinema.user.model.dao.UserDAO;
import edu.project.dreamGardenCinema.user.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO dao;

	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Override
	public User login(User inputMember) {

		User loginUser = dao.login(inputMember);
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
