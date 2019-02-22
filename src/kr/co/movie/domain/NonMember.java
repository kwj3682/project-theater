package kr.co.movie.domain;

//nm_name, serial, phone_number
public class NonMember {
	private String nmName;
	private int serial;
	private String phoneNumber;
	public String getNmName() {
		return nmName;
	}
	public void setNmName(String nmName) {
		this.nmName = nmName;
	}
	public int getSerial() {
		return serial;
	}
	public void setSerial(int serial) {
		this.serial = serial;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}

