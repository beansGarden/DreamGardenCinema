package edu.kh.project.user.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.user.model.dto.User;


@Repository
public class UserDAO {
	

	public User login(User inputMember) {
//		return sqlSession.selectOne("userMapper.login", inputMember);
		return null;
	}

}
