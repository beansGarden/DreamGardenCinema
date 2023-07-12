package edu.kh.dgc.user.model.service;

import edu.kh.dgc.user.model.dto.User;

public interface AjaxService {

	/** 아이디 중복 검사
	 * @param id
	 * @return result
	 */
	int checkId(String id);
	
	/** 이메일 중복 검사
	 * @param email
	 * @return result
	 */
	int checkEmail(String email);

	/**	닉네임 중복 검사
	 * @param nickname
	 * @return result
	 */
	int checkNickname(String nickname);

	
	String idFind(User inputUser);






}
