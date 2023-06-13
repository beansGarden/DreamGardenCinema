package edu.kh.dgc.user.model.service;

public interface AjaxService {

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

	/** 닉네임이 일부라도 일치하는 모든 회원 조회 (40명)
	 * @param input
	 * @return List
	 */

	int checkUrl(String URL);
	



}
