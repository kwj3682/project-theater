package kr.co.movie.dao;

import java.util.Date;

import kr.co.movie.domain.Movie;
import kr.co.movie.domain.ReleasedMovie;

public interface ReleasedMovieDAO {

	void insertReleasedMovieList(ReleasedMovie rm);
	void deleteReleasedMovieList(int no);
	
	
	/*
	movieNo, title, content,releaseDate, rating
	 */
	
}
