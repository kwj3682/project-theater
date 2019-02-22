package kr.co.movie.dao;

import java.util.List;

import kr.co.movie.domain.Reservation;

public interface ReservationDAO {
	List<Reservation> selectReservationList();
	void insertReservation(Reservation reservation);
	void insertReservation2(Reservation reservation);
	
	List<Reservation> selectReservationListById(String id);
	List<Reservation> selectReservationListBySerial(int serial);
	
	void deleteReservation(int reservationNo);
	
	void cleanReservationBeforeCurrent();
}
