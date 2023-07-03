package edu.kh.dgc.movie.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.movie.model.dto.MovieComment;
import edu.kh.dgc.movie.model.dto.Person;

@Mapper
public interface MovieDetailMapper {

	public Movie selectMovieDetail(int movieNo);

	public List<Person> selectMoviePerson(int movieNo);

	public List<String> selectMovieDirectorName(int movieNo);

	public List<String> selectMovieActorName(int movieNo, RowBounds rowBounds);

	public List<String> selectMovieStillCut(int movieNo);

	public List<MovieComment> selectMovieComment(int movieNo);





	

	

	


}
