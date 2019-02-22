package kr.co.movie.controller.moviecontroller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.movie.common.db.MyAppSqlConfig;
import kr.co.movie.dao.MovieDAO;
import kr.co.movie.domain.Movie;

public class ListMovieController implements Controller{
	SimpleDateFormat sdf;
	private MovieDAO dao;
	public ListMovieController(MovieDAO d) {
		dao = d;
		SqlSession session = MyAppSqlConfig.getSqlSession();
		dao = session.getMapper(MovieDAO.class);		
		sdf = new SimpleDateFormat("yyyy/MM/dd");
		
	}
	public void service(){
		List<Movie> list =  dao.selectMovieList();
		System.out.println("--------------------------------------------------------");
		System.out.println("현재 상영중");
		System.out.println("--------------------------------------------------------");
		int i = 0;
		for(Movie m : list) {
			
			System.out.println((++i)+". "+ m.getTitle());
			System.out.println("영화 번호 : " +m.getMovieNo());
			System.out.println("개봉일: "+sdf.format(m.getReleaseDate()));
			System.out.println("시청 연령 :"+m.getRating()+"세 이상 관람가");
			System.out.println("내용: " + m.getContent());
			System.out.println("--------------------------------------------------------");
		}
		
	}
}
