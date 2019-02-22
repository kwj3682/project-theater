package kr.co.movie.controller.moviecontroller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.co.movie.common.db.MyAppSqlConfig;
import kr.co.movie.dao.ReservationDAO;
import kr.co.movie.dao.SeatDAO;
import kr.co.movie.domain.Reservation;

public class ListMovieReservationController extends Controllers {
	private String id;
	private int serial;
	private ReservationDAO dao;
	private List<Reservation> list;
	private SimpleDateFormat sdf;
	private DeleteReservationController dc;
	public ListMovieReservationController(String id) {
		this.id = id;
		serial = 0;
		SqlSession session = MyAppSqlConfig.getSqlSession();
		dao = session.getMapper(ReservationDAO.class);
		list = new ArrayList<>();
		sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		dc = new DeleteReservationController(dao);
	}
	public ListMovieReservationController(int s) {
		this.serial = s;
		id = null;
		SqlSession session = MyAppSqlConfig.getSqlSession();
		dao = session.getMapper(ReservationDAO.class);
		list = new ArrayList<>();
		sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		dc = new DeleteReservationController(dao);
	}

	public void service() {
//		mm.title, s.theater_no, s.play_time, r.seat_name_tag , r.people 
		if ( id != null ) list = dao.selectReservationListById(id);
		else if (serial != 0 ) list = dao.selectReservationListBySerial(serial);
		List<Integer> seatNoList = new ArrayList<>();
		List<Integer> reservationNoList = new ArrayList<>();
		
		System.out.println("--------------------------------------------");
		while(true) {
			for(Reservation r : list) {
				System.out.println("예매 코드 : "+r.getReservationNo()+"  :  "+r.getTitle() + " " + r.getTheaterNo() + "관 " + sdf.format(r.getPlayTime()) + " " + r.getSeatNameTag() + " "+r.getPeople()+"명"); 
				seatNoList.add(r.getSeatNo());
				reservationNoList.add(r.getReservationNo());
			}
			System.out.println("--------------------------------------------");
			int sel = dc.service(seatNoList,reservationNoList);
			if(sel == 1) {
				return;
			}
			else if(sel == -1) {
				continue;
			}else {
				return;
			}
			
		}
		
	}
	
}
