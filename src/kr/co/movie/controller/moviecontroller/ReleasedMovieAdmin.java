package kr.co.movie.controller.moviecontroller;

import java.text.SimpleDateFormat;

import org.apache.ibatis.session.SqlSession;

import kr.co.movie.common.db.MyAppSqlConfig;
import kr.co.movie.dao.MovieDAO;
import kr.co.movie.dao.ReleasedMovieDAO;
import kr.co.movie.domain.Movie;
import kr.co.movie.domain.ReleasedMovie;

public class ReleasedMovieAdmin extends Controllers{
	private SimpleDateFormat sdf;
	private SeatAdmin seatAdmin;
	private ReleasedMovieDAO dao;
	private MovieDAO md;
	private MovieDAO mDAO;
	public ReleasedMovieAdmin() {
		seatAdmin = new SeatAdmin();
		sdf = new SimpleDateFormat("yyyy/MM/dd");
		SqlSession session = MyAppSqlConfig.getSqlSession();
		dao = session.getMapper(ReleasedMovieDAO.class);
		md = session.getMapper(MovieDAO.class);
	}
	/*
	movieNo, title, content,releaseDate, rating
	 */
	public void insertMovie() {
		Movie m = new Movie();
		m.setTitle(input("제목을 입력하세요 : "));
		m.setContent(input("내용을 입력하세요 : "));
		try {
			m.setReleaseDate(sdf.parse(input("날짜 입력 : [yyyy/MM/dd] : ")));			
		}catch(Exception e) {
			e.printStackTrace();
		}
		m.setRating(Integer.parseInt(input("시청 가능 연령 : ")));
		md.insertMovieToList(m);
	}
	
	public void deleteMovie() {
		System.out.println("////////////////////////////////////////////////////");
		 new ListMovieController(mDAO).service();
		 System.out.println("////////////////////////////////////////////////////");
		 int movieNo = Integer.parseInt(input("삭제할 영화 번호를 선택해주세요 : "));
		 md.deleteMovieFromList(movieNo);
	}
	
	
	
	public void insertReleasedMovie() {
		ReleasedMovie rm = new ReleasedMovie();
		rm.setMovieNo(Integer.parseInt(input("setMovieNo:")));
		rm.setTheaterNo(Integer.parseInt(input("setTheaterNo:")));
		dao.insertReleasedMovieList(rm);
		System.out.println("개봉 중 영화 삽입 완료.");
	}
	
	
	
	
	
	public void delete() {	
		dao.deleteReleasedMovieList(Integer.parseInt(sc.nextLine()));
	}
	
	public void service() throws Exception {
//		insertReleasedMovie();
		
		System.out.println("///////////////////////////////////////////////////");
		System.out.println("관리자 페이지입니다.");
		System.out.println("///////////////////////////////////////////////////");
		while(true) {
			System.out.println("1. 영화 추가");
			System.out.println("2. 영화 삭제");
			
			System.out.println("3. 상영관에 영화 할당");
//			System.out.println("4. 상영관 - 영화 할당 삭제");
			
			System.out.println("4. 영화 상영 시간 추가");
			System.out.println("0. 이전");
			System.out.println("///////////////////////////////////////////////////");
			int sel = Integer.parseInt(input("입력 : "));
			switch(sel) {
			case 1:
				insertMovie();
				break;
			case 2:
				deleteMovie();
				break;
			case 3:
				insertReleasedMovie();
				break;
			case 4:
				seatAdmin.insertSeatAll();
				break;
//			case 5:
//				break;
			case 0:
				return;
				
			default : System.out.println("잘못된 입력입니다.");
			}
		}
		
//		System.out.println("영화관에 상영할 영화를 할당하시겠습니까?");
	}
	public static void main(String[] args) throws Exception{
		
		new ReleasedMovieAdmin().service();
	}
}
