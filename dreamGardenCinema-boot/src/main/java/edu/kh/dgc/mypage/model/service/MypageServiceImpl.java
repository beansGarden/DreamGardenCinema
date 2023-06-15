package edu.kh.dgc.mypage.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.dgc.mypage.model.dao.MypageMapper;
import edu.kh.dgc.qna.model.dto.Qna;
import edu.kh.dgc.ticketing.model.dao.TicketingMapper;

@Service
public class MypageServiceImpl implements MypageService{

	@Autowired
	private MypageMapper mapper;

	//내 문의 내역 조회
	@Override
	public List<Qna> myQnaList(int userNo) {
		
		return mapper.myQnaList(userNo);
	}	
}
