package edu.kh.dgc.notice.model.service;

import java.util.List;

import edu.kh.dgc.notice.model.dto.Notice;

public interface NoticeService {

	List<Notice> selectNoticeList();

}
