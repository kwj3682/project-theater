package kr.co.movie.dao;

import java.util.List;

import kr.co.movie.domain.DistinctSeat;
import kr.co.movie.domain.Seat;

//seat_no, theater_no, seat_name, is_res
public interface SeatDAO {
	List<Seat> selectSeatList();
	List<DistinctSeat> selectSeatListDistinct(DistinctSeat dseat);
	DistinctSeat selectSeatListDistinctByNo(DistinctSeat dseat);
	int getSeatCnt(int theaterNo);
//	Seat selectSeatByNo(int no);
	void insertSeatAll(Seat seat);
	void deleteSeatAll(int no);
	
	void deleteSeatByPlayTime(Seat seat);
	
	Seat selectSeatBySeatName(Seat seat);
	
	void updateSeatIsRes(int seatNo);
	
	void cleanSeat();
	
	
}
