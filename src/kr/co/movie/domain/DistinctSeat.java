package kr.co.movie.domain;

import java.util.Date;

public class DistinctSeat {
	private Date playTime;
	private int theaterNo;
	private String playTimeString;
	
	public String getPlayTimeString() {
		return playTimeString;
	}
	public void setPlayTimeString(String playTimeString) {
		this.playTimeString = playTimeString;
	}
	private int seatCnt;
	public int getTheaterNo() {
		return theaterNo;
	}
	public void setTheaterNo(int theaterNo) {
		this.theaterNo = theaterNo;
	}
	public Date getPlayTime() {
		return playTime;
	}
	public void setPlayTime(Date playTime) {
		this.playTime = playTime;
	}
	public int getSeatCnt() {
		return seatCnt;
	}
	public void setSeatCnt(int seatCnt) {
		this.seatCnt = seatCnt;
	}
}
