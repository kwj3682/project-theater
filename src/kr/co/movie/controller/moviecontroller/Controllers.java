package kr.co.movie.controller.moviecontroller;

import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import kr.co.movie.common.db.MyAppSqlConfig;
import kr.co.movie.dao.ReservationDAO;
import kr.co.movie.dao.SeatDAO;

public abstract class Controllers implements Controller {
	public Scanner sc = new Scanner(System.in);
	public Controllers() {
		ReservationDAO rDAO; 
		SeatDAO sDAO;
		SqlSession session = MyAppSqlConfig.getSqlSession();
		rDAO = session.getMapper(ReservationDAO.class);
		rDAO.cleanReservationBeforeCurrent();
		sDAO = session.getMapper(SeatDAO.class);
		sDAO.cleanSeat();
	}
	
	protected String input(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}
}
