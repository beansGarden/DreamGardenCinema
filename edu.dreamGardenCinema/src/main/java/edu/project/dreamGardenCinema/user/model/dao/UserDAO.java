package edu.project.dreamGardenCinema.user.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.dreamGardenCinema.user.model.dto.User;

@Repository
public class UserDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public User login(User inputMember) {
		return sqlSession.selectOne("userMapper.login", inputMember);
	}

}
