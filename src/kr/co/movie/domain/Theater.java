package kr.co.movie.domain;
//theater_no, price, seat_cnt
public class Theater {
	private int theaterNo;
	private int price;
	private int seatCnt;
	public int getTheaterNo() {
		return theaterNo;
	}
	public void setTheaterNo(int theaterNo) {
		this.theaterNo = theaterNo;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSeatCnt() {
		return seatCnt;
	}
	public void setSeatCnt(int seatCnt) {
		this.seatCnt = seatCnt;
	}
}
