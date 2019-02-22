package kr.co.movie.domain;

import java.util.Date;

//seatNo, theaterNo, seatName, isRes
public class Seat {
	private int seatNo;
	private int theaterNo;
	private Date playTime;
	private int isRes;
	private int seatName;
	private int seatCnt;
	private String playTimeString;

	public String getPlayTimeString() {
		return playTimeString;
	}
	public void setPlayTimeString(String playTimeString) {
		this.playTimeString = playTimeString;
	}
	public int getSeatName() {
		return seatName;	
	}
	public void setSeatName(int num) {
		seatName = num;
	}
	public Date getPlayTime() {
		return playTime;
	}
	public void setPlayTime(Date playTime) {
		this.playTime = playTime;
	}
	public int getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
	public int getTheaterNo() {
		return theaterNo;
	}
	public void setTheaterNo(int theaterNo) {
		this.theaterNo = theaterNo;
	}
	public int getIsRes() {
		return isRes;
	}
	public void setIsRes(int isRes) {
		this.isRes = isRes;
	}
}
