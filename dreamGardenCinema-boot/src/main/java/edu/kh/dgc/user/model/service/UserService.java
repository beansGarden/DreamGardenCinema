package edu.kh.dgc.user.model.service;

import edu.kh.dgc.user.model.dto.User;

public interface UserService {
	
	User login(User inputMember);

	int signup(User inputUser);

}
