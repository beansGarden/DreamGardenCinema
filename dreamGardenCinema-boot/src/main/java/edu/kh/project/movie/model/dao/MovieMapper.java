package edu.kh.project.movie.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.movie.model.dto.Movie;

@Mapper
public interface MovieMapper {

	List<Map<String, Object>> movie();

}
