package kr.co.movie.controller.moviecontroller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.movie.common.db.MyAppSqlConfig;
import kr.co.movie.dao.MovieDAO;
import kr.co.movie.dao.SeatDAO;
import kr.co.movie.dao.TheaterDAO;
import kr.co.movie.domain.Seat;

public class SeatAdmin extends Controllers{
	private SimpleDateFormat sdf;
	private SeatDAO sDAO;
	private TheaterDAO td;
	private MovieDAO md;
	public SeatAdmin() {
		
		sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		
		SqlSession session = MyAppSqlConfig.getSqlSession();
		sDAO= session.getMapper(SeatDAO.class);
		td = session.getMapper(TheaterDAO.class);
		md = session.getMapper(MovieDAO.class);
	}
	//association
	public void selectSeatList() {
		List<Seat> list = sDAO.selectSeatList();
		for(Seat s : list) {
			System.out.println(s.getSeatNo() + "-" + s.getTheaterNo() + "-"+s.getPlayTime() + "-" + s.getIsRes());
		}
	}
	public void service() {
		
	}
	
	/*
	public void test02() {
		Seat seat = dao.selectSeatByNo(1);
		char yOrN = 'N';
		System.out.println();
		System.out.println("좌석 정보");
		System.out.println("-------------------");
		System.out.println("상영관 : " + seat.getTheaterNo()+"관");
		System.out.println("좌셕 번호 : " +  seat.getSeatName());
		if(seat.getIeRes() == 2) {
			yOrN = 'Y';
		}
		System.out.println("예매 여부: " + yOrN);
		System.out.println("-------------------");
	}
	*/
	
	public void insertSeatAll() throws Exception {
		int theaterNo = Integer.parseInt(input("theater no : "));
		int seatCnt = td.getSeatCnt(theaterNo);
		
		
		Date date =  sdf.parse(input("input date[yyyy/MM/dd HH:mm]  : "));
		
		String s = td.getReDate(theaterNo);
//		System.out.println(s);
		Date reDate = sdf.parse(s);
		Date today = new Date();
		
		
		//앞에 변수가 크면 1, 작으면 -1, 같으면 0
		int flag = date.compareTo(today);
		int flag2 = date.compareTo(reDate);
		
		if(flag == -1 ) {
			System.out.println("상영 시간은 현재 시간보다 뒤로 설정되어야합니다.");
			return;
		}
		
		if(flag2 == -1) {
			System.out.println("상영 시간은 개봉일보다 뒤로 설정되어야합니다.");
			return;			
		}
		
		
		int cnt = 0;
		
		for( int i = 1; i<=seatCnt; i++) {			
			cnt++;
			Seat seat = new Seat();
			seat.setSeatName(i);
			seat.setTheaterNo(theaterNo);
			seat.setPlayTime(date);	
			sDAO.insertSeatAll(seat);
			System.out.print(".");
			if(i%10 == 0 ) {
				System.out.println();
			}
		}
		System.out.println(cnt+"개의 좌석이 생성되었습니다.");
	}
	
	public void deleteSeatAll(int theaterNo) {
		System.out.println("삭제 중입니다.");
		sDAO.deleteSeatAll(theaterNo);
		System.out.println("삭제되었습니다.");
	}
	public static void main(String[] args) {
		SeatAdmin sa = new SeatAdmin();
		sa.deleteSeatAll(4);
	}
}
