package edu.kh.dgc.mypage.model.service;

import org.springframework.stereotype.Service;

import edu.kh.dgc.mypage.model.dao.MypageMapper;
import edu.kh.dgc.ticketing.model.dao.TicketingMapper;

@Service
public class MypageServiceImpl implements MypageService{

	private MypageMapper mapper;
}
