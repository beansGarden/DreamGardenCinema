package edu.kh.dgc.notice.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.dgc.notice.model.dao.NoticeMapper;
import edu.kh.dgc.notice.model.dto.Notice;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper mapper;
	
	@Override
	public List<Notice> selectNoticeList() {
		return mapper.selectNoticeList();
	}

}
