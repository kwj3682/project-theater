package kr.co.movie.dao;

import java.util.List;

import kr.co.movie.domain.Movie;
import kr.co.movie.domain.ReleasedMovie;
import kr.co.movie.domain.Seat;

public interface MovieDAO {
	List<Movie> selectMovieList();	
	List<ReleasedMovie> selectReleasedMovieList();
	
	ReleasedMovie selectMovieByNo(int no);
	
	void insertMovieToReservation(Movie movie);

	void deleteMovieFromList(int movieNo);
	void insertMovieToList(Movie movie);
	
}
