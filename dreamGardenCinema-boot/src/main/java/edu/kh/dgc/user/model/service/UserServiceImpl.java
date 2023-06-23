package edu.kh.dgc.user.model.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.dgc.user.model.dao.UserMapper;
import edu.kh.dgc.user.model.dto.User;
import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Override
	public User login(User inputUser) {

		User loginUser = mapper.login(inputUser);
		System.out.println("암호화 확인 : " + bcrypt.encode(inputUser.getUserPw()));

		if (loginUser != null) {

			if (bcrypt.matches(inputUser.getUserPw(), loginUser.getUserPw())) {

				loginUser.setUserPw(null);

			} else { // 다를 경우
				loginUser = null;
			}
		}
		return loginUser;
	}

	// 회원 가입 서비스
	@Transactional(rollbackFor = { Exception.class })

	@Override
	public int signup(User inputUser) {
		String encPw = bcrypt.encode(inputUser.getUserPw());
		inputUser.setUserPw(encPw);

		return mapper.signup(inputUser);
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

	public boolean isPw(String str) {
	    return Pattern.matches("^(?=.*[a-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-z\\d$@$!%*#?&]{8,}$", str);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int changePw(String userPw, String userRePw, String userId) {
		
		if (userPw.equals(userRePw) && isPw(userPw) && isPw(userRePw)) {
			User user = new User();
			user.setUserPw(bcrypt.encode(userPw));
			user.setUserId(userId);
			return mapper.changePw(user);
		}
		return 0;
	}

	// 가입한 회원 회원번호 가져오기
	@Override
	public String selectNo(String userId) {
		return mapper.selectNo(userId);
	}

	// 가입한 회원 쿠폰 8개 삽입
	@Override
	public int insertCoupon(String selectNo) {
		return mapper.insertCoupon(selectNo);
	}

}
