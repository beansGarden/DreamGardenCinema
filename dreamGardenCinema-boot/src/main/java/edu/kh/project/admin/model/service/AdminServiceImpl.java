package edu.kh.project.admin.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.project.admin.model.dao.AdminDAO;
import edu.kh.project.qna.model.dto.Qna;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO dao;
	
	@Override
	public List<Qna> adminQnaList() {
	
		
		
		return dao.adminQnaList();
	}

}
