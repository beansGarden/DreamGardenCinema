package edu.kh.project.admin.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.qna.model.dto.Qna;

@Mapper
public interface AdminMapper {

	List<Qna> adminQnaList();

}
