package edu.kh.dgc.notice.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.dgc.notice.model.dto.Notice;

@Mapper
public interface NoticeMapper {

	List<Notice> selectNoticeList();
}
