package edu.kh.dgc.user.model.service;

import edu.kh.dgc.user.model.dto.User;
import jakarta.validation.Valid;

public interface UserService {
	
	User login(User inputMember);

	int signup(User inputUser);

	boolean checkOverlap(@Valid User inputUser);

	int changePw(String userPw, String userRePw, String userId);

}
