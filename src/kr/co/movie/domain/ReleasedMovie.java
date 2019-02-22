package kr.co.movie.domain;

import java.util.Date;

//released_movie_no, theater_no, movie_no
//r.movie_no, th.theater_no, m.title, m.release_date, m.rating, th.seat_cnt
public class ReleasedMovie {
	private int releasedMovieNo;
	private int theaterNo;
	private int movieNo;
	private String title;
	private Date releaseDate;
	private int rating;
	private int seatCnt;
	public int getReleasedMovieNo() {
		return releasedMovieNo;
	}
	public void setReleasedMovieNo(int releasedMovieNo) {
		this.releasedMovieNo = releasedMovieNo;
	}
	public int getTheaterNo() {
		return theaterNo;
	}
	public void setTheaterNo(int theaterNo) {
		this.theaterNo = theaterNo;
	}
	public int getMovieNo() {
		return movieNo;
	}
	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getSeatCnt() {
		return seatCnt;
	}
	public void setSeatCnt(int seatCnt) {
		this.seatCnt = seatCnt;
	}
}

