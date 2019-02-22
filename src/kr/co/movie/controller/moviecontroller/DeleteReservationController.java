package kr.co.movie.controller.moviecontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import kr.co.movie.common.db.MyAppSqlConfig;
import kr.co.movie.dao.ReservationDAO;
import kr.co.movie.dao.SeatDAO;

public class DeleteReservationController{
	private SeatDAO seatDAO;
	private ReservationDAO reservationDAO;
	private Scanner sc;
	public DeleteReservationController(ReservationDAO rDAO) {
		reservationDAO = rDAO;
		SqlSession session = MyAppSqlConfig.getSqlSession();
		seatDAO = session.getMapper(SeatDAO.class);
		sc = new Scanner(System.in);
	}
	protected String input(String str) {
		System.out.print(str);
		return sc.nextLine();
	}
	public int service(List<Integer> seatNoList, List<Integer> reservationNoList) {
		int sel = Integer.parseInt(input("예매를 취소하려면 예매 코드를 입력하세요. [취소 : 0] 입력 : "));
		if(sel == 0) {
			return 0;
		}else {
			for(int i = 0; i < reservationNoList.size(); i++) {
				
				if(reservationNoList.get(i) == sel) {
					seatDAO.updateSeatIsRes(seatNoList.get(i));
					reservationDAO.deleteReservation(sel);
					System.out.println("예매가 취소되었습니다.");
					return 1;
				}
			}
			System.out.println("입력하신 코드에 해당하는 예매가 존재하지 않습니다.");
			return -1;
		}
	}
}
