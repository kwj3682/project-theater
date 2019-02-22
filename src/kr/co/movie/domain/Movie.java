package kr.co.movie.domain;

import java.util.Date;

// movie_no, title, content, release_date, rating
public class Movie {
	private int movieNo; 		//영화 등록 번호
	private String title;		//제목
	private String content; 	//내용
	private Date releaseDate;  //개봉일
	private int rating;			//시청연령
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	
}
