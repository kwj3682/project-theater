package kr.co.movie.domain;

import java.util.Date;

//reservation_no
//seat_no
//serial
//people
//id
///seat_name_tag

public class Reservation {
	private String id;
	private int reservationNo;
	private int seatNo;
	private int serial;
	private int people;
	private int theaterNo;
	private String seatNameTag;
	private String title;
	private Date playTime;
	
	public int getTheaterNo() {
		return theaterNo;
	}
	public void setTheaterNo(int theaterNo) {
		this.theaterNo = theaterNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getPlayTime() {
		return playTime;
	}
	public void setPlayTime(Date playTime) {
		this.playTime = playTime;
	}
	public String getSeatNameTag() {
		return seatNameTag;
	}
	public void setSeatNameTag(String seatNameTag) {
		this.seatNameTag = seatNameTag;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getReservationNo() {
		return reservationNo;
	}
	public void setReservationNo(int reservationNo) {
		this.reservationNo = reservationNo;
	}
	public int getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
	public int getSerial() {
		return serial;
	}
	public void setSerial(int serial) {
		this.serial = serial;
	}
	public int getPeople() {
		return people;
	}
	public void setPeople(int people) {
		this.people = people;
	}
}
