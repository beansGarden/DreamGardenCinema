package edu.kh.dgc.ticketing.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.dgc.ticketing.model.dto.Schedule;

@Mapper
public interface TicketingMapper {

	List<Schedule> movieTime(Map<String, Integer> paramMap);

	Schedule mainMovieTime(int movieNo);

}
